/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Lineales;

import Nodos.NodoSTR;
import javax.swing.JOptionPane;

/**
 *
 * @author lizandro
 */
public class ListaCircular {
private NodoSTR Head;
private NodoSTR End;
private int Size=0;
public ListaCircular(){
    this.Size=0;
    this.Head=null;
    this.End=null;
}
    private boolean Comp(NodoSTR N1,NodoSTR N2){
        if(N1.toString().toLowerCase().trim().compareTo(N2.toString().toLowerCase().trim())>0)
            // Izzquierda
             return true;
        else
            //Derecha
            return false;
    }
    public void Add(String Clave,Object Inf) {
        NodoSTR NewNodoSTR = new NodoSTR(Clave.trim().toLowerCase(),Inf);
        setSize(getSize() + 1);
        if (Head==null){
            setHead(NewNodoSTR);    
            getHead().setNext(getHead());
            getHead().setBack(getHead());
            End=getHead();
        }else{
            NodoSTR TempNodoSTR= getHead();
            if(Comp(TempNodoSTR,NewNodoSTR)){
                Head=NewNodoSTR;
                Head.setNext(TempNodoSTR);
                TempNodoSTR.getBack().setNext(Head);
                Head.setBack(TempNodoSTR.getBack());
                TempNodoSTR.setBack(Head);
                return;
            }
            
            if(Comp(NewNodoSTR,End)){
                End.setNext(NewNodoSTR);
                NewNodoSTR.setBack(End);
                NewNodoSTR.setNext(Head);
                Head.setBack(NewNodoSTR);
                End=NewNodoSTR;
                return;
            }
            TempNodoSTR=TempNodoSTR.getNext();
            while(End!=TempNodoSTR){
                if(Comp(TempNodoSTR,NewNodoSTR)){
                    TempNodoSTR.getBack().setNext(NewNodoSTR);
                    TempNodoSTR.setBack(NewNodoSTR);
                    NewNodoSTR.setNext(TempNodoSTR);
                    NewNodoSTR.setBack(TempNodoSTR.getBack());
                    return;
                }
                TempNodoSTR=TempNodoSTR.getNext();
            }
            
            (End.getBack()).setNext(NewNodoSTR);
            NewNodoSTR.setBack(End.getBack());
            NewNodoSTR.setNext(End);
            Head.setBack(End);
            //End.setNext(Head);
            //System.out.println(Head+"//");
        }
    }
    public void Add1(String Clave,Object Inf) {
        NodoSTR NewNodoSTR = new NodoSTR(Clave.trim().toLowerCase(),Inf);
        setSize(getSize() + 1);
         if (Head==null){
            setHead(NewNodoSTR);    
            getHead().setNext(getHead());
            getHead().setBack(getHead());
            End=getHead();
        }else{
            
           // if(Head)
             
            NodoSTR TempNodoSTR= getHead();
            if(Comp(TempNodoSTR,NewNodoSTR)){
                Head.setNext(NewNodoSTR);
                NewNodoSTR.setBack(End);
                NewNodoSTR.setNext(Head);
                Head.setBack(NewNodoSTR);
                Head=NewNodoSTR;
                
                return;
            }
            if(Comp(NewNodoSTR,End)){
                End.setNext(NewNodoSTR);
                NewNodoSTR.setBack(End);
                NewNodoSTR.setNext(Head);
                Head.setBack(NewNodoSTR);
                End=NewNodoSTR;
                return;
            }
            
            TempNodoSTR=TempNodoSTR.getNext();
            while(End!=TempNodoSTR){
                if(Comp(TempNodoSTR,NewNodoSTR)){
                    TempNodoSTR.getBack().setNext(NewNodoSTR);
                    TempNodoSTR.setBack(NewNodoSTR);
                    NewNodoSTR.setNext(TempNodoSTR);
                    NewNodoSTR.setBack(TempNodoSTR.getBack());
                    return;
                }
                TempNodoSTR=TempNodoSTR.getNext();
            }
            End.getBack().setNext(NewNodoSTR);
            NewNodoSTR.setBack(End.getBack());
            NewNodoSTR.setNext(End);
            
            /*End=NewNodoSTR;
            End.setBack(TempNodoSTR);
            End.setNext(getHead());
            TempNodoSTR.setNext(End);
            getHead().setBack(End);*/
        }
    }
    public Object Find(String Id){
        if(getHead()!=null){
        NodoSTR TempNodoSTR=getHead().getNext();
        if( getHead().getIdentificador().toLowerCase().trim().equals(Id.toLowerCase().trim()))return getHead().getContenido();
        while(TempNodoSTR!=getHead()){
            if(TempNodoSTR.getIdentificador().toLowerCase().trim().equals(Id.toLowerCase().trim()))return TempNodoSTR.getContenido();
            TempNodoSTR=TempNodoSTR.getNext();
        }
      }  
        return null;
    }
     public boolean Delete(String Id){
        setSize(getSize() - 1);
        if(getHead()!=null){
        NodoSTR TempNodoSTR=getHead();

        if((getHead().getIdentificador()).equals(Id)){

            if( getHead()==End){
                    setHead(End=null);
                    this.setSize(0);
                return true;
            }

            if(End.getIdentificador().equals(Id)){
                 End.getBack().setNext(getHead());
                    getHead().setBack(End.getBack());
                 End=End.getBack();
                 return true;

            }

          //
                setHead(getHead().getNext());
            End.setNext(getHead());
                getHead().setBack(End);
            return true;
        }else{
            while(TempNodoSTR.getNext()!=getHead()){
                if(TempNodoSTR.getNext().getIdentificador().equals(Id)){
                   NodoSTR DeleNodoSTR=TempNodoSTR.getNext();
                   TempNodoSTR.setNext(TempNodoSTR.getNext().getNext());
                   TempNodoSTR.getNext().setBack(TempNodoSTR);
                   if(DeleNodoSTR==End){
                       if(TempNodoSTR.getNext()==getHead()){
                            End=TempNodoSTR;
                       }
                       else{
                       End=TempNodoSTR.getNext();
                       }
                   }
                   DeleNodoSTR.setNext(null);
                   DeleNodoSTR.setBack(null);

                   return true;
                }

                TempNodoSTR=TempNodoSTR.getNext();
            }
         }
        }
        return false;
    }
    public void Print(){
       if(getHead()!=null){
        NodoSTR TempNodoSTR=getHead().getNext();
            getHead().Print();

        while(TempNodoSTR!=getHead()){
            TempNodoSTR.Print();
            TempNodoSTR=TempNodoSTR.getNext();
        }
           System.out.println("-------------");
      }  
    }
     
