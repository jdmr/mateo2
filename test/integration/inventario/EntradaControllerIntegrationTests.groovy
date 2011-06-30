package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(EntradaController)
class EntradaControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService
    
    @Test
    void debieraMostrarListaDeEntradas() {
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
        def almacen = new Almacen (
            codigo: "TEST"
            , nombre: "TEST"
            , empresa : empresa
        ).save()
        def proveedor = new Proveedor(
            nombre: 'proveedor'
            , nombreCompleto: 'proveedorsanchez'
            , rfc: '3333333333333'
            , empresa: empresa
        ).save()

//        assertNotNull almacen
//        assertNotNull proveedor

        for(i in 1..20) {
            new Entrada (
            	folio: "folio$i"
            	, factura: "factura$i"
            	, comentarios: "no"
            	, tipoCambio: "0.00"
            	, proveedor: proveedor
            	, almacen: almacen
            ).save()
        }

//        def currentUser = springSecurityService.currentUser
        def controller = new EntradaController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/entrada/lista', controller.response.redirectedUrl

        def model = controller.lista()
//        assertNotNull model
//        assertNotNull model.entradas

        assertEquals 10, model.entradas.size()
        assert 20 <= model.totalDeEntradas
    }

    @Test
    void debieraCrearEntrada() {
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

        def proveedor = new Proveedor(
            nombre: 'proveedor'
            , nombreCompleto: 'proveedorsanchez'
            , rfc: '3333333333333'
            , empresa: empresa1
        ).save()
        def almacen = new Almacen(
            codigo: '222'
            , nombre: "almacen1"
            , empresa: empresa1
        ).save()

        def controller = new EntradaController()
        controller.springSecurityService = springSecurityService

        //controller.springSecurityService = springSecurityService
        def model = controller.nueva()
//        assert model
//        assert model.entradaInstance

        controller.params.folio = "folio"
        controller.params.factura = "factura"
        controller.params.comentarion = "no"
        controller.params.tipoCambio = "0.00"
        controller.params.proveedor = proveedor
        controller.params.almacen = almacen
        controller.crea()

//        assert controller

        assert controller.response.redirectedUrl.startsWith('/entrada/ver')
    }

    @Test
    void debieraActualizarEntrada() {
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

        def proveedor = new Proveedor(
            nombre: 'proveedor'
            , nombreCompleto: 'proveedorsanchez'
            , rfc: '3333333333333'
            , empresa: empresa1
        ).save()
        def almacen = new Almacen(
            codigo: '222'
            , nombre: "almacen1"
            , empresa: empresa1
        ).save()

        def entrada = new Entrada (
            folio: "001"
            , factura: "10000"
            , comentarios: "no"
            , tipoCambio: "0.00"
            , proveedor: proveedor
            , almacen: almacen
        ).save()
        assertNotNull entrada
        def controller = new EntradaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = entrada.id
        def model = controller.ver()
//        assert model.entradaInstance
//        assertEquals "001", model.entradaInstance.folio
        assert model.entrada
        assertEquals "001", model.entrada.folio

        controller.params.id = entrada.id
        model = controller.edita()
//        assert model.entradaInstance
//        assertEquals "10000", model.entradaInstance.factura
        assert model.entrada
        assertEquals "10000", model.entrada.factura

        controller.params.id = entrada.id
        controller.params.version = entrada.version
        controller.params.folio = '10002'
        controller.actualiza()
        assertEquals "/entrada/ver/${entrada.id}", controller.response.redirectedUrl

        entrada.refresh()
        assertEquals '10002', entrada.folio
    }

    @Test
    void debieraEliminarEntrada() {
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

        def proveedor = new Proveedor(
            nombre: 'proveedor'
            , nombreCompleto: 'proveedorsanchez'
            , rfc: '3333333333333'
            , empresa: empresa1
        ).save()
        def almacen = new Almacen(
            codigo: '222'
            , nombre: "almacen1"
            , empresa: empresa1
        ).save()

        def entrada = new Entrada (
            folio: "001"
            , factura: "10000"
            , comentarios: "no"
            , tipoCambio: "0.00"
            , proveedor: proveedor
            , almacen: almacen
        ).save()

        def controller = new EntradaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = entrada.id
        def model = controller.ver()
//        assert model.entradaInstance
//        assertEquals "001", model.entradaInstance.folio
        assert model.entrada
        assertEquals "001", model.entrada.folio

        controller.params.id = entrada.id
        controller.elimina()
        assertEquals "/entrada/lista", controller.response.redirectedUrl

        model = Entrada.get(entrada.id)
        assert !model
    }
}