package general

class Organizacion {
    String codigo
    String nombre
    String nombreCompleto
    Set empresas

    static hasMany = [empresas: Empresa]

    static constraints = {
        codigo(blank:false, unique:true, maxSize:6)
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
