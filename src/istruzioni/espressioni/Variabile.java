package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class Variabile implements F {
	
	private String nome;
	public Variabile(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.caricaVariabile(nome);
	}
	
}
