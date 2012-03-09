package main;

import istruzioni.Assegnamento;
import istruzioni.AssegnamentoVettore;
import istruzioni.CicloFinche;
import istruzioni.CondizionaleAltrimenti;
import istruzioni.CondizionaleSe;
import istruzioni.DefinizioneAssegnamento;
import istruzioni.DefinizioneVettore;
import istruzioni.LetturaDaTastiera;
import istruzioni.LetturaTastieraElementoVettore;
import istruzioni.Stampa;
import istruzioni.StampaStringa;
import istruzioni.SuccessioneCN;
import istruzioni.SuccessioneIstruzioni;
import istruzioni.espressioni.Costante;
import istruzioni.espressioni.Divisione;
import istruzioni.espressioni.ElementoVettore;
import istruzioni.espressioni.EspressioneInParentesi;
import istruzioni.espressioni.Identificatore;
import istruzioni.espressioni.Prodotto;
import istruzioni.espressioni.Somma;
import istruzioni.espressioni.Sottrazione;
import istruzioni.logiche.Maggiore;
import istruzioni.logiche.Minore;
import istruzioni.logiche.Uguaglianza;
import edu.tum.cup2.grammar.*;
import edu.tum.cup2.semantics.*;
import edu.tum.cup2.spec.CUP2Specification;
import static main.ParserSpec.Terminals.*;
import static main.ParserSpec.NonTerminals.*;

public class ParserSpec extends CUP2Specification {

	public enum Terminals implements Terminal {
		SE, ALTRIMENTI, FINCHE, INTERO, PARENTESI_QUADRA_APERTA, PARENTESI_QUADRA_CHIUSA, DUE_PUNTI, PUNTO, VIRGOLA, PARENTESI_TONDA_APERTA, PARENTESI_TONDA_CHIUSA, UGUALE, LEGGI, SCRIVI, IDENTIFICATORE, NUMERO_INTERO, SOMMA, SOTTRAZIONE, PRODOTTO, DIVISIONE, ASSEGNAZIONE, STRINGA, MINORE, MAGGIORE, VETTORE;
	}

	// non-terminals
	public enum NonTerminals implements NonTerminal {
		N, I, E, T, F, B, C;
	}

	public class N extends SymbolValue<istruzioni.N> {
	};

	public class I extends SymbolValue<istruzioni.I> {
	};

	public class E extends SymbolValue<istruzioni.espressioni.E> {
	};

	public class T extends SymbolValue<istruzioni.espressioni.T> {
	};

	public class F extends SymbolValue<istruzioni.espressioni.F> {
	};

	public class B extends SymbolValue<istruzioni.logiche.B> {
	};

	public class C extends SymbolValue<istruzioni.C> {
	};

	public class NUMERO_INTERO extends SymbolValue<Integer> {
	};

	public class IDENTIFICATORE extends SymbolValue<String> {
	};

	public class STRINGA extends SymbolValue<String> {
	};

