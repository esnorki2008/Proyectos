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
public class Or extends Operacion{
    private Object Tipo0(Object Exec1, Object Exec2, int Tipo, TablaDeSimbolos Tabla) {
        if (Exec1 == null) {
            Exec1 = false;
        }
        switch (Tipo) {
            case 0:
                if (Exec2 == null) {
                    Exec2 = false;
                }
                this.ValorTipo=0;
                return (boolean)Exec1 || (boolean)Exec2;
            default:
                Tabla.AgregarExcepcion(new Excepcion("No Se Puede Hacer OR "+this.TipoATexto(3)+" Con "+this.TipoATexto(Tipo)));
                return null;
        }
    }

    @Override
    public Object Operar(Object Valor1,int Tip1, Object Valor2,int Tip2, TablaDeSimbolos Tabla) {
        switch (Tip1) {
           case 0:
                return Tipo0(Valor1, Valor2, Tip2, Tabla);
           
            default:
                Tabla.AgregarExcepcion(new Excepcion("No Se Puede Hacer OR "+this.TipoATexto(Tip1)+" Con "+this.TipoATexto(Tip2)));
                return null;
        }
    }
}
