package inventario

import general.*
import grails.test.mixin.*
import org.junit.*

@TestFor(SalidaController)
class SalidaControllerIntegrationTests extends BaseIntegrationTest {


    def springSecurityService
    @Test
    void debieraMostrarListaDeSalidas() {
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
        def tipoCliente = new TipoCliente(
            nombre: "test"
            , empresa: empresa
        ) .save()

        def cliente = new Cliente(
            nombre: "Test"
            , nombreCompleto: "Test Cliente"
            , rfc: "1234567890123"
            , empresa: empresa
            , tipoCliente: tipoCliente
        ).save()

        for(i in 1..20) {
            new Salida(
                folio: "folio$i"
                , comentarios: "no$i"
                , empleado: "julian$i"
                , reporte: "reporte$i"
                , departamento: "compras$i"
                , cliente: cliente
                , almacen: almacen
            ).save()
        }

//        def currentUser = springSecurityService.currentUser
        def controller = new SalidaController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/salida/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.salidas.size()
        assert 20 <= model.totalDeSalidas
    }

    @Test
    void debieraCrearSalida() {
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


        def controller = new SalidaController()
        controller.springSecurityService = springSecurityService

        //controller.springSecurityService = springSecurityService
        def model = controller.nueva()
        assert model
        assert model.salida

        controller.params.folio = "folio"
        controller.params.comentarion = "no"
        controller.params.empleado = "julian"
        controller.params.reporte = "reporte"
        controller.params.departamento = "compras"
        controller.params.cliente = cliente
        controller.params.almacen = almacen
        controller.crea()

        assert controller

        assert controller.response.redirectedUrl.startsWith('/salida/ver')
    }

    @Test
    void debieraActualizarSalida() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        assertNotNull organizacion

        def empresa1 = new Empresa(
            codigo: "emp2"
            , nombre: "emp"
            , nombreCompleto: 'emptest'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa1

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

        def salida = new Salida(
            folio: "001"
            , comentarios: "no"
            , empleado: "julian"
            , reporte: "reporte"
            , departamento: "compras"
            , cliente: cliente
            , almacen: almacen
        ).save()

        assertNotNull salida
        def controller = new SalidaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = salida.id
        def model = controller.ver()
        assert model.salida
        assertEquals "001", model.salida.folio

        controller.params.id = salida.id
        model = controller.edita()
        assert model.salida
        assertEquals "julian", model.salida.empleado

        controller.params.id = salida.id
        controller.params.version = salida.version
        controller.params.folio = "10002"
        controller.actualiza()
        assertEquals "/salida/ver/${salida.id}", controller.response.redirectedUrl

        salida.refresh()
        assertEquals "10002", salida.folio
    }

    @Test
    void debieraEliminarSalida() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        assertNotNull organizacion

        def empresa1 = new Empresa(
            codigo: "emp2"
            , nombre: "emp"
            , nombreCompleto: 'emptest'
            , organizacion: organizacion
        ).save()
        assertNotNull empresa1

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

        def salida = new Salida(
            folio: "001"
            , comentarios: "no"
            , empleado: "julian"
            , reporte: "reporte"
            , departamento: "compras"
            , cliente: cliente
            , almacen: almacen
        ).save()

        def controller = new SalidaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = salida.id
        def model = controller.ver()
        assert model.salida
        assertEquals "001", model.salida.folio

        controller.params.id = salida.id
        controller.elimina()
        assertEquals "/salida/lista", controller.response.redirectedUrl

        model = Salida.get(salida.id)
        assert !model
    }
}