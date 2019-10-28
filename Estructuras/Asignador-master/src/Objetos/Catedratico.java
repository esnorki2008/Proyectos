/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author lizandro
 */
public class Catedratico {
    private int Identificador;
    private String Nombre;
    private String Direccion;
    
    public Catedratico(int Carnet,String Nombre,String Direccion){
        this.Identificador=Carnet;
        this.Nombre=Nombre;
        this.Direccion=Direccion;
    }
    /**
     * @return the Identificador
     */
    public String toString(){
        return "CAT"+this.Identificador+this.Nombre.trim();
    }
    public int getCarnet() {
        return Identificador;
    }

    /**
     * @param carnet the Identificador to set
     */
    public void setCarnet(int carnet) {
        this.Identificador = carnet;
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

    /**
     * @return the Direccion
     */
    public String getDireccion() {
        return Direccion;
    }

    /**
     * @param Direccion the Direccion to set
     */
    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }
}
