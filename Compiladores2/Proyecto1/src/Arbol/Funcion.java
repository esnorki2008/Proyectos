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
public class Funcion implements Instruccion {
    private Instruccion Lista;
    private Cuerpo Contenido;
    public Funcion(Instruccion Lista,Instruccion Contenido){
        this.Lista=Lista;
        this.Contenido=(Cuerpo)Contenido;
    }
    
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Graficar() {
       String Retorno;
        Retorno=NombreGrafico()+"[label=\"Funcion\"]\n";
        Retorno=Retorno+Contenido.Graficar();
        Retorno=Retorno+((ListaParametros)Lista).Graficar(this);
        Retorno = Retorno +this.NombreGrafico()+ "->" + Contenido.NombreGrafico() + "\n";
        return Retorno;  
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }

    
    
    
}
