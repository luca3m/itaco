package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class Minore implements E {
	private E parteSinistra;
	private B parteDestra;
	
	public Minore(E parteSinistra, B parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.minore(parteSinistra, parteDestra);
	}
}
