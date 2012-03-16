package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

/**
 * Classe che implementa l'assegnazione di una variabile
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class AssegnamentoVariabile implements I {
	private String identificatore;
	private E espressione;

	public AssegnamentoVariabile(String identificatore, E espressione) {
		this.identificatore = identificatore;
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.storeInVariabile(identificatore, espressione);
	}

}
