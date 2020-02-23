/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Operacion.Numerica;

import Arbol.Operacion.Operacion;
import Elementos.TablaDeSimbolos;
import Excepciones.Excepcion;

/**
 *
 * @author 50241
 */
public class Negativo extends Operacion{

    @Override
    public Object Operar(Object Valor1, int Tip1, Object Valor2, int Tip2, TablaDeSimbolos Tabla) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public Object Operar(Object Valor1, int Tip1,TablaDeSimbolos Tabla) {
        super.ValorTipo = Tip1;
        switch(Tip1){
            case 1:
                return ((double)Valor1)*-1;
            case 2:
                return ((int)Valor1)*-1;
        }
        Tabla.AgregarExcepcion(new Excepcion("No Se Puede Colocar Negativo Como Prefijo En  El Tipo " + this.TipoATexto(Tip1)));
        super.ValorTipo=1;
        return 0.0;
    }
}
