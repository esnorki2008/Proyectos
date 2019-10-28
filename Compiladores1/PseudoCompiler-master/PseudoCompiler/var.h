#ifndef VAR_H
#define VAR_H
#include "qlist.h"

class Var
{
public:
    Var(int Tipo,QString Nombre,QString Lexema,int Tamano);
    void SetLexema(QString Lexama);
    void Add(Var nd);
    int Tamano;
    int a=0;
    int b=0;
    int c=0;
    int Tipo;
    QString Nombre;
    QString Lexema;
    QList<Var> Arreglo;
};

#endif // VAR_H
