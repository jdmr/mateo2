package general

import grails.converters.JSON

class UsuarioController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
		[usuarios: Usuario.list(params), totalDeUsuarios: Usuario.count()]
	}

    def nuevo = {
        def usuario = new Usuario()
        usuario.properties = params
        return [usuario: usuario]
    }

    def crea = {
        def usuario = new Usuario(params)
        usuario.password = springSecurityService.encodePassword(params.password)
        if (usuario.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuario.username])
            redirect(action: "ver", id: usuario.id)
        }
        else {
            log.error("Hubo un error al crear el usuario ${usuario.errors}")
            render(view: "crea", model: [usuario: usuario])
        }
    }

    def ver = {
        def usuario = Usuario.get(params.id)
        if (!usuario) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "lista")
        }
        else {
            [usuario: usuario]
        }
    }

    def edita = {
        def usuario = Usuario.get(params.id)
        if (!usuario) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "lista")
        }
        else {
            return [usuario: usuario]
        }
    }

    def actualiza = {
        def usuario = Usuario.get(params.id)
        if (usuario) {
            if (params.version) {
                def version = params.version.toLong()
                if (usuario.version > version) {
                    
                    usuario.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'usuario.label', default: 'Usuario')] as Object[], "Another user has updated this Usuario while you were editing")
                    render(view: "edita", model: [usuario: usuario])
                    return
                }
            }
            if (usuario.password != params.password) {
                usuario.password = springSecurityService.encodePassword(params.password)
            }
            params.remove('password')
            usuario.properties = params
            if (!usuario.hasErrors() && usuario.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuario.username])
                redirect(action: "ver", id: usuario.id)
            }
            else {
                render(view: "edita", model: [usuario: usuario])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def usuario = Usuario.get(params.id)
        if (usuario) {
            try {
                def nombre = usuario.username
                usuario.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), nombre])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "lista")
        }
    }
}
