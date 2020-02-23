/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

/**
 *
 * @author Norki
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public boolean MayorQue(String Primero, String Segundo) {
        Primero=Primero.toLowerCase().trim();
        Segundo=Primero.toLowerCase().trim();
        int Salida= Primero.compareTo(Segundo);
        if(Salida<0)
            return true;
        else
            return false;
    }
    public boolean MayorQueIgual(String Primero, String Segundo) {
        Primero=Primero.toLowerCase().trim();
        Segundo=Primero.toLowerCase().trim();
        int Salida= Primero.compareTo(Segundo);
        if(Salida<=0 )
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
    }

}
