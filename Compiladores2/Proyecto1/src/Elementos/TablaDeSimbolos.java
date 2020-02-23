/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import Arbol.Cuerpo;
import Arbol.Funcion;
import Arbol.Instruccion;
import Excepciones.Excepcion;
import java.util.LinkedList;
import java.util.regex.Pattern;

/**
 *
 * @author 50241
 */
public class TablaDeSimbolos {
    private TablaDeSimbolos Padre,Original;
    private LinkedList<Metodo> ListaMetodos;
    private LinkedList<Vector> ListaVariables;
    private LinkedList<Excepcion> ListaExcepcion;
    
    
    public TablaDeSimbolos(TablaDeSimbolos Padre,TablaDeSimbolos Original){
        if(Padre==null)
            this.Original=Original;
        this.Padre=Padre;
        ListaMetodos=new LinkedList<Metodo>();
        ListaVariables=new LinkedList<Vector>();
        ListaExcepcion=new LinkedList<Excepcion>();
    }
    /**
     * Tipo 0 Booleano
     * Tipo 1 Flotante
     * Tipo 2 Entero
     * Tipo 3 String
     * Tipo 4 Char
     */
    public int DescubrirTipo(String Contenido) {
        boolean S=Pattern.matches("true|false", Contenido);
        if(S){
            return 0;
        }
        S=Pattern.matches("[0-9]+", Contenido);
        if(S){
            return 2;
        }
        S=Pattern.matches("[0-9]+.[0-9]*", Contenido);
        if(S){
            return 1;
        }
        S=Pattern.matches("[a-z]+", Contenido.toLowerCase());
        if(S){
            return 3;
        }
        System.out.println("ERREAOROASJFDASD TABLADESIMBOLOS");
        return 5;
    }
    public void AgregarExcepcion(Excepcion E){
        if(Original!=null)
            this.Original.AgregarExcepcion(E);
        else
            this.AgregarExcepcion(E);
    }
    public TablaDeSimbolos Padre(){
        return this.Padre;
    }
    public Metodo BuscarMetodo(String Id){
        int Tama;
        Tama=ListaMetodos.size();
        for(int i=0;i<Tama;i++){
            String Namae=ListaMetodos.get(i).getNombre();
            if(Namae.equalsIgnoreCase(Id)){
                return ListaMetodos.get(i);
            }
        }
        if(Padre==null)
            return null;
        else{
            return Padre.BuscarMetodo(Id);
        }
    }
    public Vector BuscarVariable(String Id){
        int Tama;
        Tama=ListaVariables.size();
        for(int i=0;i<Tama;i++){
            String Namae=ListaVariables.get(i).getNombre();
            if(Namae.equalsIgnoreCase(Id)){
               return ListaVariables.get(i);
            }
        }
        if(Padre==null)
            return null;
        else{
            return Padre.BuscarVariable(Id);
        }
    }
   
    public void ActualizarMetodo(String Id,Funcion Raiz){
        Metodo Meto=BuscarMetodo(Id);
        if(Meto!=null){
            Meto.setRaiz(Raiz);
        }else{
            this.ListaMetodos.add(new Metodo(Id,Raiz));
        }
    }
    public void ActualizarVariable(String Id,Object Valor,int Tipo){
        Vector Vec=BuscarVariable(Id);
        if(Vec!=null){
            Vec.ActualizarPrimero(Valor);
        }else{
            this.ListaVariables.add(new Vector(Id,Valor,Tipo));
        }
    }
    
}
