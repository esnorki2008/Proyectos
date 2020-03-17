#ifndef SISTEMA_H
#define SISTEMA_H
#include "menu.h"

class Sistema
{
public:
    //FASE 2
    void Mkfile(std::string Path,std::string P,std::string Num,std::string Cont);
    void Login(std::string Usr,std::string Pwd,std::string Id);
    void Mkfs(std::string Ejecutar,std::string Type,bool Tipo);
    void Mkdir(std::string Path,std::string P);
    //FASE 1
    void Ejecutar(std::string Ejecutar);
    void CrearDirectorio(std::string Conte);
    std::string SplitRaid(std::string Conte);
    void Crear(int Size,char Unit, char Fit[2],std::string Path);
    void BorrarDisco(std::string Path);
    //Borrar Disco
    void Formato(int Size, char Fit[2],char Unit,std::string Path,char Type,std::string Name);
    void Aumentar(int Size, std::string Path, std::string Name, char Unit);
    void Borrar(std::string Delete,std::string Path,std::string Name);
    //Borrar Particion
    void Reportes(std::string Id, std::string  Name,std::string Path);
    void Montar(std::string Path, std::string  Name);
    void Desmontar(std::string  Name);
    Sistema();
private:
Functions *Func;
Menu *M;
};

#endif // SISTEMA_H
