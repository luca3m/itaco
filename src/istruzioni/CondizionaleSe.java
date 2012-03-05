package istruzioni;

import main.ScrittoreTarget;
import istruzioni.espressioni.E;

public class CondizionaleSe implements C {
	private E condizione;
	private N blocco;
	
	public CondizionaleSe(E condizione, N blocco) {
		this.condizione = condizione;
		this.blocco = blocco;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: da implementare
	}
}
