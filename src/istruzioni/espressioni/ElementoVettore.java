package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class ElementoVettore implements F {
	private String nomeVettore;
	private E indice;
		
	public ElementoVettore(String nomeVettore, E indice) {
		this.indice = indice;
		this.nomeVettore = nomeVettore;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: da implementare
	}
}
