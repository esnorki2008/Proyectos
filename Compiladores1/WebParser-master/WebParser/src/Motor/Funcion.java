/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Motor;

/**
 *
 * @author lizandro
 */
public class Funcion {
 Funcion(String Nombre, int Tipo){
     this.Tipo=Tipo;
     this.Nombre=Nombre;
 }   
    
 private int Tipo;
 private String Nombre;
 private String Alineacion;
 private String Contenido;
 private String Path;
 private String Borde;
 private String Accion;
 private String TextoBoton;
 private double Alto;
 private double Ancho;

    /**
     * @return the Tipo
     */
    public int getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(int Tipo) {
        this.Tipo = Tipo;
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
     * @return the Alineacion
     */
    public String getAlineacion() {
        return Alineacion;
    }

    /**
     * @param Alineacion the Alineacion to set
     */
    public void setAlineacion(String Alineacion) {
        this.Alineacion = Alineacion;
    }

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

    /**
     * @return the Alto
     */
    public double getAlto() {
        return Alto;
    }

    /**
     * @param Alto the Alto to set
     */
    public void setAlto(double Alto) {
        this.Alto = Alto;
    }

    /**
     * @return the Ancho
     */
    public double getAncho() {
        return Ancho;
    }

    /**
     * @param Ancho the Ancho to set
     */
    public void setAncho(double Ancho) {
        this.Ancho = Ancho;
    }

    /**
     * @return the Path
     */
    public String getPath() {
        return Path;
    }

    /**
     * @param Path the Path to set
     */
    public void setPath(String Path) {
        this.Path = Path;
    }

    /**
     * @return the Borde
     */
    public String getBorde() {
        if(Borde.toLowerCase().contains("true")){
            return    "border=\"3\"";     

        }else{
           return    "border=\"1\"";     

        }
        
    }

    /**
     * @param Borde the Borde to set
     */
    public void setBorde(String Borde) {
        this.Borde = Borde;
    }

    /**
     * @return the Accion
     */
    public String getAccion() {
        return Accion;
    }

    /**
     * @param Accion the Accion to set
     */
    public void setAccion(String Accion) {
        this.Accion = Accion;
    }

    /**
     * @return the TextoBoton
     */
    public String getTextoBoton() {
        return TextoBoton;
    }

    /**
     * @param TextoBoton the TextoBoton to set
     */
    public void setTextoBoton(String TextoBoton) {
        this.TextoBoton = TextoBoton;
    }
}
