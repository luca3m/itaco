package istruzioni.funzioni;

import istruzioni.espressioni.U;
import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa il passaggio di un vettore come argomento di una
 * chiamata a funzione
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class ArgomentoVettoreChiamataFunzione implements U {

	private String nome;

	public ArgomentoVettoreChiamataFunzione(String nome) {
		this.nome = nome;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.caricaVettore(nome);
		sc.caricaDimensioneVettore(nome);
	}

}
