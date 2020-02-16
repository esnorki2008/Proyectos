package Analizadores; // Paquete donde estará nuestro scanner
import java_cup.runtime.Symbol; 
import java.util.ArrayList;
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
suitc=switch
defaultp=default
casep=case
breakp=break
whilep=while
dop=do
ifv=if
elses=else
forp=for
continuep=continue
returnp=return
functionpp=function
inp=in
%%

// Acciones
/* Espacios en blanco */
{inp} {return new Symbol(sym.inp,yycolumn,yyline,yytext());}
{functionpp} {return new Symbol(sym.functionpp,yycolumn,yyline,yytext());}
{returnp} {return new Symbol(sym.returnp,yycolumn,yyline,yytext());}
{continuep} {return new Symbol(sym.continuep,yycolumn,yyline,yytext());}
{forp} {return new Symbol(sym.forp,yycolumn,yyline,yytext());}
{elses} {return new Symbol(sym.elses,yycolumn,yyline,yytext());}
{ifv} {return new Symbol(sym.ifv,yycolumn,yyline,yytext());}
{dop} {return new Symbol(sym.dop,yycolumn,yyline,yytext());}
{whilep} {return new Symbol(sym.whilep,yycolumn,yyline,yytext());}
{breakp} {return new Symbol(sym.breakp,yycolumn,yyline,yytext());}
{casep} {return new Symbol(sym.casep,yycolumn,yyline,yytext());}
{defaultp} {return new Symbol(sym.defaultp,yycolumn,yyline,yytext());}
{suitc} {return new Symbol(sym.suitc,yycolumn,yyline,yytext());}


{cadena} {   return new Symbol(sym.cadena,yycolumn,yyline,yytext().substring(1,yytext().length()-1).toLowerCase());}
{esp}     {/*Ignorar*/}
{entero} {return new Symbol(sym.entero,yycolumn,yyline,yytext());}
{decimal} {return new Symbol(sym.decimal,yycolumn,yyline,yytext());}
{id} {if(yytext().equals("true")||yytext().equals("false")){return new Symbol(sym.booleano,yycolumn,yyline,yytext());} return new Symbol(sym.id,yycolumn,yyline,yytext());}
{caracter} {return new Symbol(sym.caracter,yycolumn,yyline,yytext());}
"[" {return new Symbol(sym.cora,yycolumn,yyline,yytext());}
"]" {return new Symbol(sym.corc,yycolumn,yyline,yytext());}
"{" {return new Symbol(sym.llaa,yycolumn,yyline,yytext());}
"}" {return new Symbol(sym.llac,yycolumn,yyline,yytext());}
"&" {return new Symbol(sym.and,yycolumn,yyline,yytext());}
"|" {return new Symbol(sym.or,yycolumn,yyline,yytext());}
"<=" {return new Symbol(sym.menorigual,yycolumn,yyline,yytext());}
">=" {return new Symbol(sym.mayorigual,yycolumn,yyline,yytext());}
"!=" {return new Symbol(sym.diferente,yycolumn,yyline,yytext());}
"==" {return new Symbol(sym.igualdoble,yycolumn,yyline,yytext());}
"=" {return new Symbol(sym.igual,yycolumn,yyline,yytext());}
"," {return new Symbol(sym.coma,yycolumn,yyline,yytext());}
"?"    {return new Symbol(sym.interro,yycolumn,yyline,yytext());}
";"    {return new Symbol(sym.puntocom,yycolumn,yyline,yytext());}
"("    {return new Symbol(sym.para,yycolumn,yyline,yytext());}
")"    {return new Symbol(sym.parc,yycolumn,yyline,yytext());}
"+"    {return new Symbol(sym.suma,yycolumn,yyline,yytext());}
"-"    {return new Symbol(sym.resta,yycolumn,yyline,yytext());}
"*"    {return new Symbol(sym.multi,yycolumn,yyline,yytext());}
"/"    {return new Symbol(sym.div,yycolumn,yyline,yytext());}
">"    {return new Symbol(sym.may,yycolumn,yyline,yytext());}
"<"    {return new Symbol(sym.men,yycolumn,yyline,yytext());}
"!"    {return new Symbol(sym.admira,yycolumn,yyline,yytext());}
"."    {return new Symbol(sym.punto,yycolumn,yyline,yytext());}
":"    {return new Symbol(sym.doblepunto,yycolumn,yyline,yytext());}
"^"    {return new Symbol(sym.potencia,yycolumn,yyline,yytext());}
"%%"    {return new Symbol(sym.modular,yycolumn,yyline,yytext());}
"=>" {return new Symbol(sym.flecha,yycolumn,yyline,yytext());}
{Comentario} {/*Se ignora.*/}
{ComentarioMulti} {/*Se ignora.*/}
/*Recolección de errores*/
.   
{               
    


        System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1));
}


