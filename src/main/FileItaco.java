package main;

import java.io.IOException;
import java.util.logging.Logger;

public class FileItaco {

	private String baseNomeFile;
	private String directory;

	/**
	 * Costruttore senza file specificato
	 */
	public FileItaco() {
	}

	/**
	 * Costruttore con file specificato
	 * 
	 * @param nomeFile
	 *            nome del file da aprire
	 */
	public FileItaco(String nomeFile) {
		
	}

	/**
	 * Salva il file .ita in un file con nome di default
	 * 
	 * @return esito del salvataggio
	 */
	public boolean salvaFile() {
		return false;
	}

	/**
	 * Salva il file .ita in un file dal nome specificato
	 * 
	 * @param nomeFile
	 *            nome del file in cui salvare
	 * @return esito del salvataggio
	 */
	public boolean salvaFile(String nomeFile) {
		return false;
	}

	/**
	 * Compila il file nel linguaggio target selezionato
	 * 
	 * @param linguaggio
	 *            linguaggio target
	 * @return esito compilazione
	 */
	public boolean compila(ElencoLinguaggi linguaggio) {
		return false;
	}

	/**
	 * Esegui il programma .ita
	 * 
	 * @param testo
	 *            sorgente del programma da eseguire
	 * @return esito dell'esecuzione
	 */
	public boolean esegui() {
		String nomeSistemaOperativo = System.getProperty("os.name");
		String comando = null;
		if (nomeSistemaOperativo.equals("Mac OS X")) {
			comando = String
					.format("osascript -e 'tell app \"Terminal\" to do script \"java %s\"",
							baseNomeFile);
		}
		if (nomeSistemaOperativo.equals("Windows")) {

		}
		if (nomeSistemaOperativo.equals("Linux")) {
		}
		if (comando != null) {
			try {
				Runtime.getRuntime().exec(comando);
			} catch (IOException e) {
				Logger.getLogger("Itaco").severe(String.format("Errore di esecuzione: %s", e.toString()));
			}
			return true;
		} else
			return false;
	}
}
