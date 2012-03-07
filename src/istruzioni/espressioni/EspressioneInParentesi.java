package istruzioni.espressioni;

import compilatore.ScrittoreTarget;

public class EspressioneInParentesi implements F {
	private E espressione;

	public EspressioneInParentesi(E espressione) {
		this.espressione = espressione;
	}

	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		sc.espressioneInParentesi(espressione);
	}
	
}
