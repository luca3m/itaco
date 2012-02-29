package istruzioni;

import istruzioni.espressioni.E;

public class LetturaTastieraElementoVettore implements I {
	private String identificatore;
	private E indice;
	
	public LetturaTastieraElementoVettore(String identificatore, E indice) {
		this.identificatore = identificatore;
		this.indice = indice;
	}
}
