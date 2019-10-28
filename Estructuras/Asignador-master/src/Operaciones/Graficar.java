/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import Estructuras.NoLineales.ArbolAVL;
import Estructuras.NoLineales.ArbolB;
import Estructuras.NoLineales.Hashtable;
import Estructuras.NoLineales.MiniHash;
import Nodos.Node;
import Nodos.NodoB;
import Nodos.NodoSTR;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Usuario;

/**
 *
 * @author lizandro
 */
public class Graficar {
    public String GraficarEstudiantes(MiniHash[] Hash){
        String Salida = " \nrankdir=LR; subgraph cluster_Hash {rankdir=LR; tyle=filled;color=darkorchid1;    Hash[ shape=Mrecord  label=\"";
        for(int i=0;i<Hash.length;i++){
            if(Hash[i]!=null)
            Salida+="<"+Hash[i].getContenido().toString()+">"+((Estudiante)Hash[i].getContenido()).Graph()+"|\n";
            else
            Salida+="<"+"null"+i+">"+"Vacio"+"|\n";
        }
        Salida = Salida + "HashUsuario\"] \n}";
        return Salida;
    }
    public String GraficarTodo(ArbolB B,NodoSTR Head,ArbolAVL AVL,Node CabezaCu){
        String Salida="";
        Salida+=this.GraficarEdificiosYSalones(Head);
        //Salida+="subgraph cluster_sub"+"HORADE"+"{ \nnode[shape=\"box\"];  \n "+this.GraficarB(B.Raiz)+"\n}";
        Salida+=this.GraficarB2(B.Raiz);
        Salida+=this.GraficarCatedraticos(AVL);
        Salida+=this.GraficarCurso(CabezaCu);
        return Salida;
    }
   
