/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.LDHorizontal;
import Estructuras.LDVertical;
import Estructuras.Matriz;

/**
 *
 * @author linuxlite
 */
public class NodoCabecera extends NodoOrtogonal{
    private LDHorizontal ListaX=null;
    private LDVertical ListaY=null;
    public String NodoLabel(){
        return this.toString()+"[label=\" " + this.toString()+" \" , group= "+this.Id+" ];\n";
    }
    @Override
    public String DatoG(){return this.toString();}
    @Override
    public String Graficar(){
        String Retorno="";
        if(getUp()!=null){
            Retorno=Retorno+this.toString()+"->"+getUp().toString()+"[rankdir=UD];\n";
        }
        if(getDown()!=null){
            Retorno=Retorno+this.toString()+"->"+getDown().toString()+";\n";
        }
        if(getNext()!=null){
            Retorno=Retorno+this.toString()+"->"+getNext().toString()+";\n";
        }
        if(getBack()!=null){
            Retorno=Retorno+this.toString()+"->"+getBack().toString()+";\n";
        }
          if(ListaY!=null){
            Retorno=Retorno+ListaY.getHead()+"->"+this.toString()+"\n";
            Retorno=Retorno+this.toString()+"->"+ListaY.getHead()+"\n";
         }
        if(ListaX!=null){
            Retorno=Retorno+ListaX.Graficar()+"\n";
            Retorno=Retorno+ListaX.getHead()+"->"+this.toString()+"\n";
            Retorno=Retorno+this.toString()+"->"+ListaX.getHead()+"\n";
            Retorno=Retorno+ListaX.Nivel(this.toString())+"\n";
        }    
        //    Retorno=Retorno+ListaY.Uniones(this.toString())+"\n";
        //System.err.println(Retorno);
        return Retorno;
    }
    
    @Override
    public void Copiar(Matriz Nueva) {
        if(ListaX!=null){
            ListaX.CopiarContenido(Nueva);
        }  
    }
    
public LDHorizontal getListaX() {
        return ListaX;
    }

    /**
     * @param ListaX the ListaX to set
     */
    public void setListaX(LDHorizontal ListaX) {
        this.ListaX = ListaX;
    }

    /**
     * @return the ListaY
     */
    public LDVertical getListaY() {
        return ListaY;
    }

    /**
     * @param ListaY the ListaY to set
     */
    public void setListaY(LDVertical ListaY) {
        this.ListaY = ListaY;
    }
     public NodoCabecera(int ID,int IDY,LDHorizontal X){
        this.Id=ID;
        this.IdY=IDY;
        this.ListaX=X;
    }
    public NodoCabecera(int ID,int IDY,LDVertical Y){
        this.Id=ID;
        this.IdY=IDY;
        this.ListaY=Y;
    }

    @Override
    public String toString() {
        if(this.ListaX==null)
            return "X"+this.getId();
        else 
            return "Y"+this.getIdY();
    }
    
    
    @Override
    public String Nombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
}
