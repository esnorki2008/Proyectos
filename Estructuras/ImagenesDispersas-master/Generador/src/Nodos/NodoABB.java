/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.Lineales.Queue;

/**
 *
 * @author lizandro
 */
public class NodoABB {
    protected Object Dato=null;
    protected int Identificador=0;
    protected NodoABB HIzq=null,HDer=null;

    /**
     * @return the Dato
     */
    public NodoABB(int Identificador,Object Dato ){
        this.Identificador=Identificador;
        this.Dato=Dato;
    }
    public String toString(){
        return Dato.toString();
    } 
    public NodoABB Buscar(int ID){
        if(this.Identificador==ID)
            return this;
        else
        if(this.Identificador>ID){
            if(this.HIzq!=null)
                return this.HIzq.Buscar(ID);
            else
                return null;
        }
        else{
            if(this.HDer!=null)
                return this.HDer.Buscar(ID);
            else
                return null;
        }
    }
    public void Agregar(NodoABB Nuevo){
        if(this.Identificador>Nuevo.getIdentificador()){
            if(this.HIzq==null)
                this.HIzq=Nuevo;
            else
                HIzq.Agregar(Nuevo);
        }else if(this.Identificador<Nuevo.getIdentificador()){
        if(this.HDer==null)
                this.HDer=Nuevo;
            else
                HDer.Agregar(Nuevo);
        }else{
            System.out.println("Dato Ya Añadido En El ABB");
        }
        
    }
    public Queue PreOrdenNumerico(int Num,Queue Cola){
        if(Num==Cola.Count())
        return Cola;
        Cola.Enqueue(new Node(this.Dato.toString(),this.Dato));
        if(null !=HIzq && Num>0)
        Cola=HIzq.PreOrdenNumerico(Num,Cola);
        if(null !=HDer&& Num>0)
        HDer.PreOrdenNumerico(Num,Cola);
        return Cola;
    }
    public Queue InOrdenNumerico(int Num,Queue Cola){
        if(null !=HIzq && Num>0)
        Cola=HIzq.InOrdenNumerico(Num,Cola);
        if(Num==Cola.Count())
        return Cola;
        Cola.Enqueue(new Node(this.Dato.toString(),this.Dato));
        if(null !=HDer&& Num>0)
        HDer.InOrdenNumerico(Num,Cola);
        return Cola;
    }
    public Queue PostOrdenNumerico(int Num,Queue Cola){
        if(null !=HIzq && Num>0)
        Cola=HIzq.PostOrdenNumerico(Num,Cola);
        if(null !=HDer&& Num>0)
        HDer.PostOrdenNumerico(Num,Cola);
        if(Num==Cola.Count())
        return Cola;
        Cola.Enqueue(new Node(this.Dato.toString(),this.Dato));
        return Cola;
    }
    
    public void PostOrden(){
        if(null !=HIzq)
        HIzq.PostOrden();
        if(null !=HDer)
        HDer.PostOrden();
        System.out.println(this.toString());
    }
     
    public String CrearNodo(){
        return this.toString()+"[label=\" " + this.toString()+ "\" ];\n";
    }
    public String Graph(){
        String Retorno="";
        if(null !=this.HIzq){
        Retorno+=this.HIzq.Graph();
        Retorno+= this.toString()+"->"+HIzq.toString()+"\n";
        }
        if(null !=this.HDer){
        Retorno+=this.HDer.Graph();
        Retorno+= this.toString()+"->"+HDer.toString()+"\n";
        }
        return Retorno +this.CrearNodo();
    }
    public int Altura(){
        int A=0,B=0;
        if(this.HIzq!=null)
            A=this.HIzq.Altura()+1;
        if(this.HDer!=null)
            B=this.HDer.Altura()+1;
        
        if(A>B)
            return A;
        else
            return B;
        
    }
    public boolean Comp(NodoABB N1,NodoABB N2){
        if(N1.toString().toLowerCase().compareTo(N2.toString().toLowerCase())>0)
            // Izzquierda
             return true;
        else
            //Derecha
            return false;
    }
    public Object getDato() {
        return Dato;
    }

    /**
     * @param Dato the Dato to set
     */
    public void setDato(Object Dato) {
        this.Dato = Dato;
    }

    /**
     * @return the Identificador
     */
    public int getIdentificador() {
        return Identificador;
    }

    /**
     * @param Identificador the Identificador to set
     */
    public void setIdentificador(int Identificador) {
        this.Identificador = Identificador;
    }

    /**
     * @return the HIzq
     */
    public NodoABB getHIzq() {
        return HIzq;
    }

    /**
     * @param HIzq the HIzq to set
     */
    public void setHIzq(NodoABB HIzq) {
        this.HIzq = HIzq;
    }

    /**
     * @return the HDer
     */
    public NodoABB getHDer() {
        return HDer;
    }

    /**
     * @param HDer the HDer to set
     */
    public void setHDer(NodoABB HDer) {
        this.HDer = HDer;
    }
}
