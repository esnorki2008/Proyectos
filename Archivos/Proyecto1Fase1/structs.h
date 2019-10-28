#ifndef STRUCTS_H
#define STRUCTS_H
#include <time.h>
#include <string.h>
#include<QDebug>
#include <iostream>
#include <stdio.h>
typedef struct  Identificador{
    int Gid=-5;
    int Uid=-5;
}IUG;

typedef struct  Grupo{
    int Gid;
    std::string Tipo;
    std::string Grupo;
}GRU;

typedef struct  Usuario{
    int Uid;
    std::string Tipo;
    GRU *Grupo;
    std::string Usuario;
    std::string Contrasenia;
}USU;

typedef struct  Avaliable{
    int Begin;
    int End;
    int Size;
}AVA;
typedef struct  ReportsDita{
    int Begin;
    int End;
    int Size;
    int Type;
    std::string Info;
}RDI;
typedef struct  ExtendedBootRecord {
    char part_status;
    char part_fit[2];
    int part_start;
    int part_size=0;
    int part_next=-1;
    char part_name[16];
}EBR ;

typedef struct Partition{
    char part_status='f';
    char part_type;
    char part_fit[2];
    int part_start=0;
    int part_size=0;
    char part_name[16];
}PAR ;
typedef struct MasterBootRecord{
    int mbr_tamano;
    struct tm  mbr_fecha_creacion;
    int mbr_disk_signature;
    char disk_fit[2];
    PAR mbr_partition[4];
}MBR ;

typedef struct Montado{
    PAR Particion;
    EBR Logica;
    int Numero;
    bool EsLogica;
}MOU;


typedef struct Aleatorios{
    int Num;
    bool Siguiente;
    int Pos;
}ALE;

class STRUCTS
{
public:
    STRUCTS();
};

#endif // STRUCTS_H
