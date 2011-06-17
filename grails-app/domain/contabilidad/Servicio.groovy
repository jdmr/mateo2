package contabilidad

import general.Empresa

class Servicio {

    String nombre
    String descripcion
    Empresa empresa
    Set origenes
    Set destinos
    Date dateCreated
    Date lastUpdated
    String tags

    static belongsTo = [Empresa]

    static hasMany = [origenes: Componente, destinos: Componente]

    static mappedBy = [origenes:'origen', destinos: 'destino']

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