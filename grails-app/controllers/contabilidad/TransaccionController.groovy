package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class TransaccionController {

    def springSecurityService
    def folioService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(controller:'poliza', action: "lista", params: params)
    }

    def nueva = {
        Transaccion.withTransaction {
            def usuario = springSecurityService.currentUser
            def poliza = Poliza.get(params.id)
            def transaccion = new Transaccion (
                folio : folioService.temporal()
                , descripcion : 'Introduzca una descripción'
                , poliza : poliza
                , empresa : usuario.empresa
            ).save()

            if (poliza.tipo == 'INGRESOS') {
                render(view:'ingresos', model:[transaccion:transaccion])
            } else {
                render(view:'edita',model:[transaccion:transaccion])
            }
        }
    }

    def edita = {
        def transaccion = Transaccion.get(params.id)
        if (transaccion) {
            if (transaccion.poliza.tipo == 'INGRESOS') {
                def origenes = obtieneMovimientos(transaccion.origenes)
                def destinos = obtieneMovimientos(transaccion.destinos)
                render(view:'ingresos', model:[transaccion:transaccion,origenes:origenes,destinos:destinos])
            } else {
                return [transaccion: transaccion]
            }
        }
    }

    def actualiza = {
        Transaccion.withTransaction {
            def transaccion = Transaccion.get(params.id)
            if (transaccion) {
                def importeAnterior = transaccion.importe
                transaccion.properties = params
                transaccion.importe = importeAnterior
                def usuario = springSecurityService.currentUser
                transaccion.empresa = usuario.empresa

                if (params.importe) {
                    if (params.cuentaId) {
                        def cuenta = Cuenta.findByOrganizacionAndId(usuario.empresa.organizacion, params.cuentaId)
                        if (cuenta) {
                            def importe = new BigDecimal(params.importe)
                            def movimiento = new Movimiento (
                                cuenta : cuenta
                                , importe : importe
                            )
                            def auxiliar
                            if (params.auxiliarId) {
                                auxiliar = Auxiliar.findByOrganizacionAndId(usuario.empresa.organizacion, params.auxiliarId)
                                movimiento.auxiliar = auxiliar
                            }
                            if (params.esDebe) {
                                // Valido si esta distribuyendo los pagos
                                // Si quiere distribuir algo que no esta pagado
                                // no lo deja
                                if (transaccion.importe >= importe) {
                                    // Si el importe de la distribucion es igual
                                    // a lo que llevo en la transaccion
                                    // no importan los origenes, solo crea uno nuevo
                                    if (transaccion.importe == importe) {
                                        def origenes = new ArrayList(transaccion.origenes)
                                        transaccion.origenes.clear()
                                        for(origen in origenes) {
                                            origen.delete()
                                        }
                                        transaccion.addToOrigenes(movimiento)
                                    } else {
                                        // Si la cantidad es menor, entonces hay que distribuirlos
                                        // Primero valido si ya existe entre los movimientos
                                        // alguno a la misma cuenta, de ser asi, le sumo el importe
                                        // creo uno nuevo con este importe y borro el anterior.
                                        def x
                                        for(origen in transaccion.origenes) {
                                            if ((movimiento.auxiliar && origen.auxiliar == movimiento.auxiliar)
                                                    || (!movimiento.auxiliar && movimiento.cuenta == origen.cuenta)) {
                                                x = origen
                                            }
                                        }
                                        if (x) {
                                            movimiento.importe = movimiento.importe.add(importe)
                                            transaccion.origenes.remove(x)
                                            x.delete()
                                        }
                                        for(origen in transaccion.origenes) {
                                            if (origen.importe < importe) {
                                                // Si el importe es mayor a este movimiento
                                                // elmimina el movimiento, restale al importe
                                                // y ve a la siguiente iteracion para hacer
                                                // nuevamente la comparacion
                                                importe = importe.subtract(origen.importe)
                                                transaccion.origenes.remove(origen)
                                                origen.delete()
                                            } else if (origen.importe == importe) {
                                                // Si el importe ya es igual a este movimiento
                                                // solo eliminalo y crea el nuevo movimiento
                                                transaccion.origenes.remove(origen)
                                                origen.delete()
                                                transaccion.addToOrigenes(movimiento)
                                                break;
                                            } else {
                                                // Si el importe es menor a este movimiento
                                                // restale el importe y crea el nuevo movimiento
                                                origen.importe = origen.importe.subtract(importe)
                                                transaccion.addToOrigenes(movimiento)
                                                break;
                                            }
                                        }
                                    }
                                    transaccion.save(flush:true)
                                } else {
                                    // No se puede distribuir dinero que no se ha especificado
                                    // a quien se le va a entregar
                                    transaccion.discard()
                                    flash.message = "El importe no puede ser mayor a ${transaccion.importe}"
                                    redirect(action:"edita", id:transaccion.id)
                                    return
                                }
                            } else {
                                def movimiento2
                                def cuenta2 = Cuenta.findByOrganizacionAndCodigo(usuario.empresa.organizacion,'1104')
                                for(origen in transaccion.origenes) {
                                    if (origen.cuenta == cuenta2) {
                                        movimiento2 = origen
                                        movimiento2.importe = movimiento2.importe.add(importe)
                                        break;
                                    }
                                }
                                if (!movimiento2) {
                                    movimiento2 = new Movimiento (
                                        cuenta: cuenta2
                                        , importe: importe
                                    )
                                    if (cuenta2.tieneAuxiliares) {
                                        movimiento2.auxiliar = Auxiliar.findByCuenta(cuenta2)
                                    }
                                }
                                transaccion.addToOrigenes(movimiento2)
                                transaccion.addToDestinos(movimiento)
                                transaccion.importe = transaccion.importe.add(importe)
                                transaccion.save(flush:true)
                            }
                        } // si encontro la cuenta
                    } // si trae una cuenta
                } // si trae importe
                flash.message = message(code:'transaccion.actualiza.message')
                redirect(action:'edita',id:transaccion.id)
            } // Si se encuentra la transaccion
        } // Termina transaccion
    }

    def cuentas = {
        def usuario = springSecurityService.currentUser
        def auxiliares = Auxiliar.buscaPorFiltro(params.term, usuario.empresa.id).list([max:10])
        def cuentas = Cuenta.buscaPorFiltro(params.term, usuario.empresa.id).list([max:10])
        def lista = []
        for(auxiliar in auxiliares) {
            lista << [id:auxiliar.cuenta.id,value:"${auxiliar.numero} | ${auxiliar.descripcion} (AUXILIAR)",auxiliares:false,auxiliar:true,cuenta:"${auxiliar.cuenta.numero} | ${auxiliar.cuenta.descripcion}",auxiliarId:auxiliar.id]
        }
        for(cuenta in cuentas) {
            lista << [id:cuenta.id,value:"$cuenta.numero | $cuenta.descripcion",auxiliares:cuenta.tieneAuxiliares,auxiliar:false]
        }
        def resultado = lista as grails.converters.JSON
        render resultado
    }

    def auxiliares = {
        params.filtro = params.term
        def auxiliares = Auxiliar.buscaPorFiltro(params.term, usuario.empresa.id).list([max:10])
        def lista = []
        for(auxiliar in auxiliares) {
            lista << [id:auxiliar.id,value:"${auxiliar.numero} | ${auxiliar.descripcion}"]
        }
        def resultado = lista as grails.converters.JSON
        render resultado
    }

    def obtieneMovimientos = { lista ->
		def resultado = []
		def cuentas = [:] as TreeMap
		def movimientos = [:] as TreeMap
		for(movimiento in lista) {
			def cuenta = cuentas[movimiento.cuenta.id]
			if (!cuenta) {
				cuenta = [movimiento.cuenta, new BigDecimal('0')]
				cuentas[movimiento.cuenta.id] = cuenta
			}
			cuenta[1] = cuenta[1].add(movimiento.importe)
			
			def mov = movimientos[movimiento.cuenta.id]
			if (!mov) {
				movimientos[movimiento.cuenta.id] = []
			}
			movimientos[movimiento.cuenta.id] << movimiento
		}
		
		for (id in cuentas.keySet()) {
			def encabezado = cuentas[id]
			if (encabezado[0].tieneAuxiliares) {
				resultado << new Movimiento(cuenta:encabezado[0],importe:encabezado[1], padre:true)
                def size = movimientos[id].size()
                def movimiento = movimientos[id][size-1]
                movimiento.ultimo = true
				resultado.addAll(movimientos[id])
			} else {
				resultado << new Movimiento(cuenta:encabezado[0],importe:encabezado[1])
			}
		}

        return resultado
    }

}
