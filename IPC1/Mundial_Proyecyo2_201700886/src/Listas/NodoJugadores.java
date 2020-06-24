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
public class NodoJugadores {
    public NodoJugadores Siguiente,Anterior;
    public int ID;
    public String Nombre;
    public String Posicion;
    public int Año;
    public float Altura,Peso;
    public int IDEquipo;
    
    //Guarsdar para crear el objete
    NodoJugadores(int a,String b, String c,int d, float e,float f,int g){
        this.ID=a;
        this.Nombre=b;
        this.Posicion=c;
        this.Año=d;
        this.Altura=e;
        this.Peso=f;
        this.IDEquipo=g;
    }
}
