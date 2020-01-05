/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.LDHorizontal;
import Estructuras.LDVertical;

/**
 *
 * @author lizandro
 */
public  class NodeMatriz {
    private int Id=0; 
    private NodeMatriz Next=null;
    private NodeMatriz Back=null;
    private NodeMatriz Up=null;
    private NodeMatriz Down=null;
    private Object Data=null; 
    private LDHorizontal ListaX=null;
    private LDVertical ListaY=null;
    private String Clave="";
    public NodeMatriz(String Clave){
        this.Clave=Clave;
    }
   
    public NodeMatriz(int ID,LDHorizontal X,String Clave){
        this.Id=ID;
        this.ListaX=X;
        this.Data=X;
        this.Clave=Clave;
    }
    public NodeMatriz(int ID,LDVertical Y,String Clave){
        this.Id=ID;
        this.ListaY=Y;
        this.Data=Y;
         this.Clave=Clave;
    }
    public String Graficaqwr(){
        String Retorno="";
        if(Up!=null){
            Retorno=Retorno+this.toString()+"->"+Up.toString()+"\n";
          //  Retorno=Retorno+Up.toString()+"[label=\" " + Up.toString()+ " \"];\n";
        }
        if(Down!=null){
            Retorno=Retorno+this.toString()+"->"+Down.toString()+"\n";
          //  Retorno=Retorno+Down.toString()+"[label=\" " + Down.toString()+ " \"];\n";
        }
        if(Next!=null){
            Retorno=Retorno+this.toString()+"->"+Next.toString()+"\n";
           // Retorno=Retorno+Next.toString()+"[label=\" " + Next.toString()+ " \"];\n";
        }
        if(Back!=null){
            Retorno=Retorno+this.toString()+"->"+Back.toString()+"\n";
          //  Retorno=Retorno+Back.toString()+"[label=\" " + Back.toString()+ " \"];\n";
        }
        
        
        if(ListaX!=null)
            Retorno=Retorno+ListaX.Graficar()+"\n";
        if(ListaY!=null)
        Retorno=Retorno+ListaY.Graficar()+"\n";
        //System.err.println(Retorno);
        return Retorno;
    }
     public String toString(){
         
        return  "N"+this.Clave.replace(",","N");
    }
    /**
     * @return the Id    /**
     * @return the Id
     */
   

    /**
     * @return the Siguiente
     */
    public NodeMatriz getNext() {
        return Next;
    }

    /**
     * @param Next the Siguiente to set
     */
    public void setNext(NodeMatriz Next) {
        this.Next = Next;
    }

    /**
     * @return the Anterior
     */
    public NodeMatriz getBack() {
        return Back;
    }

    /**
     * @param Anterior the Anterior to set
     */
    public void setBack(NodeMatriz Back) {
        this.Back = Back;
    }

    /**
     * @return the Up
     */
    public NodeMatriz getUp() {
        return Up;
    }

    /**
     * @param Up the Up to set
     */
    public void setUp(NodeMatriz Up) {
        this.Up = Up;
    }

    /**
     * @return the Down
     */
    public NodeMatriz getDown() {
        return Down;
    }

    /**
     * @param Down the Down to set
     */
    public void setDown(NodeMatriz Down) {
        this.Down = Down;
    }

    /**
     * @return the Data
     */
    public Object getData() {
        return Data;
    }

    /**
     * @param Data the Data to set
     */
    public void setData(Object Data) {
        this.Data = Data;
    }

    /**
     * @return the Id
     */
    public int getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(int Id) {
        this.Id = Id;
    }

    /**
     * @return the ListaX
     */
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

    /**
     * @return the Clave
     */
    public String getClave() {
        return Clave;
    }

    /**
     * @param Clave the Clave to set
     */
    public void setClave(String Clave) {
        this.Clave = Clave;
    }
    
    
    
}
