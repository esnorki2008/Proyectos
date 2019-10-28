#include "graficador.h"
#include <QCoreApplication>
#include <QDebug>
#include <QtSerialPort/QSerialPort>
#include  "sintaxis.h"
Graficador::Graficador(QObject *parent) : QObject(parent)
{

}

void Graficador::Graficar(NodoAST *Raiz){






        grafo = "digraph G{";
        grafo += "node[shape=\"box\"];";
        grafo += "NodoAST0[label=\"" + escapar(Raiz->tipo +"\n" + QString::number(Raiz->tipo_)+ "\n("+ QString::number(Raiz->linea)+","+ QString::number(Raiz->columna)+")"+"\n"+Raiz->valor)  + "\"];\n";
        this->contador = 1;
        recorrerAST("NodoAST0", Raiz);
        grafo += "}";




    GenerarImagen(grafo);


   // GenerarImagen(grafo);
}
QString Graficador::escapar(QString cadena)
{
    cadena = cadena.replace("\\", "\\\\");
    cadena = cadena.replace("\"", "\\\"");
    return cadena;
}


void Graficador::recorrerAST(QString padre, NodoAST *hijo)
{
    if (hijo!=nullptr){
    int x = 0;
    for (x = 0 ; x < hijo->hijos.count() ; x++)
    {


        NodoAST NodoAST = hijo->hijos[x];



        QString nombreHijo = "NodoAST" +  QString::number(contador);//  this->contador;
        grafo += nombreHijo + "[label=\"" + escapar( NodoAST.tipo +"\n" + QString::number(NodoAST.tipo_)+ "\n("+ QString::number(NodoAST.linea)+","+ QString::number(NodoAST.columna)+")\n"+NodoAST.valor)  + "\"];\n";
        grafo += padre + "->" + nombreHijo + ";\n";
        contador++;


        recorrerAST(nombreHijo, &NodoAST);

    }

    }
}
void Graficador:: GenerarImagen(QString Entrada)
{
    QFileInfo fi("temp");


    QString grafoDOT = Entrada;
  //  qDebug()<<" SALIDAAAAAAAA   "<<fi.absolutePath();
    QString path = fi.absolutePath() +"/";
  //  QString path = " W:\\ ";

    QFile qFile(path+"grafo.txt");
    if(qFile.open(QIODevice::WriteOnly))
    {
        QTextStream out(&qFile); out << grafoDOT;
        qFile.close();
    }

    QString cadenaComando = "dot -Tjpg " + path + "grafo.txt -o " + path+"grafo.jpg ";


   // std::cout << cadenaComando.toStdString() << "\n" << endl;

    system(cadenaComando.toUtf8().constData());

}
