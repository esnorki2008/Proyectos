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
public class NodoSTR {
    protected String Identificador;
    private Object Contenido=null;
    private NodoSTR Next=null;
    private NodoSTR Back=null;
    
    public NodoSTR(String ID,Object Contenido){
        this.Identificador=ID;
        this.Contenido=Contenido;
    }
    
    public String toString(){
        return this.Contenido.toString();
    }
    public void Print(){
        System.out.println(this.toString());
    }
    public void Change(NodoSTR NodoSTR1){
        String CId=NodoSTR1.getIdentificador();
        Object CCon=NodoSTR1.getContenido();
        NodoSTR1.setContenido(Contenido);
        NodoSTR1.setId(Identificador);
        this.Identificador=CId;
        this.Contenido=CCon;
        
    }
    /**
     * @return the Id
     */
    
    public String getIdentificador() {
        return Identificador;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Identificador = Id;
    }

    /**
     * @return the Siguiente
     */
    public NodoSTR getNext() {
        return Next;
    }

    /**
     * @param Next the Siguiente to set
     */
    public void setNext(NodoSTR Next) {
        this.Next = Next;
    }

    /**
     * @return the Anterior
     */
    public NodoSTR getBack() {
        return Back;
    }

    /**
     * @param Anterior the Anterior to set
     */
    public void setBack(NodoSTR Back) {
        this.Back = Back;
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
