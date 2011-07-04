package inventario

import grails.plugins.springsecurity.Secured

@Secured(['ROLE_USER'])
class InventarioController {

    def index = {}
}
