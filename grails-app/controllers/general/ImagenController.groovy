package general

import grails.converters.JSON

class ImagenController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

	def list = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[imagenInstanceList: Imagen.list(params), imagenInstanceTotal: Imagen.count()]
	}

    def create = {
        def imagenInstance = new Imagen()
        imagenInstance.properties = params
        return [imagenInstance: imagenInstance]
    }

    def save = {
        def imagenInstance = new Imagen(params)
        if (imagenInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'imagen.label', default: 'Imagen'), imagenInstance.id])
            redirect(action: "show", id: imagenInstance.id)
        }
        else {
            render(view: "create", model: [imagenInstance: imagenInstance])
        }
    }

    def show = {
        def imagenInstance = Imagen.get(params.id)
        if (!imagenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
            redirect(action: "list")
        }
        else {
            [imagenInstance: imagenInstance]
        }
    }

    def edit = {
        def imagenInstance = Imagen.get(params.id)
        if (!imagenInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
            redirect(action: "list")
        }
        else {
            return [imagenInstance: imagenInstance]
        }
    }

    def update = {
        def imagenInstance = Imagen.get(params.id)
        if (imagenInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (imagenInstance.version > version) {
                    
                    imagenInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'imagen.label', default: 'Imagen')] as Object[], "Another user has updated this Imagen while you were editing")
                    render(view: "edit", model: [imagenInstance: imagenInstance])
                    return
                }
            }
            imagenInstance.properties = params
            if (!imagenInstance.hasErrors() && imagenInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'imagen.label', default: 'Imagen'), imagenInstance.id])
                redirect(action: "show", id: imagenInstance.id)
            }
            else {
                render(view: "edit", model: [imagenInstance: imagenInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
            redirect(action: "list")
        }
    }

    def delete = {
        def imagenInstance = Imagen.get(params.id)
        if (imagenInstance) {
            try {
                imagenInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'imagen.label', default: 'Imagen'), params.id])
            redirect(action: "list")
        }
    }
}
