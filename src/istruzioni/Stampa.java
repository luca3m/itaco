package istruzioni;

import main.ScrittoreTarget;
import istruzioni.espressioni.E;

public class Stampa implements I {
	private E espressione;

	public Stampa(E espressione) {
		this.espressione = espressione;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub;
		espressione.scriviCodice(sc);
		sc.scriviStampa();
	}
	
}
