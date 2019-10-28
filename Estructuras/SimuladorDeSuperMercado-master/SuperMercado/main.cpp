#include <iostream>
#include <windows.h>

#include "Cajas.h""
#include "Carretas.h"
#include "Cliente.h"
#include "ClienteAB.h"
#include "Compras.h"
#include "Listas.h"
#include "Pago.h"
#include <stdlib.h>
#include <sstream>
#include <time.h>
#include <string>
#include <fstream>

using namespace std;

int personas = 0;
int coches = 0;
int ventanas = 0;
static int CClientes = 0;

//OFSTREAM NOS SIRVE PARA PODER ABRIR UN FICHERO PARA LEER Y ESCRIBIR.
//IOSTREAM
//IFSTREAM SE COMPORTA COMO UN CIN
//OFSTREAM SE COMPORTA COMO UN COUT
ofstream historial;
	Cajas* filaventanas = new Cajas();
	Carretas* coches1 = new Carretas();
	Carretas* coches2 = new Carretas();
	Compras* colacompra = new Compras();
    Pago* cancelar = new Pago("Paga");
   	Pago* colacliente = new Pago("Espera");
	void insertarper(int cantidad);
	void grafoy(Pago* colacliente, Carretas* coches1, Carretas* coches2, Compras* listaCircular, Pago* cancelar, Cajas* fila);
	int x(int i, int j);
    void simulacion();
	void simulaciontiempo();




//------------------------------------------------------------------------------------------------------------------------------------------------------
int main(){
    srand(time(NULL));
	cout<<" MINIMARKET MANAGER "<<endl;
	historial.open("historial.txt",ios::out);
	cout<<"	Ingrese la cantidad de personas: ";
	cin>>personas;
	historial<<"->Se agregaron "<<personas<<" personas"<<endl;
	cout<<"	Ingrese la cantidad de coches: ";
	cin>>coches;
	historial<<"->Se agregaron "<<coches<<" coches"<<endl;
	cout<<"	Ingrese la cantidad de ventanas: ";
	cin>>ventanas;
	historial<<"->Se agregaron "<<ventanas<<" ventanas"<<endl;

	insertarper(personas);

	for(int i=0; i<coches; i++){
		std::stringstream sstm;
		sstm << "Coche: " << i + 1;
		string varia = sstm.str();
		int random = x(1,3);
		if(random == 1){
			historial<<"Coches1  Añadir  "<<varia<<" en la fila de disponibles"<<endl;
			coches1->ingresar(varia);
		}else{
			historial<<"Coches2  Añadir  "<<varia<<" en la fila de disponibles"<<endl;
			coches2->ingresar(varia);
		}
	}

	for(int i = 0; i < ventanas; i++){
		std::stringstream sstm;
		sstm << "Ventana " << i + 1;
		string varia = sstm.str();
		historial<<"Ventanilla abierta:  "<<varia<<endl;
		filaventanas->agregarElemento(varia,x(10,15));
	}
    historial.close();

//MENU DE PANTALLA DE INICIO

    cout<<endl;
	cout<<endl;
	cout<<endl;
	bool sul = true;
	while( sul != false){
		int eleccion;
		cout<<"	1 Simulacion tiempo"<<endl;
		cout<<"	2 Graphviz"<<endl;
		cout<<"	3 Historial"<<endl;
		cout<<"	4 Salir"<<endl;
		cout<<"	5 Simulacion turno"<<endl;
		cout<<"	Ingrese un numero: ";
		cin>>eleccion;
		cout<<endl;

		//MENU
		switch(eleccion){
			case 1:
				cout<<"SIMULACION DE MINIMARKET TIEMPO"<<endl;
				historial.open("historial.txt",ios::app);
				simulaciontiempo();
				sul = false;
				break;
			case 2:
			    grafoy(colacliente, coches1, coches2,colacompra,cancelar,filaventanas);
				system("grafo.png");
				break;
			case 3:
				historial.close();
				system("historial.txt");
				break;
			case 4:
				cout<<" Final "<<endl;
				sul = false;
				break;
            case 5:
				cout<<"SIMULACION DE MINIMARKET TURNO "<<endl;
				historial.open("historial.txt",ios::app);
				simulacion();
				sul = false;
				break;
			default:
			    eleccion = 0;
				cout<<"	1 Simulacion tiempo"<<endl;
				cout<<"	2 Graphviz"<<endl;
				cout<<"	3 Historial"<<endl;
				cout<<"	4 Salir"<<endl;
				cout<<"	5 Simulacion turno"<<endl;
				cout<<"	Ingresar un numero: ";
				cin>>eleccion;
				cout<<endl;
				break;
		}
	}
    return 0;
}

