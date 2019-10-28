%{
#include "scanner.h"//se importa el header del analisis sintactico
#include "NodoAST.h"
#include "qdebug.h"
#include <iostream>
#include <stdio.h>
#include <string.h>
#include <graficador.h>

extern int yylineno; //linea actual donde se encuentra el parser (analisis lexico) lo maneja BISON
extern int columna; //columna actual donde se encuentra el parser (analisis lexico) lo maneja BISON
extern char *yytext; //lexema actual donde esta el parser (analisis lexico) lo maneja BISON
extern NodoAST *raiz; // Raiz del arbol

extern NodoAST *VRaiz=nullptr;
extern Graficador *Sali= new Graficador();
int yyerror(const char* mens)
{

    std::cout << mens <<" "<<yytext<< std::endl;
    return 0;
}
%}
//error-verbose si se especifica la opcion los errores sintacticos son especificados por BISON
%defines "parser.h"
%output "parser.cpp"
%error-verbose
%locations

%union{
//se especifican los tipo de valores para los no terminales y lo terminales
char TEXT [256];
class NodoAST *Nodo;
}

//TERMINALES DE TIPO TEXT, SON STRINGS
%token<TEXT> entero;
%token<TEXT> decimal;
%token<TEXT> caracter;
%token<TEXT> booleano;
%token<TEXT> cadena;
%token<TEXT> id;
%token<TEXT> suma;
%token<TEXT> menos;
%token<TEXT> multi;
%token<TEXT> division;
%token<TEXT> potencia;
%token<TEXT> puntocoma;
%token<TEXT> si;
%token<TEXT> sino;
%token<TEXT> igual;
%token<TEXT> igualdoble;
%token<TEXT> diferente;
%token<TEXT> coma;
%token<TEXT> menor;
%token<TEXT> menorigual;
%token<TEXT> mayor;
%token<TEXT> mayorigual;
%token<TEXT> ordoble;
%token<TEXT> anddoble;
%token<TEXT> admiracion;
%token<TEXT> cora;
%token<TEXT> llaa;
%token<TEXT> llac;
%token<TEXT> corc;
%token<TEXT> imprimir;
%token<TEXT> show;

%token<TEXT> sumadoble;
%token<TEXT> restadoble;



%token<TEXT> para;
%token<TEXT> parc;
%token<TEXT> parafor;
%token<TEXT> repetir;
%token<TEXT> arreglo;

%token<TEXT> comenta;
%token<TEXT> coment;







/*No terimanesl*/
%type<Nodo> INI;//
%type<Nodo> MAS; // Aritmetica
%type<Nodo> CUERPO; // lista de instrucciones
%type<Nodo> VAL;
%type<Nodo> OPE;
%type<Nodo> VAR;
%type<Nodo> ASIG;
%type<Nodo> ALTE;
%type<Nodo> CONTE;
%type<Nodo> CONTES;
%type<Nodo> CONTESS;
%type<Nodo> NIDO;
%type<Nodo> IMPRI;
%type<Nodo> SHO;
%type<Nodo> SIS;
%type<Nodo> ELS;
%type<Nodo> PARAS;
%type<Nodo> ESC;
%type<Nodo> REPE;
%type<Nodo> CORPO;

%left ordoble
%left anddoble
%left admiracion
%left igualdoble diferente menor menorigual mayor mayorigual
%left suma menos
%left multi division
%left potencia

%start INI
%%

INI: CORPO {
   // Graficador *Graf = new Graficador();
   
    
    VRaiz=$1;
   
   
  //  Graf->Graficar($1);

   // Sali=Graf;
    
    
   // Graficador *Graf = new Graficador();   raiz=$1;


}
;
CORPO: CUERPO  CORPO
{
         /*   $$=$1;
            NodoAST *nodo = new NodoAST(@2.first_line, @2.first_column,"operacion","operacion");
            if($2!=nullptr ){
            nodo->add(*$2);
            $$->add(*nodo);
            }*/

            $$=$2;
            $$->add(*$1);
}
|
{
   /* NodoAST *nodo = new NodoAST(0, 0,"nulo","nulo"); $$=nodo;*/
    NodoAST *nodo = new NodoAST(0, 0,"cuerpo","cuerpo");
    $$=nodo;
    
}
;
CUERPO: VAR {$$ = $1;}
    |IMPRI {$$ = $1;}
    |SHO {$$ = $1;}
    |ALTE {$$ = $1;}
    |SIS {$$ = $1;}
    |ASIG {$$ = $1;}
    |PARAS {$$ = $1;}
    |REPE {$$ = $1;}
