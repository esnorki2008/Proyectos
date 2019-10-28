/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Programa;

/**
 *
 * @author Andhy
 */
public class Nodo {

    public String Post = "";
    public String Lexema = "";
    public int Tipo = 0;

    public Nodo(int Tipo, String Lexema, String Post) {
        this.Lexema = Lexema;
        this.Tipo = Tipo;
        this.Post = Post;
    }

    public String LexemaLimpio(){
        
        
     String SalidaVerdadera="";  
     String[] LexemaSpl= Lexema.split("\\;");
     for(int ini=0;ini<LexemaSpl.length;ini++){
     
     String[] Hola= LexemaSpl[ini].split("\\,");
        double Valores=0;
        String Sal="";
        for(int i=0;i<Hola.length;i++){
          
        boolean numeric = true;
        double num=0;
        try {
          num  = Double.parseDouble(Hola[i]);
        } catch (NumberFormatException e) {
            String Conc=""+Valores;
            
            
            if((Conc).split("\\.")[1].equals("0")){
                Conc=Conc.split("\\.")[0];
            }
            Sal=Sal+","+Conc;
            Valores=0;
            numeric=false;
        }

        if(numeric){
            Valores=Valores+num;
            }else{
            Sal=Sal+","+Hola[i];
            
        }
         
    }
      if(Hola.length==1){Sal=Sal+Valores;}else{Sal=Sal.substring(1);}
      SalidaVerdadera=SalidaVerdadera+"\n"+Sal;
     }
          return SalidaVerdadera;
    }

}
