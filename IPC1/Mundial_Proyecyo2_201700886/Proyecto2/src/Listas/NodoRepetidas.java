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
public class NodoRepetidas {

    public int IDUsuario, IDEstampas, Cantidad;
    public String Pegada;

    public NodoRepetidas Siguiente, Anterior;

    NodoRepetidas(int a, int b, String c, int d) {
        this.IDUsuario = a;
        this.IDEstampas = b;
        this.Pegada = c;
        this.Cantidad = d;
    }
}
