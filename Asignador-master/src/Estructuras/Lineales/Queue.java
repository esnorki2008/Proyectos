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
    protected void Add(int Clave,Object Inf) {
        Node NewNode = new Node(Clave,Inf);
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
    public void Enqueue(Object Contenido){
        Add(0,Contenido);
    }
    public Object Dequeue(){
        return super.Delete();
    }
    public Object Front(){
       return super.Get();
    }

  
}
