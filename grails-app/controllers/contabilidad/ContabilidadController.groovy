package contabilidad

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class ContabilidadController {

    def index = {
        log.debug "Mostrando pagina de inicio de contabilidad"
    }
}
