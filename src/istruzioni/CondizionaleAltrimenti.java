package istruzioni;

import istruzioni.logiche.B;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa la condizione "se ... altrimenti ..."
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class CondizionaleAltrimenti implements C {
	private B condizione;
	private N blocco1;
	private N blocco2;

	public CondizionaleAltrimenti(B condizione, N blocco1, N blocco2) {
		this.condizione = condizione;
		this.blocco1 = blocco1;
		this.blocco2 = blocco2;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.seAltrimenti(condizione, blocco1, blocco2);
	}
}
