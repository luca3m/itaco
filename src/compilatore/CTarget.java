/**
 * 
 */
package compilatore;

import istruzioni.S;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;

import main.ParserSpec;
import main.Scanner;
import edu.tum.cup2.generator.LALR1Generator;
import edu.tum.cup2.parser.LRParser;
import edu.tum.cup2.parser.tables.LRParsingTable;

/**
 * @author ale
 * 
 */
public class CTarget extends ScrittoreTarget {

	private HashMap<String, Integer> mappaDimensioneVettori= new HashMap<String, Integer>();
	private PrintStream ps;

	public CTarget(OutputStream o) throws SemanticException {
		this.ps = new PrintStream(o);
		ps.println("#include <stdio.h>\n#include <stdlib.h>\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#registraVariabile(java.lang.String)
	 */
	@Override
	public void registraVariabile(String nome) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("int "+nome+";\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#caricaVariabile(java.lang.String)
	 */
	@Override
	public void caricaVariabile(String nome) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#storeInVariabile(java.lang.String,
	 * compilatore.Espressione)
	 */
	@Override
	public void storeInVariabile(String identificatore, Espressione valore) throws SemanticException {
		// TODO Auto-generated method stub
		String linea = identificatore + " = ";
		ps.printf(linea);
		valore.scriviCodice(this);
		ps.println(";");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#costante(int)
	 */
	@Override
	public void costante(int costante) throws SemanticException {
		ps.print(costante);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#costante(java.lang.String)
	 */
	@Override
	public void costante(String stringa) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("\"" + stringa.replaceAll("\\", "\\\\") + "\"");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#somma(compilatore.Espressione,
	 * compilatore.Espressione)
	 */
	@Override
	public void somma(Espressione addendo1, Espressione addendo2) throws SemanticException {
		// TODO Auto-generated method stub
		addendo1.scriviCodice(this);
		ps.print(" + ");
		addendo2.scriviCodice(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#sottrazione(compilatore.Espressione,
	 * compilatore.Espressione)
	 */
	@Override
	public void sottrazione(Espressione minuendo, Espressione sottraendo) throws SemanticException {
		// TODO Auto-generated method stub
		minuendo.scriviCodice(this);
		ps.print(" - ");
		sottraendo.scriviCodice(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#prodotto(compilatore.Espressione,
	 * compilatore.Espressione)
	 */
	@Override
	public void prodotto(Espressione fattore1, Espressione fattore2) throws SemanticException {
		// TODO Auto-generated method stub
		fattore1.scriviCodice(this);
		ps.print(" * ");
		fattore2.scriviCodice(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#divisione(compilatore.Espressione,
	 * compilatore.Espressione)
	 */
	@Override
	public void divisione(Espressione dividendo, Espressione divisore) throws SemanticException {
		// TODO Auto-generated method stub
		dividendo.scriviCodice(this);
		ps.print(" / ");
		divisore.scriviCodice(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#maggiore(compilatore.Espressione,
	 * compilatore.Espressione)
	 */
	@Override
	public void maggiore(Espressione parteSinistra, Espressione parteDestra) throws SemanticException {
		// TODO Auto-generated method stub
		parteSinistra.scriviCodice(this);
		ps.print(" > ");
		parteDestra.scriviCodice(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#minore(compilatore.Espressione,
	 * compilatore.Espressione)
	 */
	@Override
	public void minore(Espressione parteSinistra, Espressione parteDestra) throws SemanticException {
		// TODO Auto-generated method stub
		parteSinistra.scriviCodice(this);
		ps.print(" < ");
		parteDestra.scriviCodice(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#uguaglianza(compilatore.Espressione,
	 * compilatore.Espressione)
	 */
	@Override
	public void uguaglianza(Espressione parteSinistra, Espressione parteDestra) throws SemanticException {
		// TODO Auto-generated method stub
		parteSinistra.scriviCodice(this);
		ps.print(" == ");
		parteDestra.scriviCodice(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * compilatore.ScrittoreTarget#espressioneInParentesi(compilatore.Espressione
	 * )
	 */
	@Override
	public void espressioneInParentesi(Espressione ex) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("(");
		ex.scriviCodice(this);
		ps.print(")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#stampa(compilatore.Espressione)
	 */
	@Override
	public void stampa(Espressione espressione) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("printf(\"%d\",");
		espressione.scriviCodice(this);
		ps.print(");\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#stampa(java.lang.String)
	 */
	@Override
	public void stampa(String stringa) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("printf(\"" + stringa + "\");\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#leggi(java.lang.String)
	 */
	@Override
	public void leggi(String identificatore) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("scanf(\"%d\", &" + identificatore + ");\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#leggiElementoVettore(java.lang.String,
	 * compilatore.Espressione)
	 */
	@Override
	public void leggiElementoVettore(String identificatore, Espressione indice) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("scanf(\"%d\", " + identificatore + "+");
		indice.scriviCodice(this);
		ps.print(");\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#se(compilatore.Espressione,
	 * compilatore.Blocco)
	 */
	@Override
	public void se(Espressione ex, Blocco codice) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("if(");
		ex.scriviCodice(this);
		ps.print("){\n");
		codice.scriviCodice(this);
		ps.print("\n}\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#seAltrimenti(compilatore.Espressione,
	 * compilatore.Blocco, compilatore.Blocco)
	 */
	@Override
	public void seAltrimenti(Espressione ex, Blocco codice,
			Blocco codiceAltrimenti) throws SemanticException {
		// TODO Auto-generated method stub
		se(ex, codice);
		ps.print("else {\n");
		codiceAltrimenti.scriviCodice(this);
		ps.print("\n}\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#finche(compilatore.Espressione,
	 * compilatore.Blocco)
	 */
	@Override
	public void finche(Espressione ex, Blocco codice) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("while(");
		ex.scriviCodice(this);
		ps.print("){\n");
		codice.scriviCodice(this);
		ps.print("\n}\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#definisciVettore(java.lang.String,
	 * java.lang.Integer)
	 */
	@Override
	public void definisciVettore(String identificatore, Integer dimensione) throws SemanticException {
		// TODO Auto-generated method stub
		mappaDimensioneVettori.put(identificatore, dimensione);
		ps.print("int " + identificatore + "[" + dimensione + "];\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#caricaElementoVettore(java.lang.String,
	 * compilatore.Espressione)
	 */
	@Override
	public void caricaElementoVettore(String identificatore, Espressione indice) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print(identificatore + "[");
		indice.scriviCodice(this);
		ps.print("]");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#storeElementoVettore(java.lang.String,
	 * compilatore.Espressione, compilatore.Espressione)
	 */
	@Override
	public void storeElementoVettore(String identificatore, Espressione indice,
			Espressione elemento) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print(identificatore + "[");
		indice.scriviCodice(this);
		ps.print("] = ");
		elemento.scriviCodice(this);
		ps.print(";\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#definisciFunzione(java.lang.String,
	 * java.lang.String[], java.lang.String, compilatore.Blocco)
	 */
	@Override
	public void definisciFunzione(String nome, String[] ingressi,
			String uscita, Blocco codice) throws SemanticException {
		// TODO Auto-generated method stub
		if (uscita == null) {
			ps.print("void ");
		}
		else ps.print("int ");
		ps.print(nome);
		StringBuilder stringaParametri = new StringBuilder("(");
		for ( String variabile : ingressi) {
			String[] nomeEtipo = variabile.split(":");
			String nomeParametro = nomeEtipo[0];
			String tipo = nomeEtipo[1];
			if (tipo.equals("intero")) {
				stringaParametri.append("int "+ nomeParametro+", ");
			} else {
				stringaParametri.append("int "+ nomeParametro+"[] , int "+nomeEtipo[2]+", ");
			}
		}
		stringaParametri.delete(stringaParametri.length()-2, stringaParametri.length());
		ps.print(stringaParametri);
		ps.print("){\n");
		if (uscita != null)
			registraVariabile(uscita);
		codice.scriviCodice(this);
		if (uscita != null) {
			ps.println("return " + uscita + ";");
		}
		ps.print("\n}\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#eseguiFunzione(java.lang.String,
	 * compilatore.Espressione)
	 */
	@Override
	public void eseguiFunzione(String nome, Espressione parametri) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print(nome+"(");
		parametri.scriviCodice(this);
		//FIXME
		ps.print(")");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * compilatore.ScrittoreTarget#eseguiFunzioneSenzaRitorno(java.lang.String,
	 * compilatore.Espressione)
	 */
	@Override
	public void eseguiFunzioneSenzaRitorno(String nome, Espressione parametri) throws SemanticException {
		// TODO Auto-generated method stub
		eseguiFunzione(nome, parametri);
		ps.print(";\n");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#scriviMain(compilatore.Blocco)
	 */
	@Override
	public void scriviMain(Blocco codice) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print("main() {\n");
		codice.scriviCodice(this);
		ps.print("\n}");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see compilatore.ScrittoreTarget#caricaVettore(java.lang.String)
	 */
	@Override
	public void caricaVettore(String nome) throws SemanticException {
		// TODO Auto-generated method stub
		ps.print(nome);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * compilatore.ScrittoreTarget#caricaDimensioneVettore(java.lang.String)
	 */
	@Override
	public void caricaDimensioneVettore(String nome) throws SemanticException {
		// TODO Auto-generated method stub
		int dimensione = mappaDimensioneVettori.get(nome);
		ps.print(dimensione);
	}

	public static void compilaFile(String percorsoFile) throws Exception {
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
	File sorgenteC;
	if (sorgenteFile.getParent() != null) {
		sorgenteC = new File(sorgenteFile.getParent() + File.separator + nomeClasse + ".c");
	} else {
		sorgenteC = new File(nomeClasse + ".c");
	}
	FileOutputStream sorgenteCStream = new FileOutputStream(sorgenteC);
	CTarget ct = new CTarget(sorgenteCStream);
	result.scriviCodice(ct);

	// Genero il bytecode
//	String percorsoFileClass;
//	if (sorgenteFile.getParent() != null) throws SemanticException {
//		percorsoFileClass = sorgenteFile.getParent() + File.separator
//				+ nomeClasse + ".class";
//	} else {
//		percorsoFileClass = nomeClasse + ".class";
//	}
//	// Oggetto della libreria Jasmin che si occupa di generare il file
//	// .class
//	ClassFile classFile = new ClassFile();
//	// Questa chiamata rocambolesca converte l'output a byte del nostro
//	// codice Jasmin in input a caratteri
//	// necessario per la libreria Jasmin
//	CharArrayReader assemblyInput = new CharArrayReader(new String(
//			sorgenteCStream.toByteArray()).toCharArray());
//	classFile.readJasmin(assemblyInput, sorgenteFile.getName(), false);
//	FileOutputStream classFOut = new FileOutputStream(percorsoFileClass);
//	classFile.write(classFOut);
//	classFOut.close();
}
}
