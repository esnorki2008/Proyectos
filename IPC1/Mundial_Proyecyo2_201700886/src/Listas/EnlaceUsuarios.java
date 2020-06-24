/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;
import Visuales.CargarDatos;
/**
 *
 * @author usuario
 */
public class EnlaceUsuarios {
    static int Contador=0;
    static NodoUsuarios Cabeza,Inicio;
    static NodoUsuarios Copia, Imprimir,Ordenado;
    public int Conta=CargarDatos.Contador;
    public void Agregar(int ID, String Nombre, String Apellido, String Contra) {
        NodoUsuarios Usuarios = new NodoUsuarios(ID, Nombre, Apellido, Contra);

        if (Cabeza == null) {
            Inicio=Usuarios;
            Cabeza = Usuarios;
            Contador++;
        } else {
            while (true) {
                if (Cabeza != null) {
                    Copia = Cabeza;
                    Cabeza = Cabeza.Siguiente;
                } else {

                    //CreadoAntes
                    Copia.Siguiente = Usuarios;

                    //Nuevo
                    //System.out.println(Copia);
                    // System.out.println(Cabeza.Anterior);

                    Cabeza = Usuarios;
                    Cabeza.Anterior = Copia;
                    Contador++;

                    break;
                }

            }
        }
    }

    public void Imprimir() {
        Imprimir = Cabeza;
        while (Imprimir.Anterior != null) {
            Imprimir = Imprimir.Anterior;
        }
        System.out.println(Imprimir);
        while (Imprimir != null) {
            System.out.println("/////////////////////////////////////////////////////////////");
            System.out.println("Datos   " + Imprimir.ID + "  " + Imprimir.Nombre + "   " + Imprimir.Apellido + "   " + Imprimir.Contra);
            System.out.println("Nodo Actual   " + Imprimir);
            System.out.println("Nodo Siguiente   " + Imprimir.Siguiente);
            System.out.println("Nodo Anterior   " + Imprimir.Anterior);

            Imprimir = Imprimir.Siguiente;
        }

    }
    
     public void Ordenar(){
        NodoUsuarios Actual=Cabeza;
        NodoUsuarios Siguiente=Actual.Anterior;
        NodoUsuarios Respaldo=Cabeza;
        
    for(int i=0;i<Contador;i++){
        while(Siguiente.Anterior!=null){
                
                //pausa
                int a= (int)Actual.Nombre.charAt(0);
                int b= (int)Siguiente.Nombre.charAt(0);
                
                
                if(a>b){
                    System.out.println(Actual.Nombre+"      "+Actual);    
                    System.out.println(Siguiente.Nombre+"      "+Siguiente);    
                    
                Respaldo=Actual;Respaldo.Siguiente=Actual.Siguiente;Respaldo.Anterior=Actual.Anterior;
                Respaldo.Nombre=Actual.Nombre;Respaldo.ID=Actual.ID;Respaldo.Apellido=Actual.Apellido;Respaldo.Contra=Actual.Contra;
                
                Actual=Siguiente;Actual.Siguiente=Siguiente.Siguiente;Actual.Anterior=Siguiente.Anterior;
                Actual.ID=Siguiente.ID;Actual.Nombre=Siguiente.Nombre;Actual.Apellido=Siguiente.Apellido;Actual.Contra=Siguiente.Contra;
                
                Siguiente=Respaldo;Siguiente.Siguiente=Respaldo.Siguiente;Siguiente.Anterior=Respaldo.Anterior;
                Siguiente.ID=Respaldo.ID;Siguiente.Nombre=Respaldo.Nombre;Siguiente.Apellido=Respaldo.Apellido;Siguiente.Contra=Respaldo.Contra;
                

                }else{
                Actual=Actual.Anterior;
                Siguiente=Siguiente.Anterior;}

            
        
        
        
        
        }
    
    }
    
    
    while (Actual != null) {
            System.out.println("/////////////////////////////////////////////////////////////");
            System.out.println("Datos   " + Actual.ID + "  " + Actual.Nombre + "   " + Actual.Apellido + "   " + Actual.Contra);
            System.out.println("Nodo Actual   " + Actual);
            System.out.println("Nodo Siguiente   " + Actual.Siguiente);
            System.out.println("Nodo Anterior   " + Actual.Anterior);

            Actual = Actual.Siguiente;
        }
    
    
      /*while (Siguiente != null) {
            System.out.println("/////////////////////////////////////////////////////////////");
            System.out.println("Datos   " + Siguiente.ID + "  " + Siguiente.Nombre + "   " + Siguiente.Apellido + "   " + Siguiente.Contra);
            System.out.println("Nodo Actual   " + Siguiente);
            System.out.println("Nodo Siguiente   " + Siguiente.Siguiente);
            System.out.println("Nodo Anterior   " + Siguiente.Anterior);

            Siguiente = Siguiente.Siguiente;
        }*/
    
    
    }
     
