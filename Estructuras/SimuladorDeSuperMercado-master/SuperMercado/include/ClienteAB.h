#ifndef CLIENTEAB_H
#define CLIENTEAB_H

#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <fstream>

using namespace std;

class ClienteAB
{
    private:
		string valor;
		ClienteAB* siguiente;
		ClienteAB* anterior;

    public:
        ClienteAB();
		ClienteAB(string);
		ClienteAB(string, ClienteAB*, ClienteAB*);
        int turnos;
        string carreta;
        int contTurnos;
        void setValor(string);
        bool estado;
        string cliente;
		void setSiguiente(ClienteAB*);
		ClienteAB* getSiguiente();
		ClienteAB* getAnterior();
		void setAnterior(ClienteAB*);
		string getValor();
        virtual ~ClienteAB();
};



#endif // CLIENTEAB_H
