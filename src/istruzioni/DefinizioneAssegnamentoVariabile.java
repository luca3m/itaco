package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

/**
 * Classe che implementa l'assegnamento e la definizione di una variabile sulla
 * stessa riga
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class DefinizioneAssegnamentoVariabile implements I {
	private String identificatore;
	private E espressione;

	public DefinizioneAssegnamentoVariabile(String identificatore, E espressione) {
		this.identificatore = identificatore;
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.registraVariabile(identificatore);
		sc.storeInVariabile(identificatore, espressione);
	}
}
