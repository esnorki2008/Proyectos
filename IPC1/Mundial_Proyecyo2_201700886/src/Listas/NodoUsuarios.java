/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author usuario
 */
public class NodoUsuarios {
    public int ID,Sobres;
    public String Nombre,Apellido,Contra;
    NodoUsuarios Siguiente,Anterior;
    
    
    
    NodoUsuarios(int ID,String Nombre,String Apellido, String Contra){
    this.ID=ID;
    this.Nombre=Nombre;
    this.Apellido=Apellido;
    this.Contra=Contra;
    }
}
