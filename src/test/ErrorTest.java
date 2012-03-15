package test;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import compilatore.JasminException;
import compilatore.JasminTarget;

import edu.tum.cup2.generator.exceptions.GeneratorException;
import edu.tum.cup2.parser.exceptions.LRParserException;

public class ErrorTest {

	private static final String cartellaFileTest = "testFiles" + File.separator;

	/**
	 * Questo metodo generico, fa il test di corretta esecuzione di un programma
	 * scritto in itaco
	 * 
	 * @param nomeFileSorgente
	 *            nome del file sorgente inserito nella cartella testFiles/,
	 *            senza estensione .ita
	 * @throws JasminException
	 * @throws IOException
	 * @throws LRParserException
	 * @throws GeneratorException
	 * @throws FileNotFoundException
	 */
	private void testFallimentoCompilazione(String nomeFileSorgente) {
		// Compilo il file .ita
		try {
			JasminTarget.compilaFile(cartellaFileTest + nomeFileSorgente
					+ ".ita", false);
		} catch (Exception ex) {
			return;
		}

		// Elimino il file class generato in modo da non creare spazzatura
		File classFile = new File(cartellaFileTest + nomeFileSorgente
				+ ".class");
		classFile.delete();
		fail(String.format("Compilazione di %s andata a buon fine\n",
				nomeFileSorgente));
	}

	@Test
	public void testDoppiaDefinizioneFunzione() {
		testFallimentoCompilazione("doppiaDefinizioneFunzione");
	}

	@Test
	public void testDoppiaDefinizioneVariabile() {
		testFallimentoCompilazione("doppiaDefinizioneVariabile");
	}

	@Test
	public void testUsoVariabileNonAssegnata() {
		testFallimentoCompilazione("usoVariabileNonAssegnata");
	}

	@Test
	public void testAssegnamentoValoreDiUnaFunzioneVoid() {
		testFallimentoCompilazione("assegnamentoValoreDiUnaFunzioneVoid");
	}
}
