using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Proyecto2Compiladores1.Contenido
{
    class Variable
    {

        public Variable(String Nombre) {
            this.SetNombre(Nombre);
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
        public Hashtable VarArreglo= new Hashtable();
        private String nombreTipo;
        private int tipo = -1;
        private int Visibilidad=0;
        public int GetVisibilidad()
        {
            return Visibilidad;
        }

        public void SetVisibilidad(int value)
        {
            Visibilidad= value;
        }

        public string GetNombreTipo()
        {
            return nombreTipo;
        }

        public void SetNombreTipo(string value)
        {
            nombreTipo = value;
        }

        public int GetTipo()
        {
            return tipo;
        }

        public void SetTipo(int value)
        {
            tipo = value;
        }
        public String DarIndex(String Ingreso)
        {
             
            String[] Spl = Ingreso.Split('|');
            switch (Spl.Length)
            {
                case 3:

                    return Spl[0].Trim() +"p"+ Spl[1].Trim() + "s" + Spl[2].Trim() + "t";
                case 2:
                    return Spl[0].Trim() + "p" + Spl[1].Trim()+"s"+"0t";
                case 1:
                    return Spl[0].Trim() + "p0s0t" ;
                default:
                    return "nullptrar";
            }

        }
        public void DarTamaño(String Ingreso) {
            String[] Spl = Ingreso.Split('|');
            switch (Spl.Length) {
                case 3:
                    A = int.Parse(Spl[0]);
                    B = int.Parse(Spl[1]);
                    C = int.Parse(Spl[2]);
                    break;
                case 2:
                    A = int.Parse(Spl[0]);
                    B = int.Parse(Spl[1]);
                 
                    break;
                case 1:
                    A = int.Parse(Spl[0]);
                    
                    break;
            }

        }
        public int A = -100;
        public int B = -100;
        public int C = -100;
    }
   
}
