package compilatore;

/**
 * Interfaccia che definisce una qualunque produzione
 * 
 * @author Alessandro Luca Rosario
 * 
 */
public interface Produzione {
	public void scriviCodice(ScrittoreTarget sc) throws EccezioneSemantica;
}
