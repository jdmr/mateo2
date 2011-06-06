package general

class Empresa {
    String nombre
    String nombreCompleto
    Organizacion organizacion
    Set empresas

    static belongsTo = [Organizacion]

    static hasMany = [usuarios: Usuario]

    static constraints = {
        nombre blank:false, unique:true, maxSize:32
        nombreCompleto blank:false, maxSize:128
    }

    String toString() {
        return nombre
    }
}
