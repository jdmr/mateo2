package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(FolioInventarioController)
class FolioInventarioControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeFolioInventarios() {
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
            new FolioInventario (
                nombre: "TEST-$i"
                , valor: "$i"
                , almacen : almacen
            ).save()
        }

        def controller = new FolioInventarioController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/folioInventario/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.folioInventarios.size()
        assert 20 <= model.totalDeFolioInventarios
    }

    @Test
    void debieraCrearFolioInventario() {
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

        def controller = new FolioInventarioController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model.folioInventario

        controller.params.nombre = 'TEST-1'
        controller.params.valor = '1'
        controller.params.almacen = almacen
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/folioInventario/ver')
    }

    @Test
    void debieraActualizarFolioInventario() {
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

        def folioInventario = new FolioInventario (
            nombre: "TE-"
            , valor: "123"
            , almacen: almacen
        ).save()

        def controller = new FolioInventarioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = folioInventario.id
        def model = controller.ver()
        assert model.folioInventario
        assertEquals 'TE-', model.folioInventario.nombre

        controller.params.id = folioInventario.id
        model = controller.edita()
        assert model.folioInventario
        assertEquals 'TE-', model.folioInventario.nombre

        controller.params.id = folioInventario.id
        controller.params.version = folioInventario.version
        controller.params.nombre = 'TST'
        controller.actualiza()
        assertEquals "/folioInventario/ver/${folioInventario.id}", controller.response.redirectedUrl

        almacen.refresh()
        assertEquals 'TST', folioInventario.nombre
    }

    @Test
    void debieraEliminarFolioInventario() {
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

        def folioInventario = new FolioInventario (
            nombre: "TE-"
            , valor: "146"
            , almacen: almacen
        ).save()

        def controller = new FolioInventarioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = folioInventario.id
        def model = controller.ver()
        assert model.folioInventario
        assertEquals 'TE-', model.folioInventario.nombre

        controller.params.id = folioInventario.id
        controller.elimina()
        assertEquals "/folioInventario/lista", controller.response.redirectedUrl

        model = FolioInventario.get(folioInventario.id)
        assert !model
    }
}