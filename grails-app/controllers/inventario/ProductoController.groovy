package inventario

import grails.converters.JSON

class ProductoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[productoInstanceList: Producto.list(params), productoInstanceTotal: Producto.count()]
	}

    def create = {
        def productoInstance = new Producto()
        productoInstance.properties = params
        return [productoInstance: productoInstance]
    }

    def save = {
        def productoInstance = new Producto(params)
        if (productoInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'producto.label', default: 'Producto'), productoInstance.id])
            redirect(action: "show", id: productoInstance.id)
        }
        else {
            render(view: "create", model: [productoInstance: productoInstance])
        }
    }

    def show = {
        def productoInstance = Producto.get(params.id)
        if (!productoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
        }
        else {
            [productoInstance: productoInstance]
        }
    }

    def edit = {
        def productoInstance = Producto.get(params.id)
        if (!productoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
        }
        else {
            return [productoInstance: productoInstance]
        }
    }

    def update = {
        def productoInstance = Producto.get(params.id)
        if (productoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (productoInstance.version > version) {
                    
                    productoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'producto.label', default: 'Producto')] as Object[], "Another user has updated this Producto while you were editing")
                    render(view: "edit", model: [productoInstance: productoInstance])
                    return
                }
            }
            productoInstance.properties = params
            if (!productoInstance.hasErrors() && productoInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'producto.label', default: 'Producto'), productoInstance.id])
                redirect(action: "show", id: productoInstance.id)
            }
            else {
                render(view: "edit", model: [productoInstance: productoInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def productoInstance = Producto.get(params.id)
        if (productoInstance) {
            try {
                productoInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "list")
        }
    }
}
