/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Lineales;

import Nodos.Node;

/**
 *
 * @author lizandro
 */
public class ListaDoble extends GenericLST {
    @Override
    public boolean Delete(String Id){
        return super.Delete(Id);
    }
    
     @Override
    public void Add(Node NewNode) {
        super.Size++;
        if (Head==null){
            Head=NewNode;
        }else{
            
            Node TempNode= Head; 
            if(TempNode==Head &&(Comp(TempNode,NewNode))){

                
                        NewNode.setNext(TempNode);
                      //
                        NewNode.setBack(TempNode.getBack());
                        TempNode.setBack(NewNode);
                       //
                        Head=NewNode;
                        return;
            }  
            while(TempNode.getNext()!=null){
                if(Comp(TempNode.getNext(),NewNode)){
                    NewNode.setNext(TempNode.getNext() );
                    //
                    TempNode.getNext().setBack(NewNode);
                    NewNode.setBack(TempNode);
                    //
                    TempNode.setNext(NewNode);  
                    return ;
                }
                TempNode=TempNode.getNext();                
            }       
            NewNode.setBack(TempNode);
            TempNode.setNext(NewNode);
        }
    }
     
    
}
