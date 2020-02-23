/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Operacion;

import Arbol.Instruccion;
import Elementos.TablaDeSimbolos;

/**
 *
 * @author Norki
 */
public abstract class Operacion {

    public abstract Object Operar(Object Valor1,int Tip1, Object Valor2,int Tip2, TablaDeSimbolos Tabla);

    public int Entero(Object Valor) {
        return (int) Valor;
    }
    protected int ValorTipo;

    public Operacion() {
        ValorTipo = -1;
    }

    public int GetTipo() {
        return ValorTipo;
    }

    public String Cadena(Object Valor) {
        return (String) Valor;
    }

    public boolean Booleano(Object Valor) {
        return (boolean) Valor;
    }

    public double Flotante(Object Valor) {
        return (Double) Valor;
    }
}
