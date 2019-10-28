/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import Estructuras.Lineales.ListaCircular;
import Estructuras.Lineales.ListaDoble;
import Estructuras.Lineales.ListaSimple;
import Estructuras.Lineales.ListaSimpleSTR;
import Estructuras.Lineales.Queue;
import Estructuras.NoLineales.ArbolAVL;
import Estructuras.NoLineales.ArbolB;
import Estructuras.NoLineales.Hashtable;
import GUI.Maincito;
import Nodos.Node;
import Nodos.NodoB;
import Objetos.Asignar;
import Objetos.Catedratico;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Salon;
import Objetos.Usuario;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author lizandro
 */
public class CargaMasiva {
    private ListaDoble UsuarioLST;
    private ListaCircular EdificioLST;
    private ListaSimple CursoLST;
    private Hashtable EstudianteHash;
    private ArbolAVL CatedraticoArbol;
    private ArbolB HorarioArbol;
    private Graficar Graph;
    public CargaMasiva(){
        UsuarioLST= new ListaDoble();
        EdificioLST= new ListaCircular();
        CursoLST= new ListaSimple();
        EstudianteHash = new Hashtable();
        CatedraticoArbol= new ArbolAVL();
        HorarioArbol= new ArbolB();
        Graph = new Graficar();
    }
    
