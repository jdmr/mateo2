package inventario

import grails.converters.JSON

class TipoProductoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[tipoProductoInstanceList: TipoProducto.list(params), tipoProductoInstanceTotal: TipoProducto.count()]
	}

    def create = {
        def tipoProductoInstance = new TipoProducto()
        tipoProductoInstance.properties = params
        return [tipoProductoInstance: tipoProductoInstance]
    }

    def save = {
        def tipoProductoInstance = new TipoProducto(params)
        if (tipoProductoInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), tipoProductoInstance.id])
            redirect(action: "show", id: tipoProductoInstance.id)
        }
        else {
            render(view: "create", model: [tipoProductoInstance: tipoProductoInstance])
        }
    }

    def show = {
        def tipoProductoInstance = TipoProducto.get(params.id)
        if (!tipoProductoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
            redirect(action: "list")
        }
        else {
            [tipoProductoInstance: tipoProductoInstance]
        }
    }

    def edit = {
        def tipoProductoInstance = TipoProducto.get(params.id)
        if (!tipoProductoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
            redirect(action: "list")
        }
        else {
            return [tipoProductoInstance: tipoProductoInstance]
        }
    }

    def update = {
        def tipoProductoInstance = TipoProducto.get(params.id)
        if (tipoProductoInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoProductoInstance.version > version) {
                    
                    tipoProductoInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoProducto.label', default: 'TipoProducto')] as Object[], "Another user has updated this TipoProducto while you were editing")
                    render(view: "edit", model: [tipoProductoInstance: tipoProductoInstance])
                    return
                }
            }
            tipoProductoInstance.properties = params
            if (!tipoProductoInstance.hasErrors() && tipoProductoInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), tipoProductoInstance.id])
                redirect(action: "show", id: tipoProductoInstance.id)
            }
            else {
                render(view: "edit", model: [tipoProductoInstance: tipoProductoInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def tipoProductoInstance = TipoProducto.get(params.id)
        if (tipoProductoInstance) {
            try {
                tipoProductoInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoProducto.label', default: 'TipoProducto'), params.id])
            redirect(action: "list")
        }
    }
}
