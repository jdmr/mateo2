package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(TipoProductoController)
class TipoProductoControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeTipoProductos() {
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


        for(i in 1..20) {
//            def tipoP =
            new TipoProducto (
                nombre: "TEST-$i"
                , descripcion: "TEST-$i"
                , almacen : almacen
            ).save()
//            assertNotNull tipoP
        }

        def controller = new TipoProductoController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/tipoProducto/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.tipoProductos.size()
        assert 20 <= model.totalDeTipoProductos
    }

    @Test
    void debieraCrearTipoProducto() {
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

//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa

        def controller = new TipoProductoController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model.tipoProducto

        controller.params.nombre = 'TEST-1'
        controller.params.descripcion = 'TEST-1'
        controller.params.almacen = almacen
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/tipoProducto/ver')
    }

    @Test
    void debieraActualizarTipoProducto() {
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

//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa

        def tipoProducto = new TipoProducto (
            nombre: "TE-"
            , descripcion: "TEST"
            , almacen: almacen
        ).save()

        def controller = new TipoProductoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = tipoProducto.id
        def model = controller.ver()
        assert model.tipoProducto
        assertEquals 'TE-', model.tipoProducto.nombre

        controller.params.id = tipoProducto.id
        model = controller.edita()
        assert model.tipoProducto
        assertEquals 'TE-', model.tipoProducto.nombre

        controller.params.id = tipoProducto.id
        controller.params.version = tipoProducto.version
        controller.params.nombre = 'TST'
        controller.actualiza()
        assertEquals "/tipoProducto/ver/${tipoProducto.id}", controller.response.redirectedUrl

        almacen.refresh()
        assertEquals 'TST', tipoProducto.nombre
    }

    @Test
    void debieraEliminarTipoProducto() {
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
            codigo: "TST-2"
            , nombre: "TEST-1"
            , empresa : empresa
        ).save()

//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa

        def tipoProducto = new TipoProducto (
            nombre: "TE-"
            , descripcion: "TEST"
            , almacen: almacen
        ).save()

        def controller = new TipoProductoController()
        controller.springSecurityService = springSecurityService
        controller.params.id = tipoProducto.id
        def model = controller.ver()
        assert model.tipoProducto
        assertEquals 'TE-', model.tipoProducto.nombre

        controller.params.id = tipoProducto.id
        controller.elimina()
        assertEquals "/tipoProducto/lista", controller.response.redirectedUrl

        model = TipoProducto.get(tipoProducto.id)
        assert !model
    }
}
