package general

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(UsuarioController)
class UsuarioControllerIntegrationTests extends GroovyTestCase {

    @Test
    void debieraHacerRedirectALista() {
        def controller = new UsuarioController()
        controller.index()
        assertEquals '/usuario/lista', controller.response.redirectedUrl
    }

    @Test
    void debieraMostrarListaDeUsuarios() {
        for (i in 1..20) {
            new Usuario(
                username:"TEST$i"
                ,password:"TEST$i"
                ,nombre:"TEST$i"
                ,apellido:"TEST$i"
                ,correo:"TEST$i"
            ).save()
        }

        def controller = new UsuarioController()
        def model = controller.lista()
        assertEquals 10, model.usuarios.size()
    }

    @Test
    void debieraCrearUsuario() {
        def controller = new UsuarioController()
        def model = controller.nuevo()
        assert model
        assert model.usuario

        controller.params.username = 'TEST1'
        controller.params.password = 'TEST1'
        controller.params.nombre = 'TEST1'
        controller.params.apellido = 'TEST1'
        controller.params.correo = 'TEST1'
        controller.crea()
        assert controller.response.redirectedUrl.startsWith('/usuario/ver')
    }

    @Test
    void debieraActualizarUsuario() {
        def usuario = new Usuario(
            username:"TEST-1"
            ,password:"TEST-1"
            ,nombre:"TEST-1"
            ,apellido:"TEST-1"
            ,correo:"TEST-1"
        ).save()

        def controller = new UsuarioController()
        controller.params.id = usuario.id
        def model = controller.edita()
        assert model.usuario
        assertEquals 'TEST-1', model.usuario.nombre

        controller.params.nombre = 'TEST-2'
        controller.actualiza()
        assertEquals "/usuario/ver/${usuario.id}", controller.response.redirectedUrl

        usuario.refresh()
        assertEquals 'TEST-2', usuario.nombre
    }

    @Test
    void debieraEliminarUsuario() {
        def usuario = new Usuario(
            username:"TEST-1"
            ,password:"TEST-1"
            ,nombre:"TEST-1"
            ,apellido:"TEST-1"
            ,correo:"TEST-1"
        ).save()

        def controller = new UsuarioController()
        controller.params.id = usuario.id
        controller.elimina()
        assertEquals "/usuario/lista", controller.response.redirectedUrl
        
        def model = Usuario.get(usuario.id)
        assert !model
    }
}
