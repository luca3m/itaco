package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class Prodotto implements T {
	private T fattoreSinistro;
	private F fattoreDestro;
	
	public Prodotto(T fattoreSinistro, F fattoreDestro) {
		this.fattoreSinistro = fattoreSinistro;
		this.fattoreDestro = fattoreDestro;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		fattoreSinistro.scriviCodice(sc);
		fattoreDestro.scriviCodice(sc);
		sc.prodotto(null, null);
	}
}