; 

VAR: id id MAS OPE puntocoma {
    

    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"asignacion","asignacion");

    NodoAST *noda = new NodoAST(@1.first_line, @1.first_column,$1,$2);

    $3->add(*noda);

    nod->add(*$3);
    nod->add(*$4);


$$=nod;
}
    |id id puntocoma{NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,$1,$2); $$=nod;}



   
    | id arreglo id MAS CONTES puntocoma {

        NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,$2,"arreglo");
        NodoAST *nods = new NodoAST(@1.first_line, @1.first_column,$1,$3);
        if($4!=nullptr){
        nods->add(*$4); 
        }
        nod->add(*nods);
         nod->add(*$5);

         
         
          $$=nod;}
;

CONTE: cora OPE corc CONTE 
{
/*
NodoAST *nodo = new NodoAST(@1.first_line, @1.first_column,"contenidov","contenidov");
nodo->add(*$2);
nodo->add(*$4);
$$=nodo;
*/
$4->add(*$2);
$$=$4;
}
    |cora OPE corc {
        
        NodoAST *nodo = new NodoAST(@1.first_line, @1.first_column,"llavev","llavev");  nodo->add(*$2); 
        
         $$ = nodo;}
;
CONTES: CONTE {$$ = $1;}
    | igual llaa CONTESS llac {$$ = $3;}
;
CONTESS: llaa NIDO llac coma CONTESS 
{
NodoAST *nodo = new NodoAST(@2.first_line, @2.first_column,"contenidov","contenidov");
nodo->add(*$2);
nodo->add(*$5);
$$=nodo;
}
    |llaa NIDO llac {$$ = $2;}
    |NIDO {$$ = $1;}
;



MAS: coma id MAS
{
NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"contenidov",$2);

$3->add(*nod);  

$$=$3;

}
    | igual 
{
    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"asignacionmuchas","asignacionmuchas");
   $$=nod;
}
    |
{
    //NodoAST *nod = new NodoAST(0, 0,"nulo","nulo");
    $$=nullptr;
}

;
VAL:booleano {$$ = new NodoAST(@1.first_line, @1.first_column,"booleano",$1);}
    |entero {$$ = new NodoAST(@1.first_line, @1.first_column,"entero",$1);}
    |cadena {$$ = new NodoAST(@1.first_line, @1.first_column,"cadena",$1);}
    |decimal {$$ = new NodoAST(@1.first_line, @1.first_column,"decimal",$1);}
    |id {$$ = new NodoAST(@1.first_line, @1.first_column,"identificador",$1);}
    |id CONTE {$$ = new NodoAST(@1.first_line, @1.first_column,"vectorpos",$1); $$->add(*$2);}
    |caracter {$$ = new NodoAST(@1.first_line, @1.first_column,"char",$1);}
;
ASIG: id igual OPE puntocoma
{
    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"asignacion","asignacion");
    NodoAST *opp = new NodoAST(@1.first_line, @1.first_column,"identificador",$1); 
    nod->add(*opp);

     nod->add(*$3); 
     
     $$=nod;
}
    |id CONTE igual OPE puntocoma
{
    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"asignacion","asignacion");
    NodoAST *opp = new NodoAST(@1.first_line, @1.first_column,"asignacionarreglo",$1);
     opp->add(*$2);
    nod->add(*opp);
    
     nod->add(*$4); 
     $$=nod;
}
;
ALTE: VAL sumadoble 
{
    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"sumadoble",$2); 
    NodoAST *opp = new NodoAST(@1.first_line, @1.first_column,"entero","1"); 
    nod->add(*$1); nod->add(*opp); $$=nod;
}
    |VAL restadoble 
{
    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"restadoble",$2); 
    NodoAST *opp = new NodoAST(@1.first_line, @1.first_column,"entero","1"); 
    nod->add(*$1); nod->add(*opp); $$=nod;
}
;

