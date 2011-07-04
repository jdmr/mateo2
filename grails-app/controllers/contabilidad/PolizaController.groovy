package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class PolizaController {

    def springSecurityService
    def folioService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST", cierra: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def usuario = springSecurityService.currentUser
		[polizas: Poliza.findAllByEmpresa(usuario.empresa, params), totalDePolizas: Poliza.countByEmpresa(usuario.empresa)]
	}

    def nueva = {
        def poliza = new Poliza()
        poliza.properties = params
        return [poliza: poliza]
    }

    def nuevaIngreso = {
        def poliza = new Poliza()
        poliza.properties = params
        poliza.tipo = 'INGRESOS'
        return [poliza:poliza]
    }

    def nuevaEgreso = {
        def poliza = new Poliza()
        poliza.properties = params
        poliza.tipo = 'EGRESOS'
        return [poliza:poliza]
    }

    def nuevaDiario = {
        def poliza = new Poliza()
        poliza.properties = params
        poliza.tipo = 'DIARIO'
        return [poliza:poliza]
    }

    def crea = {
        Poliza.withTransaction {
            def usuario = springSecurityService.currentUser
            def poliza = new Poliza(params)
            poliza.folio = folioService.temporal()
            poliza.empresa = usuario.empresa
            if (poliza.save(flush: true)) {
                if (poliza.tipo == 'INGRESOS' || poliza.tipo == 'EGRESOS' || poliza.tipo == 'DIARIO') {
                    flash.message = message(code: 'poliza.created.message', args: [poliza.folio])
                    redirect(controller:"transaccion", action: "nueva", id: poliza.id)
                } else {
                    flash.message = message(code: 'default.created.message', args: [message(code: 'poliza.label', default: 'Poliza'), poliza.folio])
                    redirect(action: "ver", id: poliza.id)
                }
            }
            else {
                render(view: "nueva", model: [poliza: poliza])
            }
        }
    }

    def ver = {
        def poliza = Poliza.get(params.id)
        if (!poliza) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
            redirect(action: "lista")
        }
        else {
            def origenes = [:]
            def destinos = [:]
            for(transaccion in poliza.transacciones) {
                def x = obtieneMovimientos(transaccion.origenes)
                def y = obtieneMovimientos(transaccion.destinos)
                origenes[transaccion.id] = x
                destinos[transaccion.id] = y
            }

            [poliza: poliza, origenes: origenes, destinos: destinos]
        }
    }

    def edita = {
        def poliza = Poliza.get(params.id)
        if (!poliza) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
            redirect(action: "lista")
        }
        else {
            if (poliza.estatus == 'ABIERTA') {
                def origenes = [:]
                def destinos = [:]
                for(transaccion in poliza.transacciones) {
                    def x = obtieneMovimientos(transaccion.origenes)
                    def y = obtieneMovimientos(transaccion.destinos)
                    origenes[transaccion.id] = x
                    destinos[transaccion.id] = y
                }
                if (poliza.tipo == 'INGRESOS') {
                    render(view:'editaIngreso',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                } else if (poliza.tipo == 'EGRESOS') {
                    render(view:'editaEgreso',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                } else if (poliza.tipo == 'DIARIO') {
                    render(view:'editaDiario',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                } else {
                    return [poliza: poliza]
                }
            } else {
                flash.message = message(code: 'poliza.cerrada.editar.message', args: [poliza.folio])
                redirect(action:'lista')
            }
        }
    }

    def actualiza = {
        Poliza.withTransaction {
            def usuario = springSecurityService.currentUser
            def poliza = Poliza.findByEmpresaAndId(usuario.empresa, params.id)
            if (poliza) {
                if (poliza.estatus == 'ABIERTA') {
                    if (params.version) {
                        def version = params.version.toLong()
                        if (poliza.version > version) {
                            poliza.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'poliza.label', default: 'Poliza')] as Object[], "Another user has updated this Poliza while you were editing")
                            render(view: "edita", model: [poliza: poliza])
                            return
                        }
                    }
                    poliza.properties = params
                    if (!poliza.hasErrors() && poliza.save(flush: true)) {
                        flash.message = message(code: 'default.updated.message', args: [message(code: 'poliza.label', default: 'Poliza'), poliza.folio])
                        redirect(action: "ver", id: poliza.id)
                    }
                    else {
                        def origenes = [:]
                        def destinos = [:]
                        for(transaccion in poliza.transacciones) {
                            def x = obtieneMovimientos(transaccion.origenes)
                            def y = obtieneMovimientos(transaccion.destinos)
                            origenes[transaccion.id] = x
                            destinos[transaccion.id] = y
                        }
                        if (poliza.tipo == 'INGRESOS') {
                            render(view:'editaIngreso',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                        } else if (poliza.tipo == 'EGRESOS') {
                            render(view:'editaEgreso',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                        } else if (poliza.tipo == 'DIARIO') {
                            render(view:'editaDiario',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                        }
                    }
                } else {
                    flash.message = message(code: 'poliza.cerrada.editar.message', args: [poliza.folio])
                    redirect(action:'lista')
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
                redirect(action: "lista")
            }
        }
    }

    def cierra = {
        Poliza.withTransaction {
            def usuario = springSecurityService.currentUser
            def poliza = Poliza.findByEmpresaAndId(usuario.empresa, params.id)
            if (poliza && poliza.estatus == 'ABIERTA') {
                if (params.version) {
                    def version = params.version.toLong()
                    if (poliza.version > version) {
                        poliza.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'poliza.label', default: 'Poliza')] as Object[], "Another user has updated this Poliza while you were editing")
                        render(view: "edita", model: [poliza: poliza])
                        return
                    }
                }
                poliza.properties = params
                poliza.estatus = 'CERRADA'
                poliza.folio = folioService.poliza()
                for(transaccion in poliza.transacciones) {
                    transaccion.folio = folioService.transaccion()
                }
                if (!poliza.hasErrors() && poliza.save(flush: true)) {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'poliza.label', default: 'Poliza'), poliza.folio])
                    redirect(action: "ver", id: poliza.id)
                }
                else {
                    def origenes = [:]
                    def destinos = [:]
                    for(transaccion in poliza.transacciones) {
                        def x = obtieneMovimientos(transaccion.origenes)
                        def y = obtieneMovimientos(transaccion.destinos)
                        origenes[transaccion.id] = x
                        destinos[transaccion.id] = y
                    }
                    if (poliza.tipo == 'INGRESOS') {
                        render(view:'editaIngreso',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                    } else if (poliza.tipo == 'EGRESOS') {
                        render(view:'editaEgreso',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                    } else if (poliza.tipo == 'DIARIO') {
                        render(view:'editaDiario',model:[poliza:poliza, origenes: origenes, destinos: destinos])
                    }
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
                redirect(action: "lista")
            }
        }
    }

    def elimina = {
        def poliza = Poliza.get(params.id)
        if (poliza) {
            if (poliza.estatus == 'ABIERTA') {
                def folio
                try {
                    folio = poliza.folio
                    poliza.delete(flush: true)
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'poliza.label', default: 'Poliza'), folio])
                    redirect(action: "lista")
                }
                catch (org.springframework.dao.DataIntegrityViolationException e) {
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'poliza.label', default: 'Poliza'), folio])
                    redirect(action: "ver", id: params.id)
                }
            } else {
                // Avisarle al usuario que no puede eliminar una poliza
                // a menos de que este ABIERTA
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
            redirect(action: "lista")
        }
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

    def nuevaTransaccion = {
        Poliza.withTransaction {
            def poliza = Poliza.get(params.id)
            if (poliza) {
                if (params.version) {
                    def version = params.version.toLong()
                    if (poliza.version > version) {
                        poliza.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'poliza.label', default: 'Poliza')] as Object[], "Another user has updated this Poliza while you were editing")
                        render(view: "edita", model: [poliza: poliza])
                        return
                    }
                }
                poliza.properties = params
                if (!poliza.hasErrors() && poliza.save(flush: true)) {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'poliza.label', default: 'Poliza'), poliza.folio])
                    redirect(controller:'transaccion',action: "nueva", id: poliza.id)
                }
                else {
                    render(view: "edita", model: [poliza: poliza])
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
                redirect(action: "lista")
            }
        }
    }

}
