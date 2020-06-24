package Clases;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author usuario
 */
public class Nodo {

    public int Num;
    public String Dato;
    Nodo Siguiente, Anterior;

    Nodo(int n, String D) {
        this.Num = n;
        this.Dato = D;
    }

    public void Imprimir() {
        System.out.println(Num + "    " + Dato);

    }

}

class Enlace {

    Nodo nodo;
    Nodo Recien;
    Nodo Imprimir;

    public void Agregar(int a, String b) {
        Nodo nuevo = new Nodo(a, b);

        //Cabeza y primero del nodo :D
        if (nodo == null) {

            nodo = nuevo;
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

                    nodo = nuevo;
                    nodo.Anterior = Recien;

                    //nodo.Siguiente=null;
                    //Siguiente null porque no es circular
                    break;

                }

            }

        }

    }

    public void Mostrar() {
        
        
        while (nodo.Anterior != null) {
            nodo=nodo.Anterior;
        }
        
        
        
        
        while (nodo != null) {

            System.out.println("------------------------------------------");
            System.out.println("Nodo Actual " + nodo);
            System.out.println("Nodo Siguiente " + nodo.Siguiente);
            System.out.println("Nodo Anterior " + nodo.Anterior);
            nodo.Imprimir();
            nodo = nodo.Siguiente;
        }

        

    }

}
