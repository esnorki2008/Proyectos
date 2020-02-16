/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import java.util.ArrayList;

/**
 *
 * @author 50241
 */
public class Nodo {
    private ArrayList Hijos;
    
    public void Operacion(){
    }
    
    public void Agregar(Nodo Hij){
        Hijos.add(Hij);
    }
    public void Agregar(String Hij){
        Hijos.add(new Nodo(Hij));
    }
    public void Agregar(String Hij1,String Hij2){
        Hijos.add(new Nodo(Hij1));
        Hijos.add(new Nodo(Hij2));
    }
    public void Agregar(String Hij1,String Hij2,String Hij3){
        Hijos.add(new Nodo(Hij1));
        Hijos.add(new Nodo(Hij2));
        Hijos.add(new Nodo(Hij3));
    }
    public void Agregar(String Hij1,String Hij2,String Hij3,String Hij4){
        Hijos.add(new Nodo(Hij1));
        Hijos.add(new Nodo(Hij2));
        Hijos.add(new Nodo(Hij3));
        Hijos.add(new Nodo(Hij4));
    }
    public Nodo(String ID){
        Hijos=new ArrayList();  
    }
    
}
