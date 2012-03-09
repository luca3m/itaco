package istruzioni;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

public class CondizionaleAltrimenti implements C {
	private N blocco;
	
	public CondizionaleAltrimenti(N blocco) {
		this.blocco = blocco;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// FIXME: da implementare
	}
}
