package main;

import istruzioni.espressioni.B;
import istruzioni.espressioni.E;
import istruzioni.espressioni.F;
import istruzioni.espressioni.T;

import java.io.OutputStream;
import java.io.PrintStream;

public class TestTarget extends ScrittoreTarget {
	PrintStream output;
	
	public TestTarget(OutputStream output) {
		this.output = new PrintStream(output);
	}

	@Override
	public void somma(B addendo1, T addendo2) {
		output.println("somma");
	}

	@Override
	public void caricaVariabile(String nome) {
		output.println("carico variabile " + nome);
	}

	@Override
	public void costante(int costante) {
		output.println("carico costante " + costante);
	}

	@Override
	public void sottrazione(B minuendo, T sottraendo) {
		output.println("sottrazione");
		}

	@Override
	public void prodotto(T fattore1, F fattore2) {
		output.println("prodotto");
	}

	@Override
	public void divisione(T dividendo, F divisore) {
		output.println("divido");
	}

	@Override
	public void maggiore(E parteSinistra, B parteDestra) {
		output.println("maggiore");
	}

	@Override
	public void minore(E parteSinistra, B parteDestra) {
		output.println("minore");
	}

	@Override
	public void uguaglianza(E parteSinistra, B parteDestra) {
		output.println("uguaglianza");
	}

	@Override
	public void stampa(E espressione) {
		output.println("stampo");
	}

	@Override
	public void leggi(String identificatore) {
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

	@Override
	public void scriviCostante(String stringa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void scriviStampaStringa() {
		// TODO Auto-generated method stub
		
	}

}
