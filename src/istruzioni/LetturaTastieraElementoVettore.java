package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

/**
 * Classe che implementa la lettura da standard input e la sua assegnazione a un
 * elemento di un vettore
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class LetturaTastieraElementoVettore implements I {
	private String identificatore;
	private E indice;

	public LetturaTastieraElementoVettore(String identificatore, E indice) {
		this.identificatore = identificatore;
		this.indice = indice;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.leggiElementoVettore(identificatore, indice);
	}

}
