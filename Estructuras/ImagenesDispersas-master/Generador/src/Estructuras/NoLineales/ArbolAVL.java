/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.NoLineales;

import Estructuras.Lineales.Queue;
import Nodos.Node;
import Nodos.NodoAVL;
import Objetos.Imagenes;
import Objetos.Usuarios;
import javax.swing.JOptionPane;

/**
 *
 * @author lizandro
 */
public class ArbolAVL {
    public NodoAVL raiz; 
    public ArbolAVL(){
        raiz=null;
    }
    public void EliminarImagen(NodoAVL Nodo,Imagenes Eliminar){
        if(Nodo==null)
            return;
        Usuarios User=(Usuarios)Nodo.getContenido();
        User.EliminarImagenes(Eliminar);
        EliminarImagen(Nodo.getNIzq(),Eliminar);
        EliminarImagen(Nodo.getNDer(),Eliminar);
    }
    public void insertar(String Clave, Object Tipo){
        NodoAVL nuevo= new NodoAVL(Clave,Tipo);
        if(raiz==null)
            raiz=nuevo;
        else
            raiz=insertarAVL(nuevo,raiz);
    }
    public void Eliminar(String Clave){
        ArbolAVL Nuevo= new ArbolAVL();
        Queue Cola = new Queue("");
        Cola.Enqueue(new Node(""+0,raiz));
        int ID=1;
        while(Cola.Front()!=null){
            NodoAVL Nodito= (NodoAVL)Cola.Dequeue().getContenido();
            if(Nodito.getNIzq()!=null)
                Cola.Enqueue(new Node(""+ID,Nodito.getNIzq()));
            if(Nodito.getNDer()!=null)
                Cola.Enqueue(new Node(""+ID,Nodito.getNDer()));
            if(!(Clave.toLowerCase().equals(Nodito.getClave().toLowerCase()))){
                Nuevo.insertar(Nodito.getClave(), Nodito.getContenido());
            }else{
                JOptionPane.showMessageDialog(null, "El Usuario: "+Clave+ " Fue Eliminado", "Eliminación De Usuarios", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        this.raiz=Nuevo.raiz;
    }
    public void preOrden(NodoAVL Nodo){
        if(Nodo==null)
            return;
        System.out.println(Nodo.toString());
        preOrden(Nodo.getNIzq());
        preOrden(Nodo.getNDer());
    }
    public String Graficar(){
        if(raiz==null)
           return "\n ARBOL VACIO NO SE PUEDE GRAFIcAR \n";
        else
         return  "\n digraph G{ \n"+ Graph(raiz)+"}\n";
    }
    private String Graph(NodoAVL Raiz){
        String Retorno="";
        if(Raiz==null)
            return Retorno;
        
        
        if(null !=Raiz.getNIzq()){
        Retorno+=Graph(Raiz.getNIzq());
        Retorno+= Raiz.toString()+"->"+Raiz.getNIzq().toString()+"\n";
        }
        if(null !=Raiz.getNDer()){
        Retorno+=Graph(Raiz.getNDer());
        Retorno+= Raiz.toString()+"->"+Raiz.getNDer().toString()+"\n";
        }
        return Retorno +Raiz.CrearNodo();
    }
    public NodoAVL insertarAVL(NodoAVL nuevo,NodoAVL subAr){
        NodoAVL nuevoPadre=subAr;
       
        if( Comp(subAr,nuevo)){
            if(subAr.getNIzq()==null)
                subAr.setNIzq(nuevo);
            else
                subAr.setNIzq(insertarAVL(nuevo, subAr.getNIzq()));
                if((NodoFactor(subAr.getNIzq())-(NodoFactor(subAr.getNDer())))==2)
                    if(Comp(subAr.getNIzq(),nuevo))
                        nuevoPadre=rotacionIzquierda(subAr);
                    else
                        nuevoPadre=rotacionDobleIzquierda(subAr);
        }else if(Comp(nuevo,subAr)){
             if(subAr.getNDer()==null)
                subAr.setNDer(nuevo);
            else
                subAr.setNDer(insertarAVL(nuevo, subAr.getNDer()));
                if((NodoFactor(subAr.getNIzq())-(NodoFactor(subAr.getNDer())))==-2)
                    if(Comp(nuevo,subAr.getNDer()))
                        nuevoPadre=rotacionDerecha(subAr);
                    else
                        nuevoPadre=rotacionDobleDerecha(subAr);
        }else{
            System.out.println("Nodo Duplicado");
        }
        if((subAr.getNIzq()==null)&&(subAr.getNDer()!=null))
            subAr.setFE(subAr.getNDer().getFE()+1);
        else if((subAr.getNDer()==null)&&(subAr.getNIzq()!=null))
            subAr.setFE(subAr.getNIzq().getFE()+1);
        else
            subAr.setFE(Math.max(NodoFactor(subAr.getNIzq()), NodoFactor(subAr.getNDer()))+1);
        return nuevoPadre;
    }
    
    public NodoAVL Buscar(NodoAVL Raiz, String  Dato){
        if(Raiz==null){
            System.out.println("No Encontrado");
            return null;
        }
        else
        if(Raiz.getClave().toLowerCase().equals(Dato.toLowerCase()))
            return Raiz;
        else
        if(CompStr(Raiz.getClave(),Dato))
            return Buscar(Raiz.getNIzq(),Dato);
        else
            return Buscar(Raiz.getNDer(),Dato);
    }
    
    public boolean CompStr(String N1,String N2){
        if(N1.toString().toLowerCase().compareTo(N2.toString().toLowerCase())>0)
            // Izzquierda
             return true;
        else
            //Derecha
            return false;
    }
    public boolean Comp(NodoAVL N1,NodoAVL N2){
        if(N1.toString().toLowerCase().compareTo(N2.toString().toLowerCase())>0)
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
        NodoAVL auxiliar=Rota.getNIzq();
        Rota.setNIzq(auxiliar.getNDer());
        auxiliar.setNDer(Rota);
        Rota.setFE(Math.max(NodoFactor(Rota.getNIzq()), NodoFactor(Rota.getNDer()))+1);
        auxiliar.setFE(Math.max(NodoFactor(auxiliar.getNIzq()), NodoFactor(auxiliar.getNDer()))+1);
        return auxiliar;
    }
    public NodoAVL rotacionDerecha(NodoAVL Rota){
        NodoAVL auxiliar=Rota.getNDer();
        Rota.setNDer(auxiliar.getNIzq());
        auxiliar.setNIzq(Rota);
        Rota.setFE(Math.max(NodoFactor(Rota.getNIzq()), NodoFactor(Rota.getNDer()))+1);
        auxiliar.setFE(Math.max(NodoFactor(auxiliar.getNIzq()), NodoFactor(auxiliar.getNDer()))+1);
        return auxiliar;
    }
    public NodoAVL rotacionDobleIzquierda(NodoAVL Rota){
        NodoAVL temporal;
        Rota.setNIzq(rotacionDerecha(Rota.getNIzq()));
        temporal=rotacionIzquierda(Rota);
        return temporal;
    }
    public NodoAVL rotacionDobleDerecha(NodoAVL Rota){
        NodoAVL temporal;
        Rota.setNDer(rotacionIzquierda(Rota.getNDer()));
        temporal=rotacionDerecha(Rota);
        return temporal;
    }
}
