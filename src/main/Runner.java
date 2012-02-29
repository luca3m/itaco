package main;

import istruzioni.N;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import edu.tum.cup2.generator.LALR1Generator;
import edu.tum.cup2.generator.LR0Generator;
import edu.tum.cup2.generator.LR1Generator;
import edu.tum.cup2.generator.exceptions.GeneratorException;
import edu.tum.cup2.parser.LRParser;
import edu.tum.cup2.parser.exceptions.LRParserException;
import edu.tum.cup2.parser.tables.LRParsingTable;

public class Runner {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws GeneratorException 
	 * @throws LRParserException 
	 */
	public static void main(String[] args) throws IOException, GeneratorException, LRParserException {
		// TODO Auto-generated method stub
		LALR1Generator generator = new LALR1Generator(new ParserSpec()); //we want to use LALR(1)
	    LRParsingTable table = generator.getParsingTable(); //get the resulting parsing table
	    LRParser parser = new LRParser(table); //create a new LR parser using our table
	    N result = (N) parser.parse(new Scanner(new FileReader("prova.ita"))); //apply parser to a token stream
	    JasminTarget jT = new JasminTarget("prova", new FileOutputStream("prova.j"));
	    result.scriviCodice(jT);
	    jT.endFile();
	    System.out.println("Generato codice Jasmin");
	}

}
