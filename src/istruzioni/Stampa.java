package istruzioni;

import istruzioni.espressioni.E;

public class Stampa implements I {
	private E espressione;

	public Stampa(E espressione) {
		this.espressione = espressione;
	}
	
}
