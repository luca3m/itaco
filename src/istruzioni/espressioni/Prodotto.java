package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.SemanticException;

public class Prodotto implements T {
	private T fattoreSinistro;
	private F fattoreDestro;
	
	public Prodotto(T fattoreSinistro, F fattoreDestro) {
		this.fattoreSinistro = fattoreSinistro;
		this.fattoreDestro = fattoreDestro;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.prodotto(fattoreSinistro, fattoreDestro);
	}
}
