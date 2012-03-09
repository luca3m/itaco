package istruzioni;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;
import istruzioni.logiche.B;

public class CondizionaleAltrimenti implements C {
	private B condizione;
	private N blocco1;
	private N blocco2;
	
	public CondizionaleAltrimenti(B condizione, N blocco1, N blocco2) {
		this.condizione = condizione;
		this.blocco1 = blocco1;
		this.blocco2 = blocco2;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: da implementare
	}
}
