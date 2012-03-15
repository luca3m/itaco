package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;

import compilatore.JasminTarget;

public class JasminTest {

	private static final String cartellaFileTest = "testFiles" + File.separator;

	/**
	 * Questo metodo generico, fa il test di corretta esecuzione di un programma
	 * scritto in itaco
	 * 
	 * @param nomeFileSorgente
	 *            nome del file sorgente inserito nella cartella testFiles/,
	 *            senza estensione .ita
	 * @param datiIngresso
	 *            array di stringhe da inserire come dati in ingresso al
	 *            programma
	 * @param outputPrevisto
	 *            array di stringhe che compongono l'output del programma,
	 *            l'output viene letto e confrontato con Scanner.next()
	 */
	private void testFile(String nomeFileSorgente, String[] datiIngresso,
			String[] outputPrevisto) {
		// Compilo il file .ita
		try {
			JasminTarget.compilaFile(cartellaFileTest + nomeFileSorgente
					+ ".ita", false);
		} catch (Exception e) {
			fail(String.format("Compilazione %s fallita\nErrore:%s",
					nomeFileSorgente, e));
		}

		// Creo il processo java dove il programma verrà eseguito
		ProcessBuilder pb = new ProcessBuilder("java", nomeFileSorgente);
		// Imposto la variabile di ambiente in modo che riconosce le classi
		// compilate
		// all'interno della cartella testFiles/
		Map<String, String> environment = pb.environment();
		environment.put("CLASSPATH", cartellaFileTest);

		try {
			Process process = pb.start();

			// Invio al processo tutti i dati in ingresso, separati da un
			// ritorno a capo
			PrintStream pout = new PrintStream(process.getOutputStream());
			for (String dato : datiIngresso) {
				pout.println(dato);
			}
			// Il flush è importante perché non lo fa in automatico
			// (il test di bloccava)
			pout.flush();

			// Verifico che l'output previsto coincida con quello
			// attuale
			Scanner pin = new Scanner(process.getInputStream());
			for (String output : outputPrevisto) {
				try {
					String outputAttuale = pin.next();
					assertEquals(output, outputAttuale);
				} catch (java.util.NoSuchElementException ex) {
					// Nel caso l'output nel processo non è conforme
					// a quello previsto, allora stampo il contenuto
					// dell'error stream per poter meglio fare il debug
					Scanner errorScanner = new Scanner(process.getErrorStream());
					while (errorScanner.hasNextLine()) {
						System.out.println(errorScanner.nextLine());
					}
					fail("Output del processo non conforme");
				}
			}

			process.destroy();

			// Elimino il file class generato in modo da non creare spazzatura
			File classFile = new File(cartellaFileTest + nomeFileSorgente
					+ ".class");
			classFile.delete();
		} catch (IOException e) {
			fail(String.format("Errore nell'avviare il processo:\n%s", e));
		}

	}

	@Test
	public void testCalcoloArea() {
		String[] datiIngresso = { "4", "3" };
		String[] outputPrevisto = { "12" };
		testFile("area", datiIngresso, outputPrevisto);
		String[] datiIngresso2 = { "90", "10" };
		String[] outputPrevisto2 = { "900" };
		testFile("area", datiIngresso2, outputPrevisto2);
	}

	@Test
	public void testScriviStringa() {
		String[] outputPrevisto = { "ciao" };
		testFile("stringoutput", new String[0], outputPrevisto);
	}

	@Test
	public void testEspressioneComplessa() {
		String[] datiIngresso = { "4" };
		String[] outputPrevisto = { "37" };
		testFile("espressioneComplessa", datiIngresso, outputPrevisto);
		String[] datiIngresso2 = { "45" };
		String[] outputPrevisto2 = { "365" };
		testFile("espressioneComplessa", datiIngresso2, outputPrevisto2);
	}

	@Test
	public void testVettori() {
		String[] datiIngresso = { "4", "3", "2", "1" };
		String[] outputPrevisto = { "1", "2", "3", "4" };
		testFile("vettori", datiIngresso, outputPrevisto);
	}

	@Test
	public void testCondizionaleSe() {
		String[] datiIngresso = { "10" };
		String[] outputPrevisto = { "indovinato" };
		testFile("condizionaleSe", datiIngresso, outputPrevisto);
		String[] datiIngresso2 = { "1" };
		String[] outputPrevisto2 = { "sbagliato" };
		testFile("condizionaleSe", datiIngresso2, outputPrevisto2);
		String[] datiIngresso3 = { "5" };
		String[] outputPrevisto3 = { "sbagliato" };
		testFile("condizionaleSe", datiIngresso3, outputPrevisto3);
	}

	@Test
	public void testFinche() {
		String[] datiIngresso = { "10" };
		String[] outputPrevisto = { "0", "1", "2", "3", "4", "5", "6", "7",
				"8", "9" };
		testFile("finche", datiIngresso, outputPrevisto);
	}

	@Test
	public void testFunzioneRicorsiva() {
		String[] datiIngresso = { "6" };
		String[] outputPrevisto = { "720" };
		testFile("funzioneRicorsiva", datiIngresso, outputPrevisto);
		String[] datiIngresso2 = { "2" };
		String[] outputPrevisto2 = { "2" };
		testFile("funzioneRicorsiva", datiIngresso2, outputPrevisto2);
		String[] datiIngresso3 = { "0" };
		String[] outputPrevisto3 = { "1" };
		testFile("funzioneRicorsiva", datiIngresso3, outputPrevisto3);
	}

	@Test
	public void testFunzioneDiVettori() {
		String[] datiIngresso = { "1", "2", "3", "4", };
		String[] outputPrevisto = { "6", "7", "8", "9" };
		testFile("funzioneVettori", datiIngresso, outputPrevisto);
	}

	@Test
	public void testFunzioneVoid() {
		String[] datiIngresso = {};
		String[] outputPrevisto = { "Ciao" };
		testFile("funzioneVoid", datiIngresso, outputPrevisto);
	}

	@Test
	public void testBubbleSort() {
		String[] datiIngresso = { "10", "3", "29", };
		String[] outputPrevisto = { "3", "10", "29" };
		testFile("bubbleSort", datiIngresso, outputPrevisto);
	}
}
