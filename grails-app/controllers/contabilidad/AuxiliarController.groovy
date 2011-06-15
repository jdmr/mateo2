package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_EMP'])
class AuxiliarController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def usuario = springSecurityService.currentUser
		[auxiliares: Auxiliar.findAllByOrganizacion(usuario.empresa.organizacion, params), totalDeAuxiliares: Auxiliar.countByOrganizacion(usuario.empresa.organizacion)]
	}

    def nuevo = {
        def auxiliar = new Auxiliar()
        auxiliar.properties = params
        return [auxiliar: auxiliar]
    }

    def crea = {
        Auxiliar.withTransaction {
            def auxiliar = new Auxiliar(params)
            def usuario = springSecurityService.currentUser
            auxiliar.organizacion = usuario.empresa.organizacion

            def tags = params.list('tags')
            for(tag in tags) {
                log.debug("Buscando TAG: $tag")
                def cuenta = Cuenta.findByOrganizacionAndDescripcion(usuario.empresa.organizacion, tag)
                cuenta.tieneAuxiliares = true
                auxiliar.addToCuentas(cuenta)
            }

            if (auxiliar.save(flush: true)) {
                flash.message = message(code: 'default.created.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), auxiliar.numero])
                redirect(action: "ver", id: auxiliar.id)
            }
            else {
                render(view: "nuevo", model: [auxiliar: auxiliar])
            }
        }
    }

    def ver = {
        def auxiliar = Auxiliar.get(params.id)
        if (!auxiliar) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])
            redirect(action: "lista")
        }
        else {
            [auxiliar: auxiliar]
        }
    }

    def edita = {
        def auxiliar = Auxiliar.get(params.id)
        if (!auxiliar) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])
            redirect(action: "lista")
        }
        else {
            return [auxiliar: auxiliar]
        }
    }

    def actualiza = {
        Auxiliar.withTransaction {
            def auxiliar = Auxiliar.get(params.id)
            if (auxiliar) {
                if (params.version) {
                    def version = params.version.toLong()
                    if (auxiliar.version > version) {
                        
                        auxiliar.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'auxiliar.label', default: 'Auxiliar')] as Object[], "Another user has updated this Auxiliar while you were editing")
                        render(view: "edita", model: [auxiliar: auxiliar])
                        return
                    }
                }
                auxiliar.properties = params
                def usuario = springSecurityService.currentUser
                auxiliar.organizacion = usuario.empresa.organizacion

                log.debug("TAGS: ${params.tags}")
                def temp = []
                auxiliar.cuentas.each {
                    temp << it
                }
                temp.each { cuenta ->
                    auxiliar.removeFromCuentas(cuenta)
                }
                def tags = params.list('tags')
                for(tag in tags) {
                    log.debug("Buscando TAG: $tag")
                    def cuenta = Cuenta.findByOrganizacionAndDescripcion(usuario.empresa.organizacion, tag)
                    cuenta.tieneAuxiliares = true
                    auxiliar.addToCuentas(cuenta)
                    temp.remove(cuenta)
                }
                temp.each { cuenta ->
                    log.debug("Verificando auxiliares de $cuenta.descripcion")
                    cuenta.refresh()
                    if (!cuenta.getAuxiliares()) {
                        cuenta.tieneAuxiliares = false
                        cuenta.save()
                    }
                }

                if (!auxiliar.hasErrors() && auxiliar.save(flush: true)) {
                    flash.message = message(code: 'default.updated.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), auxiliar.numero])
                    redirect(action: "ver", id: auxiliar.id)
                }
                else {
                    render(view: "edita", model: [auxiliar: auxiliar])
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])
                redirect(action: "lista")
            }
        }
    }

    def elimina = {
        Auxiliar.withTransaction {
            def auxiliar = Auxiliar.get(params.id)
            if (auxiliar) {
                def numero = auxiliar.numero
                try {
                    auxiliar.delete(flush: true)
                    flash.message = message(code: 'default.deleted.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), numero])
                    redirect(action: "lista")
                }
                catch (org.springframework.dao.DataIntegrityViolationException e) {
                    flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), numero])
                    redirect(action: "ver", id: params.id)
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'auxiliar.label', default: 'Auxiliar'), params.id])
                redirect(action: "lista")
            }
        }
    }

    def tags = {
        def usuario = springSecurityService.currentUser
        render Cuenta.findAllByOrganizacionAndDescripcionIlike(usuario.empresa.organizacion,"%${params.term}%",[max:10])*.descripcion as grails.converters.JSON
    }
}
