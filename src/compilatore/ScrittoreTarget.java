package compilatore;

public abstract class ScrittoreTarget {

	public abstract void registraVariabile(String nome)
			throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per caricare il valore della variabile nello stack
	 * 
	 * @param nome
	 *            nome della variabile
	 */
	public abstract void caricaVariabile(String nome) throws EccezioneSemantica;

	/**
	 * Prepara il valore della variabile a essere usato
	 * 
	 * @param identificatore
	 * @throws EccezioneSemantica
	 */
	public abstract void storeInVariabile(String identificatore,
			Espressione valore) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per caricare una costante nello stack
	 * 
	 * @param costante
	 */
	public abstract void costante(int costante) throws EccezioneSemantica;

	public abstract void costante(String stringa) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per fare la somma nel linguaggio target
	 * 
	 * @param addendo1
	 *            TODO
	 * @param addendo2
	 *            TODO
	 */
	public abstract void somma(Espressione addendo1, Espressione addendo2)
			throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per fare la sottrazione nel linguaggio target
	 * 
	 * @param minuendo
	 *            TODO
	 * @param sottraendo
	 *            TODO
	 */
	public abstract void sottrazione(Espressione minuendo,
			Espressione sottraendo) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per fare la moltiplicazione nel linguaggio target
	 * 
	 * @param fattore1
	 *            TODO
	 * @param fattore2
	 *            TODO
	 */
	public abstract void prodotto(Espressione fattore1, Espressione fattore2)
			throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per fare la divisione nel linguaggio target
	 * 
	 * @param dividendo
	 *            TODO
	 * @param divisore
	 *            TODO
	 */
	public abstract void divisione(Espressione dividendo, Espressione divisore)
			throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per operatore maggiore >
	 * 
	 * @param parteSinistra
	 *            TODO
	 * @param parteDestra
	 *            TODO
	 */
	public abstract void maggiore(Espressione parteSinistra,
			Espressione parteDestra) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per operatore minore <
	 * 
	 * @param parteSinistra
	 *            TODO
	 * @param parteDestra
	 *            TODO
	 */
	public abstract void minore(Espressione parteSinistra,
			Espressione parteDestra) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per operatore uguaglianza =
	 * 
	 * @param parteSinistra
	 *            TODO
	 * @param parteDestra
	 *            TODO
	 */
	public abstract void uguaglianza(Espressione parteSinistra,
			Espressione parteDestra) throws EccezioneSemantica;

	public abstract void espressioneInParentesi(Espressione ex)
			throws EccezioneSemantica;

	/**
	 * Scrivi istruzione per stampare a schermo
	 * 
	 * @param espressione
	 *            TODO
	 */
	public abstract void stampa(Espressione espressione)
			throws EccezioneSemantica;

	public abstract void stampa(String stringa) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 * 
	 * @param identificatore
	 *            TODO
	 */
	public abstract void leggi(String identificatore) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 * 
	 * @param identificatore
	 *            TODO
	 */
	public abstract void leggiElementoVettore(String identificatore,
			Espressione indice) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione condizionale "se"
	 */
	public abstract void se(Espressione ex, Blocco codice)
			throws EccezioneSemantica;

	public abstract void seAltrimenti(Espressione ex, Blocco codice,
			Blocco codiceAltrimenti) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione ciclo finche
	 */
	public abstract void finche(Espressione ex, Blocco codice)
			throws EccezioneSemantica;

	// Operazioni sui vettori
	public abstract void definisciVettore(String identificatore,
			Integer dimensione) throws EccezioneSemantica;

	public abstract void caricaElementoVettore(String identificatore,
			Espressione indice) throws EccezioneSemantica;

	public abstract void storeElementoVettore(String identificatore,
			Espressione indice, Espressione elemento) throws EccezioneSemantica;

	// Operazioni sulle funzioni
	/**
	 * 
	 * @param nome
	 * @param ingressi
	 *            un vettore di stringhe del tipo { "nomeparametro:tipo",
	 *            "nomeparametro:tipo", "nomeparametro:tipo" }
	 * @param uscita
	 *            stringa del tipo "variabileuscita:tipo"
	 * @param codice
	 *            codice eseguito dalla funzione
	 */
	public abstract void definisciFunzione(String nome, String[] ingressi,
			String uscita, Blocco codice) throws EccezioneSemantica;

	public abstract void eseguiFunzione(String nome, Espressione parametri)
			throws EccezioneSemantica;

	public abstract void eseguiFunzioneSenzaRitorno(String nome,
			Espressione parametri) throws EccezioneSemantica;

	public abstract void scriviMain(Blocco codice) throws EccezioneSemantica;

	public abstract void caricaVettore(String nome) throws EccezioneSemantica;

	public abstract void caricaDimensioneVettore(String nome)
			throws EccezioneSemantica;
}
