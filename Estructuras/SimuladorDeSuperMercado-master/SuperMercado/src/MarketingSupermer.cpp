#include "MarketingSupermer.h"
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>


MarketingSupermer::MarketingSupermer(){
	/**primero = NULL;
	final = NULL;
	contador = 0;
	nombre = n;
	*/
}
/**
void MarketingSupermer::ingresar(string _valor, string car){
    ofstream archivo;
	archivo.open("historial.txt",ios::app);

	if(primero == NULL){
		contador++;
		primero = new Cliente(_valor);
		primero->cliente = _valor;
		primero->carreta = car;
		final = primero;
		archivo<<"| Cola "<<nombre<<" | ->El  "<<_valor<<" acaba de ingresar a la cola"<<endl;
	}else{
		contador++;
		Cliente* aux = final;
		Cliente* nuevo = new Cliente(_valor);
		nuevo->cliente = _valor;
		nuevo->carreta = car;
		final = nuevo;
		aux->setSiguiente(final);
		archivo<<"| Cola "<<nombre<<" | ->El  "<<_valor<<" acaba de ingresar a la cola"<<endl;
	}
	archivo.close();
}


string MarketingSupermer::imprimir(){
	Cliente* aux = primero;
	string txt = "";

	while(aux != NULL){
		if(aux->getSiguiente() != NULL){
			txt += 	" " + aux->getValor() + "|";
		}else{
			txt += 	" " + aux->getValor() + "\n";
		}

		aux = aux->getSiguiente();
	}

	if(contador == 0){
		txt = "VACIA";
	}
	return txt;
}
*/
MarketingSupermer::~MarketingSupermer()
{
    //dtor
}
