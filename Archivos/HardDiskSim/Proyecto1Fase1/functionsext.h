#ifndef FUNCTIONSEXT_H
#define FUNCTIONSEXT_H

#include "functions.h"
class FunctionsExt : public Functions
{
public:
    //Auxiliares EXT
    //Buscar Inodo Libre
    int InodoLibre(SPB *Super,const char* Path);
    //Buscar Bloque Libre
    int BloqueLibre(SPB *Super,const char* Path);
    int BloqueLibreConte(SPB *Super,const char* Path);
    int DarPrimeraPos(SPB *Super,const char* Path);
    //Auxiliares
    bool EliminarArchivoCarpetaPadre(SPB *Super,int Comienzo,const char *PathVirtual,const char *PathReal,int Hijo);
    //Eliminar
    bool EliminarBloqueContenido(SPB *Super,int Comienzo,const char *PathReal);
    bool EliminarInodo(SPB *Super,int Comienzo,const char *PathReal);
    bool EliminarDirectos(SPB *Super,int Comienzo,const char *PathReal, int Tipo , int Elim);
    bool EliminarIndirectos(SPB *Super,int Nivel, int NivelActual,int Comienzo,const char *PathReal, int Tipo, int Elim);
    //Auxiliares
    void CantidadVeces(std::string Contenido);
    void DuplicarInodo(INO *Original,INO *Copia);
    //Busqueda
    int BuscarInodos(int Comienzo,std::string PathVirtual,const char *PathReal);
    int BuscarDirectos(int Comienzo,std::string PathVirtual,const char *PathReal,int Tipo);
    int BuscarIndirectos(SPB *Super,int Nivel,int NivelActual,int Comienzo,std::string PathVirtual,const char *PathReal,int Tipo);
    int BuscarPadre(int Comienzo,std::string PathVirtual,const char *PathReal);
    //Permisos
    std::string PermisosEnElPadre(int Comienzo,const char * PathReal,IUG Permiso);
    bool TienePermiso(int Comienzo,const char * PathReal,std::string Info,IUG Permiso);
    //Nombre De La Cosa A Crear
    std::string PathArchivo(const char *PathVirtual);
    std::string NombreACrear(const char *PathVirtual);
    //Contador
    //CantidadDeBarras
    int CantidadBarras(std::string Path);
    FunctionsExt();
    //
    int BuscarActual(int Comienzo,std::string PathVirtual,const char *PathReal);
    //
    int CrearIndirectos(int Nivel,int NivelActual,SPB *Super,const char *PathReal);
    //Reduce Un String Enviado En Tamanio Y Devuelve Lo Que Se Redujo
    std::string ReducirTamanio(std::string *Ingreso,int Num);
    //Redux
    std::string Reducir(std::string *Entrada);
    //Contenido En El Archivo
    std::string ColocarContenidoArchivo(int PosDirecto,SPB *Super,const char *PathReal,std::string Contenido);
    //ComprobarPermisos
    bool PermisoInodo(int Comienzo,const char *PathReal,IUG Permiso);
    bool PermisoDirectos(int Comienzo,const char *PathReal,IUG Permiso);
    bool PermisoIndirectos(int Nivel, int NivelActual,int Comienzo,const char *PathReal,IUG Permiso);
    //Liberar
    void LiberarBloque(SPB *Super,const char *PathReal,int Comienzo);
    void LiberarInodo(SPB *Super,const char *PathReal,int Comienzo);
    //ContenidoArchivoDirecto
    std::string ContenidoArchivoDirectos(INO *Ino,SPB *Super,int PosPadre, const char *PathReal ,std::string Contenido);
    //ContenidoArchivoIndirecto
    std::string ContenidoArchivoInDirectos(INO *Ino,SPB *Super,int PosPadre, const char *PathReal,const char *PathVirtual ,std::string Contenido);
private:
    int ValPrimeraPos=-1;
    int ValTamanio=-1;
};

#endif // FUNCTIONSEXT_H
