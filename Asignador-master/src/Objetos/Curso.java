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
public class Curso {
    private int Codigo;
    private String Nombre;
    private int Semestre;
    private int Creditos;
    public Curso(int Codigo,String Nombre,int Semestre,int Creditos){
        this.Codigo=Codigo;
        this.Nombre=Nombre;
        this.Semestre=Semestre;
        this.Creditos=Creditos;
    }
    /**
     * @return the Codigo
     */
    @Override
    public String toString(){
        return "CUR"+this.Codigo;
    }
    public String Graph(){
        return " Codigo "+Codigo+ " Nombre "+this.Nombre;
    }
    public int getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
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
     * @return the Semestre
     */
    public int getSemestre() {
        return Semestre;
    }

    /**
     * @param Semestre the Semestre to set
     */
    public void setSemestre(int Semestre) {
        this.Semestre = Semestre;
    }

    /**
     * @return the Creditos
     */
    public int getCreditos() {
        return Creditos;
    }

    /**
     * @param Creditos the Creditos to set
     */
    public void setCreditos(int Creditos) {
        this.Creditos = Creditos;
    }
            
}
