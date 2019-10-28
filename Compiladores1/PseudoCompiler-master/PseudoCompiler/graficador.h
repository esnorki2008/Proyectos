#ifndef GRAFICADOR_H
#define GRAFICADOR_H
#include <fstream>
#include <iostream>
#include <QString>
#include <fstream>
#include <QtWidgets>
#include <QObject>
#include <NodoAST.h>
class Graficador : public QObject
{
    Q_OBJECT
public:
    explicit Graficador(QObject *parent = nullptr);
    void Graficar(NodoAST *Raiz);
signals:

private:
QString escapar(QString cadena);
void recorrerAST(QString padre, NodoAST *hijo);
void GenerarImagen(QString Entrada);
NodoAST *Raiz=nullptr;
int contador=0;
QString grafo="";
public slots:
};

#endif // GRAFICADOR_H
