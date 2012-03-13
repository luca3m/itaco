package istruzioni;import compilatore.SemanticException;

import compilatore.ScrittoreTarget;

import istruzioni.logiche.B;

/**
 * 
 * @author Alessandro, Luca, Saro
 *
 */
public class CicloFinche extends Object implements C {
	private B condizione;
	private N blocco;
	
	public CicloFinche(B condizione, N blocco) {
		this.condizione = condizione;
		this.blocco = blocco;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.finche(condizione, blocco);
	}
	
}
