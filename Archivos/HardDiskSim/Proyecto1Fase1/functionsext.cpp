#include "functionsext.h"

FunctionsExt::FunctionsExt()
{

}

//

int FunctionsExt::InodoLibre(SPB *Super,const char* Path){
    int Out=-1;
    FILE *f;
    f=fopen(Path,"r+");
    int Ubi=Super->s_bm_inode_start;
    int Tamanio=Super->s_inodes_count;
    //Ubicarse En el BM
    fseek(f,Ubi,SEEK_SET);
    char Lectura;
    //std::cout<<"/////////////////////// "<< Super->s_block_start<<std::endl;
    for(int i=0;i<Tamanio;i++){
        fread(&Lectura,sizeof(Lectura),1,f);
        if(Lectura=='0'){
            Out= Super->s_inode_start+(i*Super->s_inode_size);
            fseek(f,Ubi+i,SEEK_SET);
            char Actualizar='1';
            fwrite(&Actualizar,sizeof (Actualizar),1,f);
            break;
        }else if(Lectura!='1'){
            std::cout<<"El Disco En "<<Path<<"Presenta Errores, Se Recomienda Restaurarlo "<<std::endl;
            break;
        }else if(Lectura!='1' && Lectura!='0'){
            std::cout<<"El Disco En "<<Path<<"Presenta Errores, Se Recomienda Restaurarlo '"<<Lectura<<"'"<<std::endl;
            break;
        }
    }
    //std::cout<<"/////////////////////// "<< Super->s_block_start<<std::endl;
    fclose(f);
    return Out;
}
int FunctionsExt::BloqueLibre(SPB *Super, const char *Path){
    int Out=-1;
    FILE *f;
    f=fopen(Path,"r+");
    int Ubi=Super->s_bm_block_start;
    int Tamanio=Super->s_blocks_count;
    //Ubicarse En el BM
    fseek(f,Ubi,SEEK_SET);
    char Lectura;



    for(int i=0;i<Tamanio;i++){
        fread(&Lectura,sizeof(Lectura),1,f);
        if(Lectura=='0'){
            //ARREGLAR
            Super->s_block_start= Super->s_inode_start+Super->s_inodes_count*(int(sizeof (INO)));
            //
            Out= Super->s_block_start+(i*Super->s_block_size);
            fseek(f,Ubi+i,SEEK_SET);
            char Actualizar='1';
            fwrite(&Actualizar,sizeof (Actualizar),1,f);


            break;
        }else if(Lectura!='1'){
            std::cout<<"El Disco En "<<Path<<"Presenta Errores, Se Recomienda Restaurarlo "<<std::endl;
            break;
        }else if(Lectura!='1' && Lectura!='0'){
            std::cout<<"El Disco En "<<Path<<"Presenta Errores, Se Recomienda Restaurarlo '"<<Lectura<<"'"<<std::endl;
            break;
        }
    }
    fclose(f);


    return Out;

}
int FunctionsExt::BloqueLibreConte(SPB *Super, const char *Path){
    /*if(ValPrimeraPos==-1){
        int Retorno=DarPrimeraPos(Super,Path);
        //Poner Primera Posicion
        ValPrimeraPos=Retorno;

        if(Retorno==-1){
            std::cout<<"No Hay Espacio Contiguo Para Colocar El Archivo "<<std::endl;
        }else{
            ValTamanio=0;
        }
        return Retorno;
    }
    ValTamanio++;
    return ValPrimeraPos+(ValTamanio)*(int(sizeof (BAR)));*/
    return BloqueLibre(Super,Path);
}
int FunctionsExt::DarPrimeraPos(SPB *Super, const char *Path){
    int Out=-1;
        FILE *f;
        f=fopen(Path,"r+");
        int Ubi=Super->s_bm_block_start;
        int Tamanio=Super->s_blocks_count;
        //Ubicarse En el BM
        char Lectura;
        int Contador=0;
        for(int i=0;i<Tamanio;i++){
            //Leer Cada Uno
            fseek(f,Ubi+i,SEEK_SET);
            fread(&Lectura,sizeof(Lectura),1,f);
            //Si Libre Sumar 1
            if(Lectura=='0'){
                Contador++;
                //Si Es El Primer '0' Indicar Salida
                if(Contador==1)
                    Out=Super->s_block_start+(i*Super->s_block_size);
                //Si Supera El Espacio Buscado
                if(Contador>=ValTamanio){
                    //Colocar En El Primero
                    Lectura='1';
                    fseek(f,Ubi+i,SEEK_SET);
                    fread(&Lectura,sizeof(Lectura),1,f);
                    break;
                }
            }else{
                //Si Ocupado Reiniciar
                Contador=0;
            }

            //fwrite(&Actualizar,sizeof (Actualizar),1,f);
        }

        fclose(f);

        return Out;
}

