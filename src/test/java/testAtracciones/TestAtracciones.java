package testAtracciones;

import static org.junit.Assert.*;

import org.junit.Test;

import atraccion.Atraccion;
import tipos.Tipo;

public class TestAtracciones {

	@Test
	public void queCreoUnaAtracctionYveoSusCaracteristicas() {
		Atraccion calabozo = new Atraccion(1, "Calabozo", Tipo.TERROR, 10, 2.0, 4, "Escape del calabozo");

		String nombreEsperado = "Calabozo";
		String tipoEsperado = "TERROR";
		Integer costoEsperado = 10;
		Double duracionEsperado = 2.0;
		Integer cupoMaximoEsperado = 4;

		String nombreObtenido = calabozo.getNombre();
		String tipoObtenido = calabozo.getTipo().toString();
		Integer costoObtenido = calabozo.getCosto();
		Double duracionObtenido = calabozo.getDuracion();
		Integer cupoMaximoObtenido = calabozo.getCupoMaximo();

		assertEquals(nombreEsperado, nombreObtenido);
		assertEquals(tipoEsperado, tipoObtenido);
		assertEquals(costoEsperado, costoObtenido);
		assertEquals(duracionEsperado, duracionObtenido);
		assertEquals(cupoMaximoEsperado, cupoMaximoObtenido);
	}

	@Test
	public void queCreoUnaAtraccionYestaVacia() {
		Atraccion calabozo = new Atraccion(1, "Calabozo", Tipo.TERROR, 10, 2.0, 4, "Escape del calabozo");
		assertTrue(calabozo.estaVacio());
	}

	@Test
	public void queNoIngresenMasPersonasSiNoHayCupo() {
		Atraccion calabozo = new Atraccion(1, "Calabozo", Tipo.TERROR, 10, 2.0, 4, "Escape del calabozo");
		calabozo.entrarALaAtraccion();
		calabozo.entrarALaAtraccion();
		calabozo.entrarALaAtraccion();
		calabozo.entrarALaAtraccion();
		Boolean valorEsperado = false;
		Boolean valorObtenido = calabozo.entrarALaAtraccion();
		assertEquals(valorEsperado, valorObtenido);
	}

	@Test
	public void queNoSeLLenaLaAtraccionYquedaLugarDisponible() {
		Atraccion calabozo = new Atraccion(1, "Calabozo", Tipo.TERROR, 10, 2.0, 4, "Escape del calabozo");
		calabozo.entrarALaAtraccion();
		Boolean valorEsperado = true;
		Boolean valorObtenido = calabozo.quedaLugarDisponible();
		assertEquals(valorEsperado, valorObtenido);
	}

}
