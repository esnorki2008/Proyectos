#ifndef CAJAS_H
#define CAJAS_H

#include "ClienteAB.h"
#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <fstream>

using namespace std;

class Cajas
{
    private:
        int numeroRandom(int,int);
		int cantidad;
		void setCantidad();
		void setCantidadM();
    public:
        ClienteAB* cabeza;
		ClienteAB* cola;
		int GetBkMode();
		int getCantidad();
		int GetBinaryTypeA();
		void agregarElemento(string);
		void estavacia(string);
		void ingresaraatento(string);
		string impresionnes();
		void llenarCaja(string, string, string);
		string imprimirLista();
		void eliminarElemento(string);
		void droplista();
		bool verificarCaja(string);
		void agregarElemento(string,int);
		Cajas();
        virtual ~Cajas();
};


#endif // CAJAS_H
