package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.SemanticException;

public class Variabile implements F {
	
	private String nome;
	public Variabile(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.caricaVariabile(nome);
	}
	
}
