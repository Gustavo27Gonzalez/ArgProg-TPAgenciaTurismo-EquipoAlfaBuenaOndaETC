package testOrdenes;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import comparador.Comparador;
import promociones.PromoAbsoluta;
import promociones.Promocion;
import tipos.Tipo;

/**
 * 
 * @author Daniel Paz
 *
 */
public class TestesOrdenes {

	Atraccion primerAtraccion;
	Atraccion segundaAtraccion;
	Atraccion tercerAtraccion;
	Atraccion cuartaAtraccion;
	Promocion unicaPromo;
	ArrayList<Atraccion> listaAtracciones;
	Tipo preferencia;

	@Before
	public void crearLista() {
		listaAtracciones = new ArrayList<Atraccion>();
		preferencia = Tipo.ACCION;
	}

	@Test
	public void testOrdenUno() {

		primerAtraccion = new Atraccion(1, "Uno", Tipo.ACCION, 5, 2.0, 10, "descripcion");
		segundaAtraccion = new Atraccion(2, "Dos", Tipo.ACCION, 6, 3.0, 11, "descripcion");
		tercerAtraccion = new Atraccion(3, "Tres", Tipo.ACCION, 4, 5.0, 12, "descripcion");
		listaAtracciones.add(primerAtraccion);
		listaAtracciones.add(segundaAtraccion);
		listaAtracciones.add(tercerAtraccion);

		Collections.sort(listaAtracciones, new Comparador(preferencia));

		ArrayList<Atraccion> listaOrdenada = new ArrayList<Atraccion>();
		listaOrdenada.add(segundaAtraccion);
		// mayor costo
		listaOrdenada.add(primerAtraccion);
		// segundo mayor costo
		listaOrdenada.add(tercerAtraccion);
		// ultima

		assertEquals(listaOrdenada, listaAtracciones);

	}

	@Test
	public void testOrdenDos() {

		primerAtraccion = new Atraccion(1, "Uno", Tipo.ACCION, 6, 2.0, 10, "descripcion");
		segundaAtraccion = new Atraccion(2, "Dos", Tipo.ACCION, 6, 3.0, 11, "descripcion");
		tercerAtraccion = new Atraccion(3, "Tres", Tipo.ACCION, 6, 5.0, 12, "descripcion");
		listaAtracciones.add(primerAtraccion);
		listaAtracciones.add(segundaAtraccion);
		listaAtracciones.add(tercerAtraccion);

		Collections.sort(listaAtracciones, new Comparador(preferencia));

		ArrayList<Atraccion> listaOrdenada = new ArrayList<Atraccion>();
		listaOrdenada.add(tercerAtraccion);
		// mayor tiempo
		listaOrdenada.add(segundaAtraccion);
		// segundo mayor tiempo
		listaOrdenada.add(primerAtraccion);
		// ultima

		assertEquals(listaOrdenada, listaAtracciones);

	}

	@Test
	public void testOrdenTres() {

		primerAtraccion = new Atraccion(1, "Uno", Tipo.ACCION, 6, 4.0, 10, "descripcion");
		segundaAtraccion = new Atraccion(2, "Dos", Tipo.BANQUETES, 7, 6.0, 11, "descripcion");
		tercerAtraccion = new Atraccion(3, "Tres", Tipo.ACCION, 6, 5.0, 12, "descripcion");
		cuartaAtraccion = new Atraccion(4, "Cuatro", Tipo.LOCURA, 8, 2.0, 8, "descripcion");
		unicaPromo = new PromoAbsoluta("Promo", Tipo.ACCION, 10, "descripcion");
		listaAtracciones.add(primerAtraccion);
		listaAtracciones.add(segundaAtraccion);
		listaAtracciones.add(tercerAtraccion);
		listaAtracciones.add(cuartaAtraccion);

		Collections.sort(listaAtracciones, new Comparador(preferencia));

		ArrayList<Atraccion> listaOrdenada = new ArrayList<Atraccion>();
		listaOrdenada.add(tercerAtraccion);
		// mismo tipo, mayor costo, mayor tiempo
		listaOrdenada.add(primerAtraccion);
		// mismo tipo, mismo costo, siguiente tiempo
		listaOrdenada.add(cuartaAtraccion);
		// mayor precio de otro tipo
		listaOrdenada.add(segundaAtraccion);
		// menor precio de otro tipo

		assertEquals(listaOrdenada, listaAtracciones);

	}

}
