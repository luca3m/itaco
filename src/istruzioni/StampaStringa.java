package istruzioni;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;

public class StampaStringa implements I {
private String stringa;
	
	public StampaStringa(String stringa) {
		
		this.stringa = stringa;
	}
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		// TODO Auto-generated method stub;
		sc.scriviCostante(stringa);
		sc.scriviStampaStringa();
	}

}
