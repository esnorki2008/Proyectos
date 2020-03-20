#include "cp.h"
#include "mkfile_mkdir.h"
CP::CP(SPB *Super,int Comienzo,std::string PathVirtualOrigen,const char *PathReal,std::string PathVirtualDestino,IUG Permiso)
{
    this->Permiso=Permiso;
    CopiarCarpetaArchivo(Super,Comienzo,PathVirtualOrigen,PathReal,PathVirtualDestino);
}

//Copiar
void CP::CopiarCarpetaArchivo(SPB *Super,int Comienzo, std::string PathVirtualOrigen, const char *PathReal, std::string PathVirtualDestino){
    int Actual=BuscarActual(Comienzo,PathVirtualOrigen,PathReal);
    if(Actual==-1){
        std::cout<<"No Se Puede Copiar, Porque No Se Encontro La Carpeta/Archivo De Origen  "<<PathVirtualOrigen<<std::endl;
        return;
    }
    //Copiando Archivo O Carpeta
    Actual=CopiarInodo(Super,Actual,PathReal);
    if(Actual==-1){
        std::cout<<"No Se Puede Copiar, Porque No Se Tienen Los Permisos Para Copiar  "<<PathVirtualOrigen<<std::endl;
        return;
    }
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Carpeta;//Leyendo Donde Se Coloco La Carpeta o Archivo Copia
    fseek(f,Actual,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
    //Libera Donde Estaba La Copia
    LiberarInodo(Super,PathReal,Actual);
    //Para Colocar En La Ubicacion Destino
    PathVirtualDestino=PathVirtualDestino+"/"+NombreACrear(PathVirtualOrigen.data());
    //Ver El Tipo
    int Tipo=0;
    if(Carpeta.i_type=='1')
        Tipo=1;
    //Crear Ubicacion Dependiendo El Tipo
    if(Tipo==0)
        new MKFILE_MKDIR(Super,Comienzo,PathVirtualDestino.data(),PathReal,"",true,false,Permiso);
    else
        new MKFILE_MKDIR(Super,Comienzo,PathVirtualDestino.data(),PathReal,"0",false,false,Permiso);
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
int CP::CopiarContenido(SPB *Super,int Comienzo, const char *PathReal){
    FILE *f;
    f=fopen(PathReal,"r+");
    BAR Contenido;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Contenido,sizeof(Contenido),1,f);
    fclose(f);
    BAR CopiaConte;
    strcpy(CopiaConte.b_content,Contenido.b_content);
    int NuevaCopia=BloqueLibre(Super,PathReal);
    if(NuevaCopia==-1)
        return NuevaCopia;
    f=fopen(PathReal,"r+");
    fseek(f,NuevaCopia,SEEK_SET);
    fwrite(&CopiaConte,sizeof(CopiaConte),1,f);
    fclose(f);
    return NuevaCopia;
}
int CP::CopiarDirectos(SPB *Super,int Comienzo, const char *PathReal,int Tipo){
    FILE *f;
    f=fopen(PathReal,"r+");
    BCA Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);

    BCA CopiaDir;
    for(int i=0;i<4;i++){
        CON Contenido=Carpeta.content[i];
        if(Contenido.b_inodo!=-1){
            if(Tipo==0){//Carpeta
                CopiaDir.content[i].b_inodo=CopiarInodo(Super,Contenido.b_inodo,PathReal);
                strcpy(CopiaDir.content[i].b_name,Carpeta.content[i].b_name);
            }else{//Archivo
                CopiaDir.content[i].b_inodo=CopiarContenido(Super,Contenido.b_inodo,PathReal);
                strcpy(CopiaDir.content[i].b_name,".....");
            }
        }else{
            CopiaDir.content[i].b_inodo=-1;
        }
    }



    int NuevaCopia=BloqueLibre(Super,PathReal);
    if(NuevaCopia==-1)
        return NuevaCopia;
    f=fopen(PathReal,"r+");
    fseek(f,NuevaCopia,SEEK_SET);
    fwrite(&CopiaDir,sizeof(CopiaDir),1,f);
    fclose(f);
    return NuevaCopia;
}
int CP::CopiarIndirectos(SPB *Super,int Nivel, int NivelActual, int Comienzo,  const char *PathReal,int Tipo){
    if(Nivel==NivelActual){
        return CopiarDirectos(Super,Comienzo,PathReal,Tipo);
    }
    FILE *f;
    f=fopen(PathReal,"r+");
    BAP Apunta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Apunta,sizeof(Apunta),1,f);
    fclose(f);

    BAP CopiaPtr;

    for(int i=0;i<16;i++){
        int Valor=Apunta.b_pointers[i];
        if(Valor!=-1){
            CopiaPtr.b_pointers[i]=CopiarIndirectos(Super,Nivel,NivelActual+1,Valor,PathReal,Tipo);
        }else{
            CopiaPtr.b_pointers[i]=-1;
        }
    }

    int NuevaCopia=BloqueLibre(Super,PathReal);
    if(NuevaCopia==-1)
        return NuevaCopia;
    f=fopen(PathReal,"r+");
    fseek(f,NuevaCopia,SEEK_SET);
    fwrite(&CopiaPtr,sizeof(CopiaPtr),1,f);
    fclose(f);
    return NuevaCopia;

}
int CP::CopiarInodo(SPB *Super,int Comienzo, const char *PathReal){
    if(TienePermiso(Comienzo,PathReal,"100",Permiso)==false){
        return -1;
    }


    FILE *f;
    f=fopen(PathReal,"r+");
    INO Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);

    INO CopiaCarpeta;
    DuplicarInodo(&Carpeta,&CopiaCarpeta);
    int Tipo=1;
    if(Carpeta.i_type=='0')
        Tipo=0;
    for(int i=0;i<12;i++){
        int Apuntador=Carpeta.i_block[i];
        if(Apuntador!=-1){
            CopiaCarpeta.i_block[i]=CopiarIndirectos(Super,0,0,Apuntador,PathReal,Tipo);

        }else{
            CopiaCarpeta.i_block[i]=-1;
        }
    }

    for(int i=0;i<3;i++){
        int Apuntador=Carpeta.i_block[i+12];
        if(Apuntador!=-1){
            CopiaCarpeta.i_block[i]=CopiarIndirectos(Super,1+i,0,Apuntador,PathReal,Tipo);
        }else{
            CopiaCarpeta.i_block[i]=-1;
        }
    }
    int NuevaCopia=InodoLibre(Super,PathReal);
    if(NuevaCopia==-1)
        return NuevaCopia;
    f=fopen(PathReal,"r+");
    fseek(f,NuevaCopia,SEEK_SET);
    fwrite(&CopiaCarpeta,sizeof(CopiaCarpeta),1,f);
    fclose(f);
    return NuevaCopia;
}
