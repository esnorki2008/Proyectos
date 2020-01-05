/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.Matriz;
import Nodos.NodoABB;

/**
 *
 * @author lizandro
 */
public class Capas {
    
    private Matriz MOrto= new Matriz();
    private int ID=0;
    private NodoABB Puntero=null;
    public String toString(){
        return "Capa_"+this.getID();
    }
    public Capas(int ID){
        this.ID=ID;
    }

    /**
     * @return the MOrto
     */
    public Matriz getMOrto() {
        return MOrto;
    }

    /**
     * @param MOrto the MOrto to set
     */
    public void setMOrto(Matriz MOrto) {
        this.MOrto = MOrto;
    }

    /**
     * @return the Nombre
     */
    public int getID() {
        return ID;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the Puntero
     */
    public NodoABB getPuntero() {
        return Puntero;
    }

    /**
     * @param Puntero the Puntero to set
     */
    public void setPuntero(NodoABB Puntero) {
        this.Puntero = Puntero;
    }
}
