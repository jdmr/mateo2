package general

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(OrganizacionController)
class OrganizacionControllerIntegrationTests extends BaseIntegrationTest {

    @Test
    void debieraMostrarListaDeOrganizaciones() {
        authenticateAdmin()
        for(i in 1..20) {
            new Organizacion (
                codigo: "TST$i"
                , nombre: "TEST-$i"
                , nombreCompleto : "TEST$i"
            ).save()
        }

        def controller = new OrganizacionController()
        controller.index()
        assert '/organizacion/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.organizaciones.size()
        assert 20 <= model.totalDeOrganizaciones
    }

    @Test
    void debieraCrearOrganizacion() {
        authenticateAdmin()
        def controller = new OrganizacionController()
        def model = controller.nueva()
        assert model
        assert model.organizacion

        controller.params.codigo = 'TST1'
        controller.params.nombre = 'TEST-1'
        controller.params.nombreCompleto = 'TEST-1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/empresa/edita')
    }

    @Test
    void debieraActualizarOrganizacion() {
        authenticateAdmin()
        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
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

        controller.params.id = organizacion.id
        controller.params.version = organizacion.version
        controller.params.nombre = 'TEST-2'
        controller.actualiza()
        assertEquals "/organizacion/ver/${organizacion.id}", controller.response.redirectedUrl

        organizacion.refresh()
        assertEquals 'TEST-2', organizacion.nombre
    }

    @Test
    void debieraEliminarOrganizacion() {
        authenticateAdmin()
        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
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
