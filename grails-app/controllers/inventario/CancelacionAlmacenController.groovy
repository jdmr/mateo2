package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class CancelacionAlmacenController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
                def usuario = springSecurityService.currentUser
//		[cancelacionAlmacenes: CancelacionAlmacen.findAllByEmpresa(usuario.empresa, params), totalDeCancelacionAlmacen: CancelacionAlmacen.countByEmpresa(usuario.empresa)]
                [cancelacionAlmacenes: CancelacionAlmacen.list(params), totalDeCancelacionAlmacen: CancelacionAlmacen.count()]
	}

    def nueva = {
        def cancelacionAlmacen = new CancelacionAlmacen()
        cancelacionAlmacen.properties = params
        return [cancelacionAlmacen: cancelacionAlmacen]
    }

    def crea = {
        def cancelacionAlmacen = new CancelacionAlmacen(params)
//        def usuario = springSecurityService.currentUser
//        cancelacionAlmacen.empresa = usuario.empresa
        if (cancelacionAlmacen.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), cancelacionAlmacen.folio])
            redirect(action: "ver", id: cancelacionAlmacen.id)
        }
        else {
            render(view: "nueva", model: [cancelacionAlmacen: cancelacionAlmacen])
        }
    }

    def ver = {
        def cancelacionAlmacen = CancelacionAlmacen.get(params.id)
        if (!cancelacionAlmacen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
            redirect(action: "lista")
        }
        else {
            [cancelacionAlmacen: cancelacionAlmacen]
        }
    }

    def edita = {
        def cancelacionAlmacen = CancelacionAlmacen.get(params.id)
        if (!cancelacionAlmacen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
            redirect(action: "lista")
        }
        else {
            return [cancelacionAlmacen: cancelacionAlmacen]
        }
    }

    def actualiza = {
        def cancelacionAlmacen = CancelacionAlmacen.get(params.id)
        if (cancelacionAlmacen) {
            if (params.version) {
                def version = params.version.toLong()
                if (cancelacionAlmacen.version > version) {
                    
                    cancelacionAlmacen.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen')] as Object[], "Another user has updated this CancelacionAlmacen while you were editing")
                    render(view: "edita", model: [cancelacionAlmacen: cancelacionAlmacen])
                    return
                }
            }
            cancelacionAlmacen.properties = params
            if (!cancelacionAlmacen.hasErrors() && cancelacionAlmacen.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), cancelacionAlmacen.folio])
                redirect(action: "ver", id: cancelacionAlmacen.id)
            }
            else {
                render(view: "edita", model: [cancelacionAlmacen: cancelacionAlmacen])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def cancelacionAlmacen = CancelacionAlmacen.get(params.id)
        if (cancelacionAlmacen) {
//            def nombre
            try {
//                nombre = cancelacionAlmacen.nombre
                cancelacionAlmacen.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.folio])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.folio])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
            redirect(action: "lista")
        }
    }
}
