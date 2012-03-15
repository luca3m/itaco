package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

/**
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class DefinizioneAssegnamentoVariabile implements I {
	private String identificatore;
	private E espressione;

	public DefinizioneAssegnamentoVariabile(String identificatore, E espressione) {
		this.identificatore = identificatore;
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		// FIXME: devo gestire il caso in cui la registrazione della variabile
		// vada male
		sc.registraVariabile(identificatore);
		sc.storeInVariabile(identificatore, espressione);
	}
}
