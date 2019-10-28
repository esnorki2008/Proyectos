#include "Nodo.h"

using namespace std;

Nodo::Nodo(string n){
	valor = n;
	siguiente = NULL;
}

Nodo::Nodo(string n, Nodo* nod){
	valor = n;
	siguiente = nod;
}

Nodo::Nodo(){
	siguiente = NULL;
}

void Nodo::setValor(string n){
	valor = n;
}

void Nodo::setSiguiente(Nodo* n){
	siguiente = n;
}

string Nodo::getValor(){
	return valor;
}

Nodo* Nodo::getSiguiente(){
	return siguiente;
}

Nodo::~Nodo()
{
    //dtor
}