    public Queue ReporteEstudiantesAprobadosReprobadosEnSemestre(int Semestre){
        Reportes R = new Reportes();
        return R.EstudiantesAprobadosReprobadosEnSemestre(Semestre, HorarioArbol);
    }
    public Queue ReporteCursosEnCiertoSalon(String Edificio,int NumSalon){
        Reportes R = new Reportes();
        Edificio Edi = (Edificio) this.EdificioLST.Find(Edificio);
        if(Edi==null){
            this.Mensaje("No Existe Ese Edificio", "No Se Puede Hacer Ese Reporte PorQue No Se Encontro El Edificio "+Edificio);
            return new Queue("");
        }
        Salon S = Edi.BuscarSalon(NumSalon);
        if(S==null){
            this.Mensaje("No Existe Ese Salon", "No Se Puede Hacer Ese Reporte PorQue No Se Encontro El Salon "+NumSalon);
            return new Queue("");
        }else{
            return R.CursosEnCiertoSalon(S, HorarioArbol);
        }
    }
    public Queue ReporteEstudiantesAsignadosAUnCurso(int CodCurso){
        Reportes R = new Reportes();
        Curso S = (Curso) this.CursoLST.Find(CodCurso);
        if(S==null){
            this.Mensaje("No Existe Ese Curso", "No Se Puede Hacer Ese Reporte PorQue No Se Encontro El Curso "+CodCurso);
            return new Queue("");
        }else{
            return R.EstudiantesAsignadosAUnCurso(S, HorarioArbol);
        }
    }
      public void abrirarchivo(String Nombre){

     try {

            File objetofile = new File (Path()+Nombre);
            Desktop.getDesktop().open(objetofile);

     }catch (IOException ex) {

            System.out.println(ex);

     }

}  
    public String Path(){
        final File f = new File(Maincito.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        return (f.toString()+"\\");
    }
    public void CrearArchivo(String Entrada, String Nombre) throws IOException {
        System.out.println(Path());
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(Path()  + Nombre + ".txt"), "utf-8"))) {
            writer.write(Entrada);
        }
    }
    public void Graphviz(String Entrada,String Nombre){
        
        String Comando="dot -Tpng "+Path()+Nombre+".txt -o "+Path()+Nombre+".png";
        try {
            CrearArchivo(Entrada,Nombre);
            Process p = Runtime.getRuntime().exec(Comando);
        } catch (IOException ex) {
            Mensaje("Error Al Generar Grafica","Error Del Sistema");
            Logger.getLogger(CargaMasiva.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public Queue ReporteCursosPorEstudiante(int Carnet){
        Reportes R = new Reportes();
        return R.CursosAsignadosPorEstudiantes(Carnet, getHorarioArbol());
    }
    public Queue ReporteHorariosExistentes(){
        Reportes R = new Reportes();
        return R.HorariosExistentes(HorarioArbol);
    }
    public Queue ReporteAsignacionesExistentes(){
        Reportes R = new Reportes();
        return R.ReporteAsignacionesTodas(HorarioArbol);
    }
    
    public Usuario Login(String Nombre,String Contraseña){
        Node Auxiliar= this.getUsuarioLST().getHead();
        while(Auxiliar!=null){
            Usuario Usr=(Usuario)Auxiliar.getContenido();
            if(Nombre.trim().toLowerCase().equals(""+Usr.getCodigo()) || Nombre.trim().toLowerCase().equals(Usr.getNombre().trim())){
                if(Contraseña.toLowerCase().trim().equals(Usr.getContraseña().toLowerCase().trim())){
                    return Usr;
                }
            }
           Auxiliar=Auxiliar.getNext();
        }
        return null;
    }
    
    public String GraficarEdificiosYSalones(){
        return "digraph G{ \n node[shape=\"box\"];"+this.getGraph().GraficarEdificiosYSalones(this.getEdificioLST().getHead())+"}";
    }
    public String GraficarCurso(){
        return "digraph G{ \n node[shape=\"box\"];"+this.getGraph().GraficarCurso(this.getCursoLST().getHead())+"}";
    }
    public String GraficarCatedratico(){
        return "digraph G{ \n node[shape=\"box\"];"+this.getGraph().GraficarCatedraticos(this.getCatedraticoArbol())+"}";
    }
    public String GraficarHorarioTodo(){
        return "digraph G{ \n node[shape=\"box\"];"+this.getGraph().GraficarHorariosTodo(getHorarioArbol(),this.getEdificioLST().getHead(), this.getCatedraticoArbol(), this.getCursoLST().getHead())+"}";
    }
    public String GraficarTodo(){
        String Salida="";
        Salida="digraph G{ rankdir=LR;  \n node[shape=\"box\"];"+this.getGraph().GraficarTodo(getHorarioArbol(),this.getEdificioLST().getHead(), this.getCatedraticoArbol(), this.getCursoLST().getHead())+"\n";
        Salida+="subgraph GE6{ \n node[shape=\"record\"];"+this.getGraph().GraficarEstudiantes(this.getEstudianteHash().getHash())+"}";
        Salida+="}";
        return Salida;
    }
    public String GraficarUsuarios(){
        return "digraph G{ \n node[shape=\"box\"];"+this.getGraph().GraficarUsuarios(this.getUsuarioLST().getHead())+"}";
    }
    public String GraficarEstudiantes(){
        return "digraph G{ \n node[shape=\"record\"];"+this.getGraph().GraficarEstudiantes(this.getEstudianteHash().getHash())+"}";
    }
    public String GraficarHorarios(){
        return "digraph G{ \n node[shape=\"box\"];"+this.getGraph().GraficarHorarios(this.getHorarioArbol())+"\n}";
    }
    ListaDoble StringA= new ListaDoble();
    public void Cargar(String Ingreso){
        Ingreso=Ingreso.replace("\n", "");
        Ingreso=Ingreso.replace(((char)9)+"", "");
        Ingreso=Ingreso.replace(" ", "");
        String Carga[]=Ingreso.split("\\;");
        for(int i=0;i<Carga.length;i++){
            //IdentificarElTipo
            Carga[i]=Carga[i].replace(")", "");
            Carga[i]=Carga[i].replace("", "");
            String Tipo[]=Carga[i].split("\\(");
            String ConteCarga=Tipo[1].toLowerCase().trim().replace("\"", "").replace("“", "").replace("”", "");
            switch (Tipo[0].toLowerCase().trim()) {
                case "usuario":
                    this.CargaUsuario(ConteCarga);
                    break;
                case "estudiante":
                    this.CargaEstudiante(ConteCarga);
                    break;
                case "catedratico":
                    this.CargaCatedratico(ConteCarga);
                    break;
                case "edificio":
                    this.CargaEdificio(ConteCarga);
                    break;
                case "salon":
                    this.CargaSalon(ConteCarga);
                    break;
                case "curso":
                    this.CargaCurso(ConteCarga);
                    break;
                case "horario":
                    
                    this.StringA.Add(Num(ConteCarga.split("\\,")[0]), ConteCarga);
                    //this.CargarHorario(ConteCarga);
                    break;
                case "asignar":
                    Descarga();
                    this.CargaAsignacion(ConteCarga);
                    break;
                default:
                    System.out.println("Imposible   "+Tipo[0].toLowerCase());
                    break;

            }
            
        }
        
    }
    private void Descarga(){
        Node Nodito = this.StringA.getHead();
        if(Nodito==null)
            return;
        while(Nodito!=null){
            this.CargarHorario((String)Nodito.getContenido());
            Nodito=Nodito.getNext();
        }
        this.StringA=new ListaDoble();
    }
    private void Mensaje(String Titulo,String Contenido){
        JOptionPane.showMessageDialog(null, Contenido, Titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    private int Num(String Ingreso){
        Ingreso=Ingreso.trim();
        try{
            return Integer.parseInt(Ingreso);
        }catch(Exception S){
            System.out.println(Ingreso+" NoNUM");
            Mensaje("No Es Un Numero","Se Ingreso Una Cadena Que No Contiene Valor Numerico");
            return 0;
        }
        
    }
    public void CargaAsignacion(String Ingreso){
        Ingreso=Ingreso.toLowerCase();
        String[] Spl= Ingreso.split("\\,");
        Estudiante Estu= (Estudiante) this.getEstudianteHash().Buscar(Num(Spl[0]));
        if(Estu==null){
            this.Mensaje("No Encontrado", "No Se Encontro Al Estudiante "+Spl[0]);
            return;
        }
        Horario Hora= (Horario) this.getHorarioArbol().Buscar(Num(Spl[1]));
        if(Hora==null){
            this.Mensaje("No Encontrado", "No Se Encontro El Horario"+Spl[1]);
            return;
        }
        Asignar Nueva = new Asignar(Estu,Num(Spl[2]),Num(Spl[3]));
        if(Hora.getListaAsignados().Find(Nueva.SubCod())==null){
            Hora.getListaAsignados().Add(Nueva.SubCod(), Nueva);
        }else{
            this.Mensaje("Ya Asignado", "El estudiante "+Spl[0]+" ya esta asignado Al Curso " +Hora.getCodCurso().getCodigo());
            return; 
        }
    }
    public void CargaUsuario(String Ingreso){
        Ingreso=Ingreso.toLowerCase();
        String[] Spl= Ingreso.split("\\,");
        
        if(Spl[3].trim().equals("estudiante")){
            Estudiante EstCreado= (Estudiante) this.getEstudianteHash().Buscar(Num(Spl[0]));
            EstCreado.setPlataforma(true);
            if(EstCreado!=null){
                Usuario UsrNuevo = new Usuario(EstCreado,Spl[1],Spl[2],Spl[3]);
                if(this.getUsuarioLST().Find(UsrNuevo.getCodigo())!=null){
                    this.Mensaje( "Ya Existe","Ya hay un usuario con el codigo "+Spl[0]);
                    return;
                }
                this.getUsuarioLST().Add(UsrNuevo.getCodigo(), UsrNuevo);
            }else{
                this.Mensaje("No Hay Estudiante Que Corresponda Al Carnet "+Spl[0], "No Se Puede Crear El Usuario");
            } 
            
        }else{
            Usuario UsrNuevo = new Usuario(Num(Spl[0]),Spl[1],Spl[2],Spl[3]);
            if(this.getUsuarioLST().Find(UsrNuevo.getCodigo())!=null){
                    this.Mensaje( "Ya Existe","Ya hay un usuario con el codigo "+Spl[0]);
                    return;
                }
            this.getUsuarioLST().Add(UsrNuevo.getCodigo(), UsrNuevo);
        }
    }
    public  void CargarHorario(String Ingreso){
        String[] Spl= Ingreso.split("\\,");
        Curso CursoCreado= (Curso)this.getCursoLST().Find(Num(Spl[3]));
        if(CursoCreado==null){
            this.Mensaje("No Existe", "El Curso "+Spl[3]+" No Fue Encontrado");
            return;
        }
        Edificio EdificioCreado= (Edificio)this.getEdificioLST().Find(Spl[5]);
        Salon SalonCreado=null;
        if(EdificioCreado==null){
            this.Mensaje("No Existe", "El Edificio "+Spl[5]+" No Fue Encontrado");
            return;
        }else{
            SalonCreado=EdificioCreado.BuscarSalon(Num(Spl[4]));
        }
        
        Catedratico CatedraticoCreado= (Catedratico)this.getCatedraticoArbol().Buscar(Num(Spl[6]));
        if(CatedraticoCreado==null){
            this.Mensaje("No Existe", "El Catedratico "+Spl[6]+" No Fue Encontrado");
            return;
        }
        
        Horario NuevoHora = (Horario)this.getHorarioArbol().Buscar(Num(Spl[0]));
        if(NuevoHora==null){
            NuevoHora= new Horario(Num(Spl[0]),Spl[1],Spl[2],CursoCreado,SalonCreado,EdificioCreado,CatedraticoCreado);
            this.getHorarioArbol().Agregar(NuevoHora.getCodigo(), NuevoHora);
        }else{
            this.Mensaje("Horario Ya Existe", "Este Horario Ya Fue Agregado");
        }
    }
    public  void CargaCurso(String Ingreso){
        String[] Spl= Ingreso.split("\\,");
        Curso NuevoCur= new Curso(Num(Spl[0]),Spl[1],Num(Spl[2]),Num(Spl[3]));
        if(this.getCursoLST().Find(Num(Spl[0]))==null)
            this.getCursoLST().Add(Num(Spl[0]), NuevoCur);
        else{
             this.Mensaje("Ya Existe", "El Curso "+Spl[0]+" Ya Fue Añadido");
            return;
        }
    }
    public  void CargaSalon(String Ingreso){
        String[] Spl= Ingreso.split("\\,");
        Edificio Creado= (Edificio)this.getEdificioLST().Find(Spl[0].trim());
        if(Creado==null){
            Mensaje("No Existe","No Se pudo encontrar el edificio "+Spl[0].trim()+  " para cargar el salon");
        }else{
            Salon NuevoSalon= new Salon(Creado,Num(Spl[1]), Num(Spl[2]));
            Creado.CargarSalon(NuevoSalon);
        }
    }
    public  void CargaEdificio(String Ingreso){
        Edificio NuevoEdi= new Edificio(Ingreso.trim());
        if(this.getEdificioLST().Find(Ingreso)==null){
            this.getEdificioLST().Add(Ingreso.trim(),NuevoEdi);
         }
        else{
             this.Mensaje("Ya Existe", "El Edificio "+Ingreso+" Ya Fue Añadido");
            return;
        }
    }
    public  void CargaEstudiante(String Ingreso){
       String[] Spl= Ingreso.split("\\,");
       Estudiante NuevoEstu= new Estudiante(Num(Spl[0]),Spl[1],Spl[2]);
       if(this.getEstudianteHash().Buscar(Num(Spl[0]))==null)
           this.getEstudianteHash().Agregar(Num(Spl[0]), NuevoEstu);
        else{
             this.Mensaje("Ya Existe", "El Estudiante "+Spl[0]+" Ya Fue Añadido");
            return;
        }
    }
    public  void CargaCatedratico(String Ingreso){
       String[] Spl= Ingreso.split("\\,");
       Catedratico NuevoCate= new Catedratico(Num(Spl[0]),Spl[1],Spl[2]);
       if(this.getCatedraticoArbol().Buscar(Num(Spl[0]))==null)
           this.getCatedraticoArbol().Agregar(Num(Spl[0]), NuevoCate);
        else{
             this.Mensaje("Ya Existe", "El Catedratico "+Spl[0]+" Ya Fue Añadido");
            return;
        }
    }
    public void BorrarEstudianteDelB(Estudiante Borrar){
        if(this.CatedraticoArbol.Raiz==null)
            return;
        this.BorrarEstudianteDelB(this.HorarioArbol.Raiz, Borrar);
    }
    private void BorrarEstudianteDelB(NodoB[] Raiz,Estudiante Borrar){
        if (Raiz == null) {
            return ;
        }
        for (int i = 0; i < Raiz.length; i++) {
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                Hora.BorrarAsignacionEstudiante(Borrar);
                BorrarEstudianteDelB(Raiz[i].getPagIzq(),Borrar);
                if (Raiz[i + 1] == null) {
                    BorrarEstudianteDelB(Raiz[i].getPagDer(),Borrar);
                }
            } 
        }
        return ;
    }
    public void BorrarCatedraticoDelB(Catedratico Borrar){
        if(this.CatedraticoArbol.Raiz==null)
            return;
        this.BorrarCatedraticoDelB(this.HorarioArbol.Raiz, Borrar);
    }
    private void BorrarCatedraticoDelB(NodoB[] Raiz,Catedratico Borrar){
        if (Raiz == null) {
            return ;
        }
        for (int i = 0; i < Raiz.length; i++) {
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                if(Hora.getNumIdentificacion()==Borrar){
                    Hora.setNumIdentificacion(new Catedratico(0,"Staff","Staff"));
                }
                BorrarCatedraticoDelB(Raiz[i].getPagIzq(),Borrar);
                if (Raiz[i + 1] == null) {
                    BorrarCatedraticoDelB(Raiz[i].getPagDer(),Borrar);
                }
            } 
        }
        return ;
    }
    public void BorrarCursoDelB(Curso Borrar){
        if(this.CatedraticoArbol.Raiz==null)
            return;
        this.BorrarCursoDelB(this.HorarioArbol.Raiz, Borrar);
    }
    private void BorrarCursoDelB(NodoB[] Raiz,Curso Borrar){
        if (Raiz == null) {
            return ;
        }
        for (int i = 0; i < Raiz.length; i++) {
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                if(Hora.getCodCurso()==Borrar){
                    Hora.setCodCurso(new Curso(0,"No Tiene Asignado Curso",0,0));
                }
                BorrarCursoDelB(Raiz[i].getPagIzq(),Borrar);
                if (Raiz[i + 1] == null) {
                    BorrarCursoDelB(Raiz[i].getPagDer(),Borrar);
                }
            } 
        }
        return ;
    }
    public void BorrarSalonDelB(Salon Borrar){
        if(this.CatedraticoArbol.Raiz==null)
            return;
        this.BorrarSalonDelB(this.HorarioArbol.Raiz, Borrar);
    }
    private void BorrarSalonDelB(NodoB[] Raiz,Salon Borrar){
        if (Raiz == null) {
            return ;
        }
        for (int i = 0; i < Raiz.length; i++) {
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                if(Hora.getCodSalon()==Borrar){
                    Hora.setCodSalon(new Salon(new Edificio("Se Elimino El Salon"),0,0));
                }
                BorrarSalonDelB(Raiz[i].getPagIzq(),Borrar);
                if (Raiz[i + 1] == null) {
                    BorrarSalonDelB(Raiz[i].getPagDer(),Borrar);
                }
            } 
        }
        return ;
    }
    /**
     * @return the UsuarioLST
     */
    public ListaDoble getUsuarioLST() {
        return UsuarioLST;
    }

    /**
     * @param UsuarioLST the UsuarioLST to set
     */
    public void setUsuarioLST(ListaDoble UsuarioLST) {
        this.UsuarioLST = UsuarioLST;
    }

    /**
     * @return the EdificioLST
     */
    public ListaCircular getEdificioLST() {
        return EdificioLST;
    }

    /**
     * @param EdificioLST the EdificioLST to set
     */
    public void setEdificioLST(ListaCircular EdificioLST) {
        this.EdificioLST = EdificioLST;
    }

    /**
     * @return the CursoLST
     */
    public ListaSimple getCursoLST() {
        return CursoLST;
    }

    /**
     * @param CursoLST the CursoLST to set
     */
    public void setCursoLST(ListaSimple CursoLST) {
        this.CursoLST = CursoLST;
    }

    /**
     * @return the EstudianteHash
     */
    public Hashtable getEstudianteHash() {
        return EstudianteHash;
    }

    /**
     * @param EstudianteHash the EstudianteHash to set
     */
    public void setEstudianteHash(Hashtable EstudianteHash) {
        this.EstudianteHash = EstudianteHash;
    }

    /**
     * @return the CatedraticoArbol
     */
    public ArbolAVL getCatedraticoArbol() {
        return CatedraticoArbol;
    }

    /**
     * @param CatedraticoArbol the CatedraticoArbol to set
     */
    public void setCatedraticoArbol(ArbolAVL CatedraticoArbol) {
        this.CatedraticoArbol = CatedraticoArbol;
    }

    /**
     * @return the HorarioArbol
     */
    public ArbolB getHorarioArbol() {
        return HorarioArbol;
    }

    /**
     * @param HorarioArbol the HorarioArbol to set
     */
    public void setHorarioArbol(ArbolB HorarioArbol) {
        this.HorarioArbol = HorarioArbol;
    }

    /**
     * @return the Graph
     */
    public Graficar getGraph() {
        return Graph;
    }

    /**
     * @param Graph the Graph to set
     */
    public void setGraph(Graficar Graph) {
        this.Graph = Graph;
    }
    
    
}
