package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import jdbc.ConnectionProvider;
import model.Atraccion;

public class AtraccionDAO{
	
	private Connection conn;
	
	public int insert(Atraccion atraccion) throws SQLException {
		try {
			String sql = "INSERT INTO atracciones(id, nombre, tipo, costo, duracion, cupo, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?)";
			conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());
			statement.setString(2, atraccion.getNombre());
			statement.setString(3, atraccion.getTipo());
			statement.setInt(4, atraccion.getCosto());
			statement.setDouble(5, atraccion.getDuracion());
			statement.setInt(6, atraccion.getCupoMaximo());
			statement.setString(7, atraccion.getBreveDescripcion());
			int rows = statement.executeUpdate();

			return rows;
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

	public int update(Atraccion atraccion) throws SQLException {
		try {
			String sql = "UPDATE atracciones SET nombre = ?, tipo = ?, costo = ?, duracion = ?, cupo = ?, descripcion = ?  WHERE id = ?";
			conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccion.getNombre());
			statement.setString(2, atraccion.getTipo());
			statement.setInt(3, atraccion.getCosto());
			statement.setDouble(4, atraccion.getDuracion());
			statement.setInt(5, atraccion.getCupoMaximo());
			statement.setString(6, atraccion.getBreveDescripcion());
			statement.setInt(7, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
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

	public int delete(Atraccion atraccion) throws SQLException {
		try {
			String sql = "DELETE FROM atracciones WHERE id = ?";
			conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());
			int rows = statement.executeUpdate();

			return rows;
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

	public Atraccion findByAtraccionName(String atraccionName) throws SQLException {
		try {
			String sql = "SELECT * FROM \"atracciones\" WHERE nombre = ?";
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, atraccionName);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
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
	
	public Atraccion findByAtraccionId(int atraccionID) throws SQLException {
		try {
			String sql = "SELECT * FROM \"atracciones\" WHERE id = ?";
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccionID);
			ResultSet resultados = statement.executeQuery();

			Atraccion atraccion = null;

			if (resultados.next()) {
				atraccion = toAtraccion(resultados);
			}

			return atraccion;
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

	public int countAll() {
		try {
			String sql = "SELECT count(*) AS 'total' FROM atracciones";
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			resultados.next();
			int total = resultados.getInt("total");

			return total;
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

	public List<Atraccion> findAll() {
		try {
			String sql = "SELECT * FROM atracciones";
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet resultados = statement.executeQuery();

			List<Atraccion> atracciones = new LinkedList<Atraccion>();
			while (resultados.next()) {
				atracciones.add(toAtraccion(resultados));
			}

			return atracciones;
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

	private Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getInt("id"), resultados.getString("nombre"), resultados.getString("tipo"), resultados.getInt("costo"), resultados.getDouble("duracion"), resultados.getInt("cupo"), resultados.getString("descripcion"));
	}

	
	
	/*
	 try {
			
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
	 */

}
