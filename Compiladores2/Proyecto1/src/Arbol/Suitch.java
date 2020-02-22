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
public class Suitch implements Instruccion{
    private List <Instruccion>Contenido;
    private CuerpoSuitch Default;
    private Valor Valer;
    public  Suitch(){
        Contenido=new LinkedList<Instruccion>();
    }
    public void AgregarValor(Instruccion Valor){
        this.Valer=(Valor)Valor;
    }
    public void Agregar(CuerpoSuitch Nuevo,boolean Case){//true=case      false=default
        if(!Case){
            this.Default=Nuevo;
        }else{
            Contenido.add(Nuevo);
        }
    }
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
