package main;

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
	 */
	public abstract void scriviSomma();

	/**
	 * Scrivi l'istruzione per caricare il valore della variabile nello stack
	 * 
	 * @param nome
	 *            nome della variabile
	 */
	public abstract void scriviVariabile(String nome);

	/**
	 * Scrivi l'istruzione per caricare una costante nello stack
	 * 
	 * @param costante
	 */
	public abstract void scriviCostante(int costante);

	/**
	 * Scrivi l'istruzione per fare la sottrazione nel linguaggio target
	 */
	public abstract void scriviSottrazione();

	/**
	 * Scrivi l'istruzione per fare la moltiplicazione nel linguaggio target
	 */
	public abstract void scriviMoltiplicazione();

	/**
	 * Scrivi l'istruzione per fare la divisione nel linguaggio target
	 */
	public abstract void scriviDivisione();

	/**
	 * Scrivi l'istruzione per operatore maggiore >
	 */
	public abstract void scriviMaggiore();

	/**
	 * Scrivi l'istruzione per operatore minore <
	 */
	public abstract void scriviMinore();

	/**
	 * Scrivi l'istruzione per operatore uguaglianza =
	 */
	public abstract void scriviUguaglianza();

	/**
	 * Scrivi istruzione per stampare a schermo
	 */
	public abstract void scriviStampa();

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 */
	public abstract void scriviLetturaStandard();

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
}
