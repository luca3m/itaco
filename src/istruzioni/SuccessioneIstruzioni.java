package istruzioni;import compilatore.EccezioneSemantica;

import compilatore.Produzione;
import compilatore.ScrittoreTarget;

/**
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
		// TODO Auto-generated method stub
		istruzionePrecedente.scriviCodice(sc);
		istruzioneSuccessiva.scriviCodice(sc);
	}
}
