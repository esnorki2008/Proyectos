/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Elementos.TablaDeSimbolos;

/**
 *
 * @author Norki
 */
public class Valor implements Instruccion {
    private Instruccion Valor1,Valor2;
    private String TipoOpe;
    private int TamOpe;
    private String ValorPuntual;
    public Valor(String Tipo,Instruccion Valor1,String ValorPuntual){
        this.TipoOpe=Tipo;
        this.Valor1=Valor1;
        this.Valor2=null;
        TamOpe=4;
        ValorPuntual=null;
        this.ValorPuntual=ValorPuntual;
    }
    public Valor(String Tipo,String ValorPuntual){
        this.TipoOpe=Tipo;
        this.Valor1=null;
        this.Valor2=null;
        TamOpe=2;
        ValorPuntual=null;
        this.ValorPuntual=ValorPuntual;
    }
    public Valor(String Tipo,Instruccion Valor1,Instruccion Valor2){
        this.TipoOpe=Tipo;
        this.Valor1=Valor1;
        this.Valor2=Valor2;
        TamOpe=3;
        ValorPuntual=null;
    }
    public Valor(String Tipo,Instruccion Valor1){
        this.TipoOpe=Tipo;
        this.Valor1=Valor1;
        this.Valor2=null;
        TamOpe=2;
        ValorPuntual=null;
    }
    /*public Valor(String ValorPuntual){
        this.TipoOpe=null;
        this.Valor1=null;
        this.Valor2=null;
        TamOpe=1;
        this.ValorPuntual=ValorPuntual;
    }*/
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
