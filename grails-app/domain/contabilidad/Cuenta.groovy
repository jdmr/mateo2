package contabilidad

import general.Organizacion

class Cuenta {
    String codigo
    String numero
    String descripcion
    Boolean tieneMovimientos = false
    Boolean tieneAuxiliares = false
    Cuenta padre
    Organizacion organizacion
    Set hijos
    Set auxiliares

    static belongsTo = [padre: Cuenta, organizacion: Organizacion]

    static hasMany = [hijos: Cuenta, auxiliares: Auxiliar]

    static constraints = {
        codigo maxSize:32, nullable:true 
        numero blank:false, maxSize:32, unique:'organizacion'
        descripcion blank:false, maxSize:128, unique:'organizacion'
        padre nullable:true
    }

    static mapping = {
        table 'cuentas'
        codigo index:'cuenta_codigo_idx'
    }

    static namedQueries = {
        buscaRaices { organizacionId ->
            isNull('padre')
            organizacion {
                idEq(organizacionId)
            }
        }
        
        buscaPorPadre { padreId, organizacionId ->
            padre {
                idEq(padreId)
            }
            organizacionId {
                idEq(organizacionId)
            }
        }

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

    boolean equals(o) {
        boolean result = false
        if (o != null && o instanceof Cuenta) {
            Cuenta cuenta = (Cuenta) o
            if (cuenta.id == this.id) {
                result = true
            }
        }
        return result
    }
}
