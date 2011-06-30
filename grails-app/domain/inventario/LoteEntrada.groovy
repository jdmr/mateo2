package inventario

class LoteEntrada {//implements java.io.Serializable {
    BigDecimal cantidad = new BigDecimal("0.00")
    BigDecimal precioUnitario = new BigDecimal("0.00")
    BigDecimal iva = new BigDecimal("0.00")
    Producto producto
    Entrada entrada
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Producto, Entrada]

    static constraints = {
        cantidad(scale:3,precision:8)
        precioUnitario(scale:2,precision:8,min:new BigDecimal("0"))
        iva(scale:2,precision:8)
    }

    static mapping = {
        table 'lotes_entrada'
    }

    static namedQueries = {
        listaPorEntrada { filtro ->
            entrada {
                eq 'id', filtro.id
            }
            producto {
                order 'id', 'asc'
            }
            order 'precioUnitario', 'asc'
        }
    }

    String toString() {
        "$producto : $cantidad : $precioUnitario"
    }
}