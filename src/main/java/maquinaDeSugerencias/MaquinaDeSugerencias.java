package maquinaDeSugerencias;

import java.util.Collections;
import java.util.Scanner;

import atraccion.Atraccion;
import comparador.Comparador;
import consola.Consola;
import dao.AtraccionDAO;
import dao.UsuarioDAOImpl;
import ofertable.Ofertable;
import promociones.Promocion;
import usuario.Usuario;

/**
 * Clase MaquinaDeSugerencias
 * 
 * Es la clase encargada de ordenar y iterar las listas de usuarios, promociones
 * y atracciones.
 * 
 * @author Cesar De Maurizio, Tomas Andrada
 * @version final
 *
 */
public class MaquinaDeSugerencias {

	static Scanner entrada = new Scanner(System.in);

	// Metodos publicos

	/**
	 * Verifica si un usuario compro, o no, una promocion o atraccion
	 * 
	 * @param usuario,   usuario del que queremos verificar una compra
	 * @param ofertable, promocion o atraccion que preguntamos
	 * @return boolean
	 */
	public static boolean yaLaCompro(Usuario usuario, Ofertable ofertable) {
		boolean laCompro = false;
		if (ofertable.esPromocion()) {
			if (usuario.comprasDeUsuario.contains(ofertable)) {
				laCompro = true;
			}
		} else {
			if (usuario.comprasDeUsuario.contains(ofertable)) {
				laCompro = true;
			}
		}
		return laCompro;
	}

	/**
	 * 
	 * Comprueba si el usuario tiene dinero y tiempo para adquirir un ofertable
	 * determinado, ademas se fija si yaLaCompro()
	 * 
	 * @param usuario
	 * @param ofertable
	 * @return
	 */
	public static boolean puedeComprar(Usuario usuario, Ofertable ofertable) { // se fija si el usuario tiene dinero,
																				// tiempo
		// y usa yaLaCompro()
		boolean puede = true;

		if (usuario.getDineroDisponible() < ofertable.getCosto()) {
			puede = false;
		} else if (usuario.getTiempo() < ofertable.getDuracion()) {
			puede = false;
		} else if (yaLaCompro(usuario, ofertable)) {
			puede = false;

		}
		return puede;
	}

	/**
	 * Mediante el uso de la clase Comparador(), ordena para los atributos y
	 * preferencias de un usuario, las atracciones y promociones que esten en el
	 * catalogo
	 * 
	 * @param usuario
	 */
	public static void ordenarListas(Usuario usuario) {

		Collections.sort(PromocionDAO.listaPromociones, new Comparador(usuario.getPreferencia()));
		Collections.sort(AtraccionDAO.listaAtracciones, new Comparador(usuario.getPreferencia()));

	}

	/**
	 * Para cada usuario, itera las listas ordenadas, usa ordenarListas(),
	 * puedeComprar() y ofertar()
	 */
	public static void iterarListas() {

		for (Usuario usuario : UsuarioDAOImpl.listaUsuarios) {
			Consola.primerMensaje(usuario);
			ordenarListas(usuario);

			for (Promocion promocion : PromocionDAO.listaPromociones) {

				if (MaquinaDeSugerencias.puedeComprar(usuario, promocion)) {

					ofertar(promocion, usuario);
				}
			}
			for (Atraccion atraccion : AtraccionDAO.listaAtracciones) {

				if (MaquinaDeSugerencias.puedeComprar(usuario, atraccion)) {
					ofertar(atraccion, usuario);
				}
			}
			Consola.resumenUsuario(usuario);
		}
		Consola.mensajeFinal();
	}

	/**
	 * Oferta al usuario el ofertable, y le pide que ingrese su respuesta de compra
	 * por teclado, si es afirmativa, lo compra y muestra el saldo y tiempo
	 * restante. Si es negativa, continua.
	 * 
	 * @param ofertable
	 * @param usuario
	 */
	public static void ofertar(Ofertable ofertable, Usuario usuario) {

		Consola.mensajeOfertar(ofertable);

		String respuesta;
		do {
			respuesta = entrada.nextLine();

			if (respuesta.equalsIgnoreCase("N"))
				System.out.println();
			// No se realizo ninguna compra.
			else if (respuesta.equalsIgnoreCase("S")) {
				usuario.comprar(ofertable);
				System.out.println(Consola.GRACIAS + ofertable.getNombre());
				System.out.println(("\n[Monedas restantes: " + usuario.getDineroDisponible() + "] [Horas restante: "
						+ usuario.getTiempo() + "]" + "\n"));
			} else
				System.out.println(Consola.ENTRADA_INCORRECTA + "\r\n" + "\r\n" + Consola.INGRESE);
		} while (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N"));
	}

}