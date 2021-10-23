package com.proyecto.TurismoMuyMuyLejano;

import java.io.IOException;

import dao.DAOFactory;
import dao.UsuarioDAO;
import maquinaDeSugerencias.MaquinaDeSugerencias;


public class App {

	public static void main(String[] args) throws IOException {
		UsuarioDAO usuarioDAO = DAOFactory.getUsuarioDAO();
		usuarioDAO.createArray();
		MaquinaDeSugerencias.iterarListas();
		
	}

}
