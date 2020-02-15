package Analisis; // Paquete donde estará nuestro scanner
import java_cup.runtime.Symbol; 
import java.util.ArrayList;
import Motor.*;
%% 

%{


    /*Area de declaración de codigo java. 
    *Funciones y variables necesarias
    *
    */
%}

// Area de directivas
%cupsym sym 
%class scanner
%cup
%public
%unicode
%line
%column
%char
%ignorecase
%8bit
%full


// Area de definición de expresiones regulares. Tokens.
Comentario = "#" [^\r\n]* [^\r\n]
ComentarioMulti = "#*" [^/] ~"*/" | "#*" "#"+ "*#"
entero =[0-9]+ 
decimal =[0-9]+ "." [0-9]*
cadena =[\"] [^\"\n]* [\"\n]
letra =[a-zA-ZÑñ]+
la= [\u00E1]|[\u00E9]|[\u00ED]|[\u00F3]|[\u00FA]|[\u00F1]
id =({letra}|{la})({letra}|{entero}|"_")*
caracter="'"[^]"'"  
esp = \t|\f|" "|\r|\n



%%

// Acciones
/* Espacios en blanco */

{cadena} {  Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N);   return new Symbol(TipoCadena(yytext()),yycolumn,yyline,yytext().substring(1,yytext().length()-1).toLowerCase());}
{esp}     {/*Ignorar*/}
{entero} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.entero,yycolumn,yyline,yytext());}
{decimal} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.decimal,yycolumn,yyline,yytext());}
{id} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); if(yytext().equals("true")||yytext().equals("false")){return new Symbol(sym.booleano,yycolumn,yyline,yytext());} return new Symbol(sym.id,yycolumn,yyline,yytext());}
{caracter} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.caracter,yycolumn,yyline,yytext());}
"$" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.dolar,yycolumn,yyline,yytext());}
"#" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.numeral,yycolumn,yyline,yytext());}
"&&" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.anddoble,yycolumn,yyline,yytext());}
"||" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.ordoble,yycolumn,yyline,yytext());}
"[" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.cora,yycolumn,yyline,yytext());}
"]" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.corc,yycolumn,yyline,yytext());}
"{" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.llaa,yycolumn,yyline,yytext());}
"}" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.llac,yycolumn,yyline,yytext());}
"&" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.and,yycolumn,yyline,yytext());}
"|" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.or,yycolumn,yyline,yytext());}
"<=" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.menorigual,yycolumn,yyline,yytext());}
">=" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.mayorigual,yycolumn,yyline,yytext());}
"!=" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.diferente,yycolumn,yyline,yytext());}
"==" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.igualdoble,yycolumn,yyline,yytext());}
"=" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.igual,yycolumn,yyline,yytext());}
"," {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.coma,yycolumn,yyline,yytext());}
"?"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.interro,yycolumn,yyline,yytext());}
";"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.puntocoma,yycolumn,yyline,yytext());}
"("    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.para,yycolumn,yyline,yytext());}
")"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.parc,yycolumn,yyline,yytext());}
"+"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.suma,yycolumn,yyline,yytext());}
"-"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.resta,yycolumn,yyline,yytext());}
"*"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.multi,yycolumn,yyline,yytext());}
"/"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.div,yycolumn,yyline,yytext());}
">"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.may,yycolumn,yyline,yytext());}
"<"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.men,yycolumn,yyline,yytext());}
"!"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.admira,yycolumn,yyline,yytext());}
"."    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.punto,yycolumn,yyline,yytext());}


{Comentario} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N);  /*Se ignora.*/}
{ComentarioMulti} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N);  /*Se ignora.*/}
/*Recolección de errores*/
.   
{               
    Token N= new Token(yytext(),yycolumn+1,yyline+1);
    ErrorT.add(N);


     //   System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1));
}

