package main;

import compilatore.CTarget;
import compilatore.JasminTarget;

public class Runner {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		CTarget.compilaFile("prova.ita");
		JasminTarget.compilaFile("prova.ita", true);
		System.out.println("Generato codice .class");
	}

}
