package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class EjercicioController {

    def springSecurityService
    
    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def usuario = springSecurityService.currentUser
		[ejercicios: Ejercicio.findAllByEmpresa(usuario.empresa, params), totalDeEjercicios: Ejercicio.countByEmpresa(usuario.empresa)]
	}

    def nuevo = {
        def ejercicio = new Ejercicio()
        ejercicio.properties = params
        return [ejercicio: ejercicio]
    }

    def crea = {
        def ejercicio = new Ejercicio(params)
        def usuario = springSecurityService.currentUser
        ejercicio.empresa = usuario.empresa
        if (ejercicio.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), ejercicio.nombre])
            redirect(action: "ver", id: ejercicio.id)
        }
        else {
            render(view: "nuevo", model: [ejercicio: ejercicio])
        }
    }

    def ver = {
        def ejercicio = Ejercicio.get(params.id)
        if (!ejercicio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
            redirect(action: "lista")
        }
        else {
            [ejercicio: ejercicio]
        }
    }

    def edita = {
        def ejercicio = Ejercicio.get(params.id)
        if (!ejercicio) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
            redirect(action: "lista")
        }
        else {
            return [ejercicio: ejercicio]
        }
    }

    def actualiza = {
        def ejercicio = Ejercicio.get(params.id)
        if (ejercicio) {
            if (params.version) {
                def version = params.version.toLong()
                if (ejercicio.version > version) {
                    
                    ejercicio.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'ejercicio.label', default: 'Ejercicio')] as Object[], "Another user has updated this Ejercicio while you were editing")
                    render(view: "edita", model: [ejercicio: ejercicio])
                    return
                }
            }
            ejercicio.properties = params
            if (!ejercicio.hasErrors() && ejercicio.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), ejercicio.nombre])
                redirect(action: "ver", id: ejercicio.id)
            }
            else {
                render(view: "edita", model: [ejercicio: ejercicio])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def ejercicio = Ejercicio.get(params.id)
        if (ejercicio) {
            def nombre
            try {
                nombre = ejercicio.nombre
                ejercicio.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), nombre])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), nombre])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ejercicio.label', default: 'Ejercicio'), params.id])
            redirect(action: "lista")
        }
    }
}
