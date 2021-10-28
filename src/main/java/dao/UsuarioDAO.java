package dao;

import java.sql.SQLException;

import usuario.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario>{

	public abstract void createArray() throws SQLException;

	public abstract void actualizarDBUsuarios();
}
