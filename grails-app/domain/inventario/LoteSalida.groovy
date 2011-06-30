package inventario

class LoteSalida {//implements java.io.Serializable {
    BigDecimal cantidad = new BigDecimal("0")
    BigDecimal precioUnitario = new BigDecimal("0.00")
    BigDecimal iva = new BigDecimal("0.00")
    Producto producto
    Salida salida
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Producto, Salida]

    static constraints = {
        cantidad(scale:3,precision:8)
        precioUnitario(scale:2,precision:8)
        iva(scale:2,precision:8)
    }

    static mapping = {
        table 'lotes_salida'
    }

    static namedQueries = {
        listaPorSalida { filtro ->
            salida {
                eq 'id', filtro.id
            }
            producto {
                order 'id','asc'
            }
            order 'precioUnitario', 'asc'
        }
    }

    String toString() {
        "$producto : $cantidad : $precioUnitario"
    }

}