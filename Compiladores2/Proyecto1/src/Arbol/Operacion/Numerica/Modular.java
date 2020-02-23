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
public class Modular extends Operacion{
    private Object Tipo1(Object Exec1, Object Exec2, int Tipo, TablaDeSimbolos Tabla) {
        if (Exec1 == null) {
            Exec1 = 0.0;
        }
        switch (Tipo) {
            case 1:
                if (Exec2 == null) {
                    Exec2 = 0.0;
                }
                this.ValorTipo=1;
                return (double)Exec1%(double)Exec2;
            case 2:
                if (Exec2 == null) {
                    Exec2 = 0;
                }
                this.ValorTipo=1;
                return (double)Exec1%(int)Exec2;
            default:
                Tabla.AgregarExcepcion(new Excepcion("No Se Hacer Modular  "+this.TipoATexto(1)+" Con "+this.TipoATexto(Tipo)));
                return null;
        }
    }
    private Object Tipo2(Object Exec1, Object Exec2, int Tipo, TablaDeSimbolos Tabla) {
        if (Exec1 == null) {
            Exec1 = 0;
        }
        switch (Tipo) {
            case 1:
                if (Exec2 == null) {
                    Exec2 = 0.0;
                }
                this.ValorTipo=1;
                return (int)Exec1%(double)Exec2;
            case 2:
                if (Exec2 == null) {
                    Exec2 = 0;
                }
                this.ValorTipo=2;
                return (int)Exec1%(int)Exec2;
            default:
                Tabla.AgregarExcepcion(new Excepcion("No Se Puede Hacer Modular "+this.TipoATexto(2)+" Con "+this.TipoATexto(Tipo)));
                return null;
        }
    }
    
    @Override
    public Object Operar(Object Valor1,int Tip1, Object Valor2,int Tip2, TablaDeSimbolos Tabla) {
        switch (Tip1) {
            case 1:
                return Tipo1(Valor1, Valor2, Tip2, Tabla);
            case 2:
                return Tipo2(Valor1, Valor2, Tip2, Tabla);
            default:
                Tabla.AgregarExcepcion(new Excepcion("No Se Puede Hacer Modular "+this.TipoATexto(Tip1)+" Con "+this.TipoATexto(Tip2)));
                return null;
        }
    }
}
