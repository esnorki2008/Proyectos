/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

import Visuales.*;
import static Visuales.MenuUsuarios.MaxSobres;
public class EnlaceEstampasUsuarios {
static int con=0;

   public static NodoEstampasUsuario Cuerpo, Cabeza, Respaldo,ui;

    public void Agregar(int a, int b, String c, int d) {
        NodoEstampasUsuario Nuevo = new NodoEstampasUsuario(a, b, c, d);
        if (Cuerpo == null) {

            Cabeza = Nuevo;
            Cuerpo=Nuevo;
            System.out.println("Nodo Esta es la cabeza "+Cuerpo+"     "+con);
                        con++;


        } else {
            while (true) {
                if (((Cuerpo != null))) {
                    Respaldo = Cuerpo;
                     
                    Cuerpo = Cuerpo.Siguiente;
                    if(Cuerpo==Cabeza){Cuerpo=null;}
                    
                    
                } else {
                    //Nuevo
                    Cuerpo=Nuevo;
                    //Anterior
                    Cuerpo.Anterior=Respaldo;
                    
                    //Siguiente Del Anterior
                    Respaldo.Siguiente=Nuevo;
                    
                    System.out.println("Nodo "+Cuerpo+"     "+con);
                    //Circulae
                    Cabeza.Anterior=Cuerpo;
                    Cuerpo.Siguiente=Cabeza;
                    con++;


                    //Romper Ciclo
                    break;
                }

            }

        }

    }
    
    public void Imprimir(){
    NodoEstampasUsuario Impre= Cuerpo.Siguiente;
   
    int Romper=0; 
    int Alt=0; 
    
    while((Impre!=null)&&Romper<1){          

        if(Impre.Siguiente==Cabeza){Romper++;}
    
        System.out.println("////////////////////////////////////////////////////");
        System.out.println("Nodo Actual  "+Impre+"    "+Alt);    
        System.out.println("Nodo Anterior  "+Impre.Anterior);    
        System.out.println("Nodo Siguiente  "+Impre.Siguiente);    
        System.out.println("Valores De Estampa: "+Impre.IDUsuario+" "+Impre.IDEstampas+" "+Impre.Pegada+" "+Impre.Cantidad+" ---  "+Impre.NumSobre+" ---  "+Impre.Index);
        Impre=Impre.Siguiente;Alt++;
    }        


    }
    public int CrearSobres(int IDU,int NuSobre,int CantMax){
    NodoEstampasUsuario Impre= Cuerpo.Siguiente;
   
    int Romper=0; 
    int Alt=0; 
    int Cantidad=0;
    while((Impre!=null)&&Romper<1){          

        if(Impre.Siguiente==Cabeza){Romper++; }
    
        if((Impre.IDUsuario==IDU)&&(Impre.NumSobre==0)&&(Impre.Pegada.equals("0"))){Impre.NumSobre=NuSobre;Cantidad++;Impre.Index=Cantidad;}
        if(Cantidad==CantMax){return -3;}
        
        Impre=Impre.Siguiente;
    }        

   

    return Romper;
    
    }
    
    public int MaxSobre(int ID) {
        int Max=0;
        NodoEstampasUsuario Impre = Cuerpo.Siguiente;

        int Romper = 0;
        int Alt = 0;

        while ((Impre != null) && Romper < 1) {

            if (Impre.Siguiente == Cabeza) {
                Romper++;
            }
            if(Impre.IDUsuario==ID){
            if(Max<Impre.NumSobre){Max=Impre.NumSobre;}
            
            }
            
            Impre = Impre.Siguiente;
        }
        return Max;
    }
     public int MaxEstampa(int ID,int IDE) {
        int Max=0;
        NodoEstampasUsuario Impre = Cuerpo.Siguiente;

        int Romper = 0;
        int Alt = 0;

        while ((Impre != null) && Romper < 1) {

            if (Impre.Siguiente == Cabeza) {
                Romper++;
            }
            if(Impre.IDUsuario==ID&&(Impre.NumSobre==IDE)){
            if(Max<Impre.Index){Max=Impre.Index;}
            
            }
            
            Impre = Impre.Siguiente;
        }
        return Max;
    }
    
    public void CrearSobres1(){
    NodoEstampasUsuario Impre= Cuerpo.Siguiente;
   
    int Romper=0; 
    int Alt=0; 
    
    while((Impre!=null)&&Romper<1){          

        if(Impre.Siguiente==Cabeza){Romper++;}
    
        System.out.println("////////////////////////////////////////////////////");
        System.out.println("Nodo Actual  "+Impre+"    "+Alt);    
        System.out.println("Nodo Anterior  "+Impre.Anterior);    
        System.out.println("Nodo Siguiente  "+Impre.Siguiente);    
        System.out.println("Valores De Estampa: "+Impre.IDUsuario+" "+Impre.IDEstampas+" "+Impre.Pegada+" "+Impre.Cantidad);
        Impre=Impre.Siguiente;Alt++;
    }        

    
    }
    
    public NodoEstampasUsuario Existencia(int IDesta){
        NodoEstampasUsuario Hola=Cabeza;
        int Romper=0;
        
        
        while((Hola!=null)&&Romper<1){
            if(Hola.Siguiente==Cabeza){Romper++;}
            if(Hola.IDEstampas==IDesta&&(Hola.IDUsuario==MenuUsuarios.IDUsuario)){return Hola;}
            Hola=Hola.Siguiente;
        }
        
        
    return null;
    }
    
    
     public NodoEstampasUsuario SacarEstampa(int IDSobre,int Index){
        NodoEstampasUsuario Hola=Cabeza;
        int Romper=0;
        
        
        while((Hola!=null)&&Romper<1){
            if(Hola.Siguiente==Cabeza){Romper++;}
            if(Hola.NumSobre==IDSobre&&(Hola.Index==Index)&&(Hola.IDUsuario==MenuUsuarios.IDUsuario)){return Hola;}
            Hola=Hola.Siguiente;
        }
        
        
    return null;
    }
    
     
    public NodoEstampasUsuario SacarEstampa2(int IDSobre,int Index){
        NodoEstampasUsuario Hola=Cabeza;
        int Romper=0;
        
        
        while((Hola!=null)&&Romper<1){
            if(Hola.Siguiente==Cabeza){Romper++;}
            if(Hola.NumSobre==IDSobre&&(Hola.Index==Index)&&(Hola.IDUsuario==MenuUsuarios.IDUsuario)){return Hola;}
            Hola=Hola.Siguiente;
        }
        
        
    return null;
    } 
    
    public void Limpiar(NodoEstampasUsuario Aqu){
      //  NodoEstampasUsuario Ant,Sig;
     //   Ant=Aqu.Anterior;
       // Sig=Aqu.Siguiente;
        Aqu.Pegada=""+1;
      //  Ant.Siguiente=Sig;
      //  Sig.Anterior=Ant;
    
    
    
    
    }
    
    
    public void LimpiarSobre(){
    NodoEstampasUsuario Impre= Cuerpo.Siguiente;
   
    int Romper=0; 
    int Alt=0; 
    
    while((Impre!=null)&&Romper<1){          

        if(Impre.Siguiente==Cabeza){Romper++;}

        Impre.NumSobre=0;
        Impre.Index=0;
        Impre=Impre.Siguiente;Alt++;
    }        
    
    
    
    
    
    }
    
}
