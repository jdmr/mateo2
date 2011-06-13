package inventario

import grails.converters.JSON

class FolioInventarioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[folioInventarioInstanceList: FolioInventario.list(params), folioInventarioInstanceTotal: FolioInventario.count()]
	}

    def create = {
        def folioInventarioInstance = new FolioInventario()
        folioInventarioInstance.properties = params
        return [folioInventarioInstance: folioInventarioInstance]
    }

    def save = {
        def folioInventarioInstance = new FolioInventario(params)
        if (folioInventarioInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), folioInventarioInstance.id])
            redirect(action: "show", id: folioInventarioInstance.id)
        }
        else {
            render(view: "create", model: [folioInventarioInstance: folioInventarioInstance])
        }
    }

    def show = {
        def folioInventarioInstance = FolioInventario.get(params.id)
        if (!folioInventarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
            redirect(action: "list")
        }
        else {
            [folioInventarioInstance: folioInventarioInstance]
        }
    }

    def edit = {
        def folioInventarioInstance = FolioInventario.get(params.id)
        if (!folioInventarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
            redirect(action: "list")
        }
        else {
            return [folioInventarioInstance: folioInventarioInstance]
        }
    }

    def update = {
        def folioInventarioInstance = FolioInventario.get(params.id)
        if (folioInventarioInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (folioInventarioInstance.version > version) {
                    
                    folioInventarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'folioInventario.label', default: 'FolioInventario')] as Object[], "Another user has updated this FolioInventario while you were editing")
                    render(view: "edit", model: [folioInventarioInstance: folioInventarioInstance])
                    return
                }
            }
            folioInventarioInstance.properties = params
            if (!folioInventarioInstance.hasErrors() && folioInventarioInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), folioInventarioInstance.id])
                redirect(action: "show", id: folioInventarioInstance.id)
            }
            else {
                render(view: "edit", model: [folioInventarioInstance: folioInventarioInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def folioInventarioInstance = FolioInventario.get(params.id)
        if (folioInventarioInstance) {
            try {
                folioInventarioInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'folioInventario.label', default: 'FolioInventario'), params.id])
            redirect(action: "list")
        }
    }
}