//DEL
bool FunctionsExt::EliminarDirectos(SPB *Super,int Comienzo, const char *PathReal, int Tipo, int Elim){
    FILE *f;
    f=fopen(PathReal,"r+");
    BCA Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
    std::string Concatenar="";
    for(int i=0;i<4;i++){
        CON Contenido=Carpeta.content[i];

        if(Contenido.b_inodo!=-1){
            if(Contenido.b_inodo==Elim && Tipo ==4){
                Carpeta.content[i].b_inodo=-1;
                strcpy(Carpeta.content[i].b_name,"*********");
                f=fopen(PathReal,"r+");
                fseek(f,Comienzo,SEEK_SET);
                fwrite(&Carpeta,sizeof(Carpeta),1,f);
                fclose(f);
                return true;
            }

            if(Tipo==1){
                EliminarBloqueContenido(Super,Contenido.b_inodo,PathReal);
            }else if(Tipo==0){
                EliminarInodo(Super,Contenido.b_inodo,PathReal);
            }
        }
    }
    if(Tipo!=4)
    LiberarBloque(Super,PathReal,Comienzo);
    return false;
}
bool FunctionsExt::EliminarBloqueContenido(SPB *Super,int Comienzo,const char *PathReal){
    LiberarBloque(Super,PathReal,Comienzo);
    return true;
}
bool FunctionsExt::EliminarIndirectos(SPB *Super,int Nivel, int NivelActual, int Comienzo,  const char *PathReal, int Tipo, int Elim){
    if(Nivel==NivelActual){
        return EliminarDirectos(Super,Comienzo,PathReal,Tipo,Elim);
    }
    FILE *f;
    f=fopen(PathReal,"r+");
    BAP Apunta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Apunta,sizeof(Apunta),1,f);
    fclose(f);
    for(int i=0;i<16;i++){
        int Valor=Apunta.b_pointers[i];
        if(Valor!=-1){
            EliminarIndirectos(Super,Nivel,NivelActual+1,Valor,PathReal,Tipo,Elim);
        }
    }
    if(Tipo!=4)
    LiberarBloque(Super,PathReal,Comienzo);

    return true;
}
bool FunctionsExt::EliminarInodo(SPB *Super,int Comienzo, const char *PathReal){
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
    int Tipo=0;
    if(Carpeta.i_type=='1')
        Tipo=1;
    for(int i=0;i<12;i++){
        int Apuntador=Carpeta.i_block[i];
        if(Apuntador!=-1){
            EliminarIndirectos(Super,0,0,Apuntador,PathReal,Tipo,10);
        }
    }

    for(int i=0;i<3;i++){
        int Apuntador=Carpeta.i_block[i+12];
        if(Apuntador!=-1){
            EliminarIndirectos(Super,1+i,0,Apuntador,PathReal,Tipo,10);
        }
    }

    LiberarInodo(Super,PathReal,Comienzo);
    return true;
}

