package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

@Secured(['ROLE_ADMIN'])
class OrganizacionController {

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[organizaciones: Organizacion.list(params), totalDeOrganizaciones: Organizacion.count()]
	}

    def nueva = {
        def organizacion = new Organizacion()
        organizacion.properties = params
        return [organizacion: organizacion]
    }

    def crea = {
        Organizacion.withTransaction {
            def organizacion = new Organizacion(params)
            if (organizacion.save(flush: true)) {
                def empresa = new Empresa (
                    codigo : 'EMP'
                    , nombre : 'EMPRESA'
                    , nombreCompleto : 'EMPRESA'
                    , organizacion : organizacion
                ).save(flush:true)
                flash.message = message(code: 'default.created.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), organizacion.nombre])
                redirect(controller:'empresa', action: "edita", id: empresa.id)
            }
            else {
                render(view: "nueva", model: [organizacion: organizacion])
            }
        }
    }

    def ver = {
        def organizacion = Organizacion.get(params.id)
        if (!organizacion) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])
            redirect(action: "lista")
        }
        else {
            [organizacion: organizacion]
        }
    }

    def edita = {
        def organizacion = Organizacion.get(params.id)
        if (!organizacion) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])
            redirect(action: "lista")
        }
        else {
            return [organizacion: organizacion]
        }
    }

    def actualiza = {
        def organizacion = Organizacion.get(params.id)
        if (organizacion) {
            if (params.version) {
                def version = params.version.toLong()
                if (organizacion.version > version) {
                    
                    organizacion.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'organizacion.label', default: 'Organizacion')] as Object[], "Another user has updated this Organizacion while you were editing")
                    render(view: "edita", model: [organizacion: organizacion])
                    return
                }
            }
            organizacion.properties = params
            if (!organizacion.hasErrors() && organizacion.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), organizacion.nombre])
                redirect(action: "ver", id: organizacion.id)
            }
            else {
                render(view: "edita", model: [organizacion: organizacion])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        Organizacion.withTransaction {
            def organizacion = Organizacion.get(params.id)
            if (organizacion) {
                def nombre
                try {
                    nombre = organizacion.nombre
                    organizacion.delete(flush: true)
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), nombre])
                    redirect(action: "lista")
                }
                catch (org.springframework.dao.DataIntegrityViolationException e) {
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), nombre])
                    redirect(action: "ver", id: params.id)
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizacion.label', default: 'Organizacion'), params.id])
                redirect(action: "lista")
            }
        }
    }
}
