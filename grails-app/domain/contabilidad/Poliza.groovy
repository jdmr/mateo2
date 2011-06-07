package contabilidad

import general.Empresa

class Poliza {
    String folio
    String descripcion
    String estatus = 'ABIERTA'
    String tipo = 'DIARIO'
    Ejercicio ejercicio
    Libro libro
    Empresa empresa
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Ejercicio, Libro, Empresa]

    static constraints = {
        folio maxSize:128, blank:false, unique:'empresa'
        descripcion maxSize:200, blank:false
        estatus maxSize:32, inList:['ABIERTA','CERRADA','CANCELADA']
        tipo maxSize:32, inList:['DIARIO','INGRESOS','EGRESOS']
    }

    static mapping = {
        table 'polizas'
    }

    String toString() {
        return folio
    }
}
