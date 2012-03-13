package istruzioni;import compilatore.SemanticException;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

/**
 * @author Alessandro, Luca, Saro
 *
 */
public class Stampa implements I {
	private E espressione;

	public Stampa(E espressione) {
		this.espressione = espressione;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.stampa(espressione);
	}
	
}
