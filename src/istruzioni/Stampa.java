package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

/**
 * Classe che implementa la stampa su standard output
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class Stampa implements I {
	private E espressione;

	public Stampa(E espressione) {
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.stampa(espressione);
	}

}
