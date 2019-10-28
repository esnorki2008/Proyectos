using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Proyecto2Compiladores1.Contenido
{  
    class Clase
    {
        public Clase() {
        }
        private string nombre = "";

        public string GetNombre()
        {
            return nombre;
        }

        public void SetNombre(string value)
        {
            nombre = value;
        }

        private string contenido = "";

        public string GetContenido()
        {
            return contenido;
        }

        public void SetContenido(string value)
        {
            contenido = value;
        }

        private Hashtable variables = new Hashtable();

        public Hashtable GetVariables()
        {
            return variables;
        }

        public void SetVariables(Hashtable value)
        {
            variables = value;
        }

        private Hashtable metodos = new Hashtable();

        public Hashtable GetMetodos()
        {
            return metodos;
        }

        public void SetMetodos(Hashtable value)
        {
            metodos = value;
        }
    }
}
