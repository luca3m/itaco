package istruzioni;

import java.io.OutputStream;

public abstract class Istruzione {
	public abstract void scriviCodiceTarget(OutputStream outputStream);
}
