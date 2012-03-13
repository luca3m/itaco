package istruzioni;import compilatore.SemanticException;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;
import istruzioni.logiche.B;

public class CondizionaleSe implements C {
	private B condizione;
	private N blocco;
	
	public CondizionaleSe(B condizione, N blocco) {
		this.condizione = condizione;
		this.blocco = blocco;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.se(condizione, blocco);
	}
}
