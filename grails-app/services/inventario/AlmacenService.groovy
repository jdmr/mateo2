package inventario

import java.util.List
//import auditoria.inventario.XAlmacen
import general.*
import grails.util.GrailsUtil
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

class AlmacenService {

    static transactional = true
    def springSecurityService
    def usuarioService
    def reporteService

    List<Almacen> lista(params) {
        log.debug "Lista de almacenes"
        def usuario = Usuario.obtieneConRelaciones().get(springSecurityService.getPrincipal().id)
        return Almacen.findAllByEmpresa(usuario.almacen.empresa, params)
    }

    List<Almacen> listaPorPerfil() {
        def almacenes = []
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')) {
            almacenes = Almacen.findAll("from Almacen a order by a.empresa.organizacion.nombre, a.empresa.nombre, a.nombre")
        } else if (SpringSecurityUtils.ifAnyGranted('ROLE_ORG')) {
            def usuario = Usuario.obtieneConRelaciones().get(springSecurityService.getPrincipal().id)
            almacenes = Almacen.findAll('from Almacen a where a.empresa.organizacion = :organizacion order by a.empresa.nombre, a.nombre',[organizacion:usuario.almacen.empresa.organizacion])
        } else {
            def usuario = Usuario.obtieneConRelaciones().get(springSecurityService.getPrincipal().id)
            almacenes = Almacen.findAll('from Almacen a where a.empresa = :empresa order by a.nombre',[empresa:usuario.almacen.empresa])
        }
        return almacenes
    }

    Map listaConCantidad(params) {
        def usuario = Usuario.obtieneConRelaciones().get(springSecurityService.getPrincipal().id)
        def almacenes = Almacen.findAllByEmpresa(usuario.almacen.empresa, params)
        def cantidad = Almacen.countByEmpresa(usuario.almacen.empresa)
        return [lista:almacenes, cantidad:cantidad]
    }

    Almacen obtiene(id) {
		def almacen = Almacen.get(id)
		if (!almacen) {
			throw new RuntimeException("No se encontro el almacen $id")
		}
        return almacen
    }

    Almacen crea(almacen) {
        def usuario = Usuario.get(springSecurityService.getPrincipal().id)

        almacen.empresa = usuario.almacen.empresa
        almacen.save()

        usuario.almacen = almacen
        usuarioService.actualiza(usuario, usuario.getAuthorities())

        reporteService.compila(almacen)

        audita(almacen,Constantes.CREAR)
        return almacen
    }

    Almacen creaConEmpresa(almacen) {
        almacen.save()

        def usuario = Usuario.get(springSecurityService.getPrincipal().id)
        if (usuario) {
            usuario.almacen = almacen
            usuarioService.actualiza(usuario, usuario.getAuthorities())
        }

        if ( GrailsUtil.getEnvironment().equals(GrailsApplication.ENV_PRODUCTION)) {
            reporteService.compila(almacen)
        }

        audita(almacen,Constantes.CREAR)
        return almacen
    }

    Almacen actualiza(almacen) {
        log.debug "Actualizando el almacen $almacen"
        almacen.save()
        audita(almacen,Constantes.ACTUALIZAR)
        return almacen
    }

    String elimina(id) {
        def almacen = Almacen.get(id)
        String nombre = almacen.nombre
        almacen.delete()
        audita(almacen,Constantes.ELIMINAR)
        return nombre
    }

//    void audita(almacen, actividad) {
//        log.debug "[AUDITA] $actividad almacen $almacen"
//        def creador = springSecurityService.authentication.name
//        def xalmacen = new XAlmacen(almacen.properties)
//        xalmacen.almacenId = almacen.id
//        xalmacen.empresaId = almacen.empresa.id
//        xalmacen.creador = creador
//        xalmacen.actividad = actividad
//        xalmacen.save()
//    }
}