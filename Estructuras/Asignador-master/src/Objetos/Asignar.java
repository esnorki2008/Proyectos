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
public class Asignar {
    private Estudiante AsigEstudiante;
    private int Zona;
    private int Final;
    public Asignar(Estudiante Carnet,int Zona,int Final){
        this.AsigEstudiante=Carnet;
        this.Zona=Zona;
        this.Final=Final;
    }
    @Override
    public String toString(){
        return ""+AsigEstudiante.getCarnet() +Zona+Final;
    }
    public String SubCod(){
        return ""+AsigEstudiante.getCarnet() ;
    }
    public String Graph(){
        return ""+AsigEstudiante.getCarnet() +Zona+Final;
    }
    /**
     * @return the Zona
     */
    public int getZona() {
        return Zona;
    }

    /**
     * @param Zona the Zona to set
     */
    public void setZona(int Zona) {
        this.Zona = Zona;
    }

    /**
     * @return the Final
     */
    public int getFinal() {
        return Final;
    }

    /**
     * @param Final the Final to set
     */
    public void setFinal(int Final) {
        this.Final = Final;
    }

    /**
     * @return the AsigEstudiante
     */
    public Estudiante getAsigEstudiante() {
        return AsigEstudiante;
    }

    /**
     * @param AsigEstudiante the AsigEstudiante to set
     */
    public void setAsigEstudiante(Estudiante AsigEstudiante) {
        this.AsigEstudiante = AsigEstudiante;
    }

}
