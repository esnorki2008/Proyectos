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
        TablaDeSimbolos NuevaTabla=new TablaDeSimbolos(Tabla);//Diferente Ambito
        this.Contenido.Ejecutar(NuevaTabla);
    }
    
}
