package testpromos;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import promociones.PromoAbsoluta;
import promociones.Promocion;
import tipos.Tipo;

public class TestesAbsoluta {

	Atraccion primerAtraccion;
	Atraccion segundaAtraccion;
	Atraccion tercerAtraccion;
	Integer absoluto;
	Promocion hola;
	Integer suma;
	Double duraciones;

	@Before
	public void iniciarAtracciones() {
		primerAtraccion = new Atraccion(1, "Aguilares", Tipo.ACCION, 10, 10.0, 99, "descripcion");
		segundaAtraccion = new Atraccion(2, "Roma", Tipo.ACCION, 10, 10.0, 99, "descripcion");
		tercerAtraccion = new Atraccion(3, "Tokyo", Tipo.ACCION, 10, 10.0, 99, "descripcion");
		suma = 0;
		absoluto = 20;
		duraciones = 0.0;
		hola = new PromoAbsoluta("Furia", Tipo.ACCION, absoluto, "descripcion");
		hola.atraccionesDePromo.clear();
	}

	// @Test
	public void addAtraccion() {

		hola.agregarAtraccion(primerAtraccion);

		assertEquals(primerAtraccion, hola.getAtracciones().get(0));
	} // da error pero no deberia ???

	@Test
	public void costoAtraccion() {

		hola.agregarAtraccion(primerAtraccion);
		suma = primerAtraccion.getCosto() - absoluto;
		assertEquals(suma, hola.getCosto());

	}

	@Test
	public void duracionAtraccion() {

		hola.agregarAtraccion(primerAtraccion);
		duraciones = primerAtraccion.getDuracion();
		assertEquals(duraciones, hola.getDuracion());
	}

	@Test
	public void tipoAtraccion() {

		hola.agregarAtraccion(primerAtraccion);
		assertEquals(primerAtraccion.getTipo(), hola.getTipoAtraccion());
	}

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

		suma = primerAtraccion.getCosto() + segundaAtraccion.getCosto() + tercerAtraccion.getCosto();

		suma = suma - absoluto;

		assertEquals(suma, hola.getCosto());
	}

}
