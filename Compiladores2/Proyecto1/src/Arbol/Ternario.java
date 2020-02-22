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
public class Ternario implements Instruccion{
    private Instruccion Valor,Verdadero,Falso;
    public Ternario(Instruccion Valor,Instruccion Verdadero,Instruccion Falso){
        this.Valor=Valor;
        this.Verdadero=Verdadero;
        this.Falso=Falso;
    }

    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Graficar() {
        String Salida, Nombre;
        Nombre = this.NombreGrafico();
        Salida = Nombre + "[label=\"TERNARIO\"]+\n";
        Salida=Salida+Valor.Graficar();
        Salida=Salida+Verdadero.Graficar();
        Salida=Salida+Falso.Graficar();

       
        Salida = Salida + Nombre + "->" + Valor.NombreGrafico() + "\n";
        Salida = Salida + Nombre + "->" + Verdadero.NombreGrafico() + "\n";
        Salida = Salida + Nombre + "->" + Falso.NombreGrafico() + "\n";
        return Salida;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
}
