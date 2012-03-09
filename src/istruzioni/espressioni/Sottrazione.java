package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class Sottrazione implements B {
	private B minuendo;
	private T sottraendo;
	
	public Sottrazione(B parteSinistra, T parteDestra) {
		this.minuendo = parteSinistra;
		this.sottraendo = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.sottrazione(minuendo, sottraendo);
	}
}
