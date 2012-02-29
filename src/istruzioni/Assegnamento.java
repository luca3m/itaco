package istruzioni;

import istruzioni.espressioni.E;

public class Assegnamento implements I {
	private String identificatore;
	private E espressione;
	
	public Assegnamento(String identificatore, E espressione) {
		this.identificatore = identificatore;
		this.espressione = espressione;
	}
	
}
