package inventario

import general.Cliente

class FacturaAlmacen {//implements java.io.Serializable {
    String folio
//    Estatus estatus
    String estatus
    String comentarios
    Date fecha
    BigDecimal iva = new BigDecimal('0')
    BigDecimal total = new BigDecimal('0')
    Cliente cliente
    Almacen almacen
    Date dateCreated
    Date lastUpdated
    Set salidas
    Set entradas

    static belongsTo = [Cliente, Almacen]

    static hasMany = [salidas: Salida, entradas: Entrada]

    static constraints = {
        folio unique:'almacen', maxSize:64
        comentarios nullable:true, maxSize:128
        iva scale:2, precision:8
        total scale:2, precision:8
        estatus(maxSize:64, inList:['ABIERTA','CERRADA','CANCELADA','FACTURADA'])
    }

    static mapping = {
        table 'facturas_almacen'
    }

    static namedQueries = {
        buscaPorAlmacen { filtro ->
            almacen {
                idEq(filtro.id)
            }
        }

        buscaPorFiltro { filtro ->
            filtro = "%$filtro%"
            or {
                ilike 'folio', filtro
                cliente {
                    ilike 'nombre', filtro
                }
            }
        }

        buscaPorFecha { fechaInicial, fechaFinal ->
            between 'fecha', fechaInicial, fechaFinal
        }

//        relaciones {
//            estatus {
//                order 'prioridad', 'asc'
//            }
//            order 'folio', 'desc'
//        }
    }

    String toString() {
        "$folio : $cliente : $almacen : $total"
    }
}