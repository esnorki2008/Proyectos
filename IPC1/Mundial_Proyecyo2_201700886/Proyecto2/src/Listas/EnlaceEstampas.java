/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Listas;

/**
 *
 * @author usuario
 */
public class EnlaceEstampas {
    public static int EstampasTotales=0;
    public static NodoEstampas Cabeza, Inicio,Imprimir, Reciente,Igual;
    public static int Contador=0,Repetidos=0;
    public void Agregar(int a, int b, int c, String d, String e) {
        NodoEstampas Estampas = new NodoEstampas(a, b, c, d, e);

        if (Cabeza == null) {
            EstampasTotales++;
            Contador++;
            Cabeza = Estampas;
            Inicio = Cabeza;
        } else {
            while (true) {
                if (Cabeza != null) {Reciente = Cabeza;
                    Cabeza = Cabeza.Siguiente;
                } else {
                                EstampasTotales++;

                    Contador++;
                    Cabeza = Estampas;
                    Reciente.Siguiente=Cabeza;
                    break;
                }

            }

        }Imprimir=Inicio; Igual=Inicio; 

    }

    public void Imprimir() {
        Imprimir=Inicio; 
        while (Imprimir != null) {
            System.out.println("////////////////////////////////////////////////////////////////");
            System.out.println("Nodo Siguiente " + Imprimir.Siguiente);
            System.out.println("Nodo Actual  " + Imprimir);
            System.out.println("Datos  " + Imprimir.ID+" "+Imprimir.IDJugador+" "+Imprimir.IDEquipo+" "+Imprimir.Rareza+" "+Imprimir.RutaImagen );
            Imprimir=Imprimir.Siguiente;
        }

    }
    
    public boolean EstampasIguales(String b){
    Igual=Inicio;     
    while(Igual!=null){
    
    if(Integer.parseInt(b)==Igual.ID){Repetidos++;return false;}
        
    Igual=Igual.Siguiente;
    }
    return true;
    }
    
    public NodoEstampas SacarEstampa(int ID){
    Imprimir=Inicio; 
        while (Imprimir != null) {
            if(Imprimir.ID==ID){return Imprimir;}
            Imprimir=Imprimir.Siguiente;
        }
    System.out.println("No deberia PAsar");
    return null;
    }

    
    public NodoEstampas SacarSiguiente(NodoEstampas hola){
    if(hola==null){return Inicio;}else{
        
        hola =hola.Siguiente;
        
    }
    
    
    return hola;
   
   }
    
    public int DevolverEquipoEstampa(int ID){
    Imprimir=Inicio; 
        while (Imprimir != null) {
            if(Imprimir.ID==ID){return Imprimir.IDEquipo;}
            Imprimir=Imprimir.Siguiente;
        }
    System.out.println("No deberia PAsar");
    return 0;
    }
    
}
