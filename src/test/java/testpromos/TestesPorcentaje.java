package testpromos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import promociones.PromoPorcentual;
import promociones.Promocion;
import tipos.Tipo;

/**
 * 
 * @author Daniel Paz
 *
 */
public class TestesPorcentaje {

	Atraccion primerAtraccion;
	Atraccion segundaAtraccion;
	Atraccion tercerAtraccion;
	Integer porcentaje = 20;
	Promocion hola;

	@Before
	public void iniciarAtracciones() {
		primerAtraccion = new Atraccion("Aguilares", Tipo.ACCION, 10, 10.0, 10, "descripcion");
		segundaAtraccion = new Atraccion("Roma", Tipo.ACCION, 100, 100.0, 10, "descripcion");
		tercerAtraccion = new Atraccion("Tokyo", Tipo.ACCION, 1000, 1000.0, 10, "descripcion");
		hola = new PromoPorcentual("Furia", Tipo.ACCION, porcentaje, "descripcion");
		hola.atraccionesDePromo.clear();
	}

	@Test
	public void agregaUnaAtraccionAlaPromo() {

		hola.agregarAtraccion(primerAtraccion);
		assertEquals(primerAtraccion, hola.getAtracciones().get(0));
	} // hay que hacer compare??

	@Test
	public void costoPromocionConDescuento() {

		hola.agregarAtraccion(primerAtraccion);
		Integer nino = (int) (primerAtraccion.getCosto() * ((100 - porcentaje) / 100.0));
		assertEquals(nino, hola.getCosto());
	}

	@Test
	public void duracionPromocionDeUnaAtraccion() {

		hola.agregarAtraccion(primerAtraccion);
		assertEquals(primerAtraccion.getDuracion(), hola.getDuracion());
	}

	@Test
	public void tipoDeAtraccionEsIgualAlTipoDePromocion() {

		hola.agregarAtraccion(primerAtraccion);
		assertEquals(primerAtraccion.getTipo(), hola.getTipoAtraccion());
	} // falta getTipoAtraccion() en Atracciones

	@Test
	public void unaAtraccionNoEsPromocionYunaPromocionSiEsPromocion() {

		hola.agregarAtraccion(primerAtraccion);
		assertFalse(primerAtraccion.esPromocion());
		assertTrue(hola.esPromocion());
	}

	@Test
	public void crearPromocionDeTresAtraccionesYVerificarCosto() {

		hola.agregarAtraccion(primerAtraccion);
		hola.agregarAtraccion(segundaAtraccion);
		hola.agregarAtraccion(tercerAtraccion);
		Integer suma = primerAtraccion.getCosto() + segundaAtraccion.getCosto() + tercerAtraccion.getCosto();
		suma = (int) (suma * ((100 - porcentaje) / 100.0));
		assertEquals(suma, hola.getCosto());
	}

}