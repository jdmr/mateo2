package inventario

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class LoteEntradaController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
                def usuario = springSecurityService.currentUser
//		[loteEntradas: LoteEntrada.findAllByEmpresa(usuario.empresa, params), totalDeLoteEntradas: LoteEntrada.countByEmpresa(usuario.empresa)]
                [loteEntradas: LoteEntrada.list(params), totalDeLoteEntradas: LoteEntrada.count()]
	}

    def nuevo = {
        def loteEntrada = new LoteEntrada()
        loteEntrada.properties = params
        return [loteEntrada: loteEntrada]
    }

    def crea = {
        def loteEntrada = new LoteEntrada(params)
//        def usuario = springSecurityService.currentUser
//        loteEntrada.empresa = usuario.empresa
        if (loteEntrada.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), loteEntrada.id])
            redirect(action: "ver", id: loteEntrada.id)
        }
        else {
            render(view: "nuevo", model: [loteEntrada: loteEntrada])
        }
    }

    def ver = {
        def loteEntrada = LoteEntrada.get(params.id)
        if (!loteEntrada) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
            redirect(action: "lista")
        }
        else {
            [loteEntrada: loteEntrada]
        }
    }

    def edita = {
        def loteEntrada = LoteEntrada.get(params.id)
        if (!loteEntrada) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
            redirect(action: "lista")
        }
        else {
            return [loteEntrada: loteEntrada]
        }
    }

    def actualiza = {
        def loteEntrada = LoteEntrada.get(params.id)
        if (loteEntrada) {
            if (params.version) {
                def version = params.version.toLong()
                if (loteEntrada.version > version) {
                    
                    loteEntrada.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'loteEntrada.label', default: 'LoteEntrada')] as Object[], "Another user has updated this LoteEntrada while you were editing")
                    render(view: "edita", model: [loteEntrada: loteEntrada])
                    return
                }
            }
            loteEntrada.properties = params
            if (!loteEntrada.hasErrors() && loteEntrada.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), loteEntrada.id])
                redirect(action: "ver", id: loteEntrada.id)
            }
            else {
                render(view: "edita", model: [loteEntrada: loteEntrada])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def loteEntrada = LoteEntrada.get(params.id)
        if (loteEntrada) {
//            def nombre
            try {
//                nombre = loteEntrada.nombre
                loteEntrada.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'loteEntrada.label', default: 'LoteEntrada'), params.id])
            redirect(action: "lista")
        }
    }
}
