package contabilidad

import general.Usuario
import java.text.*

class FolioService {
    def springSecurityService
    static NumberFormat nf

    def temporal() {
        def usuario = springSecurityService.currentUser
        def codigos = Usuario.executeQuery("select new map(usuario.empresa.organizacion.codigo as organizacion, usuario.empresa.codigo as empresa) from Usuario usuario where usuario = ?", [usuario])
        def folio = Folio.findByNombreAndEmpresa('TEMPORAL', usuario.empresa)
        if (!folio) {
            folio = new Folio(
                nombre : 'TEMPORAL'
                , empresa : usuario.empresa
            )
        }
        folio.valor++
        folio.save()
        if (!nf) {
            inicializaNumberFormat()
        }
        return "T-${codigos[0].organizacion}${codigos[0].empresa}${nf.format(folio.valor)}"
    }

    def poliza() {
        def usuario = springSecurityService.currentUser
        def codigos = Usuario.executeQuery("select new map(usuario.empresa.organizacion.codigo as organizacion, usuario.empresa.codigo as empresa) from Usuario usuario where usuario = ?", [usuario])
        def folio = Folio.findByNombreAndEmpresa('POLIZA', usuario.empresa)
        if (!folio) {
            folio = new Folio(
                nombre : 'POLIZA'
                , empresa : usuario.empresa
            )
        }
        folio.valor++
        folio.save()
        if (!nf) {
            inicializaNumberFormat()
        }
        return "P-${codigos[0].organizacion}${codigos[0].empresa}${nf.format(folio.valor)}"
    }

    def inicializaNumberFormat() {
        nf = DecimalFormat.getInstance()
        nf.setGroupingUsed(false)
        nf.setMinimumIntegerDigits(9)
        nf.setMaximumIntegerDigits(9)
        nf.setMaximumFractionDigits(0)
    }
}
