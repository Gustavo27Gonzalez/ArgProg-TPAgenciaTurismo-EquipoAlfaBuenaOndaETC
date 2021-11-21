package usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import atraccion.Atraccion;
import dao.AtraccionDAO;
import dao.ItinerarioDAO;
import dao.MissingDataException;
import jdbc.ConnectionProvider;
import ofertable.Ofertable;
import promociones.Promocion;
import tipos.Tipo;

public class Usuario {

    private final int id;
    private final String nombre;
    private int dineroDisponible;
    private final int dineroInicial;
    private final double tiempoInicial;
    private final Tipo preferencia;
    private double tiempo;
    public final ArrayList<Atraccion> atraccionesCompradas = new ArrayList<Atraccion>();
    public final ArrayList<Promocion> promocionesCompradas = new ArrayList<Promocion>();
    public final ArrayList<Atraccion> comprasDeUsuario = new ArrayList<Atraccion>();

    public Usuario(String nombre, int dineroDisponible, Tipo preferencia, double tiempo, int id) {
        this.id = id;
        this.nombre = nombre;
        this.dineroInicial = dineroDisponible;
        this.tiempoInicial = tiempo;
        this.dineroDisponible = dineroDisponible;
        this.preferencia = preferencia;
        this.tiempo = tiempo;
    }

    public int getId() {
        return id;
    }

    public int getDineroInicial() {
        return dineroInicial;
    }

    public double getTiempoInicial() {
        return tiempoInicial;
    }

    public double getTiempo() {
        return tiempo;
    }

    public void setTiempo(double d) {
        this.tiempo = d;
    }

    public String getNombre() {
        return nombre;
    }

    public void setDineroDisponible(int d) {
        this.dineroDisponible = d;
    }

    public int getDineroDisponible() {
        return dineroDisponible;
    }

    public Tipo getPreferencia() {
        return preferencia;
    }

    public void comprar(Ofertable o) {
        if (o.esPromocion()) {
            this.restarDinero(o);
            this.restarTiempo(o);
            promocionesCompradas.add((Promocion) o);
            comprasDeUsuario.addAll(((Promocion) o).getAtracciones());

            ItinerarioDAO.agregarPromocion(o, this.getId());

            ((Promocion) o).atraccionesDePromo.forEach((atraccion) -> {
                this.restarCupo(atraccion);
            });

        } else {
            this.restarDinero(o);
            this.restarTiempo(o);
            this.restarCupo(o);
            atraccionesCompradas.add((Atraccion) o);
            comprasDeUsuario.add((Atraccion) o);

            ItinerarioDAO.agregarAtraccion(o, this.getId());

        }

    }

    public void restarCupo(Ofertable ofertable) {
        ((Atraccion) ofertable).entrarALaAtraccion();
        AtraccionDAO.actualizarCupo(ofertable);
    }

    public void restarTiempo(Ofertable ofertable) {
        this.setTiempo(this.getTiempo() - (ofertable).getDuracion());
        try {
            String sql = "UPDATE usuarios SET tiempo = tiempo - ? WHERE id = ?";
            Connection conn = ConnectionProvider.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDouble(1, ofertable.getDuracion());
            statement.setInt(2, this.id);
            statement.executeUpdate();


        } catch (Exception e) {
            throw new MissingDataException(e);
        }
    }


    public void restarDinero(Ofertable ofertable) {
        this.setDineroDisponible(this.getDineroDisponible() - ofertable.getCosto());
        try {
            String sql = "UPDATE usuarios SET dinero = dinero - ? WHERE id = ?";
            Connection conn = ConnectionProvider.getConnection();

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ofertable.getCosto());
            statement.setInt(2, this.id);
            statement.executeUpdate();


        } catch (Exception e) {
            throw new MissingDataException(e);
        }
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", dineroDisponible=" + dineroDisponible + ", preferencia="
                + preferencia + ", tiempo=" + tiempo + '}';
    }

}
