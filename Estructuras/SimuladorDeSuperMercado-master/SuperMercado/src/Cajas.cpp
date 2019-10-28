#include "Cajas.h"
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>

using namespace std;

Cajas::Cajas()
{
    cabeza = NULL;
	cola = NULL;
	cantidad = 0;
}
Cajas::~Cajas()
{
}
int Cajas::numeroRandom(int i, int j){

	int num = i + rand() % (j - i);

	return num;

}

bool Cajas::verificarCaja(string venta)
{
    ClienteAB* aux = cabeza;
    for(int i = 0; i <= cantidad; i++){
        if(aux->getValor() == venta){
                return aux->estado;
        }else{
            aux = aux->getSiguiente();
        }
    }
}

void Cajas::setCantidadM(){
	cantidad--;
}

void Cajas::agregarElemento(string gug){


    if(cantidad == 0){
        cabeza = NULL;
    }

	if(cabeza == NULL){
		cabeza = new ClienteAB(gug);
		cabeza->estado = false;
		cabeza->carreta = "";
		cabeza->cliente = "Disponible";
		cabeza->turnos =  numeroRandom(10,15);
		cabeza->contTurnos = cabeza->turnos;
		cola = cabeza;
		setCantidad();
	}else{
		ClienteAB* aux = cola;
		cola = new ClienteAB(gug,aux,NULL);
		cola->estado = false;
		cola->carreta = "";
		cola->cliente = "Disponible";
		cola->turnos = numeroRandom(10,15);
		cola->contTurnos = cola->turnos;
		aux->setSiguiente(cola);
		setCantidad();
	}
}

void Cajas::setCantidad(){
	cantidad++;
}

void Cajas::agregarElemento(string gug, int prod){


    if(cantidad == 0){
        cabeza = NULL;
    }

	if(cabeza == NULL){
		cabeza = new ClienteAB(gug);
		cabeza->estado = false;
		cabeza->carreta = "";
		cabeza->cliente = "Disponible";
		cabeza->turnos =  prod;
		cabeza->contTurnos = prod;
		cola = cabeza;
		setCantidad();
	}else{
		ClienteAB* aux = cola;
		cola = new ClienteAB(gug,aux,NULL);
		cola->estado = false;
		cola->carreta = "";
		cola->cliente = "Disponible";
		cola->turnos = prod;
		cola->contTurnos = prod;
		aux->setSiguiente(cola);
		setCantidad();
	}
}

int Cajas::getCantidad(){
	return cantidad;
}

void Cajas::llenarCaja(string venta, string persona, string coche )
{
    ofstream archivo;
	archivo.open("historial.txt",ios::app);

    ClienteAB* caja = cabeza;
    for(int i = 0; i <= cantidad; i++){
        if(caja->getValor() == venta){
            caja->cliente = persona;
            caja->carreta = coche;
            caja->estado = true;
//BORRE DE COUT Y ARCHIVO EL CLIENTE QUE IVA ENTRE COMILLAS
            cout<<"El  "<< caja->cliente<<" debe pagar  "<<caja->getValor()<<" con "<<caja->carreta<<endl<<endl;
            archivo<<endl<<"EL  "<< caja->cliente<<" debe pagar  "<<caja->getValor()<<" con "<<caja->carreta<<endl<<endl;
            break;
        }else{
            caja = caja->getSiguiente();
        }
    }
}


void Cajas::eliminarElemento(string variabil){
	ClienteAB* aux = cabeza;
	ClienteAB* ant = NULL;


	if(aux != NULL){
		if(aux->getValor() == variabil){
                if(aux->getSiguiente() == NULL){
                    cabeza = NULL;
                    setCantidadM();
                }else{
                    cabeza = cabeza->getSiguiente();
                    cabeza->setAnterior(NULL);
                    setCantidadM();
                }
		}else{
			while(aux != NULL){
				ant = aux;
				aux = aux->getSiguiente();

				if(aux->getValor() == variabil){
				    if(aux == cola){
                        ant->setSiguiente(NULL);
                        aux->setAnterior(NULL);
                        cola = ant;
                        setCantidadM();
                        break;
				    }else{
                        ant->setSiguiente(aux->getSiguiente());
                        aux->getSiguiente()->setAnterior(ant);
                        setCantidadM();
                        break;
				    }
				}
			}
		}
	}

}

string Cajas::imprimirLista(){
	string caden = "";
	ClienteAB* aux = cabeza;
	ClienteAB* auxC = cola;

	if(aux == NULL){
		return "VACIO";
	}

	if(cantidad == 0){
        return "VACIA";
	}

	if(cabeza == cola){
		return "\""+cabeza->getValor()+"\"";
	}

	while(aux != NULL){
		if(aux->getSiguiente() != NULL){

            std::stringstream sstm;
            sstm << aux->turnos  << " Tiempo o producto";
            string producto= sstm.str();
			caden += "	\"" +aux->getValor()+"\\n - \\n "+ aux->cliente +" \\n "+aux->carreta +" \\n " + "60  " +producto+" \"-> ";
		}else{
		    std::stringstream sstm;
            sstm << aux->turnos  << " Tiempo o producto";
            string producto= sstm.str();
		    caden += "	\"" +aux->getValor()+"\\n - \\n "+ aux->cliente +" \\n "+aux->carreta +" \\n " + "60  " +producto+" \" ";
		}
		aux = aux->getSiguiente();
	}

	while(auxC != NULL){
		if(auxC->getAnterior() != NULL){

            std::stringstream sstm;
            sstm << auxC->turnos  << " Tiempo o producto";
            string producto= sstm.str();
			caden += "	\"" +auxC->getValor()+"\\n - \\n "+ auxC->cliente +" \\n "+auxC->carreta +" \\n " + "60  " +producto+" \"-> ";
		}else{
		    std::stringstream sstm;
            sstm << auxC->turnos  << " Tiempo o producto";
            string producto= sstm.str();
		    caden += "	\"" +auxC->getValor()+"\\n - \\n "+ auxC->cliente +" \\n "+auxC->carreta +" \\n " + "60  "+producto +" \" ";
		}
		auxC = auxC->getAnterior();
	}
	return caden;
}
