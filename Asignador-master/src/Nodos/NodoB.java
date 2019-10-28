package Nodos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lizandro
 */
public class NodoB {
    private NodoB[] PagIzq=null;
    private NodoB[] PagDer=null;
    private int Clave=0;
    private Object Contenido=null;

    public boolean EsHoja(){
        if(PagIzq==null && PagDer==null)
            return true;
        else 
            return false;
    }
    public String NodoGrafico(){
        return Contenido.toString()+"[label=\""+Contenido.toString()+"\"]";
    }
    public String toString(){
        return this.Contenido.toString();
    }
    public NodoB(int Clave,Object Contenido){
        this.Clave=Clave;
        this.Contenido=Contenido;
    }
    /**
     * @return the PagIzq
     */
    public NodoB[] getPagIzq() {
        return PagIzq;
    }

    /**
     * @param PagIzq the PagIzq to set
     */
    public void setPagIzq(NodoB[] PagIzq) {
        this.PagIzq = PagIzq;
    }

    /**
     * @return the PagDer
     */
    public NodoB[] getPagDer() {
        return PagDer;
    }

    /**
     * @param PagDer the PagDer to set
     */
    public void setPagDer(NodoB[] PagDer) {
        this.PagDer = PagDer;
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
    
}
