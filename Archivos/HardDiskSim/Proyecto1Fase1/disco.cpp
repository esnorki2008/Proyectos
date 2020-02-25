#include "disco.h"
#include "reports.h"
#include "ext.h"
#include "recuperacion.h"
#include "queue"
void Disco::PropietarioArchivoParticion(const char *Nombre, const char *Path, int Tipo, int Perm, IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                E->CambiarPropietarioNormalRecursivo(Super.s_first_ino,Path,Tempo->Path.data(),Perm,Tipo);
                //E->CambiarPermisosNormalRecursivo(Super.s_first_ino,Path,Tempo->Path.data(),Tipo,Perm);
                return ;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder Modificar Permisos"<<std::endl;
    return ;
}

std::queue<JOR> Disco::Recuperar(int Comienzo,const char *Path){
    Recuperacion *Recuva  = new Recuperacion();
    std::queue<JOR>  Cola=Recuva->ListaDeOperaciones(Comienzo,Path);
    JOR Actual;
    //Retornar Cola
    return Cola;
}
std::queue<JOR> Disco::RecuperarInformacion(const char *Nombre){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){

                const char* Real=Tempo->Path.data();
                int Comienzo;
                int Tamanio;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                    Tamanio=Te.Logica.part_size;
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                    Tamanio=Te.Particion.part_size;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                return Recuperar(Comienzo,Tempo->Path.data());

            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder Perder Informacion"<<std::endl;
    std::queue<JOR> Vacia;
    return Vacia;
}
void Disco::PerderInformacion(const char *Nombre){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){

                const char* Real=Tempo->Path.data();
                int Comienzo;
                int Tamanio;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                    Tamanio=Te.Logica.part_size;
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                    Tamanio=Te.Particion.part_size;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                //Indicar Al Super Que Ya No Sirve
                Super.s_magic=-1;
                fseek(f,Comienzo,SEEK_SET);
                fwrite(&Super,sizeof(Super),1,f);

                fclose(f);

                FillDisk(Super.s_bm_inode_start,Super.s_inode_start,'\0',Tempo->Path.data());
                std::cout<<" Se Ha Provocado Una Perdida De La Informacion, Se Necesita Recuperar "<<std::endl;
                return ;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder Perder Informacion"<<std::endl;
    return ;

}
void Disco::FillDisk(int Begin, int Size, char Character,const char *Path){
    FILE *f;
    f=fopen(Path,"r+");
    int Kilo=Size/1024;
    if(Kilo>0){
        char Buffi[1024];
        for (int i=0;i<1024;i++) {
            Buffi[i]=Character;
        }

        fseek(f,Begin,SEEK_SET);
        for(int i=0;i<Kilo;i++){

            fwrite(&Buffi,sizeof (Buffi),1,f);
        }

    }
    Size=Size-Kilo*1024;
    Begin=Begin+(Kilo*1024);
    fseek(f,Begin,SEEK_SET);
    for(int i=0;i<Size;i++){

        fwrite(&Character,sizeof (Character),1,f);
    }
    fclose(f);
}

//MOV
void Disco::MoverArchivoParticion(const char *Nombre, const char *PathOrigen, const char *PathDestino, IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                E->MoverCarpetaArchivo(&Super,Super.s_first_ino,PathOrigen,Tempo->Path.data(),PathDestino);


                return ;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder MOVER"<<std::endl;
    return ;
}
void Disco::CopiarArchivoParticion(const char *Nombre, const char *PathOrigen, const char *PathDestino, IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                E->CopiarCarpetaArchivo(&Super,Super.s_first_ino,PathOrigen,Tempo->Path.data(),PathDestino);


                return ;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder COPIAR "<<std::endl;
    return ;
}
void Disco::BuscarArchivoParticion(const char *Nombre, const char *PathBase, const char *NombreBusqueda, IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                E->FIND(Super.s_first_ino,PathBase,Tempo->Path.data());


                return ;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder FIND "<<std::endl;
    return ;
}
void Disco::RenombrarArchivoParticion(const char *Nombre, const char *NuevoNombre, const char *Path, IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                E->CambiarNombre(Super.s_first_ino,Path,Tempo->Path.data(),NuevoNombre);

                return ;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder CAMBIAR Nombre"<<std::endl;
    return ;
}

void Disco::PermisoArchivoParticion(const char *Nombre,const char *Path,int Tipo,int Perm,IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);

                E->CambiarPermisosNormalRecursivo(Super.s_first_ino,Path,Tempo->Path.data(),Tipo,Perm);
                return ;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder Modificar Permisos"<<std::endl;
    return ;
}


