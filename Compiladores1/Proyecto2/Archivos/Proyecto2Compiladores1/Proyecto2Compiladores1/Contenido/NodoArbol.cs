using Irony.Parsing;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Proyecto2Compiladores1.Contenido
{
    class NodoArbol
    {
        public String Contenido;
        public List<NodoArbol> Hijos= new List<NodoArbol>() ;
        public NodoArbol(String Contenido)
        {
            this.Contenido = Contenido.Split('(')[0];
        }
        public String ToStrings()
        {
            return Contenido;
        }
    }

}
