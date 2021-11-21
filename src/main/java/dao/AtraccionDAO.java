package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import atraccion.Atraccion;
import jdbc.ConnectionProvider;
import ofertable.Ofertable;
import tipos.Tipo;

public class AtraccionDAO{

	public static ArrayList<Atraccion> listaAtracciones = new ArrayList<Atraccion>();
	private Connection conn;

	public int insert(Atraccion atraccion) throws SQLException {
		try {
			String sql = "INSERT INTO atracciones(id, nombre, tipo, costo, duracion, cupo, descripcion) VALUES (?, ?, ?, ?, ?, ?, ?)";
			conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, atraccion.getId());
			statement.setString(2, atraccion.getNombre());
			statement.setString(3, atraccion.getTipo().toString());
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
			statement.setString(2, atraccion.getTipo().toString());
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

	public int countAll() throws SQLException {
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

	public List<Atraccion> findAll() throws SQLException {
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

	public Atraccion toAtraccion(ResultSet resultados) throws SQLException {
		return new Atraccion(resultados.getInt("id"), resultados.getString("nombre"), Tipo.valueOf(resultados.getString("tipo")), resultados.getInt("costo"), resultados.getDouble("duracion"), resultados.getInt("cupo"), resultados.getString(6));
	}

	public void createArray() {
		try {
			String sql = "SELECT * FROM atracciones";
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt("id");
				String nombre = rs.getString("nombre");
				Tipo tipo = Tipo.valueOf(rs.getString("tipo"));
				Integer costo = rs.getInt("costo");
				double duracion = rs.getDouble("duracion");
				int cupo = rs.getInt("cupo");
				String descripcion = rs.getString("descripcion");

				listaAtracciones.add(new Atraccion(id, nombre, tipo, costo, duracion, cupo, descripcion));
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

	public void actualizarDBAtracciones() throws SQLException {
		for (Atraccion atraccion : listaAtracciones) {
			this.update(atraccion);
		}
	}

	public static void actualizarCupo(Ofertable ofertable) {
		try {
			String sql = "UPDATE atracciones SET cupo = cupo - 1 WHERE nombre = ?";
			Connection conn = ConnectionProvider.getConnection();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, ofertable.getNombre());
			statement.executeUpdate();


		} catch (Exception e) {
			throw new MissingDataException(e);
		}
	}
	}


