package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.SemanticException;

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
