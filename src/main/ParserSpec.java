package main;

import istruzioni.Istruzione;
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
		SOTTRAZIONE, ASSEGNAZIONE, STRINGA, MINORE, MAGGIORE, VETTORE;
	}

	// non-terminals
	public enum NonTerminals implements NonTerminal {
		N, E, T, F, B, L, I;
	}

	public class S extends SymbolValue<Istruzione> {
	};

	public class A extends SymbolValue<Istruzione> {
	};

	public class I extends SymbolValue<Istruzione> {
	};

	public class D extends SymbolValue<Istruzione> {
	};

	public class C extends SymbolValue<Istruzione> {
	};

	public class B extends SymbolValue<Istruzione> {
	};

	public class E extends SymbolValue<Istruzione> {
	};

	public ParserSpec() {

		/*
		 * Definisco la grammatica
		 * la funzione grammar() è definita nella classe madre, accetta un numero
		 * variabile di argomenti che sono le produzioni
		 * Le produzioni si definiscono con la funzione prod(), essa accetta come primo
		 * parametro un non terminale e poi una serie di rhs() e oggetti Action
		 * la funzione rhs() rappresenta la parte destra della produzione e accetta come parametri
		 * caratteri terminali o non terminali, l'oggetto di tipo action deve definire il metodo a()
		 * che viene chiamato appena viene fatta la riduzione
		 */
		grammar(
				
				prod(S, rhs(I, S), new Action() {
									public String a(String I, String S) {
										return "OK";
									}
								},
				
						rhs(), new Action() {
							public String a() {
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
