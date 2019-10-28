using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Irony.Ast;
using Irony.Interpreter;
using Irony.Parsing;
using Irony.Interpreter.Ast;

namespace Proyecto2Compiladores1
{
    public class Arbol : AstNode
    {
        public Object Name;
        public List<Arbol> hijos;
        public override void Init(Irony.Ast.AstContext context, Irony.Parsing.ParseTreeNode treeNode)
        {
            hijos = new List<Arbol>();//inicializamos la lista
            base.Init(context, treeNode);
            
        }
    }
}
