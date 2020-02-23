/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Operacion.Logica;

import Arbol.Operacion.Operacion;
import Elementos.TablaDeSimbolos;
import Excepciones.Excepcion;

/**
 *
 * @author Norki
 */
public class Not extends Operacion{

    @Override
    public Object Operar(Object Valor1, int Tip1, Object Valor2, int Tip2, TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Object Operar(Object Valor1, int Tip1,TablaDeSimbolos Tabla) {
        super.ValorTipo = Tip1;
        switch(Tip1){
            case 0:
                return !((boolean)Valor1);
        }
        Tabla.AgregarExcepcion(new Excepcion("No Se Puede Negar Logicamente Una Expresion Del Tipo " + this.TipoATexto(Tip1)));
        super.ValorTipo=0;
        return false;
    }
    
}
