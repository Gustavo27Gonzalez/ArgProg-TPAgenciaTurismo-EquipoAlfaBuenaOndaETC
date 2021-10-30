package testpromos;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import promociones.PromoRegala;
import promociones.Promocion;
import tipos.Tipo;

public class TestesRegala {
	Atraccion primerAtraccion;
	Atraccion segundaAtraccion;
	Atraccion tercerAtraccion;
	Integer absoluto = 50;
	Atraccion regalo;
	Promocion hola;

	@Before
	public void iniciarAtracciones() {
		primerAtraccion = new Atraccion(1, "Aguilares", Tipo.ACCION, 10, 10.0, 10, "descripcion");
		segundaAtraccion = new Atraccion(2, "Roma", Tipo.ACCION, 100, 100.0, 10, "descripcion");
		tercerAtraccion = new Atraccion(3, "Tokyo", Tipo.ACCION, 1000, 1000.0, 10, "descripcion");

		regalo = tercerAtraccion;

		hola = new PromoRegala("Furia", Tipo.ACCION, regalo, "descripcion");
		hola.atraccionesDePromo.clear();

	}

	@Test
	public void addAtraccion() {

		hola.agregarAtraccion(primerAtraccion);
		assertEquals(primerAtraccion, hola.getAtracciones().get(0));
	} // hay que hacer compare??

	@Test
	public void costoAtraccion() {

		hola.agregarAtraccion(primerAtraccion);
		Integer nino = (int) (primerAtraccion.getCosto() - regalo.getCosto());
		assertEquals(nino, hola.getCosto());
	}

	@Test
	public void duracionAtraccion() {

		hola.agregarAtraccion(primerAtraccion);
		assertEquals(primerAtraccion.getDuracion(), hola.getDuracion());
	}

	@Test
	public void tipoAtraccion() {

		hola.agregarAtraccion(primerAtraccion);
		assertEquals(primerAtraccion.getTipo(), hola.getTipoAtraccion());
	} // falta getTipoAtraccion() en Atracciones

	@Test
	public void esPromocion() {

		hola.agregarAtraccion(primerAtraccion);
		assertFalse(primerAtraccion.esPromocion());
		assertTrue(hola.esPromocion());
	}

	@Test
	public void addMasAtacciones() {

		hola.agregarAtraccion(primerAtraccion);
		hola.agregarAtraccion(segundaAtraccion);
		hola.agregarAtraccion(tercerAtraccion);
		Integer suma = primerAtraccion.getCosto() + segundaAtraccion.getCosto() + tercerAtraccion.getCosto();
		suma = (int) (suma - regalo.getCosto());
		assertEquals(suma, hola.getCosto());
	}
}
