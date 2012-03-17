package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Logger;

import compilatore.CTarget;
import compilatore.JasminTarget;
import compilatore.RubyTarget;

/**
 * Classe che rappresenta una istanza di un file sorgente .ita e la gestisce in
 * compilazione e gestione come file
 * 
 * @author Alessandro Luca Rosario
 * 
 */
public class FileItaco {

	Logger itacoLogger = Logger.getLogger("Itaco");
	private String baseNomeFile = null;
	private String directory = null;

	/**
	 * Costruttore senza file specificato: crea una istanza della classe per
	 * accogliere un nuovo sorgente .ita
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
	 * Controlla se al file .ita è stato già assegnato un nome, ad esempio nel
	 * caso sia stato aperto da file
	 * 
	 * @return vero se il nome è impostato
	 */
	public boolean fileSalvato() {
		return baseNomeFile != null;
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
			itacoLogger.info("File salvato, percorso file:" + directory
					+ File.separator + baseNomeFile + ".ita");
			return true;
		} catch (IOException e) {
			itacoLogger.severe(String.format(
					"Errore nel salvare il file: %s\n", e));
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
		salvaFile(contenuto);
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
		if (!fileSalvato()) {
			itacoLogger.severe("Per compilare, prima bisogna salvare il file");
			return false;
		}
		String percorsoFile = directory + File.separator + baseNomeFile
				+ ".ita";
		try {
			switch (linguaggio) {
			case C:
				CTarget.compilaFile(percorsoFile);
				itacoLogger.info("Esportazione in C completata, percorso file:"
						+ directory + File.separator + baseNomeFile + ".c");
				break;
			case JASMIN:
				JasminTarget.compilaFile(percorsoFile, true);
				itacoLogger
						.info("Esportazione in Jasmin completata, percorso file:"
								+ directory
								+ File.separator
								+ baseNomeFile
								+ ".j");
				break;
			case RUBY:
				RubyTarget.compilaFile(percorsoFile);
				itacoLogger
						.info("Esportazione in Ruby completata, percorso file:"
								+ directory + File.separator + baseNomeFile
								+ ".rb");
				break;
			case CLASS:
				JasminTarget.compilaFile(percorsoFile, false);
				itacoLogger
						.info("Esportazione in Java .class completata, percorso file:"
								+ directory
								+ File.separator
								+ baseNomeFile
								+ ".class");
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
	 * @return esito dell'esecuzione
	 * @throws InterruptedException
	 */
	public boolean esegui() {
		if (!fileSalvato()) {
			itacoLogger.severe("Per eseguire, prima bisogna salvare il file");
			return false;
		}
		// Compilo il codice
		try {
			JasminTarget.compilaFile(getPercorsoFile(), false);
			itacoLogger.info("Compilazione completata");
		} catch (Exception e) {
			itacoLogger.severe(String.format("Errore di compilazione: %s",
					e.toString()));
			return false;
		}
		itacoLogger.info("Avvio esecuzione...");
		try {
			String nomeSistemaOperativo = System.getProperty("os.name").split(
					" ")[0];
			if (nomeSistemaOperativo.equals("Mac")) {
				String[] comando = {
						"osascript",
						"-e",
						String.format(
								"tell app \"Terminal\" to do script \"cd %s; java %s\"",
								directory, baseNomeFile) };
				Runtime.getRuntime().exec(comando);
			}
			if (nomeSistemaOperativo.equals("Windows")) {
				String comando = String.format(
						"cmd /c start cmd /k \"cd %s && java %s\"", directory,
						baseNomeFile);
				Runtime.getRuntime().exec(comando);
			}
			if (nomeSistemaOperativo.equals("Linux")) {
				String comando[] = new String[5];
				comando[0] = "gnome-terminal";
				comando[1] = "-x";
				comando[2] = "bash";
				comando[3] = "-c";
				comando[4] = String
						.format("cd %s ; java %s ; echo; echo Premi invio per chiudere.. ; read",
								directory, baseNomeFile);
				Runtime.getRuntime().exec(comando);
				System.out.print(comando);
			}
			return true;
		} catch (IOException e) {
			itacoLogger.severe(String.format("Errore di esecuzione: %s",
					e.toString()));
			return false;
		}
	}

	private String getPercorsoFile() {
		return directory + File.separator + baseNomeFile + ".ita";
	}

	/**
	 * Metodo che restituisce un sorgente da file. Il path del file è impostato
	 * nella classe poiché ogni classe rappresenta un codice sorgente
	 * 
	 * @return stringa contenente il sorgente aperto, stringa vuota altrimenti
	 */
	public String getContenuto() {
		try {
			java.util.Scanner scanner = new java.util.Scanner(
					new FileInputStream(getPercorsoFile()));
			StringBuilder sb = new StringBuilder();
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine());
				sb.append("\n");
			}
			return sb.toString();
		} catch (Exception e) {
			itacoLogger.severe(String.format(
					"Impossibile aprire il file %s:%s", getPercorsoFile(), e));
		}
		return "";
	}
}
