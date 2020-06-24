/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author usuario
 */
public class NodoEquipos {

    public int Num;
    public String Dato;
    public String Fecha;
    public int Mundiales;
    public NodoEquipos Siguiente, Anterior;

   public  NodoEquipos(int n, String D, String B,int a) {
        this.Num = n;
        this.Dato = D;
        this.Fecha=B;
        this.Mundiales=a;
    }

    public void Imprimir() {
        System.out.println(Num + "    " + Dato);

    }

}



