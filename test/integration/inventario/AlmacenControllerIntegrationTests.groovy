package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(AlmacenController)
class AlmacenControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeAlmacenes() {
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
            new Almacen (
                codigo: "TEST-$i"
                , nombre: "TEST-$i"
                , empresa : empresa
            ).save()
        }

        def controller = new AlmacenController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/almacen/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.almacenes.size()
        assert 20 <= model.totalDeAlmacenes
    }

    @Test
    void debieraCrearAlmacen() {
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

        def controller = new AlmacenController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model.almacen

        controller.params.codigo = 'TEST-1'
        controller.params.nombre = 'TEST-1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/almacen/ver')
    }

    @Test
    void debieraActualizarAlmacen() {
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

        def almacen = new Almacen (
            codigo: "TST-1"
            , nombre: "TEST-1"
            , empresa : empresa
        ).save()

        def controller = new AlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = almacen.id
        def model = controller.ver()
        assert model.almacen
        assertEquals 'TST-1', model.almacen.codigo

        controller.params.id = almacen.id
        model = controller.edita()
        assert model.almacen
        assertEquals 'TST-1', model.almacen.codigo

        controller.params.id = almacen.id
        controller.params.version = almacen.version
        controller.params.codigo = 'TST-2'
        controller.actualiza()
        assertEquals "/almacen/ver/${almacen.id}", controller.response.redirectedUrl

        almacen.refresh()
        assertEquals 'TST-2', almacen.codigo
    }

    @Test
    void debieraEliminarAlmacen() {
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

        def almacen = new Almacen (
            codigo: "TST-2"
            , nombre: "TEST-1"
            , empresa : empresa
        ).save()

        def controller = new AlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = almacen.id
        def model = controller.ver()
        assert model.almacen
        assertEquals 'TST-2', model.almacen.codigo

        controller.params.id = almacen.id
        controller.elimina()
        assertEquals "/almacen/lista", controller.response.redirectedUrl

        model = Almacen.get(almacen.id)
        assert !model
    }
}
