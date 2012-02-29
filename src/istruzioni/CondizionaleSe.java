package istruzioni;

import main.ScrittoreTarget;
import istruzioni.logiche.B;

public class CondizionaleSe implements C {
	private B condizione;
	private N blocco;
	
	public CondizionaleSe(B condizione, N blocco) {
		this.condizione = condizione;
		this.blocco = blocco;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: da implementare
	}
}
