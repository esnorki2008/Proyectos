#include <QCoreApplication>
#include "menu.h"
#include <algorithm>
#include <fstream>
#include <iostream>
#include <string>
#include <stdio.h>
#include "structs.h"
#include "parser.h"
#include "functions.h"
#include "sistema.h"



//int size = 1024;Un KiloByte
/* random record description - could be anything */
extern int yyrestart( FILE* archivo);//METODO QUE PASA EL ARCHIVO A FLEX
extern int yyparse();


int main()
{



    printf("\n");



    Functions *Fu = new Functions();
    while (true) {
        std::cout<<"\x1B[32m"<<"--------------MANEJO DE RAID---------------"<<std::endl;
        std::cout<<"Ingrese El Comando , Escriba Salir O Limpiar "<<std::endl;
        char Exec[1000];
        std::cin.getline(Exec,1000);



        if(Fu->IF("salir",Exec)){
            std::cout<<"Saliendo Del Programa"<<std::endl;
            return 1;
        }else if(Fu->IF("limpiar",Exec)){
            std::system("clear");
        }else{
            std::ofstream Archivo("salida.txt");
            Archivo <<Exec;
            Archivo.close();
            const char* x = "salida.txt";
            FILE* input = fopen(x, "r" );
            yyrestart(input);
            yyparse();

            //return 1;
        }
    }



    /*Functions *Fun = new Functions();
    ALE Num;
    Num.Num=20;
    Num.Siguiente=false;
    FILE *f;
    if(Fun->ExisteArchivo("Info.inf"))
        f=fopen("Info.inf","r+");
    else
        f=fopen("Info.inf","w");


    fseek(f,0,SEEK_SET);
    fread(&Num,sizeof(ALE),1,f);

*/

    //

    /*
    const char* x = "/home/linuxlite/Documents/Archivos/temp.txt";
    FILE* input = fopen(x, "r" );
    yyrestart(input);//SE PASA LA CADENA DE ENTRADA A FLEX
    yyparse();//SE INICIA LA COMPILACION*/


    //std::string age;
    //std::cin >> age;
    //M();
}


