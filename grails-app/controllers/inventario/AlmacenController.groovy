package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class AlmacenController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
                def usuario = springSecurityService.currentUser
                [almacenes: Almacen.findAllByEmpresa(usuario.empresa, params), totalDeAlmacenes: Almacen.countByEmpresa(usuario.empresa)]
	}

    def nuevo = {
        def almacen = new Almacen()
        almacen.properties = params
        return [almacen: almacen]
    }

    def crea = {
        def almacen = new Almacen(params)
        def usuario = springSecurityService.currentUser
        almacen.empresa = usuario.empresa
        almacen.nombreCompleto = almacen.getNombreCompleto()
        if (almacen.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'almacen.label', default: 'Almacen'), almacen.nombre])
            redirect(action: "ver", id: almacen.id)
        }
        else {
            render(view: "nuevo", model: [almacen: almacen])
        }
    }

    def ver = {
        def almacen = Almacen.get(params.id)
        if (!almacen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
            redirect(action: "lista")
        }
        else {
            [almacen: almacen]
        }
    }

    def edita = {
        def almacen = Almacen.get(params.id)
        if (!almacen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
            redirect(action: "lista")
        }
        else {
            return [almacen: almacen]
        }
    }

    def actualiza = {
        def almacen = Almacen.get(params.id)
        if (almacen) {
            if (params.version) {
                def version = params.version.toLong()
                if (almacen.version > version) {
                    
                    almacen.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'almacen.label', default: 'Almacen')] as Object[], "Another user has updated this Almacen while you were editing")
                    render(view: "edita", model: [almacen: almacen])
                    return
                }
            }
            almacen.properties = params
            if (!almacen.hasErrors() && almacen.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'almacen.label', default: 'Almacen'), almacen.nombre])
                redirect(action: "ver", id: almacen.id)
            }
            else {
                render(view: "edita", model: [almacen: almacen])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def almacen = Almacen.get(params.id)
        if (almacen) {
            def nombre
            try {
                nombre = almacen.nombre
                almacen.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.nombre])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.nombre])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'almacen.label', default: 'Almacen'), params.id])
            redirect(action: "lista")
        }
    }
}
