package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

/**
 * Classe che implementa l'assegnazione di un vettore
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class AssegnamentoVettore implements I {
	private String identificatore;
	private E indice;
	private E espressione;

	public AssegnamentoVettore(String identificatore, E indice, E espressione) {
		this.identificatore = identificatore;
		this.indice = indice;
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.storeElementoVettore(identificatore, indice, espressione);
	}

}