NIDO: OPE coma NIDO 
{
NodoAST *nodo = new NodoAST(@2.first_line, @2.first_column,"contenidov","contenidov");
nodo->add(*$1);
nodo->add(*$3);
$$=nodo;
}
|OPE {$$ = $1;}
;
IMPRI: imprimir para OPE parc puntocoma {NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"imprimir","imprimir"); nod->add(*$3);  $$=nod;}
;
SHO: show para OPE coma OPE parc puntocoma {NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"show","show"); nod->add(*$3); nod->add(*$5); $$=nod;}
;
SIS: si para OPE parc llaa CORPO llac ELS {

    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"si",$1); 

    NodoAST *noda = new NodoAST(@1.first_line, @1.first_column,"decision","decision"); 
    
    noda->add(*$6);
    if($8!=nullptr){
    noda->add(*$8); 
    }

    
    nod->add(*$3);
    nod->add(*noda);
    
    
    //NodoAST *condi = new NodoAST(@1.first_line, @1.first_column,"condicionsi",$1); 


   
    
    
    $$=nod;

    
   /* NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"si",$1); 
    
    NodoAST *condi = new NodoAST(@1.first_line, @1.first_column,"condicionsi",$1); 
    condi->add(*$6);
    
    nod->add(*condi); nod->add(*$8); 
    
    
    $$=nod;*/
    
    }
;
ELS: sino llaa CORPO llac ELS {
    
    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"sino",$1); 



nod->add(*$3); nod->add(*$5); 


 $$=nod;



}
    |sino SIS {NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"sino",$1); nod->add(*$2);  $$=nod;}
    | { 
        NodoAST *nod = new NodoAST(0, 0,"cuerpo","cuerpo"); 
        $$=nod;
    }
;
PARAS: parafor para ESC  OPE puntocoma ALTE parc llaa CORPO llac 
{
    NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"parafor",$1); 
   
    NodoAST *condicional = new NodoAST(@3.first_line, @3.first_column,"condicionfor","condicionfor"); 
    condicional->add(*$3);condicional->add(*$4);condicional->add(*$6);
    condicional->add(*$9);
    nod->add(*condicional);  $$=nod;
} 
;
ESC: ASIG{$$ = $1;}
    |VAR{$$ = $1;}
;
REPE: repetir para OPE parc llaa CORPO llac {NodoAST *nod = new NodoAST(@1.first_line, @1.first_column,"repetir",$1); nod->add(*$3); nod->add(*$6); $$=nod;}
;

OPE:OPE anddoble OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"anddoble",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE ordoble OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"ordoble",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE mayor OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"mayor",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE menor OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"menor",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE menorigual OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"menorigual",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE mayorigual OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"mayorigual",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE diferente OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"diferente",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |admiracion OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"admiracion",$1); nod->add(*$2);  $$=nod;}
    |OPE suma OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"suma",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE menos OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"menos",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE division OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"division",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE multi OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"multi",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE potencia OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"potencia",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |OPE igualdoble OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"igualdoble",$2); nod->add(*$1); nod->add(*$3); $$=nod;}
    |suma OPE {$$ = $2;}
    |menos OPE { $$ = new NodoAST(@2.first_line, @2.first_column,"negativo",$1); $$->add(*$2);}
    |para OPE parc {$$ = $2;}
    |OPE para OPE parc {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"multi","*"); nod->add(*$1); nod->add(*$3); $$=nod;}
    |para OPE parc OPE {NodoAST *nod = new NodoAST(@2.first_line, @2.first_column,"multi","*"); nod->add(*$2); nod->add(*$4); $$=nod;}
    |VAL {$$ = $1;}
    |ALTE {$$ = $1;}
;

%%
