import java_cup.runtime.*;

%%

%class Lexer
%debug
%cup

%%

[\r\n\t ] { }

"[" { return new Symbol(sym.LBRACK); } 
"]" { return new Symbol(sym.RBRACK); }
"," { return new Symbol(sym.COMMA);  }
":" { return new Symbol(sym.COLON);  }

[a-zA-Z_][a-zA-Z_0-9]* { return new Symbol(sym.ID, yytext()); }
\"[^\"]*\" { return new Symbol(sym.STRCONST, yytext()); }
(\+|-)?[0-9]+ { return new Symbol(sym.INTCONST, yytext()); }
0x[0-9a-fA-F]+ { return new Symbol(sym.HEXCONST, yytext()); }
(\+|-)?[0-9]+\.[0-9]+(((e|E)(\+|-)?)?[0-9]+)? { return new Symbol(sym.FLOATCONST, yytext()); }

. { return new Symbol(sym.error); }
