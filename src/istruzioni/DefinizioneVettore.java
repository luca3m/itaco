package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

/**
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class DefinizioneVettore implements I {
	private String identificatore;
	private Integer dimensione;

	public DefinizioneVettore(String identificatore, Integer dimensione) {
		this.identificatore = identificatore;
		this.dimensione = dimensione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.definisciVettore(identificatore, dimensione);
	}

}
