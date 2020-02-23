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

    public  Object Operar(Object Valor1, int Tip1, Object Valor2, int Tip2, Object Valor3, int Tip3, TablaDeSimbolos Tabla){
       return null;
    }
    public abstract Object Operar(Object Valor1,int Tip1, Object Valor2,int Tip2, TablaDeSimbolos Tabla);
    public  Object Operar(Object Valor1,int Tip1,TablaDeSimbolos Tabla){return null;}

    public String TipoATexto(int Tipo){
        switch(Tipo){
            case 0:
                return "Booleano";
            case 1:
                return "Decimal";
            case 2:
                return "Entero";
            case 3:
                return "String";
            case 4:
                return "Caracter";
            default:
                System.out.println("RETORNANDO TIPO QUE NO EXISTE EN OPERAR     "+Tipo);
                return "No Existe";
        }
    }
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
    
    public boolean MayorQue(String Primero, String Segundo) {
        Primero=Primero.toLowerCase().trim();
        Segundo=Primero.toLowerCase().trim();
        int Salida= Primero.compareTo(Segundo);
        if(Salida<0)
            return true;
        else
            return false;
    }
    public boolean MayorIgualQue(String Primero, String Segundo) {
        Primero=Primero.toLowerCase().trim();
        Segundo=Primero.toLowerCase().trim();
        int Salida= Primero.compareTo(Segundo);
        if(Salida<=0 )
            return true;
        else
            return false;
    }
    public boolean MenorQue(String Primero, String Segundo) {
        Primero=Primero.toLowerCase().trim();
        Segundo=Primero.toLowerCase().trim();
        int Salida= Primero.compareTo(Segundo);
        if(Salida>0)
            return true;
        else
            return false;
    }
    public boolean MenorIgualQue(String Primero, String Segundo) {
        Primero=Primero.toLowerCase().trim();
        Segundo=Primero.toLowerCase().trim();
        int Salida= Primero.compareTo(Segundo);
        if(Salida>=0 )
            return true;
        else
            return false;
    }
}