void Disco::BorrarArchivoParticion(const char *Nombre, const char *Path,IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                //std::cout<<Super.s_block_start<<std::endl;

                E->EliminarArchivoCarpeta(&Super,Super.s_first_ino,Path,Tempo->Path.data());

                return ;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder Leerla"<<std::endl;
    return ;
}

std::string Disco::LeerArchivoParticion(const char *Nombre, const char *Path,IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                //std::cout<<Super.s_block_start<<std::endl;
                std::string Lectura=E->LeerArchivo(Super.s_first_ino,Tempo->Path.data(),Path);

                return Lectura;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Poder Leerla"<<std::endl;
    return "";
}

void Disco::ExpandirArchivoParticion(const char *Nombre, const char *Path,  std::string Contenido,IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                //std::cout<<Super.s_block_start<<std::endl;

                E->ExpandirArchivo(&Super,Super.s_first_ino,Path,Real,Contenido);
                return;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Crear Carpeta"<<std::endl;
}


void Disco::CrearArchivoParticion(const char *Nombre, const char *Path, char Padre, std::string Contenido,IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                //std::cout<<Super.s_block_start<<std::endl;
                if(Padre=='0'){
                    E->CrearArchivoSimple(&Super,Super.s_first_ino,Path,Real,Contenido);
                }else{
                   E->CrearArchivoCompleto(&Super,Super.s_first_ino,Path,Real,Contenido);
                }

                return;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Crear Carpeta"<<std::endl;
}

void Disco::CrearCarpetaParticion(const char *Nombre, const char *Path, char Padre,IUG Permiso){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                const char* Real=Tempo->Path.data();
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    Comienzo=Te.Logica.part_start+int(sizeof(EBR));
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                FILE *f;
                f=fopen(Real,"r+");
                SPB Super;
                fseek(f,Comienzo,SEEK_SET);
                fread(&Super,sizeof(Super),1,f);
                fclose(f);
                //std::cout<<Super.s_block_start<<std::endl;
                if(Padre=='0'){
                    E->CrearCarpetaSimple(&Super,Super.s_first_ino,Path,Real);
                }else{
                    E->CrearCarpetaCompleto(&Super,Super.s_first_ino,Path,Real);
                }

                return;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<" Para Crear Carpeta"<<std::endl;

}
void Disco::FormatearParticion(const char *Nombre,IUG Permiso,int Tipo){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                EXT *E = new EXT(Permiso);
                std::string Path=Tempo->Path;
                int TamanioStruct;
                int TamanioParticion;
                int Comienzo;
                //Es Logica
                if(Te.EsLogica){
                    TamanioStruct=int(sizeof(EBR));
                    TamanioParticion=Te.Logica.part_size;
                    Comienzo=Te.Logica.part_start;
                }else{
                    //TamanioStruct=int(sizeof(EBR));
                    TamanioStruct=0;
                    TamanioParticion=Te.Particion.part_size;
                    Comienzo=Te.Particion.part_start;
                }

                //Tipo De Formato

                    E->EstructurarFormatoEXT3(Comienzo,TamanioParticion,TamanioStruct,Path,Tipo);
                    std::cout<<"Se Formateo La Particion "<<Nombre<<" Con El Formato EXT3 Del Disco Ubicado En "<<Tempo->Path<<std::endl;


                return;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion Para Formatear "<<Nombre<<std::endl;
}
Disco::Disco(std::string Path,Disco *Nuevo)
{
    this->Path=Path;
    this->Siguiente=Nuevo;
    this->Apodo="vd"+this->GenerarApodo();
}
std::string Disco::GenerarApodo(){
    if(Siguiente==nullptr){
        this->Num=0;
        return this->NumeroAString(0);
    }else{
        this->Num=Siguiente->Num+1;
        if(Num/26>0){
            return this->NumeroAString(Num/26)+this->NumeroAString(Num%26);
        }else {
            return this->NumeroAString(Num%26);
        }
    }
}
void Disco::AgregarParticion(const char *Nombre){
    MOU Mont;
    if(Fun->ExisteArchivo(Path.data())){
        FILE *f;
        MBR Main;
        f=fopen(Path.data(),"r+");
        if (!f){
            return;
        }
        fread(&Main,sizeof(MBR),1,f);
        fclose(f);
        int ParIndex=Fun->SearchPAR(Nombre,Path.data());

        if(ParIndex==-1){
           EBR Logic=Fun->SearchEBR(Nombre,Path.data());
            if(Logic.part_status=='\0'){
                std::cout<<"No Se Encontro La Particion '"<<Nombre<<"' Para Montar En El Disco De  "<<this->Path<<std::endl;
                return;
            }else{//Es Logica
                Mont.EsLogica=true;
                Mont.Logica=Logic;
            }
        }else{
            //Es Primario O Extendida
            Mont.EsLogica=false;
            PAR Part=Main.mbr_partition[ParIndex];
            Mont.Particion=Part;
        }
        for (int i=0;i<Lista.count();i++) {
            MOU Compro =this->Lista.at(i);
            if(Fun->IF(Compro.Nombre,Nombre)){
                std::cout<<"Error Esa Particion Ya Fue Montada   "<<Nombre<<std::endl;
                return;
            }
        }
        Mont.Numero=this->Lista.count()+1;
        Mont.Nombre=Nombre;
        this->Lista<<Mont;
        std::cout<<"Se monto la particion:'"<<Nombre << "'  con el alias:'"<<this->Apodo<<Mont.Numero<<"' En El Disco De  "<<this->Path<<std::endl ;
    }
    else
    {
        std::cout<<"No Se Encontro Un Archivo En La Ubicación "<<Path<<std::endl;
    }

}
std::string Disco::NumeroAString(int Num){
    std::string Salida(1, char(97+Num));    
    return Salida;
}
void Disco::BorrarParticion(const char *Nombre){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                this->Lista.removeAt(i);
                std::cout<<"Se Borro La Particion "<<Nombre<<" De Memoria Del Disco En Ubicacion "<<Tempo->Path<<std::endl;
                return;
            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<std::endl;
}

