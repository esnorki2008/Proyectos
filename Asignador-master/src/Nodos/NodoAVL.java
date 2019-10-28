/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

/**
 *
 * @author lizandro
 */
public class NodoAVL {
     private int FE=0;
     private Object Contenido=null;
     private int Clave=0;
     private NodoAVL Izq = null;
     private NodoAVL Der = null;
     public NodoAVL(int Clave,Object Contenido){
         this.Clave=Clave;
         this.Contenido=Contenido;
     }

    /**
     * @return the FE
     */
     public String CrearNodo(){
        return this.toString()+"[label=\" " + this.toString()+ "\" ];\n";
    }
    public String toString(){
        return this.Contenido.toString();
    }
    public int getFE() {
        return FE;
    }

    /**
     * @param FE the FE to set
     */
    public void setFE(int FE) {
        this.FE = FE;
    }

    /**
     * @return the Contenido
     */
    public Object getContenido() {
        return Contenido;
    }

    /**
     * @param Contenido the Contenido to set
     */
    public void setContenido(Object Contenido) {
        this.Contenido = Contenido;
    }

    /**
     * @return the Clave
     */
    public int getClave() {
        return Clave;
    }

    /**
     * @param Clave the Clave to set
     */
    public void setClave(int Clave) {
        this.Clave = Clave;
    }

    /**
     * @return the Izq
     */
    public NodoAVL getIzq() {
        return Izq;
    }

    /**
     * @param Izq the Izq to set
     */
    public void setIzq(NodoAVL Izq) {
        this.Izq = Izq;
    }

    /**
     * @return the Der
     */
    public NodoAVL getDer() {
        return Der;
    }

    /**
     * @param Der the Der to set
     */
    public void setDer(NodoAVL Der) {
        this.Der = Der;
    }
}
