/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.NoLineales;

import Estructuras.Lineales.Queue;
import Nodos.NodoABB;

/**
 *
 * @author lizandro
 */
public class ABB {
  protected NodoABB Raiz=null;

    /**
     * @return the Raiz
     */
    public NodoABB Buscar(int ID){
        if(Raiz==null)
            return null;
        else
           return Raiz.Buscar(ID);
    }
    public Queue PostOrdenCola(int Numero){
        Queue Cola= new Queue("");
        if(this.Raiz!=null)
            return Raiz.PostOrdenNumerico(Numero, Cola);
        else    
            return Cola;
    }
    public Queue InordenCola(int Numero){
        Queue Cola= new Queue("");
        if(this.Raiz!=null)
            return Raiz.InOrdenNumerico(Numero, Cola);
        else    
            return Cola;
    }
    public Queue PreOrdenCola(int Numero){
        Queue Cola= new Queue("");
        if(this.Raiz!=null)
            return Raiz.PreOrdenNumerico(Numero, Cola);
        else    
            return Cola;
    }
    public void Agregar(NodoABB Nuevo){
        if(Raiz==null)
            Raiz=Nuevo;
        else
            Raiz.Agregar(Nuevo);
    }
    public void Imprimir(){
        if(Raiz==null)
            System.out.println("Arbol Vacio");
        else
            Raiz.PostOrden();
        
    }
    public String Graficar(){
        if(Raiz==null)
           return "\n ARBOL VACIO NO SE PUEDE GRAFIcAR \n";
        else
         return  "\n digraph G{ \n"+ Raiz.Graph()+"}\n";
    }
    public String GraficarSinCabecera(){
        if(Raiz==null)
           return "\n ARBOL VACIO NO SE PUEDE GRAFIcAR \n";
        else
         return  " \n"+ Raiz.Graph()+"\n";
    }
    public int Altura(){
        if(Raiz!=null)
           return Raiz.Altura();
        else
         return  0;
    }
    public NodoABB getRaiz() {
        return Raiz;
    }

    /**
     * @param Raiz the Raiz to set
     */
    public void setRaiz(NodoABB Raiz) {
        this.Raiz = Raiz;
    }
    
}
