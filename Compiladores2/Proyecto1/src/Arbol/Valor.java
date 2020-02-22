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
    
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Graficar() {
        String Nombre=NombreGrafico()+"T";
        String Retorno;
        Retorno = NombreGrafico() + "[label=\"Valor\"]";
        Retorno = Retorno+Nombre + "[label=\""+TipoOpe+"\"]";
        Retorno = Retorno +NombreGrafico() +"->" + Nombre + "\n";
        
        if(this.Valor1!=null){
            Retorno = Retorno + Nombre +"->" +Valor1.NombreGrafico();
            Retorno = Retorno + Valor1.Graficar();
        }
        if(this.Valor2!=null){
            Retorno = Retorno + Nombre +"->" +Valor2.NombreGrafico();
            Retorno = Retorno + Valor2.Graficar();
        }
        if (this.ValorPuntual != null) {
            Retorno = NombreGrafico() + "[label=\"ValorPuntual\"]";
            Retorno = Retorno + Nombre+"V" + "[label=\"" + ValorPuntual + "\"]";
            Retorno = Retorno + Nombre + "->" + Nombre+"V" + "\n";
        }
        return Retorno;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
    
}