//-------------------------------------------------------------------------------------------------------------------------------------------------

void insertarper(int parametro){
    historial.close();
	for(int i = 1; i <= parametro; i++){
		std::stringstream sstm;
		sstm << "Persona " << ++CClientes;
		string injoi = sstm.str();
		colacliente->ingresar(injoi," No tiene coche");
	}
	historial.open("historial.txt",ios::app);
}

//-----------------------------------------------------------------------------------------------------------------------------------------------------



int x(int i, int j){
	int num = i + rand() % (j - i);
	return num;
}


//------------------------------------------------------------------------------------------------------------------------------
void simulacion(){

    historial<<" SIMULACION PROCESADA"<<endl;
    bool s = true;

    //COCHES DISPONIBLES
    int carritosDisponibles = coches1->contador + coches2->contador;
    cout<<"Hay "<<carritosDisponibles<<" Coches Disponibles"<<endl;
    historial<<endl<<"Hay "<<carritosDisponibles<<" Coches Disponibles"<<endl;

    //ASIGNACIÓN DE CARRITOS A LAS PERSONAS Y SU MOVIMIENTO A COMPRAS
    for(int i = 0; i < carritosDisponibles; i++){
        int carritos1 = coches1->contador;
        int carritos2 = coches2 ->contador;

        if(colacliente->contador > 0){
            if(carritos1 > 0 && carritos2 > 0){ // LAS DOS PILAS TIENEN CARRITOS

                int r = x(0,3);
                if(r == 1){ //PILA 1
                    Cliente* cliente = colacliente->sacar();
                    Cliente* carreta = coches1->sacar(cliente->getValor());
                    cout<<"El "<<cliente->getValor()<<" tiene un coche"<<endl;
                    colacompra->agregarElemento(cliente->getValor(),carreta->getValor());
                }else{//PILA 2
                    Cliente* cliente = colacliente->sacar();
                    Cliente* carreta = coches2->sacar(cliente->getValor());
                    cout<<"El "<<cliente->getValor()<<" tiene un coche"<<endl;
                    colacompra->agregarElemento(cliente->getValor(),carreta->getValor());
                }
            }else if(carritos1 > 0 && carritos2 <= 0){ //SE TOMAN CARRETAS DE LA PILA 1
                Cliente* cliente = colacliente->sacar();
                Cliente* carreta = coches1->sacar(cliente->getValor());
                cout<<"El "<<cliente->getValor()<<" tiene un coche"<<endl;
                colacompra->agregarElemento(cliente->getValor(),carreta->getValor());
            }else if(carritos1 <= 0 && carritos2 > 0) {//SE TOMAN CARRETAS DE LA PILA 2
                Cliente* cliente = colacliente->sacar();
                Cliente* carreta = coches2->sacar(cliente->getValor());
                cout<<"El "<<cliente->getValor()<<" tiene un coche"<<endl;
                colacompra->agregarElemento(cliente->getValor(),carreta->getValor());
            }
        }else{
            cout<<" No hay personas esperando "<<endl;
            historial<<" No hay personas esperando "<<endl;
            break;
        }
    }

    //NUMERO RANDOM PARA SACAR A UNA PERSONA DE COMPRAS
    int turnoActual = x(1,100);
    std::stringstream sstm;
    sstm << "Persona " << turnoActual;
    string yoda = sstm.str();
    cout<<endl<<endl<<"Informa que "<<yoda<<" pase a pagar"<<endl;
    historial<<endl<<endl<<"Informa que "<<yoda<<" pase a pagar"<<endl;

    bool find = colacompra->buscarElemento(yoda);
    if(find){

        cout<<"Encontrado "<<yoda<<endl<<endl;
        historial<<"Pase a compras"<<yoda<<endl<<endl;

        ClienteAB* sacarTurno = colacompra->eliminarElemento(yoda);
        cout<<"El "<<sacarTurno->getValor()<<" sale de compras"<<endl;
        historial<<"El "<<sacarTurno->getValor()<<" sale de compras"<<endl;
        cancelar->ingresar(sacarTurno->getValor(),sacarTurno->carreta);


    }else{
        cout<<endl<<endl<<"El  "<<yoda<<" no esta presente"<<endl<<endl;
        historial<<endl<<"El  "<<yoda<<" no esta presente"<<endl<<endl;
    }

    cout<<endl;

    //BUSCAR CAJAS VACIAS
    if(cancelar->contador > 0 ){
        for(int i = 1; i <= ventanas; i++){
            std::stringstream sst;
            sst << "Ventana "<<i;
            string caja= sst.str();

            if(filaventanas->verificarCaja(caja)){
                cout<<caja<<" Ocupada "<<endl;
                historial<<endl<<caja<<" Ocupada "<<endl;
                continue;
            }else{
                cout<<caja<<" Libre "<<endl;
                historial<<endl<<caja<<" Libre "<<endl;
                Cliente* sacarTurno = cancelar->sacar();
                filaventanas->llenarCaja(caja, sacarTurno->getValor(),sacarTurno->carreta);
                break;
            }
        }
    }

    //LISTA DE CAJAS VERIFICA SI EL ESTADO ESTA LIBRE O DESOCUPADA HACE VARIOS RECORRIDOS Y ASIGNA CARRITOS A LOS CLIENTES
    ClienteAB* aux = filaventanas->cabeza;
    for(int i = 1; i <= ventanas; i++){
        if(aux->turnos > 1 && aux->estado == true){
            aux->turnos--;
            cout<<"Tiempo que falta = "<<aux->turnos<<" , para  "<<aux->cliente<<endl<<endl;
            historial<<"Tiempo que falta = "<<aux->turnos<<" , para  "<<aux->cliente<<endl<<endl;
        }else if(aux->turnos == 1 ){
            string perso1 = aux->cliente;
            string cochecito = aux->carreta;
            int ran = x(1,3);
            if(ran == 1){
                coches1->ingresar(cochecito);
            }else{
                coches2->ingresar(cochecito);
            }
            aux->carreta = "";
            aux->cliente = "Disponible";
            aux->turnos = aux ->contTurnos;
            aux->estado = false;
            historial<<"El "<<perso1<<" sale de pagar y regresa el coche "<<cochecito<<endl<<endl;
        }
        aux = aux->getSiguiente();
    }

    bool bolo = true;
	while( bolo != false){
		int eleccion;
		cout<<endl<<endl;
		cout<<"	1 Simulacion"<<endl;
		cout<<"	2 Agregar personas"<<endl;
		cout<<"	3 Graphviz"<<endl;
		cout<<"	4 Historial"<<endl;
		cout<<"	5 Salir"<<endl;
		cout<<"	6 Simulacion tiempo"<<endl;
		cout<<"	Ingresar un numero: ";
		cin>>eleccion;
		cout<<endl;

		switch(eleccion){
			case 1:
				cout<<" SIMULACION DE MINIMARKET H"<<endl;
				historial.open("historial.txt",ios::app);
				simulacion();
				bolo = false;
				break;
            case 2:
                int nuevosClientes;
                cout<<"	¿Cuantos personas desea ingresar?: ";
                cin>>nuevosClientes;
                insertarper(nuevosClientes);
                break;
			case 3:
			    grafoy(colacliente, coches1, coches2,colacompra,cancelar,filaventanas);
			    system("grafo.png");
				break;
			case 4:
				historial.close();
				system("historial.txt");
				break;
			case 5:
				cout<<" Final"<<endl;
				bolo = false;
				break;
			case 6:
				cout<<" Simulacion tiempo"<<endl;
				historial.open("historial.txt",ios::app);
				simulaciontiempo();
				bolo = false;
				break;
			default:
			    eleccion = 0;
				cout<<"	1 Simulacion."<<endl;
                cout<<"	2 Graphviz"<<endl;
                cout<<"	3 Historial"<<endl;
                cout<<"	4 Salir"<<endl;
                cout<<"	5 Simulacion tiempo"<<endl;
                cout<<"	Ingresar un numero: ";
				cin>>eleccion;
				cout<<endl;
				break;
		}

	}

}

