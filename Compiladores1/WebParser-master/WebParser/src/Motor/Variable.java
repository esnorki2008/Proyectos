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
public class Variable {
    private String Lexema;
    private Almacenamiento Tipo;
    private Primitivo TipoDato;
    private String Nombre;
    private int PosX;
    private int PosY;

    /**
     * @return the Lexema
     */
    Variable(String Nombre,String Lexema, String Tipo){
        if(Tipo.equals("#")){this.Tipo=Almacenamiento.Funcion;}else{
            this.Tipo=Almacenamiento.Variable;
        }
        this.Nombre=Nombre;
        this.Lexema=Lexema;
    }
    
    public String getLexema() {
        return Lexema;
    }

    /**
     * @param Lexema the Lexema to set
     */
    public void setLexema(String Lexema) {
        this.Lexema = Lexema;
    }

    /**
     * @return the Tipo
     */
    public Almacenamiento getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(Almacenamiento Tipo) {
        this.Tipo = Tipo;
    }

    /**
     * @return the TipoDato
     */
    public Primitivo getTipoDato() {
        return TipoDato;
    }

    /**
     * @param TipoDato the TipoDato to set
     */
    public void setTipoDato(Primitivo TipoDato) {
        this.TipoDato = TipoDato;
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
     * @return the PosX
     */
    public int getPosX() {
        return PosX;
    }

    /**
     * @param PosX the PosX to set
     */
    public void setPosX(int PosX) {
        this.PosX = PosX;
    }

    /**
     * @return the PosY
     */
    public int getPosY() {
        return PosY;
    }

    /**
     * @param PosY the PosY to set
     */
    public void setPosY(int PosY) {
        this.PosY = PosY;
    }
    public enum Almacenamiento{
        Funcion,Variable
    }
    public enum Primitivo{
        entero,decimal,booleano,cadena
    }
    
    
     
   
}
