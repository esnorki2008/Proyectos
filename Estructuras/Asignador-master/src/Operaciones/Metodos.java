/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import Estructuras.Lineales.Queue;
import Estructuras.NoLineales.MiniHash;
import Nodos.Node;
import Nodos.NodoAVL;
import Nodos.NodoBinario;
import Nodos.NodoSTR;
import Objetos.Asignar;
import Objetos.Catedratico;
import Objetos.Curso;
import Objetos.Edificio;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Salon;
import Objetos.Usuario;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lizandro
 */
public class Metodos {
    CargaMasiva Car;
    public Metodos(CargaMasiva Car){
        this.Car=Car;
    }
    public DefaultTableModel ABCSalonesExistentes(String Edificio){
         String data[][] = {};
        String col[] = {"Numero","Capacidad"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        NodoSTR Nodo = Car.getEdificioLST().getHead();
        if(Nodo==null)
            return model;
        do
        {
            Edificio Edi= (Edificio) Nodo.getContenido();
            if(Edi.getNombre().toLowerCase().trim().equals(Edificio.toLowerCase().trim()))
                return Edi.ABCModeloSalones(Edificio);
            Nodo=Nodo.getNext();
        }
        while(Nodo!=Car.getEdificioLST().getHead());
        
        if(!Edificio.equals(""))
           this.Mensaje("No Encontrado", "No existe el edificio "+Edificio+" en el programa");
        
        return model;
    }
    public DefaultTableModel ABCEdificiosExistentes(){
         String data[][] = {};
        String col[] = {"Nombre"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        NodoSTR Nodo = Car.getEdificioLST().getHead();
        if(Nodo==null)
            return model;
        do
        {
            Edificio Edi= (Edificio) Nodo.getContenido();
            Object Dita[] = {Edi.getNombre()};
            model.addRow(Dita);
            Nodo=Nodo.getNext();
        }
        while(Nodo!=Car.getEdificioLST().getHead());
        
        return model;
    }
    private void Mensaje(String Titulo,String Contenido){
        JOptionPane.showMessageDialog(null, Contenido, Titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    public DefaultTableModel ABCCursosRegistrados(){
         String data[][] = {};
        String col[] = {"Codigo", "Nombre", "Semestre","Creditos"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Node Nodo = Car.getCursoLST().getHead();
        while(Nodo!=null)
        {
            Curso Cur= (Curso) Nodo.getContenido();
            Object Dita[] = {Cur.getCodigo(),Cur.getNombre(),Cur.getSemestre(),Cur.getCreditos()};
            model.addRow(Dita);
            Nodo=Nodo.getNext();
        }
        return model;
    }
    public DefaultTableModel ABCUsuariosRegistrados(){
         String data[][] = {};
        String col[] = {"Codigo", "Nombre", "Contraseña","Tipo"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Node Nodo = Car.getUsuarioLST().getHead();
        while(Nodo!=null)
        {
            Usuario Usu= (Usuario) Nodo.getContenido();
            Object Dita[] = {Usu.getCodigo(),Usu.getNombre(),Usu.getContraseña(),Usu.getTipo()};
            model.addRow(Dita);
            Nodo=Nodo.getNext();
        }
        return model;
    }
    
    public DefaultTableModel ABCCatedraticosRegistrados(){
         String data[][] = {};
        String col[] = {"Carnet", "Nombre", "Direccion"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        
        return ABCCatedraticosRegistrados(this.Car.getCatedraticoArbol().Raiz,model);
    }
    public DefaultTableModel ABCCatedraticosRegistrados(NodoAVL Raiz,DefaultTableModel model){
         if(Raiz==null)
             return model;
         
         model=this.ABCCatedraticosRegistrados(Raiz.getIzq(), model);
         Catedratico Cate = (Catedratico) Raiz.getContenido();
         Object Dita[] = {Cate.getCarnet(), Cate.getNombre(), Cate.getDireccion()};
         model.addRow(Dita);
         model=this.ABCCatedraticosRegistrados(Raiz.getDer(), model);
         
         return model;
    }
    public DefaultTableModel ABCEstudiantesRegistrados(){
         String data[][] = {};
        String col[] = {"Carnet", "Nombre", "Direccion"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        MiniHash[] Hash=Car.getEstudianteHash().getHash();
        for (int i = 0; i < Hash.length; i++) {
            if (Hash[i] != null) {
                    Estudiante Est = (Estudiante) Hash[i].getContenido();
                    Object Dita[] = {Est.getCarnet(), Est.getNombre(), Est.getDireccion()};
                    model.addRow(Dita);
            }
        }
        return model;
    }
    public DefaultTableModel ABCUsuarioEstudiantesSinUsuario(){
         String data[][] = {};
        String col[] = {"Carnet", "Nombre", "Direccion"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        MiniHash[] Hash=Car.getEstudianteHash().getHash();
        for (int i = 0; i < Hash.length; i++) {
            if (Hash[i] != null) {
                Estudiante Est = (Estudiante) Hash[i].getContenido();
                if (Est.isPlataforma()==false) {
                    Object Dita[] = {Est.getCarnet(), Est.getNombre(), Est.getDireccion()};
                    model.addRow(Dita);
                    // model.ce
                }
            }
        }
        return model;
    }
    public DefaultTableModel AsignacionesDelSistema(){
         String data[][] = {};
        String col[] = {"Carnet Estudiante","Nombre Estudiante","Nota Del Curso","Edificio", "Numero Salon", "Codigo Curso","Nombre Curso","Semestre Curso","Codigo Catedratico","Nombre Catedratico","Periodo","Dia"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Queue Cola= Car.ReporteAsignacionesExistentes();
        while(Cola.Front()!=null){
            Horario Hora= (Horario)Cola.Dequeue();
            Edificio Edi = Hora.getCodEdificio();
            Salon Sal = Hora.getCodSalon();
            Curso Curse = Hora.getCodCurso();
            Catedratico Cate = Hora.getNumIdentificacion();
            Queue ColaEstudian =Hora.AgregarAsignaciones(new Queue(""));
            while(ColaEstudian.Front()!=null){
                 Asignar Asig = (Asignar) ColaEstudian.Dequeue();
                 Estudiante Est = Asig.getAsigEstudiante();
                 Object[] Dita={Est.getCarnet(),Est.getNombre(),Asig.getFinal()+Asig.getZona(),Edi.getNombre(),Sal.getNumero(),Curse.getCodigo(),Curse.getNombre(),Curse.getSemestre(),Cate.getCarnet(),Cate.getNombre(),Hora.getPeriodo(),Hora.getDia()};
                 if(Asig.getFinal()+Asig.getZona()>60)
                 model.addRow(Dita);
            }
        }
        return model;
    }
    
    //Reporte 4 En 2
    public DefaultTableModel AprobadosEnSemestre(int Semestre){
         String data[][] = {};
        String col[] = {"Carnet Estudiante","Nombre Estudiante","Nota Del Curso","Edificio", "Numero Salon", "Codigo Curso","Nombre Curso","Semestre Curso","Codigo Catedratico","Nombre Catedratico","Periodo","Dia"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Queue Cola= Car.ReporteEstudiantesAprobadosReprobadosEnSemestre(Semestre);
        while(Cola.Front()!=null){
            Horario Hora= (Horario)Cola.Dequeue();
            Edificio Edi = Hora.getCodEdificio();
            Salon Sal = Hora.getCodSalon();
            Curso Curse = Hora.getCodCurso();
            Catedratico Cate = Hora.getNumIdentificacion();
            Queue ColaEstudian = Hora.AgregarEstudiantesQueAprobaron(new Queue(""));
            while(ColaEstudian.Front()!=null){
                 Asignar Asig = (Asignar) ColaEstudian.Dequeue();
                 Estudiante Est = Asig.getAsigEstudiante();
                 Object[] Dita={Est.getCarnet(),Est.getNombre(),Asig.getFinal()+Asig.getZona(),Edi.getNombre(),Sal.getNumero(),Curse.getCodigo(),Curse.getNombre(),Curse.getSemestre(),Cate.getCarnet(),Cate.getNombre(),Hora.getPeriodo(),Hora.getDia()};
                 if(Asig.getFinal()+Asig.getZona()>60)
                 model.addRow(Dita);
            }
        }
        return model;
    }
    public DefaultTableModel ReprobadosEnSemestre(int Semestre){
         String data[][] = {};
        String col[] = {"Carnet Estudiante","Nombre Estudiante","Nota Del Curso","Edificio", "Numero Salon", "Codigo Curso","Nombre Curso","Semestre Curso","Codigo Catedratico","Nombre Catedratico","Periodo","Dia"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Queue Cola= Car.ReporteEstudiantesAprobadosReprobadosEnSemestre(Semestre);
        while(Cola.Front()!=null){
            Horario Hora= (Horario)Cola.Dequeue();
            Edificio Edi = Hora.getCodEdificio();
            Salon Sal = Hora.getCodSalon();
            Curso Curse = Hora.getCodCurso();
            Catedratico Cate = Hora.getNumIdentificacion();
            Queue ColaEstudian = Hora.AgregarEstudiantesQueReprobaron(new Queue(""));
            while(ColaEstudian.Front()!=null){
                System.out.println("Repro");
                 Asignar Asig = (Asignar) ColaEstudian.Dequeue();
                 Estudiante Est = Asig.getAsigEstudiante();
                 Object[] Dita={Est.getCarnet(),Est.getNombre(),Asig.getFinal()+Asig.getZona(),Edi.getNombre(),Sal.getNumero(),Curse.getCodigo(),Curse.getNombre(),Curse.getSemestre(),Cate.getCarnet(),Cate.getNombre(),Hora.getPeriodo(),Hora.getDia()};
                 if(Asig.getFinal()+Asig.getZona()<61)
                 model.addRow(Dita);
            }
        }
        return model;
    }
    //REPORTE3
    public DefaultTableModel CursosEnCiertoSalon(String Edificio,int NumSalon){
         String data[][] = {};
        String col[] = {"Edificio", "Numero Salon", "Codigo Curso","Nombre Curso","Semestre Curso","Codigo Catedratico","Nombre Catedratico","Periodo","Dia"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Queue Cola= Car.ReporteCursosEnCiertoSalon(Edificio, NumSalon);
        while(Cola.Front()!=null){
            Horario Creado= (Horario)Cola.Dequeue();
            Curso Curse=Creado.getCodCurso();
            Catedratico Cate = Creado.getNumIdentificacion();
            Object[] Dita={Edificio,NumSalon,Curse.getCodigo(),Curse.getNombre(),Curse.getSemestre(),Cate.getCarnet(),Cate.getNombre(),Creado.getPeriodo(),Creado.getDia()};
            model.addRow(Dita);
        }
        return model;
    }
    //REPORTE2
    public DefaultTableModel EstudiantesEnUnCurso(int Curso){
         String data[][] = {};
        String col[] = {"Carnet", "Nombre", "Zona","Final","Nota Del Curso","Estado"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Queue Cola= Car.ReporteEstudiantesAsignadosAUnCurso(Curso);
        while(Cola.Front()!=null){
            Asignar Asig =(Asignar)Cola.Dequeue();
            Estudiante Estu = Asig.getAsigEstudiante();
            String Estado="Reprobado";
            if(Asig.getFinal()+Asig.getZona()>60){
                Estado="Aprobado";
            }
            Object[] Dita={Estu.getCarnet(),Estu.getNombre(),Asig.getZona(),Asig.getFinal(),Asig.getFinal()+Asig.getZona(),Estado};
            model.addRow(Dita);
        }
        return model;
    }
    //REPORTE1
    public DefaultTableModel CursosAsignados(int Carnet){
         String data[][] = {};
        String col[] = {"Codigo", "Nombre", "Creditos","Semestre","Catedratico","Zona","Final","Estado"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Queue Cola= Car.ReporteCursosPorEstudiante(Carnet);
        while(Cola.Front()!=null){
            Horario Creado= (Horario)Cola.Dequeue();
            Curso Curse=Creado.getCodCurso();
            Catedratico Cate = Creado.getNumIdentificacion();
            Asignar Asig = Creado.BuscarAsignacionEstudiante(Carnet);
            String Estado="Reprobado";
            if(Asig.getFinal()+Asig.getZona()>60)
                Estado="Aprobado";
            String[] Dita={Curse.getCodigo()+"",Curse.getNombre(),Curse.getCreditos()+"",Curse.getSemestre()+"",Cate.getNombre(),Asig.getZona()+"",Asig.getFinal()+"",Estado};
            model.addRow(Dita);
        }
        return model;
    }
    public DefaultTableModel ABCHorariosExistentes(){
         String data[][] = {};
        String col[] = {"Codigo Horario", "Salon","Capacidad", "CodigoCurso","Nombre Curso","Creditos","Dia","Edificio","Catedratico","Periodo"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Queue Cola= Car.ReporteHorariosExistentes();
        while(Cola.Front()!=null){
            Horario Creado= (Horario)Cola.Dequeue();
            Curso Curse=Creado.getCodCurso();
            Salon Sal = Creado.getCodSalon();
            Edificio Edi= Creado.getCodEdificio();
            Catedratico Cate = Creado.getNumIdentificacion();
            Object[] Dita={Creado.getCodigo(),Sal.getNumero(),Sal.getCapacidad(),Curse.getCodigo(),Curse.getNombre(),Curse.getCreditos(),Creado.getDia(),Edi.getNombre(),Cate.getNombre(),Creado.getPeriodo()};
            model.addRow(Dita);
        }
        return model;
    }
}
