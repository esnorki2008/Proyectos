/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author ENK
 */
public class GraficaLineas {

    public String ID = "";
    public String Titulo = "";
    public String TituloX = "";
    public String TituloY = "";
    public LinkedList<DXYLine> GB;

    public GraficaLineas() {
        this.GB = new LinkedList<DXYLine>();
    }
    public LinkedList<DXYLine> DatosXY(){
        
       return GB;
    }
    
    public void CrearXYLine(String Nombre, String Color, String Grosor, String Puntos) {
        DXYLine D = new DXYLine(Nombre,Color,Grosor,Puntos);
        this.GB.add(D);
    }

}

