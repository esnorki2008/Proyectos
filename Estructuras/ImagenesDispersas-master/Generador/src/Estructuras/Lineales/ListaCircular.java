/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Lineales;

import Nodos.Node;
import Objetos.Imagenes;
import javax.swing.JOptionPane;

/**
 *
 * @author lizandro
 */
public class ListaCircular extends GenericLST{

    @Override
    public void Add(Node NewNode) {
        
        super.Size++;
         if (Head==null){
            Head=NewNode;    
            Head.setNext(Head);
            Head.setBack(Head);
            End=Head;
        }else{
            
           // if(Head)
             
            Node TempNode= Head;
            if(Comp(TempNode,NewNode)){
                End.setNext(NewNode);
                NewNode.setBack(End);
                NewNode.setNext(Head);
                Head.setBack(NewNode);
                Head=NewNode;
                return;
            }
            TempNode=TempNode.getNext();
            while(End!=TempNode){
                if(Comp(TempNode,NewNode)){
                    TempNode.Change(NewNode);
                    return;
                }
                TempNode=TempNode.getNext();
            }
            End=NewNode;
            End.setBack(TempNode);
            End.setNext(Head);
            TempNode.setNext(End);
            Head.setBack(End);
        }
    }
    @Override
    public Node Find(String Id){
        if(Head!=null){
        Node TempNode=Head.getNext();
        if(Head.getIdentificador().equals(Id))return Head;
        while(TempNode!=Head){
            if(TempNode.getIdentificador().equals(Id))return TempNode;
            TempNode=TempNode.getNext();
        }
      }  
        return null;
    }
    @Override
     public boolean Delete(String Id){
        super.Size--;
        if(Head!=null){
        Node TempNode=Head;

        if((Head.getIdentificador()).equals(Id)){

            if(Head==End){
                Head=End=null;
                this.Size=0;
                return true;
            }

            if(End.getIdentificador().equals(Id)){
                 End.getBack().setNext(Head);
                 Head.setBack(End.getBack());
                 End=End.getBack();
                 return true;

            }

          //
            Head=Head.getNext();
            End.setNext(Head);
            Head.setBack(End);
            return true;
        }else{
            while(TempNode.getNext()!=Head){
                if(TempNode.getNext().getIdentificador().equals(Id)){
                   Node DeleNode=TempNode.getNext();
                   TempNode.setNext(TempNode.getNext().getNext());
                   TempNode.getNext().setBack(TempNode);
                   if(DeleNode==End){
                       if(TempNode.getNext()==Head){
                            End=TempNode;
                       }
                       else{
                       End=TempNode.getNext();
                       }
                   }
                   DeleNode.setNext(null);
                   DeleNode.setBack(null);

                   return true;
                }

                TempNode=TempNode.getNext();
            }
         }
        }
        return false;
    }
    @Override
    public void Print(){
       if(Head!=null){
        Node TempNode=Head.getNext();
        Head.Print();

        while(TempNode!=Head){
            TempNode.Print();
            TempNode=TempNode.getNext();
        }
           System.out.println("-------------");
      }  
    }
    @Override
    public String GraficarSinCabecera(int N,String Complemento){
        
        if(Head!=null){
            Node TempNode=Head;
            String grafo="";
           // grafo = "digraph G{";
           // grafo += "node[shape=\"box\"];\n";
            grafo += TempNode.toString()+"[label=\" " + TempNode.toString()+ " \"];\n";
            
            //
            try{
            grafo += TempNode.toString() + "->" +"LST"+TempNode.toString()+((Imagenes)TempNode.getContenido()).getListaCapas().getHead().toString()+"\n";
            } catch(Exception S){
                JOptionPane.showMessageDialog(null, "Se Intenta Generar Imagen Sin Capa", "Problema", JOptionPane.INFORMATION_MESSAGE);
            }
            grafo += ((Imagenes)TempNode.getContenido()).getListaCapas().GraficarSinCabecera(1,TempNode.toString())+"\n";
            //
            String Rank="{rank=same;"+TempNode.toString()+";";
            if (TempNode.getBack() != null) {
                grafo += TempNode.toString() + "->" + TempNode.getBack().toString() + "\n";
            }
            if (TempNode.getNext() != null) {
                grafo += TempNode.toString() + "->" + TempNode.getNext().toString() + "\n";
            }
            TempNode = TempNode.getNext();
            while (TempNode != Head) {
                Rank+=TempNode.toString()+";";
                grafo += TempNode.toString() + "[label=\" " + TempNode.toString() + " \"];\n";
                if (TempNode.getBack() != null) {
                    grafo += TempNode.toString() + "->" + TempNode.getBack().toString() + "\n";
                }
                if (TempNode.getNext() != null) {
                    grafo += TempNode.toString() + "->" + TempNode.getNext().toString() + "\n";
                }
                //
                grafo += TempNode.toString() + "->" +"LST"+TempNode.toString()+((Imagenes)TempNode.getContenido()).getListaCapas().getHead().toString()+"\n";
                grafo += ((Imagenes) TempNode.getContenido()).getListaCapas().GraficarSinCabecera(1,TempNode.toString())+"\n";
             //

                TempNode = TempNode.getNext();
            }
           // grafo += "}";
            Rank+="}\n";
            return grafo+Rank;
        }
        return "";
    }
    public String Graficar(){
        
        if(Head!=null){
            Node TempNode=Head;
            String grafo="";
            grafo = "digraph G{";
            grafo += "node[shape=\"box\"];\n";
            grafo += TempNode.toString()+"[label=\" " + TempNode.toString()+ " \"];\n";
            if(TempNode.getBack()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getBack().toString()+"\n";
            if(TempNode.getNext()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getNext().toString()+"\n";
            TempNode=TempNode.getNext();
            while(TempNode!=Head){
                grafo += TempNode.toString()+"[label=\" " + TempNode.toString()+ " \"];\n";
                if(TempNode.getBack()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getBack().toString()+"\n";
                if(TempNode.getNext()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getNext().toString()+"\n";
                TempNode=TempNode.getNext();
            }


           
            grafo += "}";
            return grafo;
        }
        return "";
    }
    
}
