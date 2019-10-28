#ifndef NODO_H
#define NODO_H

#include <string>
using namespace std;


class Nodo
{
    private:
        string valor;
		Nodo* siguiente;

    public:
        string carreta;
        string cliente;

        Nodo();
		Nodo(string);
		Nodo(string, Nodo*);

		void setValor(string);
		void setSiguiente(Nodo*);
		string getValor();
		Nodo* getSiguiente();
        virtual ~Nodo();


};

#endif // NODO_H
