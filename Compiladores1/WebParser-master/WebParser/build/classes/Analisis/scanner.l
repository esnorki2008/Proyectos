package Analisis; // Paquete donde estará nuestro scanner
import java_cup.runtime.Symbol; 
import java.util.ArrayList;
import Motor.*;
%% 

%{

public  ArrayList<Token> ErrorT = new ArrayList<Token> ();
public  ArrayList<Token> BuenoT = new ArrayList<Token> ();


    /*Area de declaración de codigo java. 
    *Funciones y variables necesarias
    *
    */
    String nombre;
    public void imprimir(String cadena)
    {
    	System.out.println(cadena);
    }

     private  boolean TipoRGB(String Ingreso){
         Ingreso=Ingreso.toLowerCase();
         if(Ingreso.length()!=7 ){
            return false;
        }
        for(int i=1;i<(Ingreso.length()-1);i++){
            char[] Vect=Ingreso.toCharArray();
            switch(Vect[i]){
                case 'a':  break;
                case 'b':  break;
                case 'c':  break;
                case 'd':  break;
                case 'e':  break;
                case 'f':  break;
                case '#':  break;
                case (char)34:  break;
                default: 
                    if(Character.isDigit(Vect[i])){}else{
                    return false;
                    }
            }
        }
        return true;
    }

    int  TipoCadena(String Ingreso){
        Ingreso=Ingreso.substring(1,Ingreso.length()-1).toLowerCase();

        switch(Ingreso){
            case "izquierda":
                return sym.izquierda;
            case "derecha":
                return sym.derecha;
            case "centrado":
                return sym.centrado;
            case "justificado":
                return sym.justificado;                
            case "rojo":
                return sym.color;
            case "azul":
                return sym.color;
            case "verde":
                return sym.color;
            case "negro":
                return sym.color;
            case "amarillo":
                return sym.color;
            case "rosado":
                return sym.color;
            default:
                if(TipoRGB(Ingreso)){
                    return sym.rgb;
                }else{
                return sym.cadena;
                }
        }
    } 
%}

// Area de directivas
%cupsym sym 
%class scanner
%cup
%public
%unicode
%line
%column
%char
%ignorecase
%8bit
%full


// Area de definición de expresiones regulares. Tokens.
Comentario = "//" [^\r\n]* [^\r\n]
ComentarioMulti = "/*" [^/] ~"*/" | "/*" "/"+ "*/"
entero =[0-9]+ 
decimal =[0-9]+ "." [0-9]*
cadena =[\"] [^\"\n]* [\"\n]
letra =[a-zA-ZÑñ]+
la= [\u00E1]|[\u00E9]|[\u00ED]|[\u00F3]|[\u00FA]|[\u00F1]
chafa=[\u01BA]

id =({letra}|{la})({letra}|{entero}|"_")*
caracter="'"[^]"'"  
esp = \t|\f|" "|\r|\n

basura = "<!" [^chafa] ~"!>" | "<!" "/"+ "!>"


compi="compi"
cabecera="cabecera"
titulo="titulo"
cuerpo="cuerpo"
fondo="fondo"
espacio="espacio"
parrafo="parrafo"
textoa="textoa"
textob="textob"
imagen ="imagen"
path = "path"
imgdir=(\\\\?([^\\/]*[\\/])*)([^\\/]+)
alto="alto"
ancho="ancho"
boton="boton"
idboton="id"
textoboton="texto"
tabla="tabla"
borde="borde"
fila="fila"
columna="columna"
columnac="columnac"
hs="hs"
echo="echo"
ifv="if"
elses="else"
repetir="repetir"
salto="salto"
alineacion="alineacion"
insertar ="insertar"
crearparrafo ="crearparrafo"
setalineacion ="setalineacion"
getalineacion ="getalineacion"
setcontenido ="setcontenido"
getcontenido ="getcontenido"

creartextoa ="creartextoa"
creartextob ="creartextob"
crearimagen ="crearimagen"
setpath ="setpath"
getpath ="getpath"
setalto ="setalto"
getalto ="getalto"
setancho ="setancho"
getancho ="getancho" 
creartabla="creartabla"
setborder ="setborde"
crearboton ="crearboton"
settexto ="settexto"
gettexto ="gettexto"
clickboton ="clickboton"

%%

// Acciones
/* Espacios en blanco */

{cadena} {  Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N);   return new Symbol(TipoCadena(yytext()),yycolumn,yyline,yytext().substring(1,yytext().length()-1).toLowerCase());}

        

