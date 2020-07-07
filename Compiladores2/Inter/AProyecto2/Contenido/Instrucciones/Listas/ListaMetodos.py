from AProyecto2.Contenido.Instrucciones.InstruccionAbstracta import Instruccion

class ListaMetodos(Instruccion):
    contenido : [] = None

    def __init__(self, contenido : []):
        self.contenido = contenido

    def str_arbol(self):
        concatenar = "digraph G { \n"
        expand = "Listado General"
        concatenar += str(id(self)) + "[shape=rect,sides=4,skew=.4,label=\"" + expand + "\"]\n"
        for con in self.contenido:
            concatenar += str(id(self)) + " -> " + str(id(con))  + "\n"
            concatenar += con.str_arbol()
        concatenar += "\n }"
        return concatenar

    def agregar(self,item):
        self.contenido.append(item)

    def ejecutar_3D(self,Tabla):
        if len(self.contenido ) == 0:
            return

        for each in self.contenido:
            each.guardar_struct(Tabla)
        main = self.contenido[-1]
        if main.nombre != "main":
            tp=(0,0)
            Tabla.nuevo_error("Error con Main","El Main No Es La Ultima Declaracion",0,tp)
            for each in self.contenido:
                if each.nombre == "main":
                    main = each
                    break
        from AProyecto2.Contenido.Instrucciones.Listas.ListaInstruccion import ListaInstruccion
        init = "main:"
        Tabla.nuevo_codigo_3d(init)
        init = "$s0=array();#pila"
        Tabla.nuevo_codigo_3d(init)
        init = "$s1=array();#retorno"
        Tabla.nuevo_codigo_3d(init)
        init = "$sp=-1;#null ptr"
        Tabla.nuevo_codigo_3d(init)
        init = "$ra=-1;#null ptr"
        Tabla.nuevo_codigo_3d(init)
        init = "$v=0;#Ptr Retornos"
        Tabla.nuevo_codigo_3d(init)
        init = "$s2=array();"
        Tabla.nuevo_codigo_3d(init)
        init = "$sp1=-1;"
        Tabla.nuevo_codigo_3d(init)
        init = "$s3=array();"
        Tabla.nuevo_codigo_3d(init)
        for each in self.contenido:
            if isinstance(each,ListaInstruccion):
                each.ejecutar_3D(Tabla)
        main.ejecutar_3D(Tabla)

        for each in self.contenido:
            if each.nombre != "main" and not isinstance(each,ListaInstruccion):
                each.ejecutar_3D(Tabla)

        #return self.contenido


