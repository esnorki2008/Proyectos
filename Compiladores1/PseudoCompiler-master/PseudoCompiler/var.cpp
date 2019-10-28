#include "var.h"

Var::Var(int Tipo,QString Nombre,QString Lexema,int Tamano)
{
    this->Tipo=Tipo;
    this->Lexema=Lexema;
    this->Tamano=Tamano;
    this->Nombre=Nombre;
}
void Var::Add(Var nd){
     this->Arreglo.append(nd);
}
void Var::SetLexema(QString Lexama){
this->Lexema=Lexama;

}
