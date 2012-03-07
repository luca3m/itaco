package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import compilatore.JasminTarget;

public class IntegrationTest {

	private static final String cartellaFileTest = "testFiles" + File.separator;
	
	private void testFile(String nomeFileSorgente, String[] datiIngresso, String[] outputPrevisto) {
		try {
			JasminTarget.compilaFile(cartellaFileTest + nomeFileSorgente + ".ita", false);
		} catch (Exception e) {
			fail(String.format("Compilazione %s fallita\n:Errore:%s", nomeFileSorgente, e));
		}
		ProcessBuilder pb = new ProcessBuilder("java", nomeFileSorgente);
		Map<String, String> environment = pb.environment();
		environment.put("CLASSPATH", cartellaFileTest);
		try {
			Process process = pb.start();
			PrintStream pout = new PrintStream(process.getOutputStream());
			for ( String dato : datiIngresso) {
				pout.println(dato);
			}
			pout.flush();
			Scanner pin = new Scanner(process.getInputStream());
			for (String output : outputPrevisto) {
				try {
					String outputAttuale = pin.next();
					assertEquals(output, outputAttuale);
				} catch (java.util.NoSuchElementException ex) {
					Scanner errorScanner = new Scanner(process.getErrorStream());
					while (errorScanner.hasNextLine()) {
						System.out.println(errorScanner.nextLine());
					}
					fail("Output del processo non conforme");
				}
			}
			process.destroy();
		} catch (IOException e) {
			fail(String.format("Errore nell'avviare il processo:\n%s", e));
		}
		
	}
	
	@Test
	public void testCalcoloArea() {
		String[] datiIngresso = { "4", "3" };
		String[] outputPrevisto = { "12"};
		testFile("area", datiIngresso, outputPrevisto);
	}

}
