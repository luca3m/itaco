package istruzioni.funzioni;

import istruzioni.espressioni.U;
import compilatore.ScrittoreTarget;

public class ArgomentoFunzioneVettore implements U {
	
	private String nome;
	public ArgomentoFunzioneVettore(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.caricaVettore(nome);
		sc.caricaDimensioneVettore(nome);
	}
	
}
