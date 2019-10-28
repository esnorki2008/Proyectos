#include "Compras.h"
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>

using namespace std;

Compras::Compras()
{
    cabeza = NULL;
	cola = NULL;
	cantidad = 0;
}

bool Compras::buscarElemento(string sata){
    ClienteAB* aux = cabeza;

    if(aux == NULL || cantidad == 0){
        return false;
    }else{
        while(aux != cola){
            if(aux->getValor() == sata){
                return true;
            }
            aux = aux->getSiguiente();
        }
        if(aux == cola && cola->getValor() == sata){
            return true;
        }else if(aux == cola && cola->getValor() != sata){
            return false;
        }
}
return false;
}

void Compras::setCantidad()
{
    cantidad++;
}
int Compras::getCantidad()
{
    return cantidad;
}

void Compras::agregarElemento(string ve,string cadif)
{
    ofstream archivo;
	archivo.open("historial.txt",ios::app);

    if(cantidad == 0){
        cabeza = NULL;
    }

    if(cabeza == NULL){
		cabeza = new ClienteAB(ve);
		cabeza->carreta = cadif;
		cabeza->setValor(ve);
		archivo<<"El "<<ve<<" esta de compras"<<endl<<endl;
		cola = cabeza;
		setCantidad();
	}else{
		ClienteAB* aux = cola;
		cola = new ClienteAB(ve,aux,cabeza);
		archivo<<"El "<<ve<<" esta de compras"<<endl<<endl;
		cola->carreta = cadif;
		cola->setValor(ve);
		aux->setSiguiente(cola);
		cabeza->setAnterior(cola);
		setCantidad();
	}
}

string Compras::imprimirLista()
{
    ClienteAB* aux = cabeza;
    ClienteAB* aux2 = cabeza;
    string cadenco = "";
    int compran = cantidad +1;
    int comprani = cantidad +1;

    if(aux == NULL){
		return "VACIO";
	}

	if(cantidad == 0){
        return "VACIA";
	}

	if(cabeza == cola){
		return "\""+cabeza->getValor()+"\"";
	}

    while(compran>0){
            if(compran == 1){
                cadenco += "	\"" +aux->getValor()+" - " + aux->carreta+"\" \n ";
            }else{
                cadenco += "	\"" +aux->getValor()+" - " + aux->carreta+"\"-> ";
            }
        aux = aux->getSiguiente();
        compran--;
    }

    while(comprani>0){
            if(comprani == 1){
                cadenco += "	\"" +aux2->getValor()+" - " + aux2->carreta+"\" \n ";
            }else{
                cadenco += "	\"" +aux2->getValor()+" - " + aux2->carreta+"\"-> ";
            }
        aux2 = aux2->getAnterior();
        comprani--;
    }

	return cadenco;
}

ClienteAB* Compras::getCabeza()
{
    return cabeza;
}
void Compras::setCantidadM()
{
    cantidad--;
}

ClienteAB* Compras::eliminarElemento(string sata)
{
    ofstream archivo;
	archivo.open("historial.txt",ios::app);

    ClienteAB* aux = cabeza;
	ClienteAB* ant = NULL;

	if(aux != NULL){
		if(aux->getValor() == sata){
                if(aux->getSiguiente() == NULL){
                    cabeza = NULL;
                    cantidad = 0;
                    archivo<<"El"<<sata<<" salio de compras"<<endl<<endl;
                    return aux;
                }else{
                    cabeza = cabeza->getSiguiente();
                    cabeza->setAnterior(cola);
                    cola->setSiguiente(cabeza);
                    setCantidadM();
                    archivo<<"El "<<sata<<" salio de compras "<<endl<<endl;
                    return aux;
                }
		}else{
			while(aux != NULL){
				ant = aux;
				aux = aux->getSiguiente();

				if(aux->getValor() == sata){
				    if(aux == cola){
                        cola = cola->getAnterior();
                        cola->setSiguiente(cabeza);
                        cabeza->setAnterior(cola);
                        setCantidadM();
                        archivo<<"El "<<sata<<" salio de compras"<<endl<<endl;
                        return aux;
                        break;
				    }else{
				        ClienteAB* r = aux;
                        ant->setSiguiente(aux->getSiguiente());
                        aux->getSiguiente()->setAnterior(ant);
                        setCantidadM();
                        archivo<<"El "<<sata<<" salio de compras"<<endl<<endl;
                        return r;
                        break;
				    }

				}
			}
		}
	}
}


Compras::~Compras()
{
    //dtor
}
