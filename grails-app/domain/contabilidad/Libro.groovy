package contabilidad

import general.Empresa

class Libro {
    String nombre
    Empresa empresa
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Empresa]

    static constraints = {
        nombre blank:false, maxSize:64, unique:'empresa'
    }

    static mapping = {
        table 'libros'
    }

    String toString() {
        return nombre
    }
}
