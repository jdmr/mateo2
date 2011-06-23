package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(LoteSalidaController)
class LoteSalidaControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeLoteSalidas() {
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
        def tipoProducto = new TipoProducto(
            nombre: "TEST-1"
            , descripcion: "TEST-1"
            , almacen: almacen
        ).save()
        def producto = new Producto (
            codigo: "TE-1"
            , sku: "TEST-"
            , nombre: "TEST-"
            , descripcion: "TEST-"
            , marca: "TEST-"
            , modelo : "TEST-"
            , ubicacion: "TEST-"
            , almacen : almacen
            , tipoProducto : tipoProducto
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
        def salida = new Salida (
            folio: "001"
            , comentarios: "no"
            , empleado: "julian"
            , reporte: "reporte"
            , departamento: "compras"
            , cliente: cliente
            , almacen: almacen
        ).save()

        for(i in 1..20) {
            new LoteSalida (
                cantidad: "1.0$i"
                , producto: producto
                , salida : salida
            ).save()
        }

//        def currentUser = springSecurityService.currentUser
        def controller = new LoteSalidaController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/loteSalida/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.loteSalidas.size()
        assert 20 <= model.totalDeLoteSalidas
    }

    @Test
    void debieraCrearLoteSalida() {
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
        def tipoProducto = new TipoProducto(
            nombre: "TEST-1"
            , descripcion: "TEST-1"
            , almacen: almacen
        ).save()
        def producto = new Producto (
            codigo: "TE-1"
            , sku: "TEST-"
            , nombre: "TEST-"
            , descripcion: "TEST-"
            , marca: "TEST-"
            , modelo : "TEST-"
            , ubicacion: "TEST-"
            , almacen : almacen
            , tipoProducto : tipoProducto
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
        def salida = new Salida (
            folio: "001"
            , comentarios: "no"
            , empleado: "julian"
            , reporte: "reporte"
            , departamento: "compras"
            , cliente: cliente
            , almacen: almacen
        ).save()

        def controller = new LoteSalidaController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()

        controller.params.producto = producto
        controller.params.salida = salida
        controller.crea()

        assert controller.response.redirectedUrl.startsWith('/loteSalida/ver')
    }

    @Test
    void debieraActualizarLoteSalida() {
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
        def tipoProducto = new TipoProducto(
            nombre: "TEST-1"
            , descripcion: "TEST-1"
            , almacen: almacen
        ).save()
        def producto = new Producto (
            codigo: "TE-1"
            , sku: "TEST-"
            , nombre: "TEST-"
            , descripcion: "TEST-"
            , marca: "TEST-"
            , modelo : "TEST-"
            , ubicacion: "TEST-"
            , almacen : almacen
            , tipoProducto : tipoProducto
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
        def salida = new Salida (
            folio: "001"
            , comentarios: "no"
            , empleado: "julian"
            , reporte: "reporte"
            , departamento: "compras"
            , cliente: cliente
            , almacen: almacen
        ).save()

        def loteSalida = new LoteSalida (
            producto: producto
            , salida : salida
        ).save()

        def controller = new LoteSalidaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = loteSalida.id
        def model = controller.ver()
        assert model.loteSalida
        assertEquals "TE-1", model.loteSalida.producto.codigo

        controller.params.id = loteSalida.id
        model = controller.edita()
        assert model.loteSalida
        assertEquals "TE-1", model.loteSalida.producto.codigo

        controller.params.id = loteSalida.id
        controller.params.version = loteSalida.version
//        controller.params.cantidad = "2.00"
        producto.codigo = "TE-2"
        controller.actualiza()
        assertEquals "/loteSalida/ver/${loteSalida.id}", controller.response.redirectedUrl

        loteSalida.refresh()
        assertEquals "TE-2", model.loteSalida.producto.codigo
    }

    @Test
    void debieraEliminarLoteSalida() {
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
        def tipoProducto = new TipoProducto(
            nombre: "TEST-1"
            , descripcion: "TEST-1"
            , almacen: almacen
        ).save()
        def producto = new Producto (
            codigo: "TE-1"
            , sku: "TEST-"
            , nombre: "TEST-"
            , descripcion: "TEST-"
            , marca: "TEST-"
            , modelo : "TEST-"
            , ubicacion: "TEST-"
            , almacen : almacen
            , tipoProducto : tipoProducto
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
        def salida = new Salida (
            folio: "001"
            , comentarios: "no"
            , empleado: "julian"
            , reporte: "reporte"
            , departamento: "compras"
            , cliente: cliente
            , almacen: almacen
        ).save()

        def loteSalida = new LoteSalida (
            producto: producto
            , salida : salida
        ).save()

        def controller = new LoteSalidaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = loteSalida.id
        def model = controller.ver()
        assert model.loteSalida
        assertEquals "TE-1", model.loteSalida.producto.codigo

        controller.params.id = loteSalida.id
        controller.elimina()
        assertEquals "/loteSalida/lista", controller.response.redirectedUrl

        model = LoteSalida.get(loteSalida.id)
        assert !model
    }
}