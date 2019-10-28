using Irony.Parsing;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Proyecto2Compiladores1.Contenido
{
    class Metodo
    {
        private String nombre;
        public int CantidadMaxima=0;
        public int CantidadLlena = 0;
        private NodoArbol raiz=null;        
        public String ValorRetorno=null;
        public String Tipo = "void";
        private Hashtable varMetodo = new Hashtable();
        public Metodo(String Nombre,String Tipo,NodoArbol raiz)
        {
            this.Tipo = Tipo;
            this.raiz = raiz;
            this.SetNombre(Nombre);
        }
        public Metodo(String Nombre)
        {
            this.SetNombre(Nombre);
        }
        public string GetNombre()
        {
            return nombre;
        }

        public void SetNombre(string value)
        {
            nombre = value;
        }

        public NodoArbol GetRaiz()
        {
            return raiz;
        }

        public void SetRaiz(NodoArbol value)
        {
            raiz = value;
        }

       

        private Hashtable variables = new Hashtable();

        public Hashtable GetVarMetodo()
        {
            return varMetodo;
        }

        public void SetVarMetodo(Hashtable value)
        {
            varMetodo = value;
        }

        public Hashtable GetVariables()
        {
            return variables;
        }

        public void SetVariables(Hashtable value)
        {
            variables = value;
        }
    }
}
