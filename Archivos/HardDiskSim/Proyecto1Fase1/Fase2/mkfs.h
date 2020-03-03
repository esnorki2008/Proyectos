#ifndef MKFS_H
#define MKFS_H
#include<QDebug>
#include <iostream>
#include <time.h>
#include "structsext.h"
#include "functions.h"

class MKFS
{
public:
    MKFS(int ComienzoParticion,int TamanioParticion,int TamanioStruct,std::string Direcc,int Tipo);
private:

    //
    Functions *Fun;

    //Hacer Formato
    void EstructurarFormatoEXT3(int ComienzoParticion,int TamanioParticion,int TamanioStruct,std::string Direcc,int Tipo);



};

#endif // MKFS_H
