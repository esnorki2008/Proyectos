#ifndef PAGO_H
#define PAGO_H

#include "Cliente.h"
#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <fstream>

using namespace std;

class Pago
{
    private:
		Cliente* primero;
		Cliente* final;
        Cliente* Idass;
		Cliente* Vuelltas;

    public:
        Pago(string);
        int getContadorsito();
        string nombre;
        int contador;
        void ingresar(string,string);
		Cliente* sacar();
        string imprimir();
        Cliente* dropnoodo(string);
		string impresionnes();
        virtual ~Pago();

};

#endif // PAGO_H
