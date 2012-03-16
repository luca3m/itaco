package istruzioni;

import compilatore.EccezioneSemantica;

import compilatore.ScrittoreTarget;

import istruzioni.logiche.B;

/**
 * Classe che implementa la condizione "se"
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class CondizionaleSe implements C {
	private B condizione;
	private N blocco;

	public CondizionaleSe(B condizione, N blocco) {
		this.condizione = condizione;
		this.blocco = blocco;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.se(condizione, blocco);
	}
}
