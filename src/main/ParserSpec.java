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
		S, A, I, D, C, B, E;
	}
	
	public class S extends SymbolValue<String> {
	};

	public ParserSpec() {

		// grammar
		grammar(
				prod(S, rhs(I,S), rhs()),
				prod(A, rhs(INTERO, IDENTIFICATORE, ASSEGNAZIONE, NUMERO_INTERO),
						rhs(INTERO, IDENTIFICATORE, ASSEGNAZIONE, IDENTIFICATORE),
						rhs(IDENTIFICATORE, ASSEGNAZIONE, IDENTIFICATORE, D),
						rhs(IDENTIFICATORE, ASSEGNAZIONE, NUMERO_INTERO, D),
						rhs(SCRIVI, IDENTIFICATORE),
						rhs(SCRIVI, NUMERO_INTERO)
						),
				prod(I, rhs(A, VIRGOLA),
						rhs(SE, PARENTESI_TONDA_APERTA, C, PARENTESI_TONDA_CHIUSA, DUE_PUNTI, B, PUNTO)),
				prod(B, rhs(), rhs(A, VIRGOLA, B), rhs(A)),
				prod(C, rhs(IDENTIFICATORE, E)),
				prod(E, rhs(), rhs(UGUALE, IDENTIFICATORE)),
				prod(D, rhs(SOMMA, IDENTIFICATORE), rhs(SOMMA, NUMERO_INTERO), rhs())
		);

	}

}
