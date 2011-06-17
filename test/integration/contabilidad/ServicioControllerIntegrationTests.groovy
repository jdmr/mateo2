package contabilidad

import general.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

@TestFor(ServicioController)
class ServicioControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeServicios() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
            , organizacion: organizacion
        ).save()
        
        def currentUser = springSecurityService.currentUser
        currentUser.empresa = empresa

        for(i in 1..20) {
            new Servicio (
                nombre: "TEST-$i"
                , descripcion: "TEST-$i"
                , empresa : empresa
            ).save()
        }
        
        def controller = new ServicioController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/servicio/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.servicios.size()
        assert 20 <= model.totalDeServicios
    }

    @Test
    void debieraCrearServicio() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
            , organizacion: organizacion
        ).save()
        
        def currentUser = springSecurityService.currentUser
        currentUser.empresa = empresa

        def controller = new ServicioController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model.servicio

        controller.params.nombre = 'TEST-1'
        controller.params.descripcion = 'TEST-1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/servicio/ver')
    }

    @Test
    void debieraActualizarServicio() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
            , organizacion: organizacion
        ).save()
        
        def currentUser = springSecurityService.currentUser
        currentUser.empresa = empresa

        def servicio = new Servicio (
            nombre: "TEST-1"
            , descripcion : 'TEST-1'
            , empresa : empresa
        ).save()
        
        def controller = new ServicioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = servicio.id
        def model = controller.ver()
        assert model.servicio
        assertEquals 'TEST-1', model.servicio.descripcion

        controller.params.id = servicio.id
        model = controller.edita()
        assert model.servicio
        assertEquals 'TEST-1', model.servicio.descripcion

        println "descripcion: $servicio.descripcion"
        controller.params.id = servicio.id
        controller.params.version = servicio.version
        controller.params.descripcion = 'TEST-2'
        controller.actualiza()
        assertEquals "/servicio/ver/${servicio.id}", controller.response.redirectedUrl

        println "descripcion: $servicio.descripcion"
        assertEquals 'TEST-2', servicio.descripcion
    }

    @Test
    void debieraEliminarServicio() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
            , organizacion: organizacion
        ).save()
        
        def currentUser = springSecurityService.currentUser
        currentUser.empresa = empresa

        def servicio = new Servicio (
            nombre: "TEST-1"
            , descripcion : 'TEST-1'
            , empresa : empresa
        ).save()
        
        def controller = new ServicioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = servicio.id
        def model = controller.ver()
        assert model.servicio
        assertEquals 'TEST-1', model.servicio.descripcion

        controller.params.id = servicio.id
        controller.elimina()
        assertEquals "/servicio/lista", controller.response.redirectedUrl

        model = Servicio.get(servicio.id)
        assert !model
    }
}
