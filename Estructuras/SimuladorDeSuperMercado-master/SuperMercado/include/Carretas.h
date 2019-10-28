#ifndef CARRETAS_H
#define CARRETAS_H

#include "Cliente.h"
#include <iostream>
#include <string>
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <fstream>

using namespace std;

class Carretas
{
    private:
		Cliente* primero;
		Cliente* _final;

    public:
        Carretas();
        void insertaar(string);
        int contador;
        string impresionnes(string);
        void ingresar(string);
        void punnterros(string);
		Cliente* sacar(string);
		int sumando;
		string imprimir();
        virtual ~Carretas();

};

#endif // CARRETAS_H
