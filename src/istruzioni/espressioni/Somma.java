package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class Somma implements B {
	private B addendo1;
	private T addendo2;
	
	public Somma(B parteSinistra, T parteDestra) {
		this.addendo1 = parteSinistra;
		this.addendo2 = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.somma(addendo1, addendo2);
	}
}
