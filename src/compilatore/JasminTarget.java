package compilatore;

import istruzioni.N;
import istruzioni.espressioni.B;
import istruzioni.espressioni.Costante;
import istruzioni.espressioni.E;
import istruzioni.espressioni.F;
import istruzioni.espressioni.T;
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
import java.util.HashMap;
import java.util.Map;

import main.ParserSpec;
import main.Scanner;

import edu.tum.cup2.generator.LALR1Generator;
import edu.tum.cup2.parser.LRParser;
import edu.tum.cup2.parser.tables.LRParsingTable;

public class JasminTarget extends ScrittoreTarget {
	
	private Map<String, Integer> tabellaSimboli = new HashMap<String, Integer>();
	/**
	 * Inizio del contatore per identificare le variabili
	 */
	private static final int START_ID = 1;

	private static int contatoreVariabili = START_ID;
	
	public boolean registraVariabile(String nome) {
		if(tabellaSimboli.containsKey(nome)){
			return false;
		}
		tabellaSimboli.put(nome, contatoreVariabili++);
		return true;
	}

	protected int idVariabile(String nome) {
		if (tabellaSimboli.containsKey(nome)) {
			return tabellaSimboli.get(nome);
		} else
			return -1;
	}

	protected int numeroVariabili() {
		return tabellaSimboli.size();
	}
	
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
			sb.append(fileData, 0,  charsRead);
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
	public void somma(B addendo1, T addendo2) {
		output.println("iadd");
	}

	@Override
	public void caricaVariabile(String nome) {
		int id = idVariabile(nome);
		output.println("iload " + id);
	}

	@Override
	public void costante(int costante) {
		output.println("ldc " + costante);
	}

	@Override
	public void sottrazione(B minuendo, T sottraendo) {
		output.println("isub");
	}
	
	@Override
	public void prodotto(T fattore1, F fattore2) {
		output.println("imul");
	}

	@Override
	public void divisione(T dividendo, F divisore) {
		output.println("idiv");
	}

	@Override
	public void maggiore(E parteSinistra, B parteDestra) {
		// TODO Auto-generated method stub

	}

	@Override
	public void minore(E parteSinistra, B parteDestra) {
		// TODO Auto-generated method stub

	}

	@Override
	public void uguaglianza(E parteSinistra, B parteDestra) {
		// TODO Auto-generated method stub
	}

	@Override
	public void stampa(E espressione) {
		output.println("invokestatic "+ className +"/writeInt(I)V");
	}

	@Override
	public void leggi(String identificatore) {
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
		int idVar = this.idVariabile(identificatore);
		output.println("istore "+idVar);
	}

	@Override
	public void scriviCicloFinche() {
		// TODO Auto-generated method stub

	}

	public void endFile() throws IOException {
		
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

	@Override
	public void scriviCostante(String stringa) {
		output.printf("ldc \"%s\"\n", stringa);
	}

	@Override
	public void scriviStampaStringa() {
		output.println("invokestatic "+ className +"/writeString(Ljava/lang/String;)V");
	}

	@Override
	public void definisciVettore(String identificatore, Costante dimensione) {
		boolean status = registraVariabile(identificatore + "[]");
		if (status == false) {
			// FIXME: lanciare una eccezione
		}
		int id = idVariabile(identificatore + "[]");
		dimensione.scriviCodice(this);
	    output.println("newarray int");
	    output.println("astore " + id);
	}
	
	
}
