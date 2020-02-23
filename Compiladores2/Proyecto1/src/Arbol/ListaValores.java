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

    /**
     * @return the Contenido
     */
    public List <Valor> getContenido() {
        return Contenido;
    }

    /**
     * @param Contenido the Contenido to set
     */
    public void setContenido(List <Valor> Contenido) {
        this.Contenido = Contenido;
    }
    private List <Valor>Contenido;
    public ListaValores(Instruccion Nuevo){
        this.Contenido=new LinkedList<Valor>();
        this.Contenido.add((Valor)Nuevo);
    }
    public void Agregar(Instruccion Nuevo){
        this.getContenido().add((Valor)Nuevo);
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
        int Tamanio=this.getContenido().size();
        for(int i=0;i<Tamanio;i++){
            Salida=Salida+getContenido().get(i).Graficar();
            Salida=Salida+Padre.NombreGrafico()+"->"+getContenido().get(i).NombreGrafico()+"\n";
        }
        return Salida;
    }
    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }
    
}
