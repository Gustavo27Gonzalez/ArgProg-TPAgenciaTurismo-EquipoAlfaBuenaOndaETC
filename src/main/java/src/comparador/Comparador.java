package comparador;

import java.util.Comparator;

import ofertable.Ofertable;
import tipos.Tipo;

/**
 * Clase Comparador
 * 
 * Utiliza compare para poder filtrar atracciones y promociones segun un
 * criterio especifico
 * 
 * @author Franco Arabales
 * @version final
 *
 */
public class Comparador implements Comparator<Ofertable> {
	Tipo atraccionFavorita;

	/**
	 * Constructor
	 * 
	 * @param atraccionFavorita, es la preferencia del usuario.
	 */
	public Comparador(Tipo atraccionFavorita) {
		this.atraccionFavorita = atraccionFavorita;
	}

	/**
	 * Compara 2 ofertables con el siguiente orden:
	 * <ul>
	 * <li>1: Si es preferencia del usuario</li>
	 * <li>2: Si es promocion</li>
	 * <li>3: Por costo(mas alto)</li>
	 * <li>4: Por tiempo(mas alto)</li>
	 * </ul>
	 */
	@Override
	public int compare(Ofertable o1, Ofertable o2) {
		if (o1.getTipo() == atraccionFavorita && o2.getTipo() != atraccionFavorita) {
			return -1;
		} else if (o1.getTipo() != atraccionFavorita && o2.getTipo() == atraccionFavorita) {
			return 1;
		}
		if (o1.esPromocion() && !o2.esPromocion()) {
			return -1;
		} else if (!o1.esPromocion() && o2.esPromocion()) {
			return 1;
		}
		if (o1.getCosto() > o2.getCosto()) {
			return -1;
		} else if (o1.getCosto() < o2.getCosto()) {
			return 1;
		}
		return (int) (o2.getDuracion() - o1.getDuracion());
	}

}
