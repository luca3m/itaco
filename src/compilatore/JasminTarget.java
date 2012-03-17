package compilatore;

import istruzioni.S;
import jas.jasError;
import jasmin.ClassFile;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

/**
 * Classe che genera codice Jasmin dato un AST. La classe viene usata tramite il
 * metodo public static void compilaFile(String percorsoFile)
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class JasminTarget extends ScrittoreTarget {

	/*
	 * Tabelle dei simboli utilizzate. Due tabelle sono utilizzate per garantire
	 * diversi scope
	 */
	private Map<String, Integer> tabellaSimboliCorrente = new HashMap<String, Integer>();
	private Map<String, Integer> tabellaSimboliGlobale = tabellaSimboliCorrente;

	/**
	 * Inizio del contatore per identificare le variabili
	 */
	private static final int START_ID = 1;
	private int label = 0;
	private int contatoreVariabiliCorrente = START_ID;
	private int contatoreVariabiliGlobali = contatoreVariabiliCorrente;

	/**
	 * Metodo per aggiungere una variabile alla tabella dei simboli
	 * 
	 * @param nome
	 *            nome della variabile da inserire
	 * @throws EccezioneSemantica
	 *             lancia eccezione se la variabile è già presente
	 */
	public void registraVariabile(String nome) throws EccezioneSemantica {
		if (tabellaSimboliCorrente.containsKey(nome)) {
			throw new EccezioneSemantica(String.format(
					"La variabile %s è già stata definita", nome));
		}
		tabellaSimboliCorrente.put(nome, contatoreVariabiliCorrente++);
	}

	protected int idVariabile(String nome) throws EccezioneSemantica {
		if (tabellaSimboliCorrente.containsKey(nome)) {
			return tabellaSimboliCorrente.get(nome);
		} else
			throw new EccezioneSemantica(String.format(
					"La variabile %s non è stata definita", nome));
	}

	protected int numeroVariabili() throws EccezioneSemantica {
		return tabellaSimboliCorrente.size();
	}

	protected void pushScope() throws EccezioneSemantica {
		tabellaSimboliCorrente = new HashMap<String, Integer>();
		contatoreVariabiliGlobali = contatoreVariabiliCorrente;
		contatoreVariabiliCorrente = 0;
	}

	protected void popScope() throws EccezioneSemantica {
		tabellaSimboliCorrente = tabellaSimboliGlobale;
		contatoreVariabiliCorrente = contatoreVariabiliGlobali;
	}

	private PrintStream outputFile;
	private String className;
	private ByteArrayOutputStream buffer;
	private PrintStream bufferOutput;

	/**
	 * Costruttore che accetta il nome della classe e lo stream di output in cui
	 * si dovrà salvare il codice target
	 * 
	 * @param className
	 * @param output
	 * @throws IOException
	 */
	public JasminTarget(String className, OutputStream output)
			throws IOException {
		this.outputFile = new PrintStream(output);
		this.className = className;
		initBuffer();
		scriviContenutoStub("initStub.j", "%className", className);
	}

	/*
	 * Utility per inizializzare e svuotare un buffer. Tutto il codice viene
	 * prima scritto in questo buffer per consentire il calcolo del numero di
	 * variabili da utilizzare nel programma e quindi settare correttamente i
	 * valori di .limit e .stack
	 */
	private void initBuffer() {
		buffer = new ByteArrayOutputStream();
		bufferOutput = new PrintStream(buffer);
	}

	private void svuotaBuffer() {
		byte[] ret = buffer.toByteArray();
		outputFile.write(ret, 0, ret.length);
		initBuffer();
	}

	/*
	 * Scrive nel linguaggio target delle parti di codice indipendenti dallo
	 * specifico programma
	 */
	private void scriviContenutoStub(String stubFileName,
			String replacePattern, String replaceString) throws IOException {
		/*
		 * Copio il contenuto dello stub nel file di output
		 */
		InputStreamReader stub = new InputStreamReader(JasminTarget.class.getResourceAsStream("/compilatore/stub/"+stubFileName));
		StringBuilder sb = new StringBuilder();
		int charsRead = 0;
		char[] fileData = new char[1024];

		while (stub.ready()) {
			charsRead = stub.read(fileData);
			sb.append(fileData, 0, charsRead);
		}
		String stubReplaced = sb.toString().replaceAll(replacePattern,
				replaceString);
		outputFile.print(stubReplaced);
	}

	/*
	 * Scrive nel linguaggio target delle parti di codice indipendenti dallo
	 * specifico programma
	 */
	@SuppressWarnings("unused")
	private void scriviContenutoStub(String stubFileName) throws IOException {
		/*
		 * Copio il contenuto dello stub nel file di output
		 */
		InputStream stub = JasminTarget.class.getResourceAsStream("/compilatore/stub/"+stubFileName);
		int copiedBytes = 0;
		int bytesRead = 0;
		byte[] fileData = new byte[1024];

		while (stub.available() > 0) {
			bytesRead = stub.read(fileData);
			outputFile.write(fileData, 0, bytesRead);
			copiedBytes += bytesRead;
		}
	}

	@Override
	public void somma(Espressione addendo1, Espressione addendo2)
			throws EccezioneSemantica {
		addendo1.scriviCodice(this);
		addendo2.scriviCodice(this);
		bufferOutput.println("iadd");
	}

	@Override
	public void caricaVariabile(String nome) throws EccezioneSemantica {
		int id = idVariabile(nome);
		bufferOutput.println("iload " + id);
	}

	@Override
	public void costante(int costante) throws EccezioneSemantica {
		bufferOutput.println("ldc " + costante);
	}

	@Override
	public void sottrazione(Espressione minuendo, Espressione sottraendo)
			throws EccezioneSemantica {
		minuendo.scriviCodice(this);
		sottraendo.scriviCodice(this);
		bufferOutput.println("isub");
	}

	@Override
	public void prodotto(Espressione fattore1, Espressione fattore2)
			throws EccezioneSemantica {
		fattore1.scriviCodice(this);
		fattore2.scriviCodice(this);
		bufferOutput.println("imul");
	}

	@Override
	public void divisione(Espressione dividendo, Espressione divisore)
			throws EccezioneSemantica {
		dividendo.scriviCodice(this);
		divisore.scriviCodice(this);
		bufferOutput.println("idiv");
	}

	@Override
	public void maggiore(Espressione parteSinistra, Espressione parteDestra)
			throws EccezioneSemantica {
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
	public void minore(Espressione parteSinistra, Espressione parteDestra)
			throws EccezioneSemantica {
		maggiore(parteDestra, parteSinistra);
	}

	@Override
	public void uguaglianza(Espressione parteSinistra, Espressione parteDestra)
			throws EccezioneSemantica {
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
	public void stampa(Espressione espressione) throws EccezioneSemantica {
		espressione.scriviCodice(this);
		bufferOutput.println("invokestatic " + className + "/writeInt(I)V");
	}

	@Override
	public void leggi(String identificatore) throws EccezioneSemantica {
		bufferOutput.println("invokestatic " + className + "/readInt()I");
		storeInVariabile(identificatore);
	}

	@Override
	public void se(Espressione ex, Blocco b) throws EccezioneSemantica {
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

	@Override
	public void seAltrimenti(Espressione ex, Blocco b1, Blocco b2)
			throws EccezioneSemantica {
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

	/*
	 * Salva una variabile in memoria
	 */
	private void storeInVariabile(String identificatore)
			throws EccezioneSemantica {
		int idVar = this.idVariabile(identificatore);
		bufferOutput.println("istore " + idVar);
	}

	@Override
	public void storeInVariabile(String identificatore, Espressione ex)
			throws EccezioneSemantica {
		ex.scriviCodice(this);
		storeInVariabile(identificatore);
	}

	@Override
	public void espressioneInParentesi(Espressione ex)
			throws EccezioneSemantica {
		ex.scriviCodice(this);
	}

	@Override
	public void finche(Espressione ex, Blocco b) throws EccezioneSemantica {
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

	/**
	 * Esegue tutte le operazioni per generare il file conetenete il codice C e
	 * salva il codice sul file specificato
	 * 
	 * @param percorsoFile
	 *            percorso del file che conterrà il codice sorgente
	 * @throws Exception
	 */
	public static void compilaFile(String percorsoFile, boolean salvaAssembly)
			throws GeneratorException, FileNotFoundException,
			LRParserException, IOException, JasminException, EccezioneSemantica {

		File sorgenteFile = new File(percorsoFile);
		/**
		 * Creo un parser LALR1
		 */
		LALR1Generator generator = new LALR1Generator(new ParserSpec());
		/**
		 * Prendi la parsing table generata
		 */
		LRParsingTable table = generator.getParsingTable();
		/**
		 * Crea un parser LR dalla tabella generata
		 */
		LRParser parser = new LRParser(table);
		/**
		 * Usa il parser per generare l'AST dallo stream di token passati dallo
		 * scanner
		 */
		S result = (S) parser.parse(new Scanner(new FileReader(sorgenteFile)));
		/**
		 * Genero il codice Jasmin
		 */
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
		/*
		 * Genero il bytecode
		 */
		String percorsoFileClass;
		if (sorgenteFile.getParent() != null) {
			percorsoFileClass = sorgenteFile.getParent() + File.separator
					+ nomeClasse + ".class";
		} else {
			percorsoFileClass = nomeClasse + ".class";
		}
		/*
		 * Oggetto della libreria Jasmin che si occupa di generare il file
		 * .class
		 */
		ClassFile classFile = new ClassFile();
		/**
		 * Questa chiamata rocambolesca converte l'output a byte del nostro
		 * codice Jasmin in input a caratteri necessario per la libreria Jasmin
		 */
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
	public void costante(String stringa) throws EccezioneSemantica {
		bufferOutput.print("ldc \"" + stringa.replaceAll("\n", "\\\\n")
				+ "\"\n");
	}

	@Override
	public void stampa(String stringa) throws EccezioneSemantica {
		costante(stringa);
		bufferOutput.println("invokestatic " + className
				+ "/writeString(Ljava/lang/String;)V");
	}

	/*
	 * Operazioni sui vettori
	 */
	private Map<String, Integer> dimensioneVettori = new HashMap<String, Integer>();

	@Override
	public void definisciVettore(String identificatore, Integer dimensione)
			throws EccezioneSemantica {
		try {
			registraVariabile(identificatore + "[]");
		} catch (EccezioneSemantica ex) {
			throw new EccezioneSemantica(String.format(
					"Il vettore %s è già stato definito", identificatore));
		}
		int id = idVariabile(identificatore + "[]");
		dimensioneVettori.put(identificatore, dimensione);
		costante(dimensione);
		bufferOutput.println("newarray int");
		bufferOutput.println("astore " + id);
	}

	@Override
	public void leggiElementoVettore(String identificatore, Espressione indice)
			throws EccezioneSemantica {
		try {
			bufferOutput.println("aload " + idVariabile(identificatore + "[]"));
		} catch (EccezioneSemantica ex) {
			throw new EccezioneSemantica("Il vettore " + identificatore
					+ " non è stato definito");
		}
		indice.scriviCodice(this);
		bufferOutput.println("invokestatic " + className + "/readInt()I");
		bufferOutput.println("iastore");
	}

	@Override
	public void caricaElementoVettore(String identificatore, Espressione indice)
			throws EccezioneSemantica {
		try {
			bufferOutput.println("aload " + idVariabile(identificatore + "[]"));
		} catch (EccezioneSemantica ex) {
			throw new EccezioneSemantica("Il vettore " + identificatore
					+ " non è stato definito");
		}
		indice.scriviCodice(this);
		bufferOutput.println("iaload");
	}

	@Override
	public void storeElementoVettore(String identificatore, Espressione indice,
			Espressione elemento) throws EccezioneSemantica {
		try {
			bufferOutput.println("aload " + idVariabile(identificatore + "[]"));
		} catch (EccezioneSemantica ex) {
			throw new EccezioneSemantica("Il vettore " + identificatore
					+ " non è stato definito");
		}
		indice.scriviCodice(this);
		elemento.scriviCodice(this);
		bufferOutput.println("iastore");
	}

	/**
	 * Metodo che genera label sempre diverse
	 * @return nuova label
	 * @throws EccezioneSemantica
	 */
	public String generaLabel() throws EccezioneSemantica {
		String labelCorrente = "L";
		labelCorrente = labelCorrente + label;
		label = label + 1;
		return labelCorrente;
	}

	@Override
	public void scriviMain(Blocco codice) throws EccezioneSemantica {
		outputFile.println(".method public static main([Ljava/lang/String;)V");
		outputFile.println(".limit stack 30");

		codice.scriviCodice(this);
		outputFile.println(".limit locals " + (numeroVariabili() + 2));

		try {
			scriviContenutoStub("preMainStub.j", "%className", className);
		} catch (IOException e) {
			e.printStackTrace();
		}
		svuotaBuffer();
		stampa("\n");
		outputFile.println("return");
		outputFile.println(".end method");
	}

	/**
	 * Metodi per le funzioni
	 */
	
	private Map<String, String> parametriFunzioni = new HashMap<String, String>();

	@Override
	public void definisciFunzione(String nome, String[] ingressi,
			String uscita, Blocco codice) throws EccezioneSemantica {
		// Se la funzione è stata definita già in precedenza
		// lanciamo un'eccezione
		if (parametriFunzioni.containsKey(nome)) {
			throw new EccezioneSemantica(String.format(
					"Funzione %s è già stata definita", nome));
		}
		pushScope();
		outputFile.printf(".method public static %s", nome);
		StringBuilder stringaParametri = new StringBuilder("(");
		for (String variabile : ingressi) {
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

		if (uscita != null) {
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
	public void eseguiFunzione(String nome, Espressione parametri)
			throws EccezioneSemantica {
		if (parametriFunzioni.get(nome).endsWith("V")) {
			throw new EccezioneSemantica(
					String.format(
							"È stata usata in una espressione la funzione %s che non ritorna valori",
							nome));
		}
		parametri.scriviCodice(this);
		bufferOutput.printf("invokestatic %s/%s%s\n", className, nome,
				parametriFunzioni.get(nome));
	}

	@Override
	public void caricaVettore(String nome) throws EccezioneSemantica {
		try {
			bufferOutput.println("aload " + idVariabile(nome + "[]"));
		} catch (EccezioneSemantica ex) {
			throw new EccezioneSemantica(String.format(
					"Il vettore %s non è stato definito", nome));
		}
	}

	@Override
	public void caricaDimensioneVettore(String nome) throws EccezioneSemantica {
		costante(dimensioneVettori.get(nome));
	}

	@Override
	public void eseguiFunzioneSenzaRitorno(String nome, Espressione parametri)
			throws EccezioneSemantica {
		if (parametri != null)
			parametri.scriviCodice(this);
		bufferOutput.printf("invokestatic %s/%s%s\n", className, nome,
				parametriFunzioni.get(nome));
		if (!parametriFunzioni.get(nome).endsWith("V")) {
			bufferOutput.println("pop");
		}
	}

}
