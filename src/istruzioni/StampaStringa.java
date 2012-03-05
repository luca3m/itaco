package istruzioni;

import istruzioni.espressioni.E;
import main.ScrittoreTarget;

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
