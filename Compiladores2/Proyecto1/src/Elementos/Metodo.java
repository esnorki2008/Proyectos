/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Arbol.Cuerpo;
import Arbol.Funcion;
import Arbol.Instruccion;

/**
 *
 * @author Norki
 */
public class Metodo implements Objeto{
    private Funcion Raiz;
    private String Nombre;
    public Metodo(String Nombre,Funcion Raiz){
        this.Raiz=Raiz;
        this.Nombre=Nombre;
    }

    @Override
    public int Tipo() {
        return 2;
    }

    /**
     * @return the Raiz
     */
    public Funcion getRaiz() {
        return Raiz;
    }

    /**
     * @param Raiz the Raiz to set
     */
    public void setRaiz(Funcion Raiz) {
        this.Raiz = Raiz;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
}
