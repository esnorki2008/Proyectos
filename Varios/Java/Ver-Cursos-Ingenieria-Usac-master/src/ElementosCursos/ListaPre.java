/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementosCursos;

/**
 *
 * @author ENK
 */

public class ListaPre {
    private Cursos Primero=null;
    
    public void Añadir(Cursos Curso){
    if(Primero==null){
        Primero=Curso;
        Primero.SigPre(null);
        Primero.AntPre(null);
    }
    else{
        Cursos Actual=Primero;
        while(Actual.SigPre()!=null){
        
        Actual=Actual.SigPre();
        if(Actual.SigPre()==Primero){break;}
        }
        
        //System.out.println(Curso.Nombre());
        
        Curso.AntPre(Actual);
        Curso.SigPre(null);
        
        Actual.SigPre(Curso);
        Primero.AntPre(Curso);
    }
    }
    
    public Boolean Comparar(Cursos Comp){
        if (Primero!=null){
      
        
    Cursos Actual=Primero;
    while(Actual.SigPre()!=Primero && Actual.SigPre()!=null){
        if(Actual.Codigo().equals(Comp.Codigo())){return true;}
        Actual=Actual.SigPre();
        
    }
        
    
    }
        return false;
    }
    
    public Object[] ComprobarCursos(ListaDeCursos CursosAprobados){
    Object[] Listas = new Object[3];
    Cursos Actual =this.Primero;
    
    return Listas;
    }
    
   public Cursos Siguiente(Cursos Prerre){
     if(Prerre==null){
         
         return Primero;
     
     }else{
         return Prerre.SigPre();
     }
   
   }
   
    public Cursos get(Cursos Curso){
        if (Primero!=null){
            
            if(Curso==null){return Primero;}else{
                return Curso.SigPre();
            }
            
        
    
    
    }else{ 
        return new Cursos("0000","NoTienePre",0);
        }
    }
    
    public void Imprimir(){
        if (Primero!=null){
    
            
        
    Cursos Actual=Primero;
    while(Actual.SigPre()!=Primero && Actual.SigPre()!=null){
        System.out.println(Actual.Nombre());
        Actual=Actual.SigPre();
        }
        System.out.println(Actual.Nombre());
    
    }else{ System.out.println("Sin Pre");}
    }
}
