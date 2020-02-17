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
public class Si implements Instruccion{
    Instruccion Valor,Si,Sino;
    public Si(Instruccion Valor,Instruccion Si,Instruccion Sino){
        this.Valor=Valor;
        this.Si=Si;
        this.Sino=Sino;
    }

    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
