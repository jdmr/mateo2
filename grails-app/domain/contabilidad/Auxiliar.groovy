package contabilidad

import general.Organizacion

class Auxiliar {
    String numero
    String descripcion
    Organizacion organizacion
    Set cuentas

    static belongsTo = [Cuenta, Organizacion]

    static hasMany = [cuentas: Cuenta]

    static constraints = {
        numero blank:false, maxSize:32, unique:'organizacion'
        descripcion blank:false, maxSize:128, unique:'organizacion'
    }

    static mapping = {
        table 'auxiliares'
    }

    static namedQueries = {
        buscaPorFiltro { filtro, organizacionId ->
            filtro = "%$filtro%"
            or {
                ilike('numero',filtro)
                ilike('descripcion',filtro)
            }
            organizacion {
                idEq(organizacionId)
            }
        }
    }

    String toString() {
        return "$numero | $descripcion"
    }
}
