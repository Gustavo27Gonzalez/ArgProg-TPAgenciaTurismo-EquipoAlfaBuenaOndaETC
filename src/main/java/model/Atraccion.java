package model;

import ofertable.Ofertable;

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

	private Integer id;
	private String nombre;
	private String tipo;
	private Integer costo;
	private Double duracion;
	private Integer cupoMaximo;
	private Integer lugaresDisponibles;
	private String breveDescripcion;

	public Atraccion(Integer id, String nombre, String tipo ,Integer costo, Double duracion, Integer cupo, String breveDescripcion) {
		this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.costo = costo;
		this.duracion = duracion;
		this.cupoMaximo = cupo;
		this.breveDescripcion = breveDescripcion;
		this.lugaresDisponibles = this.cupoMaximo;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public Integer getCosto() {
		return this.costo;
	}
	
	public void setCosto(Integer costo) {
		this.costo = costo;
	}
	
	@Override
	public Double getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Double duracion) {
		this.duracion = duracion;
	}
	
	public Integer getCupoMaximo() {
		return cupoMaximo;
	}
	
	public void setCupoMaximo(Integer cupo) {
		this.cupoMaximo = cupo;
	}
	
	@Override
	public String getBreveDescripcion() {
		return breveDescripcion;
	}
	
	public void setBreveDescripcion(String descripcion) {
		this.breveDescripcion = descripcion;
	}
	
	public Integer getLugaresDisponibles() {
		return lugaresDisponibles;
	}
	
	@Override
	public void setLugaresDisponibles(Integer lugaresDisponibles) {
		this.lugaresDisponibles = lugaresDisponibles;
	}
	
	@Override
	public boolean esPromocion() {
		return false;
	}
	
	@Override
	public String toString() {
		return "\r\n" + "Atraccion{ " + nombre + "," + "\r\n" + "costo=" + costo + "," + "\r\n" + "duracion=" + duracion
				+ "," + "\r\n" + breveDescripcion + '}';
	}
	
	public boolean estaVacio() {
		return (this.lugaresDisponibles == this.cupoMaximo);
	}

	public Boolean entrarALaAtraccion() {
		if (quedaLugarDisponible()) {
			this.lugaresDisponibles--;
			return true;
		}
		return false;
	}

	public Boolean quedaLugarDisponible() {
		return this.lugaresDisponibles > 0 ? true : false;
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
