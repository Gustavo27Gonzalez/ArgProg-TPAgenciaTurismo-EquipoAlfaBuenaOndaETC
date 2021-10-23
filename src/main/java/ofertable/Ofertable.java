package ofertable;

import tipos.Tipo;

/**
 * Interfaz publica que permite la utilizacion homogenea de algunos metodos
 * especificos en las clases, Promocion y Atraccion.
 * 
 * @author Franco Arabales
 *
 */
public interface Ofertable {

	public String getNombre();

	public Double getDuracion();

	public String getTipo();

	public Integer getCosto();

	public Integer getCupoMaximo();

	public Integer getLugaresDisponibles();

	public boolean esPromocion();

	void setLugaresDisponibles(Integer lugaresDisponibles);

	public String getBreveDescripcion();

}