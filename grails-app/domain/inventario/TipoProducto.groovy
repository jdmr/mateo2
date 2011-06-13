package inventario

class TipoProducto {//implements java.io.Serializable {
    String nombre
    String descripcion
//    Boolean base = false
        //noc para que sirve
    Almacen almacen

    static belongsTo = Almacen

    static constraints = {
        nombre unique:'almacen', blank: false, maxSize: 64
        descripcion unique: 'almacen', blank: false, maxSize: 128
    }

    static mapping = {
        table 'tipos_producto'
    }

    String toString() {
        nombre
    }
}