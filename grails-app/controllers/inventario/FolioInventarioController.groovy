package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class FolioInventarioController {

    def springSecurityService
    
    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
                def usuario = springSecurityService.currentUser
//		[folioInventarios: FolioInventario.findAllByEmpresa(usuario.empresa, params), totalDeFolioInventarios: FolioInventario.countByEmpresa(usuario.empresa)]
                [folioInventarios: FolioInventario.list(params), totalDeFolioInventarios: FolioInventario.count()]
	}

    def nuevo = {
        def folioInventario = new FolioInventario()
        folioInventario.properties = params
        return [folioInventario: folioInventario]
    }

    def crea = {
        def folioInventario = new FolioInventario(params)
//        def usuario = springSecurityService.currentUser
//        entrada.empresa = usuario.empresa
        if (folioInventario.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), folioInventario.nombre])
            redirect(action: "ver", id: folioInventario.id)
        }
        else {
            render(view: "nuevo", model: [folioInventario: folioInventario])
        }
    }

    def ver = {
        def folioInventario = FolioInventario.get(params.id)
        if (!folioInventario) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
            redirect(action: "lista")
        }
        else {
            [folioInventario: folioInventario]
        }
    }

    def edita = {
        def folioInventario = FolioInventario.get(params.id)
        if (!folioInventario) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
            redirect(action: "lista")
        }
        else {
            return [folioInventario: folioInventario]
        }
    }

    def actualiza = {
        def folioInventario = FolioInventario.get(params.id)
        if (folioInventario) {
            if (params.version) {
                def version = params.version.toLong()
                if (folioInventario.version > version) {
                    
                    folioInventario.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'folioInventario.label', default: 'FolioInventario')] as Object[], "Another user has updated this FolioInventario while you were editing")
                    render(view: "edita", model: [folioInventario: folioInventario])
                    return
                }
            }
            folioInventario.properties = params
            if (!folioInventario.hasErrors() && folioInventario.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), folioInventario.nombre])
                redirect(action: "ver", id: folioInventario.id)
            }
            else {
                render(view: "edita", model: [folioInventario: folioInventario])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def folioInventario = FolioInventario.get(params.id)
        if (folioInventario) {
//            def nombre
            try {
//                nombre = folioInventario.nombre
                folioInventario.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.nombre])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.nombre])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
            redirect(action: "lista")
        }
    }
}
