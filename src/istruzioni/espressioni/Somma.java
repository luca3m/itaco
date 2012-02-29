package istruzioni.espressioni;

public class Somma implements E {
	private E parteSinistra;
	private T parteDestra;
	public Somma(E parteSinistra, T parteDestra) {
		this.parteSinistra = parteSinistra;
		this.parteDestra = parteDestra;
	}
}
