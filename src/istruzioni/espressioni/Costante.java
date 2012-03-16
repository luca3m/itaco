package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa la memorizzazione di una costante
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
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.costante(valore);
	}
}
