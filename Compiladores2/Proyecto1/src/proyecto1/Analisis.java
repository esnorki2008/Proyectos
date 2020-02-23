/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1;

import Analizadores.parser;
import Analizadores.scanner;
import Arbol.Instruccion;
import Elementos.Metodo;
import Elementos.TablaDeSimbolos;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Norki
 */
public class Analisis {
    private void EscribirArchivo(String Contenido) {
        String Path = "C:\\Users\\Norki\\Desktop\\Proyectos\\Compiladores2\\Proyecto1\\src\\Analizadores\\Codigo.dot";
        String PathIma = "C:\\Users\\Norki\\Desktop\\Proyectos\\Compiladores2\\Proyecto1\\src\\Analizadores\\Imagen.png";
        String Comando="dot -Tpng " +Path +" -o "+PathIma;
        
        Contenido="digraph G {\n"+Contenido+"\n}";
        try {
            FileWriter myWriter = new FileWriter(Path);
            myWriter.write(Contenido);
            myWriter.close();
            Process p = Runtime.getRuntime().exec(Comando);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
}
    
    public void Analizar(String Contenido){
    scanner lexico = new scanner(new BufferedReader(new StringReader(Contenido)));        
        parser sin = new parser(lexico);
        try {
            sin.parse();
        } catch (Exception ex) {
            Logger.getLogger(Proyecto1.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.Raiz=sin.Raiz;
        EscribirArchivo(Raiz.Graficar());
        Raiz.Ejecutar(new TablaDeSimbolos(null,null));
    }
    private Instruccion Raiz;
}
