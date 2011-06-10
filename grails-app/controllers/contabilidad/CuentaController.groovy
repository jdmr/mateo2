package contabilidad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import au.com.bytecode.opencsv.CSVReader

@Secured(['ROLE_EMP'])
class CuentaController {

    def springSecurityService

    static allowedMethods = [crea: "POST", actualiza: "POST", elimina: "POST"]

    def index = {
        redirect(action: "lista", params: params)
    }

	def lista = {
        def usuario = springSecurityService.currentUser
        def cuentas
        def totalDeCuentas

        def p = [:]
        p.sort = 'numero'

        cuentas = Cuenta.findAllByEmpresa(usuario.empresa,p)
        totalDeCuentas = Cuenta.countByEmpresa(usuario.empresa)

		[cuentas: cuentas, totalDeCuentas: totalDeCuentas]
	}

    def nueva = {
        def cuenta = new Cuenta()
        cuenta.properties = params
        return [cuenta: cuenta]
    }

    def crea = {
        def cuenta = new Cuenta(params)
        def usuario = springSecurityService.currentUser
        cuenta.empresa = usuario.empresa
        if (cuenta.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), cuenta.numero])
            redirect(action: "ver", id: cuenta.id)
        }
        else {
            render(view: "nueva", model: [cuenta: cuenta])
        }
    }

    def ver = {
        def cuenta = Cuenta.get(params.id)
        if (!cuenta) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])
            redirect(action: "lista")
        }
        else {
            [cuenta: cuenta]
        }
    }

    def edita = {
        def cuenta = Cuenta.get(params.id)
        if (!cuenta) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])
            redirect(action: "lista")
        }
        else {
            return [cuenta: cuenta]
        }
    }

    def actualiza = {
        def cuenta = Cuenta.get(params.id)
        if (cuenta) {
            if (params.version) {
                def version = params.version.toLong()
                if (cuenta.version > version) {
                    
                    cuenta.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'cuenta.label', default: 'Cuenta')] as Object[], "Another user has updated this Cuenta while you were editing")
                    render(view: "edit", model: [cuenta: cuenta])
                    return
                }
            }
            cuenta.properties = params
            if (!cuenta.hasErrors() && cuenta.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), cuenta.numero])
                redirect(action: "ver", id: cuenta.id)
            }
            else {
                render(view: "edita", model: [cuenta: cuenta])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])
            redirect(action: "lista")
        }
    }

    def elimina = {
        def cuenta = Cuenta.get(params.id)
        if (cuenta) {
            def numero
            try {
                numero = cuenta.numero
                cuenta.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), numero])
                redirect(action: "lista")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), numero])
                redirect(action: "ver", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cuenta.label', default: 'Cuenta'), params.id])
            redirect(action: "lista")
        }
    }

    def carga = {
        log.debug "Cargando catalogo de cuentas"

        def usuario = springSecurityService.currentUser
        Cuenta.withTransaction {
            Cuenta.executeUpdate("delete from Cuenta where empresa = ?",[usuario.empresa])

            def catalogo = [:]

            CSVReader reader = new CSVReader(new FileReader(servletContext.getRealPath("/WEB-INF/catalogos/catalogo1.csv")))
            String[] nextLine
            def padre
            while(nextLine = reader.readNext()) {
                log.debug "${nextLine[0]} | ${nextLine[1]}"
                if (nextLine[2] != '0') {
                    padre = catalogo[nextLine[2]]
                } else {
                    padre = null
                }
                def cuenta = new Cuenta (
                    codigo : nextLine[0]
                    , numero : nextLine[0]
                    , descripcion : nextLine[1].toUpperCase()
                    , padre : padre
                    , empresa : usuario.empresa
                ).save()
                catalogo[nextLine[0]] = cuenta 
            }
        }

        redirect(action:'lista')
    }
}
