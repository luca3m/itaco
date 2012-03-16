package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa la somma
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class Somma implements E {
	private E addendo1;
	private T addendo2;

	public Somma(E parteSinistra, T parteDestra) {
		this.addendo1 = parteSinistra;
		this.addendo2 = parteDestra;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.somma(addendo1, addendo2);
	}
}
