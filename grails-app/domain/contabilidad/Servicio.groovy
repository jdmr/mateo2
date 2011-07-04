package contabilidad

import general.Empresa

class Servicio implements Serializable {

    String nombre
    String descripcion
    Empresa empresa
    Set transacciones
    Date dateCreated
    Date lastUpdated
    String tags

    static belongsTo = [Empresa]

    static hasMany = [transacciones: ServicioTransaccion]

    static constraints = {
        nombre maxSize:64, blank:false, unique:'empresa'
        descripcion maxSize:200, blank:false
        tags maxSize:200, nullable:true
    }

    static mapping = {
        table 'servicios'
    }

    String toString() {
        return nombre
    }
}
