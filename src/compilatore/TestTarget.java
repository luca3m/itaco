package compilatore;

import java.io.OutputStream;
import java.io.PrintStream;

public class TestTarget extends ScrittoreTarget {
	PrintStream output;
	
	public TestTarget(OutputStream output) {
		this.output = new PrintStream(output);
	}

	@Override
	public boolean registraVariabile(String nome) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void caricaVariabile(String nome) {
		// TODO Auto-generated method stub
	}

	@Override
	public void storeInVariabile(String identificatore, Espressione valore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void costante(int costante) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void costante(String stringa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void somma(Espressione addendo1, Espressione addendo2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sottrazione(Espressione minuendo, Espressione sottraendo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void prodotto(Espressione fattore1, Espressione fattore2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void divisione(Espressione dividendo, Espressione divisore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void maggiore(Espressione parteSinistra, Espressione parteDestra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void minore(Espressione parteSinistra, Espressione parteDestra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void uguaglianza(Espressione parteSinistra, Espressione parteDestra) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stampa(Espressione espressione) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stampa(String stringa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leggi(String identificatore) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leggiElementoVettore(String identificatore, Espressione indice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void se(Espressione ex, Blocco codice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finche(Espressione ex, Blocco codice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void definisciVettore(String identificatore, Integer dimensione) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void caricaElementoVettore(String identificatore, Espressione indice) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeElementoVettore(String identificatore, Espressione indice,
			Espressione elemento) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void espressioneInParentesi(Espressione ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void seAltrimenti(Espressione ex, Blocco codice,
			Blocco codiceAltrimenti) {
		// TODO Auto-generated method stub
		
	}

}
