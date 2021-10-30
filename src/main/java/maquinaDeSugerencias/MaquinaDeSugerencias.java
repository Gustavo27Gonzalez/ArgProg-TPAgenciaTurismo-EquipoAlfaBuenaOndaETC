package maquinaDeSugerencias;

import java.util.Collections;
import java.util.Scanner;

import atraccion.Atraccion;
import comparador.Comparador;
import consola.Consola;
import dao.AtraccionDAO;
import dao.PromocionDAOimpl;
import dao.UsuarioDAOImpl;
import ofertable.Ofertable;
import promociones.Promocion;
import usuario.Usuario;


public class MaquinaDeSugerencias {

	static Scanner entrada = new Scanner(System.in);


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


	public static boolean puedeComprar(Usuario usuario, Ofertable ofertable) { 
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


	public static void ordenarListas(Usuario usuario) {

		Collections.sort(PromocionDAOimpl.listaPromociones, new Comparador(usuario.getPreferencia()));
		Collections.sort(AtraccionDAO.listaAtracciones, new Comparador(usuario.getPreferencia()));

	}

	public static void iterarListas() {

		for (Usuario usuario : UsuarioDAOImpl.listaUsuarios) {
			Consola.primerMensaje(usuario);
			ordenarListas(usuario);

			for (Promocion promocion : PromocionDAOimpl.listaPromociones) {

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


	public static void ofertar(Ofertable ofertable, Usuario usuario) {

		Consola.mensajeOfertar(ofertable);

		String respuesta;
		do {
			respuesta = entrada.nextLine();

			if (respuesta.equalsIgnoreCase("N"))
				System.out.println();

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