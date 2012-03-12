package istruzioni.funzioni;

import java.util.List;

import compilatore.ScrittoreTarget;

public class ArgomentoVariabileFunzione implements R {

	private String nomeVariabile;
	
	/**
	 * @param nomeVariabile
	 */
	public ArgomentoVariabileFunzione(String nomeVariabile) {
		this.nomeVariabile = nomeVariabile;
	}

	@Override
	public void aggiungiALista(List<String> lista) {
		lista.add(nomeVariabile + ":intero");
	}

}
