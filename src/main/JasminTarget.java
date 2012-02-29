package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.text.TabExpander;

public class JasminTarget extends ScrittoreTarget {
	PrintStream output;
	String className;
	
	public JasminTarget(String className, OutputStream output)
			throws IOException {
		this.output = new PrintStream(output);
		this.className = className;
		this.output.println(".class public " + className);
		writeContentOfStub("preMainStub.j");
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
		output.println("invokestatic"+ nomeClasse+"/writeInt(I)V");
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
		writeContentOfStub("postMainStub.j");
	}
}
