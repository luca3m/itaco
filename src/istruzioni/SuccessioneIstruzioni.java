package istruzioni;

public class SuccessioneIstruzioni implements N {

	private I istruzionePrecedente;
	private N istruzioneSuccessiva;
	public SuccessioneIstruzioni(I istruzionePrecedente, N istruzioneSuccessiva) {
		this.istruzionePrecedente = istruzionePrecedente;
		this.istruzioneSuccessiva = istruzioneSuccessiva;
	}
	
}
