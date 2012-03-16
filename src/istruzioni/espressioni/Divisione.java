package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa la divisione
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
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.divisione(dividendo, divisore);
	}

}
