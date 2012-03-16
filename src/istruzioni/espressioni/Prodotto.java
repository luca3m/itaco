package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa il prodotto
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class Prodotto implements T {
	private T fattoreSinistro;
	private F fattoreDestro;

	public Prodotto(T fattoreSinistro, F fattoreDestro) {
		this.fattoreSinistro = fattoreSinistro;
		this.fattoreDestro = fattoreDestro;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.prodotto(fattoreSinistro, fattoreDestro);
	}
}
