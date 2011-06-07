package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class LibroController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def usuario = springSecurityService.currentUser
		[libros: Libro.findAllByEmpresa(usuario.empresa, params), totalDeLibros: Libro.countByEmpresa(usuario.empresa)]
	}

    def nuevo = {
        def libro = new Libro()
        libro.properties = params
        return [libro: libro]
    }

    def crea = {
        def libro = new Libro(params)
        def usuario = springSecurityService.currentUser
        libro.empresa = usuario.empresa
        if (libro.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'libro.label', default: 'Libro'), libro.nombre])
            redirect(action: "ver", id: libro.id)
        }
        else {
            render(view: "nuevo", model: [libro: libro])
        }
    }

    def ver = {
        def libro = Libro.get(params.id)
        if (!libro) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libro.label', default: 'Libro'), params.id])
            redirect(action: "list")
        }
        else {
            [libro: libro]
        }
    }

    def edita = {
        def libro = Libro.get(params.id)
        if (!libro) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libro.label', default: 'Libro'), params.id])
            redirect(action: "lista")
        }
        else {
            return [libro: libro]
        }
    }

    def actualiza = {
        def libro = Libro.get(params.id)
        if (libro) {
            if (params.version) {
                def version = params.version.toLong()
                if (libro.version > version) {
                    
                    libro.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'libro.label', default: 'Libro')] as Object[], "Another user has updated this Libro while you were editing")
                    render(view: "edita", model: [libro: libro])
                    return
                }
            }
            libro.properties = params
            if (!libro.hasErrors() && libro.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'libro.label', default: 'Libro'), libro.nombre])
                redirect(action: "ver", id: libro.id)
            }
            else {
                render(view: "edita", model: [libro: libro])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libro.label', default: 'Libro'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def libro = Libro.get(params.id)
        if (libro) {
            def nombre
            try {
                nombre = libro.nombre
                libro.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'libro.label', default: 'Libro'), nombre])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'libro.label', default: 'Libro'), nombre])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'libro.label', default: 'Libro'), params.id])
            redirect(action: "lista")
        }
    }
}
