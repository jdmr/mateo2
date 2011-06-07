package general

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(UsuarioController)
class UsuarioControllerIntegrationTests extends BaseIntegrationTest {

    def springSecurityService

    @Test
    void debieraHacerRedirectALista() {
        authenticateAdmin()
        def controller = new UsuarioController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/usuario/lista', controller.response.redirectedUrl
    }

    @Test
    void debieraMostrarListaDeUsuarios() {
        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto : 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto : 'TEST-1'
            , organizacion : organizacion
        ).save()
        springSecurityService.currentUser.empresa = empresa
        for (i in 1..20) {
            new Usuario(
                username:"TEST$i"
                ,password:"TEST$i"
                ,nombre:"TEST$i"
                ,apellido:"TEST$i"
                ,correo:"TEST$i"
                ,empresa: empresa
            ).save()
        }

        def controller = new UsuarioController()
        controller.springSecurityService = springSecurityService
        def model = controller.lista()
        assertEquals 10, model.usuarios.size()
        assert 20 <= model.totalDeUsuarios
    }

    @Test
    void debieraCrearUsuario() {
        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto : 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto : 'TEST-1'
            , organizacion : organizacion
        ).save()
        springSecurityService.currentUser.empresa = empresa
        def controller = new UsuarioController()
        controller.springSecurityService = springSecurityService
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
        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto : 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto : 'TEST-1'
            , organizacion : organizacion
        ).save()
        springSecurityService.currentUser.empresa = empresa
        def usuario = new Usuario(
            username:"TEST-1"
            ,password:"TEST-1"
            ,nombre:"TEST-1"
            ,apellido:"TEST-1"
            ,correo:"TEST-1"
            ,empresa: empresa
        ).save()

        def controller = new UsuarioController()
        controller.springSecurityService = springSecurityService
        controller.params.id = usuario.id
        def model = controller.ver()
        assert model.usuario
        assertEquals 'TEST-1', model.usuario.nombre

        controller.params.id = usuario.id
        model = controller.edita()
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
        def organizacion = new Organizacion (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto : 'TEST-1'
        ).save()
        def empresa = new Empresa (
            codigo: 'TST1'
            , nombre: 'TEST-1'
            , nombreCompleto : 'TEST-1'
            , organizacion : organizacion
        ).save()
        springSecurityService.currentUser.empresa = empresa
        def usuario = new Usuario(
            username:"TEST-1"
            ,password:"TEST-1"
            ,nombre:"TEST-1"
            ,apellido:"TEST-1"
            ,correo:"TEST-1"
            ,empresa: empresa
        ).save()

        def controller = new UsuarioController()
        controller.params.id = usuario.id
        controller.elimina()
        assertEquals "/usuario/lista", controller.response.redirectedUrl
        
        def model = Usuario.get(usuario.id)
        assert !model
    }
}
