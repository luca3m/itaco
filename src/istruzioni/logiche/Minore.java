package istruzioni.logiche;

import istruzioni.espressioni.E;
import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa l'operazione di minore
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
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.minore(parteSinistra, parteDestra);
	}
}
