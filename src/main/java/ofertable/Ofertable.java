package ofertable;

import tipos.Tipo;

public interface Ofertable {

	public String getNombre();

	public Double getDuracion();

	public Tipo getTipo();

	public Integer getCosto();

	public Integer getCupoMaximo();

	public Integer getLugaresDisponibles();

	public boolean esPromocion();

	void setLugaresDisponibles(Integer lugaresDisponibles);

	public String getBreveDescripcion();

}