package istruzioni;import compilatore.SemanticException;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

public class LetturaTastieraElementoVettore implements I {
	private String identificatore;
	private E indice;
	
	public LetturaTastieraElementoVettore(String identificatore, E indice) {
		this.identificatore = identificatore;
		this.indice = indice;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.leggiElementoVettore(identificatore, indice);
	}
	
}
