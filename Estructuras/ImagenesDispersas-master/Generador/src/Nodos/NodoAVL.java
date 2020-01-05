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
     private String Clave=null;
     private NodoAVL NIzq = null;
     private NodoAVL NDer = null;
     public NodoAVL(String Clave,Object Contenido){
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
    public String getClave() {
        return Clave;
    }

    /**
     * @param Clave the Clave to set
     */
    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    /**
     * @return the NIzq
     */
    public NodoAVL getNIzq() {
        return NIzq;
    }

    /**
     * @param NIzq the NIzq to set
     */
    public void setNIzq(NodoAVL NIzq) {
        this.NIzq = NIzq;
    }

    /**
     * @return the NDer
     */
    public NodoAVL getNDer() {
        return NDer;
    }

    /**
     * @param NDer the NDer to set
     */
    public void setNDer(NodoAVL NDer) {
        this.NDer = NDer;
    }
}
