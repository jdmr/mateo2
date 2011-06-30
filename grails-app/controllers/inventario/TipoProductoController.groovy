package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class TipoProductoController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		def usuario = springSecurityService.currentUser
//		[tipoProductos: Entrada.findAllByEmpresa(usuario.empresa, params), totalDeTipoProductos: Entrada.countByEmpresa(usuario.empresa)]
                [tipoProductos: TipoProducto.list(params), totalDeTipoProductos: TipoProducto.count()]
	}

    def nuevo = {
        def tipoProducto = new TipoProducto()
        tipoProducto.properties = params
        return [tipoProducto: tipoProducto]
    }

    def crea = {
        def tipoProducto = new TipoProducto(params)
//        def usuario = springSecurityService.currentUser
//        tipoProducto.empresa = usuario.empresa
        if (tipoProducto.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), tipoProducto.nombre])
            redirect(action: "ver", id: tipoProducto.id)
        }
        else {
            render(view: "nuevo", model: [tipoProducto: tipoProducto])
        }
    }

    def ver = {
        def tipoProducto = TipoProducto.get(params.id)
        if (!tipoProducto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
            redirect(action: "lista")
        }
        else {
            [tipoProducto: tipoProducto]
        }
    }

    def edita = {
        def tipoProducto = TipoProducto.get(params.id)
        if (!tipoProducto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
            redirect(action: "lista")
        }
        else {
            return [tipoProducto: tipoProducto]
        }
    }

    def actualiza = {
        def tipoProducto = TipoProducto.get(params.id)
        if (tipoProducto) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoProducto.version > version) {
                    
                    tipoProducto.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoProducto.label', default: 'TipoProducto')] as Object[], "Another user has updated this TipoProducto while you were editing")
                    render(view: "edita", model: [tipoProducto: tipoProducto])
                    return
                }
            }
            tipoProducto.properties = params
            if (!tipoProducto.hasErrors() && tipoProducto.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), tipoProducto.nombre])
                redirect(action: "ver", id: tipoProducto.id)
            }
            else {
                render(view: "edita", model: [tipoProducto: tipoProducto])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def tipoProducto = TipoProducto.get(params.id)
        if (tipoProducto) {
            def nombre
            try {
                nombre = tipoProducto.nombre
                tipoProducto.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.nombre])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.nombre])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
            redirect(action: "lista")
        }
    }
}
