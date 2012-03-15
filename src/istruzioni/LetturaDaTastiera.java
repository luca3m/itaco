package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

/**
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class LetturaDaTastiera implements I {
	String identificatore;

	public LetturaDaTastiera(String identificatore) {
		this.identificatore = identificatore;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		// TODO Auto-generated method stub
		sc.leggi(identificatore);
	}
}
