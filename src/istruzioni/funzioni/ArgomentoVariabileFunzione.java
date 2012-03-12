package istruzioni.funzioni;

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
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub

	}

}
