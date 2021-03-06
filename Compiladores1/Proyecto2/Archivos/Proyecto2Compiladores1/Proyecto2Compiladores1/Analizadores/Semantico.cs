﻿using Irony.Parsing;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using Proyecto2Compiladores1.Contenido;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Runtime.Serialization;
using System.Drawing;
using System.Globalization;

namespace Proyecto2Compiladores1.Analizadores
{
    class Semantico
    {
        public List<Figura> ListaFiguras = new List<Figura>();
        private NodoArbol Defecto = null;
        private String Caso = "";
        private Clase ClaseActual;
        private Metodo MetodoActual;
        public Hashtable Temporales { get; set; } = new Hashtable();
        public Hashtable Errores { get; set; } = new Hashtable();
        public Hashtable Clases { get; set; } = new Hashtable();
        private Boolean RetornoID = false;
        private Variable VariableActual;
        private int Visibilidad = 0;
        private String TipoString = "";
        private int Mod = 0;
        private Boolean Tempo = false;
        public Semantico(NodoArbol Ingreso)
        {
            Analizar(Ingreso,null);
        }


        int Uno = 0;
        int Dos = 0;
        int Tres = 0;
        int Llaa = 0;
        int Llac = 0;
        private NodoArbol Analizar(NodoArbol Nodo,Hashtable VariablesActuales)
        {
            // if (Nodo.Contenido != null) { Console.WriteLine(Nodo.Contenido); }

           
            Variable VariableNueva = new Variable("NOEXISTE");
          
            switch (Nodo.Contenido.ToLower().Trim())
            {
                case "programa":
                    AnalizarTodo(Nodo, new Hashtable());
                    break;
                case "clas":
                    ClaseActual = new Clase();
                    ClaseActual.SetNombre(Nodo.Hijos[1].Contenido);
                    Analizar(Nodo.Hijos[3], ClaseActual.GetVariables());
                  
                    break;
                case "clase":
                    break;
                case "clasp":
                    AnalizarTodo(Nodo, VariablesActuales);
                    break;
                case "contecla":
                    if (Nodo.Hijos.Count() == 0)
                    {
                      
                        Clases.Add(ClaseActual.GetNombre(), ClaseActual);
                    }
                    else
                    {
                        AnalizarTodo(Nodo, VariablesActuales);
                    }
                    break;
                case "comprob":
                    Caso=Analizar(Nodo.Hijos[1], VariablesActuales).Contenido;
                    Analizar(Nodo.Hijos[2], VariablesActuales);
                    Hashtable Copia = new Hashtable();
                    Copia = (Hashtable)VariablesActuales.Clone();
                    if (Defecto != null) { Analizar(Defecto, Copia); }
                    break;
                case "comprobp":
               
                    switch (Nodo.Hijos.Count) {
                        case 0:
                            break;
                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            Copia = (Hashtable)VariablesActuales.Clone();
                            Defecto = Nodo.Hijos[1];
                            Analizar(Nodo.Hijos[2], Copia);
                            break;
                        case 4:
                            Copia = (Hashtable)VariablesActuales.Clone();
                            String CasoStr = Analizar(Nodo.Hijos[1], Copia).Contenido;
                            if (Caso.ToLower().Trim().Equals(CasoStr.ToLower().Trim()))
                            {
                                Defecto = null;
                                Analizar(Nodo.Hijos[2], Copia);
                            }
                            else
                            {
                                Analizar(Nodo.Hijos[3], Copia);
                            }
                            break;
                    }
                   

                
                   
                    break;
                case "visi":
                    AnalizarTodo(Nodo, VariablesActuales);
                    break;
                case "decla":



                    Decla(Nodo,  VariablesActuales);

                    break;
                case "parametrometodo":
                    if (Nodo.Hijos.Count > 0)
                    {
                        Analizar(Nodo.Hijos[0], VariablesActuales);
                    }
                    break;
                case "parametrometodop":
                 
                    Variable Novo = new Variable(Nodo.Hijos[1].Contenido);
                    Novo.SetNombreTipo(Nodo.Hijos[0].Contenido);
                    MetodoActual.GetVariables().Add(((MetodoActual.GetVariables().Count/2)+"").Trim(), Novo);
                    MetodoActual.GetVariables().Add(Novo.GetNombre().ToLower().Trim(),Novo);
                    MetodoActual.CantidadMaxima = (MetodoActual.GetVariables().Count / 2);
                    if (Nodo.Hijos.Count == 3) {
                        Analizar(Nodo.Hijos[2],VariablesActuales);
                    }
                    break;
                case "metodo":
                    Metodo NuevoMetodo = new Metodo(Nodo.Hijos[1].Contenido, Nodo.Hijos[0].Contenido, Nodo.Hijos[4]);
                    NuevoMetodo.Tipo = Nodo.Hijos[0].Contenido;
                    MetodoActual = NuevoMetodo;
                    Analizar(Nodo.Hijos[3], VariablesActuales);
                    MetodoActual.SetRaiz(Nodo.Hijos[4]);
                    ClaseActual.GetMetodos().Add(NuevoMetodo.GetNombre().ToLower().Trim()+"metodo",NuevoMetodo);
                    MetodoActual = null;
                    break;
                case "mai":
                    Copia = new Hashtable();
                    Copia = (Hashtable)VariablesActuales.Clone();
                    MetodoActual = new Metodo("main");
                    MetodoActual.SetRaiz(Nodo.Hijos[1]);
                    ClaseActual.GetMetodos().Add(MetodoActual.GetNombre(), MetodoActual);
                    Analizar(MetodoActual.GetRaiz(), Copia);
                    MetodoActual.SetVariables(Copia);
                    MetodoActual = null;
                    break;
                case "conteme":
                    for (int i = 0; i < Nodo.Hijos.Count; i++)
                    {
                        Analizar(Nodo.Hijos.ElementAt(i), VariablesActuales);

                    }
                    break;
                case "llamameto":
                    Metodo Anterior = MetodoActual;
                    
                    Metodo Meto =(Metodo)ClaseActual.GetMetodos()[Nodo.Hijos[0].Contenido.ToLower().Trim()+"metodo"];

                    Meto.CantidadLlena = 0;
                    Copia = new Hashtable();
                    Copia = (Hashtable)Meto.GetVariables().Clone();
                   
                   
                  
                    MetodoActual = Meto;
                    Analizar(Nodo.Hijos[1],Meto.GetVariables());
                    Analizar(Meto.GetRaiz(),Meto.GetVariables());

                    MetodoActual.SetVariables(Copia);

                    MetodoActual = Anterior;


                    break;
                case "llamavalor":
                      Anterior = MetodoActual;
                   
                    Meto = (Metodo)ClaseActual.GetMetodos()[Nodo.Hijos[0].Contenido.ToLower().Trim() + "metodo"];
                    MetodoActual = Meto;
                  
                    Meto.CantidadLlena = 0;
                    Copia = new Hashtable();
                    Copia = (Hashtable)Meto.GetVariables().Clone();


                    Analizar(Nodo.Hijos[1], Meto.GetVariables());
                    Analizar(Meto.GetRaiz(), Meto.GetVariables());
                
                    NodoArbol N ;

                    MetodoActual.SetVariables(Copia);
                    if (MetodoActual.Tipo.ToLower().Equals("void")) {
                        N = new NodoArbol("nullptr");
                        Variable Mala = new Variable("E" + Errores.Count);
                        Mala.SetNombreTipo("Mal uso del tipo void");
                        Mala.SetContenido("El tipo Void no puede retornar:  " + MetodoActual.GetNombre());
                        Errores.Add(Mala.GetNombre(), Mala);
                        Console.WriteLine("PARAMETROS CANTIDAD MALA AL LLAMAR EL VALOR "+ MetodoActual.Tipo);
                    }
                    else {
                       // Console.WriteLine("SIN SENTIDO  "+ MetodoActual.ValorRetorno);
                        N = new NodoArbol(MetodoActual.ValorRetorno);
                    }
                    
                    MetodoActual = Anterior;
                    
                  
                    return N;
                case "param":
                    if (Nodo.Hijos.Count > 1)
                    {
                        Analizar(Nodo.Hijos[0], VariablesActuales);
                    }
                    break;
                case "paramp":

                    Variable Param = (Variable)MetodoActual.GetVariables()[(MetodoActual.CantidadLlena+"").Trim()];
                    if (Param != null)
                    {
                      
                        NodoArbol Nodi = Analizar(Nodo.Hijos[0], VariablesActuales);
                        Param.SetContenido(Nodi.Contenido);
                        MetodoActual.CantidadLlena++;

                        if (Nodo.Hijos.Count > 1)
                        {
                            Analizar(Nodo.Hijos[1], VariablesActuales);
                        }
                    }
                    else {
                        Variable Mala = new Variable("E" + Errores.Count);
                        Mala.SetNombreTipo("Fuera De Indice En Parametros De Metodo");
                        Mala.SetContenido("Se intento ingresar una cantidad no valida de parametros para el metodo:  "+MetodoActual.GetNombre());
                        Errores.Add(Mala.GetNombre(), Mala);
                        Console.WriteLine("PARAMETROS CANTIDAD MALA AL INGRESAR LOS PARAMETROS");
                    }
                    break;
                case "mas":
                   return Mas(Nodo, VariablesActuales);
                case "valor":
                    NodoArbol Niu= new NodoArbol(AnalizarValor(Nodo, VariablesActuales).Contenido);
                    if (Llaa != 0) {
                        String Nmr = Uno + "p" + Dos + "s" + Tres + "t";
                        Variable Vari = new Variable(Nmr); Vari.SetContenido(Niu.Contenido);
                        VariableActual.VarArreglo[Nmr] = Vari ;
                        Uno++;

                    }
                    return Niu;
                case "aumen":
                    return Aumento(Nodo, VariablesActuales);
                case "privado":
                    Visibilidad = 1;
                    break;
                case "publico":
                    Visibilidad = 0;
                    break;
                case "para":

                    CicloPara(Nodo, VariablesActuales);
                    break;
                case "parap":

                    Analizar(Nodo.Hijos[0], VariablesActuales);
                    break;

                case "repetir":

                    CicloRepetir(Nodo, VariablesActuales);
                    break;
                case "cuando":

                    CicloCuando(Nodo, VariablesActuales);
                    break;
                case "hacerm":

                    CicloHacerm(Nodo, VariablesActuales);
                    break;
                case "valorretorno":
                    this.MetodoActual.ValorRetorno = Analizar(Nodo.Hijos[1],VariablesActuales).Contenido;                
                    break;
                case "llav":
                    return Llav(Nodo,VariablesActuales);
                case "si":
                    Si(Nodo, VariablesActuales);
                    break;
                case "arre":
                    Arre(Nodo, VariablesActuales);
                    break;             
                case "llaa":
                    Llaa++;
                    break;
                case "llac":
                    Uno = 0;
                 //   Console.WriteLine("¿? " + Llaa);
                    if (Llaa > Llac) { Llac = Llaa; }
                    switch (Llac) {
                        case 3:
                            switch (Llaa) {
                                case 3:
                                    Uno = 0;
                                    Dos++;
                                    break;
                                case 2:
                                    Uno = 0;
                                    Dos = 0;
                                    Tres++;
                                    break;
                            }
                            break;
                        case 2:
                            switch (Llaa)
                            {
                              
                                case 2:
                                    Uno = 0;
                                    Dos++;
                                    break;
                            }
                            break;
                        case 1:
                            break;
                        default:
                          
                            break;
                    }
                    Llaa--;
                    break;
                   
                case "sino":
                    Sino(Nodo, VariablesActuales);
                    break;
                case "figura":
                    FiguraMetodo(Nodo, VariablesActuales);
                    break;
                case "asigna":
                    Asigna(Nodo, VariablesActuales);
                    break;
                default:
             //       Console.WriteLine(Nodo.Contenido);
                    switch (Tipo(Nodo))
                    {
                        case 1:
                            return Nodo;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 6:

                            if (RetornoID)
                            {
                                Console.WriteLine("REtorno de ID No Implementado");
                            }
                            else
                            {
                                if (ClaseActual.GetNombre().Equals(""))
                                {
                                    ClaseActual.SetNombre((Nodo.Contenido));
                                }
                                else
                                {
                                    Console.WriteLine("Innecesario  " + Nodo.Contenido);
                                   // VariableActual = new Variable(Nodo.Contenido);
                                }

                            }
                            break;
                    }
                    break;

            }

            if (Nodo.Contenido != null)
            {
            }
            return Nodo;
        }
        private void FiguraMetodo(NodoArbol Nodo, Hashtable VariablesActuales) {
            NodoArbol N2 =  (new NodoArbol(Analizar(Nodo.Hijos[2], VariablesActuales).Contenido));
            NodoArbol N3 = (new NodoArbol(Analizar(Nodo.Hijos[3], VariablesActuales).Contenido));
            NodoArbol N4 = (new NodoArbol(Analizar(Nodo.Hijos[4], VariablesActuales).Contenido));
            NodoArbol N5 = (new NodoArbol(Analizar(Nodo.Hijos[5], VariablesActuales).Contenido));
            NodoArbol N6 = (new NodoArbol(Analizar(Nodo.Hijos[6], VariablesActuales).Contenido));

            Color Colori;
            if (N2.Contenido.Contains("#"))
            {
                N2.Contenido = N2.Contenido.Replace("#", "").Replace("\"","") ;
                  
                if (N2.Contenido.Length == 7)
                {
                    var r = Hex(N2.Contenido.Substring(0, 2));
                    var g = Hex(N2.Contenido.Substring(2, 2));
                    var b = Hex(N2.Contenido.Substring(4, 2));
                    Colori = Color.FromArgb(r, g, b);
                }
                else {
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("ERROR RGB");
                    Mala.SetContenido("se intento poner un valor rgb pero este no está correcto");
                    Errores.Add(Mala.GetNombre(), Mala);
                    Colori = Color.Black;
                }
                //  Console.WriteLine(r+" "+ g+" " + b);
               

              
             
              
            }
            else {
                Colori = Color.FromName(N2.Contenido.Replace("\"","").Trim().ToLower());
               
            }

          //  Console.WriteLine(Colori + "  Color");


            Boolean BR=false;
            Figura NuevaFigura;

            switch (Nodo.Hijos[1].Contenido.ToLower().Trim()) {
                case "circle":
                    Double V3 = double.Parse(N3.Contenido);
                    Console.WriteLine(N3.Contenido.ToLower().Trim()+ "CIRCLE");
                    if (N4.Contenido.ToLower().Trim().Equals("true")|| N4.Contenido.ToLower().Trim().Equals("verdadero")) { BR = true; }
                    Double V5 = double.Parse(N5.Contenido);
                    Double V6 = double.Parse(N6.Contenido);
                    NuevaFigura = new Figura("circle",Colori,V3,BR,V5,V6);
                    ListaFiguras.Add(NuevaFigura);
                    break;
                case "triangle":
                    NodoArbol N7 = (new NodoArbol(Analizar(Nodo.Hijos[7], VariablesActuales).Contenido));
                    NodoArbol N8 = (new NodoArbol(Analizar(Nodo.Hijos[8], VariablesActuales).Contenido));
                    NodoArbol N9 = (new NodoArbol(Analizar(Nodo.Hijos[9], VariablesActuales).Contenido));
                    double V4= double.Parse(N4.Contenido);
                    if (N3.Contenido.ToLower().Trim().Equals("true") || N3.Contenido.ToLower().Trim().Equals("verdadero")) { BR = true; }
                    V5 = double.Parse(N5.Contenido);
                    V6 = double.Parse(N6.Contenido);
                    double V7 = double.Parse(N7.Contenido);
                    double V8 = double.Parse(N8.Contenido);
                    double V9 = double.Parse(N9.Contenido);
                    NuevaFigura = new Figura("triangle",Colori, BR,V4 , V5, V6,V7,V8,V9);
                    ListaFiguras.Add(NuevaFigura);
                    break;
                case "square":
                    N7 = (new NodoArbol(Analizar(Nodo.Hijos[7], VariablesActuales).Contenido));
                    if (N3.Contenido.ToLower().Trim().Equals("true") || N3.Contenido.ToLower().Trim().Equals("verdadero")) { BR = true; }
                    V4 = double.Parse(N4.Contenido);
                    V5 = double.Parse(N5.Contenido);
                    V6 = double.Parse(N6.Contenido);
                    V7 = double.Parse(N7.Contenido);
                    
                    NuevaFigura = new Figura("square", Colori, BR, V4, V5, V6, V7);
                    ListaFiguras.Add(NuevaFigura);
                    break;
                case  "line":
                    N7 = (new NodoArbol(Analizar(Nodo.Hijos[7], VariablesActuales).Contenido));
                   
                    V4 = double.Parse(N4.Contenido);
                    V5 = double.Parse(N5.Contenido);
                    V6 = double.Parse(N6.Contenido);
                    V7 = double.Parse(N7.Contenido);
                    V3 = double.Parse(N3.Contenido);
                    NuevaFigura = new Figura("line", Colori, V3, V4, V5, V6, V7);
                    ListaFiguras.Add(NuevaFigura);
                    break;
                default:
                    Console.WriteLine(Nodo.Hijos[1].Contenido+  " MAL ESCRITO");
                    break;
            }


           
        }
        public int Hex(String Ingreso) {
          

            int A0 = HNum(Ingreso.Substring(0,1))*16;
            int A1 = HNum(Ingreso.Substring(1,1))*1;

            return A0 + A1;

        }
        public int HNum(String Ingreso)
        {
           
            switch (Ingreso.ToLower().Trim()) {
                case "a":
                    return 10;
                case "b":
                    return 11;
                case "c":
                    return 12;
                case "d":
                    return 13;
                case "e":
                    return 14;
                case "f":
                    return 15;
                default:
                    if (Char.IsNumber(Ingreso.ToCharArray()[0])) { return int.Parse(Ingreso); } else {
                        Variable Mala = new Variable("E" + Errores.Count);
                        Mala.SetNombreTipo("ERROR RGB");
                        Mala.SetContenido("se intento poner un valor rgb pero este no está correcto");
                        Errores.Add(Mala.GetNombre(), Mala);
                        Console.WriteLine(" E RGB");
                        return 4;
                    }
                   
            }
        }
        private void Asigna(NodoArbol Nodo,Hashtable VariablesActuales) {
            Variable Variab = (Variable)VariablesActuales[Nodo.Hijos[0].Contenido.ToLower().Split('(')[0].Trim()];
            if (Variab != null)
            {
                switch (Nodo.Hijos.Count) {
                    case 3:
                        NodoArbol Nodi = Analizar(Nodo.Hijos[2], VariablesActuales);
                        Variab.SetContenido(Nodi.Contenido);
                        VariablesActuales[Nodo.Hijos[0].Contenido.ToLower().Split('(')[0].Trim()] = Variab;
                        break;
                    case 4:
                        Variable Nueva = new Variable(Analizar(Nodo.Hijos[1], VariablesActuales).Contenido);
                        Nueva.SetContenido(Analizar(Nodo.Hijos[3], VariablesActuales).Contenido);
                       Variab.VarArreglo[Nueva.DarIndex(Analizar(Nodo.Hijos[1], VariablesActuales).Contenido)] = Nueva;
                        break;

                }
               

            }
            else
            {
                Variable Mala = new Variable("E" + Errores.Count);
                Mala.SetNombreTipo("Variable no encontrada");
                Mala.SetContenido("Se ha buscado una variable que no existe en este ambito");
                Errores.Add(Mala.GetNombre(), Mala);
            }
        }
        private NodoArbol AnalizarValor(NodoArbol Nodo, Hashtable VariablesActuales)
        {

            switch (Nodo.Hijos.Count())
            {

                case 1:
                    NodoArbol N1 = ValorSimple(Nodo,VariablesActuales);
                    return N1;
                case 2:

                    return ValorDoble(Nodo, VariablesActuales);
                case 3:

                    return ValorTriple(Nodo, VariablesActuales);
                default:

                    Console.WriteLine(" ERROR EN VALOR   ");
                    return Nodo;
            }



        }
        private NodoArbol Mas(NodoArbol Nodo, Hashtable VariablesActuales) {
            if (Nodo.Hijos.Count == 0)
            {
                Nodo.Contenido = ("") + "0";
                return Nodo;
            }
            else
            {
                NodoArbol Hijo1 = Nodo.Hijos[0];


                if (Hijo1.Contenido.Split('(')[0].Trim().Equals("="))
                {
                    return Analizar(Nodo.Hijos[1], VariablesActuales);
                }
                else
                {

                    Variable VariableNueva = new Variable(Hijo1.Contenido);
                    VariableNueva.SetNombreTipo(TipoString);
                    VariableNueva.SetVisibilidad(Visibilidad);
                    NodoArbol Hijo2 = Analizar(Nodo.Hijos[1], VariablesActuales);
                    VariableNueva.SetContenido(Hijo2.Contenido);

                    VariablesActuales.Add(VariableNueva.GetNombre().ToLower(), VariableNueva);
                    Variable SO = (Variable)VariablesActuales[VariableNueva.GetNombre()];
                    //   Console.WriteLine("HAS      "+(SO.GetNombre() ));
                    return Hijo2;
                }
            }
        }
        private void Si(NodoArbol Nodo,Hashtable VariablesActuales)
        {
            String Comprobacion = Analizar(Nodo.Hijos[1], VariablesActuales).Contenido.ToLower().Trim();
         //   Console.WriteLine(Comprobacion+"    En SI");
            Hashtable Copia = new Hashtable();
            Copia = (Hashtable)VariablesActuales.Clone();


            if (Comprobacion.Equals("true") || Comprobacion.Equals("verdadero"))
            {
                Analizar(Nodo.Hijos[2],Copia);
            }
            else
            {
                Console.WriteLine("SINO");
                Analizar(Nodo.Hijos[3],Copia);
            }
        }
        private void Sino(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            Hashtable Copia = new Hashtable();
            Copia = (Hashtable)VariablesActuales.Clone();
            Analizar(Nodo.Hijos[1],Copia);           
        }
        private void CicloHacerm(NodoArbol Nodo, Hashtable VariablesActuales)
        {
           
            String Comprobacion = Analizar(Nodo.Hijos[3],VariablesActuales).Contenido.ToLower().Trim();
            while (Comprobacion.Equals("true") || Comprobacion.Equals("verdadero"))
            {
                Hashtable Copia = new Hashtable();
                Copia = (Hashtable)VariablesActuales.Clone();
                Analizar(Nodo.Hijos[1],Copia);
                Comprobacion = Analizar(Nodo.Hijos[3],Copia).Contenido.ToLower();          
            } 
        }
        private void CicloCuando(NodoArbol Nodo, Hashtable VariablesActuales)
        {  
            String Comprobacion = Analizar(Nodo.Hijos[1],VariablesActuales).Contenido.ToLower().Trim();
            while (Comprobacion.Equals("true")|| Comprobacion.Equals("verdadero"))
            {
                Hashtable Copia = new Hashtable();
                Copia = (Hashtable)VariablesActuales.Clone();
                Analizar(Nodo.Hijos[2],Copia);
                Comprobacion = Analizar(Nodo.Hijos[1],Copia).Contenido.ToLower();
            }
        }
        private void CicloRepetir(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            int RepetirVeces=int.Parse(Analizar(Nodo.Hijos[1],VariablesActuales).Contenido);
          //  int Seguridad=0;       
            while (RepetirVeces>0)
            {
                Hashtable Copia = new Hashtable();
                Copia = (Hashtable)VariablesActuales.Clone();
                Analizar(Nodo.Hijos[2],Copia);
                RepetirVeces = RepetirVeces - 1;
               //Seguridad++;
               //Console.WriteLine(Seguridad);
               //if (Seguridad > 1000) { break; }
            }

          //  Console.WriteLine("REPETIR  " + Nodo.Contenido + "     " + Nodo.Hijos.Count);
        }
        private void CicloPara(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            Hashtable CopiaOriginal = new Hashtable();
            CopiaOriginal = (Hashtable)VariablesActuales.Clone();

            Analizar(Nodo.Hijos[1],CopiaOriginal);
            NodoArbol Nod2 = Analizar(Nodo.Hijos[2], CopiaOriginal);    
            while (Nod2.Contenido.ToLower().Trim().Equals("true") || (Nod2).Contenido.ToLower().Trim().Equals("verdadero"))
            {
                Hashtable Copia = new Hashtable();
                Copia = (Hashtable)CopiaOriginal.Clone();

                Analizar(Nodo.Hijos[4], Copia);
                Analizar(Nodo.Hijos[3], Copia);
                Nod2 = Analizar(Nodo.Hijos[2], Copia);

                
            }

        }
        private NodoArbol Tope(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            if (Nodo.Hijos.Count != 0)
            {

                return Tope(Nodo.Hijos[0],VariablesActuales);
            }
            else
            {
                if (Nodo.Hijos.Count == 2)
                {
                    NodoArbol Hijo2 = (Nodo.Hijos[1]);
                    Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();

                    if (Hijo2.Contenido.Equals("++")) { Mod++; } else { Mod--; }
                }


                return Nodo;
            }
        }
        private NodoArbol Aumento(NodoArbol Nodo, Hashtable VariablesActuales)
        {

            NodoArbol Hijo1 = new NodoArbol( Tope(Nodo.Hijos[0],VariablesActuales).Contenido);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
         //   Console.WriteLine("AUMENTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO *   "+Hijo1.Contenido);


            NodoArbol Hijo2 = new NodoArbol((Nodo.Hijos[1]).Contenido);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();

            switch (Hijo2.Contenido)
            {
                case "++":
                    Mod++;
                    break;
                case "--":
                    Mod--;
                    break;
                default:
                    Console.WriteLine("Error EN TODO ESTO");
                    return Nodo;
            }
            //  Console.WriteLine(Nodo.Contenido + "  AUMENTO  " + Nodo.Hijos.Count + "  " + Hijo1.Contenido + "  " + Nodo.Hijos[1].Contenido);

            Variable Variab = (Variable)VariablesActuales[Hijo1.Contenido.ToLower().Split('(')[0].Trim()];

            if ((Variab != null)&&(Tipo(Hijo1)==(6)))
            {
               
                Hijo1.Contenido = ("") + (Double.Parse(Variab.GetContenido()) + Mod);


                Variab.SetContenido(Hijo1.Contenido);
             /*   Console.WriteLine(Hijo1.Contenido+  "   AUMENTTTTTTTTTTTTTTTTTTTAAAAAAAAAAAAAAAAAAR "+ (Tipo(Hijo1)));
                ClaseActual.GetVariables()[Hijo1.Contenido.ToLower().Split('(')[0].Trim()] = Variab;*/
                Mod = 0;
            }
            else
            {

                Mod = 0;
                Console.WriteLine("Variable No Encontrada Aumento       " + Hijo1.Contenido.Split('(')[0].Trim().ToLower());
                Hijo1.Contenido = ("") + "nullptr";
                Variable Mala = new Variable("E" + Errores.Count);
                Mala.SetNombreTipo("Variable no encontrada");
                Mala.SetContenido("Se ha buscado una variable que no existe en este ambito  " + Hijo1.Contenido.Split('(')[0].Trim().ToLower());

                Errores.Add(Mala.GetNombre(), Mala);
            }
            //Console.WriteLine("SALIDA AUMENTO   " + Hijo1.Contenido);
            return Hijo1;
        }
        private NodoArbol ValorDoble(NodoArbol Nodo, Hashtable VariablesActuales)
        {


            NodoArbol Hijo1 = new NodoArbol((Nodo.Hijos[0]).Contenido);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = new NodoArbol(Analizar(Nodo.Hijos[1],VariablesActuales).Contenido);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();

            switch (Hijo1.Contenido)
            {
                case "!":
                    if (Hijo2.Contenido.ToLower().Equals("false") || Hijo2.Contenido.ToLower().Equals("falso"))
                    {
                        Hijo1.Contenido = ("") + "true";
                    }
                    else
                    {
                        Hijo1.Contenido = ("") + "false";
                    }
                    return Hijo1;
                case "-":
                    Hijo1.Contenido = ("") +((-1)*double.Parse(  Analizar(Hijo2, VariablesActuales).Contenido.Split('(')[0].Trim().ToLower()));
                    return Hijo1;

                default:
                    switch (Nodo.Hijos[1].Contenido.ToLower().Trim())
                    {
                        case "llav":

                            Variable Variab = (Variable)VariablesActuales[Nodo.Hijos[0].Contenido.ToLower().Split('(')[0].Trim()];
                            if (Variab != null)
                            {                                
                                        Variable Nueva =(Variable) Variab.VarArreglo[Variab.DarIndex(Hijo2.Contenido)];
                                if (Nueva != null) { 
                                        Variab.VarArreglo[Nueva.DarIndex(Analizar(Nodo.Hijos[1], VariablesActuales).Contenido)] = Nueva;
                                        Hijo1.Contenido = Nueva.GetContenido();                                       
                                         return Hijo1;
                                }
                                else
                                {
                                    Variable Mala = new Variable("E" + Errores.Count);
                                    Mala.SetNombreTipo("Error en llamado del arreglo");
                                    Mala.SetContenido("Arreglo fuera de indice o el valor llamado es nulo");
                                    Errores.Add(Mala.GetNombre(), Mala);
                                    Console.WriteLine("Arreglo fuera de indice o el valor llamado es nulo");
                                    Nodo.Contenido = "nullptrarray";
                                    return Nodo;
                                }
                            }
                            else
                            {
                                Variable Mala = new Variable("E" + Errores.Count);
                                Mala.SetNombreTipo("Variable no encontrada");
                                Mala.SetContenido("Se ha buscado una variable que no existe en este ambito");
                                Errores.Add(Mala.GetNombre(), Mala);
                                Console.WriteLine("Se ha buscado u");
                                Nodo.Contenido = "nullptr";
                                return Nodo;
                            }
                        default:
                         
                            break;
                    }

                    Console.WriteLine("Error Doble  " + Hijo1.Contenido);
                    return Nodo;
            }
        }
        private NodoArbol ValorSimple(NodoArbol Nodo, Hashtable VariablesActuales)
        {
           

            NodoArbol Salida = new NodoArbol(Nodo.Contenido);
            Salida.Contenido = Analizar(Nodo.Hijos[0], VariablesActuales).Contenido;


            //   if (Salida.Contenido.ToLower().Trim().Equals("valor")) { return Analizar(Salida,VariablesActuales); }

            switch (Tipo(Salida))
            {
                case 6:
                   
                    Variable Variab = (Variable)VariablesActuales[Nodo.Hijos[0].Contenido.ToLower().Split('(')[0].Trim()];
                    if (Variab != null)
                    {

                        Salida.Contenido = ("") + Variab.GetContenido().Split('(')[0].Trim();
                        
                        return Salida;
                    }
                    else
                    {
                        Console.WriteLine("ERROR VARIABLE NO ENCONTRADA    EN SIMPLE |" + Nodo.Hijos[0].Contenido);
                        Variable Mala = new Variable("E" + Errores.Count);
                        Mala.SetNombreTipo("Variable no encontrada");
                        Mala.SetContenido("Se ha buscado una variable que no existe en este ambito  " + Nodo.Hijos[0].Contenido);
                        Errores.Add(Mala.GetNombre(), Mala);
                        Nodo.Hijos[0].Contenido = ("") + "nullptr";
                        return Nodo.Hijos[0];
                    }
                case 1:
                    return Salida;
                case 2:
                    return Salida;
                case 3:
                    return Salida;
                case 4:
                    return Salida;
                case 5:
                    return Salida;
                default:
                    Console.WriteLine("REGEX REGULAR NO ENCONTRADA      " + Tipo(Nodo.Hijos[0]) + "   " + Nodo.Hijos[0].Contenido);
                    return Salida;

            }


        }
        private NodoArbol ValorTriple(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            switch (Nodo.Hijos[1].Contenido.Split('(')[0].Trim())
            {
                case "+":

                    return Suma(Nodo, VariablesActuales);
                case "-":
                    return Resta(Nodo, VariablesActuales);
                case "*":
                    return Multiplicacion(Nodo, VariablesActuales);
                case "/":
                    return Division(Nodo, VariablesActuales);
                case "^":
                    return Potencia(Nodo, VariablesActuales);
                case "==":
                    return DobleIgual(Nodo, VariablesActuales);
                case "!=":
                    return Diferente(Nodo, VariablesActuales);
                case "<":
                    return Menor(Nodo, VariablesActuales);
                case ">":
                    return Mayor(Nodo, VariablesActuales);
                case "<=":
                    return MenorIgual(Nodo, VariablesActuales);
                case ">=":
                    return MayorIgual(Nodo, VariablesActuales);
                case "&&":
                    return AndDoble(Nodo, VariablesActuales);
                case "||":
                    return OrDoble(Nodo, VariablesActuales);
                default:
                    Console.WriteLine("ERROR EN VALOR TRIPLE    " + Nodo.Hijos[1].Contenido.Split('(')[0].Trim());
                    return Nodo;

            }
        }
        private NodoArbol OrDoble(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();


            if (Hijo1.Contenido.ToLower().Equals("true") || Hijo2.Contenido.ToLower().Equals("true")) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        private NodoArbol AndDoble(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();


            if (Hijo1.Contenido.ToLower().Equals("true") && Hijo2.Contenido.ToLower().Equals("true")) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        private NodoArbol MayorIgual(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo1 = Tipo(Hijo1);
            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo2 = Tipo(Hijo2);

            switch (Tipo1)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 4:
                    Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido);
                    break;
                case 5:
                    Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido);
                    break;
                default:
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("Solo se permiten valores númericos o transformables a ellos");
                    Mala.SetContenido("Se intento comprobar menor " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala.GetNombre(), Mala);
                    Console.WriteLine("ERROR TIPO DESCONOCIDO Menor");
                    return Nodo;
            }
            switch (Tipo2)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 4:
                    Hijo2.Contenido = ("") + IntChar(Hijo2.Contenido);
                    break;
                case 5:
                    Hijo2.Contenido = ("") + IntBool(Hijo2.Contenido);
                    break;
                default:
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("Solo se permiten valores númericos o transformables a ellos");
                    Mala.SetContenido("Se intento comprobar menor " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala.GetNombre(), Mala);
                    Console.WriteLine("ERROR TIPO DESCONOCIDO MayorIgual");
                    return Nodo;
            }