bool Disco::ExisteParticionMontada(const char *Nombre){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                return true;

            }
        }
        Tempo=Tempo->Siguiente;
    }
    return false;

}
void Disco::BuscarDisco(const char *Nombre){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){


            }
        }
        Tempo=Tempo->Siguiente;
    }

    std::cout<<"No Se Encontro La Particion "<<Nombre<<std::endl;
}
bool Disco::DiscoLibreParaBorrar(const char *Nombre){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {
            if(Fun->IF(Tempo->Path,Nombre)){
                    return false;
            }
        Tempo=Tempo->Siguiente;
    }
    return true;
}
bool Disco::ParticionLibreParaBorrar(const char *Nombre){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {

        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,Nombre)){
                    return false;
            }
        }
        Tempo=Tempo->Siguiente;
    }
    return true;
}
void Disco::Reporte(const char *ID, const char *Path, const char *Tipo){
    Disco *Tempo=this;
    while (Tempo!=nullptr) {
        for (int i=0;i<Tempo->Lista.count();i++) {
            MOU Te= Lista.at(i);
            std::string NombreParti=Tempo->Apodo+std::to_string(Te.Numero);
            if(Fun->IF(NombreParti,ID)){
                int InicioParti=0;
                if(!Te.EsLogica){
                    InicioParti=Te.Particion.part_start;
                }else{
                    InicioParti=Te.Logica.part_start+int(sizeof(EBR));
                }



                Reports *R= new Reports();
                if(Fun->IF(Tipo,"disk")){
                    R->Graphviz(Tempo->Path.data(),Path);
                }else if(Fun->IF(Tipo,"mbr")){
                    //Tipo De Reporet eEBR o MBR
                    //if(!Te.EsLogica){
                        R->ReporteTablaMBR(Tempo->Path.data(),Path,Te.Numero);
                    //}else{
                    //    R->ReporteTablaEBR(Te.Logica,Path,Te.Numero);
                    //}

                }else if(Fun->IF(Tipo,"bm_inode")){
                    R->Reportebm_Inodo(InicioParti,Tempo->Path.data(),Path);
                }else if(Fun->IF(Tipo,"bm_block")){
                    R->Reportebm_Bloque(InicioParti,Tempo->Path.data(),Path);
                }else if(Fun->IF(Tipo,"tree")){
                    R->ReporteArbol(InicioParti,Tempo->Path.data(),Path);
                }

                else{
                    Fun->Out("Error En Reporte Del Disco");
                }
                delete(R);
                return;
            }
        }
        Tempo=Tempo->Siguiente;
    }
    std::cout<<"No Se Encontro La Particion "<<ID<<std::endl;


}
