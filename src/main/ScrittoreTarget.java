package main;

import istruzioni.espressioni.B;
import istruzioni.espressioni.Costante;
import istruzioni.espressioni.E;
import istruzioni.espressioni.F;
import istruzioni.espressioni.T;

import java.util.HashMap;
import java.util.Map;

public abstract class ScrittoreTarget {
	private Map<String, Integer> tabellaSimboli = new HashMap<String, Integer>();
	/**
	 * Inizio del contatore per identificare le variabili
	 */
	private static final int START_ID = 1;

	private static int contatoreVariabili = START_ID;
	
	public boolean registraVariabile(String nome) {
		if(tabellaSimboli.containsKey(nome)){
			return false;
		}
		tabellaSimboli.put(nome, contatoreVariabili++);
		return true;
	}

	protected int idVariabile(String nome) {
		if (tabellaSimboli.containsKey(nome)) {
			return tabellaSimboli.get(nome);
		} else
			return -1;
	}

	protected int numeroVariabili() {
		return tabellaSimboli.size();
	}
	protected String labelInizioBlocco(String nome) {
		return "";
	}

	protected String labelFineBlocco(String nome) {
		return "";
	}

	/**
	 * Scrivi l'istruzione per fare la somma nel linguaggio target
	 * @param addendo1 TODO
	 * @param addendo2 TODO
	 */
	public abstract void somma(B addendo1, T addendo2);

	/**
	 * Scrivi l'istruzione per caricare il valore della variabile nello stack
	 * 
	 * @param nome
	 *            nome della variabile
	 */
	public abstract void caricaVariabile(String nome);

	/**
	 * Scrivi l'istruzione per caricare una costante nello stack
	 * 
	 * @param costante
	 */
	public abstract void costante(int costante);

	/**
	 * Scrivi l'istruzione per fare la sottrazione nel linguaggio target
	 * @param minuendo TODO
	 * @param sottraendo TODO
	 */
	public abstract void sottrazione(B minuendo, T sottraendo);

	/**
	 * Scrivi l'istruzione per fare la moltiplicazione nel linguaggio target
	 * @param fattore1 TODO
	 * @param fattore2 TODO
	 */
	public abstract void prodotto(T fattore1, F fattore2);

	/**
	 * Scrivi l'istruzione per fare la divisione nel linguaggio target
	 * @param dividendo TODO
	 * @param divisore TODO
	 */
	public abstract void divisione(T dividendo, F divisore);

	/**
	 * Scrivi l'istruzione per operatore maggiore >
	 * @param parteSinistra TODO
	 * @param parteDestra TODO
	 */
	public abstract void maggiore(E parteSinistra, B parteDestra);

	/**
	 * Scrivi l'istruzione per operatore minore <
	 * @param parteSinistra TODO
	 * @param parteDestra TODO
	 */
	public abstract void minore(E parteSinistra, B parteDestra);

	/**
	 * Scrivi l'istruzione per operatore uguaglianza =
	 * @param parteSinistra TODO
	 * @param parteDestra TODO
	 */
	public abstract void uguaglianza(E parteSinistra, B parteDestra);

	/**
	 * Scrivi istruzione per stampare a schermo
	 * @param espressione TODO
	 */
	public abstract void stampa(E espressione);

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 * @param identificatore TODO
	 */
	public abstract void leggi(String identificatore);

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 * @param identificatore TODO
	 */
	public abstract void leggiElementoVettore(String identificatore, E indice);
	/**
	 * Scrivi l'istruzione per l'accesso al vettore
	 * 
	 * @param nome
	 */
	public abstract void scriviAccessoVettore(String nome);

	/**
	 * Scrivi l'istruzione condizionale "se"
	 */
	public abstract void scriviCondizionaleSe();

	/**
	 * Scrivi l'istruzione ciclo finche
	 */
	public abstract void scriviCicloFinche();
	
	/**
	 * Prepara il valore della variabile a essere usato
	 * @param identificatore
	 */
	public abstract void scriviStoreInVariabile(String identificatore);

	public abstract void scriviCostante(String stringa);

	public abstract void scriviStampaStringa();

	public abstract void scriviCreaVettore();
}
