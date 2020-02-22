/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Elementos.TablaDeSimbolos;

/**
 *
 * @author Norki
 */
public class ParaCiclo implements Instruccion {
    private Valor Val;
    private Instruccion Contenido;
    private String Id;
    public ParaCiclo(String Id,Instruccion Val,Instruccion Contenido){
        this.Val=(Valor)Val;
        this.Contenido=Contenido;
        this.Id=Id;sdsadassda 
    }
    
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Graficar() {
        String Salida, Nombre;
        Nombre = this.NombreGrafico();
        Salida = Nombre + "[label=\"ParaCiclo\"]";
        Salida=Salida+Val.Graficar();
        Salida=Salida+Contenido.Graficar();

        Salida = Salida + Nombre + "->" + Val.NombreGrafico() + "\n";
        Salida = Salida + Nombre + "->" + Contenido.NombreGrafico() + "\n";
        return Salida;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
}
