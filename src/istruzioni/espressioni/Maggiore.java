package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class Maggiore implements E {
	private E parteSinistra;
	private B parteDestra;
	public Maggiore(E parteSinistra, B parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.maggiore(parteSinistra, parteDestra);
	}
}
