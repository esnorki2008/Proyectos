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
public class Usuarios {
    private String Nombre=null;
    private ListaDoble ListaImagenes= new ListaDoble();
    public Usuarios(String Nombre){
        this.Nombre=Nombre;
    }
    public String toString(){
        return "Usuario_"+this.getNombre();
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    public void EliminarImagenes(Imagenes Nueva){
        this.ListaImagenes.Delete(Nueva.getIdentificador());
    }
    public void AgregarImagenes(Imagenes Nueva){
        this.ListaImagenes.Add(new Node(Nueva.getIdentificador(),Nueva));
    }
    /**
     * @return the ListaImagenes
     */
    public ListaDoble getListaImagenes() {
        return ListaImagenes;
    }

    /**
     * @param ListaImagenes the ListaImagenes to set
     */
    public void setListaImagenes(ListaDoble ListaImagenes) {
        this.ListaImagenes = ListaImagenes;
    }
}
