package istruzioni.logiche;

import istruzioni.espressioni.E;
import compilatore.ScrittoreTarget;
import compilatore.SemanticException;

/**
 * 
 * @author Alessandro, Luca, Saro
 *
 */
public class Minore implements B {
	private E parteSinistra;
	private E parteDestra;
	
	public Minore(E parteSinistra, E parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.minore(parteSinistra, parteDestra);
	}
}
