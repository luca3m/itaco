package istruzioni.espressioni;

import istruzioni.logiche.L;

public class Costante implements F,L {
	private int valore;
	
	public Costante(int valore) {
		this.valore = valore;
	}
}
