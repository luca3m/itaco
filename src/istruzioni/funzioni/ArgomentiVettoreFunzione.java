package istruzioni.funzioni;

import compilatore.ScrittoreTarget;

import istruzioni.espressioni.E;
import istruzioni.espressioni.W;

public class ArgomentiVettoreFunzione implements W {

	private String argomentoPrecedente;
	private W argomentiSuccessivi;
	
	public ArgomentiVettoreFunzione(String argomentoPrecedente, W argomentiSuccessivi) {
		this.argomentoPrecedente = argomentoPrecedente;
		this.argomentiSuccessivi = argomentiSuccessivi;
	}
	
	@Override
	public void scriviCodice(ScrittoreTarget sc) {
		
	}

}
