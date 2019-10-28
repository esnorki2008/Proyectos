#ifndef RECUPERACION_H
#define RECUPERACION_H
#include "functions.h"
#include <queue>
#include "structsext.h"
class Recuperacion
{
public:
    void RecuMKGRP(int Comienzo, const char *Path,const char*Nombre);
    void IniciarJOUR(int Comienzo,const char *Path);
    std::queue<JOR> ListaDeOperaciones(int Comienzo,const char *Path);
    Recuperacion();
private:
    void NuevoRegistro(int Comienzo, const char *Path, JOR Recu);
};

#endif // RECUPERACION_H
