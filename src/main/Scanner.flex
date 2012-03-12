/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 1998-2009  Gerwin Klein <lsf@jflex.de>                    *
 * All rights reserved.                                                    *
 *                                                                         *
 * This program is free software; you can redistribute it and/or modify    *
 * it under the terms of the GNU General Public License. See the file      *
 * COPYRIGHT for more information.                                         *
 *                                                                         *
 * This program is distributed in the hope that it will be useful,         *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of          *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the           *
 * GNU General Public License for more details.                            *
 *                                                                         *
 * You should have received a copy of the GNU General Public License along *
 * with this program; if not, write to the Free Software Foundation, Inc., *
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA                 *
 *                                                                         *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

/* Java 1.2 language lexer specification */

/* Use together with unicode.flex for Unicode preprocesssing */
/* and java12.cup for a Java 1.2 parser                      */

/* Note that this lexer specification is not tuned for speed.
   It is in fact quite slow on integer and floating point literals, 
   because the input is read twice and the methods used to parse
   the numbers are not very fast. 
   For a production quality application (e.g. a Java compiler) 
   this could be optimized */

package main;

import edu.tum.cup2.grammar.SpecialTerminals;
import edu.tum.cup2.scanner.ScannerToken;
import static main.ParserSpec.Terminals.*;

@SuppressWarnings("unused")
%%

%public
%class Scanner
%implements edu.tum.cup2.scanner.Scanner
%function readNextTerminal
%type ScannerToken<Object>

%unicode

%line
%column

%{
  StringBuffer string = new StringBuffer();
  
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = {EndOfLineComment}

EndOfLineComment = "#" {InputCharacter}* {LineTerminator}?

/* identifiers */
Identifier = [:jletter:][:jletterdigit:]*

/* integer literals */
DecIntegerLiteral = 0 | [1-9][0-9]*
    
/* string and character literals */
StringCharacter = [^\r\n\"\\]
SingleCharacter = [^\r\n\'\\]

%state STRING

%%

<YYINITIAL> {

  /* keywords */
  "altrimenti"                   { return new ScannerToken<Object>(ALTRIMENTI, yyline+1, yycolumn); }
  "intero"                          { return new ScannerToken<Object>(INTERO, yyline+1, yycolumn); }
  "se"                           { return new ScannerToken<Object>(SE, yyline+1, yycolumn); }
  "finche"                        { return new ScannerToken<Object>(FINCHE, yyline+1, yycolumn); }
  "leggi"						 { return new ScannerToken<Object>(LEGGI, yyline+1, yycolumn); }
  "scrivi"						 { return new ScannerToken<Object>(SCRIVI, yyline+1, yycolumn); }
  "vettore"						 { return new ScannerToken<Object>(VETTORE, yyline+1, yycolumn); }
  "funzione"						 { return new ScannerToken<Object>(FUNZIONE, yyline+1, yycolumn); }
  
  /* separators */
  ":"							 { return new ScannerToken<Object>(DUE_PUNTI, yyline+1, yycolumn); }
  "("                            { return new ScannerToken<Object>(PARENTESI_TONDA_APERTA, yyline+1, yycolumn); }
  ")"                            { return new ScannerToken<Object>(PARENTESI_TONDA_CHIUSA, yyline+1, yycolumn); }
  "["                            { return new ScannerToken<Object>(PARENTESI_QUADRA_APERTA, yyline+1, yycolumn); }
  "]"                            { return new ScannerToken<Object>(PARENTESI_QUADRA_CHIUSA, yyline+1, yycolumn); }
  ","                            { return new ScannerToken<Object>(VIRGOLA, yyline+1, yycolumn); }
  "."                            { return new ScannerToken<Object>(PUNTO, yyline+1, yycolumn); }
  "|"                            { return new ScannerToken<Object>(PIPE, yyline+1, yycolumn); }
  ";"                            { return new ScannerToken<Object>(PUNTO_VIRGOLA, yyline+1, yycolumn); }
  
  /* operators */
  "->"                            { return new ScannerToken<Object>(ASSEGNAZIONE, yyline+1, yycolumn); }
  "+"							 { return new ScannerToken<Object>(SOMMA, yyline+1, yycolumn); }
  "-"                            { return new ScannerToken<Object>(SOTTRAZIONE, yyline+1, yycolumn); }
  "*"                            { return new ScannerToken<Object>(PRODOTTO, yyline+1, yycolumn); }
  "/"                            { return new ScannerToken<Object>(DIVISIONE, yyline+1, yycolumn); }
  "="                           { return new ScannerToken<Object>(UGUALE, yyline+1, yycolumn); }  
  "<"							{ return new ScannerToken<Object>(MINORE, yyline+1, yycolumn); }
  ">" 							{ return new ScannerToken<Object>(MAGGIORE, yyline+1, yycolumn); }
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
     
  {DecIntegerLiteral}            { return new ScannerToken<Object>(NUMERO_INTERO, Integer.valueOf(yytext()), yyline+1, yycolumn); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return new ScannerToken<Object>(IDENTIFICATORE, yytext(), yyline+1, yycolumn); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return new ScannerToken<Object>(STRINGA, string.toString(), yyline+1, yycolumn); }
  
  {StringCharacter}+             { string.append( yytext() ); }
  
  "\\b"                          { string.append( '\b' ); }
  "\\t"                          { string.append( '\t' ); }
  "\\n"                          { string.append( '\n' ); }
  "\\f"                          { string.append( '\f' ); }
  "\\r"                          { string.append( '\r' ); }
  "\\\""                         { string.append( '\"' ); }
  "\\'"                          { string.append( '\'' ); }
  "\\\\"                         { string.append( '\\' ); }
  
  \\.                            { throw new RuntimeException("Illegal escape sequence \""+yytext()+"\""); }
  {LineTerminator}               { throw new RuntimeException("Unterminated string at end of line"); }
}

/* error fallback */
.|\n                             { throw new RuntimeException("Illegal character \""+yytext()+
                                                              "\" at line "+yyline+1+", column "+yycolumn); }
<<EOF>>                          { return new ScannerToken<Object>(SpecialTerminals.EndOfInputStream); }
