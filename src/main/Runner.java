package main;

import java.io.FileInputStream;
import java.io.IOException;

public class Runner {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(new FileInputStream("prova.ita"));
		Symbol token;
		do {
			token = scanner.yylex();
			System.out.printf("Trovato token %s\n", token.toString());
		} while (token != Symbol.FINE_FILE);
	}

}