    public String Graficar(){
        
        if(getHead()!=null){
            NodoSTR TempNodoSTR=getHead();
            String grafo="";
            grafo = "digraph G{";
            grafo += "NodoSTR[shape=\"box\"];\n";
            grafo += TempNodoSTR.toString()+"[label=\" " + TempNodoSTR.toString()+ " \"];\n";
            if(TempNodoSTR.getBack()!=null)
                grafo += TempNodoSTR.toString()+"->"+TempNodoSTR.getBack().toString()+"\n";
            if(TempNodoSTR.getNext()!=null)
                grafo += TempNodoSTR.toString()+"->"+TempNodoSTR.getNext().toString()+"\n";
            TempNodoSTR=TempNodoSTR.getNext();
            while(TempNodoSTR!=getHead()){
                grafo += TempNodoSTR.toString()+"[label=\" " + TempNodoSTR.toString()+ " \"];\n";
                if(TempNodoSTR.getBack()!=null)
                grafo += TempNodoSTR.toString()+"->"+TempNodoSTR.getBack().toString()+"\n";
                if(TempNodoSTR.getNext()!=null)
                grafo += TempNodoSTR.toString()+"->"+TempNodoSTR.getNext().toString()+"\n";
                TempNodoSTR=TempNodoSTR.getNext();
            }


           
            grafo += "}";
            return grafo;
        }
        return "";
    }

    /**
     * @return the Head
     */
    public NodoSTR getEnd() {
        return End;
    }
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
