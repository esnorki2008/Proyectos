/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import Estructuras.Lineales.Queue;
import Operaciones.CargaMasiva;
import java.io.BufferedReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lizandro
 */
public class Maincito {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       // GUI G = new GUI(new CargaMasiva());
       //G.setVisible(true);
        
        Login L = new Login(new CargaMasiva());
        L.setVisible(true);
    
    }
    
  
    
}
