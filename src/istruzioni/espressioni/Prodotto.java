package istruzioni.espressioni;

public class Prodotto implements T {
	private T fattoreSinistro;
	private F fattoreDestro;
	
	public Prodotto(T fattoreSinistro, F fattoreDestro) {
		super();
		this.fattoreSinistro = fattoreSinistro;
		this.fattoreDestro = fattoreDestro;
	}
}
