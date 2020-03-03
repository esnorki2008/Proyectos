#include "mkfs.h"

MKFS::MKFS(int ComienzoParticion,int TamanioParticion,int TamanioStruct,std::string Direcc,int Tipo)
{
    Fun=new Functions();
    EstructurarFormatoEXT3(ComienzoParticion,TamanioParticion,TamanioStruct,Direcc,Tipo);
}
void MKFS::EstructurarFormatoEXT3(int ComienzoParticion, int TamanioParticion, int TamanioStruct, std::string Direcc,int Tipo){
    const char*Path =Direcc.data();
    int Cantidad= CalcularCantidad(TamanioParticion);
    int ComienzoEscritura=ComienzoParticion+TamanioStruct;
    //Tipo 1 EXT2

    SPB Super=LlenarSuperBloque(1,ComienzoEscritura,Cantidad);

    FILE *f;
    f=fopen(Path,"r+");
    fseek(f,ComienzoEscritura,SEEK_SET);
    //Escribir El Super Bloque
    fwrite(&Super,sizeof (SPB),1,f);
    fclose(f);
    //Llenar BitmapInodo
    LlenarVacio(Super.s_bm_inode_start,Cantidad,'0',Path);
    //Llenar BitmapBloque
    LlenarVacio(Super.s_bm_block_start,Cantidad*3,'0',Path);
    //Llenar Inodo
    LlenarVacio(Super.s_inode_start,Cantidad*int(sizeof (INO)),'I',Path);
    //Llenar Bloque
    LlenarVacio(Super.s_block_start,Cantidad*3*int(sizeof (BAP)),'B',Path);
    /*fread(&r,sizeof(MBR),1,f);*/
    int PrimerInodo=InodoLibre(&Super,Path);
    int PrimerBloque=BloqueLibre(&Super,Path);
    f=fopen(Path,"r+");
    //Escribir El Primer Inodo
    fseek(f,PrimerInodo,SEEK_SET);
    INO Inodo;
    int i_uid=1;
    int i_gid=1;
    int i_size= 0;
    IniciarInodo(&Inodo,i_uid,i_gid,i_size,PrimerBloque,'0',111111);
    fwrite(&Inodo,sizeof (Inodo),1,f);
    //Escribir EL Primer Bloque
    fseek(f,PrimerBloque,SEEK_SET);
    BCA Bloque;
    IniciarBloqueCarpeta(&Bloque);
    fwrite(&Bloque,sizeof (Bloque),1,f);
    fclose(f);

    CrearArchivoSimple(&Super,PrimerInodo,"/users.txt",Path,"1,G,root\n1,U,root,root,123\n");
    Recuperacion *Recu= new Recuperacion();
    if(Tipo==1)
    Recu->IniciarJOUR(ComienzoEscritura,Path);
}

void MKFS::IniciarBloqueCarpeta(BCA *Bloque){
    for(int i=0;i<4;i++){
        Bloque->content[i].b_inodo=-1;
        for(int j=0;j<12;j++){
        Bloque->content[i].b_name[j]='*';
        }
    }
}
void MKFS::IniciarInodo(INO *Inodo, int i_uid, int i_gid, int i_size, int PrimerBloque, char Tipo,int Perm){
    Inodo->i_uid=i_uid;
    Inodo->i_gid=i_gid;
    Inodo->i_size=i_size;
    Fun->Fecha(&Inodo->i_atime);
    Fun->Fecha(&Inodo->i_ctime);
    Fun->Fecha(&Inodo->i_mtime);
    Inodo->i_block[0]=PrimerBloque;
    for(int i=1;i<15;i++){
        Inodo->i_block[i]=-1;
    }
    Inodo->i_type=Tipo;
    Inodo->i_perm=2110110010;
}
void MKFS::LlenarVacio(int Begin, int Size, char Character,const char *Path){
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

int MKFS::CalcularCantidad(int Tamanio){

    int PesoEstructuras=0;
    //Tipo  1 EXT2  Tipo 2 EXT3
    PesoEstructuras=4+3*int(sizeof (BCA))+int(sizeof (INO))+int(sizeof (JOR));
    Tamanio = Tamanio-int(sizeof (SPB));
    int Sal=Tamanio/PesoEstructuras;
    //std::cout<<"QQ "<<Tamanio <<"   "<<Tamanio%PesoEstructuras<<std::endl;
    return Sal;
}
SPB MKFS::LlenarSuperBloque(int Tipo,int Comienzo,int Cantidad){
    SPB Nuevo;
    //TipoDeFormato
    Nuevo.s_filesystem_type=Tipo;
    //Cantidad Inodos
    Nuevo.s_inodes_count=Cantidad;
    //Cantidad Bloques
    Nuevo.s_blocks_count=Cantidad*3;
    //Cantidad Inodos Libre
    Nuevo.s_free_inodes_count=Cantidad;
    //Cantidad Bloques Libre
    Nuevo.s_free_blocks_count=Cantidad*3;
    //Fecha Montado
    Fun->Fecha(&Nuevo.s_mtime);
    //Fecha Desmontado
    Fun->Fecha(&Nuevo.s_umtime);
    //VecesMontado
    Nuevo.s_mnt_count=0;
    //NumeroMagico
    Nuevo.s_magic=0xEF53;
    //Tamaño Inodo
    Nuevo.s_inode_size=int(sizeof (INO));
    //Tamaño Bloque
    Nuevo.s_block_size=int(sizeof (BAP));
    //Primer Inodo Libre
    //Primer Bloque Libre
    //Inicio BMInodo
    Nuevo.s_bm_inode_start=Comienzo+int(sizeof (SPB))+Cantidad*int(sizeof (JOR));
    //Inicio BMBloque
    Nuevo.s_bm_block_start=Nuevo.s_bm_inode_start+Cantidad;
    //Inicio Inodo
    Nuevo.s_inode_start=Nuevo.s_bm_block_start+Cantidad*3;
    Nuevo.s_first_ino=Nuevo.s_inode_start;
    //Inicio Bloque
    Nuevo.s_block_start= Nuevo.s_inode_start+Cantidad*(int(sizeof (INO)));
    Nuevo.s_first_blo=Nuevo.s_block_start;
    return Nuevo;
}
