package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class ProductoController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
                def usuario = springSecurityService.currentUser
//		[productos: Producto.findAllByAlmacen(usuario.empresa, params), totalDeProductos: Producto.countByAlmacen(usuario.empresa)]
                [productos: Producto.list(params), totalDeProductos: Producto.count()]
	}

    def nuevo = {
        def producto = new Producto()
        producto.properties = params
        return [producto: producto]
    }

    def crea = {
        def producto = new Producto(params)
//        def usuario = springSecurityService.currentUser
//        producto.empresa = usuario.empresa
        if (producto.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'producto.label', default: 'Producto'), producto.codigo])
            redirect(action: "ver", id: producto.id)
        }
        else {
            render(view: "nuevo", model: [producto: producto])
        }
    }

    def ver = {
        def producto = Producto.get(params.id)
        if (!producto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "lista")
        }
        else {
            [producto: producto]
        }
    }

    def edita = {
        def producto = Producto.get(params.id)
        if (!producto) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "lista")
        }
        else {
            return [producto: producto]
        }
    }

    def actualiza = {
        def producto = Producto.get(params.id)
        if (producto) {
            if (params.version) {
                def version = params.version.toLong()
                if (producto.version > version) {
                    
                    producto.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'producto.label', default: 'Producto')] as Object[], "Another user has updated this Producto while you were editing")
                    render(view: "edita", model: [producto: producto])
                    return
                }
            }
            producto.properties = params
            if (!producto.hasErrors() && producto.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'producto.label', default: 'Producto'), producto.codigo])
                redirect(action: "ver", id: producto.id)
            }
            else {
                render(view: "edita", model: [producto: producto])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def producto = Producto.get(params.id)
        if (producto) {
            def nombre
            try {
                nombre = producto.nombre
                producto.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), params.codigo])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'producto.label', default: 'Producto'), params.codigo])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'producto.label', default: 'Producto'), params.id])
            redirect(action: "lista")
        }
    }
}
