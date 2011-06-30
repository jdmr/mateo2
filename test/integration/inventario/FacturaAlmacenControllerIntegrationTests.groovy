package inventario

import general.*
import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

@TestFor(FacturaAlmacenController)
class FacturaAlmacenControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService
    
    @Test
    void debieraMostrarListaDeFacturaAlmacen() {
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

        def tipoCliente = new TipoCliente(
            nombre: "test"
            , empresa: empresa1
        ) .save()
        assertNotNull tipoCliente

        def cliente = new Cliente(
            nombre: "Test"
            , nombreCompleto: "Test Cliente"
            , rfc: "1234567890123"
            , empresa: empresa1
            , tipoCliente: tipoCliente
        ).save()

        for(i in 1..20) {
            new FacturaAlmacen (
            	folio: "folio$i"
            	, fecha: new Date()
            	, cliente: cliente
            	, almacen: almacen
            ).save()
        }

//        def currentUser = springSecurityService.currentUser
        def controller = new FacturaAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/facturaAlmacen/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertNotNull model
        assertNotNull model.facturaAlmacenes

        assertEquals 10, model.facturaAlmacenes.size()
        assert 20 <= model.totalDeFacturaAlmacenes
    }

    @Test
    void debieraCrearFacturaAlmacen() {
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

        def tipoCliente = new TipoCliente(
            nombre: "test"
            , empresa: empresa1
        ) .save()
        assertNotNull tipoCliente

        def cliente = new Cliente(
            nombre: "Test"
            , nombreCompleto: "Test Cliente"
            , rfc: "1234567890123"
            , empresa: empresa1
            , tipoCliente: tipoCliente
        ).save()

        def controller = new FacturaAlmacenController()
        controller.springSecurityService = springSecurityService

        //controller.springSecurityService = springSecurityService
        def model = controller.nueva()
        assert model
        assert model.facturaAlmacen

        controller.params.folio = "folio"
        controller.params.cliente = cliente
        controller.params.almacen = almacen
        controller.params.fecha = new Date()
        controller.crea()

        assert controller

        assert controller.response.redirectedUrl.startsWith('/facturaAlmacen/ver')
    }

    @Test
    void debieraActualizarFacturaAlmacen() {
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

        def tipoCliente = new TipoCliente(
            nombre: "test"
            , empresa: empresa1
        ) .save()
        assertNotNull tipoCliente

        def cliente = new Cliente(
            nombre: "Test"
            , nombreCompleto: "Test Cliente"
            , rfc: "1234567890123"
            , empresa: empresa1
            , tipoCliente: tipoCliente
        ).save()

        def facturaAlmacen = new FacturaAlmacen (
            folio: "100"
            , cliente: cliente
            , almacen: almacen
            , fecha: new Date()
        ).save()

        assertNotNull facturaAlmacen
        def controller = new FacturaAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = facturaAlmacen.id
        def model = controller.ver()
        assert model.facturaAlmacen
        assertEquals "100", model.facturaAlmacen.folio

        controller.params.id = facturaAlmacen.id
        model = controller.edita()
        assert model.facturaAlmacen
        assertEquals cliente, model.facturaAlmacen.cliente

        controller.params.id = facturaAlmacen.id
        controller.params.version = facturaAlmacen.version
        controller.params.folio = '10002'
        controller.params.fecha = new Date()
        controller.actualiza()
        assertEquals "/facturaAlmacen/ver/${facturaAlmacen.id}", controller.response.redirectedUrl

        facturaAlmacen.refresh()
        assertEquals '10002', facturaAlmacen.folio
    }

    @Test
    void debieraEliminarFacturaAlmacen() {
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

        def tipoCliente = new TipoCliente(
            nombre: "test"
            , empresa: empresa1
        ) .save()
        assertNotNull tipoCliente

        def cliente = new Cliente(
            nombre: "Test"
            , nombreCompleto: "Test Cliente"
            , rfc: "1234567890123"
            , empresa: empresa1
            , tipoCliente: tipoCliente
        ).save()

        def facturaAlmacen = new FacturaAlmacen (
            folio: "100"
            , cliente: cliente
            , almacen: almacen
            , fecha: new Date()
        ).save()

        def controller = new FacturaAlmacenController()
        controller.springSecurityService = springSecurityService
        controller.params.id = facturaAlmacen.id
        def model = controller.ver()
        assert model.facturaAlmacen
        assertEquals '100', model.facturaAlmacen.folio

        controller.params.id = facturaAlmacen.id
        controller.elimina()
        assertEquals "/facturaAlmacen/lista", controller.response.redirectedUrl

        model = FacturaAlmacen.get(facturaAlmacen.id)
        assert !model
    }
}