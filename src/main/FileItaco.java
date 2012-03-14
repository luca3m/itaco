package main;

import javax.swing.JTextPane;

public class FileItaco {

	/**
	 * Costruttore senza file specificato
	 */
	public FileItaco(){
	}
	
	/**
	 * Costruttore con file specificato
	 * @param nomeFile	nome del file da aprire
	 */
	public FileItaco(String nomeFile){
	}
	
	/**
	 * Salva il file .ita in un file con nome di default
	 * @return	esito del salvataggio
	 */
	public boolean salvaFile(){
		return false;
	}
	
	/**
	 * Salva il file .ita in un file dal nome specificato
	 * @param nomeFile	nome del file in cui salvare
	 * @return	esito del salvataggio
	 */
	public boolean salvaFile(String nomeFile){
		return false;
	}
	
	/**
	 * Compila il file nel linguaggio target selezionato
	 * @param linguaggio	linguaggio target
	 * @return	esito compilazione
	 */
	public boolean compila(ElencoLinguaggi linguaggio){
		return false;
	}
	
	/**
	 * Esegui il programma .ita
	 * @param testo	sorgente del programma da eseguire
	 * @return	esito dell'esecuzione
	 */
	public boolean esegui(JTextPane testo){
		return false;
	}
}
