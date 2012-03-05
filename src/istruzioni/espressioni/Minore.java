package istruzioni.espressioni;

import main.ScrittoreTarget;

public class Minore implements E {
	private E parteSinistra;
	private B parteDestra;
	
	public Minore(E parteSinistra, B parteDestra) {
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
