package promociones;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import atraccion.Atraccion;
import ofertable.Ofertable;
import tipos.Tipo;

public class PromoRegala extends Promocion implements Ofertable {

	static Atraccion regalo;
	private String breveDescripcion;

	public PromoRegala(String nombre, Tipo tipo, Atraccion regalo, String breveDescripcion) {
		super(nombre, tipo);
		PromoRegala.regalo = regalo;
		this.breveDescripcion = breveDescripcion;
	}

	public static Atraccion getRegalo() {
		return regalo;
	}

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
		ArrayList<Integer> lugares = new ArrayList<Integer>();
		for (Atraccion atracciones : atraccionesDePromo){
			lugares.add(atracciones.getLugaresDisponibles());
		}
		Collections.sort(lugares);
		return lugares.indexOf(0);
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
