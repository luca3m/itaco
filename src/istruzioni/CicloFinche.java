package istruzioni;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;
import istruzioni.logiche.B;

public class CicloFinche extends Object implements C {
	private B condizione;
	private N blocco;
	
	public CicloFinche(B condizione, N blocco) {
		this.condizione = condizione;
		this.blocco = blocco;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		
	}
	
}