     public void Ordenar1(){
        NodoUsuarios Actual=Cabeza; 
        Object[][] Orde = new Object[5][Conta];
        int i=0;
        while(Actual!=null){ Orde[1][i]=Actual.Nombre; Orde[2][i]=Actual.Apellido;Orde[3][i]=Actual.Contra; Orde[4][i]=Actual.ID;
            Orde[0][i]=Actual;    Actual=Actual.Anterior;i++;
        } i=0;
        
        for(int k=0;k<Conta;k++){
        for(i=0;i<Conta-1;i++){
        int a= (char)Orde[1][i].toString().charAt(0);
        int b= (char)Orde[1][i+1].toString().charAt(0);
        
        if(Orde[1][i].equals(Orde[1][i])){break;}

        
        if(a<b){
        Object[] Copia= new Object[5];
        Copia[0]=Orde[0][i]; 
        Copia[1]=Orde[1][i]; 
        Copia[2]=Orde[2][i];
        Copia[3]=Orde[3][i];
        Copia[4]=Orde[4][i];
        
        Orde[0][i]=Orde[0][i+1];
        Orde[1][i]=Orde[1][i+1];
        Orde[2][i]=Orde[2][i+1];
        Orde[3][i]=Orde[3][i+1];
        Orde[4][i]=Orde[4][i+1];
        
        Orde[0][i+1]=Copia[0];
        Orde[1][i+1]=Copia[1];
        Orde[2][i+1]=Copia[2];
        Orde[3][i+1]=Copia[3];
        Orde[4][i+1]=Copia[4];
        
        }
        
         }
        }
        
        
        
        for(int k=0;k<Conta;k++){
        for(i=0;i<Conta-1;i++){
        int a= (char)Orde[1][i].toString().charAt(1);
        int b= (char)Orde[1][i+1].toString().charAt(1);
        
        if(Orde[1][i].equals(Orde[1][i+1])){break;}
        if(a<b){
        Object[] Copia= new Object[5];
        Copia[0]=Orde[0][i]; 
        Copia[1]=Orde[1][i]; 
        Copia[2]=Orde[2][i];
        Copia[3]=Orde[3][i];
        Copia[4]=Orde[4][i];
        
        Orde[0][i]=Orde[0][i+1];
        Orde[1][i]=Orde[1][i+1];
        Orde[2][i]=Orde[2][i+1];
        Orde[3][i]=Orde[3][i+1];
        Orde[4][i]=Orde[4][i+1];
        
        Orde[0][i+1]=Copia[0];
        Orde[1][i+1]=Copia[1];
        Orde[2][i+1]=Copia[2];
        Orde[3][i+1]=Copia[3];
        Orde[4][i+1]=Copia[4];
        
        }
        
         }
        }
        
         for(int k=0;k<Conta;k++){
        for(i=0;i<Conta-1;i++){
        int a= (char)Orde[1][i].toString().charAt(2);
        int b= (char)Orde[1][i+1].toString().charAt(2);
        
        if(Orde[1][i].equals(Orde[1][i])){break;}
        if(Orde[1][i].toString().charAt(1)!=Orde[1][i+1].toString().charAt(1)){break;}

        
        if(a<b){
        Object[] Copia= new Object[5];
        Copia[0]=Orde[0][i]; 
        Copia[1]=Orde[1][i]; 
        Copia[2]=Orde[2][i];
        Copia[3]=Orde[3][i];
        Copia[4]=Orde[4][i];
        
        Orde[0][i]=Orde[0][i+1];
        Orde[1][i]=Orde[1][i+1];
        Orde[2][i]=Orde[2][i+1];
        Orde[3][i]=Orde[3][i+1];
        Orde[4][i]=Orde[4][i+1];
        
        Orde[0][i+1]=Copia[0];
        Orde[1][i+1]=Copia[1];
        Orde[2][i+1]=Copia[2];
        Orde[3][i+1]=Copia[3];
        Orde[4][i+1]=Copia[4];
        
        }
        
         }
        }
        
        
        for(int k=0;k<Conta;k++){
        if(Orde[1][i]!=(Orde[1][i+1])){break;}
        for(i=0;i<Conta-1;i++){
        int a= (char)Orde[2][i].toString().charAt(0);
        int b= (char)Orde[2][i+1].toString().charAt(0);
        
        
        if(!Orde[1][i].equals(Orde[1][i+1])){break;}

        if(a<b){
        Object[] Copia= new Object[5];
        Copia[0]=Orde[0][i]; 
        Copia[1]=Orde[1][i]; 
        Copia[2]=Orde[2][i];
        Copia[3]=Orde[3][i];
        Copia[4]=Orde[4][i];
        
        Orde[0][i]=Orde[0][i+1];
        Orde[1][i]=Orde[1][i+1];
        Orde[2][i]=Orde[2][i+1];
        Orde[3][i]=Orde[3][i+1];
        Orde[4][i]=Orde[4][i+1];
        
        Orde[0][i+1]=Copia[0];
        Orde[1][i+1]=Copia[1];
        Orde[2][i+1]=Copia[2];
        Orde[3][i+1]=Copia[3];
        Orde[4][i+1]=Copia[4];
        
        }
        
         }
        }
   
    //Paso
    
    //Despues
    int tempo=Contador;
    Contador=0;
    for(i=0;i<tempo;i++)
        {System.out.println("HOla");
            AgregarOrden((int)Orde[4][i],Orde[1][i].toString(),Orde[2][i].toString(),Orde[3][i].toString());
        
        
        
      }
    System.out.println("*************-----------------********************");
    ImprimirOrden();
    }
   public static NodoOrden Ordenada; 
   public static  NodoOrden Copiasa; 
   public static NodoOrden Comienzo; 
   public static int Repetidos=0;
   public static  NodoOrden Impre; 
   public void AgregarOrden(int ID, String Nombre, String Apellido, String Contra) {
        NodoOrden Usuarios = new NodoOrden(ID, Nombre, Apellido, Contra);
        if (Ordenada == null) {
            Ordenada = Usuarios;
            Comienzo=Usuarios;
            Contador++;
        } else {
            while (true) {
                if (Ordenada != null) {
                    Copiasa = Ordenada;
                    Ordenada = Ordenada.Siguiente;
                } else {

                    //CreadoAntes
                    Copiasa.Siguiente = Usuarios;

                    //Nuevo
                    //System.out.println(Copia);
                    // System.out.println(Cabeza.Anterior);
                    
                    Ordenada = Usuarios;
                    Ordenada.Anterior = Copiasa;
                    Contador++;

                    break;
                }

            }
        }
    }
   public void ImprimirOrden() {
        Impre = Comienzo;
        
       
       
        System.out.println(Impre);
        while (Impre != null) {
            System.out.println("No Sirve");
            System.out.println("/////////////////////////////////////////////////////////////");
            System.out.println("Datos   " + Impre.ID + "  " + Impre.Nombre + "   " + Impre.Apellido + "   " + Impre.Contra);
            System.out.println("Nodo Actual   " + Impre);
            System.out.println("Nodo Siguiente   " + Impre.Siguiente);
            System.out.println("Nodo Anterior   " + Impre.Anterior);

            Impre = Impre.Siguiente;
        }
       
    }
     public  static NodoOrden Sacar; 
     public void PreSacar(){        Sacar=Comienzo;}
      public int SacarValores(int l) {
          
          
          
          while (Sacar.Siguiente != null) {
           
            if(l!=Sacar.ID){break;}else{Sacar = Sacar.Siguiente;}

        }
      return Sacar.ID;
      
      
      }
      
