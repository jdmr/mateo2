package general

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(OrganizacionController)
class OrganizacionControllerIntegrationTests extends GroovyTestCase {

    @Test
    void debieraMostrarListaDeUsuarios() {
        for(i in 1..20) {
            new Organizacion (
                nombre:"TEST$i"
                , nombreCompleto : "TEST$i"
            ).save()
        }

        def controller = new OrganizacionController()
        controller.index()
        assertEquals '/organizacion/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.organizaciones.size()
        assert 20 <= model.totalDeOrganizaciones
    }

    @Test
    void debieraCrearOrganizacion() {
        def controller = new OrganizacionController()
        def model = controller.nueva()
        assert model
        assert model.organizacion

        controller.params.nombre = 'TEST-1'
        controller.params.nombreCompleto = 'TEST-1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/organizacion/ver')
    }

    @Test
    void debieraActualizarOrganizacion() {
        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

        def controller = new OrganizacionController()
        controller.params.id = organizacion.id
        def model = controller.ver()
        assert model.organizacion
        assertEquals 'TEST-1', model.organizacion.nombre

        controller.params.id = organizacion.id
        model = controller.edita()
        assert model.organizacion
        assertEquals 'TEST-1', model.organizacion.nombre

        controller.params.nombre = 'TEST-2'
        controller.actualiza()
        assertEquals "/organizacion/ver/${organizacion.id}", controller.response.redirectedUrl

        organizacion.refresh()
        assertEquals 'TEST-2', organizacion.nombre
    }

    @Test
    void debieraEliminarOrganizacion() {
        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

        def controller = new OrganizacionController()
        controller.params.id = organizacion.id
        def model = controller.ver()
        assert model.organizacion
        assertEquals 'TEST-1', model.organizacion.nombre

        controller.params.id = organizacion.id
        controller.elimina()
        assertEquals "/organizacion/lista", controller.response.redirectedUrl

        model = Organizacion.get(organizacion.id)
        assert !model
    }
}
