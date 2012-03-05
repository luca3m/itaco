package istruzioni;

import main.ScrittoreTarget;
import istruzioni.espressioni.E;

public class CicloFinche extends Object implements C {
	private E condizione;
	private N blocco;
	
	public CicloFinche(E condizione, N blocco) {
		this.condizione = condizione;
		this.blocco = blocco;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		
	}
	
}
