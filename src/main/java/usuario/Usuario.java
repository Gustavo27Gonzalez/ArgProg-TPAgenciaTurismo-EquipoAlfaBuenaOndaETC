package usuario;

import java.util.ArrayList;

import atraccion.Atraccion;
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

			((Promocion) o).atraccionesDePromo.forEach((atraccion) -> {
				this.restarCupo(atraccion);
			});

		} else {
			this.restarDinero(o);
			this.restarTiempo(o);
			this.restarCupo(o);
			atraccionesCompradas.add((Atraccion) o);
			comprasDeUsuario.add((Atraccion) o);

		}

	}

	public void restarCupo(Ofertable ofertable) {
		((Atraccion) ofertable).entrarALaAtraccion();
	}

	public void restarTiempo(Ofertable ofertable) {
		this.setTiempo(this.getTiempo() - (ofertable).getDuracion());
	}

	public void restarDinero(Ofertable ofertable) {
		this.setDineroDisponible(this.getDineroDisponible() - ofertable.getCosto());
	}

	@Override
	public String toString() {
		return "Usuario{" + "nombre=" + nombre + ", dineroDisponible=" + dineroDisponible + ", preferencia="
				+ preferencia + ", tiempo=" + tiempo + '}';
	}

}
