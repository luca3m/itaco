package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

/**
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class StampaStringa implements I {
	private String stringa;

	public StampaStringa(String stringa) {

		this.stringa = stringa;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.stampa(stringa);
	}

}
