package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import compilatore.CTarget;
import compilatore.EccezioneSemantica;
import compilatore.JasminException;
import compilatore.JasminTarget;
import edu.tum.cup2.generator.exceptions.GeneratorException;
import edu.tum.cup2.parser.exceptions.LRParserException;

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
			.severe(String.format("Errore di compilazione: %s",
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
		try {
			JasminTarget.compilaFile(getPercorsoFile(), false);
		} catch (Exception e) {
			Logger.getLogger("Itaco")
			.severe(String.format("Errore di compilazione: %s",
					e.toString()));
		}
		
		String nomeSistemaOperativo = System.getProperty("os.name").split(" ")[0];
		String comando = null;
		if (nomeSistemaOperativo.equals("Mac")) {
			comando = String
					.format("osascript -e 'tell app \"Terminal\" to do script \"cd %s; java %s\"'",
							directory, baseNomeFile);
		}
		if (nomeSistemaOperativo.equals("Windows")) {
			comando = String.format("cmd /c start cmd /k \"cd %s && java %s\"", directory, baseNomeFile);
		}
		if (nomeSistemaOperativo.equals("Linux")) {
		}
		if (comando != null) {
			try {
				System.out.println(comando);
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
