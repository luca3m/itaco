package istruzioni.espressioni;

import compilatore.ScrittoreTarget;
import compilatore.SemanticException;

public class Sottrazione implements E {
	private E minuendo;
	private T sottraendo;
	
	public Sottrazione(E parteSinistra, T parteDestra) {
		this.minuendo = parteSinistra;
		this.sottraendo = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) throws SemanticException {
		sc.sottrazione(minuendo, sottraendo);
	}
}
