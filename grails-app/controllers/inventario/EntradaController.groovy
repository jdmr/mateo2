package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class EntradaController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
                def usuario = springSecurityService.currentUser
//		[entradas: Entrada.findAllByEmpresa(usuario.empresa, params), totalDeEntradas: Entrada.countByEmpresa(usuario.empresa)]
                [entradas: Entrada.list(params), totalDeEntradas: Entrada.count()]
	}

    def nueva = {
        def entrada = new Entrada()
        entrada.properties = params
        return [entrada: entrada]
    }

    def crea = {
        def entrada = new Entrada(params)
        def usuario = springSecurityService.currentUser
        entrada.empresa = usuario.empresa
        if (entrada.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'entrada.label', default: 'Entrada'), entrada.folio])
            redirect(action: "ver", id: entrada.id)
        }
        else {
            render(view: "nueva", model: [entrada: entrada])
        }
    }

    def ver = {
        def entrada = Entrada.get(params.id)
        if (!entrada) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "lista")
        }
        else {
            [entrada: entrada]
        }
    }

    def edita = {
        def entrada = Entrada.get(params.id)
        if (!entrada) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "lista")
        }
        else {
            return [entrada: entrada]
        }
    }

    def actualiza = {
        def entrada = Entrada.get(params.id)
        if (entrada) {
            if (params.version) {
                def version = params.version.toLong()
                if (entrada.version > version) {
                    
                    entrada.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'entrada.label', default: 'Entrada')] as Object[], "Another user has updated this Entrada while you were editing")
                    render(view: "edita", model: [entrada: entrada])
                    return
                }
            }
            entrada.properties = params
            if (!entrada.hasErrors() && entrada.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'entrada.label', default: 'Entrada'), entrada.folio])
                redirect(action: "ver", id: entrada.id)
            }
            else {
                render(view: "edita", model: [entrada: entrada])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def entrada = Entrada.get(params.id)
        if (entrada) {
            def nombre
            try {
                nombre = entrada.nombre
                entrada.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.folio])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.folio])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'entrada.label', default: 'Entrada'), params.id])
            redirect(action: "lista")
        }
    }
}
