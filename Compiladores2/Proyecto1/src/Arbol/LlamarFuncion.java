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
public class LlamarFuncion implements Instruccion{
    private String Id;
    private ListaValores Valores;
    public LlamarFuncion(String Id,Instruccion Valores){
        this.Id=Id;
        this.Valores=(ListaValores)Valores;
    }
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public String Graficar() {
        String Retorno;
        Retorno=NombreGrafico()+"[label=\"LlamarFuncion: |"+Id+"|\"]";
        Retorno=Retorno+((ListaValores)Valores).Graficar(this);
        return Retorno; 
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
    
}
