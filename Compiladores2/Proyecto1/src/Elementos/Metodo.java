/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Arbol.Cuerpo;
import Arbol.Instruccion;

/**
 *
 * @author Norki
 */
public class Metodo implements Objeto{
    public Cuerpo Raiz;
    public String Nombre;
    public Metodo(String Nombre,Cuerpo Raiz){
        this.Raiz=Raiz;
        this.Nombre=Nombre;
    }

    @Override
    public int Tipo() {
        return 2;
    }
    
}
