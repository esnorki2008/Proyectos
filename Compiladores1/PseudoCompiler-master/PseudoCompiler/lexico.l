%option case-insensitive
%option yylineno
%{
#include <cstdlib>
#include "parser.h"
#include <QString>
#include <stdio.h>
#include <iostream>
#include "qlist.h"

int yylex();
void yyerror(const char *s);
int linea=0; // Para contar lineas
int columna = 1; // para las columnas
int anterior = 0;
%}

%option header-file="scanner.h"
%option outfile="scanner.cpp"
%option noyywrap
%option nounput

comenta  >>[^\r\n]*[^\r\n]

coment   <-[^\->]*->

letra [a-zñA-ZÑ]+
numero [0-9]+
booleano ("verdadero"|"falso"|"true"|"false")
decimal [0-9]+"."[0-9]+
caracter "\'"("\\")?({letra}|{numero})"\'"
iden {letra}({letra}|{numero}|"_")*
cadena "\""[^"\""]*"\""

si ("si")
sino ("sino")
imprimir ("imprimir")
show ("show")
parafor ("para")
repetir ("repetir")
arreglo ("arreglo")





%x COM COMM

%%
INITIAL
{
"//" {  BEGIN COM; }
"/*" {  BEGIN COMM; }

{comenta}  {    }
{coment}  {   }

{numero}          {  columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return entero;}
{arreglo}          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return arreglo;}
{decimal}          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return decimal;}
{caracter}          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return caracter;}
{booleano}          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return booleano;}
{cadena}          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return cadena;}
"("          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return para; }
")"          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return parc; }
";"          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return puntocoma; }
"+"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return suma; }
"-"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return menos; }
"*"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return multi; }
"/"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return division; }
"^"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return potencia; }
{si}         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return si; }
{sino}         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return sino; }
"="         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return igual; }
"=="         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return igualdoble; }
"!="         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return diferente; }
","         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return coma; }
"<"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return menor; }
">"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return mayor; }
">="         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return menorigual; }
"<="         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return mayorigual; }
"||"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return ordoble; }
"&&"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return anddoble; }

"++"        {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return sumadoble; }
"--"        {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return restadoble; }

"!"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return admiracion; }
"["         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return cora; }
"]"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return corc; }
"{"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return llaa; }
"}"         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return llac; }
{imprimir}         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return imprimir; }
{show}         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return show; }
{parafor}         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return parafor; }
{repetir}         {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return repetir; }


{iden}          {   columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext); return id;}







"\t"            {
                    strcpy(yylval.TEXT, yytext); columna=26;
                }
[[:blank:]]     { columna++; }
"\n"            {linea++; columna = 0; anterior = 0;}
.               {
                    columna=columna+ anterior;  anterior = strlen(yytext);strcpy(yylval.TEXT, yytext);
/*                    errorT *nuevoE = new errorT("Lexico",yytext,linea, columna);
                    listaErrores->append(*nuevoE);
*/
                    std::cout <<yytext <<" Error Lexico en la linea "<< linea << " Columna:"<< columna<<  std::endl;
                }
}
<COMM>"*/" { BEGIN INITIAL; }
<COMM>"\n" {linea++;};
<COMM>.;
<COMM><<EOF>> { yyerror("unclosed comment"); }
<COM>"\n" { linea++; columna = 0; BEGIN INITIAL; };
<COM>.;
%%
