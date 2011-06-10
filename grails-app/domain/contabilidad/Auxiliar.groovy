package contabilidad

class Auxiliar {
    String nombre
    String descripcion
    Cuenta cuenta

    static constraints = {
        nombre blank:false, maxSize:32, unique:'cuenta'
        descripcion blank:false, maxSize:128, unique:'cuenta'
    }

    static mapping = {
        table 'auxiliares'
    }

    String toString() {
        return "$nombre | $descripcion"
    }
}
