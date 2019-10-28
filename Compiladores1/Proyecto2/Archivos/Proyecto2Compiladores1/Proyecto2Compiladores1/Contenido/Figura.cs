using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Proyecto2Compiladores1.Contenido
{
    class Figura
    {
        public int Tipo = -1;
        public double  Punto1X = 0;
        public double Punto1Y = 0;
        public double Punto2X = 0;
        public double Punto2Y = 0;
        public double Punto3X = 0;
        public double Punto3Y = 0;
        public double Radio = 0;
        public Color Colorcito = Color.Black;
        public double DAMNSHEtHICk =0;
        public Boolean Solido = false;

        public Figura(String Nada,Color Colorcito,double Radio,Boolean Solido, double X, double Y) {

           
                 this.Tipo = 1;
                 
           


            this.Colorcito = Colorcito;
            this.Radio = Radio;
            this.Solido = Solido;
            this.Punto1X = X;
            this.Punto1Y = Y;
        }
        public Figura(String Nada, Color Colorcito,  Boolean Solido, double X, double Y, double X2, double Y2)
        {


            this.Tipo = 3;




            this.Colorcito = Colorcito;
         
            this.Solido = Solido;
            this.Punto1X = X;
            this.Punto1Y = Y;
            this.Punto2X = X2;
            this.Punto2Y = Y2;
        }
        public Figura(String Nada, Color Colorcito, double X, double Y, double X2, double Y2,double Grosor)
        {


            this.Tipo = 4;

            this.Punto2X = X2;
            this.Punto2Y = Y2;
            this.DAMNSHEtHICk = Grosor;


            this.Colorcito = Colorcito;
           
            this.Punto1X = X;
            this.Punto1Y = Y;
        }
        public Figura(String Nada,Color Colorcito,  Boolean Solido, double X1, double Y1, double X2, double Y2, double X3, double Y3)
        {
            this.Tipo = 2;
            this.Colorcito = Colorcito;
          
            this.Solido = Solido;
            this.Punto1X = X1;
            this.Punto1Y = Y1;
            this.Punto2X = X2;
            this.Punto2Y = Y2;
            this.Punto3X = X3;
            this.Punto3Y = Y3;
        }

       

    }
}
