package dao;

import usuario.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{

	public abstract void createArray();

	public abstract void actualizarDBUsuarios();
}
