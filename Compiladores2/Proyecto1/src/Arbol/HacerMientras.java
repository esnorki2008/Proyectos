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
public class HacerMientras implements Instruccion {

    private Valor Val;
    private Instruccion Contenido;

    public HacerMientras(Instruccion Val, Instruccion Contenido) {
        this.Val = (Valor) Val;
        this.Contenido = Contenido;
    }

    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Graficar() {
        String Salida, Nombre;
        Nombre = this.NombreGrafico();
        Salida = Nombre + "[label=\"HacerMientras\"]";

        Salida = Salida + Contenido.Graficar();
        Salida = Salida + Val.Graficar();
        Salida = Salida + Nombre + "->" + Contenido.NombreGrafico() + "\n";
        Salida = Salida + Nombre + "->" + Val.NombreGrafico() + "\n";

        return Salida;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
}
