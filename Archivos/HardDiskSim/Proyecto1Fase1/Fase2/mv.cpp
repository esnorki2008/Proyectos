#include "mv.h"

MV::MV(IUG Permiso,SPB *Super,int Comienzo,std::string PathVirtualOrigen,const char *PathReal,std::string PathVirtualDestino)
{
    this->Permiso=Permiso;
    MoverCarpetaArchivo(Super,Comienzo,PathVirtualOrigen,PathReal,PathVirtualDestino);
}

//Mover
void MV::MoverCarpetaArchivo(SPB *Super, int Comienzo, std::string PathVirtualOrigen, const char *PathReal, std::string PathVirtualDestino){
    int Existe=BuscarActual(Comienzo,PathVirtualOrigen,PathReal);
    //Buscar Que Exista
    if(Existe==-1){
        std::cout<<"El Archivo/Carpeta No Existe En La Ruta "<<PathVirtualOrigen<<std::endl;
        return;
    }
    //Leer informacion
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Carpeta;//Leyendo Donde Se Coloco La Carpeta o Archivo Copia
    fseek(f,Existe,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
    //Borrar Del Padre
    EliminarArchivoCarpetaPadre(Super,Comienzo,PathVirtualOrigen.data(),PathReal,Existe);
    //Crear Lugar En El Nuevo Padre
    //Ver El Tipo
    int Tipo=0;
    if(Carpeta.i_type=='1')
        Tipo=1;
    //Crear Ubicacion Dependiendo El Tipo
    if(Tipo==0)
        new MKFILE_MKDIR(Super,Comienzo,PathVirtualDestino.data(),PathReal,"",true,false,Permiso);
        //CrearCarpetaSimple(Super,Comienzo,PathVirtualDestino.data(),PathReal);
    else
        //CrearArchivoSimple(Super,Comienzo,PathVirtualDestino.data(),PathReal,"Dummy");
        new MKFILE_MKDIR(Super,Comienzo,PathVirtualDestino.data(),PathReal,"",false,false,Permiso);
    //Ver Si Se Pudo Crear
    int Colocado=BuscarActual(Comienzo,PathVirtualDestino,PathReal);
    if(Colocado==-1){
        std::cout<<"No Se Pudo Colocar La Carpeta O Archivo En Su Destino "<<PathVirtualDestino<<std::endl;
        return;
    }
    //Actualizar La Informacion De Lo Creado Con Lo SIMPLE
    f=fopen(PathReal,"r+");
    fseek(f,Colocado,SEEK_SET);
    fwrite(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
}