//METODO PARA GRAFICAR-------------------------------------------------------------------------------------------------------------------------------------

void grafoy(Pago* colacliente, Carretas* coches1, Carretas* coches2, Compras* listaCircular, Pago* cancelar, Cajas* fila){
	ofstream archivo;
	archivo.open("grafo.txt",ios::out);

	archivo<<"digraph fila{"<<endl;
	archivo<<"	"<<endl;

	archivo<<"	subgraph cluster_primero{"<<endl;
	archivo<<"		node_cli [margin=0 fontcolor=white fontsize=10 height=4 width=4 shape=tripleoctagon  color=mediumaquamarine] [label=\"{";
	archivo<<		colacliente->imprimir()<<endl;
	archivo<<"		}\" style=radial];"<<endl;
	archivo<<"		label = \"Espera de personas\";";
	archivo<<"	color=red;}";
//	archivo<<"	color=yellow;}";

	archivo<<endl;
	archivo<<endl;

	archivo<<"	subgraph cluster_segundo{"<<endl;
	archivo<<"		label = \"Coches de Compras\";";
	archivo<<"		subgraph cluster_segundpri{"<<endl;
	archivo<<"			node_coches [shape=record    label=\"{";
	archivo<<			coches1->imprimir()<<endl;
	archivo<<"			}\"];"<<endl;
	archivo<<"			label = \"Coches1\";";
	archivo<<"		}";
	//archivo<<"	color=yellow;}";

	archivo<<endl;
	archivo<<endl;

	archivo<<"		subgraph cluster_segunddos{"<<endl;
	archivo<<"			node_carret [shape=record    label=\"{";
	archivo<<			coches2->imprimir()<<endl;
	archivo<<"			}\"];"<<endl;
	archivo<<"			label = \"Coches2\";";
	archivo<<"		}";
	archivo<<"	color=green;}";
	archivo<<"	subgraph cluster_tercero{"<<endl;
	archivo<<		listaCircular->imprimirLista()<<endl;
	archivo<<"		label = \"Minimarket compras\";";
	archivo<<"	 shape=Msquare color=orange style=filled}";
	//archivo<<"	color=yellow;}";

	archivo<<endl;
	archivo<<endl;

	archivo<<"	subgraph cluster_cuarto{"<<endl;
	archivo<<"		node_pago [shape=record    label=\"{";
	archivo<<		cancelar->imprimir()<<endl;
	archivo<<"		}\"];"<<endl;
	archivo<<"		label = \"Fila de pagos\";";
	archivo<<"	color=blue;}";
	//archivo<<"	color=yellow;}";

	archivo<<endl;
	archivo<<endl;

	archivo<<"	subgraph cluster_quinto{"<<endl;
	archivo<<		fila->imprimirLista()<<endl;
	archivo<<"		label = \"Ventanillas\";";
	archivo<<"	shape=Msquare color=purple style=filled}";
	//archivo<<"	color=yellow;}";

	archivo<<endl;
	archivo<<endl;

	archivo<<"}"<<endl;
	archivo.close();

	system("dot -Tpng grafo.txt -o grafo.png");
}

