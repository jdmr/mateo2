package general

class Empresa {
    String nombre
    String nombreCompleto
    Organizacion organizacion

    static belongsTo = [Organizacion]

    static hasMany = [usuarios: Usuario]

    static constraints = {
        nombre blank:false, unique:'organizacion', maxSize:32
        nombreCompleto blank:false, maxSize:128
    }

    String toString() {
        return nombre
    }
}
