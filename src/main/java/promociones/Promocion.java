package promociones;

import java.util.ArrayList;
import java.util.Iterator;

import atraccion.Atraccion;
import ofertable.Ofertable;
import tipos.Tipo;


public abstract class Promocion implements Ofertable {

	private String nombre;
	
	private Tipo tipoAtracciones; // DE ATRACCION

	public ArrayList<Atraccion> atraccionesDePromo = new ArrayList<Atraccion>();

	public Promocion(String nombre, Tipo tipo) {
		this.nombre = nombre;
		this.tipoAtracciones = tipo;
	}

	
	public void agregarAtraccion(Atraccion nuevaAtraccion) {
		atraccionesDePromo.add(nuevaAtraccion);
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atraccionesDePromo;
	}

	public Double getDuracion() {
		Double duracionTotal = 0.0;
		Iterator<Atraccion> iteradorDuracion = atraccionesDePromo.iterator();
		while (iteradorDuracion.hasNext()) {
			Atraccion a = iteradorDuracion.next();
			duracionTotal += a.getDuracion();
		}
		return duracionTotal;
	}

	public Tipo getTipo() {
		return this.tipoAtracciones;
	}

	public abstract Integer getCosto();

	public boolean esPromocion() {
		return true;
	}

	public boolean esPromocionRegala() {
		return false;
	}

	public String getNombre() {
		return this.nombre;
	}

	public Tipo getTipoAtraccion() {
		return this.tipoAtracciones;
	}

	public Integer getCupoMaximo() {
		return null;
	}
}
