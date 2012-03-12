package istruzioni.funzioni;

import compilatore.ScrittoreTarget;

public class ArgomentiDefinizioneFunzione implements A2 {

	private R argomento;
	private A2 prossimoArgomento;
	
	public ArgomentiDefinizioneFunzione(R argomento, A2 prossimoArgomento) {
		this.argomento = argomento;
		this.prossimoArgomento = prossimoArgomento;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub

	}

}
