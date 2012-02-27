package main;

import java.io.FileInputStream;
import java.io.IOException;

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
		Scanner scanner = new Scanner(new FileInputStream("prova.ita"));
		LRParsingTable table = new LR0Generator(new ParserSpec()).getParsingTable(); //here, we use the LR(1) generator
		LRParser parser = new LRParser(table);
		parser.parse(scanner); //contains "13+25*23+4/2*((4+3)*2)*5"
	}

}
