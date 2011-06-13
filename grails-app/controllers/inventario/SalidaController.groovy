package inventario

import grails.converters.JSON

class SalidaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[salidaInstanceList: Salida.list(params), salidaInstanceTotal: Salida.count()]
	}

    def create = {
        def salidaInstance = new Salida()
        salidaInstance.properties = params
        return [salidaInstance: salidaInstance]
    }

    def save = {
        def salidaInstance = new Salida(params)
        if (salidaInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'salida.label', default: 'Salida'), salidaInstance.id])
            redirect(action: "show", id: salidaInstance.id)
        }
        else {
            render(view: "create", model: [salidaInstance: salidaInstance])
        }
    }

    def show = {
        def salidaInstance = Salida.get(params.id)
        if (!salidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "list")
        }
        else {
            [salidaInstance: salidaInstance]
        }
    }

    def edit = {
        def salidaInstance = Salida.get(params.id)
        if (!salidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "list")
        }
        else {
            return [salidaInstance: salidaInstance]
        }
    }

    def update = {
        def salidaInstance = Salida.get(params.id)
        if (salidaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (salidaInstance.version > version) {
                    
                    salidaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'salida.label', default: 'Salida')] as Object[], "Another user has updated this Salida while you were editing")
                    render(view: "edit", model: [salidaInstance: salidaInstance])
                    return
                }
            }
            salidaInstance.properties = params
            if (!salidaInstance.hasErrors() && salidaInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salida.label', default: 'Salida'), salidaInstance.id])
                redirect(action: "show", id: salidaInstance.id)
            }
            else {
                render(view: "edit", model: [salidaInstance: salidaInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def salidaInstance = Salida.get(params.id)
        if (salidaInstance) {
            try {
                salidaInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'salida.label', default: 'Salida'), params.id])
            redirect(action: "list")
        }
    }
}
