using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Irony.Ast;
using Irony.Interpreter;
using Irony.Parsing;
using Proyecto2Compiladores1.Analizadores;
using Proyecto2Compiladores1.Contenido;

namespace Proyecto2Compiladores1
{
    class Analizador : Grammar
    {
        //METODO QUE RECORRERA EL ARBOL AST          
        public Double Valor(Arbol arbol)
        {
            Double entero1;
            Double entero2;
            switch ((String)arbol.Name)
            {
                case "+":
                    entero1 = Valor(arbol.hijos[0]);
                    entero2 = Valor(arbol.hijos[1]);
                    return entero1 + entero2;
                case "-":
                    entero1 = Valor(arbol.hijos[0]);
                    entero2 = Valor(arbol.hijos[1]);
                    return entero1 - entero2;
                case "*":
                    entero1 = Valor(arbol.hijos[0]);
                    entero2 = Valor(arbol.hijos[1]);
                    return entero1 * entero2;
                case "/":
                    entero1 = Valor(arbol.hijos[0]);
                    entero2 = Valor(arbol.hijos[1]);
                    return entero1 / entero2;
                case "raap":
                    return -20;
                default:
                    return 333;
            }
        }

        public Analizador():base(caseSensitive:false)
        {
            #region Regex
            
            var singleLineComment = new CommentTerminal("SingleLineComment", "//", "\r", "\n", "\u2085", "\u2028", "\u2029");
            var delimitedComment = new CommentTerminal("DelimitedComment", "/*", "*/");

            var num = new RegexBasedTerminal("num", "[0-9]+");
            
            var id = new RegexBasedTerminal("id", "[A-Z]([A-Z]|_|([0-9]))*");

            var str = new RegexBasedTerminal("str", "\"[^\"]*\"");
            var chr = new RegexBasedTerminal("chr", @"\'.?\'");
            var decim = new RegexBasedTerminal("decim", @"([0-9]*)\.([0-9])+");
            var booleano = new RegexBasedTerminal("booleano", "true|false|verdadero|falso");
           
            var puntocoma = new RegexBasedTerminal("puntocoma", ";");

            var clase = new RegexBasedTerminal("clase", "clase");

            var newe = ToTerm("new");
            var importar = ToTerm("importar");
            var privado = ToTerm("privado");
            var publico = ToTerm("publico");
            var overrid = ToTerm("override");
            var main = ToTerm("main");
            var fori = ToTerm("for");
            var repeat = ToTerm("repeat");
            var whiles = ToTerm("while");
            var hacer = ToTerm("hacer");
            var mientras = ToTerm("mientras");
            var ifi = ToTerm("if");
            var els= ToTerm("else");
            var retur = ToTerm("return");
            var array = ToTerm("array");
            var Comprobar = ToTerm("comprobar");
            var Caso = ToTerm("caso");
            var Defecto = ToTerm("defecto");

            var AddFigure = ToTerm("addfigure");
            var Circle = ToTerm("circle");
            var Triangle = ToTerm("triangle");
            var Square = ToTerm("square");
            var Line = ToTerm("line");

            importar.SetFlag(TermFlags.IsReservedWord);
            privado.SetFlag(TermFlags.IsReservedWord);
            publico.SetFlag(TermFlags.IsReservedWord);
            overrid.SetFlag(TermFlags.IsReservedWord);
            main.SetFlag(TermFlags.IsReservedWord);
            fori.SetFlag(TermFlags.IsReservedWord);
            newe.SetFlag(TermFlags.IsReservedWord);
            repeat.SetFlag(TermFlags.IsReservedWord);
            whiles.SetFlag(TermFlags.IsReservedWord);
            hacer.SetFlag(TermFlags.IsReservedWord);
            mientras.SetFlag(TermFlags.IsReservedWord);
            ifi.SetFlag(TermFlags.IsReservedWord);
            els.SetFlag(TermFlags.IsReservedWord);
            retur.SetFlag(TermFlags.IsReservedWord);
            array.SetFlag(TermFlags.IsReservedWord);
            Comprobar.SetFlag(TermFlags.IsReservedWord);
            Caso.SetFlag(TermFlags.IsReservedWord);
            Defecto.SetFlag(TermFlags.IsReservedWord);

            AddFigure.SetFlag(TermFlags.IsReservedWord);
            Circle.SetFlag(TermFlags.IsReservedWord);
            Triangle.SetFlag(TermFlags.IsReservedWord);
            Square.SetFlag(TermFlags.IsReservedWord);
            Line.SetFlag(TermFlags.IsReservedWord);
            #endregion
            #region Terminales
            array.AstConfig.NodeType = typeof(Arbol);
            id.AstConfig.NodeType = typeof(Arbol);
            num.AstConfig.NodeType = typeof(Arbol);
            str.AstConfig.NodeType = typeof(Arbol);
            chr.AstConfig.NodeType = typeof(Arbol);
            decim.AstConfig.NodeType = typeof(Arbol);
            booleano.AstConfig.NodeType = typeof(Arbol);           
            puntocoma.AstConfig.NodeType = typeof(Arbol);
            clase.AstConfig.NodeType = typeof(Arbol);
            
           
        
           
            #endregion
            #region NoTerminales
            NonTerminal DECLA = new NonTerminal("DECLA", typeof(Arbol));
            NonTerminal MAS = new NonTerminal("MAS", typeof(Arbol));
            NonTerminal ASIGNA = new NonTerminal("ASIGNA", typeof(Arbol));
            NonTerminal AUMEN = new NonTerminal("AUMEN", typeof(Arbol));
            NonTerminal LLAV = new NonTerminal("LLAV", typeof(Arbol));
            NonTerminal ARRE = new NonTerminal("ARRE", typeof(Arbol));
            NonTerminal ARREP = new NonTerminal("ARREP", typeof(Arbol));
            NonTerminal VALOR = new NonTerminal("VALOR", typeof(Arbol));
            NonTerminal CLAS = new NonTerminal("CLAS", typeof(Arbol));
            NonTerminal CLASP = new NonTerminal("CLASP", typeof(Arbol));
            NonTerminal CONTECLA = new NonTerminal("CONTECLA", typeof(Arbol));
            NonTerminal PARAMP = new NonTerminal("PARAMP", typeof(Arbol));
            NonTerminal PARAM = new NonTerminal("PARAM", typeof(Arbol));
            NonTerminal VISI = new NonTerminal("VISI", typeof(Arbol));
            NonTerminal METODO = new NonTerminal("METODO", typeof(Arbol));
            NonTerminal OVER = new NonTerminal("OVER", typeof(Arbol));
            NonTerminal CONTEME = new NonTerminal("CONTEME", typeof(Arbol));
            NonTerminal MAI = new NonTerminal("MAI", typeof(Arbol));
            NonTerminal SI = new NonTerminal("SI", typeof(Arbol));
            NonTerminal SINO = new NonTerminal("SINO", typeof(Arbol));
            NonTerminal PARA = new NonTerminal("PARA", typeof(Arbol));
            NonTerminal PARAP = new NonTerminal("PARAP", typeof(Arbol));
            NonTerminal REPETIR = new NonTerminal("REPETIR", typeof(Arbol));
            NonTerminal CUANDO = new NonTerminal("CUANDO", typeof(Arbol));
            NonTerminal COMPROB = new NonTerminal("COMPROB", typeof(Arbol));
            NonTerminal COMPROBP = new NonTerminal("COMPROBP", typeof(Arbol));
            NonTerminal HACERM = new NonTerminal("HACERM", typeof(Arbol));
            NonTerminal VARI = new NonTerminal("VARI", typeof(Arbol));
            NonTerminal PROGRAMA = new NonTerminal("PROGRAMA", typeof(Arbol));


            NonTerminal PARAMETODOP = new NonTerminal("PARAMETROMETODOP", typeof(Arbol));
            NonTerminal PARAMETODO= new NonTerminal("PARAMETROMETODO", typeof(Arbol));

            NonTerminal LLAMAMETO = new NonTerminal("LLAMAMETO", typeof(Arbol));
            NonTerminal VALORRETORNO = new NonTerminal("VALORRETORNO", typeof(Arbol));
            NonTerminal LLAMAVALOR = new NonTerminal("LLAMAVALOR", typeof(Arbol));
            NonTerminal E = new NonTerminal("E", typeof(Arbol));

            var LLAA = new NonTerminal("LLAA", typeof(Arbol));
            var LLAC = new NonTerminal("LLAC", typeof(Arbol));


            NonTerminal FIGURA = new NonTerminal("FIGURA", typeof(Arbol));
            #endregion
            #region Gramatica
            /*      E.Rule = E + ToTerm("+") + E
                      | E + ToTerm("-") + E 
                      | E + ToTerm("*") + E
                      | E + ToTerm("/") + E
                      |r
                      | number;
                      */
            #region PRIMERAPAGINA


            DECLA.Rule = id + id + MAS + ToTerm(";")
                    | id + array + id + MAS + LLAV + ToTerm(";")
                    | id + array + id + MAS + LLAV + ToTerm("=") + ARRE + ToTerm(";")
                    ;
            MAS.Rule = ToTerm(",") + id + MAS
                    | ToTerm("=") + VALOR
                    | Empty
                    ;

            ASIGNA.Rule = id + ToTerm("=") + VALOR + ToTerm(";")
                               | id + LLAV + ToTerm("=") + VALOR + ToTerm(";")
                               | id + ToTerm(";") + id + ToTerm("=") + VALOR + ToTerm(";")
                               ;
            AUMEN.Rule = VALOR + ToTerm("++")
                    | VALOR + ToTerm("--")
                    ;
            LLAV.Rule = ToTerm("[") + VALOR + ToTerm("]") + LLAV
                | ToTerm("[") + VALOR + ToTerm("]")
                ;
            ARRE.Rule = LLAA+ ARRE + LLAC
                | ARRE + ToTerm(",") + ARRE
                | VALOR
                ;
            LLAA.Rule = ToTerm("{");
            LLAC.Rule = ToTerm("}");
            #endregion
            #region SegundaPagina
            VALOR.Rule = VALOR + ToTerm("+") + VALOR

                | VALOR + ToTerm("-") + VALOR
                | VALOR + ToTerm("^") + VALOR
                | VALOR + ToTerm("/") + VALOR
                | VALOR + ToTerm("*") + VALOR
                | VALOR + ToTerm("&&") + VALOR
                | VALOR + ToTerm("||") + VALOR
                | VALOR + ToTerm(">=") + VALOR
                | VALOR + ToTerm(">") + VALOR
                | VALOR + ToTerm("<") + VALOR
                | VALOR + ToTerm("<=") + VALOR
                | VALOR + ToTerm("==") + VALOR
                | VALOR + ToTerm("!=") + VALOR            
                | ToTerm("!")+VALOR
                | id
                | id + LLAV
                | num
                | str
                | chr
                | decim
                | booleano
                | AUMEN
                | id + ToTerm(".") + id
                | id + ToTerm(".") + id + ToTerm("(") + PARAM + ToTerm(")")
                | newe + id + ToTerm("(") + PARAM + ToTerm(")")
                | LLAMAVALOR
                | ToTerm("(") + VALOR + ToTerm(")")
                | ToTerm("+") + VALOR
                | ToTerm("-") + VALOR

                ;

            LLAMAVALOR.Rule = id + ToTerm("(") + PARAM + ToTerm(")");

            #endregion
            #region TERCERAPAGINA
            PROGRAMA.Rule = PROGRAMA + CLAS
                | Empty;

            CLAS.Rule = clase + id + CLASP + CONTECLA + ToTerm("}");
            CLASP.Rule = importar + VARI + ToTerm("{")
                | ToTerm("{")
                ;
            VARI.Rule = id + ToTerm(",") + VARI
                | id
                ;
            CONTECLA.Rule = METODO + CONTECLA
               | VISI + DECLA + CONTECLA
               | VISI + METODO + CONTECLA
               |  DECLA + CONTECLA
               | ASIGNA + CONTECLA
               | MAI + CONTECLA
               | Empty
               ;
            PARAMP.Rule = VALOR + ToTerm(",") + PARAMP
                | VALOR
                ;
            PARAM.Rule = PARAMP
                | Empty
                ;
            VISI.Rule = privado
                | publico
             
                ;
            #endregion
            #region CUARTAPAGINA
            PARAMETODOP.Rule = id +id + ToTerm(",") + PARAMETODOP
                | id +id
                ;
            PARAMETODO.Rule = PARAMETODOP
                | Empty
                ;


            METODO.Rule =  id + id + OVER + PARAMETODO + ToTerm(")") + ToTerm("{") + CONTEME + ToTerm("}")
                ;
            OVER.Rule = ToTerm("(")
                | overrid + ToTerm("(")
                ;
            CONTEME.Rule = DECLA + CONTEME
                  | ASIGNA+CONTEME
                |AUMEN+CONTEME
                 | VALORRETORNO
                 |LLAMAMETO + CONTEME
                 | ToTerm("print")+ ToTerm("(")+VALOR+ ToTerm(")")+ ToTerm(";") + CONTEME
                 | ToTerm("show")+ ToTerm("(")+VALOR+ ToTerm(",")+VALOR+ ToTerm(")")+ ToTerm(";") + CONTEME
                 | SI+CONTEME
                | PARA + CONTEME
                |REPETIR+CONTEME
                | ToTerm("salir")+CONTEME
                |CUANDO +CONTEME
                |COMPROB+CONTEME
                |ToTerm("continuar")+CONTEME
                |HACERM+CONTEME   
                |FIGURA + CONTEME
                |Empty
                ;
            VALORRETORNO.Rule = (retur + VALOR + ToTerm(";") + CONTEME);

            LLAMAMETO.Rule = id + ToTerm("(") + PARAM + ToTerm(")") + ToTerm(";");

            MAI.Rule = main+ ToTerm("(")+ ToTerm(")")+ ToTerm("{")+ CONTEME+ ToTerm("}")
                ;
            #endregion
            #region QUINTAPAGINA
            SI.Rule = ifi + ToTerm("(") + VALOR + ToTerm(")") + ToTerm("{") + CONTEME + ToTerm("}") + SINO
                ;
            SINO.Rule = els + SI
                | els + ToTerm("{") + CONTEME + ToTerm("}")
                | Empty
                ;
            PARA.Rule = fori  +ToTerm("(") + PARAP + VALOR + ToTerm(";") + AUMEN + ToTerm(")") + ToTerm("{") + CONTEME + ToTerm("}")
                ;
            PARAP.Rule = ASIGNA
                | DECLA
                ;
            REPETIR.Rule = repeat + ToTerm("(") + VALOR + ToTerm(")") + ToTerm("{") + CONTEME + ToTerm("}")
                ;
            CUANDO.Rule = whiles + ToTerm("(") + VALOR + ToTerm(")") + ToTerm("{") + CONTEME + ToTerm("}")
                ;
            COMPROB.Rule = Comprobar+ ToTerm("(") + VALOR + ToTerm(")") + ToTerm("{") + COMPROBP + ToTerm("}")
                ;
            COMPROBP.Rule = Caso + VALOR + ToTerm(":") + CONTEME + COMPROBP
                | Defecto+ ToTerm(":")+CONTEME + COMPROBP
                | Empty
                ;
            HACERM.Rule = hacer + ToTerm("{") + CONTEME + ToTerm("}") + mientras + ToTerm("(") + VALOR + ToTerm(")") + ToTerm(";");

            FIGURA.Rule =AddFigure + ToTerm("(") +Circle + ToTerm("(") +VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR  + ToTerm(")") + ToTerm(")") + ToTerm(";")
                    | AddFigure + ToTerm("(") + Triangle + ToTerm("(") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(")") + ToTerm(")") + ToTerm(";")
                    | AddFigure + ToTerm("(") + Square + ToTerm("(") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR  + ToTerm(")") + ToTerm(")") + ToTerm(";")
                    | AddFigure + ToTerm("(") + Line + ToTerm("(") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR + ToTerm(",") + VALOR  + ToTerm(")") + ToTerm(")") + ToTerm(";")
                    ;
            #endregion
            #endregion
            #region Precedencia
            RegisterOperators(7,  "^"); 
            RegisterOperators(6,  "*", "/");
            RegisterOperators(5,  "+", "-");
            RegisterOperators(4,  "==", "!=","<","<=",">",">=");
            RegisterOperators(3, "!");
            RegisterOperators(2,   "&&");
            RegisterOperators(1,   "||");           
            #endregion

            this.Root = PROGRAMA;
           
            this.MarkPunctuation(",", ";", "(", ")","{","}",":");
            NonGrammarTerminals.Add(singleLineComment); // QUITAMOS LOS COMENTARIOS DE LA GRAMATICA
            NonGrammarTerminals.Add(delimitedComment);
            LanguageFlags = LanguageFlags.CreateAst;    //IMPORTANTE PARA CREAR EL ARBOL SIN ESTO NO LO CREARA
        }
        public Semantico Seman=null;
        public Double Obtener_Resultado(string sourceCode)
        {
            LanguageData language = new LanguageData(this);
            Parser parser = new Parser(language);
            ParseTree parseTree = parser.Parse(sourceCode);
            ParseTreeNode root = parseTree.Root;


            if (root != null)
            {



                Grafica Graphviz = new Grafica();


                Arbol a = (Arbol)root.AstNode;
                NodoArbol Raiz = new NodoArbol("programa");
                recorrerAST(Raiz,root);

               
                Seman = new Semantico(Raiz);
                Valor(a);
                Graphviz.Graficar(Raiz);
                return 100;
            }
            else {

                for (int i = 0; i < parseTree.ParserMessages.Count; i++) {
                    Irony.LogMessageList A = parseTree.ParserMessages;
                    Console.WriteLine(A.ElementAt(i).Level.ToString()+"         " +A.ElementAt(i).Message + "       " + A.ElementAt(i).Location.Column + "         " + A.ElementAt(i).Location.Line) ;
                }

                return -1;
            }
             //OBTENEMOS EL VALRO DE LA ENTRADA
        }
        private void recorrerAST(NodoArbol Nodo, ParseTreeNode Tree)
        {
            if (Tree != null)
            {

                foreach (ParseTreeNode hijos in Tree.ChildNodes)
                {
                  //  Console.WriteLine(hijos);

                    NodoArbol Nodito = new NodoArbol(hijos.ToString());
                    Nodo.Hijos.Add(Nodito);
                    
                    recorrerAST(Nodito, hijos);

                }

            }
        }
    }
}
