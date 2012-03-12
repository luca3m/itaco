package istruzioni.funzioni;

import compilatore.ScrittoreTarget;

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
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub

	}

}
