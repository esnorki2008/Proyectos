/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.Lineales;

import Nodos.Node;

/**
 *
 * @author linuxlite
 */
public class Queue extends GenericLST {
    public Queue(String Dato) {
        
    }

    @Override
    protected void Add(Node NewNode) {
        super.Size++;
         if (Head==null){
            Head=NewNode;            
        }else{
            Node TempNode= Head;
            while(TempNode.getNext()!=null){
                TempNode=TempNode.getNext();
            }
            TempNode.setNext(NewNode);
        }
    }
    public void Enqueue(Node NewNode){
        Add(NewNode);
    }
    public Node Dequeue(){
        return super.Delete();
    }
    public Node Front(){
       return super.Get();
    }

  
}
