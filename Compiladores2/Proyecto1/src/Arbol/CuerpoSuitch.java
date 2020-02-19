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
public class CuerpoSuitch implements Instruccion{
    private Instruccion Contenido;
    private Instruccion Detener;
    private Instruccion Valor;
    public CuerpoSuitch(Instruccion Contenido,Instruccion Detener) {
        this.Contenido=Contenido;
        this.Detener=Detener;
        this.Valor=null;
    }
    public CuerpoSuitch(Instruccion Valor,Instruccion Contenido,Instruccion Detener) {
        this.Contenido=Contenido;
        this.Detener=Detener;
        this.Valor=Valor;
    }
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Graficar() {
        String Retorno;
        if(Valor!=null)
            Retorno=NombreGrafico() + "[label=\"Case\"]";
        else
            Retorno=NombreGrafico() + "[label=\"Default\"]";
        Retorno = Retorno +Contenido.Graficar();
        Retorno = Retorno +Detener.Graficar();
        return Retorno;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
    
}
