#include "sintaxis.h"
#include <qlist.h>
#include <qdebug.h>
#include <stdlib.h>
#include <string>

#include <math.h>

Sintaxis::Sintaxis(NodoAST *Raiz)
{

    Analizar(Raiz);
}

NodoAST *Sintaxis::Analizar(NodoAST *Raiz){
  //   qDebug()<<Raiz->tipo_;

    NodoAST *NodoTemp= new NodoAST(Raiz->linea,Raiz->columna,Raiz->tipo,Raiz->valor);
    NodoTemp->hijos=Raiz->hijos;
    if(Raiz!=nullptr ){
            switch(Raiz->tipo_){

            case 6:{

                NodoTemp=Suma(NodoTemp);
                NodoTemp->Actualizar();

                return NodoTemp;
           }
            case 7:{
                NodoTemp=Resta(NodoTemp);
                NodoTemp->Actualizar();
                return NodoTemp;

           }
            case 8:{
                NodoTemp=Multiplicacion(NodoTemp);
                NodoTemp->Actualizar();
                return NodoTemp;
           }
            case 9:{
                NodoTemp=Division(NodoTemp);
                NodoTemp->Actualizar();
                return NodoTemp;
           }
            case 10:{
                NodoTemp=Potencia(NodoTemp);
                NodoTemp->Actualizar();
                return NodoTemp;
           }
            case 11:{
                break;
           }
            case 12:{

                for(int i=0;i<Variables->count();i++){

                    if(Variables->at(i).Nombre==NodoTemp->valor||Variables->at(i).Nombre==NodoTemp->NombreT){

                        NodoTemp->tipo_=Variables->at(i).Tipo;
                        NodoTemp->NombreT=NodoTemp->valor;

                        NodoTemp->valor=Variables->at(i).Lexema;


                    }


                }
            qDebug()<<"SDDDDDD      "<<NodoTemp->valor<<"       "<<NodoTemp->tipo_;
                return NodoTemp;

           }
            case 13:{


                for (int x= NodoTemp->hijos.size()-1; x>=0 ; x--)
                 {


                                    NodoAST nodo = NodoTemp->hijos.at(x);
                                    Analizar(&nodo);
                 }


                return NodoTemp;
            }
            case 14:{



                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);

                NodoAST *NodoDer=Analizar(&nodo) ;

                NodoAST *NodoIzq=Analizar(&nodo1) ;



//qDebug()<<NodoIzq->tipo_<<"    "<<NodoDer->tipo_;


if(NodoDer->tipo_==1){

    NodoDer->tipo_=5;
}
if(NodoIzq->tipo_==1){

    NodoIzq->tipo_=5;
}



if(NodoDer->tipo_!=NodoIzq->tipo_){
    qDebug()<<NodoIzq->tipo_<<" ss   "<<NodoDer->tipo_;
    NodoTemp->tipo="Asignando Tipo Diferente al de la variable  /"+QVariant(NodoIzq->tipo_).toString()+"/   / "+QVariant(NodoDer->tipo_).toString()+"/";
    Errores->append(*NodoTemp);
}else{


                if(PosI==PosF){
                    for(int i=0;i<Variables->count();i++){

                        if((Variables->at(i).Nombre==NodoDer->valor||Variables->at(i).Nombre==NodoDer->NombreT) && (A[0]!=0 ||A[1]!=0||A[2]!=0) ){
                            NodoDer->valor=Variables->at(i).Lexema;

                            qDebug()<<NodoDer->valor;
                            break;
                        }
                    }

                    for(int i=0;i<Variables->count();i++){




//////SEGUIR

QString Con=QVariant(A[0]).toString()+QVariant(A[1]).toString()+QVariant(A[2]).toString();

                        if(Variables->at(i).Nombre==NodoIzq->valor ||Variables->at(i).Nombre==NodoIzq->NombreT){

                            if(Variables->at(i).a!=0||Variables->at(i).b!=0||Variables->at(i).c!=0){

                              //  A[0]=0;A[1]=0;A[2]=0;

                            //    qDebug()<<"PASOssssss   "<<Con;
                                for(int k=0;k<Variables->at(i).Arreglo.count();k++){



                                    if(Variables->at(i).Arreglo.at(k).Nombre==Con){



                                        Var *Nueva= new Var(Variables->at(i).Tipo,Variables->at(i).Nombre,NodoDer->valor,1);
                                        Var *Nueva2= new Var(NodoIzq->tipo_,Con,NodoDer->valor,1);


                                        Nueva->a=Variables->at(i).a;
                                        Nueva->b=Variables->at(i).b;
                                        Nueva->c=Variables->at(i).c;

                                        Nueva->Arreglo=Variables->at(i).Arreglo;
                                        Nueva->Arreglo.replace(k,*Nueva2);
                                        Variables->replace(i,*Nueva);


                                        return NodoTemp;



                                    }

                                }
                                Var *Nueva= new Var(Variables->at(i).Tipo,Variables->at(i).Nombre,NodoDer->valor,1);
                                Var *Nueva2= new Var(NodoIzq->tipo_,Con,NodoDer->valor,1);

                                Nueva->a=Variables->at(i).a;
                                Nueva->b=Variables->at(i).b;
                                Nueva->c=Variables->at(i).c;

                                Nueva->Arreglo=Variables->at(i).Arreglo;
                                Nueva->Arreglo.append(*Nueva2);
                                Variables->replace(i,*Nueva);

                                return NodoTemp;

                            }else{

                          //  qDebug()<<Variables->at(i).Nombre<<"  "<<NodoIzq->valor<<"  TMPOSI  "<<PosI<<"  "<<PosF;

                        Var *Nueva= new Var(Variables->at(i).Tipo,Variables->at(i).Nombre,NodoDer->valor,1);
                         Variables->replace(i,*Nueva);
                        return NodoTemp;



}
                        }

                    }

                    NodoTemp->tipo="Asignando A Variable No Declarada  "+NodoIzq->valor+"   "+NodoDer->valor;
                    Errores->append(*NodoTemp);


                }else{




                    for(int i=0;i<Variables->count();i++){

                        if(Variables->at(i).Nombre==NodoDer->valor){
                            NodoDer->valor=Variables->at(i).Lexema;

                            break;
                        }
                    }




                for(int i=PosI;i<PosF;i++){
               // qDebug()<<Variables->at(i).Nombre<<"  TMPOSI  "<<PosI<<"  "<<PosF;



                Var *Nueva= new Var(Variables->at(i).Tipo,Variables->at(i).Nombre,NodoDer->valor,1);

                Variables->replace(i,*Nueva);




                }PosI=-1;PosF=-1;
}


}


                return NodoTemp;
           }
            case 15:{
                break;
           }
            case 16:{
                NodoAST nodo0 =NodoTemp->hijos.at(0);


                NodoAST *Nodo0=Analizar(&nodo0) ;

                return NodoTemp;
           }
            case 17:{
                NodoAST nodo0 =NodoTemp->hijos.at(0);
                NodoAST nodo1 =NodoTemp->hijos.at(1);
                NodoAST nodo2 =NodoTemp->hijos.at(2);

                 NodoAST *Nodo0=Analizar(&nodo0) ;

                NodoAST nodo3 =NodoTemp->hijos.at(3);

                 NodoAST nodo111 =nodo1.hijos.at(0);
                NodoAST *nodo11 =Analizar(&nodo111);
                 NodoAST nodo112 =nodo1.hijos.at(1);
                NodoAST *nodo12 =Analizar(&nodo112);

                NodoAST nodo21 =nodo2.hijos.at(0);


                NodoAST nodo22 =nodo2.hijos.at(1);


                int Algo=nodo11->valor.toDouble()-nodo12->valor.toDouble();
                if(Algo<0){Algo=nodo12->valor.toDouble()-nodo11->valor.toDouble();}


qDebug()<<Algo<<"SSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS  "<<nodo11->valor<<"   "<<nodo12->valor;

                for(int h=0;h<Algo;h++){
                     NodoAST *nodo312 =Analizar(&nodo21);
                     NodoAST *Nodo33=Analizar(&nodo3) ;


                }

                //   NodoAST *Nodo2=Analizar(&nodo2) ;

                //int Veces=1;
                // qDebug()<<Veces;
/*
                while(Nodo1->valor=="true"||Nodo1->valor=="verdadero"){
                    Nodo1=Analizar(Nodo1) ;



                }
*/


            return NodoTemp;
           }
            case 18:{
QString Con=QVariant(A[0]).toString()+QVariant(A[1]).toString()+QVariant(A[2]).toString();

qDebug()<<"CASE18       "<<Con;
                for(int i=0;i<Variables->count();i++){

                        if(Variables->at(i).a!=0||Variables->at(i).b!=0||Variables->at(i).c!=0){




                            for(int k=0;k<Variables->at(i).Arreglo.count();k++){



                                if(Variables->at(i).Arreglo.at(k).Nombre==Con){


                                        NodoTemp->valor=Variables->at(i).Arreglo.at(k).Lexema;
                                        NodoTemp->tipo_=Variables->at(i).Arreglo.at(k).Tipo;


                                }

                            }
                          //   A[0]=0;A[1]=0;A[2]=0;
                              return NodoTemp;

                        }else{
                        }
                    }


                 return NodoTemp;
                }
            case 19:{
                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;

                qDebug()<<"SI  "<<NodoIzq->valor;

                if(NodoIzq->valor=="true"){
                    NodoAST nodo30=NodoDer->hijos.at(0);
                    Analizar(&nodo30) ;

                }else{
                    NodoAST nodo30=NodoDer->hijos.at(1);
                    Analizar(&nodo30) ;

                }

                return NodoTemp;
           }
            case 20:{
                break;
           }
            case 21:{
                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;

                double Der=0;
                double Izq=0;

                switch (NodoDer->tipo_) {
                case 1: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoDer->valor=="true"||NodoDer->valor=="verdadero"){
                    Der=1;
                    }
                    break;
                }
                case 4: {
                    Der=CChar(NodoDer->valor);
                    break;
                }
                case 5: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }

                }

                switch (NodoIzq->tipo_) {
                case 1: {
                    Izq=QVariant(NodoIzq->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoIzq->valor=="true"||NodoIzq->valor=="verdadero"){
                    Izq=1;
                    }
                    break;
                }
                case 4: {
                    Izq=CChar(NodoIzq->valor);
                    break;
                }
                case 5: {
                    Izq=QVariant(NodoIzq->valor).toDouble();
                    break;
                }

                }

                NodoTemp->tipo_=3;
                if(Izq>Der){
                             NodoTemp->valor="true";
                }else{
                    NodoTemp->valor="false";
                }

                return NodoTemp;
           }
            case 22:{
                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;



                double Der=0;
                double Izq=0;

                switch (NodoDer->tipo_) {
                case 1: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoDer->valor=="true"||NodoDer->valor=="verdadero"){
                    Der=1;
                    }
                    break;
                }
                case 4: {
                    Der=CChar(NodoDer->valor);
                    break;
                }
                case 5: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }

                }

                switch (NodoIzq->tipo_) {
                case 1: {
                    Izq=QVariant(NodoIzq->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoIzq->valor=="true"||NodoIzq->valor=="verdadero"){
                    Izq=1;
                    }
                    break;
                }
                case 4: {
                    Izq=CChar(NodoIzq->valor);
                    break;
                }
                case 5: {
                    Izq=QVariant(NodoIzq->valor).toDouble();
                    break;
                }

                }

                NodoTemp->tipo_=3;
                if(Izq<Der){
                             NodoTemp->valor="true";
                }else{
                    NodoTemp->valor="false";
                }
                return NodoTemp;
           }
            case 23:{
                NodoAST nodo0 =NodoTemp->hijos.at(0);

                NodoAST *Nodo0=Analizar(&nodo0) ;

                Consola->append("-->"+Nodo0->valor);


                return NodoTemp;
           }
            case 24:{
                NodoAST nodo0 =NodoTemp->hijos.at(0);
                NodoAST nodo1 =NodoTemp->hijos.at(1);

                NodoAST *Nodo0=Analizar(&nodo0) ;
                NodoAST *Nodo1=Analizar(&nodo1) ;

                Show *Nuevo = new Show(Nodo0->valor,Nodo1->valor);

                Mensajes->append(*Nuevo);



                return NodoTemp;
           }
            case 25:{

                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;

                qDebug()<<NodoDer->valor<<NodoIzq->valor;




                NodoTemp->tipo_=3;
                if(NodoDer->valor==NodoIzq->valor){
                             NodoTemp->valor="true";
                }else{
                    NodoTemp->valor="false";
                }

                return NodoTemp;
           }
            case 26:{


                return NodoTemp;
           }
            case 27:{
                break;
           }
            case 28:{


                int Largo=NodoTemp->hijos.count();
                int Tip=NodoTemp->hijos.last().tipo_;

                this->PosI=Variables->count();



                for(int i=0;i<Largo;i++){

                    Var *Nueva= new Var(Tip,NodoTemp->hijos.at(i).valor,"",1);

                    Variables->append(*Nueva);



                }

                this->PosF=Variables->count();
              //  qDebug()<<"SARIDDSA  "<<PosI<<"  "<<PosF;

                NodoTemp->tipo_=Tip;
                return NodoTemp;
           }
            case 29:{
                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;

                Var *Nueva= new Var(NodoIzq->tipo_,NodoIzq->valor,"",1);
                Nueva->a=A[0];Nueva->b=A[1];Nueva->c=A[2];
                A[0]=0;A[1]=0;A[2]=0;

                Variables->append(*Nueva);


                return NodoTemp;
           }
            case 30:{

                for(int i=0;i<NodoTemp->hijos.count();i++){
                    A[2-i]=NodoTemp->hijos.at(i).valor.toInt();

                    if(NodoTemp->hijos.at(i).tipo_!=1 && NodoTemp->hijos.at(i).tipo_!=5){
                        NodoTemp->tipo="Asignando A Variable Tipo Incorrecto En Los Parametros";
                        Errores->append(*NodoTemp);

                    }
                   // qDebug()<<"CASE30     "<<A[0]<<A[1]<<A[2];
                    NodoTemp->tipo_=NodoTemp->hijos.at(i).tipo_;

                }



                return NodoTemp;
           }
            case 31:{
                 NodoAST nodo1 =NodoTemp->hijos.at(0);
                 NodoAST *NodoDer=Analizar(&nodo1) ;
  NodoTemp->tipo_=NodoDer->tipo_;
                 for(int i=0;i<Variables->count();i++){

                         if(Variables->at(i).a!=0||Variables->at(i).b!=0||Variables->at(i).c!=0){
                             if(Variables->at(i).Nombre==NodoTemp->valor){
                                   NodoTemp->tipo_=Variables->at(i).Tipo;

                             }


                    }

                 }





                 return  NodoTemp;
            }
                case 32:{  return NodoTemp;}
                 case 33:{
                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;

                double Der=0;
                double Izq=0;

                switch (NodoDer->tipo_) {
                case 1: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoDer->valor=="true"||NodoDer->valor=="verdadero"){
                    Der=1;
                    }
                    break;
                }
                case 4: {
                    Der=CChar(NodoDer->valor);
                    break;
                }
                case 5: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }

                }

                switch (NodoIzq->tipo_) {
                case 1: {
                    Izq=QVariant(NodoIzq->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoIzq->valor=="true"||NodoIzq->valor=="verdadero"){
                    Izq=1;
                    }
                    break;
                }
                case 4: {
                    Izq=CChar(NodoIzq->valor);
                    break;
                }
                case 5: {
                    Izq=QVariant(NodoIzq->valor).toDouble();
                    break;
                }

                }

                NodoTemp->tipo_=3;
                if(Izq<=Der){
                             NodoTemp->valor="true";
                }else{
                    NodoTemp->valor="false";
                }

                return NodoTemp;






            }
                 case 34:{
                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;

                double Der=0;
                double Izq=0;

                switch (NodoDer->tipo_) {
                case 1: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoDer->valor=="true"||NodoDer->valor=="verdadero"){
                    Der=1;
                    }
                    break;
                }
                case 4: {
                    Der=CChar(NodoDer->valor);
                    break;
                }
                case 5: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }

                }

                switch (NodoIzq->tipo_) {
                case 1: {
                    Izq=QVariant(NodoIzq->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoIzq->valor=="true"||NodoIzq->valor=="verdadero"){
                    Izq=1;
                    }
                    break;
                }
                case 4: {
                    Izq=CChar(NodoIzq->valor);
                    break;
                }
                case 5: {
                    Izq=QVariant(NodoIzq->valor).toDouble();
                    break;
                }

                }

                NodoTemp->tipo_=3;
                if(Izq>=Der){
                             NodoTemp->valor="true";
                }else{
                    NodoTemp->valor="false";
                }

                return NodoTemp;





            }
                 case 35:{

                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;






                NodoTemp->tipo_=3;
                if((NodoDer->valor=="true") && (NodoIzq->valor=="true")){
                             NodoTemp->valor="true";
                }else{
                    NodoTemp->valor="false";
                }

                return NodoTemp;


            }
                 case 36:{
                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;






                NodoTemp->tipo_=3;
                if((NodoDer->valor=="true") || (NodoIzq->valor=="true")){
                             NodoTemp->valor="true";
                }else{
                    NodoTemp->valor="false";
                }

                return NodoTemp;



            }
                 case 37:{
                NodoAST nodo1 =NodoTemp->hijos.at(0);
                NodoAST nodo =NodoTemp->hijos.at(1);
                NodoAST *NodoDer=Analizar(&nodo) ;
                NodoAST *NodoIzq=Analizar(&nodo1) ;






                NodoTemp->tipo_=3;
                if(NodoDer->valor!=NodoIzq->valor){
                             NodoTemp->valor="true";
                }else{
                    NodoTemp->valor="false";
                }

                return NodoTemp;



            }
                 case 38:{

                NodoAST nodo1 =NodoTemp->hijos.at(0);


                NodoAST *NodoIzq=Analizar(&nodo1) ;






                NodoTemp->tipo_=3;
                if((NodoIzq->valor=="true")){
                             NodoTemp->valor="false";
                }else{
                    NodoTemp->valor="true";
                }

                return NodoTemp;


            }

                 case 39:{

                NodoAST nodo =NodoTemp->hijos.at(0);
                NodoAST *NodoDer=Analizar(&nodo) ;




                double Der=0;


                switch (NodoDer->tipo_) {
                case 1: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoDer->valor=="true"||NodoDer->valor=="verdadero"){
                    Der=1;
                    }
                    break;
                }
                case 4: {
                    Der=CChar(NodoDer->valor);
                    break;
                }
                case 5: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }

                }
 NodoTemp->tipo_=5;
                Der=Der+1;
                NodoTemp->valor=QVariant(Der).toString();
                return NodoTemp;

            }
                 case 40:{

                NodoAST nodo =NodoTemp->hijos.at(0);
                NodoAST *NodoDer=Analizar(&nodo) ;




                double Der=0;


                switch (NodoDer->tipo_) {
                case 1: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }
                case 3: {
                    if(NodoDer->valor=="true"||NodoDer->valor=="verdadero"){
                    Der=1;
                    }
                    break;
                }
                case 4: {
                    Der=CChar(NodoDer->valor);
                    break;
                }
                case 5: {
                    Der=QVariant(NodoDer->valor).toDouble();
                    break;
                }

                }

                Der=Der-1;
                NodoTemp->tipo_=5;
                NodoTemp->valor=QVariant(Der).toString();
                return NodoTemp;


            }
                 case 41:{

                NodoAST nodo0 =NodoTemp->hijos.at(0);
                NodoAST nodo1 =NodoTemp->hijos.at(1);


                NodoAST *Nodo0=Analizar(&nodo0) ;
int Ini=0;
                switch (Nodo0->tipo_) {
                case 1:{
                    Ini=Nodo0->valor.toInt();
                    break;
                }
                case 3:{
                    if(Nodo0->valor=="true"||Nodo0->valor=="verdadero"){
                    Ini=1;
                    }
                     break;
                }
                case 4:{
                    Ini=QVariant(CChar(Nodo0->valor)).toInt();
                    break;
                }
                case 5:{
                    Ini=Nodo0->valor.toInt();
                    break;
                }

                default:{
                    NodoTemp->tipo="Parametro incorrecto para REPETIR";
                    Errores->append(*NodoTemp);
                    break;
                }

                }


                for(int i=0;i<Ini;i++){
                NodoAST *Nodo1=Analizar(&nodo1) ;
                }

            return NodoTemp;
            }
            }
        }
        return NodoTemp;
    }

 NodoAST *Sintaxis::Suma(NodoAST *NodoTemp){


     NodoAST NodoIzq= NodoTemp->hijos.at(0);
     NodoAST NodoDer= NodoTemp->hijos.at(1);
     NodoIzq=*Analizar(&NodoIzq);
     NodoDer=*Analizar(&NodoDer);


     for(int i=0;i<Variables->count();i++){
         qDebug()<<Variables->at(i).Nombre<<"   "<<NodoDer.valor;

         if(Variables->at(i).Nombre==NodoDer.valor){
             NodoDer.valor=Variables->at(i).Lexema;
             NodoDer.tipo_=Variables->at(i).Tipo;
             break;
         }
     }

     for(int i=0;i<Variables->count();i++){


         if(Variables->at(i).Nombre==NodoIzq.valor){
             NodoIzq.valor=Variables->at(i).Lexema;
             NodoIzq.tipo_=Variables->at(i).Tipo;
             break;
         }
     }




     qDebug()<<NodoIzq.tipo_<<"  Suma  "<<NodoDer.tipo_;

     switch (NodoIzq.tipo_) {
     case 1:{


         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(NodoIzq.valor.toInt()+NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 15:{
             NodoDer.valor=NodoDer.valor.replace("\"","");
             NodoTemp->valor=QVariant(NodoIzq.valor+NodoDer.valor).toString(); NodoTemp->tipo="cadena";
             return NodoTemp;
         }
         case 3:{
             int Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(NodoIzq.valor.toInt()+Valor).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{             
             NodoTemp->valor=QVariant(NodoIzq.valor.toInt()+ CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()+NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Suma 1"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }
    }
     case 15:{
         NodoIzq.valor=NodoIzq.valor.replace("\"","");
         switch (NodoDer.tipo_) {
         case 1:{
            NodoDer.valor=NodoDer.valor.replace("\"","");
            NodoTemp->valor=QVariant(NodoIzq.valor+NodoDer.valor).toString(); NodoTemp->tipo="cadena";
            return NodoTemp;
         }
         case 15:{
             NodoDer.valor=NodoDer.valor.replace("\"","");
             NodoTemp->valor=QVariant(NodoIzq.valor+NodoDer.valor).toString(); NodoTemp->tipo="cadena";
             return NodoTemp;
         }

         case 4:{
             NodoDer.valor=NodoDer.valor.replace("\'","");
             NodoTemp->valor=QVariant(NodoIzq.valor+ NodoDer.valor).toString(); NodoTemp->tipo="cadena";
             return NodoTemp;
         }
         case 5:{
             NodoDer.valor=NodoDer.valor.replace("\"","");
              NodoTemp->valor=QVariant(NodoIzq.valor+NodoDer.valor).toString(); NodoTemp->tipo="cadena";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Suma 2"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }

     }
     case 3:{
        NodoIzq.valor=NodoIzq.valor.toLower();
         switch (NodoDer.tipo_) {
         case 1:{
             int Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }

            NodoTemp->valor=QVariant(Valor+NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 3:{
             bool Valor1=false;
             bool Valor2=false;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){ Valor2=true; }
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){ Valor1=true; }
            if(Valor1||Valor2){NodoTemp->valor="true";}else{NodoTemp->valor="false";}
            NodoTemp->tipo="booleano";
             return NodoTemp;
         }
         case 4:{
             int Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(Valor+ CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
             double Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }
              NodoTemp->valor=QVariant(Valor+NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }

         default:{
             qDebug()<<"Error En Suma 3"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 4:{
         int Charsi=CChar(NodoIzq.valor);

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(Charsi+NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 15:{
             NodoDer.valor=NodoDer.valor.replace("\"","");
             NodoTemp->valor=QVariant(NodoIzq.valor+NodoDer.valor).toString(); NodoTemp->tipo="cadena";
             return NodoTemp;
         }
         case 3:{
             int Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(Charsi+Valor).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(Charsi+ CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(((double)Charsi)+NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Suma 4"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 5:{

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()+NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
            return NodoTemp;
         }
         case 15:{
             NodoDer.valor=NodoDer.valor.replace("\"","");
             NodoTemp->valor=QVariant(NodoIzq.valor+NodoDer.valor).toString(); NodoTemp->tipo="cadena";
             return NodoTemp;
         }
         case 3:{
             double Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()+Valor).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()+ ((double)CChar(NodoDer.valor))).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()+NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Suma 5"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }



     }

     default:{
         qDebug()<<"Error En Suma final  "<<NodoIzq.tipo_<<"   "<<NodoDer.tipo_<<"\n";
        NodoIzq.tipo="malo";
        Errores->append(NodoIzq);
        return NodoTemp;

     }

     }


}
 NodoAST *Sintaxis::Division(NodoAST *NodoTemp){
     NodoAST NodoIzq= NodoTemp->hijos.at(0);
     NodoAST NodoDer= NodoTemp->hijos.at(1);
     NodoIzq=*Analizar(&NodoIzq);
     NodoDer=*Analizar(&NodoDer);




     for(int i=0;i<Variables->count();i++){
         qDebug()<<Variables->at(i).Nombre<<"   "<<NodoDer.valor;

         if(Variables->at(i).Nombre==NodoDer.valor){
             NodoDer.valor=Variables->at(i).Lexema;
             NodoDer.tipo_=Variables->at(i).Tipo;
             break;
         }
     }

     for(int i=0;i<Variables->count();i++){


         if(Variables->at(i).Nombre==NodoIzq.valor){
             NodoIzq.valor=Variables->at(i).Lexema;
             NodoIzq.tipo_=Variables->at(i).Tipo;
             break;
         }
     }




     qDebug()<<NodoIzq.tipo_<<"    "<<NodoDer.tipo_;
    if(NodoDer.valor=="0"||(NodoDer.valor=="false"||NodoDer.valor=="falso")){
        qDebug()<<" DIVISON POR 0";
    }else{

     switch (NodoIzq.tipo_) {
     case 1:{


         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(NodoIzq.valor.toInt()/NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }

         case 3:{
             int Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(NodoIzq.valor.toInt()/Valor).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(NodoIzq.valor.toInt()/ CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()/NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Division 1"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }
}
     case 3:{
        NodoIzq.valor=NodoIzq.valor.toLower();
         switch (NodoDer.tipo_) {
         case 1:{
             int Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }

            NodoTemp->valor=QVariant(Valor/NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }

         case 4:{
             int Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(Valor/ CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
             double Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }
              NodoTemp->valor=QVariant(Valor/NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }

         default:{
             qDebug()<<"Error En Division 3"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 4:{
         int Charsi=CChar(NodoIzq.valor);

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(Charsi/NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 3:{
             int Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(Charsi/Valor).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(Charsi/ CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(((double)Charsi)/NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Division 4"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 5:{

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()/NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
            return NodoTemp;
         }
         case 3:{
             double Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()/Valor).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()/ ((double)CChar(NodoDer.valor))).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()/NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Division 5"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }



     }

     default:{
         qDebug()<<"Error En Division final  "<<NodoIzq.tipo_<<"   "<<NodoDer.tipo_<<"\n";
        NodoIzq.tipo="malo";
        Errores->append(NodoIzq);
        return NodoTemp;

     }

     }
}

}

 NodoAST *Sintaxis::Potencia(NodoAST *NodoTemp){
     NodoAST NodoIzq= NodoTemp->hijos.at(0);
     NodoAST NodoDer= NodoTemp->hijos.at(1);
     NodoIzq=*Analizar(&NodoIzq);
     NodoDer=*Analizar(&NodoDer);


     for(int i=0;i<Variables->count();i++){
         qDebug()<<Variables->at(i).Nombre<<"   "<<NodoDer.valor;

         if(Variables->at(i).Nombre==NodoDer.valor){
             NodoDer.valor=Variables->at(i).Lexema;
             NodoDer.tipo_=Variables->at(i).Tipo;
             break;
         }
     }

     for(int i=0;i<Variables->count();i++){


         if(Variables->at(i).Nombre==NodoIzq.valor){
             NodoIzq.valor=Variables->at(i).Lexema;
             NodoIzq.tipo_=Variables->at(i).Tipo;
             break;
         }
     }




     qDebug()<<NodoIzq.tipo_<<"    "<<NodoDer.tipo_;

     switch (NodoIzq.tipo_) {
     case 1:{


         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(PO(NodoIzq.valor,NodoDer.valor)).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }

         case 3:{
             QString Valor="0";
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor="1";
             }
             NodoTemp->valor=QVariant(PO(NodoIzq.valor,Valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(PO(NodoIzq.valor, QVariant(CChar(NodoDer.valor)).toString())).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(PO(NodoIzq.valor,NodoDer.valor)).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Potencia 1"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }
}
     case 3:{
        NodoIzq.valor=NodoIzq.valor.toLower();
         switch (NodoDer.tipo_) {
         case 1:{
             QString Valor="0";
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor="1";
             }

            NodoTemp->valor=QVariant(PO(Valor,NodoDer.valor)).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 3:{
             QString Valor1="0";
             QString Valor2="0";
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){ Valor2="1"; }
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){ Valor1="1"; }

            NodoTemp->valor=QVariant(PO(Valor1,Valor2)).toString(); NodoTemp->tipo="entero";
            NodoTemp->tipo="booleano";
             return NodoTemp;
         }
         case 4:{
             QString Valor="0";
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor="1";
             }
             NodoTemp->valor=QVariant(PO(Valor,QVariant(CChar(NodoDer.valor)).toString())).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
             QString Valor="0";
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor="1";
             }
              NodoTemp->valor=QVariant(PO(Valor,NodoDer.valor)).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }

         default:{
             qDebug()<<"Error En Potencia 3"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 4:{
         int Charsi=CChar(NodoIzq.valor);

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(PO(QVariant(Charsi).toString(),NodoDer.valor)).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 3:{
             QString Valor="0";
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor="1";
             }
             NodoTemp->valor=QVariant(PO(QVariant(Charsi).toString(),Valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(PO(QVariant(Charsi).toString(),NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(PO(QVariant(Charsi).toString(),NodoDer.valor)).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Potencia 4"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 5:{

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(PO(NodoIzq.valor,NodoDer.valor)).toString(); NodoTemp->tipo="decimal";
            return NodoTemp;
         }
         case 3:{
             QString Valor="0";
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor="1";
             }
             NodoTemp->valor=QVariant(PO(NodoIzq.valor,Valor)).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(PO(NodoIzq.valor,QVariant(CChar(NodoDer.valor)).toString())).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(PO(NodoIzq.valor,NodoDer.valor)).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En  Potencia  5"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }



     }

     default:{
         qDebug()<<"Error En Potencia final  "<<NodoIzq.tipo_<<"   "<<NodoDer.tipo_<<"\n";
        NodoIzq.tipo="malo";
        Errores->append(NodoIzq);
        return NodoTemp;

     }

     }


}


 NodoAST *Sintaxis::Multiplicacion(NodoAST *NodoTemp){
     NodoAST NodoIzq= NodoTemp->hijos.at(0);
     NodoAST NodoDer= NodoTemp->hijos.at(1);
     NodoIzq=*Analizar(&NodoIzq);
     NodoDer=*Analizar(&NodoDer);


     for(int i=0;i<Variables->count();i++){
         qDebug()<<Variables->at(i).Nombre<<"   "<<NodoDer.valor;

         if(Variables->at(i).Nombre==NodoDer.valor){
             NodoDer.valor=Variables->at(i).Lexema;
             NodoDer.tipo_=Variables->at(i).Tipo;
             break;
         }
     }

     for(int i=0;i<Variables->count();i++){


         if(Variables->at(i).Nombre==NodoIzq.valor){
             NodoIzq.valor=Variables->at(i).Lexema;
             NodoIzq.tipo_=Variables->at(i).Tipo;
             break;
         }
     }







     qDebug()<<NodoIzq.tipo_<<"    "<<NodoDer.tipo_;

     switch (NodoIzq.tipo_) {
     case 1:{


         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(NodoIzq.valor.toInt()*NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }

         case 3:{
             int Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(NodoIzq.valor.toInt()*Valor).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(NodoIzq.valor.toInt()* CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()*NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Multiplicacion 1"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }
}
     case 3:{
        NodoIzq.valor=NodoIzq.valor.toLower();
         switch (NodoDer.tipo_) {
         case 1:{
             int Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }

            NodoTemp->valor=QVariant(Valor*NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 3:{
             bool Valor1=false;
             bool Valor2=false;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){ Valor2=true; }
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){ Valor1=true; }
            if(Valor1&&Valor2){NodoTemp->valor="true";}else{NodoTemp->valor="false";}
            NodoTemp->tipo="booleano";
             return NodoTemp;
         }
         case 4:{
             int Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(Valor* CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
             double Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }
              NodoTemp->valor=QVariant(Valor*NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }

         default:{
             qDebug()<<"Error En Multiplicacion 3"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 4:{
         int Charsi=CChar(NodoIzq.valor);

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(Charsi*NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 3:{
             int Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(Charsi*Valor).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(Charsi* CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(((double)Charsi)*NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Multiplicacion 4"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 5:{

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()*NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
            return NodoTemp;
         }
         case 3:{
             double Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()*Valor).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()* ((double)CChar(NodoDer.valor))).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()*NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Multiplicacion 5"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }



     }

     default:{
         qDebug()<<"Error En Multiplicacion final  "<<NodoIzq.tipo_<<"   "<<NodoDer.tipo_<<"\n";
        NodoIzq.tipo="malo";
        Errores->append(NodoIzq);
        return NodoTemp;

     }

     }


}
double Sintaxis::PO(QString In,QString Fin){
    double N= In.toDouble();
    double N1= Fin.toDouble();

    double Sal=pow(N,N1);

    return Sal;
}
 NodoAST *Sintaxis::Resta(NodoAST *NodoTemp){


     NodoAST NodoIzq= NodoTemp->hijos.at(0);
     NodoAST NodoDer= NodoTemp->hijos.at(1);
     NodoIzq=*Analizar(&NodoIzq);
     NodoDer=*Analizar(&NodoDer);

     for(int i=0;i<Variables->count();i++){
         qDebug()<<Variables->at(i).Nombre<<"   "<<NodoDer.valor;

         if(Variables->at(i).Nombre==NodoDer.valor){
             NodoDer.valor=Variables->at(i).Lexema;
             NodoDer.tipo_=Variables->at(i).Tipo;
             break;
         }
     }

     for(int i=0;i<Variables->count();i++){


         if(Variables->at(i).Nombre==NodoIzq.valor){
             NodoIzq.valor=Variables->at(i).Lexema;
             NodoIzq.tipo_=Variables->at(i).Tipo;
             break;
         }
     }





     qDebug()<<NodoIzq.tipo_<<"    "<<NodoDer.tipo_;

     switch (NodoIzq.tipo_) {
     case 1:{


         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(NodoIzq.valor.toInt()-NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }

         case 3:{
             int Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(NodoIzq.valor.toInt()-Valor).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(NodoIzq.valor.toInt()- CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()-NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Resta 1"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }
}

     case 3:{
    NodoIzq.valor=NodoIzq.valor.toLower();
         switch (NodoDer.tipo_) {
         case 1:{
             int Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }

            NodoTemp->valor=QVariant(Valor-NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }
         case 5:{
             double Valor=0;
             if(NodoIzq.valor=="true"||NodoIzq.valor=="verdadero"){
                Valor=1;
             }
              NodoTemp->valor=QVariant(Valor-NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }

         default:{
             qDebug()<<"Error En Resta 3"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 4:{
         int Charsi=CChar(NodoIzq.valor);

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(Charsi-NodoDer.valor.toInt()).toString(); NodoTemp->tipo="entero";
            return NodoTemp;
         }               
         case 4:{
             NodoTemp->valor=QVariant(Charsi- CChar(NodoDer.valor)).toString(); NodoTemp->tipo="entero";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(((double)Charsi)-NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Resta 4"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }


     }
     case 5:{

         switch (NodoDer.tipo_) {
         case 1:{
            NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()-NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
            return NodoTemp;
         }        
         case 3:{
             double Valor=0;
             NodoDer.valor=NodoDer.valor.toLower();
             if(NodoDer.valor=="true"||NodoDer.valor=="verdadero"){
                Valor=1;
             }
             NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()-Valor).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 4:{
             NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()- ((double)CChar(NodoDer.valor))).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         case 5:{
              NodoTemp->valor=QVariant(NodoIzq.valor.toDouble()-NodoDer.valor.toDouble()).toString(); NodoTemp->tipo="decimal";
             return NodoTemp;
         }
         default:{
             qDebug()<<"Error En Resta 5"<<"\n";
            NodoDer.tipo="malo";
            Errores->append(NodoDer);
            return NodoTemp;
         }
     }



     }

     default:{
         qDebug()<<"Error En Resta final  "<<NodoIzq.tipo_<<"   "<<NodoDer.tipo_<<"\n";
        NodoIzq.tipo="malo";
        Errores->append(NodoIzq);
        return NodoTemp;

     }

     }


}

 int Sintaxis::CChar(QString Entrada){
    Entrada=Entrada.replace("\'","");
    const char* ch = Entrada.toStdString().c_str();
    char Cha=ch[0];

    return (int)Cha;
 }
