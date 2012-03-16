package main;

import istruzioni.AssegnamentoVariabile;
import istruzioni.AssegnamentoVettore;
import istruzioni.CicloFinche;
import istruzioni.CondizionaleAltrimenti;
import istruzioni.CondizionaleSe;
import istruzioni.DefinizioneAssegnamentoVariabile;
import istruzioni.DefinizioneMain;
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
import istruzioni.espressioni.Variabile;
import istruzioni.espressioni.Prodotto;
import istruzioni.espressioni.Somma;
import istruzioni.espressioni.Sottrazione;
import istruzioni.funzioni.ArgomentiDefinizioneFunzione;
import istruzioni.funzioni.ArgomentiChiamataFunzione;
import istruzioni.funzioni.ArgomentoVettoreChiamataFunzione;
import istruzioni.funzioni.ArgomentoVariabileDefinizioneFunzione;
import istruzioni.funzioni.ArgomentoVettoreDefinizioneFunzione;
import istruzioni.funzioni.ChiamaFunzione;
import istruzioni.funzioni.ChiamaFunzioneSenzaRitorno;
import istruzioni.funzioni.DefinizioneFunzione;
import istruzioni.logiche.Maggiore;
import istruzioni.logiche.Minore;
import istruzioni.logiche.Uguaglianza;
import edu.tum.cup2.grammar.*;
import edu.tum.cup2.semantics.*;
import edu.tum.cup2.spec.CUP2Specification;
import static main.ParserSpec.Terminals.*;
import static main.ParserSpec.NonTerminals.*;

/**
 * Classe racchiudente tutte le specifiche per Java CUP 2
 * 
 * @author Alessandro, Luca, Saro
 * 
 */
public class ParserSpec extends CUP2Specification {

	/**
	 * Lista dei terminali passati dallo scanner
	 * 
	 * @author Alessandro, Luca, Saro
	 */
	public enum Terminals implements Terminal {
		SE, ALTRIMENTI, FINCHE, INTERO, PARENTESI_QUADRA_APERTA, PARENTESI_QUADRA_CHIUSA, DUE_PUNTI, PUNTO, VIRGOLA, PARENTESI_TONDA_APERTA, PARENTESI_TONDA_CHIUSA, UGUALE, LEGGI, SCRIVI, IDENTIFICATORE, NUMERO_INTERO, SOMMA, SOTTRAZIONE, PRODOTTO, DIVISIONE, ASSEGNAZIONE, STRINGA, MINORE, MAGGIORE, VETTORE, FUNZIONE, PIPE, PUNTO_VIRGOLA;
	}

	/**
	 * Lista dei non terminali
	 * 
	 * @author Alessandro, Luca, Saro
	 * 
	 */
	public enum NonTerminals implements NonTerminal {
		S, Z, N, I, E, T, F, B, C, A, A2, R, W, W2, U;
	}

	/**
	 * Dichiarazione classi per i non terminali
	 * 
	 * @author Alessandro, Luca, Saro
	 * 
	 */

	public class S extends SymbolValue<istruzioni.S> {
	};

	public class Z extends SymbolValue<istruzioni.Z> {
	};

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

	public class A extends SymbolValue<istruzioni.funzioni.A> {
	};

	public class A2 extends SymbolValue<istruzioni.funzioni.A2> {
	};

	public class R extends SymbolValue<istruzioni.funzioni.R> {
	};

	public class W extends SymbolValue<istruzioni.espressioni.W> {
	};

	public class W2 extends SymbolValue<istruzioni.espressioni.W2> {
	};

	public class U extends SymbolValue<istruzioni.espressioni.U> {
	};

