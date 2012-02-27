package simboli;

import main.TipoSimbolo;
import edu.tum.cup2.semantics.SymbolValue;

public class FabbricaSimboli {
	
	public SymbolValue<?> ottieniSimbolo(TipoSimbolo tipo, Object valore) {
		switch (tipo) {
		case NUMERO_INTERO:
			return new NumeroIntero((Integer)valore);
			default:
				return null;
		}
	}

	public SymbolValue<?> ottieniSimbolo(TipoSimbolo tipo) {
		/*switch (tipo) {
		case INTERO: return new SimboloIntero();
		case ASSEGNAZIONE: return new SimboloAssegnazione()
		}*/
		return null;
	}
}
