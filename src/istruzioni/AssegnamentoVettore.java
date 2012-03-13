package istruzioni;import compilatore.SemanticException;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

/**
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
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.storeElementoVettore(identificatore, indice, espressione);
	}
	
}
