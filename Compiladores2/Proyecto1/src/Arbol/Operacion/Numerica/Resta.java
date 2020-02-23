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
 * @author Norki
 */
public class Resta extends Operacion{

    private Object Tipo0(Object Exec1, Object Exec2, int Tipo, TablaDeSimbolos Tabla) {
        if (Exec1 == null) {
            Exec1 = false;
        }
        switch (Tipo) {
            case 3:
                if (Exec2 == null) {
                    Exec1 = "";
                }
                this.ValorTipo=3;
                return (boolean)Exec1+(String)Exec2 + "";
            default:
                Tabla.AgregarExcepcion(new Excepcion("Tipo Booleano Solo Puede Usarse Con String"));
                return null;
        }
    }
    
    @Override
    public Object Operar(Object Valor1,int Tip1, Object Valor2,int Tip2, TablaDeSimbolos Tabla) {
        int Salida = 0;
        switch (Tip1) {
           case 0:
                return Tipo0(Valor1, Valor2, Tip2, Tabla);
            case 1:
                return Tipo1(Valor1, Valor2, Tip2, Tabla);
            case 2:
                return Tipo2(Valor1, Valor2, Tip2, Tabla);
            case 3:
                return Tipo3(Valor1, Valor2, Tip2, Tabla);

        }
        return Salida;
    }
    
}
