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
public class NodoEstampasUsuario {

    public int IDUsuario, IDEstampas, Cantidad;
    public String Pegada;

    public NodoEstampasUsuario Siguiente, Anterior;

    NodoEstampasUsuario(int a, int b, String c, int d) {
        this.IDUsuario = a;
        this.IDEstampas = b;
        this.Pegada = c;
        this.Cantidad = d;
    }

    int NumSobre, Index;

    NodoEstampasUsuario(int a, int b) {
        this.NumSobre = a;
        this.Index = b;

    }
}
