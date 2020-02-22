/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Elementos.TablaDeSimbolos;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Norki
 */
public class ListaValores implements Instruccion{
    private List <Valor>Contenido;
    public ListaValores(Instruccion Nuevo){
        this.Contenido=new LinkedList<Valor>();
        this.Contenido.add((Valor)Nuevo);
    }
    public void Agregar(Instruccion Nuevo){
        this.Contenido.add((Valor)Nuevo);
    }
    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String Graficar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public String Graficar(Instruccion Padre){
        String Salida="";
        int Tamanio=this.Contenido.size();
        for(int i=0;i<Tamanio;i++){
            Salida=Salida+Contenido.get(i).Graficar();
            Salida=Salida+Padre.NombreGrafico()+"->"+Contenido.get(i).NombreGrafico()+"\n";
        }
        return Salida;
    }
    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
    
}
