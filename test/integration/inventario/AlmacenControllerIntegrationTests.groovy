package inventario

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(AlmacenController)
class AlmacenControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeAlmacenes() {
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
            new Almacen (
                codigo: "TEST-$i"
                , nombre: "TEST-$i"
                , empresa : empresa
            ).save()
        }

        def controller = new AlmacenController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/almacen/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.almacenes.size()
        assert 20 <= model.totalDeAlmacenes
    }

    @Test
    void debieraCrearAlmacen() {
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

        def controller = new AlmacenController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model.almacen

        controller.params.nombre = 'TEST-1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/almacen/ver')
    }

//    @Test
//    void debieraActualizarEjercicio() {
//        authenticateAdmin()
//
//        def organizacion = new Organizacion (
//            codigo: 'TST1'
//            , nombre: 'TEST-1'
//            , nombreCompleto: 'TEST-1'
//        ).save()
//        def empresa = new Empresa (
//            codigo: 'TST1'
//            , nombre: 'TEST-1'
//            , nombreCompleto: 'TEST-1'
//            , organizacion: organizacion
//        ).save()
//
//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa
//
//        def ejercicio = new Ejercicio (
//            nombre: "TEST-1"
//            , empresa : empresa
//        ).save()
//
//        def controller = new EjercicioController()
//        controller.springSecurityService = springSecurityService
//        controller.params.id = ejercicio.id
//        def model = controller.ver()
//        assert model.ejercicio
//        assertEquals 'TEST-1', model.ejercicio.nombre
//
//        controller.params.id = ejercicio.id
//        model = controller.edita()
//        assert model.ejercicio
//        assertEquals 'TEST-1', model.ejercicio.nombre
//
//        controller.params.id = ejercicio.id
//        controller.params.version = ejercicio.version
//        controller.params.nombre = 'TEST-2'
//        controller.actualiza()
//        assertEquals "/ejercicio/ver/${ejercicio.id}", controller.response.redirectedUrl
//
//        ejercicio.refresh()
//        assertEquals 'TEST-2', ejercicio.nombre
//    }
//
//    @Test
//    void debieraEliminarEjercicio() {
//        authenticateAdmin()
//
//        def organizacion = new Organizacion (
//            codigo: 'TST1'
//            , nombre: 'TEST-1'
//            , nombreCompleto: 'TEST-1'
//        ).save()
//        def empresa = new Empresa (
//            codigo: 'TST1'
//            , nombre: 'TEST-1'
//            , nombreCompleto: 'TEST-1'
//            , organizacion: organizacion
//        ).save()
//
//        def currentUser = springSecurityService.currentUser
//        currentUser.empresa = empresa
//
//        def ejercicio = new Ejercicio (
//            nombre: "TEST-1"
//            , empresa : empresa
//        ).save()
//
//        def controller = new EjercicioController()
//        controller.springSecurityService = springSecurityService
//        controller.params.id = ejercicio.id
//        def model = controller.ver()
//        assert model.ejercicio
//        assertEquals 'TEST-1', model.ejercicio.nombre
//
//        controller.params.id = ejercicio.id
//        controller.elimina()
//        assertEquals "/ejercicio/lista", controller.response.redirectedUrl
//
//        model = Ejercicio.get(ejercicio.id)
//        assert !model
//    }
}
