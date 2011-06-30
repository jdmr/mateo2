package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

@TestFor(CancelacionAlmacenController)
class CancelacionAlmacenControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeCancelacionAlmacen() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

        def empresa1 = new Empresa(
            codigo: "emp2"
            , nombre: "emp"
            , nombreCompleto: 'emptest'
            , organizacion: organizacion
        ).save()

        def almacen = new Almacen(
            codigo: '222'
            , nombre: "almacen1"
            , empresa: empresa1
        ).save()
        assertNotNull almacen

        for(i in 1..20) {
            new CancelacionAlmacen(
            	folio: "10$i"
            	, creador: "persona"
            	, almacen: almacen
            ).save()
        }

        def currentUser = springSecurityService.currentUser
        def controller = new CancelacionAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/cancelacionAlmacen/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertNotNull model
        assertNotNull model.cancelacionAlmacenes

        assertEquals 10, model.cancelacionAlmacenes.size()
        assert 20 <= model.totalDeCancelacionAlmacen
    }

    @Test
    void debieraCrearCancelacionAlmacen() {
    	authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

        def empresa1 = new Empresa(
            codigo: "emp2"
            , nombre: "emp"
            , nombreCompleto: 'emptest'
            , organizacion: organizacion
        ).save()

        def almacen = new Almacen(
            codigo: '222'
            , nombre: "almacen1"
            , empresa: empresa1
        ).save()

        def controller = new CancelacionAlmacenController()
        controller.springSecurityService = springSecurityService

        //controller.springSecurityService = springSecurityService
        def model = controller.nueva()
        assert model
        assert model.cancelacionAlmacen

        controller.params.folio = "100"
        controller.params.creador = "persona"
        controller.params.almacen = almacen
        controller.crea()

        assert controller

        assert controller.response.redirectedUrl.startsWith('/cancelacionAlmacen/ver')
    }

    @Test
    void debieraActualizarCancelacionAlmacen() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

        def empresa1 = new Empresa(
            codigo: "emp2"
            , nombre: "emp"
            , nombreCompleto: 'emptest'
            , organizacion: organizacion
        ).save()

        def almacen = new Almacen(
            codigo: '222'
            , nombre: "almacen1"
            , empresa: empresa1
        ).save()

        def cancelacionAlmacen = new CancelacionAlmacen(
            folio: "100"
            , creador: "persona"
            , almacen: almacen
        ).save()

        assertNotNull cancelacionAlmacen
        def controller = new CancelacionAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = cancelacionAlmacen.id
        def model = controller.ver()
        assert model.cancelacionAlmacen
        assertEquals "100", model.cancelacionAlmacen.folio

        controller.params.id = cancelacionAlmacen.id
        model = controller.edita()
        assert model.cancelacionAlmacen
        assertEquals "persona", model.cancelacionAlmacen.creador

        controller.params.id = cancelacionAlmacen.id
        controller.params.version = cancelacionAlmacen.version
        controller.params.folio = '10002'
        controller.actualiza()
        assertEquals "/cancelacionAlmacen/ver/${cancelacionAlmacen.id}", controller.response.redirectedUrl

        cancelacionAlmacen.refresh()
        assertEquals '10002', cancelacionAlmacen.folio
    }

    @Test
    void debieraEliminarCancelacionAlmacen() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

        def empresa1 = new Empresa(
            codigo: "emp2"
            , nombre: "emp"
            , nombreCompleto: 'emptest'
            , organizacion: organizacion
        ).save()

        def almacen = new Almacen(
            codigo: '222'
            , nombre: "almacen1"
            , empresa: empresa1
        ).save()

        def cancelacionAlmacen = new CancelacionAlmacen(
            folio: "100"
            , creador: "persona"
            , almacen: almacen
        ).save()

        def controller = new CancelacionAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = cancelacionAlmacen.id
        def model = controller.ver()
        assert model.cancelacionAlmacen
        assertEquals "100", model.cancelacionAlmacen.folio

        controller.params.id = cancelacionAlmacen.id
        controller.elimina()
        assertEquals "/cancelacionAlmacen/lista", controller.response.redirectedUrl

        model = CancelacionAlmacen.get(cancelacionAlmacen.id)
        assert !model
    }
}