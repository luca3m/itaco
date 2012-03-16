package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.Produzione;
import compilatore.ScrittoreTarget;

/**
 * Classe che implementa una sequenza di istruzioni
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class SuccessioneIstruzioni implements N {

	private Produzione istruzionePrecedente;
	private Produzione istruzioneSuccessiva;

	public SuccessioneIstruzioni(Produzione istruzionePrecedente,
			Produzione istruzioneSuccessiva) {
		this.istruzionePrecedente = istruzionePrecedente;
		this.istruzioneSuccessiva = istruzioneSuccessiva;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		istruzionePrecedente.scriviCodice(sc);
		istruzioneSuccessiva.scriviCodice(sc);
	}
}
