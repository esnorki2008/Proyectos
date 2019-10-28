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
public class Salon {
    private int Numero;
    private int Capacidad;
    private Edificio Edi;
    public Salon(Edificio Edi,int Numero,int Capacidad){
        this.Edi=Edi;
        this.Numero=Numero;
        this.Capacidad=Capacidad;
    }
    public String toString(){
        return Edi.toString()+"SAL"+Numero+Capacidad;
    }
    public String Graph(){
        return" SALON "+Numero+"\n Capacidad "+Capacidad;
    }
    /**
     * @return the Nombre
     */
   

    /**
     * @return the Numero
     */
    public int getNumero() {
        return Numero;
    }

    /**
     * @param Numero the Numero to set
     */
    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    /**
     * @return the Capacidad
     */
    public int getCapacidad() {
        return Capacidad;
    }

    /**
     * @param Capacidad the Capacidad to set
     */
    public void setCapacidad(int Capacidad) {
        this.Capacidad = Capacidad;
    }
}
