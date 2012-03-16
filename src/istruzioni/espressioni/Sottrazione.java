package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa la sottrazione
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class Sottrazione implements E {
	private E minuendo;
	private T sottraendo;

	public Sottrazione(E parteSinistra, T parteDestra) {
		this.minuendo = parteSinistra;
		this.sottraendo = parteDestra;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.sottrazione(minuendo, sottraendo);
	}
}