    public String GraficarHorariosTodo(ArbolB B,NodoSTR Head,ArbolAVL AVL,Node CabezaCu){
        String Salida="";
        Salida+=this.GraficarEdificiosYSalones(Head);
        //Salida+="subgraph cluster_sub"+"HORADE"+"{ \nnode[shape=\"box\"];  \n "+this.GraficarB(B.Raiz)+"\n}";
        Salida+=this.GraficarB(B.Raiz);
        Salida+=this.GraficarCatedraticos(AVL);
        Salida+=this.GraficarCurso(CabezaCu);
        return Salida;
    }
    
   
    private String PunteroB(NodoB Nodito){
        String Salida="";
        Horario Hora = (Horario) Nodito.getContenido();
        Salida+=Hora.toString()+" -> "+Hora.getCodCurso().toString()+"\n";
        Salida+=Hora.toString()+" -> "+Hora.getCodSalon().toString()+"\n";
        Salida+=Hora.toString()+" -> "+Hora.getNumIdentificacion().toString()+"\n";
        Salida+=Hora.GenerarSalones()+"\n";
        return Salida;
    }
    /***
    public String GraficarB3(NodoB[] Raiz){
        if (Raiz == null) {
            return "";
        }
        if(Raiz[0]==null)
            return "";
        String Datos="\npag"+Raiz[0].toString()+"[shape=record    label=\"";
        //shape=record|{above|middle|below}|right
        //Salida+="<"+"null"+i+">"+"Vacio"+"|\n";
        String Izquierda = "";
        String Derecha = "";
        String Punteros="";
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Izquierda+=GraficarB3(Raiz[i].getPagIzq());
                Datos+="<"+Raiz[i].toString()+">"+Raiz[i].toString()+"|\n";
                Punteros+=PunteroB3(Raiz[i],"pag"+Raiz[0]+":");
                if(Raiz[i].getPagIzq()!=null){
                    Punteros+="pag"+Raiz[0]+":"+Raiz[i].toString()+" -> "+"pag"+Raiz[i].getPagIzq()[0].toString()+":"+Raiz[i].getPagIzq()[0].toString()+"\n";
                } 
                if(Raiz[i].getPagDer()!=null){
                    Punteros+="pag"+Raiz[0]+":"+Raiz[i].toString()+" -> "+"pag"+Raiz[i].getPagDer()[0].toString()+":"+Raiz[i].getPagDer()[0].toString()+"\n";
                } 
                if (Raiz[i + 1] == null) {
                    Derecha+=GraficarB3(Raiz[i].getPagDer());
                }else{
                   // Punteros+="pag"+Raiz[0]+":"+Raiz[i].toString()+" -> "+"pag"+Raiz[0]+":"+Raiz[i+1].toString()+"\n";
                }
            } 
        }
        Datos+="\"];";
        return Datos+Izquierda+Derecha+Punteros;
    }
    
     *private String PunteroB3(NodoB Nodito,String HermanoMayor){
        String Salida="";
        Horario Hora = (Horario) Nodito.getContenido();
        Salida+=HermanoMayor+Hora.toString()+" -> "+Hora.getCodCurso().toString()+"\n";
        Salida+=HermanoMayor+Hora.toString()+" -> "+Hora.getCodSalon().toString()+"\n";
        Salida+=HermanoMayor+Hora.toString()+" -> "+Hora.getNumIdentificacion().toString()+"\n";
        //Salida+=Hora.GenerarSalones(HermanoMayor)+"\n";
        return Salida;
    }*/
    private String GraficarB2(NodoB[] Raiz){
        if (Raiz == null) {
            return "";
        }
        String Datos="";
        String Rank="{ rank=same;";
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Datos+=GraficarB2(Raiz[i].getPagIzq());
                Datos+=(Raiz[i]).NodoGrafico()+"\n";
                Rank+=(Raiz[i]).toString()+";";
                Datos+=PunteroB(Raiz[i]);
                if(Raiz[i].getPagIzq()!=null){
                    Datos+=Raiz[i].toString()+" -> "+Raiz[i].getPagIzq()[0].toString()+"\n";
                } 
                if(Raiz[i].getPagDer()!=null){
                    Datos+=Raiz[i].toString()+" -> "+Raiz[i].getPagDer()[0].toString()+"\n";
                } 
                if (Raiz[i + 1] == null) {
                    Datos+=GraficarB2(Raiz[i].getPagDer());
                }else{
                    Datos+=Raiz[i].toString()+" -> "+Raiz[i+1].toString()+"\n";
                }
            } 
        }
        Rank+="}\n";
        return Datos+Rank;
    }
    private String GraficarB(NodoB[] Raiz){
        if (Raiz == null) {
            return "";
        }
        String Datos="";
        String Rank="{ rank=same;";
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Datos+=GraficarB(Raiz[i].getPagIzq());
                Datos+=(Raiz[i]).NodoGrafico()+"\n";
                Rank+=(Raiz[i]).toString()+";";
                Datos+=PunteroB(Raiz[i]);
                if(Raiz[i].getPagIzq()!=null){
                    Datos+=Raiz[i].toString()+" -> "+Raiz[i].getPagIzq()[0].toString()+"\n";
                } 
                if(Raiz[i].getPagDer()!=null){
                    Datos+=Raiz[i].toString()+" -> "+Raiz[i].getPagDer()[0].toString()+"\n";
                } 
                if (Raiz[i + 1] == null) {
                    Datos+=GraficarB(Raiz[i].getPagDer());
                }else{
                    Datos+=Raiz[i].toString()+" -> "+Raiz[i+1].toString()+"\n";
                }
            } 
        }
        Rank+="}\n";
        return Datos+Rank;
    }
    public String GraficarCurso(Node Head){
        if(Head!=null){
            Node TempNode=Head;
            String grafo="";
            grafo = "subgraph cluster_subCurso{"+"\nnode[shape=\"box\"];  \nstyle=filled;color=azure2\n";
            String Same="\n{rank=same;";
            while(TempNode.getNext()!=null){
                Curso Cur = (Curso)TempNode.getContenido();
                grafo += TempNode.toString()+"[label=\" " + Cur.Graph()+ " \"];\n";
                Same+=TempNode.toString()+";";
                grafo += TempNode.toString()+"->"+TempNode.getNext().toString()+"\n";
                if(TempNode.getBack()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getBack().toString()+"\n";
                TempNode=TempNode.getNext();
            }
            Curso Cur = (Curso)TempNode.getContenido();
            if(TempNode.getBack()!=null)
            grafo += TempNode.toString()+" -> "+TempNode.getBack().toString()+"\n";
            grafo += TempNode.toString().replace(".", "")+"[label=\" " +  Cur.Graph()+ " \"];\n";
            Same+=TempNode.toString()+";}\n";
            grafo += Same+"\n}";
            return grafo;
        }
        return "";
    }
    public String GraficarCatedraticos(ArbolAVL AVL){
         return "subgraph cluster_sub"+"Catedra"+"{ \nnode[shape=\"box\"]; \nstyle=filled;color=aquamarine4;  \n "+AVL.Graficar()+"\n}";
    }
    public String GraficarHorarios(ArbolB B){
           return "subgraph cluster_sub"+"Hora"+"{ \nnode[shape=\"box\"]; \nstyle=filled;color=darkturquoise;  \n "+B.Graficar()+"\n}";
    }
    public String GraficarUsuarios(Node Head){
        if(Head!=null){
            Node TempNode=Head;
            String grafo="";
            grafo = "subgraph cluster_subUsuarios{"+"\nnode[shape=\"box\"];  \nstyle=filled;color=aquamarine";
            
            while(TempNode.getNext()!=null){
                Usuario Usr = (Usuario)TempNode.getContenido();
                grafo += TempNode.toString()+"[label=\" " + Usr.Graph()+ " \"];\n";
                grafo += TempNode.toString()+"->"+TempNode.getNext().toString()+"\n";
                if(TempNode.getBack()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getBack().toString()+"\n";
                TempNode=TempNode.getNext();
            }
            Usuario Usr = (Usuario)TempNode.getContenido();
            if(TempNode.getBack()!=null)
            grafo += TempNode.toString()+" -> "+TempNode.getBack().toString()+"\n";
            grafo += TempNode.toString().replace(".", "")+"[label=\" " +  Usr.Graph()+ " \"];\n";
            grafo += "}";
            return grafo;
        }
        return "";
    }
    public String GraficarEdificiosYSalones(NodoSTR Head){
        if(Head!=null){
            NodoSTR TempNodoSTR=Head;
            String grafo="";
            grafo = "subgraph cluster_subEdificios{"+" \nnode[shape=\"box\"]; \nstyle=filled;color=darkseagreen2\n";
            Edificio Edi= (Edificio)TempNodoSTR.getContenido();
            grafo += TempNodoSTR.toString()+"[label=\" " + Edi.Graph()+ " \"];\n";
            grafo+=Edi.GenerarSalones()+"\n";
            if(TempNodoSTR.getBack()!=null)
                grafo += TempNodoSTR.toString()+"->"+TempNodoSTR.getBack().toString()+"\n";
            if(TempNodoSTR.getNext()!=null)
                grafo += TempNodoSTR.toString()+"->"+TempNodoSTR.getNext().toString()+"\n";
            TempNodoSTR=TempNodoSTR.getNext();
            while(TempNodoSTR!=Head){
                Edi= (Edificio)TempNodoSTR.getContenido();
                grafo += TempNodoSTR.toString()+"[label=\" " + Edi.Graph()+ " \"];\n";
                grafo+=Edi.GenerarSalones()+"\n";
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
}
