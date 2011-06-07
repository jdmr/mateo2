class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        log.info("Inicializando aplicacion")

        def organizacion
        if (general.Organizacion.count() == 0) {
            organizacion = new general.Organizacion (
                codigo : 'UM'
                , nombre : 'UM'
                , nombreCompleto : 'Universidad de Montemorelos A.C.'
            ).save()
        }

        def empresa
        if (general.Empresa.count() == 0) {
            if (!organizacion) {
                def p = [:]
                p.max = 1
                def organizaciones = general.Organizacion.list(p)
                organizacion = organizaciones[0]
            }
            empresa = new general.Empresa (
                codigo : 'CTL'
                , nombre : 'CENTRAL'
                , nombreCompleto : 'CENTRAL'
                , organizacion : organizacion
            ).save()
        }

        log.info "Validando roles"
        def rolAdmin = general.Rol.findByAuthority('ROLE_ADMIN')
        if (general.Rol.count() != 4) {
            if (!rolAdmin) {
                rolAdmin = new general.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }
            def rolOrg = general.Rol.findByAuthority('ROLE_ORG')
            if (!rolOrg) {
                rolOrg = new general.Rol(authority: 'ROLE_ORG').save(flush:true)
            }
            def rolEmp = general.Rol.findByAuthority('ROLE_EMP')
            if (!rolEmp) {
                rolEmp = new general.Rol(authority: 'ROLE_EMP').save(flush:true)
            }
            def rolUser = general.Rol.findByAuthority('ROLE_USER')
            if (!rolUser) {
                rolUser = new general.Rol(authority: 'ROLE_USER').save(flush:true)
            }
        }

        log.info "Validando usuarios"
        def admin = general.UsuarioRol.findByRol(rolAdmin)
        if (!admin) {
            if (!empresa) {
                def p = [:]
                p.max = 1
                def empresas = general.Empresa.list(p)
                empresa = empresas[0]
            }
            def password = springSecurityService.encodePassword('admin')
            admin = new general.Usuario(
                username:'admin'
                ,password:password
                ,nombre:'David'
                ,apellido:'Mendoza'
                ,correo:'david.mendoza@um.edu.mx'
                ,empresa:empresa
            )
            admin.save(flush:true)
            general.UsuarioRol.create(admin, rolAdmin, true)
        }

        log.info("Aplicacion inicializada")
    }

    def destroy = {
    }
}
