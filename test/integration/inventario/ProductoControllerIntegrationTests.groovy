package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(ProductoController)
class ProductoControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeProductos() {
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
            codigo: "TEST-1"
            , nombre: "TEST-1"
            , empresa : empresa
        ).save()
        def tipoProducto = new TipoProducto(
            nombre: "TEST-1"
            , descripcion: "TEST-1"
            , almacen: almacen
        ).save()

//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa

        for(i in 1..20) {
            new Producto (
                codigo: "TE-$i"
                , sku: "TEST-$i"
                , nombre: "TEST-$i"
                , descripcion: "TEST-$i"
                , marca: "TEST-$i"
                , modelo : "TEST-$i"
                , ubicacion: "TEST-$i"
                , almacen : almacen
                , tipoProducto : tipoProducto
            ).save()
        }

        def controller = new ProductoController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/producto/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.productos.size()
        assert 20 <= model.totalDeProductos
    }

    @Test
    void debieraCrearProducto() {
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
            codigo: "TEST-1"
            , nombre: "TEST-1"
            , empresa : empresa
        ).save()
        def tipoProducto = new TipoProducto(
            nombre: "TEST-1"
            , descripcion: "TEST-1"
            , almacen: almacen
        ).save()

//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa

        def controller = new ProductoController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model.producto

        controller.params.codigo = 'TET-1'
        controller.params.sku = 'TEST-1'
        controller.params.nombre = 'TEST-1'
        controller.params.descripcion = 'TEST-1'
        controller.params.marca = 'TEST-1'
        controller.params.modelo = 'TEST-1'
        controller.params.ubicacion = 'TEST-1'
        controller.params.almacen = almacen
        controller.params.tipoProducto = tipoProducto
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/producto/ver')
    }

    @Test
    void debieraActualizarProducto() {
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
            codigo: "TEST-1"
            , nombre: "TEST-1"
            , empresa : empresa
        ).save()
        def tipoProducto = new TipoProducto(
            nombre: "TEST-1"
            , descripcion: "TEST-1"
            , almacen: almacen
        ).save()

//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa

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

        def controller = new ProductoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = producto.id
        def model = controller.ver()
        assert model.producto
        assertEquals 'TE-1', model.producto.codigo

        controller.params.id = producto.id
        model = controller.edita()
        assert model.producto
        assertEquals 'TE-1', model.producto.codigo

        controller.params.id = producto.id
        controller.params.version = producto.version
        controller.params.codigo = 'TST'
        controller.actualiza()
        assertEquals "/producto/ver/${producto.id}", controller.response.redirectedUrl

        producto.refresh()
        assertEquals 'TST', producto.codigo
    }

    @Test
    void debieraEliminarProducto() {
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
            codigo: "TEST-1"
            , nombre: "TEST-1"
            , empresa : empresa
        ).save()
        def tipoProducto = new TipoProducto(
            nombre: "TEST-1"
            , descripcion: "TEST-1"
            , almacen: almacen
        ).save()

//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa

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

        def controller = new ProductoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = producto.id
        def model = controller.ver()
        assert model.producto
        assertEquals 'TE-1', model.producto.codigo

        controller.params.id = producto.id
        controller.elimina()
        assertEquals "/producto/lista", controller.response.redirectedUrl

        model = Producto.get(producto.id)
        assert !model
    }
}
