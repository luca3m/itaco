package istruzioni;

import compilatore.ScrittoreTarget;

public class DefinizioneVettore implements I {
	private String identificatore;
	private Integer dimensione;
	
	public DefinizioneVettore(String identificatore, Integer dimensione) {
		this.identificatore = identificatore;
		this.dimensione = dimensione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.definisciVettore(identificatore, dimensione);
	}
	
}
