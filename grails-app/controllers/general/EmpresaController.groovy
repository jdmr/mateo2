package general

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

@Secured(['ROLE_ORG'])
class EmpresaController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
		params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def usuario = springSecurityService.currentUser
		[empresas: Empresa.findAllByOrganizacion(usuario.empresa.organizacion, params), totalDeEmpresas: Empresa.countByOrganizacion(usuario.empresa.organizacion)]
	}

    def nueva = {
        def empresa = new Empresa()
        empresa.properties = params
        return [empresa: empresa]
    }

    def crea = {
        Empresa.withTransaction {
            def empresa = new Empresa(params)
            def usuario = springSecurityService.currentUser
            empresa.organizacion = usuario.empresa.organizacion
            if (empresa.save(flush: true)) {
                // Actualizando empresa del usuario
                usuario.empresa = empresa
                usuario.save()

                flash.message = message(code: 'default.created.message', args: [message(code: 'empresa.label', default: 'Empresa'), empresa.nombre])
                redirect(action: "ver", id: empresa.id)
            }
            else {
                render(view: "nueva", model: [empresa: empresa])
            }
        }
    }

    def ver = {
        def empresa = Empresa.get(params.id)
        if (!empresa) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
            redirect(action: "lista")
        }
        else {
            [empresa: empresa]
        }
    }

    def edita = {
        def empresa = Empresa.get(params.id)
        if (!empresa) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
            redirect(action: "lista")
        }
        else {
            return [empresa: empresa]
        }
    }

    def actualiza = {
        Empresa.withTransaction {
            def empresa = Empresa.get(params.id)
            if (empresa) {
                if (params.version) {
                    def version = params.version.toLong()
                    if (empresa.version > version) {
                        
                        empresa.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'empresa.label', default: 'Empresa')] as Object[], "Another user has updated this Empresa while you were editing")
                        render(view: "edita", model: [empresa: empresa])
                        return
                    }
                }
                empresa.properties = params
                def usuario = springSecurityService.currentUser
                empresa.organizacion = usuario.empresa.organizacion
                if (!empresa.hasErrors() && empresa.save(flush: true)) {
                    // Actualizando empresa del usuario
                    usuario.empresa = empresa
                    usuario.save()

                    flash.message = message(code: 'default.updated.message', args: [message(code: 'empresa.label', default: 'Empresa'), empresa.nombre])
                    redirect(action: "ver", id: empresa.id)
                }
                else {
                    render(view: "edita", model: [empresa: empresa])
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
                redirect(action: "lista")
            }
        }
    }

    def elimina = {
        Empresa.withTransaction {
            def empresa = Empresa.get(params.id)
            if (empresa) {
                def usuario = springSecurityService.currentUser
                if (usuario.empresa.organizacion == empresa.organizacion) {
                    def nombre
                    try {
                        // Cambiar de empresa al usuario
                        if (usuario.empresa == empresa) {
                            def empresas = Empresa.findAllByOrganizacion(empresa.organizacion)
                            for (x in empresas) {
                                if (x != empresa) {
                                    usuario.empresa = x
                                    break
                                }
                            }

                            if (usuario.empresa == empresa) {
                                flash.message = message(code:'empresa.noEliminada.message', args: [empresa.nombre])
                                redirect(action:'ver',id:empresa.id)
                            }
                        }

                        nombre = empresa.nombre
                        empresa.delete(flush: true)
                        flash.message = message(code: 'default.deleted.message', args: [message(code: 'empresa.label', default: 'Empresa'), nombre])
                        redirect(action: "lista")
                    }
                    catch (org.springframework.dao.DataIntegrityViolationException e) {
                        flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'empresa.label', default: 'Empresa'), nombre])
                        redirect(action: "ver", id: params.id)
                    }
                }
            }
            else {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'empresa.label', default: 'Empresa'), params.id])
                redirect(action: "lista")
            }
        }
    }
}
