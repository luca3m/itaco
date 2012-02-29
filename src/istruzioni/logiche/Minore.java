package istruzioni.logiche;

public class Minore implements B {
	private B parteSinistra;
	private L parteDestra;
	
	public Minore(B parteSinistra, L parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
}
