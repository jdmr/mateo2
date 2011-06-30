package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class FacturaAlmacenController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
                def usuario = springSecurityService.currentUser
//		[facturaAlmacenes: FacturaAlmacen.findAllByEmpresa(usuario.empresa, params), totalDeFacturaAlmacenes: FacturaAlmacen.countByEmpresa(usuario.empresa)]
                [facturaAlmacenes: FacturaAlmacen.list(params), totalDeFacturaAlmacenes: FacturaAlmacen.count()]
	}

    def nueva = {
        def facturaAlmacen = new FacturaAlmacen()
        facturaAlmacen.properties = params
        return [facturaAlmacen: facturaAlmacen]
    }

    def crea = {
        def facturaAlmacen = new FacturaAlmacen(params)
//        def usuario = springSecurityService.currentUser
//        facturaAlmacen.empresa = usuario.empresa
        if (facturaAlmacen.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), facturaAlmacen.folio])
            redirect(action: "ver", id: facturaAlmacen.id)
        }
        else {
            render(view: "nueva", model: [facturaAlmacen: facturaAlmacen])
        }
    }

    def ver = {
        def facturaAlmacen = FacturaAlmacen.get(params.id)
        if (!facturaAlmacen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
            redirect(action: "lista")
        }
        else {
            [facturaAlmacen: facturaAlmacen]
        }
    }

    def edita = {
        def facturaAlmacen = FacturaAlmacen.get(params.id)
        if (!facturaAlmacen) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
            redirect(action: "lista")
        }
        else {
            return [facturaAlmacen: facturaAlmacen]
        }
    }

    def actualiza = {
        def facturaAlmacen = FacturaAlmacen.get(params.id)
        if (facturaAlmacen) {
            if (params.version) {
                def version = params.version.toLong()
                if (facturaAlmacen.version > version) {
                    
                    facturaAlmacen.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen')] as Object[], "Another user has updated this FacturaAlmacen while you were editing")
                    render(view: "edita", model: [facturaAlmacen: facturaAlmacen])
                    return
                }
            }
            facturaAlmacen.properties = params
            if (!facturaAlmacen.hasErrors() && facturaAlmacen.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), facturaAlmacen.folio])
                redirect(action: "ver", id: facturaAlmacen.id)
            }
            else {
                render(view: "edita", model: [facturaAlmacen: facturaAlmacen])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def facturaAlmacen = FacturaAlmacen.get(params.id)
        if (facturaAlmacen) {
//            def nombre
            try {
//                nombre = facturaAlmacen.nombre
                facturaAlmacen.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.folio])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.folio])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'facturaAlmacen.label', default: 'FacturaAlmacen'), params.id])
            redirect(action: "lista")
        }
    }
}
