/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.Matriz;

/**
 *
 * @author linuxlite
 */
public abstract class NodoOrtogonal {
    protected int Id=0; 
    protected int IdY=0; 
    protected int Grupo=0;
    protected NodoOrtogonal Next=null;
    protected NodoOrtogonal Back=null;
    protected NodoOrtogonal Up=null;
    protected NodoOrtogonal Down=null;
    public abstract void Copiar(Matriz Nueva);
    public abstract String DatoG();
    public abstract String Graficar();
    public int getIdY() {
        return IdY;
    }
     public void setIdY(int Id) {
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }
     public void setId(int Id) {
        this.Id = Id;
    }
    public abstract String NodoLabel(); 
    @Override
    public abstract String toString(); 
    public abstract String Nombre(); 
    /**
     * @param Id the Id to set
     */
   
    /**
     * @return the Next
     */
    public NodoOrtogonal getNext() {
        return Next;
    }

    /**
     * @param Next the Next to set
     */
    public void setNext(NodoOrtogonal Next) {
        this.Next = Next;
    }

    /**
     * @return the Back
     */
    public NodoOrtogonal getBack() {
        return Back;
    }

    /**
     * @param Back the Back to set
     */
    public void setBack(NodoOrtogonal Back) {
        this.Back = Back;
    }

    /**
     * @return the Up
     */
    public NodoOrtogonal getUp() {
        return Up;
    }

    /**
     * @param Up the Up to set
     */
    public void setUp(NodoOrtogonal Up) {
        this.Up = Up;
    }

    /**
     * @return the Down
     */
    public NodoOrtogonal getDown() {
        return Down;
    }

    /**
     * @param Down the Down to set
     */
    public void setDown(NodoOrtogonal Down) {
        this.Down = Down;
    }

    /**
     * @return the Grupo
     */
    public int getGrupo() {
        return Grupo;
    }

    /**
     * @param Grupo the Grupo to set
     */
    public void setGrupo(int Grupo) {
        this.Grupo = Grupo;
    }
}
