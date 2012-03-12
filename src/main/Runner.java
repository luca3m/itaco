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
		System.out.println("Generato codice .class");
	}

}
