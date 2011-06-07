package contabilidad

import general.*

import grails.test.mixin.*
import grails.test.mixin.support.*
import org.junit.*

@TestFor(PolizaController)
class PolizaControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService
    def folioService

    @Test
    void debieraMostrarListaDePolizas() {
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

        def ejercicio = new Ejercicio (
            nombre : 'EJERCICIO1'
            , empresa : empresa
        ).save()
        def libro = new Libro (
            nombre : 'LIBRO1'
            , empresa : empresa
        ).save()

        for(i in 1..20) {
            new Poliza (
                folio: "TEST-$i"
                , descripcion: "TEST-$i"
                , ejercicio : ejercicio
                , libro : libro
                , empresa : empresa
            ).save()
        }
        
        def controller = new PolizaController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/poliza/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.polizas.size()
        assert 20 <= model.totalDePolizas
    }

    @Test
    void debieraCrearPoliza() {
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

        def ejercicio = new Ejercicio (
            nombre : 'EJERCICIO1'
            , empresa : empresa
        ).save()
        def libro = new Libro (
            nombre : 'LIBRO1'
            , empresa : empresa
        ).save()

        def controller = new PolizaController()
        controller.springSecurityService = springSecurityService
        controller.folioService = folioService
        def model = controller.nueva()
        assert model.poliza

        controller.params.descripcion = 'TEST-1'
        controller.params.ejercicio = ejercicio
        controller.params.libro = libro
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/poliza/ver')
    }

    @Test
    void debieraActualizarPoliza() {
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

        def ejercicio = new Ejercicio (
            nombre : 'EJERCICIO1'
            , empresa : empresa
        ).save()
        def libro = new Libro (
            nombre : 'LIBRO1'
            , empresa : empresa
        ).save()

        def poliza = new Poliza (
            folio: "TEST-1"
            , descripcion : 'TEST-1'
            , ejercicio : ejercicio
            , libro : libro
            , empresa : empresa
        ).save()
        
        def controller = new PolizaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = poliza.id
        def model = controller.ver()
        assert model.poliza
        assertEquals 'TEST-1', model.poliza.descripcion

        controller.params.id = poliza.id
        model = controller.edita()
        assert model.poliza
        assertEquals 'TEST-1', model.poliza.descripcion

        controller.params.id = poliza.id
        controller.params.version = poliza.version
        controller.params.descripcion = 'TEST-2'
        controller.actualiza()
        assertEquals "/poliza/ver/${poliza.id}", controller.response.redirectedUrl

        poliza.refresh()
        assertEquals 'TEST-2', poliza.descripcion
    }

    @Test
    void debieraEliminarPoliza() {
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

        def ejercicio = new Ejercicio (
            nombre : 'EJERCICIO1'
            , empresa : empresa
        ).save()
        def libro = new Libro (
            nombre : 'LIBRO1'
            , empresa : empresa
        ).save()

        def poliza = new Poliza (
            folio: "TEST-1"
            , descripcion : 'TEST-1'
            , ejercicio : ejercicio
            , libro : libro
            , empresa : empresa
        ).save()
        
        def controller = new PolizaController()
        controller.springSecurityService = springSecurityService
        controller.params.id = poliza.id
        def model = controller.ver()
        assert model.poliza
        assertEquals 'TEST-1', model.poliza.descripcion

        controller.params.id = poliza.id
        controller.elimina()
        assertEquals "/poliza/lista", controller.response.redirectedUrl

        model = Poliza.get(poliza.id)
        assert !model
    }
}
