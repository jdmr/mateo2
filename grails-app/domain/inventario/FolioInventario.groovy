package inventario

class FolioInventario {//implements java.io.Serializable {
    String nombre
    Long valor
    Almacen almacen

    static belongsTo = Almacen

    static constraints = {
        nombre(maxSize:32,unique:'almacen')
        valor()
        almacen()
    }

    static mapping = {
        table 'folio_inventario'
        nombre index:'folio_inventario_idx'
        almacen index:'folio_inventario_idx'
    }

    String toString() {
        "$almacen : $nombre : $valor"
    }
}