//Eliminar Archivo
bool FunctionsExt::EliminarArchivoCarpetaPadre(SPB *Super, int Comienzo, const char *PathVirtual, const char *PathReal, int Hijo){
    int Actual=BuscarPadre(Comienzo,PathVirtual,PathReal);
    if(Actual==-1){
        std::cout<<"No Se Encontro Una Carpeta O Archivo En "<<PathVirtual<<" No Se Pudo Borrar"<<std::endl;
        return false;
    }


    FILE *f;
    f=fopen(PathReal,"r+");
    INO Carpeta;
    fseek(f,Actual,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
    bool Retorno=false;
    for(int i=0;i<12;i++){
        int Apuntador=Carpeta.i_block[i];
        if(Apuntador!=-1){
            Retorno=EliminarIndirectos(Super,0,0,Apuntador,PathReal,4,Hijo);
            if(Retorno)
                return  true;
        }
    }

    for(int i=0;i<3;i++){
        int Apuntador=Carpeta.i_block[i+12];
        if(Apuntador!=-1){
            Retorno=EliminarIndirectos(Super,1+i,0,Apuntador,PathReal,4,Hijo);
            if(Retorno)
                return  true;
        }
    }

    return false;
}
//Aux
void FunctionsExt::DuplicarInodo(INO *Original, INO *Copia){
    Copia->i_gid=Original->i_gid;
    Copia->i_uid=Original->i_uid;
    Copia->i_perm=Original->i_perm;
    Copia->i_size=Original->i_size;
    Copia->i_type=Original->i_type;
    Copia->i_atime=Original->i_atime;
    Copia->i_ctime=Original->i_ctime;
    Copia->i_mtime=Original->i_mtime;
}
void FunctionsExt::CantidadVeces(std::string Contenido){
    int Div = int(Contenido.length())/64;
    if(Contenido.length()%64!=0)
        Div++;
      ValTamanio=Div;
}


//Contenido
//
std::string FunctionsExt::ContenidoArchivoDirectos(INO *Ino, SPB *Super, int PosPadre, const char *PathReal,  std::string Contenido){
    INO Inodo=*Ino;
    for(int i=0;i<12;i++){
        if(IF(Contenido,""))
            return "";

        int Pos=Inodo.i_block[i];

          if(Pos==-1){
            //Crea Bloque Directo Y Se Manda A Crear Carpeta

            int EspacioBloque=BloqueLibre(Super,PathReal);

            if(EspacioBloque==-1){
                std::cout<<"No Se Pudo Crear El Bloque, Insuficiente Tamanio"<<std::endl;
                return Contenido;
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

            std::string Colocacion="";
            std::string Parametro;
            Parametro=ReducirTamanio(&Contenido,64*4);
            Colocacion=ColocarContenidoArchivo(EspacioBloque,Super,PathReal,Parametro);
            if(!IF(Colocacion,"")){
                Contenido=Contenido+Parametro;
            }



        }else{
            //Como Posicion Es Valida Se Prueba Colocar Carpeta
            std::string Valor="";
            std::string Parametro;
            Parametro=ReducirTamanio(&Contenido,64*4);
            Valor=ColocarContenidoArchivo(Pos,Super,PathReal,Parametro);
            if(!IF(Valor,"")){
                Contenido=Contenido+Parametro;
            }
        }
    }
    return Contenido;
}
std::string FunctionsExt::ContenidoArchivoInDirectos(INO *Ino, SPB *Super, int PosPadre, const char *PathReal, const char *PathVirtual,  std::string Contenido){
    INO Inodo=*Ino;
    //Posicion De Un Bloque De Directos Con Alguna Ranura
    for(int i=0;i<3;i++){
        if(IF(Contenido,""))
            return "";

        int Pos=Inodo.i_block[12+i];
        if(Pos==-1){
            //Se Crean Los Bloques Indirectos
            Pos=CrearIndirectos(i+1,0,Super,PathReal);
            if(Pos!=-1 && Pos!=0){
                //Si No Se Puede Crear Retornar
                std::cout<<"Cancelando POR NO PODER CREAR INDIRECTO"<<std::endl;
                return Contenido;
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
            std::string  Valor;
            std::string Parametro;
            Parametro=ReducirTamanio(&Contenido,64*4);
            Valor=ColocarContenidoArchivo(Pos,Super,PathReal,Parametro);
            if(!IF(Valor,"")){
                Contenido=Contenido+Parametro;
            }
        }

    }
    return Contenido;
}
//Liberar
void FunctionsExt::LiberarInodo(SPB *Super, const char *PathReal, int Comienzo){
    FILE *f;
    f=fopen(PathReal,"r+");
    char Actual='0';
    int See=Super->s_bm_inode_start+(Comienzo-Super->s_inode_start)/Super->s_inode_size;
    fseek(f,See,SEEK_SET);
    fwrite(&Actual,sizeof(Actual),1,f);
    fclose(f);
}
void FunctionsExt::LiberarBloque(SPB *Super, const char *PathReal,int Comienzo){
    FILE *f;
    f=fopen(PathReal,"r+");
    char Actual='0';
    int See=Super->s_bm_block_start+(Comienzo-Super->s_block_start)/Super->s_block_size;
    fseek(f,See,SEEK_SET);
    fwrite(&Actual,sizeof(Actual),1,f);
    fclose(f);

}
//Busqueda
int FunctionsExt::BuscarPadre(int Comienzo, std::string PathVirtual, const char *PathReal){
    int Num=BuscarInodos(Comienzo,PathVirtual,PathReal);
    if(Num==-2){
        std::cout<<"OPTI"<<std::endl;
        return-1;
    }
    return Num;

}
//BSUCA EN EL INODO
int FunctionsExt::BuscarInodos(int Comienzo, std::string PathVirtual, const char *PathReal){
    //std::cout<<"InodoPathVirtual"<<"  "<<PathVirtual<<"     "<<Comienzo<<std::endl;

    int Contador=CantidadBarras(PathVirtual);


    FILE *f;
    f=fopen(PathReal,"r+");
    INO Inodo;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Inodo,sizeof(Inodo),1,f);
    fclose(f);






    if(Contador==0){
        return Comienzo;
    }

    if(Inodo.i_type=='1'){

        return Comienzo;
    }
    for(int i=0;i<12;i++){
        int Comprobar=Inodo.i_block[i];



        if(Comprobar!=-1){
            Comprobar =BuscarIndirectos(nullptr,0,0,Comprobar,PathVirtual,PathReal,1);

            if(Comprobar==-2)
                return -2;

            if(Comprobar!=-1){
                //Es El Padre El Inodo Actual
                if(Comprobar==1)
                    return Comienzo;
                else
                    return Comprobar;
            }
        }

    }


    for(int i=0;i<3;i++){
        int Busq=BuscarIndirectos(nullptr,1+i,0,Inodo.i_block[12+i],PathVirtual,PathReal,1);
        if(Busq==-2)
            return -2;

        if(Busq!=-1)
            return Comienzo;
    }

    return -1;
}
int FunctionsExt::BuscarDirectos(int Comienzo, std::string PathVirtual, const char *PathReal,int Tipo){


    FILE *f;
    f=fopen(PathReal,"r+");
    BCA Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
    std::string NombreTotal =PathVirtual.substr(1);

    std::string NombreActual=Reducir(&NombreTotal);

    for(int i=0;i<4;i++){

        CON Contenido=Carpeta.content[i];

        //std::cout<<PathVirtual<<"  "<<Contenido.b_inodo<<std::endl;

        std::string Nombre=Contenido.b_name;
        Nombre=Nombre.substr(0,12);
        // std::cout<<Nombre<<"----"<<PathVirtual<<"****"<<Comienzo<<"****"<<Contenido.b_inodo<<std::endl;
        if(Tipo==1){
            if(CantidadBarras( PathVirtual)==1){
                return 1;
            }

            if(IF(Nombre,NombreActual) && Contenido.b_inodo!=-1){
                int Busq=BuscarInodos(Contenido.b_inodo,NombreTotal,PathReal);
                if(Busq==-1 || Busq==-2){

                    return -2;

                }

                return Busq;
            }
        }else if(Tipo==2)
            //Tipo 2 Pra Posocion Del Bloque Directo, Al crear Carpetas
        {
            if(Contenido.b_inodo==-1)
                return Comienzo;
        }else if(Tipo==3 && Contenido.b_inodo!=-1){

            if(IF(Nombre,PathVirtual)){

                return Contenido.b_inodo;
            }
        }
    }
    /*if(Tipo==1){

    //Siginifca que el Inodo Anterior Era El Padre
    if(Fun->IF(NombreTotal,""))
        return 1;
    }*/

    return -1;
}
int FunctionsExt::BuscarIndirectos(SPB *Super,int Nivel, int NivelActual, int Comienzo, std::string PathVirtual, const char *PathReal,int Tipo){



    if(Nivel==NivelActual){
        return BuscarDirectos(Comienzo,PathVirtual,PathReal,Tipo);
    }
    FILE *f;
    f=fopen(PathReal,"r+");
    BAP Apunta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Apunta,sizeof(Apunta),1,f);
    fclose(f);
    for(int i=0;i<16;i++){
        int Valor=Apunta.b_pointers[i];
        if(Valor!=-1){
            Valor=BuscarIndirectos(Super,Nivel,NivelActual+1,Valor,PathVirtual,PathReal,Tipo);
            if(Valor==-2){
                return -2;
            }

            if(Valor!=-1){
                return Valor;
            }
        }else if(Tipo==2){
            int BloqueDirectoNuevo=BloqueLibre(Super,PathReal);
            FILE *f;
            f=fopen(PathReal,"r+");
            fseek(f,BloqueDirectoNuevo,SEEK_SET);
            BCA BloqueDirecto;
            IniciarBloqueCarpeta(&BloqueDirecto);
            fwrite(&BloqueDirecto,sizeof (BloqueDirecto),1,f);
            fclose(f);
            //Crear El Directo
            return BloqueDirectoNuevo;

        }
    }
    return -1;
}

//Permisos
std::string FunctionsExt::PermisosEnElPadre(int Comienzo, const char *PathReal,IUG Permiso){
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);

    int Selec=3;
    if(Carpeta.i_uid==Permiso.Uid){
        Selec=1;
    }else if(Carpeta.i_gid==Permiso.Gid){
        Selec=2;
    }

    if(Permiso.Uid==1 || Permiso.Gid==1)
        return "111";

    std::string Comparar="";

    std::string Num = std::to_string(Carpeta.i_perm);
    if(Num.length()==10){
        switch (Selec) {
        case 1:{
            Comparar=Comparar+Num.data()[1]+Num.data()[2]+Num.data()[3];
            break;
        }
        case 2:{
            Comparar=Comparar+Num.data()[4]+Num.data()[5]+Num.data()[6];
            break;
        }
        case 3:{
            Comparar=Comparar+Num.data()[7]+Num.data()[8]+Num.data()[9];
            break;
        }

        }

    }else
        std::cout<<"Error En La Particion, Permisos n Leer Correctamente"<<std::endl;
    return Comparar;
}
bool FunctionsExt::TienePermiso(int Comienzo, const char *PathReal, std::string Info,IUG Permiso){
    std::string PermisoCarpeta=PermisosEnElPadre(Comienzo,PathReal,Permiso);
    if(IF(PermisoCarpeta,""))
        return false;



    for(int i=0;i<3;i++){
        char Com=PermisoCarpeta.data()[i];
        char Per=Info.data()[i];
        if(Per=='1'){


            if(Com=='0'){

                return false;
            }
        }
    }

    return true;
}
//Nombres
std::string FunctionsExt::PathArchivo(const char *PathVirtual){
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
        }
        s.erase(0, pos + delimiter.length());
    }

    return Escritura;
}
std::string FunctionsExt::NombreACrear(const char *PathVirtual){
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
        }
        s.erase(0, pos + delimiter.length());
    }

    return s;
}
//Cuenta Cuantas '/' Hay en un string
int FunctionsExt::CantidadBarras(std::string Path){
    int Contador=0;
    int Longitud=Path.length();
    for(int i=0;i<Longitud;i++){
        if('/'==Path.at(i)){
            Contador++;
        }
    }
    return Contador;
}
//BuscarActual
int FunctionsExt::BuscarActual(int Comienzo, std::string PathVirtual, const char *PathReal){
    PathVirtual=PathVirtual.substr(1);
    int Busq=BuscarPadre(Comienzo,(PathVirtual),PathReal);
    if(Busq==-1)
        return Busq;
    std::string Nombre=NombreACrear(PathVirtual.data());
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
    int Punteros=-1;

    for(int i=0;i<12;i++){
        int Apuntador=Carpeta.i_block[i];
        if(Apuntador!=-1){
             Punteros=BuscarIndirectos(nullptr,0,0,Apuntador,Nombre,PathReal,3);
             if(Punteros!=-1)
                 return Punteros;
        }
    }
    for(int i=0;i<3;i++){
        int Apuntador=Carpeta.i_block[12+i];
        if(Apuntador!=-1){
            Punteros=BuscarIndirectos(nullptr,1+i,0,Apuntador,Nombre,PathReal,3);
            if(Punteros!=-1)
                return Punteros;
        }
    }



    return  Punteros;
}
int FunctionsExt::CrearIndirectos(int Nivel, int NivelActual, SPB *Super, const char *PathReal){
    if(Nivel==NivelActual)
        return 0;

    int PosicionPadre=BloqueLibre(Super,PathReal);
    if(PosicionPadre==-1){
        std::cout<<"No Se Puedo Encontrar Un Bloque Libre"<<std::endl;
        return -1;
    }
    BAP Apuntador;
    for(int i=0;i<16;i++){
         Apuntador.b_pointers[i]=-1;
    }
    for(int i=0;i<16;i++){
        int Hijo=CrearIndirectos(Nivel,NivelActual+1,Super,PathReal);
        if(Hijo==-1){
            std::cout<<" Se Llego Al Limite De Apuntadores "<<std::endl;
            break;
        }else if(Hijo!=0){
            //Posicion De Cada Hijo;
            Apuntador.b_pointers[i]=Hijo;
        }
    }

    FILE *f;
    f=fopen(PathReal,"r+");
    fseek(f,PosicionPadre,SEEK_SET);
    fwrite(&Apuntador,sizeof (Apuntador),1,f);
    fclose(f);
    return PosicionPadre;

}

