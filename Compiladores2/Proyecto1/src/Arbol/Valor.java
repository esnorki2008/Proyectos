/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol;

import Arbol.Operacion.*;
import Arbol.Operacion.Logica.Igualdad;
import Arbol.Operacion.Numerica.Division;
import Arbol.Operacion.Numerica.Modular;
import Arbol.Operacion.Numerica.Multiplicacion;
import Arbol.Operacion.Numerica.Negativo;
import Arbol.Operacion.Numerica.Positivo;
import Arbol.Operacion.Numerica.Potencia;
import Arbol.Operacion.Numerica.Resta;
import Arbol.Operacion.Numerica.Suma;
import Elementos.TablaDeSimbolos;

/**
 *
 * @author Norki
 */
public class Valor implements Instruccion {

    /**
     * @return the Valor1
     */
    public Instruccion getValor1() {
        return Valor1;
    }

    /**
     * @param Valor1 the Valor1 to set
     */
    public void setValor1(Instruccion Valor1) {
        this.Valor1 = Valor1;
    }

    /**
     * @return the Valor2
     */
    public Instruccion getValor2() {
        return Valor2;
    }

    /**
     * @param Valor2 the Valor2 to set
     */
    public void setValor2(Instruccion Valor2) {
        this.Valor2 = Valor2;
    }

    /**
     * @return the TipoOpe
     */
    public String getTipoOpe() {
        return TipoOpe;
    }

    /**
     * @param TipoOpe the TipoOpe to set
     */
    public void setTipoOpe(String TipoOpe) {
        this.TipoOpe = TipoOpe;
    }

    /**
     * @return the TamOpe
     */
    public int getTamOpe() {
        return TamOpe;
    }

    /**
     * @param TamOpe the TamOpe to set
     */
    public void setTamOpe(int TamOpe) {
        this.TamOpe = TamOpe;
    }

    /**
     * @return the ValorPuntual
     */
    public String getValorPuntual() {
        return ValorPuntual;
    }

    /**
     * @param ValorPuntual the ValorPuntual to set
     */
    public void setValorPuntual(String ValorPuntual) {
        this.ValorPuntual = ValorPuntual;
    }
    private Instruccion Valor1, Valor2;
    private String TipoOpe;
    private int TamOpe;
    private String ValorPuntual;
    private Object SalidaValor;
    private int Tipo = -1;

    public int getTipo() {
        return Tipo;
    }

    public Valor(String Tipo, Instruccion Valor1, String ValorPuntual) {
        this.TipoOpe = Tipo;
        this.Valor1 = Valor1;
        this.Valor2 = null;
        TamOpe = 4;
        ValorPuntual = null;
        this.ValorPuntual = ValorPuntual;
    }

    public Valor(String Tipo, String ValorPuntual) {
        this.TipoOpe = Tipo;
        this.Valor1 = null;
        this.Valor2 = null;
        TamOpe = 2;
        this.ValorPuntual = ValorPuntual;
    }

    public Valor(String Tipo, Instruccion Valor1, Instruccion Valor2) {
        this.TipoOpe = Tipo;
        this.Valor1 = Valor1;
        this.Valor2 = Valor2;
        TamOpe = 3;
        ValorPuntual = null;
    }

    public Valor(String Tipo, Instruccion Valor1) {
        this.TipoOpe = Tipo;
        this.Valor1 = Valor1;
        this.Valor2 = null;
        TamOpe = 2;
        ValorPuntual = null;
    }

    public Object SalidaEjecucion() {
        return SalidaValor;
    }

    @Override
    public void Ejecutar(TablaDeSimbolos Tabla) {
        SalidaValor = CargarDatos(Tabla);
    }

