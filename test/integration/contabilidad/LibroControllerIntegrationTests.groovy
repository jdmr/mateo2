package contabilidad

import general.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(LibroController)
class LibroControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraMostrarListaDeLibro() {
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
            new Libro (
                nombre: "TEST-$i"
                , empresa : empresa
            ).save()
        }
        
        def controller = new LibroController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/libro/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.libros.size()
        assert 20 <= model.totalDeLibros
    }

    @Test
    void debieraCrearLibro() {
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

        def controller = new LibroController()
        controller.springSecurityService = springSecurityService
        def model = controller.nuevo()
        assert model.libro

        controller.params.nombre = 'TEST-1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/libro/ver')
    }

    @Test
    void debieraActualizarLibro() {
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

        def libro = new Libro (
            nombre: "TEST-1"
            , empresa : empresa
        ).save()
        
        def controller = new LibroController()
        controller.springSecurityService = springSecurityService
        controller.params.id = libro.id
        def model = controller.ver()
        assert model.libro
        assertEquals 'TEST-1', model.libro.nombre

        controller.params.id = libro.id
        model = controller.edita()
        assert model.libro
        assertEquals 'TEST-1', model.libro.nombre

        controller.params.id = libro.id
        controller.params.version = libro.version
        controller.params.nombre = 'TEST-2'
        controller.actualiza()
        assertEquals "/libro/ver/${libro.id}", controller.response.redirectedUrl

        libro.refresh()
        assertEquals 'TEST-2', libro.nombre
    }

    @Test
    void debieraEliminarLibro() {
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

        def libro = new Libro (
            nombre: "TEST-1"
            , empresa : empresa
        ).save()
        
        def controller = new LibroController()
        controller.springSecurityService = springSecurityService
        controller.params.id = libro.id
        def model = controller.ver()
        assert model.libro
        assertEquals 'TEST-1', model.libro.nombre

        controller.params.id = libro.id
        controller.elimina()
        assertEquals "/libro/lista", controller.response.redirectedUrl

        model = Libro.get(libro.id)
        assert !model
    }
}
