package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import atraccion.Atraccion;
import jdbc.ConnectionProvider;
import promociones.Promocion;
import usuario.Usuario;

public class ItinerarioDAO {
	private Connection conn;
	

	public void generarItinerario() {
		try {
			String sql = "INSERT INTO itinerario (id_usuario, compra) VALUES (?, ?)";
			Connection conn = ConnectionProvider.getConnection();
			
			for (Usuario usuario : UsuarioDAOImpl.listaUsuarios) {
				for (Atraccion atraccion : usuario.atraccionesCompradas) {
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setLong(1, usuario.getId());
					statement.setString(2, atraccion.getNombre());
					statement.executeUpdate();
				}
				
				for (Promocion promocion : usuario.promocionesCompradas) {
					PreparedStatement statement = conn.prepareStatement(sql);
					statement.setLong(1, usuario.getId());
					statement.setString(2, promocion.getNombre());
					statement.executeUpdate();
				}
			}
		} catch (Exception e) {
			throw new MissingDataException(e);
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
