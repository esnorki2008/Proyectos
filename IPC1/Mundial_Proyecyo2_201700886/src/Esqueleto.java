
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author usuario
 */
public class Esqueleto {
    
}
 class Lector{
    public static AlmaE lista ;
     
     
    public  void leer(String Caminito) throws FileNotFoundException, IOException{
        File archivo =null;
        FileReader fr =null;
        BufferedReader br = null;
        lista  = new AlmaE();

        archivo = new File(Caminito);
        fr = new FileReader(archivo);
        br = new BufferedReader(fr);
        
        System.out.println("Leído Correctamente");
        String Linea;//Partiendo La COmida
        
        while((Linea=br.readLine())!=null){
              String[] Banana = Linea.split("\\|");
              //for(String hola: Banana){System.out.println(hola);} 
              
             

              lista.Agregar(Integer.parseInt(Banana[0]), Banana[1], Banana[2], Integer.parseInt(Banana[3]));
              
              
              
        }if(null!=fr){
            fr.close();
        }
                
                              lista.Imprimir();

                
                
        
    }

    
}