package general

/**
 *
 * @author jdmr
 */
class Cliente implements java.io.Serializable {
    String nombre
    String nombreCompleto
    String rfc
    String curp
    String direccion
    String telefono
    String fax
    String contacto
    String correo
    Boolean base = false
    Empresa empresa
    TipoCliente tipoCliente

    static belongsTo = [Empresa, TipoCliente]

    static constraints = {
        nombre unique:'empresa',blank:false,maxSize:64
        nombreCompleto blank:false, maxSize:128
        rfc size:12..13,blank:false
        curp nullable:true
        direccion nullable:true,maxSize:500
        telefono nullable:true,maxSize:25
        fax nullable:true,maxSize:25
        contacto nullable:true,maxSize:64
        correo nullable:true,email:true,maxSize:128
    }

    static mapping = {
        table 'clientes'
        nombre index:'cliente_nombre_idx'
        nombreCompleto index:'cliente_nombre_completo_idx'
        rfc index:'cliente_rfc_idx'
        empresa index:'cliente_empresa_idx'
    }

    static namedQueries = {
        buscaPorEmpresa { filtro ->
            empresa {
                eq 'id', filtro.id
            }
        }

        buscaPorFiltro { filtro ->
            filtro = "%$filtro%"
            or {
                ilike 'nombre',filtro
                ilike 'nombreCompleto',filtro
                ilike 'rfc',filtro
            }
        }

        relaciones {
            join 'tipoCliente'
        }
    }

    String toString() {
        return nombre
    }

}