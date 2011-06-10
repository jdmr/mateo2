package contabilidad

import general.Empresa

class Cuenta {
    String codigo
    String numero
    String descripcion
    Boolean tieneMovimientos = false
    Boolean tieneAuxiliares = false
    Cuenta padre
    Empresa empresa
    Set hijos
    Set auxiliares

    static belongsTo = [padre: Cuenta, empresa: Empresa]

    static hasMany = [hijos: Cuenta, auxiliares: Auxiliar]

    static constraints = {
        codigo maxSize:32, nullable:true 
        numero blank:false, maxSize:32, unique:'empresa'
        descripcion blank:false, maxSize:128, unique:'empresa'
        padre nullable:true
    }

    static mapping = {
        table 'cuentas'
        codigo index:'cuenta_codigo_idx'
    }

    static namedQueries = {
        buscaRaices { empresaId ->
            isNull('padre')
            empresa {
                idEq(empresaId)
            }
        }
        
        buscaPorPadre { padreId, empresaId ->
            padre {
                idEq(padreId)
            }
            empresa {
                idEq(empresaId)
            }
        }
    }

    String toString() {
        return "$numero | $descripcion"
    }
}
