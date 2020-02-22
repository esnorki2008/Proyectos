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
        this.Default=null;
        this.Valer=null;
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

    @Override
    public String Graficar() {
        String Salida, Nombre;
        Nombre = this.NombreGrafico();
        Salida = Nombre + "[label=\"SUITCH\"]\n";
        Salida = Nombre + "VAL[label=\"SUITCH\"]\n";
        Salida = Nombre + "DEF[label=\"SUITCH\"]\n";
        
        Salida = Salida +Nombre+ "->VAL" + Nombre+ "\n";
        Salida = Salida +Nombre+ "->DEF" + Nombre+ "\n";
        int Tamaño = Contenido.size();
        for (int i = 0; i < Tamaño; i++) {
            Salida = Salida + Nombre+"->" + Contenido.get(i).NombreGrafico() + "\n";
            Salida = Salida + Contenido.get(i).Graficar();
        }

        return Salida;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
    
}
