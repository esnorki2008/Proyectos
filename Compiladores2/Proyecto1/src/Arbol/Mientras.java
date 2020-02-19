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
public class Mientras implements Instruccion{
    private Valor Val;
    private Instruccion Contenido;
    public Mientras(Instruccion Val,Instruccion Contenido){
        this.Val=(Valor)Val;
        this.Contenido=Contenido;
    }
    
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
