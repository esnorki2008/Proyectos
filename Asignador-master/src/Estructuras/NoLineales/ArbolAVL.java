/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.NoLineales;

import Estructuras.Lineales.Queue;
import Nodos.Node;
import Nodos.NodoAVL;

import javax.swing.JOptionPane;

/**
 *
 * @author lizandro
 */
public class ArbolAVL {
    public NodoAVL Raiz; 
    public ArbolAVL(){
        Raiz=null;
    }
   
    public void Agregar(int Clave, Object Tipo){
        NodoAVL nuevo= new NodoAVL(Clave,Tipo);
        //System.out.println("Insertar "+Clave);
        if(Raiz==null)
            Raiz=nuevo;
        else
            Raiz=insertarAVL(nuevo,Raiz);
    }
    public void Eliminar(int Clave){
        ArbolAVL Nuevo= new ArbolAVL();
        Queue Cola = new Queue("");
        Cola.Enqueue(Raiz);
        while(Cola.Front()!=null){
            NodoAVL Nodito= (NodoAVL)Cola.Dequeue();
            if(Nodito.getIzq()!=null)
                Cola.Enqueue(Nodito.getIzq());
            if(Nodito.getDer()!=null)
                Cola.Enqueue(Nodito.getDer());
            if((Clave!=(Nodito.getClave()))){
                Nuevo.Agregar(Nodito.getClave(), Nodito.getContenido());
            }else{
                JOptionPane.showMessageDialog(null, "El Catedratico: "+Clave+ " Fue Eliminado", "Eliminación De Catedratico", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        this.Raiz=Nuevo.Raiz;
    }
    public void preOrden(NodoAVL Nodo){
        if(Nodo==null)
            return;
        System.out.println(Nodo.toString());
        preOrden(Nodo.getIzq());
        preOrden(Nodo.getDer());
    }
    public String Graficar(){
        if(Raiz==null)
           return "\n\n";
        else
         return  "\n  \n"+ Graph(Raiz)+"\n";
    }
    private String Graph(NodoAVL Raiz){
        String Retorno="";
        if(Raiz==null)
            return Retorno;
        
        
        if(null !=Raiz.getIzq()){
        Retorno+=Graph(Raiz.getIzq());
        Retorno+= Raiz.toString()+"->"+Raiz.getIzq().toString()+"\n";
        }
        if(null !=Raiz.getDer()){
        Retorno+=Graph(Raiz.getDer());
        Retorno+= Raiz.toString()+"->"+Raiz.getDer().toString()+"\n";
        }
        return Retorno +Raiz.CrearNodo();
    }
    public NodoAVL insertarAVL(NodoAVL nuevo,NodoAVL subAr){
        NodoAVL nuevoPadre=subAr;
       
        if( Comp(subAr,nuevo)){
            if(subAr.getIzq()==null)
                subAr.setIzq(nuevo);
            else
                subAr.setIzq(insertarAVL(nuevo, subAr.getIzq()));
                if((NodoFactor(subAr.getIzq())-(NodoFactor(subAr.getDer())))==2)
                    if(Comp(subAr.getIzq(),nuevo))
                        nuevoPadre=rotacionIzquierda(subAr);
                    else
                        nuevoPadre=rotacionDobleIzquierda(subAr);
        }else if(Comp(nuevo,subAr)){
             if(subAr.getDer()==null)
                subAr.setDer(nuevo);
            else
                subAr.setDer(insertarAVL(nuevo, subAr.getDer()));
                if((NodoFactor(subAr.getIzq())-(NodoFactor(subAr.getDer())))==-2)
                    if(Comp(nuevo,subAr.getDer()))
                        nuevoPadre=rotacionDerecha(subAr);
                    else
                        nuevoPadre=rotacionDobleDerecha(subAr);
        }else{
            System.out.println("Nodo Duplicado");
        }
        if((subAr.getIzq()==null)&&(subAr.getDer()!=null))
            subAr.setFE(subAr.getDer().getFE()+1);
        else if((subAr.getDer()==null)&&(subAr.getIzq()!=null))
            subAr.setFE(subAr.getIzq().getFE()+1);
        else
            subAr.setFE(Math.max(NodoFactor(subAr.getIzq()), NodoFactor(subAr.getDer()))+1);
        return nuevoPadre;
    }
    
    private Object Buscar(NodoAVL Raiz, int  Dato){
        if(Raiz==null){
            //System.out.println("No Encontrado "+Dato);
            return null;
        }

        if(Raiz.getClave()==(Dato))
            return Raiz.getContenido();
        else
        if((Raiz.getClave()>Dato))
            return Buscar(Raiz.getIzq(),Dato);
        else
            return Buscar(Raiz.getDer(),Dato);
    }
    public Object Buscar(int  Dato){
      if(Raiz==null)
          return null;
      else
          return Buscar(this.Raiz,Dato);
    }
    
    public boolean Comp(NodoAVL N1,NodoAVL N2){
        if(N1.getClave()>N2.getClave())
            // Izzquierda
             return true;
        else
            //Derecha
            return false;
    }
    
    public int NodoFactor(NodoAVL Nodo){
        if(Nodo==null)
            return -1;
        else
            return Nodo.getFE();
    }
    public NodoAVL rotacionIzquierda(NodoAVL Rota){
        NodoAVL auxiliar=Rota.getIzq();
        Rota.setIzq(auxiliar.getDer());
        auxiliar.setDer(Rota);
        Rota.setFE(Math.max(NodoFactor(Rota.getIzq()), NodoFactor(Rota.getDer()))+1);
        auxiliar.setFE(Math.max(NodoFactor(auxiliar.getIzq()), NodoFactor(auxiliar.getDer()))+1);
        return auxiliar;
    }
    public NodoAVL rotacionDerecha(NodoAVL Rota){
        NodoAVL auxiliar=Rota.getDer();
        Rota.setDer(auxiliar.getIzq());
        auxiliar.setIzq(Rota);
        Rota.setFE(Math.max(NodoFactor(Rota.getIzq()), NodoFactor(Rota.getDer()))+1);
        auxiliar.setFE(Math.max(NodoFactor(auxiliar.getIzq()), NodoFactor(auxiliar.getDer()))+1);
        return auxiliar;
    }
    public NodoAVL rotacionDobleIzquierda(NodoAVL Rota){
        NodoAVL temporal;
        Rota.setIzq(rotacionDerecha(Rota.getIzq()));
        temporal=rotacionIzquierda(Rota);
        return temporal;
    }
    public NodoAVL rotacionDobleDerecha(NodoAVL Rota){
        NodoAVL temporal;
        Rota.setDer(rotacionIzquierda(Rota.getDer()));
        temporal=rotacionDerecha(Rota);
        return temporal;
    }
}
