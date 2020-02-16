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
public class Cuerpo implements Instruccion{
    private List <Instruccion>Contenido;
    public Cuerpo(){
        Contenido=new LinkedList<Instruccion>();
    }
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        for (int i = 0; i < Contenido.size(); i++) {
            Instruccion Ins=Contenido.get(i);
            Ins.Ejecutar(Tabla);
        }
    }
    
}
