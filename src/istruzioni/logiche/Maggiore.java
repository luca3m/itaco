package istruzioni.logiche;

public class Maggiore implements B {
	private B parteSinistra;
	private L parteDestra;
	public Maggiore(B parteSinistra, L parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
	
}
