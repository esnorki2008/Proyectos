/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.NoLineales;

import Nodos.NodoB;


/**
 *
 * @author lizandro
 */
public class ArbolB {
    public NodoB[] Raiz= new NodoB[5];
    public void Agregar(int Clave,Object Contenido){
        if(Raiz[0]==null)
            Raiz[0]=new NodoB(Clave,Contenido);
        else
            this.Agregar(Clave, Contenido, Raiz);
        
    }
    public Object Buscar(int Clave){
        return Buscar(Clave,Raiz);
    }
    private Object Buscar(int Clave, NodoB[] Raiz) {
        Object Salida = null;
        if (Raiz == null) {
            return Salida;
        }
        for (int i = 0; i < Raiz.length; i++) {

            if (Raiz[i] != null) {
                if (Raiz[i].getClave() == Clave) {
                    Salida= Raiz[i].getContenido();
                } else if (Raiz[i].getClave() > Clave) {
                    Salida=Buscar(Clave, Raiz[i].getPagIzq());
                } else {
                    Salida=Buscar(Clave, Raiz[i].getPagDer());
                }
            }
            
            if(Salida!=null){
                return Salida;
            }
        }

        return Salida;
    }
    private int Ocupado(NodoB[] Raiz){
        for(int i=0;i<Raiz.length;i++){
            if(Raiz[i]==null)
                return i;
        }
        return 4;
    }
    private boolean EsHoja(NodoB[] Raiz){
        return Raiz[0].EsHoja();
    }
    public String Graficar(){
      //  System.out.println(this.Raiz[1]);
       // return"";
       return " digraph G{ \n node [shape=record]; \n"+Graficar(this.Raiz)+"\n}";
    }
    
    private String Graficar(NodoB[] Raiz){
        if (Raiz == null) {
            return "";
        }
        String Datos="";
        String Rank="{ rank=same;";
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Datos+=Graficar(Raiz[i].getPagIzq());
                Datos+=(Raiz[i]).NodoGrafico()+"\n";
                Rank+=(Raiz[i]).getClave()+";";
                // Hash[ shape=Mrecord  label="<>|"] 
                if(Raiz[i].getPagIzq()!=null){
                    Datos+=Raiz[i].getClave()+" -> "+Raiz[i].getPagIzq()[0].getClave()+"\n";
                } 
                if(Raiz[i].getPagDer()!=null){
                    Datos+=Raiz[i].getClave()+" -> "+Raiz[i].getPagDer()[0].getClave()+"\n";
                } 
                if (Raiz[i + 1] == null) {
                    Datos+=Graficar(Raiz[i].getPagDer());
                }else{
                    Datos+=Raiz[i].getClave()+" -> "+Raiz[i+1].getClave()+"\n";
                }
            } 
        }
        Rank+="}\n";
        return Datos+Rank;
    }
    public void Imprimir(){
        Imprimir(this.Raiz);
    }
    private void Imprimir(NodoB[] Raiz) {
        if (Raiz == null) {
            return;
        }
        for (int i = 0; i < Raiz.length; i++) {
            if (Raiz[i] != null) {
                Imprimir(Raiz[i].getPagIzq());
                System.out.println(Raiz[i]);
                if (Raiz[i + 1] == null) {
                    Imprimir(Raiz[i].getPagDer());
                }
            } else {
                return;
            }
        }
    }
    private NodoB GenCentral(NodoB[] Raiz){
        Raiz[2].setPagIzq(GenIzq(Raiz));
        Raiz[2].setPagDer(GenDerecho(Raiz));
        Raiz[0]=Raiz[2];
        for(int i=1;i<Raiz.length;i++){
            Raiz[i]=null;
        }
        return Raiz[0];
    }
    /*private NodoB[] GenCentral(NodoB[] Raiz){
        Raiz[2].setPagIzq(GenIzq(Raiz));
        Raiz[2].setPagDer(GenDerecho(Raiz));
        Raiz[0]=Raiz[2];
        for(int i=1;i<Raiz.length;i++){
            Raiz[i]=null;
        }
        return Raiz;
    }*/
    private NodoB[] GenDerecho(NodoB[] Raiz){
        NodoB[] Nuevo = new NodoB[5];
        Nuevo[0]=Raiz[3];
        Nuevo[1]=Raiz[4];
        return Nuevo;
    }
    private NodoB[] GenIzq(NodoB[] Raiz){
        NodoB[] Nuevo = new NodoB[5];
        Nuevo[0]=Raiz[0];
        Nuevo[1]=Raiz[1];
        return Nuevo;
    }
   
    private NodoB Agregar(int Clave, Object Contenido, NodoB[] Raiz) {
        NodoB Llenado=null;
        
        if (EsHoja(Raiz)) {
            
            for (int i = 0; i < Raiz.length; i++) {
                
                if (Raiz[i] == null) {
                    Raiz[i] = new NodoB(Clave, Contenido);
                    break;
                } else if (Raiz[i].getClave() > Clave) {
                    Raiz = Mover(Raiz, i);
                    Raiz[i] = new NodoB(Clave, Contenido);
                    break;
                }
            }
            
        }else{
            
            for (int i = 0; i < Raiz.length; i++) {
                if(Raiz[i]!=null){
                if (Raiz[i + 1] != null) {
                    if (Raiz[i].getClave() > Clave) {
                        Llenado=Agregar(Clave, Contenido, Raiz[i].getPagIzq());
                    }else if (Raiz[i+1].getClave() > Clave) {
                        Llenado=Agregar(Clave, Contenido, Raiz[i].getPagDer());
                    }
                } else {
                    if (Raiz[i].getClave() > Clave) {
                        Llenado=Agregar(Clave, Contenido, Raiz[i].getPagIzq());
                    } else if (Raiz[i].getClave() < Clave) {
                        Llenado=Agregar(Clave, Contenido, Raiz[i].getPagDer());
                    }
                }
                
              }  
            }
        }
        
        
        if(Llenado!=null){
            for(int i=0;i<Raiz.length;i++){
                if(Raiz[i]==null){
                    Raiz[i-1].setPagDer(Llenado.getPagIzq());
                    Raiz[i]=Llenado;
                    break;
                }
            }
        }
        if (Raiz[Raiz.length - 1] != null) {
                System.out.println(Raiz[4]);
                NodoB Nodito= GenCentral(Raiz);
                return Nodito;
        }else{
            return null;
        }
        
    }
    private NodoB[] Mover(NodoB[] Raiz,int Indice){
        for(int i=Raiz.length-1;i>Indice;i--){
            Raiz[i]=Raiz[i-1];
        }

        return Raiz;
    }
}
