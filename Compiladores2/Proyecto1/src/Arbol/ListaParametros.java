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
 * @author Norki
 */
public class ListaParametros implements Instruccion{
    private List <Valor>Contenido;
    public ListaParametros(Instruccion Nuevo){
        this.Contenido=new LinkedList<Valor>();
        this.Contenido.add((Valor)Nuevo);
    }
    public void Agregar(Instruccion Nuevo){
        this.Contenido.add((Valor)Nuevo);
    }
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
