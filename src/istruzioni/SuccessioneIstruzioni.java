package istruzioni;

import compilatore.ScrittoreTarget;

public class SuccessioneIstruzioni implements N {

	private I istruzionePrecedente;
	private N istruzioneSuccessiva;
	public SuccessioneIstruzioni(I istruzionePrecedente, N istruzioneSuccessiva) {
		this.istruzionePrecedente = istruzionePrecedente;
		this.istruzioneSuccessiva = istruzioneSuccessiva;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		istruzionePrecedente.scriviCodice(sc);
		istruzioneSuccessiva.scriviCodice(sc);
	}
}
