package contabilidad

import general.Empresa

class Folio {
    String nombre
    Long valor = 0
    Empresa empresa
    Date dateCreated
    Date lastUpdated

    static belongsTo = [Empresa]

    static constraints = {
        nombre blank:false, maxSize: 32, unique:'empresa'
    }
}
