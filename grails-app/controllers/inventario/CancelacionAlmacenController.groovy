package inventario

import grails.converters.JSON

class CancelacionAlmacenController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[cancelacionAlmacenInstanceList: CancelacionAlmacen.list(params), cancelacionAlmacenInstanceTotal: CancelacionAlmacen.count()]
	}

    def create = {
        def cancelacionAlmacenInstance = new CancelacionAlmacen()
        cancelacionAlmacenInstance.properties = params
        return [cancelacionAlmacenInstance: cancelacionAlmacenInstance]
    }

    def save = {
        def cancelacionAlmacenInstance = new CancelacionAlmacen(params)
        if (cancelacionAlmacenInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), cancelacionAlmacenInstance.id])
            redirect(action: "show", id: cancelacionAlmacenInstance.id)
        }
        else {
            render(view: "create", model: [cancelacionAlmacenInstance: cancelacionAlmacenInstance])
        }
    }

    def show = {
        def cancelacionAlmacenInstance = CancelacionAlmacen.get(params.id)
        if (!cancelacionAlmacenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
            redirect(action: "list")
        }
        else {
            [cancelacionAlmacenInstance: cancelacionAlmacenInstance]
        }
    }

    def edit = {
        def cancelacionAlmacenInstance = CancelacionAlmacen.get(params.id)
        if (!cancelacionAlmacenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
            redirect(action: "list")
        }
        else {
            return [cancelacionAlmacenInstance: cancelacionAlmacenInstance]
        }
    }

    def update = {
        def cancelacionAlmacenInstance = CancelacionAlmacen.get(params.id)
        if (cancelacionAlmacenInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (cancelacionAlmacenInstance.version > version) {
                    
                    cancelacionAlmacenInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen')] as Object[], "Another user has updated this CancelacionAlmacen while you were editing")
                    render(view: "edit", model: [cancelacionAlmacenInstance: cancelacionAlmacenInstance])
                    return
                }
            }
            cancelacionAlmacenInstance.properties = params
            if (!cancelacionAlmacenInstance.hasErrors() && cancelacionAlmacenInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), cancelacionAlmacenInstance.id])
                redirect(action: "show", id: cancelacionAlmacenInstance.id)
            }
            else {
                render(view: "edit", model: [cancelacionAlmacenInstance: cancelacionAlmacenInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def cancelacionAlmacenInstance = CancelacionAlmacen.get(params.id)
        if (cancelacionAlmacenInstance) {
            try {
                cancelacionAlmacenInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cancelacionAlmacen.label', default: 'CancelacionAlmacen'), params.id])
            redirect(action: "list")
        }
    }
}
