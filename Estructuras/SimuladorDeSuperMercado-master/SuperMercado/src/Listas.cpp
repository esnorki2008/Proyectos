#include "Listas.h"
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>

using namespace std;
Listas::Listas(){
	cabeza = NULL;
	cola = NULL;
	cantidad = 0;
}
void Listas::agregarElemento(string fed){
	ofstream archivo;
	archivo.open("historial.txt",ios::app);

	if(cabeza == NULL){
		Cliente* nuevo = new Cliente(fed,cabeza);
		cabeza = nuevo;
		cola = cabeza;
		setCantidad();
		archivo<<"| Compras | -> El "<<nuevo->getValor()<<" se encuentra comprando"<<endl;
	}else{
		cantidad++;
		Cliente* aux = cola;
		Cliente* nuevo = new Cliente(fed,cabeza);
		archivo<<"| Compras | -> El "<<nuevo->getValor()<<" se encuentra comprando"<<endl;
		cola = nuevo;
		aux->setSiguiente(cola);
	}
}

void Listas::setCantidad(){
	cantidad++;
}

string Listas::imprimirLista(){
	string cadenlis = "";
	Cliente* aux = cabeza;

	if(cabeza == NULL){
		return "VACIA";
	}
	if(cantidad == 0){
        return "VACIA";
	}
	if(cabeza == cola){
		return "\""+cabeza->getValor()+"\"";
	}
    cadenlis += 	"	\"" + cabeza->getValor() + "\"-> ";
	aux = aux->getSiguiente();
	while(aux != cabeza){
		cadenlis += 	"	\"" + aux->getValor() + "\"-> ";
		aux=aux->getSiguiente();
	}
	cadenlis += 	"	\"" + aux->getValor() + "\"\n ";
	return cadenlis;
}

int Listas::getCantidad(){
	return cantidad;
}

void Listas::eliminarElemento(string pizad){
	Cliente* aux = cabeza;
	Cliente* ant = NULL;

	if(aux != NULL){
		if(aux->getValor() == pizad){
            if(aux->getSiguiente() == NULL){
                cabeza = NULL;
                cola =  NULL;
                cantidad = 0;
            }else{
                cola->setSiguiente(aux->getSiguiente());
                cabeza = aux->getSiguiente();
                setCantidadM();
            }
		}else{
			while(aux != NULL){
				ant = aux;
				aux = aux->getSiguiente();

				if(aux->getValor() == pizad){
					ant->setSiguiente(aux->getSiguiente());
					aux->setSiguiente(NULL);
					setCantidadM();
					break;
				}

			}
		}
	}
}
void Listas::setCantidadM(){
	cantidad--;
}

Listas::~Listas()
{
    //dtor
}
