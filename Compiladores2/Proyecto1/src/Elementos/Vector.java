/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.LinkedList;

/**
 *
 * @author Norki
 */

public class Vector implements Objeto{

    /**
     * @return the Tipo
     */
    public int getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
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

    /**
     * @return the Contenido
     */
    public LinkedList getContenido() {
        return Contenido;
    }

    /**
     * @param Contenido the Contenido to set
     */
    public void setContenido(LinkedList Contenido) {
        this.Contenido = Contenido;
    }
    private int Tipo;
    private String Nombre;
    private LinkedList Contenido;
    public void ActualizarPrimero(Object Conte){
        this.Contenido.set(0, Conte);
    }
  
    public Vector(String Nombre,Object Contenido,int Tipo){
        this.Tipo=Tipo;
        this.Nombre=Nombre;
        this.Contenido=new LinkedList();
        this.Contenido.add(Contenido);
    }

    @Override
    public int Tipo() {
        return 1;
    }

   
    
    
    
    
    
}
   