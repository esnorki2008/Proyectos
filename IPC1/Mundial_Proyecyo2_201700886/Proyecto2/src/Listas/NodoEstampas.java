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
public class NodoEstampas {
   public NodoEstampas Siguiente;
    
    public int ID,IDJugador,IDEquipo;
    public String Rareza,RutaImagen;
    NodoEstampas(int a,int b,int c,String d, String e){
    this.ID=a;
    this.IDJugador=b;
    this.IDEquipo=c;
    this.Rareza=d;
    this.RutaImagen=e;
    
    }
}
