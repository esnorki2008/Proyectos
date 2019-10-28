#include "Cliente.h"
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>
using namespace std;

Cliente::Cliente(string ve){
	valor = ve;
	siguiente = NULL;
}

Cliente::Cliente(string ve, Cliente* vid){
	valor = ve;
	siguiente = vid;
}

Cliente::Cliente(){
	siguiente = NULL;
}

void Cliente::setValor(string ve){
	valor = ve;
}

void Cliente::setSiguiente(Cliente* ve){
	siguiente = ve;
}

string Cliente::getValor(){
	return valor;
}

Cliente* Cliente::getSiguiente(){
	return siguiente;
}

Cliente::~Cliente()
{
    //dtor
}
