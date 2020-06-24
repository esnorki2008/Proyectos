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
public class EnlaceJugadores {

    static NodoJugadores Actual;
   public static NodoJugadores Cabeza, Imprimir,Inicio,Sacar;
   public static int Repetidos=0;

    public void Agregar(int a, String b, String c, int d, float e, float f, int g) {
        NodoJugadores Jugador = new NodoJugadores(a, b, c, d, e, f, g);
        if (Cabeza == null) {
            Inicio=Jugador;
            Cabeza = Jugador;
            Cabeza.Anterior = null;
            Cabeza.Siguiente = null;
        } else {
            while (true) {
                if (Cabeza != null) {
                    Actual = Cabeza;
                    Cabeza = Cabeza.Siguiente;
                } else {
                    Actual.Siguiente = Jugador;
                    Cabeza = Actual.Siguiente;
                    Cabeza.Anterior = Actual;

                    break;
                }

            }

        }
        Imprimir = Cabeza;
        // Imprimir();
    }

    public void Imprimir() {
        while (Imprimir.Anterior != null) {
            Imprimir = Imprimir.Anterior;
        }

        while (Imprimir != null) {
            System.out.println(" ---------------------------------------------------");
            System.out.println("Nombre del Jugador  " + Imprimir.Nombre + "ID Equipo " + Imprimir.IDEquipo);
            System.out.println("Nodo Actual    " + Imprimir + "  Altura del Jugador:  " + Imprimir.Altura);
            System.out.println("Nodo Anterior " + Imprimir.Anterior);
            System.out.println("Nodo Siguiente " + Imprimir.Siguiente);
            Imprimir = Imprimir.Siguiente;
        }

    }

    public boolean Iguales(int ID) {
        NodoJugadores Igualdad = Cabeza;
        while (Igualdad != null) {
            if (Igualdad.ID == ID) {
                Repetidos++;
                return false;
            }
            Igualdad = Igualdad.Anterior;
        }
        return true;
    
    }
    
     public String DevolverJugador(int ID){
        Sacar=Inicio;
        while(Sacar!=null){
        
        if(Sacar.ID==ID){return Sacar.Nombre;}
        
        Sacar= Sacar.Siguiente;
        }
        return "NO";
    
    }

}
