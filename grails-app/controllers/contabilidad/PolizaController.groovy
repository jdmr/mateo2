package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class PolizaController {

    def springSecurityService
    def folioService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def usuario = springSecurityService.currentUser
		[polizas: Poliza.findAllByEmpresa(usuario.empresa, params), totalDePolizas: Poliza.countByEmpresa(usuario.empresa)]
	}

    def nueva = {
        def poliza = new Poliza()
        poliza.properties = params
        return [poliza: poliza]
    }

    def nuevaIngreso = {
        def poliza = new Poliza()
        poliza.properties = params
        poliza.tipo = 'INGRESOS'
        return [poliza:poliza]
    }

    def crea = {
        Poliza.withTransaction {
            def usuario = springSecurityService.currentUser
            def poliza = new Poliza(params)
            poliza.folio = folioService.temporal()
            poliza.empresa = usuario.empresa
            if (poliza.save(flush: true)) {
                flash.message = message(code: 'default.created.message', args: [message(code: 'poliza.label', default: 'Poliza'), poliza.folio])
                redirect(action: "ver", id: poliza.id)
            }
            else {
                render(view: "nueva", model: [poliza: poliza])
            }
        }
    }

    def ver = {
        def poliza = Poliza.get(params.id)
        if (!poliza) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
            redirect(action: "lista")
        }
        else {
            [poliza: poliza]
        }
    }

    def edita = {
        def poliza = Poliza.get(params.id)
        if (!poliza) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
            redirect(action: "lista")
        }
        else {
            if (poliza.tipo == 'INGRESOS') {
                render(view:'editaIngreso',model:[poliza:poliza])
            } else {
                return [poliza: poliza]
            }
        }
    }

    def actualiza = {
        Poliza.withTransaction {
            def poliza = Poliza.get(params.id)
            if (poliza) {
                if (params.version) {
                    def version = params.version.toLong()
                    if (poliza.version > version) {
                        poliza.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'poliza.label', default: 'Poliza')] as Object[], "Another user has updated this Poliza while you were editing")
                        render(view: "edita", model: [poliza: poliza])
                        return
                    }
                }
                poliza.properties = params
                if (!poliza.hasErrors() && poliza.save(flush: true)) {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'poliza.label', default: 'Poliza'), poliza.folio])
                    redirect(action: "ver", id: poliza.id)
                }
                else {
                    render(view: "edita", model: [poliza: poliza])
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
                redirect(action: "lista")
            }
        }
    }

    def elimina = {
        def poliza = Poliza.get(params.id)
        if (poliza) {
            if (poliza.estatus == 'ABIERTA') {
                def folio
                try {
                    folio = poliza.folio
                    poliza.delete(flush: true)
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'poliza.label', default: 'Poliza'), folio])
                    redirect(action: "lista")
                }
                catch (org.springframework.dao.DataIntegrityViolationException e) {
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'poliza.label', default: 'Poliza'), folio])
                    redirect(action: "ver", id: params.id)
                }
            } else {
                // Avisarle al usuario que no puede eliminar una poliza
                // a menos de que este ABIERTA
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'poliza.label', default: 'Poliza'), params.id])
            redirect(action: "lista")
        }
    }
}
