package main;

import istruzioni.N;
import jasmin.ClassFile;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import edu.tum.cup2.generator.LALR1Generator;
import edu.tum.cup2.parser.LRParser;
import edu.tum.cup2.parser.tables.LRParsingTable;

public class JasminTarget extends ScrittoreTarget {
	PrintStream output;
	String className;
	
	public JasminTarget(String className, OutputStream output)
			throws IOException {
		this.output = new PrintStream(output);
		this.className = className;
		writeContentOfStub("preMainStub.j", "%className", className);
	}

	private void writeContentOfStub(String stubFileName, String replacePattern, String replaceString ) throws IOException {
		// Copio il contenuto dello stub nel file di output
		File stubFile = new File(stubFileName);
		FileReader stub = new FileReader(stubFile);
		StringBuilder sb = new StringBuilder();
		int copiedBytes = 0;
		long fileSizeInBytes = stubFile.length();
		int charsRead = 0;
		char[] fileData = new char[1024];

		while (copiedBytes < fileSizeInBytes) {
			charsRead = stub.read(fileData);
			sb.append(charsRead);
			copiedBytes += charsRead;
		}
		String stubReplaced = sb.toString().replaceAll(replacePattern, replaceString);
		output.print(stubReplaced);
	}
	private void writeContentOfStub(String stubFileName) throws IOException {
		// Copio il contenuto dello stub nel file di output
		File stubFile = new File(stubFileName);
		FileInputStream stub = new FileInputStream(stubFile);
		int copiedBytes = 0;
		long fileSizeInBytes = stubFile.length();
		int bytesRead = 0;
		byte[] fileData = new byte[1024];

		while (copiedBytes < fileSizeInBytes) {
			bytesRead = stub.read(fileData);
			output.write(fileData, 0, bytesRead);
			copiedBytes += bytesRead;
		}
	}

	@Override
	public void scriviSomma() {
		output.println("iadd");
	}

	@Override
	public void scriviVariabile(String nome) {
		int id = idVariabile(nome);
		output.println("iload " + id);
	}

	@Override
	public void scriviCostante(int costante) {
		output.println("ldc " + costante);
	}

	@Override
	public void scriviSottrazione() {
		output.println("isub");
	}
	
	@Override
	public void scriviMoltiplicazione() {
		output.println("imul");
	}

	@Override
	public void scriviDivisione() {
		output.println("idiv");
	}

	@Override
	public void scriviMaggiore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void scriviMinore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void scriviUguaglianza() {
		// TODO Auto-generated method stub
	}

	@Override
	public void scriviStampa() {
		output.println("invokestatic "+ className +"/writeInt(I)V");
	}

	@Override
	public void scriviLetturaStandard() {
		// TODO Auto-generated method stub
		output.println("invokestatic "+className+"/readInt()I");
	}

	@Override
	public void scriviAccessoVettore(String nome) {
		// TODO Auto-generated method stub

	}

	@Override
	public void scriviCondizionaleSe() {
		// TODO Auto-generated method stub

	}

	@Override
	public void scriviStoreInVariabile(String identificatore) {
		int idVar=this.idVariabile(identificatore);
		output.println("istore "+idVar);
	}

	@Override
	public void scriviCicloFinche() {
		// TODO Auto-generated method stub

	}

	public void endFile() throws IOException {
		output.printf(".limit locals " + numeroVariabili());
		writeContentOfStub("postMainStub.j");
	}
	
	public static void compilaFile(String percorsoFile, boolean salvaAssembly) throws Exception {
		
		File sorgenteFile = new File(percorsoFile);
		
		// Genero l'AST
		LALR1Generator generator = new LALR1Generator(new ParserSpec()); //we want to use LALR(1)
	    LRParsingTable table = generator.getParsingTable(); //get the resulting parsing table
	    LRParser parser = new LRParser(table); //create a new LR parser using our table
	    N result = (N) parser.parse(new Scanner(new FileReader(sorgenteFile))); //apply parser to a token stream
		
	    // Genero il codice assembly Jasmin
	    String nomeClasse = sorgenteFile.getName().split("\\.")[0];
		ByteArrayOutputStream jasminAssemblyBytes = new ByteArrayOutputStream();
	    JasminTarget jt = new JasminTarget(nomeClasse, jasminAssemblyBytes);
	    result.scriviCodice(jt);
	    jt.endFile();
	    if (salvaAssembly) {
	    	String percorsoFileJ;
	    	if (sorgenteFile.getParent() != null) {
	    		percorsoFileJ =  sorgenteFile.getParent() + File.pathSeparator + nomeClasse + ".j";
	    	} else {
	    		percorsoFileJ =  nomeClasse + ".j";
	    	}
	    	
			FileOutputStream fileOut = new FileOutputStream(percorsoFileJ);
			fileOut.write(jasminAssemblyBytes.toByteArray());
			fileOut.close();
	    }
	    
	    // Genero il bytecode
	    String percorsoFileClass;
    	if (sorgenteFile.getParent() != null) {
    		percorsoFileClass =  sorgenteFile.getParent() + File.pathSeparator + nomeClasse + ".class";
    	} else {
    		percorsoFileClass =  nomeClasse + ".class";
    	}
		// Oggetto della libreria Jasmin che si occupa di generare il file .class
	    ClassFile classFile = new ClassFile();
	    // Questa chiamata rocambolesca converte l'output a byte del nostro codice Jasmin in input a caratteri
	    // necessario per la libreria Jasmin
	    CharArrayReader assemblyInput = new CharArrayReader(new String(jasminAssemblyBytes.toByteArray()).toCharArray());
		classFile.readJasmin(assemblyInput, sorgenteFile.getName(), false);
		FileOutputStream classFOut = new FileOutputStream(percorsoFileClass);
		classFile.write(classFOut);
		classFOut.close();
	}
}
