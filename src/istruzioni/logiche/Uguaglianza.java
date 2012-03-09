package istruzioni.logiche;

import istruzioni.espressioni.E;
import compilatore.ScrittoreTarget;

public class Uguaglianza implements B {
	private E parteSinistra;
	private E parteDestra;
	
	public Uguaglianza(E parteSinistra, E parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.uguaglianza(parteSinistra, parteDestra);
	}
}
