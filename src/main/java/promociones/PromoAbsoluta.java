package promociones;

import atraccion.Atraccion;
import ofertable.Ofertable;
import tipos.Tipo;

public class PromoAbsoluta extends Promocion implements Ofertable {

	private Integer descuento = 0;
	private String breveDescripcion;
	Integer costoTotal = 0;

	public PromoAbsoluta(String nombre, Tipo tipo, int descuento, String breveDescripcion) {
		super(nombre, tipo);
		this.descuento = descuento;
		this.breveDescripcion = breveDescripcion;
	}

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

	@Override
	public Tipo getTipo() {
		return this.getTipoAtraccion();
	}

}
