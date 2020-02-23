/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Arbol.Funciones.Print;
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
        switch(Id.toLowerCase()){
            case "print":
                new Print(Valores,Tabla);
                break;
        }
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
