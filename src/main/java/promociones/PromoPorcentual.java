package promociones;

import java.util.Iterator;

import atraccion.Atraccion;
import ofertable.Ofertable;
import tipos.Tipo;

public class PromoPorcentual extends Promocion implements Ofertable {
	Integer porcentaje = 0;
	private String breveDescripcion;

	public PromoPorcentual(String nombre, Tipo tipo, Integer porcentaje, String breveDescripcion) {
		super(nombre, tipo);
		this.porcentaje = porcentaje;
		this.breveDescripcion = breveDescripcion;
	}

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

	public Integer getPorcentaje() {
		return porcentaje;
	}

	@Override
	public Tipo getTipo() {
		return this.getTipoAtraccion();
	}

}
