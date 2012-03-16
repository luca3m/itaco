package istruzioni.funzioni;

import java.util.List;

/**
 * Classe che implementa il passaggio di un vettore a una funzione come
 * argomento nella sua definizione.
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class ArgomentoVettoreDefinizioneFunzione implements R {

	private String nomeVettore;
	private String dimVettore;

	/**
	 * @param nomeVettore
	 * @param dimVettore
	 */
	public ArgomentoVettoreDefinizioneFunzione(String nomeVettore, String dimVettore) {
		this.nomeVettore = nomeVettore;
		this.dimVettore = dimVettore;
	}

	@Override
	public void aggiungiALista(List<String> lista) {
		lista.add(String.format("%s:%s:%s", nomeVettore, "vettore", dimVettore));
	}

}
