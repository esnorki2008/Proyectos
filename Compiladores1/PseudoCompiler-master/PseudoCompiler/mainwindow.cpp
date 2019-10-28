
#include "mainwindow.h"
#include "ui_mainwindow.h"

#include <qdebug.h>
#include <NodoAST.h>
#include <parser.h>  // Nuestro parser
#include <scanner.h>  // Nuestro scanner
#include  "sintaxis.h"

extern int yyparse(); //
extern NodoAST *raiz; // Raiz del arbol
extern int linea; // Linea del token
extern int columna; // Columna de los tokens
extern int yylineno;
extern NodoAST *VRaiz;
extern Graficador *Sali;

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::on_pushButton_clicked()
{
    QString programa = ui->textEdit->toPlainText().toLower();
    YY_BUFFER_STATE buffer = yy_scan_string(programa.toUtf8().constData());

    linea = 0;
    columna = 0;
    yylineno = 0;

    if(yyparse()==0)
    {
        if(VRaiz!=nullptr){
        Graficador *Graf= new Graficador();
        Graf->Graficar(VRaiz);

        Sintaxis *Sinta = new Sintaxis(VRaiz);


        qDebug()<<"----------------------------------VARIABLES----------------------------------";
        for(int i=0;i<Sinta->Variables->count();i++){

             ui->textEdit_2->setText( ui->textEdit_2->toPlainText()+Sinta->Variables->at(i).Nombre+"        "+Sinta->Variables->at(i).Lexema+Sinta->Variables->at(i).Tipo+"\n");

                     qDebug()<<Sinta->Variables->at(i).Nombre<<"        "<<Sinta->Variables->at(i).a<<Sinta->Variables->at(i).b<<Sinta->Variables->at(i).c<<Sinta->Variables->at(i).Lexema<<Sinta->Variables->at(i).Tipo;
                     if(Sinta->Variables->at(i).a!=0||Sinta->Variables->at(i).b!=0||Sinta->Variables->at(i).c!=0){
                     ui->textEdit_2->setText( ui->textEdit_2->toPlainText()+"VariablesDelArreglo"+"\n");
                         qDebug()<<"++++++++++++++++++++++++++++++++++++++++VARIABLES++++++++++++++++++++++++++++++++++++++++";
                        for(int k=0;k<Sinta->Variables->at(i).Arreglo.count();k++){

                            ui->textEdit_2->setText( ui->textEdit_2->toPlainText()+Sinta->Variables->at(i).Arreglo.at(k).Nombre+"        "+Sinta->Variables->at(i).Arreglo.at(k).Lexema+"\n");
                                qDebug()<<Sinta->Variables->at(i).Arreglo.at(k).Nombre<<"        "<<Sinta->Variables->at(i).Arreglo.at(k).Lexema;
                        }
                        qDebug()<<"++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++";

                    }

        }

        qDebug()<<"----------------------------------Errores----------------------------------";
        for(int i=0;i<Sinta->Errores->count();i++){
                     qDebug()<<Sinta->Errores->at(i).tipo<<"        "<<Sinta->Errores->at(i).valor;

              ui->textEdit_4->setText( ui->textEdit_4->toPlainText()+Sinta->Errores->at(i).valor+"\n");
        }

        for(int i=0;i<Sinta->Consola->count();i++){
                     qDebug()<<Sinta->Consola->at(i);
       ui->textEdit_3->setText( ui->textEdit_3->toPlainText()+Sinta->Consola->at(i)+"\n");

        }

        for(int i=0;i<Sinta->Mensajes->count();i++){
                     qDebug()<<Sinta->Mensajes->at(i).A<<"      "<<Sinta->Mensajes->at(i).B;
                     std::string str = Sinta->Mensajes->at(i).A.toStdString();
                     const char* p = str.c_str();

                     std::string str1 = Sinta->Mensajes->at(i).B.toStdString();
                     const char* p1 = str.c_str();


                     QMessageBox::information(
                         this,
                         tr(p),
                         tr(p1) );


        }

}

    }

}
