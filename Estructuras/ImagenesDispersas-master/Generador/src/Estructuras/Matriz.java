/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Nodos.NodoCabecera;
import Nodos.NodoMatriz;


/**
 *
 * @author lizandro
 */
public class Matriz {
    LDHorizontal ListaX=new LDHorizontal();
    LDVertical ListaY=new LDVertical();
    
    public void Agregar(int X,int Y,Object Dato){
        NodoCabecera CabeceraX=this.ListaX.Find(X);;
        NodoCabecera CabeceraY=this.ListaY.Find(Y);;
        if(CabeceraX==null){
            CabeceraX= new NodoCabecera(X,0,new LDVertical());
            ListaX.Add(CabeceraX);
        }        
        if(CabeceraY==null){
            CabeceraY= new NodoCabecera(0,Y,new LDHorizontal());
            ListaY.Add(CabeceraY);
        } 
        NodoMatriz Busqueda=null; 
        if(CabeceraX.getListaY().Count()<CabeceraY.getListaX().Count())
            Busqueda=CabeceraX.getListaY().Find(X+","+Y);
        else
            Busqueda=CabeceraY.getListaX().Find(X+","+Y);
        
        if(Busqueda==null){
            Busqueda=new NodoMatriz(X,Y,X+","+Y,Dato);
            CabeceraX.getListaY().Add(Busqueda);
            CabeceraY.getListaX().Add(Busqueda);
        }else{
           // System.out.println(Busqueda.toString() +"|||||||||||||||");
            //System.out.println("Ya Existe Ese Nodo");
        }
            
    }
    public String Graficar(){
        String Retorno ="\n digraph G{\ngraph [splines = ortho];\n";
        Retorno=Retorno+"M[label=\"Matriz\" ,group=1 ];\n";
        Retorno=Retorno+"M->"+ListaY.Head.toString()+"\n";
        Retorno=Retorno+"M->"+ListaX.Head.toString()+"\n";
        Retorno=Retorno+"\n"+this.ListaX.Graficar("");
        Retorno=Retorno+"\n"+this.ListaX.Graficar();
        Retorno=Retorno+"\n"+this.ListaY.Graficar();
        Retorno=Retorno+"{rank=max;"+this.ListaY.Fin()+";}";
        return Retorno+"\n }\n";
    }   
    public Matriz GenerarMatrizCopia(Matriz Nueva){
        this.ListaX.CopiarContenido(Nueva);
        this.ListaY.CopiarContenido(Nueva);
        return Nueva;
    }
    public String GenerarImagen(){
        if(this.ListaX.Cabeza()==null || this.ListaY.Cabeza()==null)
            return "";
        String Retorno ="\n digraph dibujo{\n node [shape=plaintext]\n a[label=<<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">\n";
        int X=this.ListaX.Fin().getId();
        int Y=this.ListaY.Fin().getIdY();
        for(int j=0;j<Y;j++){
            Retorno+="\n<TR>";
            for(int i=0;i<X;i++){
                NodoMatriz Busque=this.Buscar(i, j);
                if(Busque!=null){
                    Retorno+="<TD BGCOLOR=\""+Busque.getData()+"\"></TD>";
                }else{
                    Retorno+="<TD BGCOLOR=\"#FFFFFF\"></TD>";
                }
            }
             Retorno+="</TR>";
        }
    
        
       
        return Retorno+"\n </TABLE>>]; \n }\n";
    }
    public NodoMatriz Buscar(int X,int Y){
        NodoMatriz Busqueda=null;
        NodoCabecera CabeceraX=this.ListaX.Find(X);;
        NodoCabecera CabeceraY=this.ListaY.Find(Y);;
        if(CabeceraX==null || CabeceraY==null )
            return null;
        if(CabeceraX.getListaY().Count()<CabeceraY.getListaX().Count())
            Busqueda=CabeceraX.getListaY().Find(X+","+Y);
        else
             Busqueda=CabeceraY.getListaX().Find(X+","+Y);
        return Busqueda;
    }
}
