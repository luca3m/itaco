package istruzioni;

import main.ScrittoreTarget;
import istruzioni.espressioni.E;

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
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: da implementare
	}
	
}
