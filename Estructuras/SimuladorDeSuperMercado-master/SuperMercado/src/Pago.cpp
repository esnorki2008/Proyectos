#include "Pago.h"
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>

using namespace std;

Pago::Pago(string n){
	primero = NULL;
	final = NULL;
	contador = 0;
	nombre = n;
}
Cliente* Pago::sacar(){
	Cliente* aux = primero;
	ofstream archivo;
	archivo.open("historial.txt",ios::app);

	if(aux != NULL){
		primero = aux->getSiguiente();
		archivo<<"| Cola "<<nombre<<" | ->El  "<<aux->getValor()<<" salio de la cola "<<endl;
		if(contador > 0){
			contador--;
		}
	}else{
		archivo<<"| Cola "<<nombre<<" -> La cola se encuentra vacia"<<endl;
	}

	return aux;
}

void Pago::ingresar(string variabler, string carre){
    ofstream archivo;
	archivo.open("historial.txt",ios::app);

	if(primero == NULL){
		contador++;
		primero = new Cliente(variabler);
		primero->cliente = variabler;
		primero->carreta = carre;
		final = primero;
		archivo<<"| Cola "<<nombre<<" | ->El  "<<variabler<<" acaba de ingresar a la cola"<<endl;
	}else{
		contador++;
		Cliente* aux = final;
		Cliente* nuevo = new Cliente(variabler);
		nuevo->cliente = variabler;
		nuevo->carreta = carre;
		final = nuevo;
		aux->setSiguiente(final);
		archivo<<"| Cola "<<nombre<<" | ->El  "<<variabler<<" acaba de ingresar a la cola"<<endl;
	}
	archivo.close();
}

string Pago::imprimir(){
	Cliente* aux = primero;
	string cadenpag = "";

	while(aux != NULL){
		if(aux->getSiguiente() != NULL){
			cadenpag += 	" " + aux->getValor() + "|";
		}else{
			cadenpag += 	" " + aux->getValor() + "\n";
		}
		aux = aux->getSiguiente();
	}
	if(contador == 0){
		cadenpag = "VACIA";
	}
	return cadenpag;
}


Pago::~Pago()
{
    //dtor
}
