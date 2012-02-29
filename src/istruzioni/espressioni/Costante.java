package istruzioni.espressioni;

import main.ScrittoreTarget;
import istruzioni.logiche.L;

public class Costante implements F,L {
	private int valore;
	
	public Costante(int valore) {
		this.valore = valore;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.scriviCostante(valore);
	}
}
