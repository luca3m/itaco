package istruzioni.espressioni;

import main.ScrittoreTarget;

public class Sottrazione implements B {
	private B parteSinistra;
	private T parteDestra;
	
	public Sottrazione(B parteSinistra, T parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		parteSinistra.scriviCodice(sc);
		parteDestra.scriviCodice(sc);
		sc.sottrazione(null, null);
	}
}
