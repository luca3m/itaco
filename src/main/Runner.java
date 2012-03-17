package main;

import java.io.File;

import compilatore.CTarget;
import compilatore.JasminTarget;
import compilatore.RubyTarget;

/**
 * Classe di avvio per eseguire la compilazione
 * 
 * @author Alessandro Luca Rosario
 * 
 */
public class Runner {

	/**
	 * Main per la generazione del codice
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		String file = "." + File.separator + "prova.ita";
		FileItaco fi = new FileItaco(file);
		fi.esegui();
	}

	/**
	 * Genera il codice C da un file .ita
	 * 
	 * @param nomeFile
	 *            nome del file .ita da tradurre
	 * @throws Exception
	 */
	public static void generaCodiceC(String nomeFile) throws Exception {
		if (nomeFile == null)
			nomeFile = "prova.ita";
		CTarget.compilaFile(nomeFile);
		System.out.println("Generato codice C");
	}

	/**
	 * Genera il codice Ruby da un file .ita
	 * 
	 * @param nomeFile
	 *            nome del file .ita da tradurre
	 * @throws Exception
	 */
	public static void generaCodiceRuby(String nomeFile) throws Exception {
		if (nomeFile == null)
			nomeFile = "prova.ita";
		RubyTarget.compilaFile(nomeFile);
		System.out.println("Generato codice Ruby");
	}

	/**
	 * Genera il codice Jasmin da un file .ita
	 * 
	 * @param nomeFile
	 *            nome del file .ita da tradurre
	 * @throws Exception
	 */
	public static void generaCodiceJasmin(String nomeFile) throws Exception {
		if (nomeFile == null)
			nomeFile = "prova.ita";
		JasminTarget.compilaFile(nomeFile, true);
		System.out.println("Generato codice Jasmin");
	}

	/**
	 * Genera il codice .class da un file .ita
	 * 
	 * @param nomeFile
	 *            nome del file .ita da compilare
	 * @throws Exception
	 */
	public static void generaByteCodeJava(String nomeFile) throws Exception {
		if (nomeFile == null)
			nomeFile = "prova.ita";
		JasminTarget.compilaFile(nomeFile, false);
		System.out.println("Generato codice .class");
	}
}
