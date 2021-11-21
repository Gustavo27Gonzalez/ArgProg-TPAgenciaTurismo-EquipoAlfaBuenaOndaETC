package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import atraccion.Atraccion;
import jdbc.ConnectionProvider;
import ofertable.Ofertable;
import promociones.Promocion;
import usuario.Usuario;

public class ItinerarioDAO {
    private Connection conn;


    public static void agregarPromocion(Ofertable promocion, int id) {
        try {
            String sql = "INSERT INTO itinerario (id_usuario, compra) VALUES (?, ?)";
            Connection conn = ConnectionProvider.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setString(2, promocion.getNombre());
            statement.executeUpdate();


        } catch (Exception e) {
            throw new MissingDataException(e);
        }
    }

    public static void agregarAtraccion(Ofertable atraccion, int id) {
        try {
            String sql = "INSERT INTO itinerario (id_usuario, compra) VALUES (?, ?)";
            Connection conn = ConnectionProvider.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id);
            statement.setString(2, atraccion.getNombre());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new MissingDataException(e);
        }
    }
}
