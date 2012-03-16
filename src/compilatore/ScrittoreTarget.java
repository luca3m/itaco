package compilatore;

/**
 * Classe astratta per la scrittura in diversi linguaggi target
 * 
 * @author Alessandro Luca Rosario
 * 
 */
public abstract class ScrittoreTarget {

	/**
	 * Registra una variabile nella tabella dei simboli
	 * 
	 * @param nome
	 * @throws EccezioneSemantica
	 */
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
	 * Scrivi l'istruzione per caricare una costante nello stack, nel caso sia
	 * una costante intera
	 * 
	 * @param costante
	 */
	public abstract void costante(int costante) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per caricare una costante nello stack, nel caso sia
	 * una stringa
	 * 
	 * @param stringa
	 * @throws EccezioneSemantica
	 */
	public abstract void costante(String stringa) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per fare la somma nel linguaggio target
	 * 
	 * @param addendo1
	 * @param addendo2
	 */
	public abstract void somma(Espressione addendo1, Espressione addendo2)
			throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per fare la sottrazione nel linguaggio target
	 * 
	 * @param minuendo
	 * @param sottraendo
	 */
	public abstract void sottrazione(Espressione minuendo,
			Espressione sottraendo) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per fare la moltiplicazione nel linguaggio target
	 * 
	 * @param fattore1
	 * @param fattore2
	 */
	public abstract void prodotto(Espressione fattore1, Espressione fattore2)
			throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per fare la divisione nel linguaggio target
	 * 
	 * @param dividendo
	 * @param divisore
	 */
	public abstract void divisione(Espressione dividendo, Espressione divisore)
			throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per operatore maggiore >
	 * 
	 * @param parteSinistra
	 * @param parteDestra
	 */
	public abstract void maggiore(Espressione parteSinistra,
			Espressione parteDestra) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per operatore minore <
	 * 
	 * @param parteSinistra
	 * @param parteDestra
	 */
	public abstract void minore(Espressione parteSinistra,
			Espressione parteDestra) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per operatore uguaglianza =
	 * 
	 * @param parteSinistra
	 * @param parteDestra
	 */
	public abstract void uguaglianza(Espressione parteSinistra,
			Espressione parteDestra) throws EccezioneSemantica;

	/**
	 * Gestisci la priorità delle parentesi che racchiudono una espressione
	 * 
	 * @param ex
	 * @throws EccezioneSemantica
	 */
	public abstract void espressioneInParentesi(Espressione ex)
			throws EccezioneSemantica;

	/**
	 * Scrivi istruzione per stampare a schermo
	 * 
	 * @param espressione
	 */
	public abstract void stampa(Espressione espressione)
			throws EccezioneSemantica;

	public abstract void stampa(String stringa) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 * 
	 * @param identificatore
	 */
	public abstract void leggi(String identificatore) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione per leggere un intero da tastiera
	 * 
	 * @param identificatore
	 */
	public abstract void leggiElementoVettore(String identificatore,
			Espressione indice) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione condizionale "se"
	 */
	public abstract void se(Espressione ex, Blocco codice)
			throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione condizionale "se" con in seguito "altrimenti"
	 * 
	 * @param ex
	 * @param codice
	 * @param codiceAltrimenti
	 * @throws EccezioneSemantica
	 */
	public abstract void seAltrimenti(Espressione ex, Blocco codice,
			Blocco codiceAltrimenti) throws EccezioneSemantica;

	/**
	 * Scrivi l'istruzione ciclo finche
	 */
	public abstract void finche(Espressione ex, Blocco codice)
			throws EccezioneSemantica;

	/*
	 * Operazioni sui vettori
	 */
	/**
	 * Definisci un vettore
	 * 
	 * @param identificatore
	 * @param dimensione
	 * @throws EccezioneSemantica
	 */
	public abstract void definisciVettore(String identificatore,
			Integer dimensione) throws EccezioneSemantica;

	/**
	 * Prepara per l'uso un elemento di un vettore
	 * 
	 * @param identificatore
	 * @param indice
	 * @throws EccezioneSemantica
	 */
	public abstract void caricaElementoVettore(String identificatore,
			Espressione indice) throws EccezioneSemantica;

	/**
	 * Memorizza un elemento di un vettore
	 * 
	 * @param identificatore
	 * @param indice
	 * @param elemento
	 * @throws EccezioneSemantica
	 */
	public abstract void storeElementoVettore(String identificatore,
			Espressione indice, Espressione elemento) throws EccezioneSemantica;

	/**
	 * Prepara un vettore per l'utilizzo
	 * 
	 * @param nome
	 * @throws EccezioneSemantica
	 */
	public abstract void caricaVettore(String nome) throws EccezioneSemantica;

	/**
	 * Prepara la dimensione di un vettore
	 * 
	 * @param nome
	 * @throws EccezioneSemantica
	 */
	public abstract void caricaDimensioneVettore(String nome)
			throws EccezioneSemantica;

	/**
	 * Definisci una funzione
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

	/**
	 * Chiama una funzione che ritorna un valore per l'esecuzione
	 * 
	 * @param nome
	 * @param parametri
	 * @throws EccezioneSemantica
	 */
	public abstract void eseguiFunzione(String nome, Espressione parametri)
			throws EccezioneSemantica;

	/**
	 * Chiama una funzione che non ritorna nessun valore per l'esecuzione
	 * 
	 * @param nome
	 * @param parametri
	 * @throws EccezioneSemantica
	 */
	public abstract void eseguiFunzioneSenzaRitorno(String nome,
			Espressione parametri) throws EccezioneSemantica;

	/**
	 * Scrivi una porzione di codice in testa al codice target
	 * 
	 * @param codice
	 * @throws EccezioneSemantica
	 */
	public abstract void scriviMain(Blocco codice) throws EccezioneSemantica;

}
