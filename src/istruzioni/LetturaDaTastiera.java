package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

/**
 * Classe che implementa la lettura da standard input di un intero e
 * l'assegnazione a una variabile
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
		sc.leggi(identificatore);
	}
}
