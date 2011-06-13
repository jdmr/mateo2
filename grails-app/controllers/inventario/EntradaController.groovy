package inventario

import grails.converters.JSON

class EntradaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[entradaInstanceList: Entrada.list(params), entradaInstanceTotal: Entrada.count()]
	}

    def create = {
        def entradaInstance = new Entrada()
        entradaInstance.properties = params
        return [entradaInstance: entradaInstance]
    }

    def save = {
        def entradaInstance = new Entrada(params)
        if (entradaInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'entrada.label', default: 'Entrada'), entradaInstance.id])
            redirect(action: "show", id: entradaInstance.id)
        }
        else {
            render(view: "create", model: [entradaInstance: entradaInstance])
        }
    }

    def show = {
        def entradaInstance = Entrada.get(params.id)
        if (!entradaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "list")
        }
        else {
            [entradaInstance: entradaInstance]
        }
    }

    def edit = {
        def entradaInstance = Entrada.get(params.id)
        if (!entradaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "list")
        }
        else {
            return [entradaInstance: entradaInstance]
        }
    }

    def update = {
        def entradaInstance = Entrada.get(params.id)
        if (entradaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (entradaInstance.version > version) {
                    
                    entradaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'entrada.label', default: 'Entrada')] as Object[], "Another user has updated this Entrada while you were editing")
                    render(view: "edit", model: [entradaInstance: entradaInstance])
                    return
                }
            }
            entradaInstance.properties = params
            if (!entradaInstance.hasErrors() && entradaInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'entrada.label', default: 'Entrada'), entradaInstance.id])
                redirect(action: "show", id: entradaInstance.id)
            }
            else {
                render(view: "edit", model: [entradaInstance: entradaInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def entradaInstance = Entrada.get(params.id)
        if (entradaInstance) {
            try {
                entradaInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "list")
        }
    }
}
