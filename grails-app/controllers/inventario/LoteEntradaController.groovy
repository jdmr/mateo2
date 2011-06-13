package inventario

import grails.converters.JSON

class LoteEntradaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[loteEntradaInstanceList: LoteEntrada.list(params), loteEntradaInstanceTotal: LoteEntrada.count()]
	}

    def create = {
        def loteEntradaInstance = new LoteEntrada()
        loteEntradaInstance.properties = params
        return [loteEntradaInstance: loteEntradaInstance]
    }

    def save = {
        def loteEntradaInstance = new LoteEntrada(params)
        if (loteEntradaInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), loteEntradaInstance.id])
            redirect(action: "show", id: loteEntradaInstance.id)
        }
        else {
            render(view: "create", model: [loteEntradaInstance: loteEntradaInstance])
        }
    }

    def show = {
        def loteEntradaInstance = LoteEntrada.get(params.id)
        if (!loteEntradaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
            redirect(action: "list")
        }
        else {
            [loteEntradaInstance: loteEntradaInstance]
        }
    }

    def edit = {
        def loteEntradaInstance = LoteEntrada.get(params.id)
        if (!loteEntradaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
            redirect(action: "list")
        }
        else {
            return [loteEntradaInstance: loteEntradaInstance]
        }
    }

    def update = {
        def loteEntradaInstance = LoteEntrada.get(params.id)
        if (loteEntradaInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (loteEntradaInstance.version > version) {
                    
                    loteEntradaInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'loteEntrada.label', default: 'LoteEntrada')] as Object[], "Another user has updated this LoteEntrada while you were editing")
                    render(view: "edit", model: [loteEntradaInstance: loteEntradaInstance])
                    return
                }
            }
            loteEntradaInstance.properties = params
            if (!loteEntradaInstance.hasErrors() && loteEntradaInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), loteEntradaInstance.id])
                redirect(action: "show", id: loteEntradaInstance.id)
            }
            else {
                render(view: "edit", model: [loteEntradaInstance: loteEntradaInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def loteEntradaInstance = LoteEntrada.get(params.id)
        if (loteEntradaInstance) {
            try {
                loteEntradaInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
            redirect(action: "list")
        }
    }
}
