package istruzioni;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

public class DefinizioneAssegnamentoVariabile implements I {
	private String identificatore;
	private E espressione;
	
	public DefinizioneAssegnamentoVariabile(String identificatore, E espressione) {
		this.identificatore = identificatore;
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: devo gestire il caso in cui la registrazione della variabile vada male
		sc.registraVariabile(identificatore);
		sc.storeInVariabile(identificatore, espressione);
	}
}
