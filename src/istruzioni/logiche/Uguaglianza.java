package istruzioni.logiche;

import main.ScrittoreTarget;

public class Uguaglianza implements B {
	private B parteSinistra;
	private L parteDestra;
	
	public Uguaglianza(B parteSinistra, L parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		parteSinistra.scriviCodice(sc);
		parteDestra.scriviCodice(sc);
		sc.scriviUguaglianza();
	}
}
