package istruzioni.espressioni;

public class Sottrazione implements E {
	private E parteSinistra;
	private T parteDestra;
	public Sottrazione(E parteSinistra, T parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
}
