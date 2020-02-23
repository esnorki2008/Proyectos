/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Excepciones;

/**
 *
 * @author Norki
 */
public class Excepcion {

    /**
     * @return the Contenido
     */
    public String getContenido() {
        return Contenido;
    }

    /**
     * @param Contenido the Contenido to set
     */
    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }
    private String Contenido;
    public Excepcion(String Conte){
        this.Contenido=Conte;
    }
}
