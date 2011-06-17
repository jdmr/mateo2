package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import general.Tag

@Secured(['ROLE_USER'])
class TransaccionController {

    def springSecurityService
    def folioService

    static allowedMethods = [crea: "POST", actualiza: "POST"]

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
            } else if (poliza.tipo == 'EGRESOS') {
                render(view:'egresos', model:[transaccion:transaccion])
            } else if (poliza.tipo == 'DIARIO') {
                render(view:'diario', model:[transaccion:transaccion])
            } else {
                render(view:'edita',model:[transaccion:transaccion])
            }
        }
    }

    def edita = {
        def transaccion = Transaccion.get(params.id)
        if (transaccion) {
            def origenes = obtieneMovimientos(transaccion.origenes)
            def destinos = obtieneMovimientos(transaccion.destinos)
            if (transaccion.poliza.tipo == 'INGRESOS') {
                render(view:'ingresos', model:[transaccion:transaccion,origenes:origenes,destinos:destinos])
            } else if (transaccion.poliza.tipo == 'EGRESOS') {
                render(view:'egresos', model:[transaccion:transaccion,origenes:origenes,destinos:destinos])
            } else if (transaccion.poliza.tipo == 'DIARIO') {
                render(view:'diario', model:[transaccion:transaccion,origenes:origenes,destinos:destinos])
            } else {
                return [transaccion: transaccion]
            }
        }
    }

    def actualizaIngreso = {
        Transaccion.withTransaction {
            def transaccion = Transaccion.get(params.id)
            if (transaccion) {
                def importeAnterior = transaccion.importe
                transaccion.properties = params
                transaccion.importe = importeAnterior
                def usuario = springSecurityService.currentUser
                transaccion.empresa = usuario.empresa

                log.debug("TAGS: ${transaccion.tags}")
                def tags = transaccion.tags?.tokenize(',')
                log.debug("TOKENS: ${tags}")
                for(tag in tags) {
                    tag = tag.tr('A-Z','a-z')
                    def x = Tag.findByOrganizacionAndNombre(usuario.empresa.organizacion, tag)
                    if (!x) {
                        new Tag(nombre: tag, organizacion: usuario.empresa.organizacion).save()
                    }
                }

                if (params.importe) {
                    if (params.cuentaId) {
                        def cuenta = Cuenta.findByOrganizacionAndId(usuario.empresa.organizacion, params.cuentaId)
                        if (cuenta) {
                            if (cuenta.tieneAuxiliares && !params.auxiliarId) {
                                throw new RuntimeException("No se puede crear el movimiento porque falta el auxiliar")
                            }
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
                            if (params.esDebe != null) {
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
                                    log.debug("Validando que cuenta tenga auxiliares : $cuenta2.tieneAuxiliares")
                                    if (cuenta2.tieneAuxiliares) {
                                        def x = Auxiliar.find("from Auxiliar a inner join a.cuentas cuenta where cuenta = ?",[cuenta2],[max:1])
                                        log.debug("Asignando el auxiliar : ${x}")
                                        movimiento2.auxiliar = x[0]
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

    def actualizaEgreso = {
        Transaccion.withTransaction {
            def transaccion = Transaccion.get(params.id)
            if (transaccion) {
                def importeAnterior = transaccion.importe
                transaccion.properties = params
                transaccion.importe = importeAnterior
                def usuario = springSecurityService.currentUser
                transaccion.empresa = usuario.empresa

                log.debug("TAGS: ${transaccion.tags}")
                def tags = transaccion.tags?.tokenize(',')
                log.debug("TOKENS: ${tags}")
                for(tag in tags) {
                    tag = tag.tr('A-Z','a-z')
                    def x = Tag.findByOrganizacionAndNombre(usuario.empresa.organizacion, tag)
                    if (!x) {
                        new Tag(nombre: tag, organizacion: usuario.empresa.organizacion).save()
                    }
                }

                if (params.importe) {
                    if (params.cuentaId) {
                        def cuenta = Cuenta.findByOrganizacionAndId(usuario.empresa.organizacion, params.cuentaId)
                        if (cuenta) {
                            if (cuenta.tieneAuxiliares && !params.auxiliarId) {
                                throw new RuntimeException("No se puede crear el movimiento porque falta el auxiliar")
                            }
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
                            if (params.esHaber != null) {
                                // Valido si esta distribuyendo los pagos
                                // Si quiere distribuir algo que no esta pagado
                                // no lo deja
                                if (transaccion.importe >= importe) {
                                    // Si el importe de la distribucion es igual
                                    // a lo que llevo en la transaccion
                                    // no importan los destinos, solo crea uno nuevo
                                    if (transaccion.importe == importe) {
                                        def destinos = new ArrayList(transaccion.destinos)
                                        transaccion.destinos.clear()
                                        for(destino in destinos) {
                                            destino.delete()
                                        }
                                        transaccion.addToDestinos(movimiento)
                                    } else {
                                        // Si la cantidad es menor, entonces hay que distribuirlos
                                        // Primero valido si ya existe entre los movimientos
                                        // alguno a la misma cuenta, de ser asi, le sumo el importe
                                        // creo uno nuevo con este importe y borro el anterior.
                                        def x
                                        for(destino in transaccion.destinos) {
                                            if ((movimiento.auxiliar && destino.auxiliar == movimiento.auxiliar)
                                                    || (!movimiento.auxiliar && movimiento.cuenta == destino.cuenta)) {
                                                x = destino
                                            }
                                        }
                                        if (x) {
                                            movimiento.importe = movimiento.importe.add(importe)
                                            transaccion.destinos.remove(x)
                                            x.delete()
                                        }
                                        for(destino in transaccion.destinos) {
                                            if (destino.importe < importe) {
                                                // Si el importe es mayor a este movimiento
                                                // elmimina el movimiento, restale al importe
                                                // y ve a la siguiente iteracion para hacer
                                                // nuevamente la comparacion
                                                importe = importe.subtract(destino.importe)
                                                transaccion.destinos.remove(destino)
                                                destino.delete()
                                            } else if (destino.importe == importe) {
                                                // Si el importe ya es igual a este movimiento
                                                // solo eliminalo y crea el nuevo movimiento
                                                transaccion.destinos.remove(destino)
                                                destino.delete()
                                                transaccion.addToDestinos(movimiento)
                                                break;
                                            } else {
                                                // Si el importe es menor a este movimiento
                                                // restale el importe y crea el nuevo movimiento
                                                destino.importe = destino.importe.subtract(importe)
                                                transaccion.addToDestinos(movimiento)
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
                                for(destino in transaccion.destinos) {
                                    if (destino.cuenta == cuenta2) {
                                        movimiento2 = destino
                                        movimiento2.importe = movimiento2.importe.add(importe)
                                        break;
                                    }
                                }
                                if (!movimiento2) {
                                    movimiento2 = new Movimiento (
                                        cuenta: cuenta2
                                        , importe: importe
                                    )
                                    log.debug("Validando que cuenta tenga auxiliares : $cuenta2.tieneAuxiliares")
                                    if (cuenta2.tieneAuxiliares) {
                                        def x = Auxiliar.find("from Auxiliar a inner join a.cuentas cuenta where cuenta = ?",[cuenta2],[max:1])
                                        log.debug("Asignando el auxiliar : ${x}")
                                        movimiento2.auxiliar = x[0]
                                    }
                                }
                                transaccion.addToOrigenes(movimiento)
                                transaccion.addToDestinos(movimiento2)
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

    def actualizaDiario = {
        Transaccion.withTransaction {
            def transaccion = Transaccion.get(params.id)
            if (transaccion) {
                def importeAnterior = transaccion.importe
                transaccion.properties = params
                transaccion.importe = importeAnterior
                def usuario = springSecurityService.currentUser
                transaccion.empresa = usuario.empresa

                def tags = transaccion.tags?.tokenize(',')
                for(tag in tags) {
                    tag = tag.tr('A-Z','a-z')
                    def x = Tag.findByOrganizacionAndNombre(usuario.empresa.organizacion, tag)
                    if (!x) {
                        new Tag(nombre: tag, organizacion: usuario.empresa.organizacion).save()
                    }
                }

                if (params.importe) {
                    if (params.cuentaId) {
                        def cuenta = Cuenta.findByOrganizacionAndId(usuario.empresa.organizacion, params.cuentaId)
                        if (cuenta) {
                            if (cuenta.tieneAuxiliares && !params.auxiliarId) {
                                throw new RuntimeException("No se puede crear el movimiento porque falta el auxiliar")
                            }
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
                            if (params.esHaber != null) {
                                // Valido si esta distribuyendo los pagos
                                // Si quiere distribuir algo que no esta pagado
                                // no lo deja
                                if (transaccion.importe >= importe) {
                                    // Si el importe de la distribucion es igual
                                    // a lo que llevo en la transaccion
                                    // no importan los destinos, solo crea uno nuevo
                                    if (transaccion.importe == importe) {
                                        def destinos = new ArrayList(transaccion.destinos)
                                        transaccion.destinos.clear()
                                        for(destino in destinos) {
                                            destino.delete()
                                        }
                                        transaccion.addToDestinos(movimiento)
                                    } else {
                                        // Si la cantidad es menor, entonces hay que distribuirlos
                                        // Primero valido si ya existe entre los movimientos
                                        // alguno a la misma cuenta, de ser asi, le sumo el importe
                                        // creo uno nuevo con este importe y borro el anterior.
                                        def x
                                        for(destino in transaccion.destinos) {
                                            if ((movimiento.auxiliar && destino.auxiliar == movimiento.auxiliar)
                                                    || (!movimiento.auxiliar && movimiento.cuenta == destino.cuenta)) {
                                                x = destino
                                            }
                                        }
                                        if (x) {
                                            movimiento.importe = movimiento.importe.add(importe)
                                            transaccion.destinos.remove(x)
                                            x.delete()
                                        }
                                        for(destino in transaccion.destinos) {
                                            if (destino.importe < importe) {
                                                // Si el importe es mayor a este movimiento
                                                // elmimina el movimiento, restale al importe
                                                // y ve a la siguiente iteracion para hacer
                                                // nuevamente la comparacion
                                                importe = importe.subtract(destino.importe)
                                                transaccion.destinos.remove(destino)
                                                destino.delete()
                                            } else if (destino.importe == importe) {
                                                // Si el importe ya es igual a este movimiento
                                                // solo eliminalo y crea el nuevo movimiento
                                                transaccion.destinos.remove(destino)
                                                destino.delete()
                                                transaccion.addToDestinos(movimiento)
                                                break;
                                            } else {
                                                // Si el importe es menor a este movimiento
                                                // restale el importe y crea el nuevo movimiento
                                                destino.importe = destino.importe.subtract(importe)
                                                transaccion.addToDestinos(movimiento)
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
                                for(destino in transaccion.destinos) {
                                    if (destino.cuenta == cuenta2) {
                                        movimiento2 = destino
                                        movimiento2.importe = movimiento2.importe.add(importe)
                                        break;
                                    }
                                }
                                if (!movimiento2) {
                                    movimiento2 = new Movimiento (
                                        cuenta: cuenta2
                                        , importe: importe
                                    )
                                    log.debug("Validando que cuenta tenga auxiliares : $cuenta2.tieneAuxiliares")
                                    if (cuenta2.tieneAuxiliares) {
                                        def x = Auxiliar.find("from Auxiliar a inner join a.cuentas cuenta where cuenta = ?",[cuenta2],[max:1])
                                        log.debug("Asignando el auxiliar : ${x}")
                                        movimiento2.auxiliar = x[0]
                                    }
                                }
                                transaccion.addToOrigenes(movimiento)
                                transaccion.addToDestinos(movimiento2)
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
            for(cuenta in auxiliar.cuentas) {
                lista << [id:cuenta.id,value:"${auxiliar.numero} | ${auxiliar.descripcion} (AUXILIAR de ${cuenta.descripcion})",tieneAuxiliares:false,auxiliar:true,cuenta:"${cuenta.numero} | ${cuenta.descripcion}",auxiliarId:auxiliar.id]
            }
        }
        for(cuenta in cuentas) {
            lista << [id:cuenta.id,value:"$cuenta.numero | $cuenta.descripcion",tieneAuxiliares:cuenta.tieneAuxiliares,auxiliar:false]
        }
        def resultado = lista as grails.converters.JSON
        render resultado
    }

    def auxiliares = {
        def usuario = springSecurityService.currentUser
        def auxiliares = Auxiliar.buscaPorFiltro(params.term, usuario.empresa.id).list([max:10])
        def lista = []
        for(auxiliar in auxiliares) {
            for(cuenta in auxiliar.cuentas) {
                lista << [id:auxiliar.id,value:"${auxiliar.numero} | ${auxiliar.descripcion} (AUXILIAR de ${cuenta.descripcion})",cuenta:"${cuenta.numero} | ${cuenta.descripcion}",cuentaId:cuenta.id]
            }
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

    def elimina = {
        Transaccion.withTransaction {
            def usuario = springSecurityService.currentUser
            def transaccion = Transaccion.get(params.id)
            def poliza = transaccion.poliza
            def folio = transaccion.folio
            if (transaccion.poliza.estatus == 'ABIERTA' && poliza.empresa == usuario.empresa) {
                transaccion.delete()
            }
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'transaccion.label'), folio])
            redirect(controller:'poliza',action:'edita',id:poliza.id)
        }
    }

    def tags = {
        def usuario = springSecurityService.currentUser
        render Tag.buscaPorFiltro("%${params.term}%",usuario.empresa.organizacion.id).list([max:10])*.nombre as JSON
    }
}
