#include <QCoreApplication>
#include <stdlib.h>
#include <iostream>
#include <fstream>
struct Str{
    int Tipo;
    char Apellido1[21];
    char Apellido2[21];
    char Nombre[21];
    char Codigo[21];
    int Nota;
};

int BuscarStruct(const char *Entrada);
void Eliminar();
void Agregar();
void Actualizar();
void CrearArchivo();
bool CompararChar(const char *a, const char *b);
void Escribir(Str *Info);
void MostrarTodo();

int main()
{
    CrearArchivo();
    //Agregar();

    Actualizar();
    MostrarTodo();
    //QCoreApplication a(argc, argv);
    return 0;
}
void MostrarTodo(){
    FILE* file;
    Str Info;
    file = fopen("alumnos_[201700886]", "r+");
    fseek(file,0,SEEK_SET );

    std::cout<<"***********************************************"<<std::endl;
    std::cout<<"Información De Todos Los Alumnos Y Profesores"<<std::endl;;
    while (!feof(file)) // Leer Al Fin
    {
        fread(&Info, sizeof(Str), 1, file);
        if(!feof(file)){
        std::cout<<"***********************************************"<<std::endl;
        std::cout<<"Tipo: "<<Info.Tipo<<std::endl
                <<"Apellido1: "<<Info.Apellido1<<std::endl
               <<"Apellido2: "<<Info.Apellido2<<std::endl
              <<"Nombre: "<<Info.Nombre<<std::endl
             <<"Codigo: "<<Info.Codigo<<std::endl
            <<"Nota: "<<Info.Nota<<std::endl ;
        std::cout<<"***********************************************"<<std::endl;
        }
    }
    fclose(file);
}
void Actualizar(){
    Str Info;
    std::cout<<"Ingrese El Codigo Del Alumno|Profesor A Buscar(MAX 21 Caracteres)"<<std::endl;;
    std::cin>>Info.Codigo;
    int Indice=BuscarStruct(Info.Codigo);
    if(Indice==-1){
        std::cout<<"No Se Encontro El Estudiante|Profesor Con Codigo:  "<<Info.Codigo<<std::endl;;
        return;
    }
    FILE* file;
    file = fopen("alumnos_[201700886]", "r+");
    fseek(file, Indice, SEEK_SET );
    fread(&Info, sizeof(Str), 1, file);

    std::cout<<"Ingrese La Información Que Se Solicita Para Actualizar"<<std::endl;;

    //std::cout<<"Ingrese El Tipo (0 Estudiante| 1 Profesor)"<<std::endl;
    //std::cin>>Info.Tipo;

    std::cout<<"Ingrese Nombre (MAX 21 Caracteres)"<<std::endl;;
    std::cin>>Info.Nombre;

    std::cout<<"Ingrese Apellido1 (MAX 21 Caracteres)"<<std::endl;;
    std::cin>>(Info.Apellido1);

    std::cout<<"Ingrese Apellido2 (MAX 21 Caracteres)"<<std::endl;
    std::cin>>(Info.Apellido2);

    if(Info.Tipo==0)
        std::cout<<"Ingrese Nota"<<std::endl;
    else
        std::cout<<"Ingrese Seccion"<<std::endl;
    std::cin >>Info.Nota;
    fclose(file);
    Escribir(&Info);
    std::cout<<"Se Ha Actualizado La Información"<<std::endl;
}
void Escribir(Str *Info){
    FILE* file;
    file = fopen("alumnos_[201700886]", "r+");
    fseek(file,0,SEEK_END);
    fwrite(Info, sizeof(Str), 1, file);



    fclose(file);
}
void Agregar(){
    Str Info;

    std::cout<<"***********************************************"<<std::endl;
    std::cout<<"Ingrese La Información Que Se Solicita"<<std::endl;

    std::cout<<"***********************************************"<<std::endl;
    std::cout<<"Ingrese El Tipo (0 Estudiante| 1 Profesor)"<<std::endl;
    std::cin>>Info.Tipo;

    std::cout<<"Ingrese Codigo (MAX 21 Caracteres)"<<std::endl;
    std::cin>>(Info.Codigo);

    std::cout<<"Ingrese Nombre (MAX 21 Caracteres)"<<std::endl;
    std::cin>>(Info.Nombre);

    std::cout<<"Ingrese Apellido1 (MAX 21 Caracteres)"<<std::endl;
    std::cin>>(Info.Apellido1);

    std::cout<<"Ingrese Apellido2 (MAX 21 Caracteres)"<<std::endl;
    std::cin>>(Info.Apellido2);

    if(Info.Tipo==0)
        std::cout<<"Ingrese Nota"<<std::endl;
    else
        std::cout<<"Ingrese Seccion"<<std::endl;
    std::cin >>Info.Nota;
    Escribir(&Info);

    std::cout<<"***********************************************"<<std::endl;
    std::cout<<"Se Ha Guardado La Información"<<std::endl;

    std::cout<<"***********************************************"<<std::endl;
}
bool CompararChar(const char *a, const char *b){
    std::string A = a;
    std::string B = b;

    if (A.size() != B.size()) {
        return false;
    }
    for (std::string::const_iterator c1 = A.begin(), c2 = B.begin(); c1 != A.end(); ++c1, ++c2) {
        if (tolower(*c1) != tolower(*c2)) {
            return false;
        }
    }
    return true;
}
int BuscarStruct(const char *Entrada){
    FILE* file;
    file = fopen("alumnos_[201700886]", "r+");
    Str Dato;
    //
    int Seek=-1;
    while (!feof(file)) // Leer Al Fin
    {
        fread(&Dato, sizeof(Str), 1, file);
        Seek=Seek+(int)sizeof(Str);
        if(CompararChar(Dato.Codigo,Entrada)){
            return Seek;
        }

    }
    fclose(file);
    return Seek;
}
void CrearArchivo(){
    std::ifstream infile("alumnos_[201700886]");
    bool Existe=infile.good();
    if(!Existe){
        FILE* file;
        file =fopen("alumnos_[201700886]", "w"); //Crear El Archivo
        fclose(file);
    }
}
