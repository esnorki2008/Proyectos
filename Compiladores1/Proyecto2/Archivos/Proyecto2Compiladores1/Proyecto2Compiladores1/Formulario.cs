using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Irony.Parsing;
using Irony.Ast;
using Irony.Interpreter.Ast;
using System.Collections;
using Proyecto2Compiladores1.Contenido;
using System.Drawing.Drawing2D;

namespace Proyecto2Compiladores1
{
   

    public partial class Formulario : Form
    {
        public Formulario()
        {
           

            InitializeComponent();

             
            
        }

        Analizador grammatica = new Analizador();

        private void button1_Click(object sender, EventArgs e)
        {
            /*
             Hashtable ht = new Hashtable();

              ht.Add("001P", "Zara Ali");
              ht.Add("002", "Abida Rehman");
              ht.Add("003", "Joe Holzner");

              if (ht["001P"] == null)
              {
                  Console.WriteLine("VACIO");
              }
              else
              {
                  Console.WriteLine("LLEno");
              }
              */



              Console.WriteLine(grammatica.Obtener_Resultado(this.richTextBox1.Text));

           // Console.WriteLine("FIN");
            if (grammatica.Seman != null) {
               
                ICollection key = grammatica.Seman.Clases.Keys;
               

                foreach (string k in key)
                {
                    Console.WriteLine("----------------CLASES----------------");
                    Clase Cla= (Clase)grammatica.Seman.Clases[k];

                    Console.WriteLine(k + ": " + Cla.GetNombre());
                    ICollection key1 = Cla.GetVariables().Keys;

                    this.dataGridView1.Rows.Add(Cla.GetNombre(), "Clase", Cla.GetNombre(), "No Aplica", "No Aplica");

                    Console.WriteLine("----------------VARIABLESCLASES----------------");
                    foreach (string k1 in key1)
                    {
                     
                        Variable Vari = (Variable)Cla.GetVariables()[k1];
                        Console.WriteLine("|"+k1+"|" + ": " +Vari.GetContenido() );                        
                    }
                
                    ICollection key2 = Cla.GetMetodos().Keys;
                    foreach (string k2 in key2)
                    {
                        Console.WriteLine("----------------METODOS----------------");
                        Metodo Meto = (Metodo)Cla.GetMetodos()[k2];
                        Console.WriteLine("|" + k2 + "|" + ": " + Meto.GetNombre());

                        Console.WriteLine("----------------VARIABLESMETODOS----------------");
                        ICollection key3 = Meto.GetVariables().Keys;
                        foreach (string k3 in key3)
                        {

                            Variable Vari = (Variable)Meto.GetVariables()[k3];
                            Console.WriteLine("|" + k3 + "|" + ": " + Vari.GetContenido());
                        }
                    }

                }

               
            }



        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.pictureBox1.Refresh();
            System.Drawing.Graphics graphicsObj;
             
            graphicsObj = this.pictureBox1.CreateGraphics();

            Pen myPen = new Pen(System.Drawing.Color.Red, 5);
            
          
            for (int i=0;i<grammatica.Seman.ListaFiguras.Count;i++) {
                Figura Fig = grammatica.Seman.ListaFiguras[i];
                switch (Fig.Tipo) {
                    case 1:
                        myPen = new Pen(Fig.Colorcito, 5);
                        SolidBrush brush = new SolidBrush(Fig.Colorcito);
                        GraphicsPath path = new GraphicsPath();


                        Console.WriteLine(Fig.Punto1X + " " + Fig.Punto1Y + " " + Fig.Punto2X + " " + Fig.Punto2Y + " " + Fig.Punto3X + " " + Fig.Punto3Y + " ");

                        path.AddEllipse(float.Parse(Fig.Punto1X + ""), float.Parse(Fig.Punto1Y+ ""), float.Parse(Fig.Radio+""), float.Parse(Fig.Radio+""));

                        if (Fig.Solido)
                        {
                            graphicsObj.FillPath(brush, path);
                        }
                        graphicsObj.DrawPath(myPen, path);
                        break;
                    case 2:
                        myPen = new Pen(Fig.Colorcito, 5);
                        brush = new SolidBrush(Fig.Colorcito);
                        path = new GraphicsPath();


                        Console.WriteLine(Fig.Punto1X+" "+ Fig.Punto1Y + " "+ Fig.Punto2X + " "+ Fig.Punto2Y + " "+ Fig.Punto3X + " "+ Fig.Punto3Y + " ");
                        Point P1 = new Point( int.Parse(Fig.Punto1X + ""), int.Parse(Fig.Punto1Y + ""));
                        Point P2 = new Point(int.Parse(Fig.Punto2X + ""), int.Parse(Fig.Punto2Y + ""));
                        Point P3 = new Point(int.Parse(Fig.Punto3X + ""), int.Parse(Fig.Punto3Y + ""));
                        path.AddLine(P1,P2);
                        path.AddLine(P2, P3);
                        path.AddLine(P1, P3);

                        if (Fig.Solido) { 
                        graphicsObj.FillPath(brush,path);
                        }
                        graphicsObj.DrawPath(myPen,path);

                        break;
                    case 3:
                        myPen = new Pen(Fig.Colorcito, 5);
                        brush = new SolidBrush(Fig.Colorcito);
                        path = new GraphicsPath();


                        Console.WriteLine(Fig.Punto1X + " " + Fig.Punto1Y + " " + Fig.Punto2X + " " + Fig.Punto2Y + " " + Fig.Punto3X + " " + Fig.Punto3Y + " ");

                        Rectangle pathRect = new Rectangle(int.Parse(Fig.Punto1X + ""), int.Parse(Fig.Punto1Y + ""), int.Parse(Fig.Punto2X + ""), int.Parse(Fig.Punto2Y + "")); ;
                        path.AddRectangle(pathRect);


                        if (Fig.Solido)
                        {
                            graphicsObj.FillPath(brush, path);
                        }
                        graphicsObj.DrawPath(myPen, path);
                        break;
                    case 4:
                        myPen = new Pen(Fig.Colorcito, float.Parse(Fig.DAMNSHEtHICk + ""));
                        graphicsObj.DrawLine(myPen, float.Parse(Fig.Punto1X + ""), float.Parse(Fig.Punto1Y + ""), float.Parse(Fig.Punto2X + ""), float.Parse(Fig.Punto2Y + ""));

                        break;
                }
               
            }
            
        }
    }

  

   

}
