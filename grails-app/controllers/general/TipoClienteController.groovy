package general

import grails.converters.JSON

class TipoClienteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[tipoClienteInstanceList: TipoCliente.list(params), tipoClienteInstanceTotal: TipoCliente.count()]
	}

    def create = {
        def tipoClienteInstance = new TipoCliente()
        tipoClienteInstance.properties = params
        return [tipoClienteInstance: tipoClienteInstance]
    }

    def save = {
        def tipoClienteInstance = new TipoCliente(params)
        if (tipoClienteInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), tipoClienteInstance.id])
            redirect(action: "show", id: tipoClienteInstance.id)
        }
        else {
            render(view: "create", model: [tipoClienteInstance: tipoClienteInstance])
        }
    }

    def show = {
        def tipoClienteInstance = TipoCliente.get(params.id)
        if (!tipoClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
            redirect(action: "list")
        }
        else {
            [tipoClienteInstance: tipoClienteInstance]
        }
    }

    def edit = {
        def tipoClienteInstance = TipoCliente.get(params.id)
        if (!tipoClienteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
            redirect(action: "list")
        }
        else {
            return [tipoClienteInstance: tipoClienteInstance]
        }
    }

    def update = {
        def tipoClienteInstance = TipoCliente.get(params.id)
        if (tipoClienteInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (tipoClienteInstance.version > version) {
                    
                    tipoClienteInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'tipoCliente.label', default: 'TipoCliente')] as Object[], "Another user has updated this TipoCliente while you were editing")
                    render(view: "edit", model: [tipoClienteInstance: tipoClienteInstance])
                    return
                }
            }
            tipoClienteInstance.properties = params
            if (!tipoClienteInstance.hasErrors() && tipoClienteInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), tipoClienteInstance.id])
                redirect(action: "show", id: tipoClienteInstance.id)
            }
            else {
                render(view: "edit", model: [tipoClienteInstance: tipoClienteInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def tipoClienteInstance = TipoCliente.get(params.id)
        if (tipoClienteInstance) {
            try {
                tipoClienteInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoCliente.label', default: 'TipoCliente'), params.id])
            redirect(action: "list")
        }
    }
}
