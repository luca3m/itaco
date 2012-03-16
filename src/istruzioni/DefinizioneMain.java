package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

/**
 * Classe che implementa la produzione principale dell'assioma S -> N.
 * @author Alessandro, Luca, Saro
 * 
 */
public class DefinizioneMain implements S {

	private N codiceProgramma;

	/**
	 * 
	 * @param codiceProgramma
	 */
	public DefinizioneMain(N codiceProgramma) {
		this.codiceProgramma = codiceProgramma;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.scriviMain(codiceProgramma);
	}

}
