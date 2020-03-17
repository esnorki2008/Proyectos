#include "mkfile_mkdir.h"

MKFILE_MKDIR::MKFILE_MKDIR(SPB *Super, int Comienzo, const char *PathVirtual, const char *PathReal, std::string Contenido,bool EsCarpeta, bool EsCompleto,IUG Permiso)
{
    this->Permiso=Permiso;
    if(EsCarpeta){
        if(EsCompleto){
            CrearCarpetaCompleto(Super,Comienzo,PathVirtual,PathReal);
        }else{
            CrearCarpetaSimple(Super,Comienzo,PathVirtual,PathReal);
        }
    }else
    {
        if(EsCompleto){
            CrearArchivoCompleto(Super,Comienzo,PathVirtual,PathReal,Contenido);
        }else{
            CrearArchivoSimple(Super,Comienzo,PathVirtual,PathReal,Contenido);
        }

    }
}

//CrearArchivo
bool MKFILE_MKDIR::CrearArchivoCompleto(SPB *Super, int Comienzo, const char *PathVirtual, const char *PathReal, std::string Contenido){
    int PosPadre=0;
    if(IF(Contenido,"")){
        Contenido=Contenido+" ";
    }
    //CrearCarpetaCompleto(Super,Comienzo,PathVirtual,PathReal);
    std::string CarpetaCrear=PathArchivo(PathVirtual);
    if(CarpetaCrear.length()==0)
        CarpetaCrear=CarpetaCrear+"/";

    CrearCarpetaCompleto(Super,Comienzo,CarpetaCrear.data(),PathReal);

    std::string NombreArchivo=NombreACrear(PathVirtual);
    if(CantidadBarras(PathVirtual)==1){
        PosPadre=Comienzo;
    }else{
        PosPadre=BuscarPadre(Comienzo,PathVirtual,PathReal);
        if(PosPadre==-1){
            std::cout<<"No Se Pudo Crear El Archivo "<<NombreArchivo  <<" En "<<PathVirtual<<" En El Disco Ubicado En"<<PathReal<<std::endl;
            return false;
        }
    }

    if(!TienePermiso(PosPadre,PathReal,"010",Permiso)==false){
        std::cout<<"No Se Pudo Crear El Archivo "<<NombreArchivo  <<" La Sesion Actual No Tiene Los Permisos Necesarios Para La Ruta"<<PathReal<<std::endl;
        return false;
    }

    std::string NombreCarpeta=NombreACrear(PathVirtual);
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Inodo;
    fseek(f,PosPadre,SEEK_SET);
    fread(&Inodo,sizeof(Inodo),1,f);
    fclose(f);

    int Num=CarpetaArchivoSimpleDirectos(&Inodo,Super,PosPadre,PathReal,NombreCarpeta,Contenido);
    if(Num==-2)
        return false;

    if(Num==1)
        return true;

    if(CarpetaArchivoSimpleInDirectos(&Inodo,Super,PosPadre,PathReal,PathVirtual,NombreCarpeta,Contenido)!=-1)
        return true;

    return false;
}
bool MKFILE_MKDIR::CrearArchivoSimple(SPB *Super, int Comienzo, const char *PathVirtual, const char *PathReal, std::string Contenido){
    int PosPadre=0;
    if(IF(Contenido,"")){
        Contenido=Contenido+" ";
    }
    std::string NombreArchivo=NombreACrear(PathVirtual);
    if(CantidadBarras(PathVirtual)==1){
        PosPadre=Comienzo;
    }else{
        PosPadre=BuscarPadre(Comienzo,PathVirtual,PathReal);
        if(PosPadre==-1){
            std::cout<<"No Se Pudo Crear El Archivo "<<NombreArchivo  <<" En "<<PathVirtual<<" En El Disco Ubicado En"<<PathReal<<std::endl;
            return false;
        }
    }

    if(!TienePermiso(PosPadre,PathReal,"010",Permiso)){
        std::cout<<"No Se Pudo Crear El Archivo "<<NombreArchivo  <<" La Sesion Actual No Tiene Los Permisos Necesarios Para La Ruta"<<PathReal<<std::endl;
        return false;
    }

    std::string NombreCarpeta=NombreACrear(PathVirtual);
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Inodo;
    fseek(f,PosPadre,SEEK_SET);
    fread(&Inodo,sizeof(Inodo),1,f);
    fclose(f);
    int Num;
    Num=CarpetaArchivoSimpleDirectos(&Inodo,Super,PosPadre,PathReal,NombreCarpeta,Contenido);
    if(Num==1)
        return true;
    if(Num==-2)
        return false;

    if(CarpetaArchivoSimpleInDirectos(&Inodo,Super,PosPadre,PathReal,PathVirtual,NombreCarpeta,Contenido)==1)
        return true;

    return false;
}
//CarpetaSimple
bool MKFILE_MKDIR::CrearCarpetaSimple(SPB *Super,int Comienzo, const char *PathVirtual, const char *PathReal){
    /*if(BuscarActual(Comienzo,PathVirtual,PathReal)!=-1)
        return true;
*/

    int PosPadre=0;
    if(CantidadBarras(PathVirtual)==1){
        PosPadre=Comienzo;
    }else{
        PosPadre=BuscarPadre(Comienzo,PathVirtual,PathReal);
        if(PosPadre==-1){
            std::cout<<"No Se Pudo Crear La Carpeta En "<<PathVirtual<<" En El Disco Ubicado En"<<PathReal<<std::endl;
            return false;
        }
    }

    if(TienePermiso(PosPadre,PathReal,"010",Permiso)==false){
        std::cout<<"No Se Tiene Permisos Para ESCRIBIR En la Carpeta Padre En '"<<PathVirtual<<"' La Sesion Actual No Tiene Los Permisos Necesarios "<<std::endl;
        return false;
    }

    std::string NombreCarpeta=NombreACrear(PathVirtual);
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Inodo;
    fseek(f,PosPadre,SEEK_SET);
    fread(&Inodo,sizeof(Inodo),1,f);
    fclose(f);
    int Num=CarpetaArchivoSimpleDirectos(&Inodo,Super,PosPadre,PathReal,NombreCarpeta,"");
    if(Num==1)
        return true;
    if(Num==-2)
        return false;

    if(CarpetaArchivoSimpleInDirectos(&Inodo,Super,PosPadre,PathReal,PathVirtual,NombreCarpeta,"")==1)
        return true;
    return false;
}
//CarpetaCompletaUsandoLaSimple
bool MKFILE_MKDIR::CrearCarpetaCompleto(SPB *Super,int Comienzo, const char *PathVirtual, const char *PathReal){
    std::string s = PathVirtual;
    std::string delimiter = "/";
    size_t pos = 0;
    std::string token;
    std::string Escritura="";
    while ((pos = s.find(delimiter)) != std::string::npos) {
        token = s.substr(0, pos);
        if(IF("",token)){

        }else{
        Escritura=Escritura+"/"+token;
                if(BuscarActual(Comienzo,Escritura.data(),PathReal)==-1){
                    std::cout<<"Creando Carpeta "<<Escritura.data()<<std::endl;
                    CrearCarpetaSimple(Super,Comienzo,Escritura.data(),PathReal);
                }
        }
        s.erase(0, pos + delimiter.length());
    }
    Escritura=Escritura+"/"+s;
    CrearCarpetaSimple(Super,Comienzo,Escritura.data(),PathReal);

    return true;
}

