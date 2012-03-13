package istruzioni;import compilatore.SemanticException;

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
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		// TODO Auto-generated method stub
		sc.leggi(identificatore);
	}
}
