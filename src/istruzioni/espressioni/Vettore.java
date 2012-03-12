package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class Vettore implements F {
	
	private String nome;
	public Vettore(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.caricaVariabile(nome);
	}
	
}
