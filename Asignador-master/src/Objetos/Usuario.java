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
public class Usuario {
    private Estudiante Estudian;
    private String Nombre;
    private int Codigo;
    private String Contraseña;
    private String Tipo;

    public String toString(){
        return "usr"+this.getNombre().trim()+this.getCodigo();
    }
    public String Graph(){
        return this.getCodigo()+"\n"+this.getNombre()+"\n"+this.getContraseña()+"\n"+this.getTipo();
    }
    public Usuario(int Codigo,String Nombre,String Contraseña,String Tipo){
        this.Codigo=Codigo;
        this.Nombre=Nombre;
        this.Contraseña=Contraseña;
        this.Tipo=Tipo;
    }
    public Usuario(Estudiante Usuario,String Nombre,String Contraseña,String Tipo){
        this.Codigo=0;
        this.Nombre=Nombre;
        this.Estudian=Usuario;
        this.Contraseña=Contraseña;
        this.Tipo=Tipo;
    }
    /**
     * @return the Nombre
     */
   
   
    /**
     * @param Nombre the Nombre to set
     */
   

    /**
     * @return the Contraseña
     */
    public String getContraseña() {
        return Contraseña;
    }

    /**
     * @param Contraseña the Contraseña to set
     */
    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    /**
     * @return the Tipo
     */
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
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
     * @return the Estudian
     */
    public Estudiante getEstudian() {
        return Estudian;
    }

    /**
     * @param Estudian the Estudian to set
     */
    public void setEstudian(Estudiante Estudian) {
        this.Estudian = Estudian;
    }

    /**
     * @return the Codigo
     */
    public int getCodigo() {
        if(this.Estudian!=null)
            return this.Estudian.getCarnet();
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }
}
