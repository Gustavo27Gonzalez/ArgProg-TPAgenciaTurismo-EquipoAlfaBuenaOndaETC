package testItinerario;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import atraccion.Atraccion;
import maquinaDeSugerencias.MaquinaDeSugerencias;
import promociones.PromoAbsoluta;
import promociones.Promocion;
import tipos.Tipo;
import usuario.Usuario;

/**
 * 
 * @author Daniel Paz
 *
 */
public class testesItinerario {
	
	Atraccion primerAtraccion;
	Atraccion segundaAtraccion;
	Atraccion tercerAtraccion;
	Integer absoluto = 15;
	Promocion promo;
	Usuario u1;
	Usuario u2;
	Usuario u3;
	
	@Before
	public void iniciador(){
		primerAtraccion = new Atraccion("Aguilares", Tipo.ACCION, 10, 10.0, 99, "descripcion");
		segundaAtraccion = new Atraccion("Roma", Tipo.ACCION, 10, 10.0, 99, "descripcion");
		tercerAtraccion = new Atraccion("Tokyo", Tipo.ACCION, 10, 10.0, 99, "descripcion");
		
		promo = new PromoAbsoluta("Furia", Tipo.ACCION, absoluto, "descripcion");
		
		u1 = new Usuario("J", 50, Tipo.ACCION, 60.0, 1);
		u2 = new Usuario("C", 70, Tipo.ACCION, 40.0, 2);
		u3 = new Usuario("M", 100, Tipo.ACCION, 30.0, 3);
	}

	@Test
	public void compraTodo() {
		if (MaquinaDeSugerencias.puedeComprar(u1,primerAtraccion)) {
			MaquinaDeSugerencias.ofertar(primerAtraccion,u1);
		}
		promo.agregarAtraccion(segundaAtraccion);
		promo.agregarAtraccion(tercerAtraccion);
		if (MaquinaDeSugerencias.puedeComprar(u1,promo)) {
			MaquinaDeSugerencias.ofertar(promo,u1);
		}
		
		ArrayList<Atraccion> atracciones = new ArrayList<Atraccion>();
		atracciones.add(primerAtraccion);
		atracciones.add(segundaAtraccion);
		atracciones.add(tercerAtraccion);
		
		
		assertEquals(atracciones,u1.comprasDeUsuario);
		// el test necesita una entrada, si se aceptan las sugerencias el test da correcto
	}
	

}
