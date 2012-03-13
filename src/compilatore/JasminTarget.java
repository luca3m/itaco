package compilatore;

import istruzioni.S;
import jas.jasError;
import jasmin.ClassFile;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import edu.tum.cup2.generator.exceptions.GeneratorException;
import edu.tum.cup2.parser.LRParser;
import edu.tum.cup2.parser.exceptions.LRParserException;
import edu.tum.cup2.parser.tables.LRParsingTable;

public class JasminTarget extends ScrittoreTarget {

	private Map<String, Integer> tabellaSimboliCorrente = new HashMap<String, Integer>();
	private Map<String, Integer> tabellaSimboliGlobale = tabellaSimboliCorrente;
	
	/**
	 * Inizio del contatore per identificare le variabili
	 */
	private static final int START_ID = 1;
	private int label = 0;
	private int contatoreVariabiliCorrente = START_ID;
	private int contatoreVariabiliGlobali = contatoreVariabiliCorrente;

	public void registraVariabile(String nome) throws SemanticException {
		if (tabellaSimboliCorrente.containsKey(nome)) {
			throw new SemanticException(String.format("La variabile %s è già stata definita", nome));
		}
		tabellaSimboliCorrente.put(nome, contatoreVariabiliCorrente++);
	}

	protected int idVariabile(String nome) throws SemanticException {
		if (tabellaSimboliCorrente.containsKey(nome)) {
			return tabellaSimboliCorrente.get(nome);
		} else
			throw new SemanticException(String.format("La variabile %s non è stata definita", nome));
	}

	protected int numeroVariabili() throws SemanticException {
		return tabellaSimboliCorrente.size();
	}

	protected void pushScope() throws SemanticException {
		tabellaSimboliCorrente = new HashMap<String, Integer>();
		contatoreVariabiliGlobali = contatoreVariabiliCorrente;
		contatoreVariabiliCorrente = 0;
	}
	
	protected void popScope() throws SemanticException {
		tabellaSimboliCorrente = tabellaSimboliGlobale;
		contatoreVariabiliCorrente = contatoreVariabiliGlobali;
	}
	
	PrintStream outputFile;
	String className;
	ByteArrayOutputStream buffer;
	PrintStream bufferOutput;
	
	OutputStream fileOut;
	public JasminTarget(String className, OutputStream output)
			throws IOException {
		this.fileOut = output;
		this.outputFile = new PrintStream(output);
		this.className = className;
		initBuffer();
		writeContentOfStub("initStub.j", "%className", className);
	}

	private void initBuffer() {
		buffer = new ByteArrayOutputStream();
		bufferOutput = new PrintStream(buffer);
	}
	
	private void svuotaBuffer() {
		byte[] ret = buffer.toByteArray();
		outputFile.write(ret, 0, ret.length);
		initBuffer();
	}
	
