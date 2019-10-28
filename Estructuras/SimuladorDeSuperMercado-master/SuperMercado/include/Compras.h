#ifndef COMPRAS_H
#define COMPRAS_H

#include "ClienteAB.h"
#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <fstream>

using namespace std;

class Compras
{
    private:
		ClienteAB* cabeza;
		ClienteAB* iniciado;
		ClienteAB* cola;
		ClienteAB* finalizado;
		void setCantidad();
		void setContadorsito();
		int cantidad;
		int contadorsito;
		void setContadorsitoP();
		void setCantidadM();

    public:
        Compras();
        void agregarElemento(string,string);
		string imprimirLista();
		void insertarnodoo(string,string);
		ClienteAB* eliminarElemento(string);
		ClienteAB* dropnoodo(string);
		int getCantidad();
		bool buscarElemento(string);
		int getContadorsito();
		string impresionnes();
		ClienteAB* getCabeza();
        virtual ~Compras();

};

#endif // COMPRAS_H
