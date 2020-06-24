/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import Visuales.MenuUsuarios;

/**
 *
 * @author Esnorki
 */
public class EnlaceRepetidas {
    public static int Repetidas=0;
    public static NodoRepetidas Cabeza, Inicio, Cuerpo, Copia;
    public void Agregar(int a, int b, String c, int d) {
        int Romper = 0;

        NodoRepetidas nuevo = new NodoRepetidas(a, b, c, d);

        if (Cabeza == null) {
            Repetidas++;
            Cabeza = nuevo;
            System.out.println("Nodo Cabeza   "+Cabeza);
            Inicio = nuevo;
            Cuerpo = Cabeza;
        } else {        

            while (true) {
                if (Cuerpo != null) {
                    
                   

                    Copia=Cuerpo;
                    Cuerpo = Cuerpo.Siguiente;
                    if(Cuerpo==Cabeza){Cuerpo=null;}

                     
                } else {
                 Repetidas++;
   
                 //Dando Valores Al Nodo Viejo
                 Copia.Siguiente=nuevo;
                                  

                 //Dando Valores Al Nuevo Nodo
                 Cuerpo=nuevo;
                 Cuerpo.Anterior=Copia;

                 Cuerpo.Siguiente=Cabeza;
                 
                 System.out.println("LLego hasta aqui");
                 System.out.println(Cabeza.Anterior);
                 System.out.println(nuevo);

                 Cabeza.Anterior=Cuerpo;
                 
                 //ROmper
                 break;
                }
                

            }
        }

    }
    
    public void Imprimir(){
    NodoRepetidas Imprimir=Cuerpo.Siguiente;
    while(Imprimir.Siguiente!=Cabeza){
        System.out.println("///////////////////////////////////////////////////////");
        System.out.println("Nodo Actual "+Imprimir);
        System.out.println("Nodo Anterior "+Imprimir.Anterior);
        System.out.println("Nodo Siguiente "+Imprimir.Siguiente);
        Imprimir=Imprimir.Siguiente;
    }
    
    
    }
    
    
     public NodoRepetidas Existencia(int IDesta){
        NodoRepetidas Hola=Inicio;
        int Romper=0;
        
        
        while((Hola!=null)&&Romper<1){
            if(Hola.Siguiente==Cabeza){Romper++;}
            if(Hola.IDEstampas==IDesta&&(Hola.IDUsuario==MenuUsuarios.IDUsuario)){return Hola;}
            Hola=Hola.Siguiente;
        }
        
        
    return null;
    }
     
    public NodoRepetidas SiguienteNodo(NodoRepetidas Nodo){
        
        
        if(Nodo==null){Nodo=Inicio;}
        
        
        int Romper=0;
        
        
        while((Nodo!=null)&&Romper<1){
            if(Nodo.Siguiente==Cabeza){Romper++;}
            if((Nodo.IDUsuario==MenuUsuarios.IDUsuario)){return Nodo;}
            Nodo=Nodo.Siguiente;
    
            }
        return null;//???

    }
    
     public NodoRepetidas SiguienteNodo2(NodoRepetidas Nodo){
        NodoRepetidas Ingresado=Nodo;
        
        if(Nodo==null){Nodo=Cuerpo.Siguiente;}
        
        int Cant=0;
        int Romper=0;
        
        
        while((Nodo!=null)&&Romper<2){
            if(Nodo.Siguiente==Cabeza){Romper++;}
            Cant=Nodo.Cantidad;
            if(Nodo.Pegada.equals("1")){Cant=Nodo.Cantidad-1;}
            if((Nodo.IDUsuario == MenuUsuarios.IDUsuario)&&(Nodo!=Ingresado)&&(Cant>0)){
                return Nodo;
            }
            Nodo=Nodo.Siguiente;
    
            }
        return null;//???

    }
     
     
     public NodoRepetidas SiguienteNodo3(NodoRepetidas Nodo){
        
        
        if(Nodo==null){Nodo=Inicio;}
        
        
        int Romper=0;
        NodoRepetidas Ingre=Nodo;
        
        while((Nodo!=null)&&Romper<1){
            if(Nodo.Siguiente==Cabeza){Romper++;}
            if((Nodo.IDUsuario==MenuUsuarios.IDUsuario)&&(Nodo!=Ingre)){return Nodo;}
            Nodo=Nodo.Siguiente;
    
            }
        return null;//???

    }
     
    public NodoRepetidas AnteriorNodo(NodoRepetidas Nodo){
        
        
        if(Nodo==null){Nodo=Inicio;}
        
        
        int Romper=0;
        NodoRepetidas Ingre=Nodo;
        
        while((Nodo!=null)&&Romper<1){
            if(Nodo.Anterior==Cabeza){Romper++;}
            if((Nodo.IDUsuario==MenuUsuarios.IDUsuario)&&(Nodo!=Ingre)&&(Nodo.Pegada.equals("0"))){return Nodo;}
            Nodo=Nodo.Anterior;
    
            }
        return null;//???

    } 
     
}
