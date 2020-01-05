package GUI;


import Estructuras.Lineales.ListaCircular;
import Estructuras.Lineales.ListaDoble;
import Estructuras.Matriz;
import Estructuras.NoLineales.ABB;
import Estructuras.NoLineales.ArbolAVL;
import Nodos.Node;
import Nodos.NodoABB;
import java.io.File;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lizandro
 */
public class Maincito {

    /**
     * @param args the command line arguments
     */
    public static void PruebaAVL(){
         ArbolAVL AVL= new ArbolAVL();
        AVL.insertar("10","10");
        AVL.insertar("11","11");
        AVL.insertar("12","12");
        AVL.insertar("13","13");
        AVL.insertar("14","14");
        AVL.insertar("15","15");
        AVL.insertar("16","16");
        AVL.insertar("17","17");
        AVL.insertar("18","18");
        AVL.insertar("19","19");
        AVL.insertar("20","20");
        AVL.insertar("21","21");
        //  AVL.preOrden(AVL.raiz);
        System.out.println(AVL.Graficar());
        //System.out.println( AVL.Buscar(AVL.raiz, "21"));
    }
    public static void PruebaMatriz(){
      Matriz M= new Matriz();
        M.Agregar(1, 1,"");
        M.Agregar(2, 1,"");
        M.Agregar(2, 2,"");
        M.Agregar(3, 3,"");
        M.Agregar(4, 4,"");
        
     //   System.out.println(M.Buscar(1, 2));
     //   System.out.println(M.Buscar(8, 3));
        System.out.println("digraph G{"+M.Graficar()+"}");
    
    }
   /* public static void PruebaCircular(){
      ListaCircular S= new ListaCircular();
        S.Add(new Node(10,"10"));
        S.Add(new Node(98,"98"));
        S.Add(new Node(25,"25"));
        S.Add(new Node(17,"17"));
        S.Add(new Node(27,"27"));
        S.Add(new Node(7,"7"));
        S.Add(new Node(3,"3"));
        S.Add(new Node(18,"18"));
        
        S.Print();
    }
    public static void PruebaDoble(){
      ListaDoble S= new ListaDoble();
        S.Add(new Node(0,"3"));
        S.Add(new Node(0,"18"));
        S.Add(new Node(0,"10"));
        S.Add(new Node(0,"98"));
        S.Add(new Node(0,"25"));
        S.Add(new Node(0,"17"));
        S.Add(new Node(0,"27"));
        S.Add(new Node(0,"7"));
        
        S.Print();
    }*/
    public static void main(String[] args) {

        
       GUIGenerador G= new GUIGenerador();  
       G.setVisible(true);
        
       // PruebaDoble();
    }
    
}
