/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.Lineales.ListaDoble;
import Nodos.Node;

/**
 *
 * @author lizandro
 */
public class Imagenes {
    private String Identificador;
    private ListaDoble ListaCapas= new ListaDoble();
    public Imagenes(String ID){
        this.Identificador=ID;
    }
    public void EliminarCapas(Capas Nueva){
        this.ListaCapas.Delete(""+Nueva.getID());
    }
    public void AgregarCapas(Capas Nueva){
        this.ListaCapas.Add(new Node(""+Nueva.getID(),Nueva));
    }
    public String toString(){
        return "Imagen_"+this.getIdentificador();
    }
    
    public String getIdentificador() {
        return Identificador;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setIdentificador(String ID) {
        this.Identificador = ID;
    }

    /**
     * @return the ListaCapas
     */
    public ListaDoble getListaCapas() {
        return ListaCapas;
    }

    /**
     * @param ListaCapas the ListaCapas to set
     */
    public void setListaCapas(ListaDoble ListaCapas) {
        this.ListaCapas = ListaCapas;
    }
}
