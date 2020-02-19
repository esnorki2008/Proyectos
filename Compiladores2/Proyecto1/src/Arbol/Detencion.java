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
public class Detencion implements Instruccion{
    private int Tipo;
    private Instruccion Val;
    public Detencion(int Tipo,Instruccion Val){
        this.Val=Val;
        this.Tipo=Tipo;
    }
    public Detencion(int Tipo){
        this.Tipo=Tipo;
    }
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
