package compilatore;

/**
 * Classe per segnalare errori specifici riguardanti i tipi o le variabili
 * 
 * @author Alessandro Luca Rosario
 */
@SuppressWarnings("serial")
public class EccezioneSemantica extends Exception {
	public EccezioneSemantica(String message) {
		super(message);
	}
}
