package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(LoteEntradaController)
class LoteEntradaControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeLoteEntradas() {
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
        def proveedor = new Proveedor(
            nombre: 'proveedor'
            , nombreCompleto: 'proveedorsanchez'
            , rfc: '3333333333333'
            , empresa: empresa
        ).save()
        def entrada = new Entrada (
            folio: "001"
            , factura: "10000"
            , comentarios: "no"
            , tipoCambio: "0.00"
            , proveedor: proveedor
            , almacen: almacen
        ).save()

        for(i in 1..20) {
            new LoteEntrada (
                cantidad: "1.0$i"
                , producto: producto
                , entrada : entrada
            ).save()
        }

//        def currentUser = springSecurityService.currentUser
        def controller = new LoteEntradaController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/loteEntrada/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.loteEntradas.size()
        assert 20 <= model.totalDeLoteEntradas
    }

    @Test
    void debieraCrearLoteEntrada() {
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
        def proveedor = new Proveedor(
            nombre: 'proveedor'
            , nombreCompleto: 'proveedorsanchez'
            , rfc: '3333333333333'
            , empresa: empresa
        ).save()
        def entrada = new Entrada (
            folio: "001"
            , factura: "10000"
            , comentarios: "no"
            , tipoCambio: "0.00"
            , proveedor: proveedor
            , almacen: almacen
        ).save()

        def controller = new LoteEntradaController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()

        controller.params.producto = producto
        controller.params.entrada = entrada
        controller.crea()

        assert controller.response.redirectedUrl.startsWith('/loteEntrada/ver')
    }

    @Test
    void debieraActualizarLoteEntrada() {
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
        def proveedor = new Proveedor(
            nombre: 'proveedor'
            , nombreCompleto: 'proveedorsanchez'
            , rfc: '3333333333333'
            , empresa: empresa
        ).save()
        def entrada = new Entrada (
            folio: "001"
            , factura: "10000"
            , comentarios: "no"
            , tipoCambio: "0.00"
            , proveedor: proveedor
            , almacen: almacen
        ).save()

        def loteEntrada = new LoteEntrada (
            producto: producto
            , entrada : entrada
        ).save()

        def controller = new LoteEntradaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = loteEntrada.id
        def model = controller.ver()
        assert model.loteEntrada
        assertEquals "TE-1", model.loteEntrada.producto.codigo

        controller.params.id = loteEntrada.id
        model = controller.edita()
        assert model.loteEntrada
        assertEquals "TE-1", model.loteEntrada.producto.codigo

        controller.params.id = loteEntrada.id
        controller.params.version = loteEntrada.version
//        controller.params.cantidad = "2.00"
        producto.codigo = "TE-2"
        controller.actualiza()
        assertEquals "/loteEntrada/ver/${loteEntrada.id}", controller.response.redirectedUrl

        loteEntrada.refresh()
        assertEquals "TE-2", model.loteEntrada.producto.codigo
    }

    @Test
    void debieraEliminarLoteEntrada() {
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
        def proveedor = new Proveedor(
            nombre: 'proveedor'
            , nombreCompleto: 'proveedorsanchez'
            , rfc: '3333333333333'
            , empresa: empresa
        ).save()
        def entrada = new Entrada (
            folio: "001"
            , factura: "10000"
            , comentarios: "no"
            , tipoCambio: "0.00"
            , proveedor: proveedor
            , almacen: almacen
        ).save()

        def loteEntrada = new LoteEntrada (
            producto: producto
            , entrada : entrada
        ).save()

        def controller = new LoteEntradaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = loteEntrada.id
        def model = controller.ver()
        assert model.loteEntrada
        assertEquals "TE-1", model.loteEntrada.producto.codigo

        controller.params.id = loteEntrada.id
        controller.elimina()
        assertEquals "/loteEntrada/lista", controller.response.redirectedUrl

        model = LoteEntrada.get(loteEntrada.id)
        assert !model
    }
}