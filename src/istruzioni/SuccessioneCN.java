package istruzioni;

public class SuccessioneCN implements N {
	private C istruzionePrecedente;
	private N istruzioneSuccessiva;
	
	public SuccessioneCN(C istruzionePrecedente, N istruzioneSuccessiva) {
		this.istruzionePrecedente = istruzionePrecedente;
		this.istruzioneSuccessiva = istruzioneSuccessiva;
	}
}