	private void writeContentOfStub(String stubFileName, String replacePattern,
			String replaceString) throws IOException {
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
			sb.append(fileData, 0, charsRead);
			copiedBytes += charsRead;
		}
		String stubReplaced = sb.toString().replaceAll(replacePattern,
				replaceString);
		outputFile.print(stubReplaced);
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
			outputFile.write(fileData, 0, bytesRead);
			copiedBytes += bytesRead;
		}
	}

	@Override
	public void somma(Espressione addendo1, Espressione addendo2) throws SemanticException {
		addendo1.scriviCodice(this);
		addendo2.scriviCodice(this);
		bufferOutput.println("iadd");
	}

	@Override
	public void caricaVariabile(String nome) throws SemanticException {
		int id = idVariabile(nome);
		bufferOutput.println("iload " + id);
	}

	@Override
	public void costante(int costante) throws SemanticException {
		bufferOutput.println("ldc " + costante);
	}

	@Override
	public void sottrazione(Espressione minuendo, Espressione sottraendo) throws SemanticException {
		minuendo.scriviCodice(this);
		sottraendo.scriviCodice(this);
		bufferOutput.println("isub");
	}

	@Override
	public void prodotto(Espressione fattore1, Espressione fattore2) throws SemanticException {
		fattore1.scriviCodice(this);
		fattore2.scriviCodice(this);
		bufferOutput.println("imul");
	}

	@Override
	public void divisione(Espressione dividendo, Espressione divisore) throws SemanticException {
		dividendo.scriviCodice(this);
		divisore.scriviCodice(this);
		bufferOutput.println("idiv");
	}

	@Override
	public void maggiore(Espressione parteSinistra, Espressione parteDestra) throws SemanticException {
		sottrazione(parteSinistra, parteDestra);
		String labelMaggiore1 = generaLabel();
		String labelMaggiore2 = generaLabel();
		String ifgt = "ifgt ";
		ifgt = ifgt + labelMaggiore1;
		bufferOutput.println(ifgt);
		bufferOutput.println("ldc -1");
		String go_to = "goto ";
		go_to = go_to + labelMaggiore2;
		bufferOutput.println(go_to);
		bufferOutput.println(labelMaggiore1 + ":");
		bufferOutput.println("ldc 1");
		bufferOutput.println(labelMaggiore2 + ": ");
	}

	@Override
	public void minore(Espressione parteSinistra, Espressione parteDestra) throws SemanticException {
		maggiore(parteDestra, parteSinistra);

	}

	@Override
	public void uguaglianza(Espressione parteSinistra, Espressione parteDestra) throws SemanticException {
		sottrazione(parteSinistra, parteDestra);
		String labelMaggiore1 = generaLabel();
		String labelMaggiore2 = generaLabel();
		String ifeq = "ifeq ";
		ifeq = ifeq + labelMaggiore1;
		bufferOutput.println(ifeq);
		bufferOutput.println("ldc -1");
		String go_to = "goto ";
		go_to = go_to + labelMaggiore2;
		bufferOutput.println(go_to);
		bufferOutput.println(labelMaggiore1 + ":");
		bufferOutput.println("ldc 1");
		bufferOutput.println(labelMaggiore2 + ": ");
	}

	@Override
	public void stampa(Espressione espressione) throws SemanticException {
		espressione.scriviCodice(this);
		bufferOutput.println("invokestatic " + className + "/writeInt(I)V");
	}

	@Override
	public void leggi(String identificatore) throws SemanticException {
		bufferOutput.println("invokestatic " + className + "/readInt()I");
		storeInVariabile(identificatore);
	}

	@Override
	public void se(Espressione ex, Blocco b) throws SemanticException {
		ex.scriviCodice(this);
		String labelMaggiore1 = generaLabel();
		String labelMaggiore2 = generaLabel();
		String ifgt = "ifgt ";
		ifgt = ifgt + labelMaggiore1;
		bufferOutput.println(ifgt);
		String go_to = "goto ";
		go_to = go_to + labelMaggiore2;
		bufferOutput.println(go_to);
		bufferOutput.println(labelMaggiore1 + ": ");
		b.scriviCodice(this);
		bufferOutput.println(labelMaggiore2 + ": ");
	}

	public void seAltrimenti(Espressione ex, Blocco b1, Blocco b2) throws SemanticException {
		ex.scriviCodice(this);
		String labelMaggiore1 = generaLabel();
		String labelMaggiore2 = generaLabel();
		String ifgt = "ifgt ";
		ifgt = ifgt + labelMaggiore1;
		bufferOutput.println(ifgt);
		b2.scriviCodice(this);
		String go_to = "goto ";
		go_to = go_to + labelMaggiore2;
		bufferOutput.println(go_to);
		bufferOutput.println(labelMaggiore1 + ": ");
		b1.scriviCodice(this);
		bufferOutput.println(labelMaggiore2 + ": ");

	}

	private void storeInVariabile(String identificatore) throws SemanticException {
		int idVar = this.idVariabile(identificatore);
		bufferOutput.println("istore " + idVar);
	}

	@Override
	public void storeInVariabile(String identificatore, Espressione ex) throws SemanticException {
		ex.scriviCodice(this);
		storeInVariabile(identificatore);
	}

	@Override
	public void espressioneInParentesi(Espressione ex) throws SemanticException {
		ex.scriviCodice(this);
	}

	@Override
	public void finche(Espressione ex, Blocco b) throws SemanticException {
		String labelMaggiore1 = generaLabel();
		String labelMaggiore2 = generaLabel();
		String labelMaggiore3 = generaLabel();
		bufferOutput.println(labelMaggiore1 + ": ");
		ex.scriviCodice(this);
		String ifgt = "ifgt ";
		ifgt = ifgt + labelMaggiore2;
		bufferOutput.println(ifgt);
		bufferOutput.println("goto " + labelMaggiore3);
		bufferOutput.println(labelMaggiore2 + ": ");
		b.scriviCodice(this);
		bufferOutput.println("goto " + labelMaggiore1);
		bufferOutput.println(labelMaggiore3 + ": ");
	}

	public static void compilaFile(String percorsoFile, boolean salvaAssembly) throws GeneratorException, FileNotFoundException, LRParserException, IOException, JasminException, SemanticException {

		File sorgenteFile = new File(percorsoFile);

		// Genero l'AST
		LALR1Generator generator = new LALR1Generator(new ParserSpec()); // we
																			// want
																			// to
																			// use
																			// LALR(1)
		LRParsingTable table = generator.getParsingTable(); // get the resulting
															// parsing table
		LRParser parser = new LRParser(table); // create a new LR parser using
												// our table
		S result = (S) parser.parse(new Scanner(new FileReader(sorgenteFile))); // apply
																				// parser
																				// to
																				// a
																				// token
																				// stream

		// Genero il codice assembly Jasmin
		String nomeClasse = sorgenteFile.getName().split("\\.")[0];
		ByteArrayOutputStream jasminAssemblyBytes = new ByteArrayOutputStream();
		JasminTarget jt = new JasminTarget(nomeClasse, jasminAssemblyBytes);
		result.scriviCodice(jt);
		if (salvaAssembly) {
			String percorsoFileJ;
			if (sorgenteFile.getParent() != null) {
				percorsoFileJ = sorgenteFile.getParent() + File.separator
						+ nomeClasse + ".j";
			} else {
				percorsoFileJ = nomeClasse + ".j";
			}

			FileOutputStream fileOut = new FileOutputStream(percorsoFileJ);
			fileOut.write(jasminAssemblyBytes.toByteArray());
			fileOut.close();
		}

		// Genero il bytecode
		String percorsoFileClass;
		if (sorgenteFile.getParent() != null) {
			percorsoFileClass = sorgenteFile.getParent() + File.separator
					+ nomeClasse + ".class";
		} else {
			percorsoFileClass = nomeClasse + ".class";
		}
		// Oggetto della libreria Jasmin che si occupa di generare il file
		// .class
		ClassFile classFile = new ClassFile();
		// Questa chiamata rocambolesca converte l'output a byte del nostro
		// codice Jasmin in input a caratteri
		// necessario per la libreria Jasmin
		CharArrayReader assemblyInput = new CharArrayReader(new String(
				jasminAssemblyBytes.toByteArray()).toCharArray());
		try {
			classFile.readJasmin(assemblyInput, sorgenteFile.getName(), false);
		} catch (Exception e) {
			throw new JasminException(e.getLocalizedMessage());
		}
		FileOutputStream classFOut = new FileOutputStream(percorsoFileClass);
		try {
			classFile.write(classFOut);
		} catch (jasError e) {
			throw new JasminException(e.getLocalizedMessage());
		}
		classFOut.close();
	}

	@Override
	public void costante(String stringa) throws SemanticException {
		bufferOutput.printf("ldc \"%s\"\n", stringa);
	}

	@Override
	public void stampa(String stringa) throws SemanticException {
		costante(stringa);
		bufferOutput.println("invokestatic " + className
				+ "/writeString(Ljava/lang/String;)V");
	}

	// Operazioni sui vettori
	private Map<String, Integer> dimensioneVettori = new HashMap<String, Integer>();
	
	@Override
	public void definisciVettore(String identificatore, Integer dimensione) throws SemanticException {
		try {
			registraVariabile(identificatore + "[]");
		} catch (SemanticException ex) {
			throw new SemanticException(String.format("Il vettore %s è già stato definito", identificatore));
		}
		int id = idVariabile(identificatore + "[]");
		dimensioneVettori.put(identificatore, dimensione);
		costante(dimensione);
		bufferOutput.println("newarray int");
		bufferOutput.println("astore " + id);
	}

	@Override
	public void leggiElementoVettore(String identificatore, Espressione indice) throws SemanticException {
		bufferOutput.println("aload " + idVariabile(identificatore + "[]"));
		indice.scriviCodice(this);
		bufferOutput.println("invokestatic " + className + "/readInt()I");
		bufferOutput.println("iastore");
	}

	@Override
	public void caricaElementoVettore(String identificatore, Espressione indice) throws SemanticException {
		bufferOutput.println("aload " + idVariabile(identificatore + "[]"));
		indice.scriviCodice(this);
		bufferOutput.println("iaload");
	}

	@Override
	public void storeElementoVettore(String identificatore, Espressione indice,
			Espressione elemento) throws SemanticException {
		bufferOutput.println("aload " + idVariabile(identificatore + "[]"));
		indice.scriviCodice(this);
		elemento.scriviCodice(this);
		bufferOutput.println("iastore");
	}

	public String generaLabel() throws SemanticException {
		String labelCorrente = "L";
		labelCorrente = labelCorrente + label;
		label = label + 1;
		return labelCorrente;
	}

	@Override
	public void scriviMain(Blocco codice) throws SemanticException {
		outputFile.println(".method public static main([Ljava/lang/String;)V");
	    outputFile.println(".limit stack 30");
	   
	    codice.scriviCodice(this);
	    outputFile.println(".limit locals " + (numeroVariabili() + 2));
	    
	    try {
			writeContentOfStub("preMainStub.j", "%className", className);
		} catch (IOException e) {
			e.printStackTrace();
		}
		svuotaBuffer();
		outputFile.println("return");
		outputFile.println(".end method");
	}
	
	// Operazioni sulle funzioni
	
	private Map<String, String> parametriFunzioni = new HashMap<String, String>();
	
	@Override
	public void definisciFunzione(String nome, String[] ingressi,
			String uscita, Blocco codice) throws SemanticException {
		// Se la funzione è stata definita già in precedenza
		// lanciamo un'eccezione
		if (parametriFunzioni.containsKey(nome)) {
			throw new SemanticException(String.format("Funzione %s è già stata definita", nome));
		}
		pushScope();
		outputFile.printf(".method public static %s", nome);
		StringBuilder stringaParametri = new StringBuilder("(");
		for ( String variabile : ingressi) {
			String[] nomeEtipo = variabile.split(":");
			String nomeParametro = nomeEtipo[0];
			String tipo = nomeEtipo[1];
			if (tipo.equals("intero")) {
				registraVariabile(nomeParametro);
				stringaParametri.append("I");
			} else {
				registraVariabile(nomeParametro + "[]");
				registraVariabile(nomeEtipo[2]);
				stringaParametri.append("[II");
			}
		}
		stringaParametri.append(")");
		if (uscita != null) {
			registraVariabile(uscita);
			stringaParametri.append("I");
		} else {
			stringaParametri.append("V");
		}
		
		parametriFunzioni.put(nome, stringaParametri.toString());
		
		outputFile.println(stringaParametri.toString());
		
		outputFile.println(".limit stack 9");
		codice.scriviCodice(this);
		outputFile.println(".limit locals " + numeroVariabili());
		
		if (uscita != null)  {
			caricaVariabile(uscita);
			bufferOutput.println("ireturn");
		} else {
			bufferOutput.println("return");
		}
		svuotaBuffer();
		
		outputFile.println(".end method");
		popScope();
	}

	@Override
	public void eseguiFunzione(String nome, Espressione parametri) throws SemanticException {
		if (parametriFunzioni.get(nome).endsWith("V")) {
			throw new SemanticException(String.format("È stata usata in una espressione la funzione %s che non ritorna valori", nome));
		}
		parametri.scriviCodice(this);
		bufferOutput.printf("invokestatic %s/%s%s\n", className, nome, parametriFunzioni.get(nome));
	}


	@Override
	public void caricaVettore(String nome) throws SemanticException {
		try {
			bufferOutput.println("aload " + idVariabile(nome + "[]"));
		} catch (SemanticException ex) {
			throw new SemanticException(String.format("Il vettore %s non è stato definito", nome));
		}
	}

	@Override
	public void caricaDimensioneVettore(String nome) throws SemanticException {
		costante(dimensioneVettori.get(nome));
	}

	@Override
	public void eseguiFunzioneSenzaRitorno(String nome, Espressione parametri) throws SemanticException {
		if (parametri != null)
			parametri.scriviCodice(this);
		bufferOutput.printf("invokestatic %s/%s%s\n", className, nome, parametriFunzioni.get(nome));
		if (! parametriFunzioni.get(nome).endsWith("V")) {
			bufferOutput.println("pop");
		}
	}

}
