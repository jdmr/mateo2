package contabilidad

class ServicioMovimiento implements Serializable {
    BigDecimal importe = new BigDecimal('0')
    Cuenta cuenta
    Auxiliar auxiliar
    Date dateCreated
    Date lastUpdated
    Boolean preguntar = false
    boolean padre = false
    boolean ultimo = false
    ServicioTransaccion origen
    ServicioTransaccion destino

    static transients = ['padre','ultimo']

    static belongsTo = [cuenta:Cuenta, origen:ServicioTransaccion, destino:ServicioTransaccion]

    static constraints = {
        importe scale:2, precision:8
        auxiliar nullable:true
        origen nullable:true
        destino nullable:true
    }

    static mapping = {
        table 'servicio_movimiento'
    }

    String toString() {
        return "$cuenta : $importe"
    }
}
