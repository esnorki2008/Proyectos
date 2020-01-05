package Analizadores;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ENK
 */
public class Token {
    public enum tipo  {
    Numero,Codigo,Nombre,Creditos,Fecha,Nota,Observaciones
    }
    private final tipo Tipo;
    private final String Lexema;
    Token(tipo Tipo,String Info ){
        this.Tipo=Tipo;
        this.Lexema=Info;
    }
}
