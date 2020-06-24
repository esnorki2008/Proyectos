public class ListaE {
    public ListaE Siguiente;
    public int ID,Cantidad;
    public String Nombre,Fecha;

    public ListaE(int ID,String Nombre,String Fecha,int Bochinche) {
        this.Nombre=Nombre;
        this.ID=ID;
        this.Fecha=Fecha;
        this.Cantidad=Bochinche;
    }



    public void Mostrar(){
        System.out.println(ID+"---"+ Nombre+"---"+Fecha+"---"+ Cantidad);
    }


}

class AlmaE{

    
    
    
   public ListaE Primer; 
   
   
   AlmaE(){
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
   
   public void Agregar(int ID,String Nombre,String Fecha,int Bochinche){
    ListaE nuevo = new ListaE(ID,Nombre,Fecha,Bochinche);
    
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
   
   
}