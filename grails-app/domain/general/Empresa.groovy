package general

class Empresa {
    String nombre
    String nombreCompleto
    Organizacion organizacion

    static belongsTo = [Organizacion]

    static constraints = {
        nombre blank:false, unique:true, maxSize:32
        nombreCompleto blank:false, maxSize:128
    }

    String toString() {
        return nombre
    }
}
