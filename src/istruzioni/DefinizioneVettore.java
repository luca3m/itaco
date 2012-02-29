package istruzioni;

import main.ScrittoreTarget;
import istruzioni.espressioni.Costante;

public class DefinizioneVettore implements I {
	private String identificatore;
	private Costante dimensione;
	
	public DefinizioneVettore(String identificatore, Costante dimensione) {
		this.identificatore = identificatore;
		this.dimensione = dimensione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: da implementare
	}
	
}
