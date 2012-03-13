package istruzioni.funzioni;

import istruzioni.espressioni.U;
import compilatore.ScrittoreTarget;
import compilatore.SemanticException;

public class ArgomentoFunzioneVettore implements U {
	
	private String nome;
	public ArgomentoFunzioneVettore(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.caricaVettore(nome);
		sc.caricaDimensioneVettore(nome);
	}
	
}
