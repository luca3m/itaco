package main;

import edu.tum.cup2.grammar.*;
import edu.tum.cup2.semantics.*;
import edu.tum.cup2.spec.CUP2Specification;
import static main.ParserSpec.Terminals.*;
import static main.ParserSpec.NonTerminals.*;

public class ParserSpec extends CUP2Specification {

	public enum Terminals implements Terminal {
		SE, ALTRIMENTI, FINCHE, INTERO, PARENTESI_QUADRA_APERTA,
		PARENTESI_QUADRA_CHIUSA, DUE_PUNTI, PUNTO, VIRGOLA, PARENTESI_TONDA_APERTA,
		PARENTESI_TONDA_CHIUSA, UGUALE, LEGGI, SCRIVI, COMMENTO, IDENTIFICATORE,
		NUMERO_INTERO, SOMMA, SOTTRAZIONE, ASSEGNAZIONE, FINE_FILE, STRINGA;
	}

	// non-terminals
	public enum NonTerminals implements NonTerminal {
		s;
	}
	
	public class s extends SymbolValue<String> {
	};

	public ParserSpec() {

		// grammar
		grammar(
				prod(s, rhs(INTERO, IDENTIFICATORE,
						ASSEGNAZIONE, NUMERO_INTERO, VIRGOLA))
		);

	}

}
