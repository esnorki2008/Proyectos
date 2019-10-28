#ifndef NODODOBLE_H
#define NODODOBLE_H

#include <string>


using namespace std;

class NodoDoble
{
    private:
		string valor;
		NodoDoble* siguiente;
		NodoDoble* anterior;

    public:
        int turnos;
        int contTurnos;
        bool estado;
        string cliente;
        string carreta;


        NodoDoble();
		NodoDoble(string);
		NodoDoble(string, NodoDoble*, NodoDoble*);

		void setValor(string);
		void setSiguiente(NodoDoble*);
		void setAnterior(NodoDoble*);
		string getValor();
		NodoDoble* getSiguiente();
		NodoDoble* getAnterior();

        virtual ~NodoDoble();
};



#endif // NODODOBLE_H
