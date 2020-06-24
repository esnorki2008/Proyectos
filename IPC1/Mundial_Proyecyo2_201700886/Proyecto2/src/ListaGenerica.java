/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class ListaGenerica {
    /*
    public ListaE Siguiente;
    public int ID;
    public String Nombre;

    public ListaGenerica(int ID,String Nombre) {
        this.Nombre=Nombre;
        this.ID=ID;
    }



    public void Mostrar(){
        System.out.println(ID+"  Nombre Equipo:  "+ Nombre);
    
    }


}

class AlmaGenerica{

    
    
    
   public ListaE Primer; 
   
   
   AlmaGenerica(){
       Primer =null;
   }
   
    public void Imprimir() {
        ListaE Enla = Primer;
        while (Enla != null) {
            Enla.Mostrar();
            System.out.println(" Actual Nodo " + Enla);
            System.out.println(" Siguente Nodo " + Enla.Siguiente);
            Enla = Enla.Siguiente;
            System.out.println();

        }
    }
   
   
   public boolean Vacio(){
       return(Primer==null);
   }
   
   public void Agregar(int ID,String Nombre){
    ListaE nuevo = new ListaE(ID,Nombre);
    
    nuevo.Siguiente=Primer;
    Primer = nuevo;
   }

   public ListaE Encontrar(int Dato){ 
   
       ListaE Encont=Primer;
       if(!Vacio()){
           while(Encont.ID != Dato){
               if(Encont.Siguiente==null){
                   System.out.println("Final, nada más");
               }else{
                   Encont = Encont.Siguiente;
               }
           }
       
       }else{System.out.println("No hay nada");
       }
       return Encont;
   }
   
   public ListaE Eliminar(int Dato){
     ListaE Actual = Primer;
     ListaE Anterior = Primer;
     
      while(Actual.ID != Dato){
          if(Actual.Siguiente==null){
              System.out.println("No Puede Borrar");
              return null;
          }else{
              Anterior=Actual;
              Actual = Actual.Siguiente;
          
          
          }
      
      }
      if(Actual == Primer){
          Primer=Primer.Siguiente;
      }else{
          Anterior.Siguiente=Actual.Siguiente;
      }
      return Actual;
   }
   */
   
}
