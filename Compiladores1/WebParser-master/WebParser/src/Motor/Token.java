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
public class Token {
    private int PosX;
    private int PosY;
    private String Lexema;
    private String Tipo="";

    /**
     * @return the PosX
     */
    public Token(String Lexema,int PosX,int PosY){
        this.PosX=PosX;
        this.PosY=PosY;
        this.Lexema=Lexema;
    }
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

    /**
     * @return the Lexema
     */
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
    public String getTipo() {
        return Tipo;
    }

    /**
     * @param Tipo the Tipo to set
     */
    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }
}
