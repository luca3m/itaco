package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

import compilatore.JasminTarget;

public class IntegrationTest {

	private void testFile(String path, String[] datiIngresso, String[] outputPrevisto) {
		File fd = new File(path);
		try {
			JasminTarget.compilaFile(path, false);
		} catch (Exception e) {
			fail(String.format("Compilazione %s fallita\n:Errore:%s", fd.getName(), e));
		}
		ProcessBuilder pb = new ProcessBuilder("java", " -cp", "testFiles", fd.getName().split("\\.")[0]);
		try {
			Process process = pb.start();
			PrintStream pout = new PrintStream(process.getOutputStream());
			for ( String dato : datiIngresso) {
				pout.println(dato);
			}
			Scanner pin = new Scanner(process.getInputStream());
			for (String output : outputPrevisto) {
				assertEquals(output, pin.next());
			}
		} catch (IOException e) {
			fail(String.format("Errore nell'avviare il processo:\n%s", e));
		}
		
	}
	
	@Test
	public void testCalcoloArea() {
		String[] datiIngresso = { "3", "4"};
		String[] outputPrevisto = { "12"};
		testFile("testFiles" + File.separator + "area.ita", datiIngresso, outputPrevisto);
	}

}
