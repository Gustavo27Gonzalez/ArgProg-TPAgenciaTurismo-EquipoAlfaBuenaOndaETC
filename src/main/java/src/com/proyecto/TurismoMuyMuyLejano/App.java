package com.proyecto.TurismoMuyMuyLejano;

import java.io.IOException;

import maquinaDeSugerencias.MaquinaDeSugerencias;
import readerWriter.ReaderWriter;


public class App {

	public static void main(String[] args) throws IOException {
		ReaderWriter.construirListas();
		MaquinaDeSugerencias.iterarListas();
		ReaderWriter.crearArchivoDeSalida();
		
	}

}
