import java_cup.runtime.*;

%%

%class Lexer
%cup

id=[a-zA-Z_][a-zA-Z_0-9]*

%%

//whitespace
[\t\r\n ] { ; }

//terminals
"{" { return new Symbol(sym.LBRACE); }
"}" { return new Symbol(sym.RBRACE); }
"," { return new Symbol(sym.COMMA); }
":" { return new Symbol(sym.COLON); }

{id} { return new Symbol(sym.ID, yytext()); }
(\+|-)?[0-9]+ { return new Symbol(sym.DECIMALCONST, yytext()); }
0x[0-9A-Fa-f]+ { return new Symbol(sym.HEXCONST, yytext()); }
[0-9]+\.[0-9]+((e|E)?(\+|-)?[0-9]+)? { return new Symbol(sym.FLOATCONST, yytext()); }
\"[^]*\" { return new Symbol(sym.STRCONST, yytext()); }
