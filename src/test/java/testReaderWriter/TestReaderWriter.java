package testReaderWriter;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Test;

import readerWriter.ReaderWriter;
import tipos.Tipo;
import usuario.Usuario;

/**
 * 
 * @author Tomas Andrada
 *
 */
public class TestReaderWriter {

	@Test
	public void test() throws IOException {

		ReaderWriter.listaUsuarios.clear();
		Usuario usuarioTest = new Usuario("Garfio", 50, Tipo.ACCION, 7);
		ReaderWriter.listaUsuarios.add(usuarioTest);
		ReaderWriter.crearArchivoDeSalida();
		ReaderWriter.listaUsuarios.add(usuarioTest);
		String fileName1 = "Salida/Garfio.txt";
		String fileName2 = "ArchivosTest/UsuarioTest.txt";
		byte[] file1Bytes = Files.readAllBytes(Paths.get(fileName1));
		byte[] file2Bytes = Files.readAllBytes(Paths.get(fileName2));
		String file1 = new String(file1Bytes, StandardCharsets.UTF_8);
		String file2 = new String(file2Bytes, StandardCharsets.UTF_8);

		assertEquals("The content in the strings should match", file1, file2);

	}

	@After
	public void deleteFile() {
		File file = new File("Garfio.txt");
		file.delete();
	}

}
