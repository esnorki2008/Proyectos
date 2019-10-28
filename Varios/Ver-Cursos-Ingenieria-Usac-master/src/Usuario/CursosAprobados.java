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
public class CursosAprobados {
    private ListaDeCursos Aprobados = new ListaDeCursos();
    
    public void l(){
        
      
       Carreras Ca = new Carreras();
       ListaDeCursos Sistemas =Ca.CursosC09();
       
       ListaDeCursos Apro= new ListaDeCursos();

     Apro.Añadir(new Cursos("5","TECNICAS DE ESTUDIO E INVESTIGACION",3));
Apro.Añadir(new Cursos("69","AREA TECNICA COMPLEMENTARIA 1",3));
Apro.Añadir(new Cursos("101","AREA MATEMATICA BASICA 1",7));
Apro.Añadir(new Cursos("348","QUIMICA GENERAL 1",3));
Apro.Añadir(new Cursos("103","AREA MATEMATICA BASICA 2",7));
Apro.Añadir(new Cursos("17","AREA SOCIAL HUMANISTICA 1",4));
Apro.Añadir(new Cursos("107","AREA MATEMATICA INTERMEDIA 1",10));
Apro.Añadir(new Cursos("147","FISICA BASICA",5));
Apro.Añadir(new Cursos("112","AREA MATEMATICA INTERMEDIA 2",5));
Apro.Añadir(new Cursos("114","AREA MATEMATICA INTERMEDIA 3",5));
Apro.Añadir(new Cursos("116","MATEMATICA APLICADA 3",5));
Apro.Añadir(new Cursos("118","MATEMATICA APLICADA 1",6));
Apro.Añadir(new Cursos("150","FISICA 1",6));
Apro.Añadir(new Cursos("770","INTRODUCCION A LA PROGRAMACION Y COMPUTACION 1",4));
Apro.Añadir(new Cursos("795","LOGICA DE SISTEMAS",2));
Apro.Añadir(new Cursos("960","MATEMATICA PARA COMPUTACION 1",5));
Apro.Añadir(new Cursos("732","ESTADISTICA 1",5));
Apro.Añadir(new Cursos("771","INTRODUCCION A LA PROGRAMACION Y COMPUTACION 2",5));
Apro.Añadir(new Cursos("796","LENGUAJES FORMALES Y DE PROGRAMACION",3));
Apro.Añadir(new Cursos("962","MATEMATICA PARA COMPUTACION 2",5));
       //Sistemas.Sacar("101");
       //Sistemas.Sacar("017");
       // Sistemas.Sacar("069");
       
       
       Object[] Comparacion=Sistemas.Comparar(Apro,Sistemas);
       ListaDeCursos Posibles =(ListaDeCursos) Comparacion[0];
       ListaDeCursos Ganados =(ListaDeCursos) Comparacion[1];
       
      Posibles.Imprimir();
      // Ganados.Imprimir();
       
//Sistemas.Imprimir();
            
    }
    
    
}