{compi} { Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.compi,yycolumn,yyline,yytext());}
{cabecera} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.cabecera,yycolumn,yyline,yytext());}
{titulo} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.titulo,yycolumn,yyline,yytext());}
{cuerpo} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.cuerpo,yycolumn,yyline,yytext());}
{fondo} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.fondo,yycolumn,yyline,yytext());}
{espacio} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.espacio,yycolumn,yyline,yytext());}
{parrafo} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.parrafo,yycolumn,yyline,yytext());}
{textoa} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.textoa,yycolumn,yyline,yytext());}
{textob} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.textob,yycolumn,yyline,yytext());}
{imagen} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.imagen,yycolumn,yyline,yytext());}
{path} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.path,yycolumn,yyline,yytext());}
{imgdir} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.imgdir,yycolumn,yyline,yytext());}
{alto} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.alto,yycolumn,yyline,yytext());}
{ancho} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.ancho,yycolumn,yyline,yytext());}
{boton} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.boton,yycolumn,yyline,yytext());}
{idboton} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.idboton,yycolumn,yyline,yytext());}
{textoboton} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.textoboton,yycolumn,yyline,yytext());}
{tabla} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.tabla,yycolumn,yyline,yytext());}
{borde} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.borde,yycolumn,yyline,yytext());}
{fila} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.fila,yycolumn,yyline,yytext());}
{columna} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.columna,yycolumn,yyline,yytext());}
{columnac} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.columnac,yycolumn,yyline,yytext());}
{hs} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.hs,yycolumn,yyline,yytext());}
{echo} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.echo,yycolumn,yyline,yytext());}
{ifv} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.ifv,yycolumn,yyline,yytext());}
{elses} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.elses,yycolumn,yyline,yytext());}
{repetir} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.repetir,yycolumn,yyline,yytext());}
{salto} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.salto,yycolumn,yyline,yytext());}
{alineacion} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.alineacion,yycolumn,yyline,yytext());}
{basura} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); }
{insertar} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.insertar,yycolumn,yyline,yytext());}
{crearparrafo}  {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.crearparrafo,yycolumn,yyline,yytext());}
{setalineacion} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.setalineacion,yycolumn,yyline,yytext());}
{getalineacion} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.getalineacion,yycolumn,yyline,yytext());}
{setcontenido} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.setcontenido,yycolumn,yyline,yytext());}
{getcontenido} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.getcontenido,yycolumn,yyline,yytext());}
{creartabla} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.creartabla,yycolumn,yyline,yytext());}


{creartextoa} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.creartextoa,yycolumn,yyline,yytext());}
{creartextob} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.creartextob,yycolumn,yyline,yytext());}
{crearimagen} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.crearimagen,yycolumn,yyline,yytext());}
{setpath} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.setpath,yycolumn,yyline,yytext());}
{getpath} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.getpath,yycolumn,yyline,yytext());}
{setalto} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.setalto,yycolumn,yyline,yytext());}
{getalto} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.getalto,yycolumn,yyline,yytext());}
{setancho} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.setancho,yycolumn,yyline,yytext());}
{getancho} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.getancho ,yycolumn,yyline,yytext());}

{setborder} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.setborder,yycolumn,yyline,yytext());}
{crearboton} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.crearboton,yycolumn,yyline,yytext());}
{settexto} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.settexto,yycolumn,yyline,yytext());}
{gettexto}  {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.gettexto,yycolumn,yyline,yytext());}
{clickboton} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.clickboton ,yycolumn,yyline,yytext());}







{esp}     {/*Ignorar*/}
{entero} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.entero,yycolumn,yyline,yytext());}
{decimal} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.decimal,yycolumn,yyline,yytext());}
{id} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); if(yytext().equals("true")||yytext().equals("false")){return new Symbol(sym.booleano,yycolumn,yyline,yytext());} return new Symbol(sym.id,yycolumn,yyline,yytext());}
{caracter} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.caracter,yycolumn,yyline,yytext());}
"$" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.dolar,yycolumn,yyline,yytext());}
"#" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.numeral,yycolumn,yyline,yytext());}
"&&" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.anddoble,yycolumn,yyline,yytext());}
"||" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.ordoble,yycolumn,yyline,yytext());}
"[" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.cora,yycolumn,yyline,yytext());}
"]" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.corc,yycolumn,yyline,yytext());}
"{" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.llaa,yycolumn,yyline,yytext());}
"}" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.llac,yycolumn,yyline,yytext());}
"&" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.and,yycolumn,yyline,yytext());}
"|" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.or,yycolumn,yyline,yytext());}
"<=" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.menorigual,yycolumn,yyline,yytext());}
">=" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.mayorigual,yycolumn,yyline,yytext());}
"!=" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.diferente,yycolumn,yyline,yytext());}
"==" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.igualdoble,yycolumn,yyline,yytext());}
"=" {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.igual,yycolumn,yyline,yytext());}
"," {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.coma,yycolumn,yyline,yytext());}




"?"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.interro,yycolumn,yyline,yytext());}
";"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.puntocoma,yycolumn,yyline,yytext());}
"("    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.para,yycolumn,yyline,yytext());}
")"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.parc,yycolumn,yyline,yytext());}
"+"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.suma,yycolumn,yyline,yytext());}
"-"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.resta,yycolumn,yyline,yytext());}
"*"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.multi,yycolumn,yyline,yytext());}
"/"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.div,yycolumn,yyline,yytext());}
">"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.may,yycolumn,yyline,yytext());}
"<"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.men,yycolumn,yyline,yytext());}
"!"    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.admira,yycolumn,yyline,yytext());}
"."    {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N); return new Symbol(sym.punto,yycolumn,yyline,yytext());}


{Comentario} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N);  /*Se ignora.*/}
{ComentarioMulti} {Token N= new Token(yytext(),yycolumn,yyline);   BuenoT.add(N);  /*Se ignora.*/}
/*Recolección de errores*/
.   
{               
    Token N= new Token(yytext(),yycolumn+1,yyline+1);
    ErrorT.add(N);


     //   System.err.println("Error lexico: "+yytext()+ " Linea:"+(yyline+1)+" Columna:"+(yycolumn+1));
}


