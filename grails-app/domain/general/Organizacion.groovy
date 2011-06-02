package general

class Organizacion {
    String nombre
    String nombreCompleto

    static constraints = {
        nombre(blank:false, unique:true, maxSize:32)
        nombreCompleto(blank:false, unique:true, maxSize:128)
    }

    static mapping = {
        table 'organizaciones'
    }

    String toString() {
        return nombre
    }
}
