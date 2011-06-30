package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class LoteSalidaController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
                def usuario = springSecurityService.currentUser
//		[loteSalidas: LoteSalida.findAllByEmpresa(usuario.empresa, params), totalDeLoteSalidas: LoteSalida.countByEmpresa(usuario.empresa)]
                [loteSalidas: LoteSalida.list(params), totalDeLoteSalidas: LoteSalida.count()]
	}

    def nuevo = {
        def loteSalida = new LoteSalida()
        loteSalida.properties = params
        return [loteSalida: loteSalida]
    }

    def crea = {
        def loteSalida = new LoteSalida(params)
//        def usuario = springSecurityService.currentUser
//        loteSalida.empresa = usuario.empresa
        if (loteSalida.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), loteSalida.id])
            redirect(action: "ver", id: loteSalida.id)
        }
        else {
            render(view: "nuevo", model: [loteSalida: loteSalida])
        }
    }

    def ver = {
        def loteSalida = LoteSalida.get(params.id)
        if (!loteSalida) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
            redirect(action: "lista")
        }
        else {
            [loteSalida: loteSalida]
        }
    }

    def edita = {
        def loteSalida = LoteSalida.get(params.id)
        if (!loteSalida) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
            redirect(action: "lista")
        }
        else {
            return [loteSalida: loteSalida]
        }
    }

    def actualiza = {
        def loteSalida = LoteSalida.get(params.id)
        if (loteSalida) {
            if (params.version) {
                def version = params.version.toLong()
                if (loteSalida.version > version) {
                    
                    loteSalida.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'loteSalida.label', default: 'LoteSalida')] as Object[], "Another user has updated this LoteSalida while you were editing")
                    render(view: "edita", model: [loteSalida: loteSalida])
                    return
                }
            }
            loteSalida.properties = params
            if (!loteSalida.hasErrors() && loteSalida.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), loteSalida.id])
                redirect(action: "ver", id: loteSalida.id)
            }
            else {
                render(view: "edita", model: [loteSalida: loteSalida])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def loteSalida = LoteSalida.get(params.id)
        if (loteSalida) {
//            def nombre
            try {
//                nombre = loteSalida.nombre
                loteSalida.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteSalida.label', default: 'LoteSalida'), params.id])
            redirect(action: "lista")
        }
    }
}