	@SuppressWarnings("unused")
	public ParserSpec() {

		/*
		 * Definisco la grammatica la funzione grammar() è definita nella classe
		 * madre, accetta un numero variabile di argomenti che sono le
		 * produzioni Le produzioni si definiscono con la funzione prod(), essa
		 * accetta come primo parametro un non terminale e poi una serie di
		 * rhs() e oggetti Action la funzione rhs() rappresenta la parte destra
		 * della produzione e accetta come parametri caratteri terminali o non
		 * terminali, l'oggetto di tipo action deve definire il metodo a() che
		 * viene chiamato appena viene fatta la riduzione
		 */
		grammar(

		prod(N, rhs(I), new Action() {
			public istruzioni.N a(istruzioni.I i) {
				return i;
			}
		}, rhs(I, VIRGOLA, N), new Action() {
			public istruzioni.N a(istruzioni.I i, istruzioni.N n) {
				return new SuccessioneIstruzioni(i, n);
			}
		}, rhs(C, PUNTO, N), new Action() {
			public istruzioni.N a(istruzioni.C c, istruzioni.N n) {
				return new SuccessioneCN(c, n);
			}
		}, rhs(C, PUNTO), new Action() {
			public istruzioni.N a(istruzioni.C c) {
				return c;
			}
		}),
				prod(I,
						rhs(INTERO, IDENTIFICATORE, ASSEGNAZIONE, E),
						new Action() {
							public istruzioni.I a(String id,
									istruzioni.espressioni.E e) {
								return new DefinizioneAssegnamento(id, e);
							}
						},
						rhs(IDENTIFICATORE, ASSEGNAZIONE, E),
						new Action() {
							public istruzioni.I a(String id,
									istruzioni.espressioni.E e) {
								return new Assegnamento(id, e);
							}
						},
						rhs(SCRIVI, STRINGA),
						new Action() {
							public istruzioni.I a(String s) {
								return new StampaStringa(s);
							}
						},
						rhs(VETTORE, IDENTIFICATORE, PARENTESI_QUADRA_APERTA,
								NUMERO_INTERO, PARENTESI_QUADRA_CHIUSA),
						new Action() {
							public istruzioni.I a(String id, Integer i) {
								return new DefinizioneVettore(id, i);
							}
						},
						rhs(IDENTIFICATORE, PARENTESI_QUADRA_APERTA, E,
								PARENTESI_QUADRA_CHIUSA, ASSEGNAZIONE, E),
						new Action() {
							public istruzioni.I a(String id,
									istruzioni.espressioni.E indice,
									istruzioni.espressioni.E e) {
								return new AssegnamentoVettore(id, indice, e);
							}
						},
						rhs(SCRIVI, E),
						new Action() {
							public istruzioni.I a(istruzioni.espressioni.E e) {
								return new Stampa(e);
							}
						},
						rhs(LEGGI, IDENTIFICATORE),
						new Action() {

							public istruzioni.I a(String id) {
								return new LetturaDaTastiera(id);
							}
						},
						rhs(LEGGI, IDENTIFICATORE, PARENTESI_QUADRA_APERTA, E,
								PARENTESI_QUADRA_CHIUSA), new Action() {
							public istruzioni.I a(String id,
									istruzioni.espressioni.E indice) {
								return new LetturaTastieraElementoVettore(id,
										indice);
							}
						}),
				prod(C, rhs(SE, B, DUE_PUNTI, N), new Action() {
					public istruzioni.C a(istruzioni.espressioni.E e,
							istruzioni.N n) {
						return new CondizionaleSe(e, n);
					}
				}, rhs(SE, B, DUE_PUNTI, N, ALTRIMENTI, N), new Action() {
					public istruzioni.C a(istruzioni.espressioni.E e,
							istruzioni.N n) {
						return new CondizionaleAltrimenti(n);
					}
				}, rhs(FINCHE, B, DUE_PUNTI, N), new Action() {
					public istruzioni.C a(istruzioni.espressioni.E e,
							istruzioni.N n) {
						return new CicloFinche(e, n);
					}
				}),
				// Espressioni
				prod(B, rhs(E, MAGGIORE, E), new Action() {
					public istruzioni.logiche.B a(istruzioni.espressioni.E e,
							istruzioni.espressioni.E e2) {
						return new Maggiore(e, e2);
					}
				}, rhs(E, MINORE, E), new Action() {
					public istruzioni.logiche.B a(istruzioni.espressioni.E e,
							istruzioni.espressioni.E e2) {
						return new Minore(e, e2);
					}
				}, rhs(E, UGUALE, E), new Action() {
					public istruzioni.logiche.B a(istruzioni.espressioni.E e,
							istruzioni.espressioni.E e2) {
						return new Uguaglianza(e, e2);
					}
				}),
				prod(E, rhs(E, SOMMA, T), new Action() {
					public istruzioni.espressioni.E a(
							istruzioni.espressioni.E e,
							istruzioni.espressioni.T t) {
						return new Somma(e, t);
					}
				}, rhs(E, SOTTRAZIONE, T), new Action() {
					public istruzioni.espressioni.E a(
							istruzioni.espressioni.E e,
							istruzioni.espressioni.T t) {
						return new Sottrazione(e, t);
					}
				}, rhs(T), new Action() {
					public istruzioni.espressioni.E a(istruzioni.espressioni.T t) {
						return t;
					}
				}),
				prod(T, rhs(T, PRODOTTO, F), new Action() {
					public istruzioni.espressioni.T a(
							istruzioni.espressioni.T t,
							istruzioni.espressioni.F f) {
						return new Prodotto(t, f);
					}
				}, rhs(T, DIVISIONE, F), new Action() {
					public istruzioni.espressioni.T a(
							istruzioni.espressioni.T t,
							istruzioni.espressioni.F f) {
						return new Divisione(t, f);
					}
				}, rhs(F), new Action() {
					public istruzioni.espressioni.T a(istruzioni.espressioni.F f) {
						return f;
					}
				}),
				prod(F,
						rhs(IDENTIFICATORE),
						new Action() {
							public istruzioni.espressioni.F a(String id) {
								return new Identificatore(id);
							}
						},
						rhs(NUMERO_INTERO),
						new Action() {
							public istruzioni.espressioni.F a(Integer numero) {
								return new Costante(numero);
							}
						},
						rhs(PARENTESI_TONDA_APERTA, E, PARENTESI_TONDA_CHIUSA),
						new Action() {
							public istruzioni.espressioni.F a(
									istruzioni.espressioni.E espressione) {
								return new EspressioneInParentesi(espressione);
							}
						},
						rhs(IDENTIFICATORE, PARENTESI_QUADRA_APERTA, E,
								PARENTESI_QUADRA_CHIUSA), new Action() {
							public istruzioni.espressioni.F a(String id,
									istruzioni.espressioni.E e) {
								return new ElementoVettore(id, e);
							}
						}));

	}

}
