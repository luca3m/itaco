package istruzioni;

import istruzioni.logiche.B;

public class CondizionaleSe implements I {
	private B condizione;
	private N blocco;
	
	public CondizionaleSe(B condizione, N blocco) {
		this.condizione = condizione;
		this.blocco = blocco;
	}
}