//Crear CarpetasYArchivos
int MKFILE_MKDIR::CarpetaArchivoSimpleDirectos(INO *Ino,SPB *Super,int PosPadre, const char *PathReal ,std::string NombreCarpeta,std::string Contenido){
    INO Inodo=*Ino;
    FILE *f;
    f=fopen(PathReal,"r+");
    BCA BloqueDirecto;


    for(int i=0;i<12;i++){
        int Pos=Inodo.i_block[i];
        fseek(f,Pos,SEEK_SET);
        fread(&BloqueDirecto,sizeof (BloqueDirecto),1,f);
            for(int j=0;j<4;j++){
                CON Con=BloqueDirecto.content[j];
                if(IF(NombreCarpeta,Con.b_name)){
                    fclose(f);
                    return -2;
                }
            }


    }
    fclose(f);
    for(int i=0;i<12;i++){
        int Pos=Inodo.i_block[i];

          if(Pos==-1){
            //Crea Bloque Directo Y Se Manda A Crear Carpeta

            int EspacioBloque=BloqueLibre(Super,PathReal);

            if(EspacioBloque==-1){
                std::cout<<"No Se Pudo Crear El Bloque, Insuficiente Tamanio"<<std::endl;
                return -1;
            }
            FILE *f;
            f=fopen(PathReal,"r+");
            BCA BloqueDirecto;
            IniciarBloqueCarpeta(&BloqueDirecto);
            fseek(f,EspacioBloque,SEEK_SET);
            fwrite(&BloqueDirecto,sizeof (BloqueDirecto),1,f);
            Inodo.i_block[i]=EspacioBloque;
            fseek(f,PosPadre,SEEK_SET);
            fwrite(&Inodo,sizeof (Inodo),1,f);
            fclose(f);

            int Colocacion=0;
            if(IF("",Contenido))
            Colocacion=ColocarCarpeta(NombreCarpeta,EspacioBloque,Super,PathReal);
            else
            Colocacion=ColocarArchivo(NombreCarpeta,EspacioBloque,Super,PathReal,Contenido);


            if(Colocacion!=-1)
                return 1;

        }else{
            //Como Posicion Es Valida Se Prueba Colocar Carpeta
            int Valor=-1;
            if(IF("",Contenido))
            Valor=ColocarCarpeta(NombreCarpeta,Pos,Super,PathReal);
            else
            Valor=ColocarArchivo(NombreCarpeta,Pos,Super,PathReal,Contenido);


            if(Valor!=-1){
                //Se Creo La Carpeta
                return 1;
            }
        }
    }
    return -1;
}
int MKFILE_MKDIR::CarpetaArchivoSimpleInDirectos(INO *Ino, SPB *Super, int PosPadre, const char *PathReal,const char *PathVirtual, std::string NombreCarpeta,std::string Contenido){
    INO Inodo=*Ino;
    //Posicion De Un Bloque De Directos Con Alguna Ranura
    for(int i=0;i<3;i++){
        int Pos=Inodo.i_block[12+i];
        if(Pos==-1){
            //Se Crean Los Bloques Indirectos
            Pos=CrearIndirectos(i+1,0,Super,PathReal);
            if(Pos==-1 || Pos==0){
                //Si No Se Puede Crear Retornar
                std::cout<<"Cancelando POR NO PODER CREAR INDIRECTO "<<Pos<<std::endl;
                return -1;
            }else{
                Inodo.i_block[12+i]=Pos;
                //Escribir Inodo Modificado
                FILE *f;
                f=fopen(PathReal,"r+");
                fseek(f,PosPadre,SEEK_SET);
                fwrite(&Inodo,sizeof (Inodo),1,f);
                fclose(f);
            }
        }

        //El Indirecto En La Posicion Pos Si Existe
        int Busqueda=BuscarIndirectos(Super,0,0,Pos,PathVirtual,PathReal,2);
        if(Busqueda!=-1){
            //Bloque Directo Existente
            int Valor=-1;
            if(IF("",Contenido))
            Valor=ColocarCarpeta(NombreCarpeta,Busqueda,Super,PathReal);
            else
            Valor=ColocarArchivo(NombreCarpeta,Busqueda,Super,PathReal,Contenido);
            if(Valor!=-1 && Valor!=-2){
                //Se Creo La Carpeta
                return 1;
            }
        }

    }
    return -1;
}
//ColocarCarpetaEnElBloque
int MKFILE_MKDIR::ColocarCarpeta(std::string NombreCarpeta,int PosDirecto, SPB *Super, const char *PathReal){
    FILE *f;
    f=fopen(PathReal,"r+");
    fseek(f,PosDirecto,SEEK_SET);
    BCA Directo;
    fread(&Directo,sizeof(Directo),1,f);
    for(int z=0;z<4;z++){

        int Pos=Directo.content[z].b_inodo;

        if(Pos==-1){

            //Llenar Nombre
            std::string Sub;
            Sub=NombreCarpeta.substr(0,12);
            strcpy(Directo.content[z].b_name,Sub.c_str());
            //Inodo Nuevo
            int Libre = InodoLibre(Super,PathReal);
            Directo.content[z].b_inodo=Libre;

            //Se Coloca La Carpeta
            fseek(f,Libre,SEEK_SET);
            INO Carpeta;
            std::cout<<"SE CREO CARPETA  "<<NombreCarpeta <<" EN "<<Libre <<" PERO FALTA PONERLE PERMISOS Y ESO"<<std::endl;
            IniciarInodo(&Carpeta,1,1,0,-1,'0',111111);
            fwrite(&Carpeta,sizeof (Carpeta),1,f);
            //Se Actualizo El Apuntador Directo
            fseek(f,PosDirecto,SEEK_SET);
            fwrite(&Directo,sizeof (Directo),1,f);
            fclose(f);
            return 1;
        }

    }
    //fwrite(&Apuntador,sizeof (Apuntador),1,f);


    return -1;
}
//ColocarArchivo
int MKFILE_MKDIR::ColocarArchivo(std::string NombreCarpeta, int PosDirecto, SPB *Super, const char *PathReal, std::string Contenido){
    FILE *f;
    f=fopen(PathReal,"r+");
    fseek(f,PosDirecto,SEEK_SET);
    BCA Directo;
    fread(&Directo,sizeof(Directo),1,f);
    for(int z=0;z<4;z++){

        int Pos=Directo.content[z].b_inodo;

        if(Pos==-1){

            //Llenar Nombre
            std::string Sub;
            Sub=NombreCarpeta.substr(0,12);
            strcpy(Directo.content[z].b_name,Sub.c_str());
            //Inodo Nuevo
            int Libre = InodoLibre(Super,PathReal);
            Directo.content[z].b_inodo=Libre;

            //Se Coloca Archivo
            fseek(f,Libre,SEEK_SET);
            INO Archivo;
            std::cout<<"Se creo Archivo  "<<NombreCarpeta <<" en "<<Libre <<" PERO FALTA PONERLE PERMISOS Y ESO"<<std::endl;
            IniciarInodo(&Archivo,1,1,0,-1,'0',111111);
            Archivo.i_type='1';
            fwrite(&Archivo,sizeof (Archivo),1,f);
            //Se Actualizo El Apuntador Directo
            fseek(f,PosDirecto,SEEK_SET);
            fwrite(&Directo,sizeof (Directo),1,f);
            fclose(f);

            std::string Retor=ContenidoArchivoDirectos(&Archivo,Super,Libre,PathReal,Contenido);
            if(IF(Retor,"")){
                return 1;
            }else{
                std::string Retor=ContenidoArchivoInDirectos(&Archivo,Super,Libre,PathReal,"",Contenido);
            }

            if(IF(Retor,"")){
                return 1;
            }
        }

    }


    return -1;
}
