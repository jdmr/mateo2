package general

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(ProveedorController)
class ProveedorControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeProveedores() {
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
            def proveedor = new Proveedor (
                nombre : "TEST-$i"
                , nombreCompleto : "TEST-$i"
                , rfc: "TST00000000$i"
                , empresa : empresa
            ).save()
        }

        def controller = new ProveedorController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/proveedor/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.proveedores.size()
        assert 20 <= model.totalDeProveedores
    }

    @Test
    void debieraCrearProveedor() {
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

        def controller = new ProveedorController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model.proveedor

        controller.params.nombre = 'TEST-1'
        controller.params.nombreCompleto = 'TEST-1'
        controller.params.rfc = 'TST000000001'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/proveedor/ver')
    }

    @Test
    void debieraActualizarProveedor() {
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
        def proveedor = new Proveedor (
            nombre : 'TEST-1'
            , nombreCompleto : 'TEST-1'
            , rfc : 'TST000000001'
            , empresa : empresa
        ).save()

        def controller = new ProveedorController()
        controller.springSecurityService = springSecurityService
        controller.params.id = proveedor.id
        def model = controller.ver()
        assert model.proveedor
        assertEquals 'TEST-1', model.proveedor.nombre

        controller.params.id = proveedor.id
        model = controller.edita()
        assert model.proveedor
        assertEquals 'TEST-1', model.proveedor.nombre

        controller.params.id = proveedor.id
        controller.params.version = proveedor.version
        controller.params.nombre = 'TEST-2'
        controller.actualiza()
        assertEquals "/proveedor/ver/${proveedor.id}", controller.response.redirectedUrl

        proveedor.refresh()
        assertEquals 'TEST-2', proveedor.nombre
    }

    @Test
    void debieraElminarProveedor() {
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
        def proveedor = new Proveedor (
            nombre : 'TEST-1'
            , nombreCompleto : 'TEST-1'
            , rfc : 'TST000000001'
            , empresa : empresa
        ).save()

        def controller = new ProveedorController()
        controller.springSecurityService = springSecurityService
        controller.params.id = proveedor.id
        def model = controller.ver()
        assert model.proveedor
        assertEquals 'TEST-1', model.proveedor.nombre

        controller.params.id = proveedor.id
        controller.elimina()
        assertEquals "/proveedor/lista", controller.response.redirectedUrl

        model = Proveedor.get(proveedor.id)
        assert !model
    }
}
