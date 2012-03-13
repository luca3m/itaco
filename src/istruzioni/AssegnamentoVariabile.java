package istruzioni;import compilatore.SemanticException;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

public class AssegnamentoVariabile implements I {
	private String identificatore;
	private E espressione;
	
	public AssegnamentoVariabile(String identificatore, E espressione) {
		this.identificatore = identificatore;
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.storeInVariabile(identificatore, espressione);
	}
	
}
