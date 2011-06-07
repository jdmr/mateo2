package contabilidad

import general.Empresa

class Ejercicio {
    String nombre
    String estatus = 'ABIERTO'
    Empresa empresa
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Empresa]

    static constraints = {
        nombre blank:false, maxSize:64, unique:'empresa'
        estatus inList:['ABIERTO','CERRADO']
    }

    static mapping = {
        table 'ejercicios'
    }

    String toString() {
        return nombre
    }
}
