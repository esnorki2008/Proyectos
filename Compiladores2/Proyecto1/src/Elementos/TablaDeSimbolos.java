/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.LinkedList;

/**
 *
 * @author 50241
 */
public class TablaDeSimbolos {
    private TablaDeSimbolos Padre;
    private LinkedList ListaMetodos;
    private LinkedList ListaVariables;
    
    public TablaDeSimbolos(TablaDeSimbolos Padre){
        this.Padre=Padre;
        ListaMetodos=new LinkedList();
        ListaVariables=new LinkedList();
    }
    
    public Objeto Buscar(String Id){
        int Tama;
        Tama=ListaMetodos.size();
        for(int i=0;i<Tama;i++){
            
        }
    
    }
}
