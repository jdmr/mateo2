package inventario

class CancelacionAlmacen implements Serializable {
    String folio
    String comentarios
    Entrada entrada
    Salida salida
    Set entradas
    Set salidas
    String creador
    Almacen almacen
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Entrada, Salida]

    static hasMany   = [entradas: Entrada, salidas: Salida]

    static constraints = {
        folio(blank:false,maxSize:64)
        comentarios(maxSize:200, nullable:true)
        entrada(nullable:true)
        salida(nullable:true)
        creador(blank:false, maxSize:64)
    }

    static mapping = {
        table 'cancelaciones_almacen'
    }

    String toString() {
        return folio
    }
}