     public boolean Iguales(String b) {
        Imprimir = Inicio;
        while (Imprimir != null) {

            if (Integer.parseInt(b) == Imprimir.ID) {
                Repetidos++;
                return false;
            }

            Imprimir = Imprimir.Siguiente;
        }
        return true;
    }

   public NodoOrden SacarSiguiente(NodoOrden hola){
    if(hola==null){return Comienzo;}else{
        
        hola =hola.Siguiente;
        
    }
    
    
    return hola;
   
   }
     
   
   
   
   public boolean UsuarioContra(int ID,String Contra) {
        Impre = Comienzo;
        
       
       
        System.out.println(Impre);
        while (Impre != null) {
            if((Impre.ID==ID)&&(Impre.Contra.equals(Contra))){
            return true;
            }

            Impre = Impre.Siguiente;
        }
       return false;
    }
   
    public int UsE(int ID) {
        Impre = Comienzo;

        System.out.println(Impre);
        while (Impre != null) {
            if (Impre.ID == ID) {
                Impre.Sobres = Impre.Sobres + 1;    
                break;
            }

            Impre = Impre.Siguiente;

        }

        
        return Impre.Sobres;
    }
    
    public String Usua(int ID) {
        Impre = Comienzo;

        System.out.println(Impre);
        while (Impre != null) {
            if (Impre.ID == ID) {
                  return Impre.Nombre+"  "+Impre.Apellido; 
                //break;
            }

            Impre = Impre.Siguiente;

        }

        
        return "NO";
    }
}
