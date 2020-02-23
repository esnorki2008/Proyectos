/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Elementos.TablaDeSimbolos;

/**
 *
 * @author 50241
 */
public interface Instruccion {
    /*Ejecutar Todo*/
    public void Ejecutar(TablaDeSimbolos Tabla);
    public String Graficar();
    public String NombreGrafico();
}
