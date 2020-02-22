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
enum Primitivo{
    Entero,Flotante,Texto,Caracter,Booleano
}
public class Vector implements Objeto{
    private Primitivo Tipo;
    private String Nombre;
    private LinkedList Contenido;
    
    public Vector(Primitivo Tipo,String Nombre){
        this.Tipo=Tipo;
        this.Nombre=Nombre;
        this.Contenido=new LinkedList();
    }

    @Override
    public int Tipo() {
        return 1;
    }
    
    
    
    
    
    
}
