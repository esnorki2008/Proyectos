#include "Carretas.h"
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>

using namespace std;

Carretas::Carretas(){
	primero = NULL;
	_final = NULL;
	contador = 0;
}

void Carretas::ingresar(string variaba){

	if(primero == NULL && contador == 0){
		contador++;
		primero = new Cliente(variaba);
		_final = primero;
	}else{
		contador++;
		Cliente* aux = primero;
		primero = new Cliente (variaba);
		primero->setSiguiente(aux);
	}
}

Cliente* Carretas::sacar(string sax){
	Cliente* aux = primero;
	ofstream archivo;
	archivo.open("historial.cadencar",ios::app);
	if(aux != NULL){
		primero = aux->getSiguiente();
        archivo<<"|  "<<aux->getValor()<<"| -> esta en uso "<<sax<<endl;
        if(contador > 0){
			contador--;
		}
	}else{
		archivo<<"| Fila de coches | -> esta vacia"<<endl;
		contador = 0;
	}
	return aux;
}

string Carretas::imprimir(){
	Cliente* aux = primero;
	string cadencar = "";
	ofstream archivo;
	archivo.open("historial.txt",ios::app);
	while(aux != NULL ){
            std::cout<<endl<<"-------------- Valor"<<aux->getValor()<<"---------"<<endl<<endl;
            archivo<<endl<<"--------------  Valor "<<aux->getValor()<<"---------"<<endl<<endl;
		if(aux->getSiguiente() != NULL){
			cadencar += 	" " + aux->getValor() + "|";
		}else{
			cadencar += 	" " + aux->getValor() + "\n";
		}
		aux = aux->getSiguiente();
	}
	if(contador == 0 && aux == NULL){
		cadencar = "VACIA";
	}
	return cadencar;
}


Carretas::~Carretas()
{
    //dtor
}
