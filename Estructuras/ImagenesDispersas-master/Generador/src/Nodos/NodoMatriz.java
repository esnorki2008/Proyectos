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
 * @author lizandro
 */
public  class NodoMatriz extends NodoOrtogonal{
   
    private Object Data=null; 
    private String Clave="";
    public NodoMatriz(int Id,int IdY,String Clave,Object Dato){

        this.Clave=Clave;
        this.Id=Id;
        this.IdY=IdY;
        this.Data=Dato;
    }
    @Override
   public String NodoLabel(){
        return this.toString()+"[label=\" " + this.toString()+" \" , group= "+this.Grupo+" ];\n";
    }
     @Override
    public void Copiar(Matriz Nueva) {
           Nueva.Agregar(Id, IdY, Data);
    }
    @Override
    public String DatoG(){return Data.toString();}
    @Override
    public String Graficar(){
        String Retorno="";
        if(getUp()!=null)
            Retorno=Retorno+this.toString()+"->"+getUp().toString()+";\n";
        if(getDown()!=null)
            Retorno=Retorno+this.toString()+"->"+getDown().toString()+";\n";
        if(getNext()!=null)
            Retorno=Retorno+this.toString()+"->"+getNext().toString()+";\n";
        if(getBack()!=null)
            Retorno=Retorno+this.toString()+"->"+getBack().toString()+";\n";
        
        
      
        return Retorno;
    }
   
    
    @Override
    public String toString(){
       return "N"+this.Clave.replace(",","N");
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
   
    /**
     * @return the ListaX
     */
    

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

    @Override
    public String Nombre() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
 
    
    
    
}
