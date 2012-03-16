package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa la selezione dell'elemento di un vettore e la sua
 * preparazione all'uso
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class ElementoVettore implements F {
	private String nomeVettore;
	private E indice;

	public ElementoVettore(String nomeVettore, E indice) {
		this.indice = indice;
		this.nomeVettore = nomeVettore;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.caricaElementoVettore(nomeVettore, indice);
	}
}
