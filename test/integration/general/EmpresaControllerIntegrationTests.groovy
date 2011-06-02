package general

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(EmpresaController)
class EmpresaControllerIntegrationTests extends GroovyTestCase {

    @Test
    void debieraMostrarListaDeEmpresas() {
        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        for(i in 1..20) {
            new Empresa (
                nombre: "TEST$i"
                , nombreCompleto: "TEST$i"
                , organizacion: organizacion
            ).save()
        }

        def controller = new EmpresaController()
        controller.index()
        assertEquals '/empresa/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.empresas.size()
        assert 20 <= model.totalDeEmpresas
    }

    @Test
    void debieraCrearEmpresa() {
        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

        def controller = new EmpresaController()
        def model = controller.nueva()
        assert model.empresa

        controller.params.nombre = "TEST-1"
        controller.params.nombreCompleto = "TEST-1"
        controller.params.organizacion = organizacion
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/empresa/ver')
    }

    @Test
    void debieraActualizarEmpresa() {
        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def empresa = new Empresa (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
            , organizacion: organizacion
        ).save()

        def controller = new EmpresaController()
        controller.params.id = empresa.id
        def model = controller.ver()
        assert model.empresa
        assertEquals 'TEST-1', model.empresa.nombre

        controller.params.id = empresa.id
        model = controller.edita()
        assert model.empresa
        assertEquals 'TEST-1', model.empresa.nombre

        controller.params.nombre = 'TEST-2'
        controller.actualiza()
        assertEquals "/empresa/ver/${empresa.id}", controller.response.redirectedUrl

        empresa.refresh()
        assertEquals 'TEST-2', empresa.nombre
    }

    @Test
    void debieraEliminarEmpresa() {
        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def empresa = new Empresa (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
            , organizacion: organizacion
        ).save()

        def controller = new EmpresaController()
        controller.params.id = empresa.id
        def model = controller.ver()
        assert model.empresa
        assertEquals 'TEST-1', model.empresa.nombre

        controller.params.id = empresa.id
        controller.elimina()
        assertEquals "/empresa/lista", controller.response.redirectedUrl

        model = Empresa.get(empresa.id)
        assert !model
    }
}
