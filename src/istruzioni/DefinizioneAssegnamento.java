package istruzioni;

import istruzioni.espressioni.E;

public class DefinizioneAssegnamento implements I {
	private String identificatore;
	private E espressione;
	
	public DefinizioneAssegnamento(String identificatore, E espressione) {
		this.identificatore = identificatore;
		this.espressione = espressione;
	}
}
