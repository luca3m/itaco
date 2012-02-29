package istruzioni.espressioni;

import main.ScrittoreTarget;
import istruzioni.logiche.L;

public class Identificatore implements F,L {
	
	String nome;
	public Identificatore(String nome) {
		this.nome = nome;
	}
	
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.scriviVariabile(nome);
	}
	
}
