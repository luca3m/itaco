package istruzioni.logiche;

import main.ScrittoreTarget;

public class EspressioneLogicaInParentesi implements L {
	private B espressione;

	public EspressioneLogicaInParentesi(B espressione) {
		this.espressione = espressione;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub
		espressione.scriviCodice(sc);
	}
}
