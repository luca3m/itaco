package main;

import java.io.OutputStream;
import java.io.PrintStream;

public class TestTarget extends ScrittoreTarget {
	PrintStream output;
	
	public TestTarget(OutputStream output) {
		this.output = new PrintStream(output);
	}

	@Override
	public void scriviSomma() {
		output.println("somma");
	}

	@Override
	public void scriviVariabile(String nome) {
		output.println("carico variabile " + nome);
	}

	@Override
	public void scriviCostante(int costante) {
		output.println("carico costante " + costante);
	}

	@Override
	public void scriviSottrazione() {
		output.println("sottrazione");
		}

	@Override
	public void scriviMoltiplicazione() {
		output.println("prodotto");
	}

	@Override
	public void scriviDivisione() {
		output.println("divido");
	}

	@Override
	public void scriviMaggiore() {
		output.println("maggiore");
	}

	@Override
	public void scriviMinore() {
		output.println("minore");
	}

	@Override
	public void scriviUguaglianza() {
		output.println("uguaglianza");
	}

	@Override
	public void scriviStampa() {
		output.println("stampo");
	}

	@Override
	public void scriviLetturaStandard() {
		output.println("leggo");
	}

	@Override
	public void scriviAccessoVettore(String nome) {
		output.println("accedo a vettore");
	}

	@Override
	public void scriviCondizionaleSe() {
		output.println("se");
	}

	@Override
	public void scriviStoreInVariabile(String identificatore) {
		output.println("salvo in variabile " + identificatore);
	}

	@Override
	public void scriviCicloFinche() {
		output.println("finché");
	}

}
