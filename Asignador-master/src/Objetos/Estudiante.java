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
public class Estudiante {
    private int carnet;
    private String Nombre;
    private String Direccion;
    private boolean Plataforma;
    public Estudiante(int Carnet,String Nombre,String Direccion){
        this.carnet=Carnet;
        this.Nombre=Nombre;
        this.Direccion=Direccion;
        this.Plataforma=false;
    }
    public String toString(){
        return "EST"+this.carnet;
    }
    public String Graph(){
        return this.carnet+this.Nombre;
    }
    /**
     * @return the carnet
     */
    public int getCarnet() {
        return carnet;
    }

    /**
     * @param carnet the carnet to set
     */
    public void setCarnet(int carnet) {
        this.carnet = carnet;
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

    /**
     * @return the Plataforma
     */
    public boolean isPlataforma() {
        return Plataforma;
    }

    /**
     * @param Plataforma the Plataforma to set
     */
    public void setPlataforma(boolean Plataforma) {
        this.Plataforma = Plataforma;
    }
}
