package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Logger;

import compilatore.CTarget;
import compilatore.JasminTarget;
import compilatore.RubyTarget;

public class FileItaco {

	Logger itacoLogger = Logger.getLogger("Itaco");
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
		setPercorsoFile(nomeFile);
	}

	private void setPercorsoFile(String nomeFile) {
		File fileDescriptor = new File(nomeFile);
		directory = fileDescriptor.getParent();
		baseNomeFile = fileDescriptor.getName().split("\\.")[0];
	}

	/**
	 * Salva il file .ita in un file con nome di default
	 * 
	 * @return esito del salvataggio
	 */
	public boolean salvaFile(String contenuto) {
		try {
			FileOutputStream file1 = new FileOutputStream(getPercorsoFile());
			PrintStream Output = new PrintStream(file1);
			Output.print(contenuto);
			file1.close();
			return true;
		} catch (IOException e) {
			itacoLogger.severe(String.format("Errore nel salvare il file: %s\n", e));
			return false;
		}
	}

	/**
	 * Salva il file .ita in un file dal nome specificato
	 * 
	 * @param nomeFile
	 *            nome del file in cui salvare
	 * @return esito del salvataggio
	 */
	public boolean salvaFile(String contenuto, String nomeFile) {
		setPercorsoFile(nomeFile);

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
			case RUBY:
				RubyTarget.compilaFile(percorsoFile);
				break;
			case CLASS:
				JasminTarget.compilaFile(percorsoFile, false);
				break;
			}
		} catch (Exception e) {
			itacoLogger.severe(String.format("Errore di compilazione: %s",
					e.toString()));
		}
		return false;
	}

	/**
	 * Esegui il programma .ita
	 * 
	 * @param itacoLogger
	 *            TODO
	 * @param testo
	 *            sorgente del programma da eseguire
	 * 
	 * @return esito dell'esecuzione
	 */
	public boolean esegui() {
		// Compilo il codice
		try {
			JasminTarget.compilaFile(getPercorsoFile(), false);
		} catch (Exception e) {
			itacoLogger.severe(String.format("Errore di compilazione: %s",
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
			comando = String.format("cmd /c start cmd /k \"cd %s && java %s\"",
					directory, baseNomeFile);
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
		return directory + File.separator + baseNomeFile + ".ita";
	}
}
