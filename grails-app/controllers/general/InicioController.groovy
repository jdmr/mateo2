package general

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class InicioController {
    
    def index = {
        log.debug "Mostrando pagina de inicio"
    }
}
