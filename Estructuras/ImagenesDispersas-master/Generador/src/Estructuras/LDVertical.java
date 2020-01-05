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
public class LDVertical {
    int Size=0;
    NodoOrtogonal Head;
    public NodoOrtogonal End;
    public NodoOrtogonal Cabeza(){return Head;}
    public NodoOrtogonal Fin(){
        NodoOrtogonal TempNode=Head;
        while(TempNode.getUp()!=null){
            TempNode=TempNode.getUp();   
        }
        End=TempNode;
        return TempNode;
    }
    public int Count(){return Size;}
    public String getHead(){return Head.toString();}
    public void Add(NodoOrtogonal NewNode) {
        Size++;
        if (Head==null){
            Head=NewNode;
            End=NewNode;
        }else{
            NodoOrtogonal TempNode= Head; 
            if((Comparacion(TempNode,NewNode))){
                        End=Head;
                        NewNode.setUp(Head);
                        NewNode.setDown(null);
                        Head.setDown(NewNode);
                        Head=NewNode;
                        return;
            }  
            
            while(TempNode.getUp()!=null){
                if(Comparacion(TempNode,NewNode)){
                      
                   // System.out.println("C");
                    
                  /*  System.out.println("M   "+TempNode.getIdY()+"     "+NewNode.getIdY()+"  "+TempNode.getUp().getIdY());
                    NewNode.setUp(TempNode.getUp());
                    //
                    TempNode.getUp().setDown(NewNode);
                    NewNode.setDown(TempNode);
                    //
                    TempNode.setUp(NewNode);  
                    System.out.println("M   "+TempNode.getDown().getIdY()+"     "+TempNode.getUp().getIdY());*/
                    TempNode.getDown().setUp(NewNode);
                    NewNode.setDown(TempNode.getDown());
                    NewNode.setUp(TempNode);
                    TempNode.setDown(NewNode);
                    return ;
                }
                TempNode=TempNode.getUp();                
                End=TempNode;
            }
            if(Comparacion(TempNode,NewNode)){
            //System.out.println(TempNode.getIdY()+"       "+NewNode.getIdY()+"    CCCC  ");
                TempNode.getDown().setUp(NewNode);
                NewNode.setDown(TempNode.getDown());
                NewNode.setUp(TempNode);
                TempNode.setDown(NewNode);
                return ;
            }
            
            End=NewNode;
            NewNode.setDown(TempNode);
            TempNode.setUp(NewNode);
        }
    }
    public Boolean Comparacion(NodoOrtogonal N1,NodoOrtogonal N2){
        if(N1.getIdY()>N2.getIdY())
            return true;
        else
        if(N1.getIdY()==N2.getIdY()){
            //System.out.println(N1.getId()+"     "+N2.getId()+"        "+N1.getIdY()+"     "+N2.getIdY());

            if(N1.getId()>N2.getId())
                return true;
            else 
                return false;
        }else{
            return false;
        }
    
    }
    public NodoCabecera Find(int Id){
        NodoOrtogonal TempNode=Head;
        while(TempNode!=null){
            if(Id==TempNode.getIdY()){
                return (NodoCabecera)TempNode;
            }      
            TempNode=TempNode.getUp();
        }
        
        return null;
    }
    /*public String Graficar(String Nombre){
        
        if(Head!=null){
            NodoOrtogonal TempNode=Head;
            
            String Rank="{ rank=same; Y"+TempNode.getIdY()+";";
        //    grafo = "digraph G{";
        //    grafo += "node[shape=\"box\"];";
           
        
            while(TempNode.getUp()!=null){
                Rank +=TempNode.toString()+";";
                TempNode=TempNode.getUp();
            }
            Rank +=TempNode.toString()+"; }";
//    grafo += "}";
            return Rank;
        }
        return "";
    }*/
    
    public String Graficar(){
        
        if(Head!=null){
            NodoOrtogonal TempNode=Head;
            String grafo="";
            while(TempNode.getUp()!=null){
                grafo += TempNode.toString()+"[label=\" " + TempNode.toString()+" \" ,group="+1+ " ];\n";
                grafo +=TempNode.Graficar();
                TempNode=TempNode.getUp();
            }
            grafo += TempNode.toString()+"[label=\" " + TempNode.toString()+" \" ,group="+1+ " ];\n";
            grafo +=TempNode.Graficar();
            return grafo;
        }
        return "";
    }
    
    public void CopiarContenido(Matriz Nueva){
        if(Head!=null){
            NodoOrtogonal TempNode=Head;
            while(TempNode.getUp()!=null){
                TempNode.Copiar(Nueva);
                TempNode=TempNode.getUp();
            }
            TempNode.Copiar(Nueva);
        }
    
    }
    
    public String Uniones(String Nombre){
        
        if(Head!=null){
            NodoOrtogonal TempNode=Head;
            String grafo="";
            while(TempNode.getUp()!=null){
                
                grafo += Nombre+"->";
                grafo +=TempNode.Graficar();
                TempNode=TempNode.getUp();
            }
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
            TempNode=(NodoMatriz)TempNode.getUp();
        }
        return null;
    }
}