	@SuppressWarnings("unused")
	public ParserSpec() {
		/*
		 * Definisco la grammatica la funzione grammar() è definita nella classe
		 * madre, accetta un numero variabile di argomenti che sono le
		 * produzioni Le produzioni si definiscono con la funzione prod(), essa
		 * accetta come primo parametro un non terminale e poi una serie di
		 * rhs(), new Action() { public istruzioni.funzioni.A a() { return null;
		 * } }, e oggetti Action la funzione rhs(), new Action() { public
		 * istruzioni.funzioni.A a() { return null; } }, rappresenta la parte
		 * destra della produzione e accetta come parametri caratteri terminali
		 * o non terminali, l'oggetto di tipo action deve definire il metodo a()
		 * che viene chiamato appena viene fatta la riduzione
		 */
		grammar(

		/**
		 * produzioni per l'assioma S
		 */
		prod(S, rhs(Z, S), new Action() {
			public istruzioni.S a(istruzioni.Z z, istruzioni.S s) {
				return new SuccessioneIstruzioni(z, s);
			}
		}, rhs(N, PUNTO), new Action() {
			public istruzioni.S a(istruzioni.N n) {
				return new DefinizioneMain(n);
			}
		}),
				/**
				 * produzini per il non terminale Z
				 */
				prod(Z,
						rhs(FUNZIONE, IDENTIFICATORE, PARENTESI_TONDA_APERTA,
								A, PARENTESI_TONDA_CHIUSA, ASSEGNAZIONE,
								INTERO, IDENTIFICATORE, DUE_PUNTI, N, PUNTO),
						new Action() {
							public istruzioni.Z a(String id_funzione,
									istruzioni.funzioni.A a_ingresso,
									String id_uscita, istruzioni.N n) {
								return new DefinizioneFunzione(id_funzione,
										a_ingresso, id_uscita, n);
							}
						},
						rhs(FUNZIONE, IDENTIFICATORE, PARENTESI_TONDA_APERTA,
								A, PARENTESI_TONDA_CHIUSA, DUE_PUNTI, N, PUNTO),
						new Action() {
							public istruzioni.Z a(String id,
									istruzioni.funzioni.A a_ingresso,
									istruzioni.N n) {
								return new DefinizioneFunzione(id, a_ingresso,
										n);
							}
						}),
				/**
				 * produzini per il non terminale A
				 */
				prod(A, rhs(), new Action() {
					public istruzioni.funzioni.A a() {
						return null;
					}
				}, rhs(A2), new Action() {
					public istruzioni.funzioni.A a(istruzioni.funzioni.A2 a2) {
						return a2;
					}
				}),

				/**
				 * produzini per il non terminale A2
				 */
				prod(A2,

				rhs(R), new Action() {
					public istruzioni.funzioni.A2 a(istruzioni.funzioni.R r) {
						return r;
					}
				}, rhs(R, PIPE, A2), new Action() {
					public istruzioni.funzioni.A2 a(istruzioni.funzioni.R r,
							istruzioni.funzioni.A2 a2) {
						return new ArgomentiDefinizioneFunzione(r, a2);
					}
				}),
				/**
				 * produzini per il non terminale R
				 */
				prod(R,
						rhs(INTERO, IDENTIFICATORE),
						new Action() {
							public istruzioni.funzioni.R a(String id) {
								return new ArgomentoVariabileDefinizioneFunzione(id);
							}
						},
						rhs(VETTORE, IDENTIFICATORE, PARENTESI_QUADRA_APERTA,
								IDENTIFICATORE, PARENTESI_QUADRA_CHIUSA),
						new Action() {
							public istruzioni.funzioni.R a(String id, String dim) {
								return new ArgomentoVettoreDefinizioneFunzione(id, dim);
							}
						}),
				/**
				 * produzini per il non terminale N
				 */
				prod(N, rhs(I), new Action() {
					public istruzioni.N a(istruzioni.I i) {
						return i;
					}
				}, rhs(I, VIRGOLA, N), new Action() {
					public istruzioni.N a(istruzioni.I i, istruzioni.N n) {
						return new SuccessioneIstruzioni(i, n);
					}
				}, rhs(C, N), new Action() {
					public istruzioni.N a(istruzioni.C c, istruzioni.N n) {
						return new SuccessioneCN(c, n);
					}
				}, rhs(C), new Action() {
					public istruzioni.N a(istruzioni.C c) {
						return c;
					}
				}),
				/**
				 * produzini per il non terminale I
				 */
				prod(I,
						rhs(E, ASSEGNAZIONE, INTERO, IDENTIFICATORE),
						new Action() {
							public istruzioni.I a(istruzioni.espressioni.E e,
									String id) {
								return new DefinizioneAssegnamentoVariabile(id,
										e);
							}
						},
						rhs(E, ASSEGNAZIONE, IDENTIFICATORE),
						new Action() {
							public istruzioni.I a(istruzioni.espressioni.E e,
									String id) {
								return new AssegnamentoVariabile(id, e);
							}
						},
						rhs(INTERO, IDENTIFICATORE),
						new Action() {
							public istruzioni.I a(String id) {
								return new DefinizioneAssegnamentoVariabile(id,
										new Costante(0));
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
						rhs(E, ASSEGNAZIONE, IDENTIFICATORE,
								PARENTESI_QUADRA_APERTA, E,
								PARENTESI_QUADRA_CHIUSA),
						new Action() {
							public istruzioni.I a(istruzioni.espressioni.E e,
									String id, istruzioni.espressioni.E indice) {
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
								PARENTESI_QUADRA_CHIUSA),
						new Action() {
							public istruzioni.I a(String id,
									istruzioni.espressioni.E indice) {
								return new LetturaTastieraElementoVettore(id,
										indice);
							}
						},
						rhs(IDENTIFICATORE, PARENTESI_TONDA_APERTA, W,
								PARENTESI_TONDA_CHIUSA), new Action() {
							public istruzioni.I a(String id,
									istruzioni.espressioni.W w) {
								return new ChiamaFunzioneSenzaRitorno(id, w);
							}
						}),
				/**
				 * produzini per il non terminale C
				 */
				prod(C, rhs(SE, B, DUE_PUNTI, N, PUNTO), new Action() {
					public istruzioni.C a(istruzioni.logiche.B b, istruzioni.N n) {
						return new CondizionaleSe(b, n);
					}
				},
						rhs(SE, B, DUE_PUNTI, N, PUNTO_VIRGOLA, ALTRIMENTI,
								DUE_PUNTI, N, PUNTO), new Action() {
							public istruzioni.C a(istruzioni.logiche.B b,
									istruzioni.N n1, istruzioni.N n2) {
								return new CondizionaleAltrimenti(b, n1, n2);
							}
						}, rhs(FINCHE, B, DUE_PUNTI, N, PUNTO), new Action() {
							public istruzioni.C a(istruzioni.logiche.B b,
									istruzioni.N n) {
								return new CicloFinche(b, n);
							}
						}),
				/**
				 * produzini per il non terminale B
				 */
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
				/**
				 * produzini per il non terminale E
				 */
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
				/**
				 * produzini per il non terminale T
				 */
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
				/**
				 * produzini per il non terminale F
				 */
				prod(F,
						rhs(IDENTIFICATORE),
						new Action() {
							public istruzioni.espressioni.F a(String id) {
								return new Variabile(id);
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
								PARENTESI_QUADRA_CHIUSA),
						new Action() {
							public istruzioni.espressioni.F a(String id,
									istruzioni.espressioni.E e) {
								return new ElementoVettore(id, e);
							}
						},
						rhs(IDENTIFICATORE, PARENTESI_TONDA_APERTA, W,
								PARENTESI_TONDA_CHIUSA), new Action() {
							public istruzioni.espressioni.F a(String id,
									istruzioni.espressioni.W w) {
								return new ChiamaFunzione(id, w);
							}
						}),
				/**
				 * produzini per il non terminale W
				 */
				prod(W, rhs(), new Action() {
					public istruzioni.espressioni.W a() {
						return null;
					}
				}, rhs(W2), new Action() {
					public istruzioni.espressioni.W a(
							istruzioni.espressioni.W2 w2) {
						return w2;
					}
				}),
				/**
				 * produzini per il non terminale W2
				 */
				prod(W2, rhs(U, PIPE, W2), new Action() {
					public istruzioni.espressioni.W2 a(
							istruzioni.espressioni.U u,
							istruzioni.espressioni.W2 w2) {
						return new ArgomentiChiamataFunzione(u, w2);
					}
				}, rhs(U), new Action() {
					public istruzioni.espressioni.W2 a(
							istruzioni.espressioni.U u) {
						return u;
					}
				}),
				/**
				 * produzini per il non terminale U
				 */
				prod(U, rhs(E), new Action() {
					public istruzioni.espressioni.U a(istruzioni.espressioni.E e) {
						return e;
					}
				},
						rhs(IDENTIFICATORE, PARENTESI_QUADRA_APERTA,
								PARENTESI_QUADRA_CHIUSA), new Action() {
							public istruzioni.espressioni.U a(String id) {
								return new ArgomentoVettoreChiamataFunzione(id);
							}
						}));
	}
}
