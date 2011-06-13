package contabilidad

class Movimiento {
    BigDecimal importe = new BigDecimal('0')
    Cuenta cuenta
    Auxiliar auxiliar
    Date dateCreated
    Date lastUpdated
    boolean padre = false
    boolean ultimo = false
    Transaccion origen
    Transaccion destino

    static transients = ['padre','ultimo']

    static belongsTo = [cuenta:Cuenta, origen:Transaccion, destino:Transaccion]

    static constraints = {
        importe scale:2, precision:8
        auxiliar nullable:true
        origen nullable:true
        destino nullable:true
    }

    static mapping = {
        table 'movimientos'
    }

    String toString() {
        return "$cuenta : $importe"
    }
}
