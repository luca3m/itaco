package istruzioni.logiche;

import main.ScrittoreTarget;

public class Minore implements B {
	private B parteSinistra;
	private L parteDestra;
	
	public Minore(B parteSinistra, L parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		parteSinistra.scriviCodice(sc);
		parteDestra.scriviCodice(sc);
		sc.scriviMinore();
	}
}
