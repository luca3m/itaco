package istruzioni;

import java.io.OutputStream;

public class Somma extends Istruzione {

	private static Istruzione istrSx;
	private static Istruzione istrDx;
	
	@Override
	public void scriviCodiceTarget(OutputStream outputStream) {
		
	}
	public Somma(Istruzione istrSx, Istruzione istrDx){
		this.istrSx=istrSx;
		this.istrDx=istrDx;
	}
	
}
