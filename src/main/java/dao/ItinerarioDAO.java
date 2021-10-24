package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import atraccion.Atraccion;
import jdbc.ConnectionProvider;
import usuario.Usuario;

public class ItinerarioDAO {

	public void generarItinerario() throws SQLException {
		String sql = "INSERT INTO itinerario (id_usuario, compra) VALUES (?, ?)";
		Connection conn = ConnectionProvider.getConnection();

		for (Usuario usuario : UsuarioDAOImpl.listaUsuarios) {
			for (Atraccion atraccion : usuario.atraccionesCompradas) {
				PreparedStatement statement = conn.prepareStatement(sql);
				statement.setLong(1, usuario.getId());
				statement.setString(2, atraccion.getNombre());
				statement.executeUpdate();
			}
		}

	}
}