//REDUCIR TAMANIO String
std::string FunctionsExt::ReducirTamanio(std::string *Ingreso, int Num){
    int Longitud=Ingreso->length();
    std::string Copia="";
    if(Longitud>Num){
       Copia=Ingreso->substr(0,Num);
       *Ingreso=Ingreso->substr(Num);
       return Copia;
    }else{
        Copia=*Ingreso;
        *Ingreso="";
        return Copia;
    }
}
//Redux
std::string FunctionsExt::Reducir(std::string *Entrada){

    int Longitud=int(Entrada->length());
    for(int i=0;i<Longitud;i++){
        char Letra=char((*Entrada)[i]);
        if(Letra=='/'){
            std::string  Salida=Entrada->substr(0,i);
            *Entrada=Entrada->substr(i);

            return Salida;
        }
    }
    std::string Salida=*Entrada;
    *Entrada="";
    return Salida;
}
std::string FunctionsExt::ColocarContenidoArchivo(int PosDirecto, SPB *Super, const char *PathReal, std::string Contenido){
    int Longitud=Contenido.length();
    if(Longitud>64*4){
        std::cout<<"STRING MUY GRANDFEE"<<std::endl;
        return Contenido;
    }

    FILE *f;
    f=fopen(PathReal,"r+");
    fseek(f,PosDirecto,SEEK_SET);
    BCA Directo;
    fread(&Directo,sizeof(Directo),1,f);
    for(int z=0;z<4;z++){
        if(IF(Contenido,""))
            break;
        int Pos=Directo.content[z].b_inodo;

        if(Pos==-1){

            //Llenar Nombre
            std::string Sub="......";

            strcpy(Directo.content[z].b_name,Sub.c_str());
            //Inodo Nuevo
            //int Libre = BloqueLibre(Super,PathReal);
            int Libre = BloqueLibreConte(Super,PathReal);
            Directo.content[z].b_inodo=Libre;

            //Se Reduce
            std::string ValorBloque=ReducirTamanio(&Contenido,64);
            //Se Coloca Contenido De Archivo
            fseek(f,Libre,SEEK_SET);
            BAR ConteArchivo;
            //std::cout<<"Se Coloco Contenido "<<ValorBloque <<" en "<<Libre <<" BORRAR MENSAJE   "<<z<<std::endl;
            strcpy(ConteArchivo.b_content,ValorBloque.c_str());
            fwrite(&ConteArchivo,sizeof (ConteArchivo),1,f);
            //Se Actualizo El Apuntador Directo
            fseek(f,PosDirecto,SEEK_SET);
            fwrite(&Directo,sizeof (Directo),1,f);




        }else{
            f=fopen(PathReal,"r+");
            BAR Cont;
            fseek(f,Pos,SEEK_SET);
            fread(&Cont,sizeof(Cont),1,f);
            std::string Almacenado=Cont.b_content;
            int Llenar=64-(Almacenado.length());



            if(Llenar>0){
                Almacenado=Almacenado+ReducirTamanio(&Contenido,Llenar);


            strcpy(Cont.b_content,Almacenado.c_str());

            fseek(f,Pos,SEEK_SET);
            fwrite(&Cont,sizeof(Cont),1,f);
            }
        }

    }

    fclose(f);
    //fwrite(&Apuntador,sizeof (Apuntador),1,f);


    return Contenido;
}
//Contenido
//Verificar Si Tiene Permisos
bool FunctionsExt::PermisoDirectos(int Comienzo, const char *PathReal,IUG Permiso){
    FILE *f;
    f=fopen(PathReal,"r+");
    BCA Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);
    std::string Concatenar="";
    bool Comp=true;
    for(int i=0;i<4;i++){
        CON Contenido=Carpeta.content[i];

        if(Contenido.b_inodo!=-1){
            Comp=PermisoInodo(Contenido.b_inodo,PathReal,Permiso);
            if(Comp==false)
                return false;
        }
    }
    return true;
}
bool FunctionsExt::PermisoIndirectos(int Nivel, int NivelActual, int Comienzo,  const char *PathReal,IUG Permiso){
    if(Nivel==NivelActual){
        return PermisoDirectos(Comienzo,PathReal,Permiso);
    }
    FILE *f;
    f=fopen(PathReal,"r+");
    BAP Apunta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Apunta,sizeof(Apunta),1,f);
    fclose(f);
    bool Com=true;
    for(int i=0;i<16;i++){
        int Valor=Apunta.b_pointers[i];
        if(Valor!=-1){
            Com=PermisoIndirectos(Nivel,NivelActual+1,Valor,PathReal,Permiso);
            if(Com==false)
                return false;
        }
    }

    return true;
}
bool FunctionsExt::PermisoInodo(int Comienzo, const char *PathReal,IUG Permiso){
    FILE *f;
    f=fopen(PathReal,"r+");
    INO Carpeta;
    fseek(f,Comienzo,SEEK_SET);
    fread(&Carpeta,sizeof(Carpeta),1,f);
    fclose(f);

    if(TienePermiso(Comienzo,PathReal,"010",Permiso)==false)
        return false;

    if(Carpeta.i_type=='1')//Es Carpeta No Hay Mas
        return true;

    bool Comp=true;
    for(int i=0;i<12;i++){
        int Apuntador=Carpeta.i_block[i];
        if(Apuntador!=-1){
            Comp=PermisoIndirectos(0,0,Apuntador,PathReal,Permiso);
            if(Comp==false)
                return false;
        }
    }

    for(int i=0;i<3;i++){
        int Apuntador=Carpeta.i_block[i+12];
        if(Apuntador!=-1){
            Comp=PermisoIndirectos(1+i,0,Apuntador,PathReal,Permiso);
            if(Comp==false)
                return false;
        }
    }
    return true;
}

