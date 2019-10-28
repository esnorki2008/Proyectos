package Analisis; // Paquete donde estará nuestro scanner
import java_cup.runtime.Symbol; 
import java.util.ArrayList;
 
%% 

%{



    /*Area de declaración de codigo java. 
    *Funciones y variables necesarias
    *
    */
    String nombre;
    public void imprimir(String cadena)
    {
    	System.out.println(cadena);
    }

   

    
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
Comentario = "//" [^\r\n]* [^\r\n]
ComentarioMulti = "/*" [^/] ~"*/" | "/*" "/"+ "*/"
entero =[0-9]+ 
decimal =[0-9]+ "." [0-9]*
cadena =[\"] [^\"\n]* [\"\n]
cadena2 =[\'] [^\'\n]* [\'\n]
booleano =true|false
esp = \t|\f|" "|\r|\n



%%

// Acciones
/* Espacios en blanco */

     




{esp}     {/*Ignorar*/}
{entero} { return new Symbol(sym.entero,yycolumn,yyline,yytext());}
{decimal} { return new Symbol(sym.decimal,yycolumn,yyline,yytext());}
{booleano} { return new Symbol(sym.booleano,yycolumn,yyline,yytext());}
{cadena} { return new Symbol(sym.cadena,yycolumn,yyline,yytext());}
{cadena2} { return new Symbol(sym.cadena,yycolumn,yyline,yytext());}

   
","    { return new Symbol(sym.coma,yycolumn,yyline,yytext());}
";"    { return new Symbol(sym.puntocoma,yycolumn,yyline,yytext());}
"("    { return new Symbol(sym.para,yycolumn,yyline,yytext());}
")"    { return new Symbol(sym.parc,yycolumn,yyline,yytext());}
"+"    { return new Symbol(sym.suma,yycolumn,yyline,yytext());}
"-"    {     return new Symbol(sym.resta,yycolumn,yyline,yytext());}
"*"    {    return new Symbol(sym.multi,yycolumn,yyline,yytext());}
"/"    {   return new Symbol(sym.div,yycolumn,yyline,yytext());}


{Comentario} { /*Se ignora.*/}
{ComentarioMulti} {  /*Se ignora.*/}
/*Recolección de errores*/
.   
{               
   


        System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1));
}


