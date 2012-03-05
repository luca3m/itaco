package main;

public class Runner {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		JasminTarget.compilaFile("prova.ita", true);
		System.out.println("Generato codice .class");
	}

}
