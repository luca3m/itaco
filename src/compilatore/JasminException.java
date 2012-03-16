package compilatore;

/**
 * Classe per segnalare errori specifici riguardanti il codice Jasmin
 * 
 * @author Alessandro Luca Rosario
 */
@SuppressWarnings("serial")
public class JasminException extends Exception {
	JasminException(String s) {
		super(s);
	}
}
