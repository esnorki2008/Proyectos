using Irony.Parsing;
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

namespace Proyecto2Compiladores1.Analizadores
{
    class Semantico
    {
        private Clase ClaseActual;
        private Metodo MetodoActual;
        public Hashtable Temporales { get; set; } = new Hashtable();
        public Hashtable Errores { get; set; } = new Hashtable();
        public Hashtable Clases { get; set; } = new Hashtable();
        private Boolean RetornoID = false;
      //  private Variable VariableActual;
        private int Visibilidad = 0;
        private String TipoString = "";
        private int Mod = 0;
        private Boolean Tempo = false;
        public Semantico(NodoArbol Ingreso)
        {
            Analizar(Ingreso,null);
        }



        private NodoArbol Analizar(NodoArbol Nodo,Hashtable VariablesActuales)
        {
            // if (Nodo.Contenido != null) { Console.WriteLine(Nodo.Contenido); }

            NodoArbol Hijo1 = null;
            NodoArbol Hijo2 = null;
            NodoArbol Hijo3 = null;
            Variable VariableNueva = new Variable("NOEXISTE");
           
            switch (Nodo.Contenido.ToLower())
            {

                case "clas":
                    ClaseActual = new Clase();
                    AnalizarTodo(Nodo);
                    break;
                case "clase":
                    break;
                case "clasp":
                    AnalizarTodo(Nodo);
                    break;
                case "contecla":
                    if (Nodo.Hijos.Count() == 0)
                    {
                        Clases.Add(ClaseActual.GetNombre(), ClaseActual);
                    }
                    else
                    {
                        AnalizarTodo(Nodo);
                    }
                    break;
                case "visi":
                    AnalizarTodo(Nodo);
                    break;
                case "decla":
                    //  AnalizarTodo(Nodo);
                    Hijo1 = Nodo.Hijos[0];
                    TipoString = Hijo1.Contenido;
                    Hijo2 = Nodo.Hijos[1];
                    VariableNueva = new Variable(Hijo2.Contenido);
                    VariableNueva.SetNombreTipo(TipoString);
                    VariableNueva.SetVisibilidad(Visibilidad);
                    Hijo3 = Analizar(Nodo.Hijos[2]);
                    VariableNueva.SetContenido(Hijo3.Contenido);

                    Variable Variab1 = (Variable)ClaseActual.GetVariables()[VariableNueva.GetNombre().ToLower().Split('(')[0].Trim()];
                    if (Variab1 == null)
                    {
                        ClaseActual.GetVariables().Add(VariableNueva.GetNombre().ToLower().Split('(')[0].Trim(), VariableNueva);
                    }
                    else
                    {
                        Variab1=VariableNueva;
                        Console.WriteLine("ARREGLAR ASIGNACIONES PARA CILOS E IFS");
                    }

                    break;
                case "mai":
                    MetodoActual = new Metodo("main");
                    MetodoActual.SetRaiz(Nodo.Hijos[1]);
                    ClaseActual.GetMetodos().Add(MetodoActual.GetNombre(), MetodoActual);
                    Analizar(MetodoActual.GetRaiz());
                    MetodoActual = null;
                    break;
                case "conteme":
                    for (int i = 0; i < Nodo.Hijos.Count; i++)
                    {
                        Analizar(Nodo.Hijos.ElementAt(i));

                    }
                    break;
                case "mas":
                    if (Nodo.Hijos.Count == 0)
                    {
                        Nodo.Contenido = ("") + "0";
                        return Nodo;
                    }
                    else
                    {
                        Hijo1 = Nodo.Hijos[0];


                        if (Hijo1.Contenido.Split('(')[0].Trim().Equals("="))
                        {
                            return Analizar(Nodo.Hijos[1]);
                        }
                        else
                        {

                            VariableNueva = new Variable(Hijo1.Contenido);
                            VariableNueva.SetNombreTipo(TipoString);
                            VariableNueva.SetVisibilidad(Visibilidad);
                            Hijo2 = Analizar(Nodo.Hijos[1]);
                            VariableNueva.SetContenido(Hijo2.Contenido);

                            ClaseActual.GetVariables().Add(VariableNueva.GetNombre().ToLower(), VariableNueva);
                            Variable SO = (Variable)ClaseActual.GetVariables()[VariableNueva.GetNombre()];
                            //   Console.WriteLine("HAS      "+(SO.GetNombre() ));
                            return Hijo2;
                        }
                    }
                case "valor":
                    return AnalizarValor(Nodo);
                case "aumen":
                    return Aumento(Nodo);
                case "privado":
                    Visibilidad = 1;
                    break;
                case "publico":
                    Visibilidad = 0;
                    break;
                case "para":

                    CicloPara(Nodo);
                    break;
                case "parap":

                    Analizar(Nodo.Hijos[0]);
                    break;

                case "repetir":

                    CicloRepetir(Nodo);
                    break;
                case "cuando":

                    CicloCuando(Nodo);
                    break;
                case "hacerm":

                    CicloHacerm(Nodo);
                    break;
                case "si":
                    Si(Nodo);
                    break;
                case "sino":
                    Sino(Nodo);
                    break;
                case "asigna":





                    Variable Variab = (Variable)ClaseActual.GetVariables()[Nodo.Hijos[0].Contenido.ToLower().Split('(')[0].Trim()];
                    if (Variab != null)
                    {

                        NodoArbol Nodi = Analizar(Nodo.Hijos[2]);
                        Variab.SetContenido(Nodi.Contenido);
                        ClaseActual.GetVariables()[Nodo.Hijos[0].Contenido.ToLower().Split('(')[0].Trim()] = Variab;

                    }
                    else
                    {
                        Variable Mala = new Variable("E" + Errores.Count);
                        Mala.SetNombreTipo("Variable no encontrada");
                        Mala.SetContenido("Se ha buscado una variable que no existe en este ambito");
                        Errores.Add(Mala.GetNombre(), Mala);
                    }

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
        private NodoArbol AnalizarValor(NodoArbol Nodo)
        {

            switch (Nodo.Hijos.Count())
            {

                case 1:
                    NodoArbol N1 = ValorSimple(Nodo);
                    return N1;
                case 2:

                    return ValorDoble(Nodo);
                case 3:

                    return ValorTriple(Nodo);
                default:

                    Console.WriteLine(" ERROR EN VALOR   ");
                    return Nodo;
            }



        }

        private void Si(NodoArbol Nodo)
        {
            String Comprobacion = Analizar(Nodo.Hijos[1]).Contenido.ToLower().Trim();
            Console.WriteLine(Comprobacion+"    En SI");
            if (Comprobacion.Equals("true") || Comprobacion.Equals("verdadero"))
            {
                Analizar(Nodo.Hijos[2]);
            }
            else
            {
                Console.WriteLine("SINO");
                Analizar(Nodo.Hijos[3]);
            }
        }
        private void Sino(NodoArbol Nodo)
        {
           
                Analizar(Nodo.Hijos[1]);           
        }
        private void CicloHacerm(NodoArbol Nodo)
        {        
            String Comprobacion = Analizar(Nodo.Hijos[3]).Contenido.ToLower().Trim();
            while (Comprobacion.Equals("true") || Comprobacion.Equals("verdadero"))
            {
                Analizar(Nodo.Hijos[1]);
                Comprobacion = Analizar(Nodo.Hijos[3]).Contenido.ToLower();          
            } 
        }
        private void CicloCuando(NodoArbol Nodo)
        {  
            String Comprobacion = Analizar(Nodo.Hijos[1]).Contenido.ToLower().Trim();
            while (Comprobacion.Equals("true")|| Comprobacion.Equals("verdadero"))
            {
                Analizar(Nodo.Hijos[2]);
                Comprobacion = Analizar(Nodo.Hijos[1]).Contenido.ToLower();
            }
        }
        private void CicloRepetir(NodoArbol Nodo)
        {
            int RepetirVeces=int.Parse(Analizar(Nodo.Hijos[1]).Contenido);
          //  int Seguridad=0;       
            while (RepetirVeces>0)
            {              
                Analizar(Nodo.Hijos[2]);
                RepetirVeces = RepetirVeces - 1;
               //Seguridad++;
               //Console.WriteLine(Seguridad);
               //if (Seguridad > 1000) { break; }
            }

          //  Console.WriteLine("REPETIR  " + Nodo.Contenido + "     " + Nodo.Hijos.Count);
        }
        private void CicloPara(NodoArbol Nodo)
        {
            Analizar(Nodo.Hijos[1]);
            NodoArbol Nod2 = Analizar(Nodo.Hijos[2]);    
            while (Nod2.Contenido.ToLower().Trim().Equals("true") || (Nod2).Contenido.ToLower().Trim().Equals("verdadero"))
            {               
                Analizar(Nodo.Hijos[4]);
                Analizar(Nodo.Hijos[3]);
                Nod2 = Analizar(Nodo.Hijos[2]);
              
            }

        }
        private NodoArbol Tope(NodoArbol Nodo)
        {
            if (Nodo.Hijos.Count != 0)
            {

                return Tope(Nodo.Hijos[0]);
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
        private NodoArbol Aumento(NodoArbol Nodo)
        {

            NodoArbol Hijo1 = new NodoArbol( Tope(Nodo.Hijos[0]).Contenido);
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

            Variable Variab = (Variable)ClaseActual.GetVariables()[Hijo1.Contenido.ToLower().Split('(')[0].Trim()];

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
        private NodoArbol ValorDoble(NodoArbol Nodo)
        {


            NodoArbol Hijo1 = (Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[1]);
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
                    Hijo1.Contenido = ("") + "-" + Analizar(Hijo2).Contenido.Split('(')[0].Trim().ToLower();
                    return Hijo1;

                default:


                    Console.WriteLine("Error Doble  " + Hijo1.Contenido);
                    return Nodo;
            }
        }
        private NodoArbol ValorSimple(NodoArbol Nodo)
        {

            NodoArbol Salida = new NodoArbol(Nodo.Contenido);
            Salida.Contenido = ("") + Nodo.Hijos[0].Contenido.Split('(')[0].Trim();
            Salida = Analizar(Salida);


            switch (Tipo(Nodo.Hijos[0]))
            {
                case 6:
                    Variable Variab = (Variable)ClaseActual.GetVariables()[Nodo.Hijos[0].Contenido.ToLower().Split('(')[0].Trim()];
                    if (Variab != null)
                    {

                        Salida.Contenido = ("") + Variab.GetContenido().Split('(')[0].Trim();
                      //  Console.WriteLine(Nodo.Hijos[0].Contenido + " VALOR DE BUSQUEDA");

                        return Salida;
                    }
                    else
                    {
                        Console.WriteLine("ERROR VARIABLE NO ENCONTRADA    EN SIMPLE " + Nodo.Hijos[0].Contenido);
                        Variable Mala = new Variable("E" + Errores.Count);
                        Mala.SetNombreTipo("Variable no encontrada");
                        Mala.SetContenido("Se ha buscado una variable que no existe en este ambito  " + Nodo.Hijos[0].Contenido);
                        Errores.Add(Mala.GetNombre(), Mala);
                        Nodo.Hijos[0].Contenido = ("") + "nullptr";
                        return Nodo.Hijos[0];
                    }
                case 1:
                    return Nodo.Hijos[0];
                case 2:
                    return Nodo.Hijos[0];
                case 3:
                    return Nodo.Hijos[0];
                case 4:
                    return Nodo.Hijos[0];
                case 5:
                    return Nodo.Hijos[0];
                default:
                    Console.WriteLine("REGEX REGULAR NO ENCONTRADA      " + Tipo(Nodo.Hijos[0]) + "   " + Nodo.Hijos[0].Contenido);
                    return Salida;

            }


        }
        private NodoArbol ValorTriple(NodoArbol Nodo)
        {
            switch (Nodo.Hijos[1].Contenido.Split('(')[0].Trim())
            {
                case "+":

                    return Suma(Nodo);
                case "-":
                    return Resta(Nodo);
                case "*":
                    return Multiplicacion(Nodo);
                case "/":
                    return Division(Nodo);
                case "^":
                    return Potencia(Nodo);
                case "==":
                    return DobleIgual(Nodo);
                case "!=":
                    return Diferente(Nodo);
                case "<":
                    return Menor(Nodo);
                case ">":
                    return Mayor(Nodo);
                case "<=":
                    return MenorIgual(Nodo);
                case ">=":
                    return MayorIgual(Nodo);
                case "&&":
                    return AndDoble(Nodo);
                case "||":
                    return OrDoble(Nodo);
                default:
                    Console.WriteLine("ERROR EN VALOR TRIPLE    " + Nodo.Hijos[1].Contenido.Split('(')[0].Trim());
                    return Nodo;

            }
        }
        private NodoArbol OrDoble(NodoArbol Nodo)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();


            if (Hijo1.Contenido.ToLower().Equals("true") || Hijo2.Contenido.ToLower().Equals("true")) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        private NodoArbol AndDoble(NodoArbol Nodo)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();


            if (Hijo1.Contenido.ToLower().Equals("true") && Hijo2.Contenido.ToLower().Equals("true")) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        private NodoArbol MayorIgual(NodoArbol Nodo)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo1 = Tipo(Hijo1);
            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
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
        private NodoArbol MenorIgual(NodoArbol Nodo)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo1 = Tipo(Hijo1);
            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
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
        private NodoArbol Mayor(NodoArbol Nodo)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo1 = Tipo(Hijo1);
            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
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



        private NodoArbol Menor(NodoArbol Nodo)
        {
            NodoArbol hola = new NodoArbol(Nodo.Hijos[0].Contenido);



            NodoArbol Hijo1 = new NodoArbol(Analizar(Nodo.Hijos[0]).Contenido);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();
            int Tipo1 = Tipo(Hijo1);
            NodoArbol Hijo2 = new NodoArbol(Analizar(Nodo.Hijos[2]).Contenido);
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
        private NodoArbol DobleIgual(NodoArbol Nodo)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();


            if (Hijo1.Contenido.ToLower().Equals(Hijo2.Contenido.ToLower())) { Hijo1.Contenido = ("") + "true"; } else { Hijo1.Contenido = ("") + "false"; }

            return Hijo1;
        }
        private NodoArbol Diferente(NodoArbol Nodo)
        {
            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim().ToLower();

            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
            Hijo2.Contenido = ("") + Hijo2.Contenido.Split('(')[0].Trim().ToLower();

            if (Hijo1.Contenido.ToLower().Equals(Hijo2.Contenido.ToLower())) { Hijo1.Contenido = ("") + "false"; } else { Hijo1.Contenido = ("") + "true"; }

            return Hijo1;
        }
        private NodoArbol Suma(NodoArbol Nodo)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
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

        private NodoArbol Resta(NodoArbol Nodo)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
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
        private NodoArbol Multiplicacion(NodoArbol Nodo)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
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
        private NodoArbol Division(NodoArbol Nodo)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
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
        private NodoArbol Potencia(NodoArbol Nodo)
        {
            int Tipo1 = -1;
            int Tipo2 = -1;


            NodoArbol Hijo1 = Analizar(Nodo.Hijos[0]);
            Hijo1.Contenido = ("") + Hijo1.Contenido.Split('(')[0].Trim();
            Tipo1 = Tipo(Hijo1);

            //  Console.WriteLine(Tipo1+"     +++++++++++++++++++++++++++      " + Hijo1.Contenido);


            NodoArbol Hijo2 = Analizar(Nodo.Hijos[2]);
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
        private void AnalizarTodo(NodoArbol Nodo)
        {
            foreach (NodoArbol N in Nodo.Hijos)
            {
                Analizar(N);
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
