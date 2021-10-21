package promociones;

import java.util.Iterator;

import atraccion.Atraccion;
import ofertable.Ofertable;
import tipos.Tipo;

/**
 * Clase PromoAbsoluta integra un tipo de descuento porcentual al precio total
 * 
 * @author Daniel Paz
 *
 */
public class PromoPorcentual extends Promocion implements Ofertable {
	Integer porcentaje = 0;
	private String breveDescripcion;

	/**
	 * Constructor
	 * 
	 * @param nombre,               String !=null
	 * @param tipo,                 tipo.Tipo !=null
	 * @param descuento,porcentual, Integer !=null
	 * @param breveDescripcion,     String !null
	 */
	public PromoPorcentual(String nombre, Tipo tipo, Integer porcentaje, String breveDescripcion) {
		super(nombre, tipo);
		this.porcentaje = porcentaje;
		this.breveDescripcion = breveDescripcion;
	}

	/**
	 * Calcula el costo como Promocion, pero le descuenta porcentualmente el @param
	 * descuento
	 */
	@Override
	public Integer getCosto() {
		Integer costoTotal = 0;
		Iterator<Atraccion> iteradorCosto = getAtracciones().iterator();
		while (iteradorCosto.hasNext()) {
			Atraccion a = iteradorCosto.next();
			costoTotal += a.getCosto();
		}
		costoTotal = (int) costoTotal * (100 - porcentaje) / 100;
		return costoTotal;
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
