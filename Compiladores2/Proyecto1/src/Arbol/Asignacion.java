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
public class Asignacion implements Instruccion{
    private String Id;
    private Instruccion Valor;
    public Asignacion(String Id,Instruccion Valor){
        this.Id=Id;
        this.Valor=Valor;
    }
    
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        Valor Val=(Valor)this.Valor;
        if (Val.getTipoOpe().equalsIgnoreCase("declafuncion")) {
            Tabla.ActualizarMetodo(Id, (Funcion)Valor);
            System.out.println("Es Declaracion Funcion");
        }else{
            Val.Ejecutar(Tabla);
            Object ValorVariable=Val.SalidaEjecucion();
            Tabla.ActualizarVariable(Id,ValorVariable,Val.getTipo());
            System.out.println("No Func");
        }
    }

    @Override
    public String Graficar() {
        String Salida,Nombre;
        Nombre=this.NombreGrafico();
        Salida=Nombre+"[label="+"\"Asignar:"+ Id+"\"]\n";
        Salida=Salida+Valor.Graficar();
        Salida=Salida+Nombre+"->"+Valor.NombreGrafico()+"\n";
        return Salida;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }

    
    
}
