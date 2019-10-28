#include "ClienteAB.h"
#include <iostream>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>

using namespace std;

ClienteAB::ClienteAB(string ve){
	valor = ve;
	siguiente = NULL;
	anterior = NULL;
}


ClienteAB::ClienteAB(string ve, ClienteAB* vid, ClienteAB* vod){
	valor = ve;
	anterior = vid;
	siguiente = vod;
}

ClienteAB::ClienteAB(){
	siguiente = NULL;
	anterior = NULL;
}

void ClienteAB::setValor(string ve){
	valor = ve;
}

void ClienteAB::setSiguiente(ClienteAB* ve){
	siguiente = ve;
}

void ClienteAB::setAnterior(ClienteAB* ve){
	anterior = ve;
}

string ClienteAB::getValor(){
	return valor;
}

ClienteAB* ClienteAB::getSiguiente(){
	return siguiente;
}

ClienteAB* ClienteAB::getAnterior(){
	return anterior;
}


ClienteAB::~ClienteAB()
{
    //dtor
}
