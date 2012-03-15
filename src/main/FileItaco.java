package main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import compilatore.CTarget;
import compilatore.JasminTarget;

public class FileItaco {
	
	private String baseNomeFile = null;
	private String directory = null;

	/**
	 * Costruttore senza file specificato
	 */
	public FileItaco() {
		directory = System.getenv("USER.DIR");
	}

	/**
	 * Costruttore con file specificato
	 * 
	 * @param nomeFile
	 *            nome del file da aprire
	 */
	public FileItaco(String nomeFile) {
		File fileDescriptor = new File(nomeFile);
		directory = fileDescriptor.getParent();
		baseNomeFile = fileDescriptor.getName().split("\\.")[0];
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
		String percorsoFile = directory + File.separator + baseNomeFile
				+ ".ita";
		try {
			switch (linguaggio) {
				case C:
					CTarget.compilaFile(percorsoFile);
					break;
				case JASMIN:
					JasminTarget.compilaFile(percorsoFile, true);
					break;
			}
		} catch (Exception e) {
			Logger.getLogger("Itaco")
			.severe(String.format("Errore di esecuzione: %s",
					e.toString()));
		}
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
		// Compilo il codice
		JasminTarget.compilaFile(percorsoFile, true);
		
		String nomeSistemaOperativo = System.getProperty("os.name");
		String comando = null;
		if (nomeSistemaOperativo.equals("Mac OS X")) {
			comando = String
					.format("osascript -e 'tell app \"Terminal\" to do script \"cd %s; java %s\"",
							directory, baseNomeFile);
		}
		if (nomeSistemaOperativo.equals("Windows")) {

		}
		if (nomeSistemaOperativo.equals("Linux")) {
		}
		if (comando != null) {
			try {
				Runtime.getRuntime().exec(comando);
			} catch (IOException e) {
				Logger.getLogger("Itaco")
						.severe(String.format("Errore di esecuzione: %s",
								e.toString()));
			}
			return true;
		} else
			return false;
	}
	
	private String getPercorsoFile() {
		return directory + File.separator + baseNomeFile
				+ ".ita";
	}
}
