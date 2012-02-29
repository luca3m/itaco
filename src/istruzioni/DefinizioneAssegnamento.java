package istruzioni;

import main.ScrittoreTarget;
import istruzioni.espressioni.E;

public class DefinizioneAssegnamento implements I {
	private String identificatore;
	private E espressione;
	
	public DefinizioneAssegnamento(String identificatore, E espressione) {
		this.identificatore = identificatore;
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: devo gestire il caso in cui la registrazione della variabile vada male
		sc.registraVariabile(identificatore);
		espressione.scriviCodice(sc);
		sc.scriviStoreInVariabile(identificatore);
	}
}
