/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Lineales;

import Nodos.Node;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lizandro
 */
public abstract   class GenericLST {
    protected Node Head=null;
    protected Node End=null;
    protected int Size=0;
    public int Count(){
        return Size;
    }
    public Node getHead(){
        return Head;
    }
    protected abstract void Add(Node NewNode);
    public boolean Comp(Node N1,Node N2){
        if(N1.toString().toLowerCase().compareTo(N2.toString().toLowerCase())>0)
            // Izzquierda
             return true;
        else
            //Derecha
            return false;
    }
    
    public String Graficar(){
        
        if(Head!=null){
            Node TempNode=Head;
            String grafo="";
            grafo = "digraph G{";
            grafo += "node[shape=\"box\"];";
            
            while(TempNode.getNext()!=null){
                grafo += TempNode.toString()+"[label=\" " + TempNode.getIdentificador()+ " \"];\n";
                grafo += TempNode.toString()+"->"+TempNode.getNext().toString()+"\n";
                if(TempNode.getBack()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getBack().toString()+"\n";
                TempNode=TempNode.getNext();
            }
            grafo += TempNode.toString().replace(".", "")+"[label=\" " + TempNode.getIdentificador()+ " \"];\n";


           
            grafo += "}";
            return grafo;
        }
        return "";
    }
    public String GraficarSinCabecera(int N,String Complemento){
        
        if(Head!=null){
            Node TempNode=Head;
            String grafo="";
         //   grafo = "digraph G{";
         //   grafo += "node[shape=\"box\"];";
            while(TempNode.getNext()!=null){
                grafo += "LST"+Complemento+TempNode.toString()+"[label=\" " + TempNode.getIdentificador()+ " \"];\n";
                grafo += "LST"+Complemento+TempNode.toString()+" -> "+"LST"+Complemento+TempNode.getNext().toString()+"\n";
                if(N==2)
                grafo+="\nLST"+Complemento+TempNode.toString()+" -> "+Complemento+TempNode.toString()+"\n";
                if(TempNode.getBack()!=null)
                grafo += "LST"+Complemento+TempNode.toString()+" -> "+"LST"+Complemento+TempNode.getBack().toString()+"\n";
                TempNode=TempNode.getNext();
            }
            if(TempNode.getBack()!=null)
            grafo += "LST"+Complemento+TempNode.toString()+" -> "+"LST"+Complemento+TempNode.getBack().toString()+"\n";
            grafo += "LST"+Complemento+TempNode.toString()+"[label=\" " + TempNode.getIdentificador()+ " \"];\n";
          //  grafo += "}";
            return grafo;
        }
        return "";
    }
    public void Print(){
        
        
        Node TempNode=Head;
        while(TempNode!=null){
            TempNode.Print();
            TempNode=TempNode.getNext();
        }
    }
    public boolean Modify(Node NewNode){
        Node TempNode=Head;
        if(NewNode.getIdentificador().equals(TempNode.getIdentificador())){
            Head=Head.getNext();
            return true;
        }else{
            while(TempNode.getNext()!=null){
                if(NewNode.getIdentificador().equals(TempNode.getNext().getIdentificador())){
                   NewNode.setNext(TempNode.getNext());
                   TempNode.setNext(NewNode);
                   return true;
                }           
                TempNode=TempNode.getNext();
            }
        }
        return false;
    }
    public Node Find(String Id){
        Node TempNode=Head;
        while(TempNode!=null){
            if(TempNode.getIdentificador().equals(Id)){
                return TempNode;
            }           
            TempNode=TempNode.getNext();
        }
        return null;
    }
    protected Node Delete(){
        if(Head!=null){
            Node Temp=Head;
            Head=Head.getNext();             
            return Temp;
        }else{
            return null;
        }
       
    }
     protected boolean Delete(String Id){
        if(Head!=null){
        Node TempNode=Head;
        if(Id.equals(TempNode.getIdentificador())){
            Head=Head.getNext();
            return true;
        }else{
            while(TempNode.getNext()!=null){
                if(Id==TempNode.getNext().getIdentificador()){
                   TempNode.setNext(TempNode.getNext().getNext());
                   return true;
                }           
                TempNode=TempNode.getNext();
            }
         }
        
        } 
        return false;
        
        
    }
    protected Node Get(){
        return Head;
    }
}
