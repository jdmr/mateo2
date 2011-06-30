package general

class Proveedor implements java.io.Serializable {
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

    static belongsTo = [Empresa]

    static constraints = {
        nombre unique:'empresa', blank : false, maxSize:64
        nombreCompleto blank:false, maxSize:128
        rfc blank:false, size:12..13
        curp nullable:true,size:18..18
        direccion nullable:true,maxSize:500
        telefono nullable:true,maxSize:25
        fax nullable:true,maxSize:25
        contacto nullable:true,maxSize:64
        correo nullable:true,email:true,maxSize:128
    }

    static mapping = {
        table 'proveedores'
        nombre index:'proveedor_nombre_idx'
        nombreCompleto index:'proveedor_nombre_completo_idx'
        rfc index:'proveedor_rfc_idx'
        empresa index:'proveedor_empresa_idx'
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
                ilike 'nombre', filtro
                ilike 'nombreCompleto', filtro
                ilike 'rfc', filtro
            }
        }
    }

    String toString() {
        return nombre
    }
}