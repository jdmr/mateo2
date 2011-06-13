package inventario

import grails.converters.JSON

class FacturaAlmacenController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[facturaAlmacenInstanceList: FacturaAlmacen.list(params), facturaAlmacenInstanceTotal: FacturaAlmacen.count()]
	}

    def create = {
        def facturaAlmacenInstance = new FacturaAlmacen()
        facturaAlmacenInstance.properties = params
        return [facturaAlmacenInstance: facturaAlmacenInstance]
    }

    def save = {
        def facturaAlmacenInstance = new FacturaAlmacen(params)
        if (facturaAlmacenInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), facturaAlmacenInstance.id])
            redirect(action: "show", id: facturaAlmacenInstance.id)
        }
        else {
            render(view: "create", model: [facturaAlmacenInstance: facturaAlmacenInstance])
        }
    }

    def show = {
        def facturaAlmacenInstance = FacturaAlmacen.get(params.id)
        if (!facturaAlmacenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
            redirect(action: "list")
        }
        else {
            [facturaAlmacenInstance: facturaAlmacenInstance]
        }
    }

    def edit = {
        def facturaAlmacenInstance = FacturaAlmacen.get(params.id)
        if (!facturaAlmacenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
            redirect(action: "list")
        }
        else {
            return [facturaAlmacenInstance: facturaAlmacenInstance]
        }
    }

    def update = {
        def facturaAlmacenInstance = FacturaAlmacen.get(params.id)
        if (facturaAlmacenInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (facturaAlmacenInstance.version > version) {
                    
                    facturaAlmacenInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen')] as Object[], "Another user has updated this FacturaAlmacen while you were editing")
                    render(view: "edit", model: [facturaAlmacenInstance: facturaAlmacenInstance])
                    return
                }
            }
            facturaAlmacenInstance.properties = params
            if (!facturaAlmacenInstance.hasErrors() && facturaAlmacenInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), facturaAlmacenInstance.id])
                redirect(action: "show", id: facturaAlmacenInstance.id)
            }
            else {
                render(view: "edit", model: [facturaAlmacenInstance: facturaAlmacenInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def facturaAlmacenInstance = FacturaAlmacen.get(params.id)
        if (facturaAlmacenInstance) {
            try {
                facturaAlmacenInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
            redirect(action: "list")
        }
    }
}
