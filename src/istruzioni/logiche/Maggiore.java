package istruzioni.logiche;

import istruzioni.espressioni.E;
import compilatore.ScrittoreTarget;

public class Maggiore implements B {
	private E parteSinistra;
	private E parteDestra;
	public Maggiore(E parteSinistra, E parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.maggiore(parteSinistra, parteDestra);
	}
}
