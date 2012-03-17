package istruzioni.logiche;

import istruzioni.espressioni.E;
import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa l'operazione di uguaglianza
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class Uguaglianza implements B {
	private E parteSinistra;
	private E parteDestra;

	public Uguaglianza(E parteSinistra, E parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.uguaglianza(parteSinistra, parteDestra);
	}
}
