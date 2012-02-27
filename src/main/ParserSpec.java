package main;

import edu.tum.cup2.grammar.*;
import edu.tum.cup2.semantics.*;
import edu.tum.cup2.spec.CUP2Specification;
import static main.ParserSpec.Terminals.*;
import static main.ParserSpec.NonTerminals.*;

public class ParserSpec extends CUP2Specification {

	public enum Terminals implements Terminal {
		SE, ALTRIMENTI, FINCHE, INTERO, PARENTESI_QUADRA_APERTA,
		PARENTESI_QUADRA_CHIUSA, DUE_PUNTI, PUNTO, VIRGOLA,
		PARENTESI_TONDA_APERTA, PARENTESI_TONDA_CHIUSA, UGUALE, LEGGI,
		SCRIVI, COMMENTO, IDENTIFICATORE, NUMERO_INTERO, SOMMA,
		SOTTRAZIONE, ASSEGNAZIONE, STRINGA, MINORE, MAGGIORE;
	}

	// non-terminals
	public enum NonTerminals implements NonTerminal {
		S, A, I, D, C, B, E;
	}

	public class S extends SymbolValue<String> {
	};

	public class A extends SymbolValue<String> {
	};

	public class I extends SymbolValue<String> {
	};

	public class D extends SymbolValue<String> {
	};

	public class C extends SymbolValue<String> {
	};

	public class B extends SymbolValue<String> {
	};

	public class E extends SymbolValue<String> {
	};

	public ParserSpec(final ParserDelegate delegate) {

		// grammar
		grammar(
				
				prod(S, rhs(I, S), new Action() {
									public String a(String I, String S) {
										delegate.scriviTarget("Trovata riduzione S-> IS");
										return "OK";
									}
								},
				
						rhs(), new Action() {
							public String a() {
								delegate.scriviTarget("Trovata riduzione S-> epsilon");
								return "OK";
							}
						}),
				prod(A,
						rhs(INTERO, IDENTIFICATORE, ASSEGNAZIONE, NUMERO_INTERO),
						new Action() {
							public String a(String I, String S) {
								delegate.scriviTarget("Trovata riduzione S-> IS");
								return "OK";
							}
						},
						rhs(INTERO, IDENTIFICATORE, ASSEGNAZIONE,
								IDENTIFICATORE),
						new Action() {
							public String a(String I, String S) {
								delegate.scriviTarget("Trovata riduzione S-> IS");
								return "OK";
							}
						},
						rhs(IDENTIFICATORE, ASSEGNAZIONE, IDENTIFICATORE, D),
						new Action() {
							public String a(String I, String S) {
								delegate.scriviTarget("Trovata riduzione S-> IS");
								return "OK";
							}
						}, rhs(IDENTIFICATORE, ASSEGNAZIONE, NUMERO_INTERO, D),
						new Action() {
							public String a(String I, String S) {
								delegate.scriviTarget("Trovata riduzione S-> IS");
								return "OK";
							}
						}, rhs(SCRIVI, IDENTIFICATORE), new Action() {
							public String a(String I, String S) {
								delegate.scriviTarget("Trovata riduzione S-> IS");
								return "OK";
							}
						}, rhs(SCRIVI, NUMERO_INTERO), new Action(){
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}),
				prod(I,
						rhs(A, VIRGOLA),
						new Action() {
							public String a(String I, String S) {
								delegate.scriviTarget("Trovata riduzione S-> IS");
								return "OK";
							}
						},
						rhs(SE, PARENTESI_TONDA_APERTA, C,
								PARENTESI_TONDA_CHIUSA, DUE_PUNTI, B, PUNTO), new Action(){
							public String a(String I, String S) {
								delegate.scriviTarget("Trovata riduzione S-> IS");
								return "OK";
							}
						}),
				prod(B, rhs(), new Action() {
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}, rhs(A, VIRGOLA, B), new Action() {
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}, rhs(A), new Action() {
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}), 
			
				prod(C, rhs(IDENTIFICATORE, E), new Action() {
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}),
				
				prod(E, rhs(), new Action() {
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}, rhs(UGUALE, IDENTIFICATORE), new Action() {
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}),
				
				prod(D, rhs(SOMMA, IDENTIFICATORE), new Action() {
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}, rhs(SOMMA, NUMERO_INTERO), new Action() {
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				}, rhs(), new Action(){
					public String a(String I, String S) {
						delegate.scriviTarget("Trovata riduzione S-> IS");
						return "OK";
					}
				})
		);

	}

}
