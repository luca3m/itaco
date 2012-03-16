package istruzioni.funzioni;

import java.util.List;

/**
 * Classe che implementa il passaggio di una variabile come argomento di una
 * funzione. Per la chiamata di una funzione viene usata una normale espressione
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class ArgomentoVariabileDefinizioneFunzione implements R {

	private String nomeVariabile;

	/**
	 * @param nomeVariabile
	 */
	public ArgomentoVariabileDefinizioneFunzione(String nomeVariabile) {
		this.nomeVariabile = nomeVariabile;
	}

	@Override
	public void aggiungiALista(List<String> lista) {
		lista.add(nomeVariabile + ":intero");
	}

}
