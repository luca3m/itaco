package istruzioni.funzioni;

import java.util.LinkedList;
import java.util.List;

import compilatore.ScrittoreTarget;
import compilatore.EccezioneSemantica;

import istruzioni.N;
import istruzioni.Z;

/**
 * Classe che implementa la definizione di una funzione
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
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
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica {
		List<String> listaArgomenti = new LinkedList<String>();
		if (argomenti != null) {
			argomenti.aggiungiALista(listaArgomenti);
		}
		sc.definisciFunzione(nomeFunzione,
				listaArgomenti.toArray(new String[0]), idValoreRitorno,
				codiceFunzione);
	}

}
