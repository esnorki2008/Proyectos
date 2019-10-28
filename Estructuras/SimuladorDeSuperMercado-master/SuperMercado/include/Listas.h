#ifndef LISTAS_H
#define LISTAS_H

#include "Cliente.h"
#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <fstream>

using namespace std;

class Listas
{
    private:
		Cliente* cabeza;
		Cliente* inicializao;
		int cantidad;
		void setCantidad();
		int contadorsito;
		void setContadorsitoP();
		void setCantidadM();
		void setContadorsito();

    public:
        Listas();
        Cliente* cola;
        void insertarnodoo(string,string);
        string imprimirLista();
        void agregarElemento(string);
        int getContadorsito();
		string impresionnes();
		void eliminarElemento(string);
        Cliente* dropnoodo(string);
		int getCantidad();
        virtual ~Listas();
};

#endif // LISTAS_H
