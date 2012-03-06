package istruzioni.espressioni;

import main.ScrittoreTarget;

public class Identificatore implements F {
	
	private String nome;
	public Identificatore(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.caricaVariabile(nome);
	}
	
}
