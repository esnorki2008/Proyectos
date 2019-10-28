/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Lineales;

import Nodos.NodoSTR;

/**
 *
 * @author lizandro
 */
public class ListaSimpleSTR {
    private NodoSTR Head=null;
    private int Size=0;
    public Object Find(String Id){
        NodoSTR TempNode=getHead();
        while(TempNode!=null){
            if(TempNode.getIdentificador().equals(Id)){
                return TempNode.getContenido();
            }           
            TempNode=TempNode.getNext();
        }
        return null;
    }
     public boolean Delete(String Id){
        if(getHead()!=null){
        NodoSTR TempNode=getHead();
        if((TempNode.getIdentificador()).equals(Id)){
                setHead(getHead().getNext());
            return true;
        }else{
            while(TempNode.getNext()!=null){
                if(TempNode.getNext().getIdentificador().equals(Id)){
                   TempNode.setNext(TempNode.getNext().getNext());
                   return true;
                }           
                TempNode=TempNode.getNext();
            }
         }
        
        } 
        return false;
    }
     public boolean Comp(NodoSTR N1,NodoSTR N2){
        if(N1.toString().toLowerCase().compareTo(N2.toString().toLowerCase())>0)
            // Izzquierda
             return true;
        else
            //Derecha
            return false;
    }
    public void Add(String Clave,Object Inf) {
        NodoSTR NewNode = new NodoSTR(Clave,Inf);
        setSize(getSize() + 1);
        if (getHead()==null){
            setHead(NewNode);
        }else{
            NodoSTR TempNode= getHead(); 
            if(TempNode==getHead() &&(Comp(TempNode,NewNode))){
                        NewNode.setNext(TempNode);
                      //
                       //
                        setHead(NewNode);
                        return;
            }  
            while(TempNode.getNext()!=null){
                if(Comp(TempNode.getNext(),NewNode)){
                    NewNode.setNext(TempNode.getNext() );
                    //
                    //
                    TempNode.setNext(NewNode);  
                    return ;
                }
                TempNode=TempNode.getNext();                
            }       
            TempNode.setNext(NewNode);
        }
    }

    /**
     * @return the Head
     */
    public NodoSTR getHead() {
        return Head;
    }

    /**
     * @param Head the Head to set
     */
    public void setHead(NodoSTR Head) {
        this.Head = Head;
    }

    /**
     * @return the Size
     */
    public int getSize() {
        return Size;
    }

    /**
     * @param Size the Size to set
     */
    public void setSize(int Size) {
        this.Size = Size;
    }
}
