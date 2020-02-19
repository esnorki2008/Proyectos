/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Elementos.TablaDeSimbolos;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author 50241
 */
public class Cuerpo implements Instruccion {

    private List<Instruccion> Contenido;

    public Cuerpo() {
        Contenido = new LinkedList<Instruccion>();
    }

    public void Agregar() {
    }

    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        for (int i = 0; i < Contenido.size(); i++) {
            Instruccion Ins = Contenido.get(i);
            Ins.Ejecutar(Tabla);
        }
    }

    public void Agregar(Instruccion Nueva) {
        this.Contenido.add(Nueva);
    }

    @Override
    public String Graficar() {
        String Salida, Nombre;
        Nombre = this.NombreGrafico();
        Salida = Nombre + "[label=\"Cuerpo\"]";
        int Tamaño = Contenido.size();
        for (int i = 0; i < Tamaño; i++) {
            Salida = Salida + "->" + Contenido.get(i).NombreGrafico() + "\n";
            Salida = Salida + Contenido.get(i).Graficar();
        }

        return Salida;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
}
