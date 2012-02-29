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
Comment = {TraditionalComment} | {EndOfLineComment} | 
          {DocumentationComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/*" "*"+ [^/*] ~"*/"

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
  "altrimenti"                   { return new ScannerToken<Object>(ALTRIMENTI); }
  "intero"                          { return new ScannerToken<Object>(INTERO); }
  "se"                           { return new ScannerToken<Object>(SE); }
  "finché"                        { return new ScannerToken<Object>(FINCHE); }
  "leggi"						 { return new ScannerToken<Object>(LEGGI); }
  "scrivi"						 { return new ScannerToken<Object>(SCRIVI); }
  "vettore"						 { return new ScannerToken<Object>(VETTORE); }
  
  /* separators */
  ":"							 { return new ScannerToken<Object>(DUE_PUNTI); }
  "("                            { return new ScannerToken<Object>(PARENTESI_TONDA_APERTA); }
  ")"                            { return new ScannerToken<Object>(PARENTESI_TONDA_CHIUSA); }
  "["                            { return new ScannerToken<Object>(PARENTESI_QUADRA_APERTA); }
  "]"                            { return new ScannerToken<Object>(PARENTESI_QUADRA_CHIUSA); }
  ","                            { return new ScannerToken<Object>(VIRGOLA); }
  "."                            { return new ScannerToken<Object>(PUNTO); }
  
  /* operators */
  "<-"                            { return new ScannerToken<Object>(ASSEGNAZIONE); }
  "+"							 { return new ScannerToken<Object>(SOMMA); }
  "-"                            { return new ScannerToken<Object>(SOTTRAZIONE); }
  "="                           { return new ScannerToken<Object>(UGUALE); }  
  "<"							{ return new ScannerToken<Object>(MINORE);}
  ">" 							{ return new ScannerToken<Object>(MAGGIORE);}
  
  /* string literal */
  \"                             { yybegin(STRING); string.setLength(0); }

  /* numeric literals */

  /* This is matched together with the minus, because the number is too big to 
     be represented by a positive integer. */
     
  {DecIntegerLiteral}            { return new ScannerToken<Object>(NUMERO_INTERO, Integer.valueOf(yytext())); }
  
  /* comments */
  {Comment}                      { /* ignore */ }

  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }

  /* identifiers */ 
  {Identifier}                   { return new ScannerToken<Object>(IDENTIFICATORE, yytext()); }  
}

<STRING> {
  \"                             { yybegin(YYINITIAL); return new ScannerToken<Object>(STRINGA, string.toString()); }
  
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
                                                              "\" at line "+yyline+", column "+yycolumn); }
<<EOF>>                          { return new ScannerToken<Object>(SpecialTerminals.EndOfInputStream); }
