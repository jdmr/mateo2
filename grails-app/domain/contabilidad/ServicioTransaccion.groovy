package contabilidad

import general.Empresa

class ServicioTransaccion implements Serializable {
    String descripcion
    BigDecimal importe = new BigDecimal('0')
    Servicio servicio
    Set origenes
    Set destinos
    Date dateCreated
    Date lastUpdated
    String tags

    static belongsTo = [servicio: Servicio]

    static hasMany = [origenes: ServicioMovimiento, destinos: ServicioMovimiento]

    static mappedBy = [origenes:'origen', destinos: 'destino']

    static constraints = {
        descripcion maxSize:128, blank:false
        importe scale:2, precision:8
        tags maxSize:200, nullable:true
    }

    static mapping = {
        table 'servicio_transaccion'
    }

    String toString() {
        return folio
    }
}
