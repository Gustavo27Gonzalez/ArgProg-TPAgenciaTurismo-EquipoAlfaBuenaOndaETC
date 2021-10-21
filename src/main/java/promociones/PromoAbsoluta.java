package promociones;

import atraccion.Atraccion;
import ofertable.Ofertable;
import tipos.Tipo;

/**
 * Clase PromoAbsoluta integra un tipo de descuento neto
 * 
 * @author Daniel Paz
 *
 */
public class PromoAbsoluta extends Promocion implements Ofertable {

	private Integer descuento = 0;
	private String breveDescripcion;
	Integer costoTotal = 0;

	/**
	 * Constructor
	 * 
	 * @param nombre,           String !=null
	 * @param tipo,             tipo.Tipo !=null
	 * @param descuento,        neto, int !=null
	 * @param breveDescripcion, String !null
	 */
	public PromoAbsoluta(String nombre, Tipo tipo, int descuento, String breveDescripcion) {
		super(nombre, tipo);
		this.descuento = descuento;
		this.breveDescripcion = breveDescripcion;
	}

	/**
	 * Calcula el costo como Promocion, pero le resta nominalmente el @param
	 * descuento
	 */
	@Override
	public Integer getCosto() {
		costoTotal = 0;
		for (Atraccion atracciones : atraccionesDePromo) {
			this.costoTotal += atracciones.getCosto();
		}

		return this.costoTotal - this.descuento;
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