            if (double.Parse(Hijo1.Contenido) >= double.Parse(Hijo2.Contenido)) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        private NodoArbol MenorIgual(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo1 = Tipo(Hijo1);
            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo2 = Tipo(Hijo2);

            switch (Tipo1)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 4:
                    Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido);
                    break;
                case 5:
                    Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido);
                    break;
                default:
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("Solo se permiten valores númericos o transformables a ellos");
                    Mala.SetContenido("Se intento comprobar menor " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala.GetNombre(), Mala);
                    Console.WriteLine("ERROR TIPO DESCONOCIDO Menorigual");
                    return Nodo;
            }
            switch (Tipo2)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 4:
                    Hijo2.Contenido = ("") + IntChar(Hijo2.Contenido);
                    break;
                case 5:
                    Hijo2.Contenido = ("") + IntBool(Hijo2.Contenido);
                    break;
                default:
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("Solo se permiten valores númericos o transformables a ellos");
                    Mala.SetContenido("Se intento comprobar menor " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala.GetNombre(), Mala);
                    Console.WriteLine("ERROR TIPO DESCONOCIDO Menor");
                    return Nodo;
            }

            if (double.Parse(Hijo1.Contenido) <= double.Parse(Hijo2.Contenido)) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        private NodoArbol Mayor(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo1 = Tipo(Hijo1);
            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo2 = Tipo(Hijo2);

            switch (Tipo1)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 4:
                    Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido);
                    break;
                case 5:
                    Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido);
                    break;
                default:
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("Solo se permiten valores númericos o transformables a ellos");
                    Mala.SetContenido("Se intento comprobar menor " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala.GetNombre(), Mala);
                    Console.WriteLine("ERROR TIPO DESCONOCIDO Mayor");
                    return Nodo;
            }
            switch (Tipo2)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 4:
                    Hijo2.Contenido = ("") + IntChar(Hijo2.Contenido);
                    break;
                case 5:
                    Hijo2.Contenido = ("") + IntBool(Hijo2.Contenido);
                    break;
                default:
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("Solo se permiten valores númericos o transformables a ellos");
                    Mala.SetContenido("Se intento comprobar menor " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala.GetNombre(), Mala);
                    Console.WriteLine("ERROR TIPO DESCONOCIDO Menor");
                    return Nodo;
            }

            if (double.Parse(Hijo1.Contenido) > double.Parse(Hijo2.Contenido)) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        
        private NodoArbol Llav(NodoArbol Nodo, Hashtable VariablesActuales )
        {
            switch (Nodo.Hijos.Count) {
                case 3:
                    return new NodoArbol(Analizar(Nodo.Hijos[1], VariablesActuales).Contenido );

                case 4:
                   
                    return new NodoArbol(Analizar(Nodo.Hijos[3], VariablesActuales).Contenido+"|"+ Analizar(Nodo.Hijos[1], VariablesActuales).Contenido);
                   
            }
            return new NodoArbol("Arraynullptr");
        }
        private void Decla(NodoArbol Nodo, Hashtable VariablesActuales) {
            switch (Nodo.Hijos.Count) {
                case 3:
            NodoArbol Hijo1,Hijo2, Hijo3;
            Variable VariableNueva;
            Hijo1 = Nodo.Hijos[0];
            TipoString = Hijo1.Contenido;
            Hijo2 = Nodo.Hijos[1];
            VariableNueva = new Variable(Hijo2.Contenido);
            VariableNueva.SetNombreTipo(TipoString);
            VariableNueva.SetVisibilidad(Visibilidad);
            Hijo3 = Analizar(Nodo.Hijos[2], VariablesActuales);
            VariableNueva.SetContenido(Hijo3.Contenido);                       
            Variable Variab1 = (Variable)VariablesActuales[VariableNueva.GetNombre().ToLower().Split('(')[0].Trim()];
            if (Variab1 == null)
            {
                VariablesActuales.Add(VariableNueva.GetNombre().ToLower().Split('(')[0].Trim(), VariableNueva);
            }
            else
            {
                Variab1 = VariableNueva;
                Console.WriteLine("ARREGLAR ASIGNACIONES PARA CILOS E IFS   "+Variab1.GetNombre());
            }
            break;
                case 5:
                    
                    Hijo1 = Nodo.Hijos[0];
                    TipoString = Hijo1.Contenido;
                    Hijo2 = Nodo.Hijos[2];
                    VariableNueva = new Variable(Hijo2.Contenido);
                    VariableNueva.SetNombreTipo(TipoString);
                    VariableNueva.SetVisibilidad(Visibilidad);
                    //  Hijo3 = Analizar(Nodo.Hijos[2], VariablesActuales);
                    //   VariableNueva.SetContenido(Hijo3.Contenido);
                  
   
                       Variab1 = (Variable)VariablesActuales[VariableNueva.GetNombre().ToLower().Split('(')[0].Trim()];
                    if (Variab1 == null)
                    {
                        Hijo1 = Analizar(Nodo.Hijos[4], VariablesActuales);
                        VariableNueva.DarTamaño(Hijo1.Contenido);
                        VariablesActuales.Add(VariableNueva.GetNombre().ToLower().Split('(')[0].Trim(), VariableNueva);
                     
                        

                      
                    }
                    else
                    {
                        Variab1 = VariableNueva;
                        Console.WriteLine("ARREGLAR ASIGNACIONES PARA CILOS E IFS       " + Variab1.GetNombre());
                    }

                    break;
                case 7:
                    Hijo1 = Nodo.Hijos[0];
                    TipoString = Hijo1.Contenido;
                    Hijo2 = Nodo.Hijos[2];
                    VariableNueva = new Variable(Hijo2.Contenido);
                    VariableNueva.SetNombreTipo(TipoString);
                    VariableNueva.SetVisibilidad(Visibilidad);
                    //  Hijo3 = Analizar(Nodo.Hijos[2], VariablesActuales);
                    //   VariableNueva.SetContenido(Hijo3.Contenido);


                    Variab1 = (Variable)VariablesActuales[VariableNueva.GetNombre().ToLower().Split('(')[0].Trim()];
                    if (Variab1 == null)
                    {

                        Hijo1 = Analizar(Nodo.Hijos[4], VariablesActuales);
                        VariableNueva.DarTamaño(Hijo1.Contenido);
                        VariableActual = VariableNueva;
                        Analizar(Nodo.Hijos[6], VariablesActuales);
                        VariablesActuales.Add(VariableNueva.GetNombre().ToLower().Split('(')[0].Trim(), VariableNueva);

                      



                    }
                    else
                    {
                        Variab1 = VariableNueva;
                        Console.WriteLine("ARREGLAR ASIGNACIONES PARA CILOS E IFS       " + Variab1.GetNombre());
                    }
                  
                    break;
                  
        }
            Llac=Llaa=Uno=Dos=Tres= 0;
        }
        private void Arre(NodoArbol Nodo, Hashtable VariablesActuales) {
            AnalizarTodo(Nodo,VariablesActuales);
        }
        private NodoArbol Menor(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            NodoArbol hola = new NodoArbol(Nodo.Hijos[0].Contenido);



            NodoArbol Hijo1 = new NodoArbol(Analizar(Nodo.Hijos[0], VariablesActuales).Contenido);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo1 = Tipo(Hijo1);
            NodoArbol Hijo2 = new NodoArbol(Analizar(Nodo.Hijos[2], VariablesActuales).Contenido);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo2 = Tipo(Hijo2);

            switch (Tipo1)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 4:
                    Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido);
                    break;
                case 5:
                    Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido);
                    break;
                default:
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("Solo se permiten valores númericos o transformables a ellos");
                    Mala.SetContenido("Se intento comprobar menor " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala.GetNombre(), Mala);
                    Console.WriteLine("ERROR TIPO DESCONOCIDO Menor");
                    return Nodo;
            }
            switch (Tipo2)
            {
                case 1:
                    break;
                case 2:
                    break;
                case 4:
                    Hijo2.Contenido = ("") + IntChar(Hijo2.Contenido);
                    break;
                case 5:
                    Hijo2.Contenido = ("") + IntBool(Hijo2.Contenido);
                    break;
                default:
                    Variable Mala = new Variable("E" + Errores.Count);
                    Mala.SetNombreTipo("Solo se permiten valores númericos o transformables a ellos");
                    Mala.SetContenido("Se intento comprobar menor " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala.GetNombre(), Mala);
                    Console.WriteLine("ERROR TIPO DESCONOCIDO Menor");
                    return Nodo;
            }
           
            if (double.Parse(Hijo1.Contenido) < double.Parse(Hijo2.Contenido)) { hola.Contenido = ("") + "true"; } else { hola.Contenido = ("") + "false"; }
          //  Console.WriteLine("MENOR    " + Hijo1.Contenido + "     " + Hijo2.Contenido+"       "+hola.Contenido);

            return hola;
        }
        private NodoArbol DobleIgual(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();


            if (Hijo1.Contenido.ToLower().Equals(Hijo2.Contenido.ToLower())) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        private NodoArbol Diferente(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();

            if (Hijo1.Contenido.ToLower().Equals(Hijo2.Contenido.ToLower())) { Hijo1.Contenido = ("") + "false"; } else { Hijo1.Contenido = ("") + "true"; }

            return Hijo1;
        }
        private NodoArbol Suma(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim();
            Tipo2 = Tipo(Hijo2);

            // Console.WriteLine(Tipo2+"     +++++++++++++++++++++++++++      " + Hijo2.Contenido);

            switch (Tipo1)
            {
                case 1:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + (int.Parse(Hijo1.Contenido) + int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) + double.Parse((Hijo2.Contenido)));
                            return Hijo1;
                        case 3:
                            Hijo1.Contenido = ("") + ((Hijo1.Contenido) + (Hijo2.Contenido));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + (int.Parse(Hijo1.Contenido) + IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + (int.Parse(Hijo1.Contenido) + IntBool(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Suma de valores no permitidos");
                            Mala.SetContenido("Se intento sumar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO SUMA1");
                            return Nodo;
                    }
                case 2:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) + double.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) + double.Parse((Hijo2.Contenido)));
                            return Hijo1;
                        case 3:
                            Hijo1.Contenido = ("") + ((Hijo1.Contenido) + (Hijo2.Contenido));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) + IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) + IntBool(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Suma de valores no permitidos");
                            Mala.SetContenido("Se intento sumar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO SUMA2");
                            return Nodo;
                    }
                case 3:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + "\"" + (Hijo1.Contenido).Replace("\"", "") + (Hijo2.Contenido) + "\"";
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + "\"" + (Hijo1.Contenido).Replace("\"", "") + (Hijo2.Contenido) + "\"";
                            return Hijo1;
                        case 3:
                            Hijo1.Contenido = ("") + "\"" + (Hijo1.Contenido).Replace("\"", "") + (Hijo2.Contenido).Replace("\"", "") + "\"";
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + "\"" + (Hijo1.Contenido).Replace("\"", "") + (Hijo2.Contenido).Replace("\'", "") + "\"";
                            return Hijo1;

                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Suma de valores no permitidos");
                            Mala.SetContenido("Se intento sumar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO SUMA3");
                            return Nodo;
                    }
                case 4:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") +( IntChar(Hijo1.Contenido) + int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + (IntChar(Hijo1.Contenido) + double.Parse((Hijo2.Contenido)));
                            return Hijo1;
                        case 3:
                            Hijo1.Contenido = ("") + "\"" + (Hijo1.Contenido).Replace("\'", "") + (Hijo2.Contenido).Replace("\"", "") + "\"";
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") +( IntChar(Hijo1.Contenido) + IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + (IntChar(Hijo1.Contenido) + IntBool(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Suma de valores no permitidos");
                            Mala.SetContenido("Se intento sumar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO SUMA4");
                            return Nodo;
                    }
                case 5:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + (IntBool(Hijo1.Contenido) + int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + (IntBool(Hijo1.Contenido) + double.Parse((Hijo2.Contenido)));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") +( IntBool(Hijo1.Contenido) + IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Boolean Hi1 = Hijo1.Contenido.ToLower().Equals("verdadero") || (Hijo1.Contenido.ToLower().Equals("true"));
                            Boolean Hi2 = Hijo2.Contenido.ToLower().Equals("verdadero") || (Hijo2.Contenido.ToLower().Equals("true"));

                            String Salida = "false";
                            if (Hi1 || Hi2) { Salida = "true"; }

                            Hijo1.Contenido = ("") + Salida;
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Suma de valores no permitidos");
                            Mala.SetContenido("Se intento sumar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO SUMA5");
                            return Nodo;
                    }
                default:
                    Variable Mala1 = new Variable("E" + Errores.Count);
                    Mala1.SetNombreTipo("Suma de valores no permitidos");
                    Mala1.SetContenido("Se intento sumar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala1.GetNombre(), Mala1);
                    Hijo1.Contenido = ("") + "nullptr";
                    Console.WriteLine("ERROR TIPO DESCONOCIDO SUMAFINAL     " + Tipo1 + "   " + Tipo2);
                    return Nodo;
            }

        }
        private void CopiarArbol(NodoArbol Nodo, NodoArbol Tree)
        {
            if (Tree != null)
            {

                foreach (NodoArbol hijos in Tree.Hijos)
                {
                    //  Console.WriteLine(hijos);

                    NodoArbol Nodito = new NodoArbol(hijos.ToString());
                    Nodo.Hijos.Add(Nodito);

                   CopiarArbol(Nodito, hijos);

                }

            }
        }
        private NodoArbol Resta(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim();
            Tipo2 = Tipo(Hijo2);

            // Console.WriteLine(Tipo2+"     +++++++++++++++++++++++++++      " + Hijo2.Contenido);

            switch (Tipo1)
            {
                case 1:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + (int.Parse(Hijo1.Contenido) - int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) - double.Parse((Hijo2.Contenido)));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + (int.Parse(Hijo1.Contenido) - IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + (int.Parse(Hijo1.Contenido) - IntBool(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Resta de valores no permitidos");
                            Mala.SetContenido("Se intento Restar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO Resta1");
                            return Nodo;
                    }
                case 2:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) - double.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) - double.Parse((Hijo2.Contenido)));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) - IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + (double.Parse(Hijo1.Contenido) - IntBool(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Resta de valores no permitidos");
                            Mala.SetContenido("Se intento Restar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO Resta2");
                            return Nodo;
                    }
                case 4:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + (IntChar(Hijo1.Contenido) - int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + (IntChar(Hijo1.Contenido) - double.Parse((Hijo2.Contenido)));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + (IntChar(Hijo1.Contenido) - IntChar(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Resta de valores no permitidos");
                            Mala.SetContenido("Se intento Restar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO Resta4");
                            return Nodo;
                    }
                case 5:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + (IntBool(Hijo1.Contenido) - int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + (IntBool(Hijo1.Contenido) - double.Parse((Hijo2.Contenido)));
                            return Hijo1;

                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Resta de valores no permitidos");
                            Mala.SetContenido("Se intento Restar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO Resta5");
                            return Nodo;
                    }
                default:
                    Variable Mala1 = new Variable("E" + Errores.Count);
                    Mala1.SetNombreTipo("Resta de valores no permitidos");
                    Mala1.SetContenido("Se intento Restar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala1.GetNombre(), Mala1);
                    Hijo1.Contenido = ("") + "nullptr";
                    Console.WriteLine("ERROR TIPO DESCONOCIDO RESTAFINAL     " + Tipo1 + "   " + Tipo2);
                    return Nodo;
            }

        }
        private NodoArbol Multiplicacion(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim();
            Tipo2 = Tipo(Hijo2);

            // Console.WriteLine(Tipo2+"     +++++++++++++++++++++++++++      " + Hijo2.Contenido);

            switch (Tipo1)
            {
                case 1:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + int.Parse(Hijo1.Contenido) * int.Parse(Hijo2.Contenido);
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) * double.Parse((Hijo2.Contenido));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + int.Parse(Hijo1.Contenido) * IntChar(Hijo2.Contenido);
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + int.Parse(Hijo1.Contenido) * IntBool(Hijo2.Contenido);
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Multiplicacion de valores no permitidos");
                            Mala.SetContenido("Se intento multiplicar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO MULTI1");
                            return Nodo;
                    }
                case 2:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) * double.Parse(Hijo2.Contenido);
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) * double.Parse((Hijo2.Contenido));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) * IntChar(Hijo2.Contenido);
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) * IntBool(Hijo2.Contenido);
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Multiplicacion de valores no permitidos");
                            Mala.SetContenido("Se intento multiplicar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO MULTI2");
                            return Nodo;
                    }

                case 4:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido) * int.Parse(Hijo2.Contenido);
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido) * double.Parse((Hijo2.Contenido));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido) * IntChar(Hijo2.Contenido);
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido) * IntBool(Hijo2.Contenido);
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Multiplicacion de valores no permitidos");
                            Mala.SetContenido("Se intento multiplicar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO MULTI4");
                            return Nodo;
                    }
                case 5:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido) * int.Parse(Hijo2.Contenido);
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido) * double.Parse((Hijo2.Contenido));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido) * IntChar(Hijo2.Contenido);
                            return Hijo1;
                        case 5:
                            Boolean Hi1 = Hijo1.Contenido.ToLower().Equals("verdadero") || (Hijo1.Contenido.ToLower().Equals("true"));
                            Boolean Hi2 = Hijo2.Contenido.ToLower().Equals("verdadero") || (Hijo2.Contenido.ToLower().Equals("true"));

                            String Salida = "false";
                            if (Hi1 && Hi2) { Salida = "true"; }

                            Hijo1.Contenido = ("") + Salida;
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Multiplicacion de valores no permitidos");
                            Mala.SetContenido("Se intento multiplicar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO MULTI5");
                            return Nodo;
                    }
                default:
                    Variable Mala1 = new Variable("E" + Errores.Count);
                    Mala1.SetNombreTipo("Multiplicacion de valores no permitidos");
                    Mala1.SetContenido("Se intento multiplicar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala1.GetNombre(), Mala1);
                    Hijo1.Contenido = ("") + "nullptr";
                    Console.WriteLine("ERROR TIPO DESCONOCIDO MULTIFINAL     " + Tipo1 + "   " + Tipo2);
                    return Nodo;
            }

        }
        private NodoArbol Division(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim();
            Tipo2 = Tipo(Hijo2);

            // Console.WriteLine(Tipo2+"     +++++++++++++++++++++++++++      " + Hijo2.Contenido);

            switch (Tipo1)
            {
                case 1:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) / double.Parse(Hijo2.Contenido);
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) / double.Parse((Hijo2.Contenido));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) / IntChar(Hijo2.Contenido);
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) / IntBool(Hijo2.Contenido);
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("División de valores no permitidos");
                            Mala.SetContenido("Se intento dividir " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO DIVI1");
                            return Nodo;
                    }
                case 2:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) / double.Parse(Hijo2.Contenido);
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) / double.Parse((Hijo2.Contenido));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) / IntChar(Hijo2.Contenido);
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + double.Parse(Hijo1.Contenido) / IntBool(Hijo2.Contenido);
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("División de valores no permitidos");
                            Mala.SetContenido("Se intento dividir " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO DIVI2");
                            return Nodo;
                    }

                case 4:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido) / double.Parse(Hijo2.Contenido);
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido) / double.Parse((Hijo2.Contenido));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido) / IntChar(Hijo2.Contenido);
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + IntChar(Hijo1.Contenido) / IntBool(Hijo2.Contenido);
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("División de valores no permitidos");
                            Mala.SetContenido("Se intento dividir " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO DIVI4");
                            return Nodo;
                    }
                case 5:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido) / double.Parse(Hijo2.Contenido);
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido) / double.Parse((Hijo2.Contenido));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + IntBool(Hijo1.Contenido) / IntChar(Hijo2.Contenido);
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("División de valores no permitidos");
                            Mala.SetContenido("Se intento dividir " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO DIVI5");
                            return Nodo;
                    }
                default:
                    Variable Mala1 = new Variable("E" + Errores.Count);
                    Mala1.SetNombreTipo("Division de valores no permitidos");
                    Mala1.SetContenido("Se intento dividir " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala1.GetNombre(), Mala1);
                    Hijo1.Contenido = ("") + "nullptr";
                    Console.WriteLine("ERROR TIPO DESCONOCIDO DIVIFINAL     " + Tipo1 + "   " + Tipo2);
                    return Nodo;
            }

        }
        private NodoArbol Potencia(NodoArbol Nodo, Hashtable VariablesActuales)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0], VariablesActuales);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2], VariablesActuales);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim();
            Tipo2 = Tipo(Hijo2);

            // Console.WriteLine(Tipo2+"     +++++++++++++++++++++++++++      " + Hijo2.Contenido);

            switch (Tipo1)
            {
                case 1:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + Math.Pow(int.Parse(Hijo1.Contenido), int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + Math.Pow(double.Parse(Hijo1.Contenido), double.Parse(Hijo2.Contenido));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + Math.Pow(int.Parse(Hijo1.Contenido), IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + Math.Pow(int.Parse(Hijo1.Contenido), IntBool(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Potencia de valores no permitidos");
                            Mala.SetContenido("Se intento elevar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO Potencia1");
                            return Nodo;
                    }
                case 2:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + Math.Pow(double.Parse(Hijo1.Contenido), double.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + Math.Pow(double.Parse(Hijo1.Contenido), double.Parse((Hijo2.Contenido)));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + Math.Pow(double.Parse(Hijo1.Contenido), IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + Math.Pow(double.Parse(Hijo1.Contenido), IntBool(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Potencia de valores no permitidos");
                            Mala.SetContenido("Se intento elevar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO Potencia2");
                            return Nodo;
                    }

                case 4:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + Math.Pow(IntChar(Hijo1.Contenido), int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + Math.Pow(IntChar(Hijo1.Contenido), double.Parse((Hijo2.Contenido)));
                            return Hijo1;

                        case 4:
                            Hijo1.Contenido = ("") + Math.Pow(IntChar(Hijo1.Contenido), IntChar(Hijo2.Contenido));
                            return Hijo1;
                        case 5:
                            Hijo1.Contenido = ("") + Math.Pow(IntChar(Hijo1.Contenido), IntBool(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Potencia de valores no permitidos");
                            Mala.SetContenido("Se intento elevar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO Potencia4");
                            return Nodo;
                    }
                case 5:
                    switch (Tipo2)
                    {
                        case 1:
                            Hijo1.Contenido = ("") + Math.Pow(IntBool(Hijo1.Contenido), int.Parse(Hijo2.Contenido));
                            return Hijo1;
                        case 2:
                            Hijo1.Contenido = ("") + Math.Pow(IntBool(Hijo1.Contenido), double.Parse((Hijo2.Contenido)));
                            return Hijo1;
                        case 4:
                            Hijo1.Contenido = ("") + Math.Pow(IntBool(Hijo1.Contenido), IntChar(Hijo2.Contenido));
                            return Hijo1;
                        default:
                            Variable Mala = new Variable("E" + Errores.Count);
                            Mala.SetNombreTipo("Potencia de valores no permitidos");
                            Mala.SetContenido("Se intento elevar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                            Errores.Add(Mala.GetNombre(), Mala);
                            Console.WriteLine("ERROR TIPO DESCONOCIDO Potencia5");
                            return Nodo;
                    }
                default:
                    Variable Mala1 = new Variable("E" + Errores.Count);
                    Mala1.SetNombreTipo("Potencia de valores no permitidos");
                    Mala1.SetContenido("Se intento elevar " + TipoStr(Tipo1) + "  Con " + TipoStr(Tipo2) + " Valores con el error " + Hijo1.Contenido + "    " + Hijo2.Contenido);
                    Errores.Add(Mala1.GetNombre(), Mala1);
                    Hijo1.Contenido = ("") + "nullptr";
                    Console.WriteLine("ERROR TIPO DESCONOCIDO PotenciaFINAL     " + Tipo1 + "   " + Tipo2);
                    return Nodo;
            }

        }
        private int IntChar(String Ingreso)
        {
            Ingreso = Ingreso.Replace("'", "");
            byte Salida = Encoding.ASCII.GetBytes(Ingreso)[0];
            return int.Parse(Salida.ToString());
        }
        private int IntBool(String Ingreso)
        {
            if (Ingreso.Equals("true") || Ingreso.Equals("verdadero"))
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        private void AnalizarTodo(NodoArbol Nodo,Hashtable VariablesActuales)
        {
            foreach (NodoArbol N in Nodo.Hijos)
            {
                Analizar(N, VariablesActuales);
            }
        }


        private int Tipo(NodoArbol Nodo)
        {


            if ((Nodo.Contenido.ToLower().Equals("nullptr")))
            {
                return -1;
            }
            if (Regex.IsMatch(Nodo.Contenido.ToLower(), "true|false|verdadero|falso"))
            {
                return 5;
            }
            if (Regex.IsMatch(Nodo.Contenido, "\"[^\"]*\""))
            {
                return 3;
            }
            if (Regex.IsMatch(Nodo.Contenido, @"\'.?\'"))
            {
                return 4;
            }
            if (Regex.IsMatch(Nodo.Contenido.ToLower(), "[a-z]([a-z]|_|([0-9]))*"))
            {
                return 6;
            }
            if (Regex.IsMatch(Nodo.Contenido, @"[0-9]*\.[0-9]+"))
            {
                return 2;
            }

            if (Regex.IsMatch(Nodo.Contenido, "[0-9]+"))
            {
                return 1;
            }






            Console.WriteLine("NO ES NINGUN REGEX EN TIPO   " + Nodo.Contenido.ToLower());
            return -1;
        }
        private String TipoStr(int Ingreso)
        {
            switch (Ingreso)
            {
                case 1: return "Entero";
                case 2: return "Decimal";
                case 3: return "Cadena";
                case 4: return "Char";
                case 5: return "Booleano";
                default: return "NULO";
            }

        }
    }
}
