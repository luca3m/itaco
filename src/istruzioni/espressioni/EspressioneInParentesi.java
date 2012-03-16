package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa la priorità date dalle parentesi
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class EspressioneInParentesi implements F {
	private E espressione;

	public EspressioneInParentesi(E espressione) {
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.espressioneInParentesi(espressione);
	}

}
