package istruzioni.funzioni;

import compilatore.ScrittoreTarget;

import istruzioni.I;
import istruzioni.espressioni.F;
import istruzioni.espressioni.W;

public class ChiamaFunzioneSenzaRitorno implements F,I {

	private String id;
	private W argomenti;

	public ChiamaFunzioneSenzaRitorno(String id, W w) {
		this.id = id;
		this.argomenti = w;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.eseguiFunzioneSenzaRitorno(id, argomenti);
	}

}
