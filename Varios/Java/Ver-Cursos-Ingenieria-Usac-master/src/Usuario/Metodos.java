/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

/**
 *
 * @author ENK
 */
import ElementosCursos.*;

public class Metodos {
   private void RevisarL(String Linea){
       
   
   } 
    
   public ListaDeCursos CargarTexto(String Texto){
 ListaDeCursos Aprobados = new ListaDeCursos();

       String[] Saltos = Texto.split(((char) 10)+"" );
    for(String Salto: Saltos){
        String[] Lineas = Salto.split("	");
        if(Lineas.length !=6){
            System.out.println(Lineas.length);
            System.out.println("La línea que comienza con '"+Lineas[0] + "' No está en el formato correcto ");}
        else{
            Cursos Curso = new Cursos(Lineas[1],Lineas[2],Integer.parseInt(Lineas[3]));
             Aprobados.Añadir(Curso);
      //       System.out.println(Curso.Codigo());
    }
   } 
   return Aprobados;
   }
  
}
