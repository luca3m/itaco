package istruzioni;

import main.ScrittoreTarget;

public class LetturaDaTastiera implements I {
	String identificatore;

	public LetturaDaTastiera(String identificatore) {
		this.identificatore = identificatore;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		sc.scriviLetturaStandard();
		sc.scriviStoreInVariabile(identificatore);
	}
}
