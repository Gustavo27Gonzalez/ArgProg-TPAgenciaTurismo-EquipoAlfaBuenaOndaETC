package consola;

import java.util.ArrayList;

import atraccion.Atraccion;
import ofertable.Ofertable;
import promociones.PromoRegala;
import promociones.Promocion;
import usuario.Usuario;

/**
 * Clase Atraccion
 * 
 * Representa un lugar visitable por los turistas
 * 
 * @author Cesar De Maurizio, Franco Arabales
 * @version final
 *
 */
public class Consola {

	static ArrayList<Atraccion> atraccionesTemp = new ArrayList<Atraccion>();

	public static final String ENTRADA_INCORRECTA = "<<<ENTRADA INCORRECTA>>>";
	static final String SUBRAYADO = "------";
	static final String SALUDO_INICIAL = SUBRAYADO + "¡ BIENVENIDO A LA OFICINA DE TURISMO DE MUY MUY LEJANO !"
			+ SUBRAYADO;
	static final String TENEMOS_OFERTAS = "-----------------¡TENEMOS JUGOSAS OFERTAS PARA VOS!-----------------";
	static final String MENSAJE_FINAL = SUBRAYADO + "MUCHAS GRACIAS, YA NO QUEDAN USUARIOS POR VER" + SUBRAYADO;
	static final String COSTO = "Costo" + SUBRAYADO + " $";
	static final String COSTO_TOTAL = "Costo total" + SUBRAYADO + " $";
	static final String TIEMPO = "Tiempo" + SUBRAYADO;
	static final String TIPO = "Tipo" + SUBRAYADO;
	public static final String INGRESE = "Ingrese S para comprar, N para continuar:";
	public static final String GRACIAS = "¡Gracias, se registró la compra de: ";
	static final String AGRADECIMIENTO = "¡MUCHAS GRACIAS A TODOS, ESPEREMOS QUE DISFRUTEN SU ESTADÍA!";

	// Metodos publicos

	/**
	 * Imprime en consola texto con un formato especifico
	 * 
	 * @param ofertable, promocion o atraccion
	 */
	public static void mensajeOfertar(Ofertable ofertable) {

		System.out.println("\r\n¿DESEA COMPRAR " + ofertable.getNombre().toUpperCase() + "?\r\n");
		System.out.println(ofertable.getBreveDescripcion() + "\r\n");
		if (ofertable.esPromocion()) {
			System.out.println("Atracciones de promocion:\r\n");
			if (((Promocion) ofertable).esPromocionRegala()) {
				for (Atraccion atracciones : ((Promocion) ofertable).atraccionesDePromo) {
					System.out.println(atracciones.getNombre().toUpperCase() + " // " + COSTO + atracciones.getCosto());
				}
				System.out.println(
						PromoRegala.getRegalo().getNombre().toUpperCase() + " // " + "Costo" + SUBRAYADO + " GRATIS");
			} else {
				for (Atraccion atracciones : ((Promocion) ofertable).atraccionesDePromo) {
					System.out.println(atracciones.getNombre().toUpperCase() + " // " + COSTO + atracciones.getCosto());
				}
			}
			System.out.println("\r\n" + COSTO_TOTAL + ofertable.getCosto());
			System.out.println(TIEMPO + ofertable.getDuracion());
			System.out.println(TIPO + ofertable.getTipo() + "\r\n\r\n");
			System.out.println(INGRESE);
		} else {
			System.out.println("\r\n" + COSTO + ofertable.getCosto());
			System.out.println(TIEMPO + ofertable.getDuracion());
			System.out.println(TIPO + ofertable.getTipo() + "\r\n\r\n");
			System.out.println(INGRESE);
		}
	}

	/**
	 * Imprime en consola texto con un formato especifico
	 * 
	 * @param usuario
	 */
	public static void primerMensaje(Usuario usuario) {

		System.out.println("\r\n\r\n" + SALUDO_INICIAL + "\r\n\r\n\r\n");
		System.out.println(usuario.getNombre().toUpperCase() + " te esperan muchas aventuras!\r\n\r\n\r\n");
		System.out.println(TENEMOS_OFERTAS);
	}

	/**
	 * Imprime en consola texto con un formato especifico
	 */
	public static void mensajeFinal() {
		System.out.println("\r\n" + AGRADECIMIENTO + "\r\n");
	}

	/**
	 * Imprime un resumen de las iteraciones del usuario, detalla sus compras,
	 * calcula su dinero restante, y su tiempo restante.
	 * 
	 * @param usuario
	 */
	public static void resumenUsuario(Usuario usuario) {
		System.out.println(usuario.getNombre() + " gracias por por sus compras, "
				+ "a continuación le detallamos su itinerario: \r\n" + "\r\n" + usuario.comprasDeUsuario.toString()
				+ "\r\n" + "\r\nTiempo total de su itinerario: " + (usuario.getTiempoInicial() - usuario.getTiempo())
				+ " hs" + "\r\n" + "\r\nCosto total de su itinerario: "
				+ (usuario.getDineroInicial() - usuario.getDineroDisponible()) + " monedas" + "\r\n"
				+ "\r\nGRACIAS POR ELEGIR MUY MUY LEJANO Y ESPERAMOS QUE DISFRUTE DE SU VISITA");
	}
}