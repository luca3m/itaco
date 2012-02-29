package istruzioni.logiche;

import main.ScrittoreTarget;

public class Maggiore implements B {
	private B parteSinistra;
	private L parteDestra;
	public Maggiore(B parteSinistra, L parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		parteSinistra.scriviCodice(sc);
		parteDestra.scriviCodice(sc);
		sc.scriviMaggiore();
	}
}
