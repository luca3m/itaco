package istruzioni.funzioni;

import compilatore.ScrittoreTarget;

import istruzioni.N;
import istruzioni.Z;

public class DefinizioneFunzione implements Z {

	private String nomeFunzione;
	private A argomenti;
	private String idValoreRitorno = null;
	private N codiceFunzione;

	public DefinizioneFunzione(String nomeFunzione, A argomenti,
			String valoreRitorno, N codiceFunzione) {
		this.nomeFunzione = nomeFunzione;
		this.argomenti = argomenti;
		this.idValoreRitorno = valoreRitorno;
		this.codiceFunzione = codiceFunzione;
	}

	public DefinizioneFunzione(String nomeFunzione, A argomenti,
			N codiceFunzione) {
		this.nomeFunzione = nomeFunzione;
		this.argomenti = argomenti;
		this.codiceFunzione = codiceFunzione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		// mettere il controllo se il valore di ritorno è null la f non ritorna
		// valori
	}

}
