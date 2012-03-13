package istruzioni;import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

/**
 * 
 * @author Alessandro, Luca, Saro
 *
 */
public class SuccessioneCN implements N {
	private C istruzionePrecedente;
	private N istruzioneSuccessiva;
	
	public SuccessioneCN(C istruzionePrecedente, N istruzioneSuccessiva) {
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
