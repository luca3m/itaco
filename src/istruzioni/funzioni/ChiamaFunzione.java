package istruzioni.funzioni;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.F;
import istruzioni.espressioni.W;

public class ChiamaFunzione implements F {

	private String id;
	private W argomenti;

	public ChiamaFunzione(String id, W w) {
		this.id = id;
		this.argomenti = w;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub

	}

}
