package general

import grails.converters.JSON

class ProveedorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[proveedorInstanceList: Proveedor.list(params), proveedorInstanceTotal: Proveedor.count()]
	}

    def create = {
        def proveedorInstance = new Proveedor()
        proveedorInstance.properties = params
        return [proveedorInstance: proveedorInstance]
    }

    def save = {
        def proveedorInstance = new Proveedor(params)
        if (proveedorInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedorInstance.id])
            redirect(action: "show", id: proveedorInstance.id)
        }
        else {
            render(view: "create", model: [proveedorInstance: proveedorInstance])
        }
    }

    def show = {
        def proveedorInstance = Proveedor.get(params.id)
        if (!proveedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
        }
        else {
            [proveedorInstance: proveedorInstance]
        }
    }

    def edit = {
        def proveedorInstance = Proveedor.get(params.id)
        if (!proveedorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
        }
        else {
            return [proveedorInstance: proveedorInstance]
        }
    }

    def update = {
        def proveedorInstance = Proveedor.get(params.id)
        if (proveedorInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (proveedorInstance.version > version) {
                    
                    proveedorInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'proveedor.label', default: 'Proveedor')] as Object[], "Another user has updated this Proveedor while you were editing")
                    render(view: "edit", model: [proveedorInstance: proveedorInstance])
                    return
                }
            }
            proveedorInstance.properties = params
            if (!proveedorInstance.hasErrors() && proveedorInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), proveedorInstance.id])
                redirect(action: "show", id: proveedorInstance.id)
            }
            else {
                render(view: "edit", model: [proveedorInstance: proveedorInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def proveedorInstance = Proveedor.get(params.id)
        if (proveedorInstance) {
            try {
                proveedorInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'proveedor.label', default: 'Proveedor'), params.id])
            redirect(action: "list")
        }
    }
}
