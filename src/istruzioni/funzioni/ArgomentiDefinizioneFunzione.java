package istruzioni.funzioni;

import java.util.List;

/**
 * Classe che implementa gli argomenti nella definizione di una funzione
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class ArgomentiDefinizioneFunzione implements A2 {

	private R argomento;
	private A2 prossimoArgomento;

	public ArgomentiDefinizioneFunzione(R argomento, A2 prossimoArgomento) {
		this.argomento = argomento;
		this.prossimoArgomento = prossimoArgomento;
	}

	@Override
	public void aggiungiALista(List<String> lista) {
		argomento.aggiungiALista(lista);
		prossimoArgomento.aggiungiALista(lista);
	}

}
