package compilatore;

public abstract class ScrittoreTarget {
	
	public abstract boolean registraVariabile(String nome);
	
	protected String labelInizioBlocco(String nome) {
		return "";
	}

	protected String labelFineBlocco(String nome) {
		return "";
	}

	/**
	 * Scrivi l'istruzione per caricare il valore della variabile nello stack
	 * 
	 * @param nome nome della variabile
	 */
	public abstract void caricaVariabile(String nome);

	/**
	 * Prepara il valore della variabile a essere usato
	 * @param identificatore
	 */
	public abstract void storeInVariabile(String identificatore, Espressione valore);
	
	/**
	 * Scrivi l'istruzione per caricare una costante nello stack
	 * 
	 * @param costante
	 */
	public abstract void costante(int costante);
	
	public abstract void costante(String stringa);

	/**
	 * Scrivi l'istruzione per fare la somma nel linguaggio target
	 * @param addendo1 TODO
	 * @param addendo2 TODO
	 */
	public abstract void somma(Espressione addendo1, Espressione addendo2);
	
	/**
	 * Scrivi l'istruzione per fare la sottrazione nel linguaggio target
	 * @param minuendo TODO
	 * @param sottraendo TODO
	 */
	public abstract void sottrazione(Espressione minuendo, Espressione sottraendo);

	/**
	 * Scrivi l'istruzione per fare la moltiplicazione nel linguaggio target
	 * @param fattore1 TODO
	 * @param fattore2 TODO
	 */
	public abstract void prodotto(Espressione fattore1, Espressione fattore2);

	/**
	 * Scrivi l'istruzione per fare la divisione nel linguaggio target
	 * @param dividendo TODO
	 * @param divisore TODO
	 */
	public abstract void divisione(Espressione dividendo, Espressione divisore);

	/**
	 * Scrivi l'istruzione per operatore maggiore >
	 * @param parteSinistra TODO
	 * @param parteDestra TODO
	 */
	public abstract void maggiore(Espressione parteSinistra, Espressione parteDestra);

	/**
	 * Scrivi l'istruzione per operatore minore <
	 * @param parteSinistra TODO
	 * @param parteDestra TODO
	 */
	public abstract void minore(Espressione parteSinistra, Espressione parteDestra);

	/**
	 * Scrivi l'istruzione per operatore uguaglianza =
	 * @param parteSinistra TODO
	 * @param parteDestra TODO
	 */
	public abstract void uguaglianza(Espressione parteSinistra, Espressione parteDestra);

	public abstract void espressioneInParentesi(Espressione ex);
	
	/**
	 * Scrivi istruzione per stampare a schermo
	 * @param espressione TODO
	 */
	public abstract void stampa(Espressione espressione);
	public abstract void stampa(String stringa);

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 * @param identificatore TODO
	 */
	public abstract void leggi(String identificatore);

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 * @param identificatore TODO
	 */
	public abstract void leggiElementoVettore(String identificatore, Espressione indice);

	/**
	 * Scrivi l'istruzione condizionale "se"
	 */
	public abstract void se(Espressione ex, Blocco codice);

	/**
	 * Scrivi l'istruzione ciclo finche
	 */
	public abstract void finche(Espressione ex, Blocco codice);


	// Operazioni sui vettori
	public abstract void definisciVettore(String identificatore, Integer dimensione);
	public abstract void caricaElementoVettore(String identificatore, Espressione indice);
	public abstract void storeElementoVettore(String identificatore, Espressione indice, Espressione elemento);
}
