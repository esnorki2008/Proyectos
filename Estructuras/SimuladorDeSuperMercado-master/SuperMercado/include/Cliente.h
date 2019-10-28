#ifndef CLIENTE_H
#define CLIENTE_H

#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <fstream>

using namespace std;


class Cliente
{
    private:
        Cliente* siguiente;
        string valor;
        int contadorsito;

    public:

        Cliente();
		Cliente(string);
		Cliente(string, Cliente*);
        string carreta;
		void setValor(string);
		void setSiguiente(Cliente*);
		string getValor();
		Cliente* getSiguiente();
		string cliente;
        virtual ~Cliente();


};

#endif // CLIENTE_H
