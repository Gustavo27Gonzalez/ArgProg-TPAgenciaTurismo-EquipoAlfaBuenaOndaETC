package com.proyecto.TurismoMuyMuyLejano;

import java.io.IOException;
import java.sql.SQLException;

import dao.*;
import maquinaDeSugerencias.MaquinaDeSugerencias;
import promociones.Promocion;
import tipos.Tipo;


public class App {

	public static void main(String[] args) throws IOException, SQLException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.createArray();
		AtraccionDAO atraccionDAO = DAOFactory.getAtraccionDAO();
		atraccionDAO.createArray();
		PromocionDAO promocionDAO = DAOFactory.getPromociosDAO();
		promocionDAO.findAll();
		MaquinaDeSugerencias.iterarListas();
	}

}
