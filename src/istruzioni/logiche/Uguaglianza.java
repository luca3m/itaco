package istruzioni.logiche;

public class Uguaglianza implements B {
	private B parteSinistra;
	private L parteDestra;
	
	public Uguaglianza(B parteSinistra, L parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
}
