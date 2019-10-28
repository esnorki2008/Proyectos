#include "NodoDoble.h"
#include "NodoDoble.h"

using namespace std;

NodoDoble::NodoDoble(string n){
	valor = n;
	siguiente = NULL;
	anterior = NULL;
}


NodoDoble::NodoDoble(string n, NodoDoble* nod, NodoDoble* nod2){
	valor = n;
	anterior = nod;
	siguiente = nod2;
}

NodoDoble::NodoDoble(){
	siguiente = NULL;
	anterior = NULL;
}

void NodoDoble::setValor(string n){
	valor = n;
}

void NodoDoble::setSiguiente(NodoDoble* n){
	siguiente = n;
}

void NodoDoble::setAnterior(NodoDoble* n){
	anterior = n;
}

string NodoDoble::getValor(){
	return valor;
}

NodoDoble* NodoDoble::getSiguiente(){
	return siguiente;
}

NodoDoble* NodoDoble::getAnterior(){
	return anterior;
}


NodoDoble::~NodoDoble()
{
    //dtor
}
