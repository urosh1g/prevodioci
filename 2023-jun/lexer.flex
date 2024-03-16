import java_cup.runtime.*;

%%

%class Lexer
%cup

%%

//whitespace
[\r\n\t ] {}

"enum" { return new Symbol(sym.ENUM, yytext()); }
"{" { return new Symbol(sym.LBRACK); } 
"}" { return new Symbol(sym.RBRACK); }
"," { return new Symbol(sym.COMMA); }
"=" { return new Symbol(sym.EQUALS); }

[a-zA-Z_][a-zA-Z_0-9]* { return new Symbol(sym.ID, yytext()); }
[0-9]+ { return new Symbol(sym.INTCONST, yytext()); }
(\+|-)?[0-9]+\.[0-9]+(((\+|-)?[eE][0-9]+)?)? { return new Symbol(sym.REALCONST, yytext()); }
\'\.\' { return new Symbol(sym.CHARCONST, yytext()); }


\. { return new Symbol(sym.error); }
