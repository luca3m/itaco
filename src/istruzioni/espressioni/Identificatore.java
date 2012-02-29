package istruzioni.espressioni;

import istruzioni.logiche.L;

public class Identificatore implements F,L {
	
	String nome;
	public Identificatore(String nome) {
		this.nome = nome;
	}
}
