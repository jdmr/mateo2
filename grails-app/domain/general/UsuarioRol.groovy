package general

import org.apache.commons.lang.builder.HashCodeBuilder

class UsuarioRol implements Serializable {

	Usuario usuario
	Rol rol

	boolean equals(other) {
		if (!(other instanceof UsuarioRol)) {
			return false
		}

		other.usuario?.id == usuario?.id &&
			other.rol?.id == rol?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (usuario) builder.append(usuario.id)
		if (rol) builder.append(rol.id)
		builder.toHashCode()
	}

	static UsuarioRol get(long usuarioId, long rolId) {
		find 'from UsuarioRol where usuario.id=:usuarioId and rol.id=:rolId',
			[usuarioId: usuarioId, rolId: rolId]
	}

	static UsuarioRol create(Usuario usuario, Rol rol, boolean flush = false) {
		new UsuarioRol(usuario: usuario, rol: rol).save(flush: flush, insert: true)
	}

	static boolean remove(Usuario usuario, Rol rol, boolean flush = false) {
		UsuarioRol instance = UsuarioRol.findByUsuarioAndRol(usuario, rol)
		instance ? instance.delete(flush: flush) : false
	}

	static void removeAll(Usuario usuario) {
		executeUpdate 'DELETE FROM UsuarioRol WHERE usuario=:usuario', [usuario: usuario]
	}

	static void removeAll(Rol rol) {
		executeUpdate 'DELETE FROM UsuarioRol WHERE rol=:rol', [rol: rol]
	}

	static mapping = {
		id composite: ['rol', 'usuario']
		version false
	}
}
