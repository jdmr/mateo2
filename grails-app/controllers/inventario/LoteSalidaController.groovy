package inventario

import grails.converters.JSON

class LoteSalidaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[loteSalidaInstanceList: LoteSalida.list(params), loteSalidaInstanceTotal: LoteSalida.count()]
	}

    def create = {
        def loteSalidaInstance = new LoteSalida()
        loteSalidaInstance.properties = params
        return [loteSalidaInstance: loteSalidaInstance]
    }

    def save = {
        def loteSalidaInstance = new LoteSalida(params)
        if (loteSalidaInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), loteSalidaInstance.id])
            redirect(action: "show", id: loteSalidaInstance.id)
        }
        else {
            render(view: "create", model: [loteSalidaInstance: loteSalidaInstance])
        }
    }

    def show = {
        def loteSalidaInstance = LoteSalida.get(params.id)
        if (!loteSalidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
            redirect(action: "list")
        }
        else {
            [loteSalidaInstance: loteSalidaInstance]
        }
    }

    def edit = {
        def loteSalidaInstance = LoteSalida.get(params.id)
        if (!loteSalidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
            redirect(action: "list")
        }
        else {
            return [loteSalidaInstance: loteSalidaInstance]
        }
    }

    def update = {
        def loteSalidaInstance = LoteSalida.get(params.id)
        if (loteSalidaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (loteSalidaInstance.version > version) {
                    
                    loteSalidaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'loteSalida.label', default: 'LoteSalida')] as Object[], "Another user has updated this LoteSalida while you were editing")
                    render(view: "edit", model: [loteSalidaInstance: loteSalidaInstance])
                    return
                }
            }
            loteSalidaInstance.properties = params
            if (!loteSalidaInstance.hasErrors() && loteSalidaInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), loteSalidaInstance.id])
                redirect(action: "show", id: loteSalidaInstance.id)
            }
            else {
                render(view: "edit", model: [loteSalidaInstance: loteSalidaInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def loteSalidaInstance = LoteSalida.get(params.id)
        if (loteSalidaInstance) {
            try {
                loteSalidaInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
            redirect(action: "list")
        }
    }
}
