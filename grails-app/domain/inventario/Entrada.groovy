package inventario

import general.Proveedor

class Entrada {//implements java.io.Serializable {
    String folio
    String factura
    Date fechaFactura
    String comentarios
    BigDecimal tipoCambio
    BigDecimal iva = new BigDecimal("0.00")
    BigDecimal total = new BigDecimal("0.00")
    Boolean devolucion = false
//    Estatus estatus
    String estatus
//    Boolean pendiente = false
        //en el estatus se podra ver
    Proveedor proveedor
    Almacen almacen
    Date dateCreated
    Date lastUpdated
    Set lotes
    BigDecimal totalFactura = new BigDecimal("0")
//    Boolean facturada = false
        //en el estatus se podra ver
    FacturaAlmacen facturaAlmacen

//    static transients = ['totalFactura']
        //por lo de serializable

    static belongsTo = [Proveedor, Almacen, FacturaAlmacen]

    static hasMany = [lotes: LoteEntrada]

    static constraints = {
        folio(maxSize:64,unique:'almacen')
        factura(blank:false, maxSize:64, unique:'almacen')
        fechaFactura()
        iva(scale:2,precision:8,min:new BigDecimal('0'))
        total(scale:2,precision:8,min:new BigDecimal('0'))
        tipoCambio(nullable:true,scale:2,precision:8)
        estatus(maxSize:64, inList:['ABIERTA','CERRADA','CANCELADA','FACTURADA'])
        comentarios(nullable:true,maxSize:128)
        facturaAlmacen(nullable:true)
    }

    static mapping = {
        table 'entradas'
        folio index:'entrada_folio_idx'
        estatus index:'entrada_estatus_idx'
        almacen index:'entrada_almacen_idx'
    }

    static namedQueries = {
        relaciones {
//            join 'estatus'
            join 'proveedor'
//            estatus {
//                order 'prioridad', 'asc'
//            }
            order 'folio', 'desc'
        }

        buscaPorAlmacen { filtro ->
            almacen {
                eq 'id', filtro.id
            }
        }

        buscaPorFiltro { filtro ->
            filtro = "%$filtro%"
            or {
                ilike 'folio', filtro
                ilike 'factura', filtro
                estatus {
                    ilike 'nombre', filtro
                }
                proveedor {
                    or {
                        ilike 'nombre', filtro
                        ilike 'rfc', filtro
                    }
                }
            }
        }

        buscaPorFecha { fechaInicial, fechaFinal ->
            between 'lastUpdated', fechaInicial, fechaFinal
        }

        //falta arreglar estatus
        listaParaFacturar { almacenId, filtro ->
            filtro = "%$filtro%"
            ilike 'folio', filtro
            eq 'devolucion', true
//            eq 'facturada', false
            almacen {
                idEq(almacenId)
            }
//            estatus {
//                eq 'nombre', 'estatus.cerrada'
//            }
            order 'lastUpdated', 'desc'
        }
    }

    String toString() {
        "$folio : $factura : $total"
    }

    boolean equals(Object o) {
        if (o != null) {
            if (o instanceof Entrada) {
                def entrada = (Entrada)o
                if (entrada.id == this.id) {
                    return true
                }
            }
        }
        return false
    }

}