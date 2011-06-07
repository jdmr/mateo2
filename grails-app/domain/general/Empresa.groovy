package general

class Empresa {
    String codigo
    String nombre
    String nombreCompleto
    Organizacion organizacion

    static belongsTo = [Organizacion]

    static hasMany = [usuarios: Usuario]

    static constraints = {
        codigo(blank:false, unique:true, maxSize:6)
        nombre blank:false, unique:'organizacion', maxSize:32
        nombreCompleto blank:false, maxSize:128
    }

    String toString() {
        return nombre
    }

    String getNombreCanonico() {
        return "${organizacion.nombre} | ${nombre}"
    }
}
