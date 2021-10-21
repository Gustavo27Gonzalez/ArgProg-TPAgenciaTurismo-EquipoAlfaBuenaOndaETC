package atraccion;

import ofertable.Ofertable;
import tipos.Tipo;

/**
 * Clase Atraccion
 * 
 * Representa un lugar visitable por los turistas
 * 
 * @author Gustavo Gonzalez
 * @version final
 *
 */
public class Atraccion implements Ofertable {

	/**
	 * Nombre de la atraccion.
	 */
	private String nombre;
	/**
	 * Tipo de la atraccion : Aventura, accion, etc.
	 */
	private Tipo tipo;
	/**
	 * Costo de entrada.
	 */
	private Integer costo;
	/**
	 * Tiempo que toma recorrer la atraccion.
	 */
	private Double duracion;
	/**
	 * Cupo de usuarios inicial.
	 */
	private Integer cupoMaximo;
	/**
	 * Cupo de usuarios variable(contabiliza los ingresos).
	 */
	private Integer lugaresDisponibles;
	/**
	 * Descripcion para mostrar en consola.
	 */
	private String breveDescripcion;

	// Metodos publicos

	/**
	 * Constructor
	 * 
	 * @param nombre            de la atraccion String != null
	 * @param tipo              de la atraccion, enum != null
	 * @param costo             de entrada, Integer != < 0, != null
	 * @param duracion          de recorrida Double != < 1, != null
	 * @param cupo              de personas, Integer != <1, !null
	 * @param breveDescripcion, String != null
	 */

	public Atraccion(String nombre, Tipo tipo, Integer costo, Double duracion, Integer cupo, String breveDescripcion) {
		this.nombre = nombre;
		this.tipo = tipo;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoMaximo = cupo;
		this.lugaresDisponibles = this.cupoMaximo;
		this.breveDescripcion = breveDescripcion;
	}

	/**
	 * Comprueba que no hayan ingresado usuarios a la atraccion
	 * 
	 * @return boolean
	 */
	public boolean estaVacio() {
		return (this.lugaresDisponibles == this.cupoMaximo);
	}

	/**
	 * Comprueba que haya lugar disponible y resta 1 cupo
	 * 
	 * @return boolean
	 */
	public Boolean entrarALaAtraccion() {
		if (quedaLugarDisponible()) {
			this.lugaresDisponibles--;
			return true;
		}
		return false;
	}

	/**
	 * Comprueba que se pueda ingresar a la atraccion
	 * 
	 * @return boolean
	 */
	public Boolean quedaLugarDisponible() {
		if (this.lugaresDisponibles > 0) {
			return true;
		} else {
			return false;
		}
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Integer getCosto() {
		return this.costo;
	}

	public Integer getCupoMaximo() {
		return cupoMaximo;
	}

	@Override
	public void setLugaresDisponibles(Integer lugaresDisponibles) {
		this.lugaresDisponibles = lugaresDisponibles;
	}

	public Integer getLugaresDisponibles() {
		return lugaresDisponibles;
	}

	public Tipo getTipo() {
		return tipo;
	}

	@Override
	public Double getDuracion() {
		return duracion;
	}

	@Override
	public boolean esPromocion() {
		return false;
	}

	@Override
	public String getBreveDescripcion() {
		return breveDescripcion;
	}

	@Override
	public String toString() {
		return "\r\n" + "Atraccion{ " + nombre + "," + "\r\n" + "costo=" + costo + "," + "\r\n" + "duracion=" + duracion
				+ "," + "\r\n" + breveDescripcion + '}';
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((breveDescripcion == null) ? 0 : breveDescripcion.hashCode());
		result = prime * result + ((costo == null) ? 0 : costo.hashCode());
		result = prime * result + ((cupoMaximo == null) ? 0 : cupoMaximo.hashCode());
		result = prime * result + ((duracion == null) ? 0 : duracion.hashCode());
		result = prime * result + ((lugaresDisponibles == null) ? 0 : lugaresDisponibles.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Atraccion other = (Atraccion) obj;
		if (breveDescripcion == null) {
			if (other.breveDescripcion != null)
				return false;
		} else if (!breveDescripcion.equals(other.breveDescripcion))
			return false;
		if (costo == null) {
			if (other.costo != null)
				return false;
		} else if (!costo.equals(other.costo))
			return false;
		if (cupoMaximo == null) {
			if (other.cupoMaximo != null)
				return false;
		} else if (!cupoMaximo.equals(other.cupoMaximo))
			return false;
		if (duracion == null) {
			if (other.duracion != null)
				return false;
		} else if (!duracion.equals(other.duracion))
			return false;
		if (lugaresDisponibles == null) {
			if (other.lugaresDisponibles != null)
				return false;
		} else if (!lugaresDisponibles.equals(other.lugaresDisponibles))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

}
