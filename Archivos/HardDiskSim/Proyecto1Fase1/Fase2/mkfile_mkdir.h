#ifndef MKFILE_H
#define MKFILE_H
#include <iostream>
#include <time.h>
#include "structsext.h"
#include "functionsext.h"

class MKFILE_MKDIR:public FunctionsExt
{
public:
    MKFILE_MKDIR(SPB *Super, int Comienzo, const char *PathVirtual, const char *PathReal, std::string Contenido,bool EsCarpeta, bool EsCompleto,IUG Permiso);
    //CrearArchivoSimple
    bool CrearArchivoCompleto(SPB *Super,int Comienzo,const char *PathVirtual,const char *PathReal,std::string Contenido);
    //CrearArchivoSimple
    bool CrearArchivoSimple(SPB *Super,int Comienzo,const char *PathVirtual,const char *PathReal,std::string Contenido);
    //CrearTodasCarpetas
    bool CrearCarpetaCompleto(SPB *Super,int Comienzo,const char *PathVirtual,const char *PathReal);
    //CrearCarpetasSimples
    bool CrearCarpetaSimple(SPB *Super,int Comienzo,const char *PathVirtual,const char *PathReal);
    //Buscadores
    int CarpetaArchivoSimpleDirectos(INO *Ino,SPB *Super,int PosPadre, const char *PathReal ,std::string NombreCarpeta,std::string Contenido);
    //CrearCarpetaSimpleEn Apuntadores Indirectos
    int CarpetaArchivoSimpleInDirectos(INO *Ino,SPB *Super,int PosPadre, const char *PathReal,const char *PathVirtual ,std::string NombreCarpeta,std::string Contenido);

private:
    IUG Permiso;
    //Poner Archivo
    int ColocarArchivo(std::string NombreCarpeta,int PosDirecto,SPB *Super,const char *PathReal,std::string Contenido);
    //CrearCarpetaSimpleEn Apuntadores Drectos
    //Comprobar Si Se Puede Colocar Carpeta En Un Bloque Directo
    int ColocarCarpeta(std::string NombreCarpeta,int PosDirecto,SPB *Super,const char *PathReal);
};

#endif // MKFILE_H
