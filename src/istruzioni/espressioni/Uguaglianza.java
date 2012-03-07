package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class Uguaglianza implements E {
	private E parteSinistra;
	private B parteDestra;
	
	public Uguaglianza(E parteSinistra, B parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.uguaglianza(parteSinistra, parteDestra);
	}
}
