/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Estructuras.Lineales.ListaCircular;
import Estructuras.Lineales.Queue;
import Estructuras.Matriz;
import Estructuras.NoLineales.ABB;
import Estructuras.NoLineales.ArbolAVL;
import Nodos.Node;
import Nodos.NodoABB;
import Nodos.NodoAVL;
import Objetos.Capas;
import Objetos.Imagenes;
import Objetos.Usuarios;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lizandro
 */
public class Acciones {
    private ArbolAVL ArbolUsuarios;
    private ABB ArbolCapas;
    private ListaCircular ListaImagenes;
    public Acciones(){
        this.ArbolUsuarios= new ArbolAVL();
        this.ArbolCapas= new ABB();
        this.ListaImagenes= new ListaCircular();
    }
    public void CrearArchivo(String Entrada, String Nombre) throws IOException {
        System.out.println(Path());
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Path()  + Nombre + ".txt"), "utf-8"))) {
            writer.write(Entrada);
        }
    }
    public String Path(){
        final File f = new File(Maincito.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        return (f.toString()+"\\");
    }
    public void Graphviz(String Entrada,String Nombre){
        
        String Comando="dot -Tpng "+Path()+Nombre+".txt -o "+Path()+Nombre+".png";
        try {
            CrearArchivo(Entrada,Nombre);
            Process p = Runtime.getRuntime().exec(Comando);
        } catch (IOException ex) {
            Mensaje("Error Al Generar Grafica","Error Del Sistema");
            Logger.getLogger(Acciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public String MemoriaArbolUsuarios(){
        String Retorno="";
        Retorno=this.ArbolUsuarios.Graficar();
        return Retorno;
    }
    public String MemoriaImagen_Capas(String Imagen){
        String Retorno="";
       /* NodoAVL Nodito=this.ArbolUsuarios.Buscar(this.ArbolUsuarios.raiz, Nombre);
        if(Nodito==null){
            Mensaje("Usuario "+Nombre+" No Encontrado","No Encontrado");
            return "";
        }
        Usuarios User= (Usuarios) Nodito.getContenido();
        Node S=User.getListaImagenes().Find(Imagen);
        if(S==null){
            Mensaje("El Usuario "+Nombre+" No tiene esta imagen "+ Imagen,"No Encontrado");
            return "";
        }*/
        Imagenes Ima=null;
        try {
            Ima = ((Imagenes) this.ListaImagenes.Find(Imagen).getContenido());
        } catch (Exception S) {
            Mensaje("La Imagen " + Imagen + " No Fue Encontrada ", "Imagen Error");
            return "";
        }
        Retorno += "digraph G{\n";
        Retorno += "node[shape=\"box\"];\n";
        Retorno+=Ima.toString()+" -> "+"LST"+Ima.getListaCapas().getHead().toString()+"\n";
         Node TempNode=Ima.getListaCapas().getHead();
        Retorno+="\n{rank=min;"+Ima.toString()+";";
            while(TempNode!=null){
                Retorno+="LST"+TempNode.toString()+";";
                TempNode=TempNode.getNext();
            }
        Retorno+="}\n";

        
        Retorno+=this.ArbolCapas.GraficarSinCabecera();
        Retorno+=Ima.getListaCapas().GraficarSinCabecera(2,"");
        Retorno += "}";
        return Retorno;
    }
    public String MemoriaCapa(int ID){
        NodoABB Nodito=this.ArbolCapas.Buscar(ID);
        if(Nodito==null)
            return "";
        Capas S=(Capas)Nodito.getDato();
        if(S==null)
            return "";
        else
            return S.getMOrto().Graficar();
    }
    public String MemoriaListaImagenes(){
        String grafo="";
        grafo = "digraph G{";
        grafo += "node[shape=\"box\"];\n";
        grafo += this.ListaImagenes.GraficarSinCabecera(1,"");
        grafo += "}";
        return grafo;
    }
    public String MemoriaArbolCapas(){
        return this.ArbolCapas.Graficar();
    }
    private void Mensaje(String Contenido,String Titulo){
                    JOptionPane.showMessageDialog(null, Contenido, Titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    public String GenerarImagenPorUsuarioImagen(String Nombre,String Imagen){
        NodoAVL Nodito=this.ArbolUsuarios.Buscar(this.ArbolUsuarios.raiz, Nombre);
        if(Nodito==null){
            Mensaje("Usuario "+Nombre+" No Encontrado","No Encontrado");
            return "";
        }
        Usuarios User= (Usuarios) Nodito.getContenido();
        Node S=User.getListaImagenes().Find(Imagen);
        if(S==null){
            Mensaje("El Usuario "+Nombre+" No tiene esta imagen "+ Imagen,"No Encontrado");
            return "";
        }
        Imagenes Ima=((Imagenes)S.getContenido());
        Node Cabeza=Ima.getListaCapas().getHead();
        Matriz NuevaMatriz= new Matriz();
        while(Cabeza!=null){
            NuevaMatriz=((Capas)Cabeza.getContenido()).getMOrto().GenerarMatrizCopia(NuevaMatriz);
            Cabeza=Cabeza.getNext();
        }
        return NuevaMatriz.GenerarImagen();
    }
    public String GenerarImagenPorCapa(int ID){
        NodoABB S=this.ArbolCapas.Buscar(ID);
        if(S==null){
            Mensaje("Capa "+ID+" No Encontrada ","No Encontrado");
            return "";
        }
        Capas Capa=((Capas)S.getDato());
        Matriz NuevaMatriz= new Matriz();
        NuevaMatriz=(Capa).getMOrto().GenerarMatrizCopia(NuevaMatriz);
        return NuevaMatriz.GenerarImagen();
    }
    public String GenerarImagenPorLista(String ID){
        Node S=getListaImagenes().Find(ID);
        if(S==null)
        {
             Mensaje("Imagen "+ID+" No Encontrada ","No Encontrado");
             return "";
        }
        Imagenes Ima=((Imagenes)S.getContenido());
        Node Cabeza=Ima.getListaCapas().getHead();
        Matriz NuevaMatriz= new Matriz();
        while(Cabeza!=null){
            NuevaMatriz=((Capas)Cabeza.getContenido()).getMOrto().GenerarMatrizCopia(NuevaMatriz);
            Cabeza=Cabeza.getNext();
        }
        return NuevaMatriz.GenerarImagen();
    }
    public String GenerarImagenPreOrden(int Num){
        Queue Cola= new Queue("");
        Cola=ArbolCapas.PreOrdenCola(Num);
        Matriz S= new Matriz();
        while(Cola.Front()!=null){
            S=((Capas)Cola.Front().getContenido()).getMOrto().GenerarMatrizCopia(S);
            Cola.Dequeue();
        }
        return S.GenerarImagen();
    }
    public String GenerarPostOrden(int Num){
        Queue Cola= new Queue("");
        Cola=ArbolCapas.PostOrdenCola(Num);
        Matriz S= new Matriz();
        while(Cola.Front()!=null){
            S=((Capas)Cola.Front().getContenido()).getMOrto().GenerarMatrizCopia(S);
            Cola.Dequeue();
        }
        return S.GenerarImagen();
    }
    public String GenerarImagenInorden(int Num){
        Queue Cola= new Queue("");
        Cola=ArbolCapas.InordenCola(Num);
        Matriz S= new Matriz();
        while(Cola.Front()!=null){
            S=((Capas)Cola.Front().getContenido()).getMOrto().GenerarMatrizCopia(S);
            Cola.Dequeue();
        }
        return S.GenerarImagen();
    }
    
    private void RecorridoEliminar(String Ingreso){
        Queue Cola= new Queue("NoUso");
        if(this.ArbolUsuarios.raiz==null){
            Mensaje("Arbol De Usuarios Vacio","Sin Usuarios");
            return;
            }
        NodoAVL NodoTemp=this.ArbolUsuarios.raiz;
        Cola.Enqueue(new Node("",NodoTemp));
        Object Ob=this.ListaImagenes.Find(Ingreso);
        if(Ob==null){
            Mensaje("No Existe Esa Imagen En La Lista","Imagen No Encontrada");
            return;
        }
        Imagenes Img=(Imagenes)((Node)Ob).getContenido();
        while(Cola.Front()!=null){
            NodoTemp=(NodoAVL)Cola.Dequeue().getContenido();
            if(NodoTemp.getNIzq()!=null)
            Cola.Enqueue(new Node("",NodoTemp.getNIzq()));
            if(NodoTemp.getNDer()!=null)
            Cola.Enqueue(new Node("",NodoTemp.getNDer()));
            Usuarios Usr= (Usuarios)NodoTemp.getContenido();
            Usr.EliminarImagenes(Img);
        }
    }
    public void ABCUsuariosEliminar(String Ingreso){
        this.ArbolUsuarios.Eliminar(Ingreso);
    }
    public void ABCImagenesEliminar(String Ingreso){
        RecorridoEliminar(Ingreso);
        this.ListaImagenes.Delete(Ingreso);
    }
    public ListaCircular ABCCargarImagenes(String Ingreso,ABB ArbolCapas){
        Ingreso=Ingreso.replace("\n", "");
        //Separanod Cada Imagen
        String CargaImagenes[]=Ingreso.split("\\}");
        for(int i=0;i<CargaImagenes.length;i++){
            //Separando Nombre Imagen Y Su Contenido
            String[] ConteImagenes=CargaImagenes[i].split("\\{");
            Imagenes NuevaImagen = new Imagenes((ConteImagenes[0]));
            if(ConteImagenes.length!=1){
            String[] Atributos = ConteImagenes[1].split("\\,");
            for(int j=0;j<Atributos.length;j++){
                int SupuestaCapa=ComNum(Atributos[j]);
                NodoABB Capita=ArbolCapas.Buscar(SupuestaCapa);
                if(Capita==null)
                     //Comentario Para CUANDO NO SE ENCUENTEA LA CAPA 
                    Mensaje("La CAPA NO EXISTE "+Atributos[j],"Problema");
                else
                   // NuevaImagen.getListaCapas().Add(new Node(Capita.getIdentificador(),Capita.getDato()));
                    
                    NuevaImagen.getListaCapas().Add(new Node(""+Capita.getIdentificador(),Capita.getDato()));
            }
            }
            Object Existe=this.ListaImagenes.Find(NuevaImagen.getIdentificador());
            if(Existe==null)
            this.ListaImagenes.Add(new Node(NuevaImagen.getIdentificador(),NuevaImagen));
        }
        return this.ListaImagenes;
    }
    public ArbolAVL ABCUsuariosCargar(String Ingreso,ListaCircular ListaImagenes){
        ArbolAVL Arbol = this.getArbolUsuarios();
        Ingreso=Ingreso.replace("\n", "");
        //Separando Cada Usuario
        String CargaImagenes[]=Ingreso.split("\\;");
        for(int i=0;i<CargaImagenes.length;i++){
            //Separar Contenido Del usuario
            String[] Atributos= CargaImagenes[i].split("\\:");
            Usuarios NuevoUsuario= new Usuarios(Atributos[0]);
            //Separar Cada Imagen
            if(Atributos.length>1){
            String[] Image=Atributos[1].split(",");
            for(int j=0;j<Image.length;j++){
                Node Supuesta = ListaImagenes.Find((Image[j]));
                if(Supuesta==null)
                    Mensaje("LA Imagen No Existe "+Image[j],"Problema");
                else{
                NuevoUsuario.getListaImagenes().Add(new Node(Supuesta.getIdentificador(),Supuesta.getContenido()));
                }
            }
            }
            Arbol.insertar(NuevoUsuario.getNombre(), NuevoUsuario);
        }
        return Arbol;
    }
    public ArbolAVL CargarUsuarios(String Ingreso,ListaCircular ListaImagenes){
        ArbolAVL Arbol = new ArbolAVL();
        Ingreso=Ingreso.replace("\n", "");
        //Separando Cada Usuario
        String CargaImagenes[]=Ingreso.split("\\;");
        for(int i=0;i<CargaImagenes.length;i++){
            //Separar Contenido Del usuario
            String[] Atributos= CargaImagenes[i].split("\\:");
            Usuarios NuevoUsuario= new Usuarios(Atributos[0]);
            //Separar Cada Imagen
            if(Atributos.length>1){
            String[] Image=Atributos[1].split(",");
            for(int j=0;j<Image.length;j++){
                Node Supuesta = ListaImagenes.Find((Image[j]));
                if(Supuesta==null)
                Mensaje("LA Imagen No Existe "+Image[j],"Problema");
                else{
                NuevoUsuario.getListaImagenes().Add(new Node(Supuesta.getIdentificador(),Supuesta.getContenido()));
                }
                
            }
            }
            Arbol.insertar(NuevoUsuario.getNombre(), NuevoUsuario);
        }
        this.setArbolUsuarios(Arbol);
        return Arbol;
    }
    public ListaCircular CargarImagenes(String Ingreso,ABB ArbolCapas){
        ListaCircular Lista= new ListaCircular();
        Ingreso=Ingreso.replace("\n", "");
        //Separanod Cada Imagen
        String CargaImagenes[]=Ingreso.split("\\}");
        for(int i=0;i<CargaImagenes.length;i++){
            //Separando Nombre Imagen Y Su Contenido
            String[] ConteImagenes=CargaImagenes[i].split("\\{");
            Imagenes NuevaImagen = new Imagenes((ConteImagenes[0]));
            String[] Atributos = ConteImagenes[1].split("\\,");
            for(int j=0;j<Atributos.length;j++){
                int SupuestaCapa=ComNum(Atributos[j]);
                NodoABB Capita=ArbolCapas.Buscar(SupuestaCapa);
                if(Capita==null)
                     //Comentario Para CUANDO NO SE ENCUENTEA LA CAPA 
                    Mensaje("La CAPA NO EXISTE "+Atributos[j],"Problema");
                else
                   // NuevaImagen.getListaCapas().Add(new Node(Capita.getIdentificador(),Capita.getDato()));
                    NuevaImagen.getListaCapas().Add(new Node(""+Capita.getIdentificador(),Capita.getDato()));
            }
            Object Existe=this.ListaImagenes.Find(NuevaImagen.getIdentificador());
            if(Existe==null)
            Lista.Add(new Node(NuevaImagen.getIdentificador(),NuevaImagen));
        }
        this.setListaImagenes(Lista);
        return Lista;
    }
    public ABB CargarCapas(String Ingreso){
        ABB Capas= new ABB();
        Ingreso=Ingreso.replace("\n", "");
        Ingreso=Ingreso.replace(((char)9)+"", "");
        String CargaCapas[]=Ingreso.split("\\}");
        // pos 0 Nombre 1 Demas Contenido partiendo por capas
        for(int i=0;i<CargaCapas.length;i++){
          //  System.out.println(CargaCapas[i]);
            String[] SubCapa=CargaCapas[i].split("\\{");
            Capas NuevaCapa = new Capas(ComNum(SubCapa[0]));
            if(SubCapa.length!=1){
            String CargaAtributo[]=SubCapa[1].split("\\;");
            //Partiendo Attibutos De X y Y
            for(int j=0;j<CargaAtributo.length;j++){
                //Partir Agregando A La Capa
                String ConteCapa[]=CargaAtributo[j].split("\\,");
                if(ConteCapa.length>1){
               // System.out.println(ConteCapa[0]+"----"+ConteCapa[1]+"-----"+ConteCapa[2]+"---"+NuevaCapa.getID());
                NuevaCapa.getMOrto().Agregar(ComNum(ConteCapa[1]),ComNum(ConteCapa[0]), ConteCapa[2]);
                }
            }
           // System.out.println(NuevaCapa.getMOrto().Graficar());
            //return null;
             }else{
                NuevaCapa.getMOrto().Agregar(1,1, "#FFFFFF");
            }
             Capas.Agregar(new NodoABB(NuevaCapa.getID(),NuevaCapa) );
       
            }
        this.setArbolCapas(Capas);
        return Capas;
    }
    private int ComNum(String Ingreso){
        int Num=0;
        try{
           Num= Integer.parseInt(Ingreso.trim().replace("\"",""));
        }catch(Exception S){
            return 0;
        }
        return Num;
    }

    /**
     * @return the ArbolUsuarios
     */
    public ArbolAVL getArbolUsuarios() {
        return ArbolUsuarios;
    }

    /**
     * @param ArbolUsuarios the ArbolUsuarios to set
     */
    public void setArbolUsuarios(ArbolAVL ArbolUsuarios) {
        this.ArbolUsuarios = ArbolUsuarios;
    }

    /**
     * @return the ArbolCapas
     */
    public ABB getArbolCapas() {
        return ArbolCapas;
    }

    /**
     * @param ArbolCapas the ArbolCapas to set
     */
    public void setArbolCapas(ABB ArbolCapas) {
        this.ArbolCapas = ArbolCapas;
    }

    /**
     * @return the ListaImagenes
     */
    public ListaCircular getListaImagenes() {
        return ListaImagenes;
    }

    /**
     * @param ListaImagenes the ListaImagenes to set
     */
    public void setListaImagenes(ListaCircular ListaImagenes) {
        this.ListaImagenes = ListaImagenes;
    }
}
