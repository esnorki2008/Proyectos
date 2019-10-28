#ifndef SINTAXIS_H
#define SINTAXIS_H
#include "NodoAST.h"
#include "qlist.h"
#include "var.h"
#include "stdlib.h"
#include <vector>
#include "show.h"

class Sintaxis
{
public:
    Sintaxis(NodoAST *Raiz);
     QList<Var> *Variables= new QList<Var>;
 QList<NodoAST> *Errores= new QList<NodoAST>;
 QList<QString> *Consola= new QList<QString>;
 QList<Show> *Mensajes= new QList<Show>;
private:
    int PosI=-1;
    int PosF=-1;
    int A[3];


    NodoAST *Analizar(NodoAST *Raiz);
    NodoAST *Suma(NodoAST *Raiz);
    NodoAST *Resta(NodoAST *Raiz);
    NodoAST *Multiplicacion(NodoAST *Raiz);
    NodoAST *Division(NodoAST *Raiz);
    NodoAST *Potencia(NodoAST *Raiz);
    double PO(QString In,QString Fin);
    int CChar(QString Entrada);
    NodoAST Raiz;


};

#endif // SINTAXIS_H
