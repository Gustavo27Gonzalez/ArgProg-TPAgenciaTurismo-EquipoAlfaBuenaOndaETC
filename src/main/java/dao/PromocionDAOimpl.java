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

    //private AtraccionDAO atraccionDao;
    private Connection conn;

    //public PromocionDAOimpl() {
    //    this.atraccionDao = new AtraccionDAO();
    //}

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

    public int countAll(){
        try {
            String sql = "SELECT COUNT(1) AS TOTAL FROM Promocion";
            Connection conn = ConnectionProvider.getConnection();
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

    public int insert(Promocion t){
        try {
            String sql = "INSERT INTO Promocion  (nombre ,Tipo,monto,Tiempo,AtraccionGratis,Descuento) VALUES "
                    + "(?,?,?,?,?,?)";
            Connection conn = ConnectionProvider.getConnection();
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(4, t.getNombre());
            statement.setObject(5, t.getTipo());
            statement.setDouble(6, t.getCosto());
            statement.setDouble(7, t.getDuracion());
            statement.setObject(8, ((PromoRegala) t).getRegalo());
            statement.setDouble(9, ((PromoPorcentual) t).getPorcentaje());
            
            int rows = statement.executeUpdate();
            
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int update(Promocion t){
        try {
            String sql = "UPDATE Promocion SET Nombre = ? WHERE Tipo  = ?";
            Connection conn = ConnectionProvider.getConnection();
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(4, t.getNombre());
            statement.setObject(5, t.getTipo());
            int rows = statement.executeUpdate();
            
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    public int delete(Promocion t){
        try {
            String sql = "DELETE FROM Promocion WHERE Nombre LIKE ?";
            Connection conn = ConnectionProvider.getConnection();
            
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(3, t.getNombre());
            int rows = statement.executeUpdate();
            
            return rows;
        } catch (SQLException ex) {
            Logger.getLogger(PromocionDAOimpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;

    }

    private Promocion toPromo(ResultSet resultados) throws Exception {

        Tipo tipoAtraccion = Tipo.valueOf(resultados.getString(5));
        Atraccion[] packAtracciones = atraccionesDeLaPromocion(resultados.getLong(2), resultados.getLong(3));
        String nombre = resultados.getString(4);
        String promocionTipo = resultados.getString(10);
        Promocion promo = null;
        promo = crearPromo(resultados, tipoAtraccion, packAtracciones, nombre, promocionTipo, promo);
        return promo;

    }

    private Promocion crearPromo(ResultSet resultados, Tipo tipo, Atraccion[] packAtracciones,
            String nombre, String promocionTipo, Promocion promo) throws SQLException {
        if (promocionTipo.equals("AxB")) {
            Atraccion gratis = atraccionDao.buscarPorId(resultados.getLong(8));
            promo = new PromoRegala(nombre, packAtracciones, tipo, gratis);
        } else if (promocionTipo.equals("PORCENTUAL")) {
            int descuento = resultados.getInt(9);
            promo = new PromoPorcentual(nombre, packAtracciones, tipo, descuento);
        } else if (promocionTipo.equals("NETO")) {
            double monto = resultados.getInt(6);
            promo = new PromoAbsoluta(nombre, packAtracciones, tipo, monto);
        }
        return promo;
    }

    private Atraccion[] atraccionesDeLaPromocion(Long atraccion1, Long atraccion2) throws Exception {
        Atraccion[] packs = new Atraccion[2];
        try {

            Connection conn = ConnectionProvider.getConnection();
            PreparedStatement statement = conn.prepareStatement(sqlAtraccion());
            statement.setLong(1, atraccion1);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                packs[0] = atraccionDao.toAtraccion(result);
            }
            statement.setLong(1, atraccion2);
            ResultSet result2 = statement.executeQuery();
            while (result2.next()) {
                packs[1] = atraccionDao.toAtraccion(result2);
            }
            return packs;
        } catch (Exception e) {
            throw new Exception();
        }
    }

    private String sqlAtraccion() {
        String sql = "select *" + "from Atraccion WHERE ID_Atraccion = ?";
        return sql;
    }

}
