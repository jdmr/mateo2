package contabilidad

class Componente {
    BigDecimal importe = new BigDecimal('0')
    Cuenta cuenta
    Auxiliar auxiliar
    Date dateCreated
    Date lastUpdated
    Servicio origen
    Servicio destino

    static belongsTo = [cuenta: Cuenta, origen: Servicio, destino: Transaccion]

    static constraints = {
        importe sclae:2, precision:8
        auxiliar nullable:true
        origen nullable:true
        destino nullable:true
    }

    static mapping = {
        table 'componentes'
    }

    String toString() {
        return "$cuenta : $importe"
    }
}
