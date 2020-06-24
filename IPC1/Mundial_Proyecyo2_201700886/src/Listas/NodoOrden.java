/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author Esnorki
 */
public class NodoOrden {
    public int ID,Sobres=0;
    public String Nombre,Apellido,Contra;
    public NodoOrden Siguiente,Anterior;
    
    
    
    NodoOrden(int ID,String Nombre,String Apellido, String Contra){
    this.ID=ID;
    this.Nombre=Nombre;
    this.Apellido=Apellido;
    this.Contra=Contra;
    }
}
