/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoCabecera;
import Nodos.NodoMatriz;
import Nodos.NodoOrtogonal;


/**
 *
 * @author lizandro
 */
public class LDHorizontal {
    int Size=0;
    NodoOrtogonal Head;
    NodoOrtogonal End;
    public NodoOrtogonal Cabeza(){return Head;}
    public String getHead(){return Head.toString();}
    public int Count(){return Size;}
    public NodoOrtogonal Fin(){
        NodoOrtogonal TempNode=Head;
        while(TempNode.getNext()!=null){
            TempNode=TempNode.getNext();   
        }
        End=TempNode;
        return TempNode;
    }
    public String Nivel(String Nombre){
        
        if(Head!=null){
            NodoOrtogonal TempNode=Head;
            
            String Rank="{ rank=same; Y"+TempNode.getIdY()+";";
        //    grafo = "digraph G{";
        //    grafo += "node[shape=\"box\"];";
           
           
            
            while(TempNode.getNext()!=null){
                
                Rank +=TempNode.toString()+";";
                TempNode=TempNode.getNext();
            }
            
            Rank +=TempNode.toString()+"; }";
//    grafo += "}";
            return Rank;
        }
        return "";
    }
    public void Add(NodoOrtogonal NewNode) {
        Size++;
        if (Head==null){
            Head=NewNode;
        }else{
            NodoOrtogonal TempNode= Head; 
            if(TempNode==Head &&(Comparacion(TempNode,NewNode))){
                        NewNode.setNext(TempNode);
                      //
                        NewNode.setBack(TempNode.getBack());
                        TempNode.setBack(NewNode);
                       //
                        Head=NewNode;
                        return;
            }  
            while(TempNode.getNext()!=null){
                if(Comparacion(TempNode,NewNode)){
                  /*  NewNode.setNext(TempNode.getNext() );
                    //
                    System.out.println("/////////");
                    TempNode.getNext().setBack(NewNode);
                    NewNode.setBack(TempNode);
                    //
                    TempNode.setNext(NewNode);  */
                    
                    TempNode.getBack().setNext(NewNode);
                    NewNode.setBack(TempNode.getBack());
                    NewNode.setNext(TempNode);
                    TempNode.setBack(NewNode);
                    
                    return ;
                }
                TempNode=TempNode.getNext();                
            }
            if(Comparacion(TempNode,NewNode)){
            //System.out.println(TempNode.getIdY()+"       "+NewNode.getIdY()+"    CCCC  ");
                TempNode.getBack().setNext(NewNode);
                NewNode.setBack(TempNode.getBack());
                NewNode.setNext(TempNode);
                TempNode.setBack(NewNode);
                return ;
            }
            
            NewNode.setBack(TempNode);
            TempNode.setNext(NewNode);
        }
    }
    public void CopiarContenido(Matriz Nueva){
        if(Head!=null){
            NodoOrtogonal TempNode=Head;
            while(TempNode.getNext()!=null){
                TempNode.Copiar(Nueva);
                TempNode=TempNode.getNext();
            }
            TempNode.Copiar(Nueva);
        }
    }
    
    public Boolean Comparacion(NodoOrtogonal N1,NodoOrtogonal N2){

        
        if(N1.getId()>N2.getId())
            return true;
        else  if(N1.getId()<N2.getId())
            return false;
        else
        if(N1.getId()==N2.getId()){
            
            if(N1.getIdY()>N2.getIdY())
                return true;
            else 
                return false;
        }else{
            return false;
        }
    
    }
    public  NodoCabecera Find(int Id){
        NodoCabecera TempNode=(NodoCabecera)Head;
        while(TempNode!=null){
            if(Id==TempNode.getId()){
                return TempNode;
            }           
            TempNode=(NodoCabecera)TempNode.getNext();
        }
        return null;
    }
    public String Graficar(String Nombre){
        
        if(Head!=null){
            NodoOrtogonal TempNode=Head;
            
            String Rank="{ rank=min;M;X"+TempNode.getId()+";";
        //    grafo = "digraph G{";
        //    grafo += "node[shape=\"box\"];";
           
            while(TempNode.getNext()!=null){
                Rank +=TempNode.toString()+";";
                TempNode=TempNode.getNext();
            }
            Rank +=TempNode.toString()+"; }";
//    grafo += "}";
            return Rank;
        }
        return "";
    }
     public String Graficar(){
        
        if(Head!=null){
            NodoOrtogonal TempNode=Head;
            String grafo="";
        //    grafo = "digraph G{";
        //    grafo += "node[shape=\"box\"];";
            while(TempNode.getNext()!=null){
                
                grafo += TempNode.toString()+"[label=\" " + TempNode.DatoG()+" \" ,group="+(TempNode.getId()+1)+ " ];\n";
                grafo +=TempNode.Graficar();
                TempNode=TempNode.getNext();
            }
                grafo += TempNode.toString()+"[label=\" " + TempNode.DatoG()+" \" ,group="+(TempNode.getId()+1)+ " ];\n";
                grafo +=TempNode.Graficar();

//    grafo += "}";
            return grafo;
        }
        return "";
    }
     public NodoMatriz Find(String Clave){
        NodoMatriz TempNode=(NodoMatriz)Head;
        while(TempNode!=null){
            if(Clave.equals(TempNode.getClave())){
                return TempNode;
            }           
            TempNode=(NodoMatriz)TempNode.getNext();
        }
        return null;
    }

}
