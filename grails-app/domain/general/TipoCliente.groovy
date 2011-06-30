package general

/**
 *
 * @author jdmr
 */
class TipoCliente implements java.io.Serializable {
    String nombre
    String descripcion
    BigDecimal margenUtilidad = new BigDecimal("0.20")
    Empresa empresa

    static belongsTo = Empresa

    static constraints = {
        nombre(blank:false, maxSize:16, unique:'empresa')
        descripcion(nullable:true,maxSize:128)
        margenUtilidad(scale:2,precision:8)
    }

    static mapping = {
        table 'tipos_cliente'
    }

    String toString() {
        nombre
    }

}
