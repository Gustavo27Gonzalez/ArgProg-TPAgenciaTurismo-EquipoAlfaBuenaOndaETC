package dao;

import atraccion.Atraccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbc.ConnectionProvider;
import promociones.PromoAbsoluta;
import promociones.PromoPorcentual;
import promociones.PromoRegala;
import promociones.Promocion;
import tipos.Tipo;

public class PromocionDAOimpl implements PromocionDAO {

    private Connection conn;

    public List<Promocion> findAll() {
        try {
            String sql = "SELECT * FROM Promocion";
            conn = ConnectionProvider.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultados = statement.executeQuery();

            List<Promocion> promo = new LinkedList<Promocion>();
            while (resultados.next()) {
                try {
                    promo.add(toPromo(resultados));
                } catch (Exception e) {

                    throw new MissingDataException(e);
                }
            }
            return promo;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return null;
    }

    public int countAll() {
        try {
            String sql = "SELECT COUNT(1) AS TOTAL FROM Promocion";
            conn = ConnectionProvider.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultados = statement.executeQuery();

            resultados.next();
            int total = resultados.getInt("TOTAL");

            return total;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int insert(Promocion t) {
        try {
            String sql = "INSERT INTO Promocion  (nombre ,Tipo,monto,Tiempo,AtraccionGratis,Descuento) VALUES "
                    + "(?,?,?,?,?,?)";
            conn = ConnectionProvider.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, t.getNombre());
            statement.setObject(2, t.getTipo());
            statement.setDouble(3, t.getCosto());
            statement.setDouble(4, t.getDuracion());
            statement.setObject(5, ((PromoRegala) t).getRegalo());
            statement.setDouble(6, ((PromoPorcentual) t).getPorcentaje());

            int rows = statement.executeUpdate();

            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int update(Promocion t) {
        try {
            String sql = "UPDATE Promocion SET Nombre = ? WHERE Tipo  = ?";
            conn = ConnectionProvider.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, t.getNombre());
            statement.setObject(2, t.getTipo());
            int rows = statement.executeUpdate();

            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int delete(Promocion t) {
        try {
            String sql = "DELETE FROM Promocion WHERE Nombre LIKE ?";
            Connection conn = ConnectionProvider.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, t.getNombre());
            int rows = statement.executeUpdate();

            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    private Promocion toPromo(ResultSet resultados) throws Exception {

        Tipo tipoAtraccion = Tipo.valueOf(resultados.getString(2));
        String breveDescripcion = resultados.getString(10);
        Atraccion[] packAtracciones = atraccionesDeLaPromocion(resultados.getLong(8), resultados.getLong(9));
        String nombre = resultados.getString(1);
        String promocionTipo = resultados.getString(7);
        Promocion promo = null;
        promo = crearPromo(resultados, tipoAtraccion, breveDescripcion, nombre, promocionTipo, promo);
        return promo;

    }

    private Promocion crearPromo(ResultSet resultados, Tipo tipo, String breveDescripcion,
            String nombre, String promocionTipo, Promocion promo) throws SQLException {
        if (promocionTipo.equals("AxB")) {
            Atraccion gratis = buscarPorId(resultados.getLong(5));
            promo = new PromoRegala(nombre, tipo, gratis, breveDescripcion);
        } else if (promocionTipo.equals("PORCENTUAL")) {
            int descuento = resultados.getInt(6);
            promo = new PromoPorcentual(nombre, tipo, descuento, breveDescripcion);
        } else if (promocionTipo.equals("NETO")) {
            int descuento = resultados.getInt(3);
            promo = new PromoAbsoluta(nombre, tipo, descuento, breveDescripcion);
        }
        return promo;
    }

    private Atraccion[] atraccionesDeLaPromocion(Long atraccion1, Long atraccion2) throws Exception {
        Atraccion[] promos = new Atraccion[2];
        try {

            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlAtraccion());
            statement.setLong(1, atraccion1);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                promos[0] = AtraccionDAO.toAtraccion(result);
            }
            statement.setLong(1, atraccion2);
            ResultSet result2 = statement.executeQuery();
            while (result2.next()) {
                promos[1] = AtraccionDAO.toAtraccion(result2);
            }
            return promos;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private String sqlAtraccion() {
        String sql = "select *" + "from Atraccion WHERE ID_Atraccion = ?";
        return sql;
    }

    public Atraccion buscarPorId(Long IdAtraccion) {
        try {
            String sql = "SELECT Atraccion.ID_Atraccion, Atraccion.Nombre, Atraccion.Cupo_Disponible,"
                    + " Atraccion.Costo, Atraccion.Tiempo,"
                    + "  Atraccion.TipoDeAtraccion"
                    + " FROM Atraccion"
                    + " WHERE Atraccion.Id_Atraccion = ?";
            conn = ConnectionProvider.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, IdAtraccion);
            ResultSet resultados = statement.executeQuery();

            Atraccion atraccion = null;

            if (resultados.next()) {
                atraccion = AtraccionDAO.toAtraccion(resultados);
            }

            return atraccion;
        } catch (Exception e) {
            throw new MissingDataException(e);
        }
    }
}
