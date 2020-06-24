
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Mainsito {

    
    
    public static void main(String[] args) {
        //AlmaE asa = new AlmaE();
        //ListaE lista = asa.Primer;
        //lista.Agregar(0, "Brazil","20/01/2011",5);
        //lista.Agregar(1, "Turquía","20/01/1927",0);
        //lista.Agregar(2, "Irán","20/01/1935",1);
        
        
        
        
        CargaArchivos ca = new CargaArchivos();
        ca.setVisible(true);
        ca.setBounds(100, 100, 810, 500);
        
        
        //Lector.lista.Imprimir();
        
    }
}
    
    
    
   
