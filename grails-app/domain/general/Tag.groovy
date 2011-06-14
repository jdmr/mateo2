package general

class Tag {
    String nombre
    Organizacion organizacion

    static constraints = {
        nombre maxSize:64,blank:false,unique:'organizacion'
    }

    static mapping = {
        table 'tags'
    }

    static namedQueries = {
        buscaPorFiltro { filtro, organizacionId ->
            filtro = "%$filtro%"
            ilike('nombre',filtro)
            organizacion {
                idEq(organizacionId)
            }
        }
    }

    String toString() {
        return nombre
    }
}
