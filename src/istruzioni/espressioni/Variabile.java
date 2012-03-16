package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

/**
 * Classe che implementa una variabile
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class Variabile implements F {

	private String nome;

	public Variabile(String nome) {
		this.nome = nome;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		sc.caricaVariabile(nome);
	}

}
