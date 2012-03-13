package istruzioni;import compilatore.SemanticException;

import compilatore.Produzione;
import compilatore.ScrittoreTarget;

public class SuccessioneIstruzioni implements N {

	private Produzione istruzionePrecedente;
	private Produzione istruzioneSuccessiva;

	public SuccessioneIstruzioni(Produzione istruzionePrecedente,
			Produzione istruzioneSuccessiva) {
		this.istruzionePrecedente = istruzionePrecedente;
		this.istruzioneSuccessiva = istruzioneSuccessiva;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		// TODO Auto-generated method stub
		istruzionePrecedente.scriviCodice(sc);
		istruzioneSuccessiva.scriviCodice(sc);
	}
}
