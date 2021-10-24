package com.proyecto.TurismoMuyMuyLejano;

import java.io.IOException;
import java.sql.SQLException;

import dao.DAOFactory;
import dao.ItinerarioDAO;
import dao.UsuarioDAO;
import maquinaDeSugerencias.MaquinaDeSugerencias;


public class App {

	public static void main(String[] args) throws IOException, SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.createArray();
		MaquinaDeSugerencias.iterarListas();
		ItinerarioDAO itinerarioDAO = DAOFactory.getItinerarioDAO();
		itinerarioDAO.generarItinerario();
		usuarioDAO.actualizarDBUsuarios();
		
	}

}
