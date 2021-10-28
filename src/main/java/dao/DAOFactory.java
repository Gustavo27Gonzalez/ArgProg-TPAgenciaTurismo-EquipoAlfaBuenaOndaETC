package dao;

public class DAOFactory {

	public static UsuarioDAO getUsuarioDAO(){
		return new UsuarioDAOImpl();
	}
	
	public static ItinerarioDAO getItinerarioDAO( ) {
		return new ItinerarioDAO();
	}
	
	public static AtraccionDAO getAtraccionDAO() {
		return new AtraccionDAO();
	}
	
	public static PromocionDAO getPromociosDAO() {
		return new PromocionDAOimpl();
		}
	
}