//SIMULACION CON TIEMPO ------------------------------------------------------------------------------------------------------------------------

void simulaciontiempo(){

    historial<<" SIMULACION PROCESADA"<<endl;
    bool pul = true;

    int carritosDisponibles = coches1->contador + coches2->contador;
    cout<<"Hay "<<carritosDisponibles<<" Coches Disponibles"<<endl;
    historial<<endl<<"Hay "<<carritosDisponibles<<" Coches Disponibles"<<endl;

    for(int i = 0; i < carritosDisponibles; i++){
        int carritos1 = coches1->contador;
        int carritos2 = coches2 ->contador;
        if(colacliente->contador > 0){
            if(carritos1 > 0 && carritos2 > 0){ // LAS DOS PILAS TIENEN COCHES
                int r = x(0,3);
                if(r == 1){ //PILA 1
                    Cliente* cliente = colacliente->sacar();
                    Cliente* carreta = coches1->sacar(cliente->getValor());
                    cout<<"El "<<cliente->getValor()<<" tiene un coche"<<endl;
                    colacompra->agregarElemento(cliente->getValor(),carreta->getValor());
                }else{//PILA 2
                    Cliente* cliente = colacliente->sacar();
                    Cliente* carreta = coches2->sacar(cliente->getValor());
                    cout<<"El "<<cliente->getValor()<<" tiene un coche"<<endl;
                    colacompra->agregarElemento(cliente->getValor(),carreta->getValor());
                }
            }else if(carritos1 > 0 && carritos2 <= 0){ //SE TOMAN COCHES DE LA PILA 1
                Cliente* cliente = colacliente->sacar();
                Cliente* carreta = coches1->sacar(cliente->getValor());
                cout<<"El "<<cliente->getValor()<<" tiene un coche"<<endl;
                colacompra->agregarElemento(cliente->getValor(),carreta->getValor());
            }else if(carritos1 <= 0 && carritos2 > 0) {//SE TOMAN COCHES DE LA PILA 2
                Cliente* cliente = colacliente->sacar();
                Cliente* carreta = coches2->sacar(cliente->getValor());
                cout<<"El "<<cliente->getValor()<<" tiene un coche"<<endl;
                colacompra->agregarElemento(cliente->getValor(),carreta->getValor());
            }
        }else{
            cout<<" No hay personas esperando "<<endl;
            historial<<" No hay personas esperando "<<endl;
            break;
        }
    }


    int turnoActual = x(1,100);
    std::stringstream sstm;
    sstm << "Persona " << turnoActual;
    string variapq = sstm.str();
    cout<<endl<<endl<<"Informa que "<<variapq<<" pase a pagar"<<endl;
    historial<<endl<<endl<<"Informa que "<<variapq<<" pase a pagar"<<endl;

    bool find = colacompra->buscarElemento(variapq);
    if(find){
        cout<<"Encontrado "<<variapq<<endl<<endl;
        historial<<"Pase a compras"<<variapq<<endl<<endl;
        ClienteAB* sacarTurno = colacompra->eliminarElemento(variapq);
        cout<<"El "<<sacarTurno->getValor()<<" sale de compras"<<endl;
        historial<<"El "<<sacarTurno->getValor()<<" sale de compras"<<endl;
        cancelar->ingresar(sacarTurno->getValor(),sacarTurno->carreta);
    }else{
        cout<<endl<<endl<<"El  "<<variapq<<" no esta presente"<<endl<<endl;
        historial<<endl<<"El  "<<variapq<<" no esta presente"<<endl<<endl;
    }
    cout<<endl;

    if(cancelar->contador > 0 ){
        for(int i = 1; i <= ventanas; i++){
            std::stringstream sst;
            sst << "Ventana "<<i;
            string caja= sst.str();
            if(filaventanas->verificarCaja(caja)){
                cout<<caja<<" Ocupada "<<endl;
                historial<<endl<<caja<<" Ocupada "<<endl;
                continue;
            }else{
                cout<<caja<<" Libre "<<endl;
                historial<<endl<<caja<<" Libre "<<endl;
                Cliente* sacarTurno = cancelar->sacar();
                filaventanas->llenarCaja(caja, sacarTurno->getValor(),sacarTurno->carreta);
                break;
            }
        }
    }


    ClienteAB* aux = filaventanas->cabeza;
    int tempo = 1;
    for(int i = 1; i <= ventanas; i++){
//if(aux->turnos > 1 && aux->estado == true){
        while(tempo < 60 && aux->estado == true){
            //aux->turnos--;
            tempo++;
            cout<<"Tiempo  = "<<tempo<<" , para  "<<aux->cliente<<endl<<endl;
            historial<<"Tiempo  = "<<tempo<<" , para "<<aux->cliente<<endl<<endl;
            Sleep(1000);
        } if(tempo == 60){
            string nombre = aux->cliente;
            string carrito = aux->carreta;
            tempo == 1;
            int ran = x(1,2);
            if(ran == 1){
                coches1->ingresar(carrito);
            }else{
                coches2->ingresar(carrito);
            }
            aux->carreta = "";
            aux->cliente = "Disponible";
            aux ->turnos = aux ->contTurnos;
            aux->estado = false;
            historial<<"El "<<nombre<<" sale de pagar y regresa el coche "<<carrito<<endl<<endl;
        }
        aux = aux->getSiguiente();
    }


    bool pule = true;
	while( pule != false){
		int eleccion;
		cout<<endl<<endl;
		cout<<"	1 Simulacion tiempo"<<endl;
		cout<<"	2 Agregar personas"<<endl;
		cout<<"	3 Graphviz"<<endl;
		cout<<"	4 Historial"<<endl;
		cout<<"	5 Salir"<<endl;
	    cout<<"	6 Simulacion "<<endl;
		cout<<"	Ingresar un numero: ";
		cin>>eleccion;
		cout<<endl;

		switch(eleccion){
			case 1:
				cout<<" SIMULACION DE MINIMARKET H"<<endl;
				historial.open("historial.txt",ios::app);
				simulaciontiempo();
				pule = false;
				break;
            case 2:
                int nuevosClientes;
                cout<<"	¿Cuantos personas desea ingresar?: ";
                cin>>nuevosClientes;
                insertarper(nuevosClientes);
                break;
			case 3:
			    grafoy(colacliente, coches1, coches2,colacompra,cancelar,filaventanas);
			    system("grafo.png");
				break;
			case 4:
				historial.close();
				system("historial.txt");
				break;
			case 5:
				cout<<" Final"<<endl;
				pule = false;
				break;
            case 6:
				cout<<"Simulacion"<<endl;
				historial.open("historial.txt",ios::app);
				simulacion();
				pule = false;
				break;
			default:
			    eleccion = 0;
				cout<<"	1 Simulacion tiempo"<<endl;
                cout<<"	2 Graphviz"<<endl;
                cout<<"	3 Historial"<<endl;
                cout<<"	4 Salir"<<endl;
   				cout<<"	5 Simulacion "<<endl;
                cout<<"	Ingresar un numero: ";
				cin>>eleccion;
				cout<<endl;
				break;
		}

	}

}
