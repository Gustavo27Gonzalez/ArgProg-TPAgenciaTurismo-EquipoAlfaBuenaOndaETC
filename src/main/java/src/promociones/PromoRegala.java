package promociones;

import java.util.Iterator;

import atraccion.Atraccion;
import ofertable.Ofertable;
import tipos.Tipo;

/**
 * Clase PromoAbsoluta integra un descuento en forma de Atraccion gratis
 * 
 * @author Daniel Paz
 *
 */
public class PromoRegala extends Promocion implements Ofertable {

	static Atraccion regalo;
	private String breveDescripcion;

	/**
	 * Cosntructor
	 * 
	 * @param nombre,String     !=null
	 * @param tipo,tipo.Tipo    !=null
	 * @param regalo,           atraccion.Atraccion !=null
	 * @param breveDescripcion, String !null
	 */
	public PromoRegala(String nombre, Tipo tipo, Atraccion regalo, String breveDescripcion) {
		super(nombre, tipo);
		PromoRegala.regalo = regalo;
		this.breveDescripcion = breveDescripcion;
	}

	/**
	 * Devuelve la atraccion regalada
	 * 
	 * @return atraccion.Atraccion regalo
	 */
	public static Atraccion getRegalo() {
		return regalo;
	}

	/**
	 * Calcula el costo como Promocion, pero le resta nominalmente el costo de la
	 * Atraccion regala
	 */
	@Override
	public Integer getCosto() {
		Integer costoTotal = 0;
		Iterator<Atraccion> iteradorCosto = getAtracciones().iterator();
		while (iteradorCosto.hasNext()) {
			Atraccion a = iteradorCosto.next();
			costoTotal += a.getCosto();
		}
		costoTotal -= regalo.getCosto();
		return costoTotal;
	}

	@Override
	public boolean esPromocionRegala() {
		return true;
	}

	@Override
	public Integer getLugaresDisponibles() {
		return null;
	}

	@Override
	public void setLugaresDisponibles(Integer lugaresDisponibles) {
	}

	@Override
	public String getBreveDescripcion() {
		return breveDescripcion;
	}

}
