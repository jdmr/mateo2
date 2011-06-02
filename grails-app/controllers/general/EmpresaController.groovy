package general

import grails.converters.JSON

class EmpresaController {

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[empresas: Empresa.list(params), totalDeEmpresas: Empresa.count()]
	}

    def nueva = {
        def empresa = new Empresa()
        empresa.properties = params
        return [empresa: empresa]
    }

    def crea = {
        def empresa = new Empresa(params)
        if (empresa.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'empresa.label', default: 'Empresa'), empresa.id])
            redirect(action: "ver", id: empresa.id)
        }
        else {
            render(view: "nueva", model: [empresa: empresa])
        }
    }

    def ver = {
        def empresa = Empresa.get(params.id)
        if (!empresa) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
            redirect(action: "lista")
        }
        else {
            [empresa: empresa]
        }
    }

    def edita = {
        def empresa = Empresa.get(params.id)
        if (!empresa) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
            redirect(action: "lista")
        }
        else {
            return [empresa: empresa]
        }
    }

    def actualiza = {
        def empresa = Empresa.get(params.id)
        if (empresa) {
            if (params.version) {
                def version = params.version.toLong()
                if (empresa.version > version) {
                    
                    empresa.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'empresa.label', default: 'Empresa')] as Object[], "Another user has updated this Empresa while you were editing")
                    render(view: "edita", model: [empresa: empresa])
                    return
                }
            }
            empresa.properties = params
            if (!empresa.hasErrors() && empresa.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'empresa.label', default: 'Empresa'), empresa.id])
                redirect(action: "ver", id: empresa.id)
            }
            else {
                render(view: "edita", model: [empresa: empresa])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def empresa = Empresa.get(params.id)
        if (empresa) {
            try {
                empresa.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
            redirect(action: "lista")
        }
    }
}
