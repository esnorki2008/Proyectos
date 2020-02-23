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
public class Bloque implements Instruccion {
    private Instruccion Contenido;
    /*Constructor*/
    public Bloque(Instruccion Contenido) {
        this.Contenido=Contenido;
    }
    /*Ejecutar Todo El Contenido*/
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        TablaDeSimbolos NuevaTabla=new TablaDeSimbolos(Tabla,Tabla.Padre());//Diferente Ambito
        this.Contenido.Ejecutar(NuevaTabla);
    }
    @Override
    public String Graficar() {
        String Salida,Nombre;
        Nombre=this.NombreGrafico();
        Salida=Nombre+"[label=\"Bloque\"]\n";
        Salida=Salida+"->"+Contenido.NombreGrafico()+"\n";
        Salida=Salida+Contenido.Graficar();
        return Salida;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }

}
