/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author usuario
 */
public class EnlaceEquipos {
    public static NodoEquipos nodo;
    public static int Contador=0,Repetidos=0;
    public static int CantidadEquipos=0;
    static NodoEquipos Recien;
    static NodoEquipos Imprimir;
    
    //Creado publico y estatico
    public static NodoEquipos SacarEquipos;
    public static NodoEquipos Cabeza,Sacar,Inicio;

    public void Agregar(int ID, String Nombre,String Fecha,int Mundiales) {
        NodoEquipos nuevo = new NodoEquipos(ID, Nombre,Fecha,Mundiales);

        //Cabeza y primero del nodo 
        if (nodo == null) {
            CantidadEquipos++;
            Contador++;
            Cabeza=nuevo;
            Inicio=nuevo;
            nodo = nuevo;
            //Nodo que copia el primero en ser agregado
            SacarEquipos=nuevo;
            //
            nodo.Siguiente = null;
            nodo.Anterior = null;
        } else {
            while (true) {

                if (nodo != null) {
                    Recien = nodo;
                    nodo = nodo.Siguiente;
                } else {
                    //Nodo Anterior Creado

                    Recien.Siguiente = nuevo;
                    //Nodo Recien Creado 
                    CantidadEquipos++;
                    nodo = nuevo;
                    nodo.Anterior = Recien;
                    Contador++;
                    //nodo.Siguiente=null;
                    //Siguiente null porque no es circular
                    break;
                    
                }

            }

        }Imprimir=nodo;

    }

    public void Mostrar() {
        
        
        while (Imprimir.Anterior != null) {
            Imprimir=Imprimir.Anterior;
        }
        
        
        
        
        while (Imprimir != null) {

            System.out.println("------------------------------------------");
            System.out.println("Nodo Actual " + Imprimir);
            System.out.println("Nodo Siguiente " + Imprimir.Siguiente);
            System.out.println("Nodo Anterior " + Imprimir.Anterior);
            Imprimir.Imprimir();
            Imprimir = Imprimir.Siguiente;
        }

        

    }
    
    public boolean Iguales(int a){
        NodoEquipos Igual=nodo;
        while(Igual!=null){
            if(Igual.Num==a){Repetidos++;return false;
            
            }
            Igual=Igual.Anterior;
            
        }
        return true;
    }
    
     public String DevolverEquipo(int ID){
        Sacar=Cabeza;
        while(Sacar!=null){
        
        if(Sacar.Num==ID){return Sacar.Dato;}
        
        Sacar= Sacar.Siguiente;
        }
        return "NO";
    
    }
     
     public NodoEquipos SacarSiguiente(NodoEquipos hola){
    if(hola==null){return Inicio;}else{
        
        hola =hola.Siguiente;
        
    }
    
    
    return hola;
   
   } 
     
     
     
     
}
