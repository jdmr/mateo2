package contabilidad

import general.Empresa

class Transaccion {
    String folio
    String descripcion
    BigDecimal importe = new BigDecimal('0')
    Poliza poliza
    Empresa empresa
    Set origenes
    Set destinos
    Date dateCreated
    Date lastUpdated
    String tags

    static belongsTo = [poliza: Poliza, empresa: Empresa]

    static hasMany = [origenes: Movimiento, destinos: Movimiento]

    static mappedBy = [origenes:'origen', destinos: 'destino']

    static constraints = {
        folio maxSize:64, unique:'empresa'
        descripcion maxSize:128, blank:false
        importe scale:2, precision:8
        tags maxSize:200, nullable:true
    }

    static mapping = {
        table 'transacciones'
    }

    String toString() {
        return folio
    }
}
