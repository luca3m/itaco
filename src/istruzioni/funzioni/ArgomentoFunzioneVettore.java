package istruzioni.funzioni;

import istruzioni.espressioni.U;
import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class ArgomentoFunzioneVettore implements U {

	private String nome;

	public ArgomentoFunzioneVettore(String nome) {
		this.nome = nome;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.caricaVettore(nome);
		sc.caricaDimensioneVettore(nome);
	}

}
