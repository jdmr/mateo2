package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import general.Tag

@Secured(['ROLE_EMP'])
class ServicioController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def usuario = springSecurityService.currentUser
		[servicios: Servicio.findAllByEmpresa(usuario.empresa, params), totalDeServicios: Servicio.countByEmpresa(usuario.empresa)]
	}

    def nuevo = {
        def servicio = new Servicio()
        servicio.properties = params
        return [servicio: servicio]
    }

    def crea = {
        def usuario = springSecurityService.currentUser
        def servicio = new Servicio(params)
        servicio.empresa = usuario.empresa
        if (servicio.save(flush: true)) {
            def tags = servicio.tags?.tokenize(',')
            for(tag in tags) {
                tag = tag.tr('A-Z','a-z')
                def x = Tag.findByOrganizacionAndNombre(usuario.empresa.organizacion, tag)
                if (!x) {
                    new Tag(nombre: tag, organizacion: usuario.empresa.organizacion).save()
                }
            }

            flash.message = message(code: 'default.created.message', args: [message(code: 'servicio.label', default: 'Servicio'), servicio.nombre])
            redirect(action: "ver", id: servicio.id)
        }
        else {
            render(view: "nuevo", model: [servicio: servicio])
        }
    }

    def ver = {
        def usuario = springSecurityService.currentUser
        def servicio = Servicio.findByEmpresaAndId(usuario.empresa, params.id)
        if (!servicio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'servicio.label', default: 'Servicio'), params.id])
            redirect(action: "lista")
        }
        else {
            def origenes = [:]
            def destinos = [:]
            for(transaccion in servicio.transacciones) {
                def x = obtieneMovimientos(transaccion.origenes)
                def y = obtieneMovimientos(transaccion.destinos)
                origenes[transaccion.id] = x
                destinos[transaccion.id] = y
            }

            [servicio: servicio, origenes: origenes, destinos: destinos]
        }
    }

    def edita = {
        def usuario = springSecurityService.currentUser
        def servicio = Servicio.findByEmpresaAndId(usuario.empresa, params.id)
        if (!servicio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'servicio.label', default: 'Servicio'), params.id])
            redirect(action: "lista")
        }
        else {
            def origenes = [:]
            def destinos = [:]
            for(transaccion in servicio.transacciones) {
                def x = obtieneMovimientos(transaccion.origenes)
                def y = obtieneMovimientos(transaccion.destinos)
                origenes[transaccion.id] = x
                destinos[transaccion.id] = y
            }

            log.debug("Origenes: $origenes")
            log.debug("Destinos: $destinos")

            return [servicio: servicio, origenes: origenes, destinos: destinos]
        }
    }

    def actualiza = {
        def usuario = springSecurityService.currentUser
        def servicio = Servicio.findByEmpresaAndId(usuario.empresa, params.id)
        if (servicio) {
            if (params.version) {
                def version = params.version.toLong()
                if (servicio.version > version) {
                    
                    servicio.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'servicio.label', default: 'Servicio')] as Object[], "Another user has updated this Servicio while you were editing")
                    render(view: "edit", model: [servicio: servicio])
                    return
                }
            }
            servicio.properties = params
            servicio.empresa = usuario.empresa

            if (!servicio.hasErrors() && servicio.save(flush: true)) {
                def tags = servicio.tags?.tokenize(',')
                for(tag in tags) {
                    tag = tag.tr('A-Z','a-z')
                    def x = Tag.findByOrganizacionAndNombre(usuario.empresa.organizacion, tag)
                    if (!x) {
                        new Tag(nombre: tag, organizacion: usuario.empresa.organizacion).save()
                    }
                }

                flash.message = message(code: 'default.updated.message', args: [message(code: 'servicio.label', default: 'Servicio'), servicio.nombre])
                redirect(action: "ver", id: servicio.id)
            }
            else {
                def origenes = [:]
                def destinos = [:]
                for(transaccion in servicio.transacciones) {
                    def x = obtieneMovimientos(transaccion.origenes)
                    def y = obtieneMovimientos(transaccion.destinos)
                    origenes[transaccion.id] = x
                    destinos[transaccion.id] = y
                }
                render(view: "edita", model: [servicio: servicio, origenes: origenes, destinos:destinos])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'servicio.label', default: 'Servicio'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def usuario = springSecurityService.currentUser
        def servicio = Servicio.findByEmpresaAndId(usuario.empresa, params.id)
        if (servicio) {
            try {
                servicio.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'servicio.label', default: 'Servicio'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'servicio.label', default: 'Servicio'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'servicio.label', default: 'Servicio'), params.id])
            redirect(action: "lista")
        }
    }

    def tags = {
        def usuario = springSecurityService.currentUser
        render Tag.buscaPorFiltro("%${params.term}%",usuario.empresa.organizacion.id).list([max:10])*.nombre as JSON
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
				resultado << new ServicioMovimiento(cuenta:encabezado[0],importe:encabezado[1], padre:true)
                def size = movimientos[id].size()
                def movimiento = movimientos[id][size-1]
                movimiento.ultimo = true
				resultado.addAll(movimientos[id])
			} else {
				resultado << new ServicioMovimiento(cuenta:encabezado[0],importe:encabezado[1])
			}
		}

        return resultado
    }

}
