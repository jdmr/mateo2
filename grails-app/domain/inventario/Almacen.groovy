package inventario

import general.Empresa
import general.Usuario

class Almacen {//implements java.io.Serializable {
    String codigo
    String nombre
    Empresa empresa
    String nombreCompleto
    Set productos
    Set tiposProducto
    Set entradas
    Set salidas
    Set facturas
    Set folios

    static transients = ['nombreCompleto']

    static belongsTo = [Empresa]

    static hasMany = [usuarios: Usuario, productos: Producto, tiposProducto: TipoProducto, entradas: Entrada, salidas: Salida, facturas: FacturaAlmacen, folios: FolioInventario]

    static constraints = {
        codigo blank:false,size:1..18,unique:'empresa'
        nombre blank:false,size:1..64,unique:'empresa'
    }

    static mapping = {
        table 'almacenes'
    }

    static namedQueries = {

        buscaPorOrganizacion { organizacionId ->
            empresa {
                organizacion {
                    idEq(organizacionId)
                }
            }
        }

        buscaPorEmpresa { empresaId ->
            empresa {
                idEq(empresaId)
            }
        }

    }

    String getNombreCompleto() {
        return "$empresa.organizacion.nombre | $empresa.nombre | $nombre"
    }

    String toString() {
        return nombre
    }
}