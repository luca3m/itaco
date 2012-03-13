package istruzioni.funzioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

import istruzioni.I;
import istruzioni.espressioni.F;
import istruzioni.espressioni.W;

/**
 * 
 * @author Alessandro, Luca, Saro
 *
 */
public class ChiamaFunzioneSenzaRitorno implements F,I {

	private String id;
	private W argomenti;

	public ChiamaFunzioneSenzaRitorno(String id, W w) {
		this.id = id;
		this.argomenti = w;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.eseguiFunzioneSenzaRitorno(id, argomenti);
	}

}