    private Object CargarDatos(TablaDeSimbolos Tabla) {
        Object Retorno = null;
        Operacion Ope = null;
        Valor Val1 = (Valor) Valor1;
        Valor Val2 = (Valor) Valor2;
        if (Val1 != null) {
            Val1.Ejecutar(Tabla);
        }
        if (Val2 != null) {
            Val2.Ejecutar(Tabla);
        }
        Object Out;
        switch (this.TipoOpe.toLowerCase()) {
            case "+":
                if (TamOpe == 3) {
                    Ope = new Suma();
                    Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                    this.Tipo = Ope.GetTipo();
                    return Out;
                } else {
                    Ope = new Positivo();
                    Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Tabla);
                    this.Tipo = Ope.GetTipo();
                    return Out;
                }
            case "-":
                if (TamOpe == 3) {
                    Ope = new Resta();
                    Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                    this.Tipo = Ope.GetTipo();
                    return Out;
                } else {
                    Ope = new Negativo();
                    Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Tabla);
                    this.Tipo = Ope.GetTipo();
                    return Out;
                }
            case "*":
                Ope = new Multiplicacion();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            case "/":
                Ope = new Division();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            case "^":
                Ope = new Potencia();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            case "%%":
                Ope = new Modular();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            case "var":
                return Tabla.BuscarVariable(this.ValorPuntual);
            case "entero":
                this.Tipo = 2;
                return Integer.parseInt(this.ValorPuntual);
            case "cadena":
                this.Tipo = 3;
                return (this.ValorPuntual);
            case "booleano":
                this.Tipo = 0;
                if (this.ValorPuntual.equalsIgnoreCase("true")) {
                    return true;
                } else {
                    return false;
                }
            case "decimal":
                return Double.parseDouble(this.ValorPuntual);
            default:
                return OperacionesNumericas(Tabla);
        }
        //System.out.println(TipoOpe + "    --      " + this.ValorPuntual);
        //return Retorno;
    }
    private Object OperacionesNumericas(TablaDeSimbolos Tabla) {
        Operacion Ope = null;
        Valor Val1 = (Valor) Valor1;
        Valor Val2 = (Valor) Valor2;
        if (Val1 != null) {
            Val1.Ejecutar(Tabla);
        }
        if (Val2 != null) {
            Val2.Ejecutar(Tabla);
        }
        Object Out=null;
        switch (this.TipoOpe.toLowerCase()) {
            case "+":
                if (TamOpe == 3) {
                    Ope = new Suma();
                    Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                    this.Tipo = Ope.GetTipo();
                    return Out;
                } else {
                    Ope = new Positivo();
                    Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Tabla);
                    this.Tipo = Ope.GetTipo();
                    return Out;
                }
            case "-":
                if (TamOpe == 3) {
                    Ope = new Resta();
                    Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                    this.Tipo = Ope.GetTipo();
                    return Out;
                } else {
                    Ope = new Negativo();
                    Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Tabla);
                    this.Tipo = Ope.GetTipo();
                    return Out;
                }
            case "*":
                Ope = new Multiplicacion();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            case "/":
                Ope = new Division();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            case "^":
                Ope = new Potencia();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            case "%%":
                Ope = new Modular();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            default :
                return OperacionesLogicas(Tabla);
        }
    }
    private Object OperacionesLogicas(TablaDeSimbolos Tabla){
        Operacion Ope = null;
        Valor Val1 = (Valor) Valor1;
        Valor Val2 = (Valor) Valor2;
        if (Val1 != null) {
            Val1.Ejecutar(Tabla);
        }
        if (Val2 != null) {
            Val2.Ejecutar(Tabla);
        }
        Object Out=null;
        switch (this.TipoOpe.toLowerCase()) {
            case "==":
                Ope = new Igualdad();
                Out = Ope.Operar(Val1.SalidaEjecucion(), Val1.Tipo, Val2.SalidaEjecucion(), Val2.Tipo, Tabla);
                this.Tipo = Ope.GetTipo();
                return Out;
            default :
                System.out.println(TipoOpe + "    --      " + this.ValorPuntual);
                return Out;
        }
    }
    @Override
    public String Graficar() {
        String Nombre = NombreGrafico() + "T";
        String Retorno;
        Retorno = this.NombreGrafico() + "[label=\"Valor\"]" + "\n";
        Retorno = Retorno + Nombre + "[label=\"" + getTipoOpe() + "\"]" + "\n";
        Retorno = Retorno + NombreGrafico() + "->" + Nombre + "\n";

        if (this.getValor1() != null) {
            Retorno = Retorno + Nombre + "->" + getValor1().NombreGrafico() + "\n";
            Retorno = Retorno + getValor1().Graficar();
        }
        if (this.getValor2() != null) {
            Retorno = Retorno + Nombre + "->" + getValor2().NombreGrafico() + "\n";
            Retorno = Retorno + getValor2().Graficar();
        }
        if (this.getValorPuntual() != null) {
            Retorno = Retorno + Nombre + "V[label=\"" + getValorPuntual() + "\"]\n";
            Retorno = Retorno + Nombre + "->" + Nombre + "V" + "\n";
        }
        return Retorno;
    }

    @Override
    public String NombreGrafico() {
        return this.toString().replace(".", "").replace("@", "");
    }

}
