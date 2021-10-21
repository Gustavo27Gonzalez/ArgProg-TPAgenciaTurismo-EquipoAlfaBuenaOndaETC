package promociones;

import java.util.ArrayList;
import java.util.Iterator;

import atraccion.Atraccion;
import ofertable.Ofertable;
import tipos.Tipo;

/**
 * Clase Abstracta que permite la herencia de 3 tipos distintos de promocion
 * 
 * @author Daniel Paz, Tomas Andrada
 *
 */
public abstract class Promocion implements Ofertable {

	/**
	 * Nombre de la promocion
	 */
	private String nombre;
	/**
	 * Tipò de la promocion(Aventura, locura, etc)
	 */
	private Tipo tipoAtracciones; // DE ATRACCION

	public ArrayList<Atraccion> atraccionesDePromo = new ArrayList<Atraccion>();

	/**
	 * Constructor
	 * 
	 * @param nombre,String  != null
	 * @param tipo,tipo.Tipo !=null
	 */
	public Promocion(String nombre, Tipo tipo) {
		this.nombre = nombre;
		this.tipoAtracciones = tipo;
	}

	// Metodos publicos

	/**
	 * Agrega una Atraccion las atracciones contenidas por la promocion
	 * 
	 * @param nuevaAtraccion, atraccion.Atraccion !=null
	 */
	public void agregarAtraccion(Atraccion nuevaAtraccion) {
		atraccionesDePromo.add(nuevaAtraccion);
	}

	public ArrayList<Atraccion> getAtracciones() {
		return this.atraccionesDePromo;
	}

	/**
	 * Calcula la duracion de la promocion(total) sumando los tiempos de duracion de
	 * las atraccines que la integran
	 */
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

	/**
	 * Getter abstracto, espera ser definido por las promociones
	 */
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
