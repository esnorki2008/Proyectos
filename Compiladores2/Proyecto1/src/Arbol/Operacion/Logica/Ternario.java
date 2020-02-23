/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Operacion.Logica;

import Arbol.Instruccion;
import Arbol.Operacion.Operacion;
import Arbol.Valor;
import Elementos.TablaDeSimbolos;
import Excepciones.Excepcion;

/**
 *
 * @author Norki
 */
public class Ternario extends Operacion{

    @Override
    public Object Operar(Object Valor1, int Tip1, Object Valor2, int Tip2, Object Valor3, int Tip3, TablaDeSimbolos Tabla) {
       if(Tip1==0){
           if((boolean)Valor1){
               super.ValorTipo=Tip2;
               return Valor2;
           }else{
               super.ValorTipo=Tip3;
               return Valor3;
           }
           
       }else{
           Tabla.AgregarExcepcion(new Excepcion("No Se Puede Usar Ternario Con Primer Parametro De Tipo " + this.TipoATexto(Tip1) ));
           return null;
       }
    }

    @Override
    public Object Operar(Object Valor1, int Tip1, Object Valor2, int Tip2, TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
