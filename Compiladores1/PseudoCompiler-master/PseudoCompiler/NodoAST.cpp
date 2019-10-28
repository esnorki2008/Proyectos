#include "NodoAST.h"
#include <qdebug.h> // Para imprimir en consola.
NodoAST::NodoAST()
{
    tipo ="";
    valor = "";
    linea = 0;
    columna = 0;
    hijos =  QList<NodoAST>();
}

NodoAST:: NodoAST(int l, int c, QString t, QString v)
{
    linea = l;
    columna = c;
    tipo = t;
    valor = v;
    tipo_ = getTipo();
    hijos = QList<NodoAST>();
}

NodoAST:: NodoAST(int l, int c, QString t, QString v,NodoAST *Posi)
{
    linea = l;
    columna = c;
    tipo = t;
    valor = v;
    PosT=Posi;
    tipo_ = getTipo();
    hijos = QList<NodoAST>();
}

void NodoAST::add(NodoAST nd)
{
    this->hijos.append(nd);
}
void NodoAST::Actualizar(){

        this->tipo_=this->getTipo();

}


int NodoAST::getTipo()
{
    this->tipo= this->tipo.toLower();
    if(this->tipo=="entero" ||this->tipo=="int"){return 1;}
    if(this->tipo=="string"){return 15;}
    if(this->tipo=="booleano"||this->tipo=="bool"){return 3;}
    if(this->tipo=="char"){return 4;}
    if(this->tipo=="decimal" ||this->tipo=="double"){return 5;}
    if(this->tipo=="suma"){return 6;}
    if(this->tipo=="menos"){return 7;}
    if(this->tipo=="multi"){return 8;}
    if(this->tipo=="division"){return 9;}
    if(this->tipo=="potencia"){return 10;}
    if(this->tipo=="operacion"){return 11;}
    if(this->tipo=="identificador"){return 12;}
    if(this->tipo=="cuerpo"){return 13;}
    if(this->tipo=="asignacion"){return 14;}
    if(this->tipo=="cadena"){return 15;}
    if(this->tipo=="parafor"){return 16;}
    if(this->tipo=="condicionfor"){return 17;}
    if(this->tipo=="vectorpos"){return 18;}
    if(this->tipo=="si"){return 19;}
    if(this->tipo=="sino"){return 20;}

    if(this->tipo=="mayor"){return 21;}
    if(this->tipo=="menor"){return 22;}

    if(this->tipo=="menorigual"){return 33;}
    if(this->tipo=="mayorigual"){return 34;}

    if(this->tipo=="anddoble"){return 35;}
    if(this->tipo=="ordoble"){return 36;}
    if(this->tipo=="admiracion"){return 38;}

    if(this->tipo=="diferente"){return 37;}


    if(this->tipo=="imprimir"){return 23;}
    if(this->tipo=="show"){return 24;}
    if(this->tipo=="igualdoble"){return 25;}
    if(this->tipo=="contenidov"){return 26;}
    if(this->tipo=="negativo"){return 27;}
    if(this->tipo=="asignacionmuchas"){return 28;}
    if(this->tipo=="arreglo"){return 29;}
    if(this->tipo=="llavev"){return 30;}
    if(this->tipo=="asignacionarreglo"){return 31;}
    if(this->tipo=="decision"){return 32;}

    if(this->tipo=="sumadoble"){return 39;}
    if(this->tipo=="restadoble"){return 40;}
    if(this->tipo=="repetir"){return 41;}

    qDebug()<<this->tipo<<"SAAAAAAAAAAAAAA************************************************************/-";
    return 0;
}
