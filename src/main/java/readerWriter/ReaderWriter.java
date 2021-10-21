package readerWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import atraccion.Atraccion;
import promociones.PromoAbsoluta;
import promociones.PromoPorcentual;
import promociones.PromoRegala;
import promociones.Promocion;
import tipos.Tipo;
import usuario.Usuario;

/**
 * Clase ReaderWriter lee archivos de entrada y genera archivos de salida
 * 
 * @author Tomas Andrada
 *
 */
public class ReaderWriter {
	static FileReader fr = null;
	static BufferedReader br = null;
	public static ArrayList<Atraccion> listaAtracciones = new ArrayList<Atraccion>();
	public static ArrayList<Promocion> listaPromociones = new ArrayList<Promocion>();
	public static ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();

	/**
	 * Lee el archivo de entrada, carga los constructores de las clases: Usuario
	 * Atraccion y Promocion. Y los agrega a sus respectivas listas.
	 */
	public static void construirListas() {

		try {
			fr = new FileReader("Entrada/ArchivoDeEntrada.txt");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		br = new BufferedReader(fr);
		String linea;

		try {
			while ((linea = br.readLine()) != null) {
				{
					String[] datos = linea.split(",");

					if (datos[0].matches("ACCION|BANQUETES|LOCURA|TERROR")) {

						Tipo tipo = Tipo.valueOf(datos[0]);
						String nombre = datos[1];
						Integer costo = Integer.parseInt(datos[2]);
						double duracion = Double.parseDouble(datos[3]);
						Integer cupo = Integer.parseInt(datos[4]);
						String breveDescripcion = datos[5];

						listaAtracciones.add(new Atraccion(nombre, tipo, costo, duracion, cupo, breveDescripcion));
					}

					if (datos[0].matches("PORCENTUAL")) {
						Tipo tipo = Tipo.valueOf(datos[1]);
						String nombre = datos[2];
						int porcentaje = Integer.parseInt(datos[3]);
						String breveDescripcion = datos[6];

						listaPromociones.add(new PromoPorcentual(nombre, tipo, porcentaje, breveDescripcion));
						listaPromociones.get(listaPromociones.size() - 1)
								.agregarAtraccion(ReaderWriter.buscarAtraccion(datos[4]));
						listaPromociones.get(listaPromociones.size() - 1)
								.agregarAtraccion(ReaderWriter.buscarAtraccion(datos[5]));

					}

					if (datos[0].matches("ABSOLUTA")) {

						Tipo tipo = Tipo.valueOf(datos[1]);
						String nombre = datos[2];
						int descuento = Integer.parseInt(datos[3]);
						String breveDescripcion = datos[6];

						listaPromociones.add(new PromoAbsoluta(nombre, tipo, descuento, breveDescripcion));
						listaPromociones.get(listaPromociones.size() - 1)
								.agregarAtraccion(ReaderWriter.buscarAtraccion(datos[4]));
						listaPromociones.get(listaPromociones.size() - 1)
								.agregarAtraccion(ReaderWriter.buscarAtraccion(datos[5]));
					}

					if (datos[0].matches("AXB")) {

						Tipo tipo = Tipo.valueOf(datos[1]);
						String nombre = datos[2];
						Atraccion regalo = ReaderWriter.buscarAtraccion(datos[3]);
						String breveDescripcion = datos[6];

						listaPromociones.add(new PromoRegala(nombre, tipo, regalo, breveDescripcion));
						listaPromociones.get(listaPromociones.size() - 1)
								.agregarAtraccion(ReaderWriter.buscarAtraccion(datos[4]));
						listaPromociones.get(listaPromociones.size() - 1)
								.agregarAtraccion(ReaderWriter.buscarAtraccion(datos[5]));

					}

					if (datos[0].matches("USUARIO")) {

						String nombre = datos[1];
						int dineroDisponible = Integer.parseInt(datos[2]);
						Tipo preferencia = Tipo.valueOf(datos[3]);
						int tiempo = Integer.parseInt(datos[4]);

						listaUsuarios.add(new Usuario(nombre, dineroDisponible, preferencia, tiempo));
					}
				}
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	/**
	 * Busca una atraccion por su nombre en un string
	 * 
	 * @param nombre, string donde queremos buscar la atraccion
	 * @return si la encuentra: atraccion.Atraccion atraccion. Si no la encuentra:
	 *         null;
	 */
	private static Atraccion buscarAtraccion(String nombre) {

		for (Atraccion atraccion : listaAtracciones) {
			if (atraccion.getNombre().equals(nombre)) {
				return atraccion;
			}
		}
		return null;
	}

	/**
	 * Crea un archivos de salida donde resume los echo por los usuarios en la
	 * ejecucion del programa
	 * 
	 * @throws IOException
	 */
	public static void crearArchivoDeSalida() throws IOException {
		for (Usuario usuario : listaUsuarios) {
			PrintWriter salida = new PrintWriter(new FileWriter(new File("Salida/" + usuario.getNombre() + ".txt")));
			salida.println(usuario.getNombre());
			salida.println("Dinero: " + usuario.getDineroInicial());
			salida.println("Preferencia: " + usuario.getPreferencia());
			salida.println("Tiempo " + usuario.getTiempoInicial());

			salida.println("El usuario compro: ");
			for (Atraccion atraccion : usuario.atraccionesCompradas) {
				salida.println(atraccion.getNombre());
			}

			for (Promocion promocion : usuario.promocionesCompradas) {
				salida.println(promocion.getNombre());
			}

			int dinero = usuario.getDineroInicial() - usuario.getDineroDisponible();
			double tiempo = usuario.getTiempoInicial() - usuario.getTiempo();
			salida.println("Dinero a pagar: " + dinero);
			salida.println("Tiempo a invertir: " + tiempo);
			salida.close();
		}
	}
}
