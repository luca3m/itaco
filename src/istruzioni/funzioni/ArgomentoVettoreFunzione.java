package istruzioni.funzioni;

import java.util.List;

/**
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class ArgomentoVettoreFunzione implements R {

	private String nomeVettore;
	private String dimVettore;

	/**
	 * @param nomeVettore
	 * @param dimVettore
	 */
	public ArgomentoVettoreFunzione(String nomeVettore, String dimVettore) {
		this.nomeVettore = nomeVettore;
		this.dimVettore = dimVettore;
	}

	@Override
	public void aggiungiALista(List<String> lista) {
		lista.add(String.format("%s:%s:%s", nomeVettore, "vettore", dimVettore));
	}

}
