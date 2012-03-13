package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.SemanticException;

/**
 * 
 * @author Alessandro, Luca, Saro
 *
 */
public class Costante implements F {
	private int valore;
	
	public Costante(int valore) {
		this.valore = valore;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.costante(valore);
	}
}
