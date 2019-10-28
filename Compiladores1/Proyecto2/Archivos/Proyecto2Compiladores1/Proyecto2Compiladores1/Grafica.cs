using Irony.Parsing;
using Proyecto2Compiladores1.Contenido;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Proyecto2Compiladores1
{
    class Grafica
    {
        private String grafo = "";
        private int contador = 0;
        public void Graficar(NodoArbol Raiz)
        {

           

            if (Raiz != null)
            {


                grafo = "digraph G{";
                grafo += "node[shape=\"box\"];";
                grafo += "NodoAST0[label=\" " + Raiz.Contenido.Replace("\"","")+ " \"];\n";
                this.contador = 1;
                recorrerAST("NodoAST0", Raiz);
                grafo += "}";




                GenerarImagen(grafo);


                // GenerarImagen(grafo);
            }

        }

        private void recorrerAST(String padre, NodoArbol hijo)
        {
            if (hijo != null)
            {
                int x = 0;
                foreach (NodoArbol hijos in hijo.Hijos)
                {


                    NodoArbol NodoAST = hijos;



                    String nombreHijo = "NodoAST" +contador;//  this->contador;
                    grafo += nombreHijo + "[label=\" " + NodoAST.Contenido.Replace("\"", "") + " \"];\n";
                    grafo += padre + "->" + nombreHijo + ";\n";
                    contador++;


                    recorrerAST(nombreHijo, NodoAST);

                }

            }
        }
        private void GenerarImagen(String Entrada)
        {

            using (System.IO.StreamWriter file =
               new System.IO.StreamWriter(@"W:\A0\grafo.txt"))
            {


                file.WriteLine(Entrada);


            }

           

            Process cmd = new Process();
            cmd.StartInfo.FileName = "cmd.exe";
            cmd.StartInfo.RedirectStandardInput = true;
            cmd.StartInfo.RedirectStandardOutput = true;
            cmd.StartInfo.CreateNoWindow = true;
            cmd.StartInfo.UseShellExecute = false;
            cmd.Start();

            cmd.StandardInput.WriteLine("dot -Tjpg " + " W:\\A0\\grafo.txt -o " + " W:\\A0\\grafo.jpg ");
            cmd.StandardInput.Flush();
            cmd.StandardInput.Close();
            cmd.WaitForExit();
           // Console.WriteLine(cmd.StandardOutput.ReadToEnd());

            /* 
              System.Diagnostics.Process process = new System.Diagnostics.Process();
              System.Diagnostics.ProcessStartInfo startInfo = new System.Diagnostics.ProcessStartInfo();
              startInfo.WindowStyle = System.Diagnostics.ProcessWindowStyle.Hidden;
              startInfo.FileName = "cmd.exe";
              startInfo.Arguments = "/C   dot -Tjpg " + "cd W:\\A0\\grafo.txt -o " + "cd W:\\A0\\grafo.jpg ";
              process.StartInfo = startInfo;
              process.Start();*/










        }


    }
}
