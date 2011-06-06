package general

import grails.test.*
import grails.test.mixin.*
import org.junit.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.GrantedAuthorityImpl
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUser
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.authentication.TestingAuthenticationToken

@TestFor(EmpresaController)
class EmpresaControllerIntegrationTests extends GroovyTestCase {

    def springSecurityService

    @Test
    void debieraMostrarListaDeEmpresas() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def currentUser = springSecurityService.currentUser
        def flag = true
        for(i in 1..20) {
            def empresa = new Empresa (
                nombre: "TEST$i"
                , nombreCompleto: "TEST$i"
                , organizacion: organizacion
            ).save()
            if (flag) { 
                currentUser.empresa = empresa
                flag = false
            }
        }

        def controller = new EmpresaController()
        controller.springSecurityService = springSecurityService
        controller.index()
        assertEquals '/empresa/lista', controller.response.redirectedUrl

        def model = controller.lista()
        assertEquals 10, model.empresas.size()
        assert 20 <= model.totalDeEmpresas
    }

    @Test
    void debieraCrearEmpresa() {
        authenticateAdmin()

        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()

        def controller = new EmpresaController()
        controller.springSecurityService = springSecurityService
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
        authenticateAdmin()

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
        controller.springSecurityService = springSecurityService
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
        authenticateAdmin()

        def organizacion = new Organizacion (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
        ).save()
        def empresa = new Empresa (
            nombre: 'TEST-1'
            , nombreCompleto: 'TEST-1'
            , organizacion: organizacion
        ).save()
        new Empresa (
            nombre: 'TEST-2'
            , nombreCompleto: 'TEST-2'
            , organizacion: organizacion
        ).save()
        def currentUser = springSecurityService.currentUser
        currentUser.empresa = empresa

        def controller = new EmpresaController()
        controller.springSecurityService = springSecurityService
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

    def authenticateAdmin() {
        def credentials = 'test'
        def user = new Usuario(
                username:'admin'
                ,password:credentials
            )
        def authorities = [new GrantedAuthorityImpl('ROLE_ADMIN')]
        def principal = new GrailsUser(user.username,credentials,true,true,true,true,authorities,1)
        authenticate(principal,credentials,authorities)
    }

    def authenticate(principal, credentials, authorities) {
        def authentication = new TestingAuthenticationToken(principal, credentials, authorities as GrantedAuthority[])
        authentication.authenticated = true
        SCH.context.authentication = authentication
        return authentication
    }

    def logout() {
        SCH.context.authentication = null
    }

}
