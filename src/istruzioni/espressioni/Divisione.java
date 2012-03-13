package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.SemanticException;

/**
 * 
 * @author Alessandro, Luca, Saro
 *
 */
public class Divisione implements T {
	private T dividendo;
	private F divisore;
	public Divisione(T dividendo, F divisore) {
		this.dividendo = dividendo;
		this.divisore = divisore;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.divisione(dividendo, divisore);
	}
	
}
