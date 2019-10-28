/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.Lineales.ListaSimpleSTR;
import Estructuras.Lineales.Queue;
import Nodos.NodoSTR;

/**
 *
 * @author lizandro
 */
public class Horario {
    private int Codigo;
    private Salon CodSalon;
    private Curso CodCurso;
    private String Dia;
    private Edificio CodEdificio;
    private Catedratico NumIdentificacion;
    private String Periodo;
    private ListaSimpleSTR ListaAsignados;
    public Horario(int Codigo,String Periodo,String Dia,Curso CodCurso, Salon CodSalon, Edificio CodEdificio,Catedratico NumIdentificacion){
        this.Codigo=Codigo;
        this.CodSalon=CodSalon;
        this.CodCurso=CodCurso;
        this.CodEdificio=CodEdificio;
        this.Dia=Dia;
        this.NumIdentificacion=NumIdentificacion;
        this.Periodo=Periodo;
        this.ListaAsignados= new ListaSimpleSTR();
    }
    /**
     * @return the Codigo
     */
    @Override
    public String toString(){
        return "HOR"+this.getCodigo();
    }
    public String GenerarSalones(){
        return this.GenerarAsignados(this.ListaAsignados.getHead());
    }
    public void BorrarAsignacionEstudiante(Estudiante BorrarEstu){
        NodoSTR Head= this.ListaAsignados.getHead();
        String Borrar="";
        if (Head != null) {
            NodoSTR TempNode = Head;
            while (TempNode.getNext() != null) {
                Asignar Asig = (Asignar) TempNode.getContenido();
                if(Asig.getAsigEstudiante()==BorrarEstu){
                   Borrar= Asig.SubCod();
                   break;
                }
                TempNode = TempNode.getNext();
            }
            Asignar Asig = (Asignar) TempNode.getContenido();
            if(Asig.getAsigEstudiante()==BorrarEstu)
                    Borrar= Asig.SubCod();
            this.ListaAsignados.Delete(Borrar);
        }
    }
    public Queue AgregarAsignaciones(Queue Cola){
        NodoSTR Head= this.ListaAsignados.getHead();
        if (Head != null) {
            NodoSTR TempNode = Head;
            while (TempNode.getNext() != null) {
                Asignar Asig = (Asignar) TempNode.getContenido();
                Cola.Enqueue(Asig);
                TempNode = TempNode.getNext();
            }
            Asignar Asig = (Asignar) TempNode.getContenido();
            Cola.Enqueue(Asig);
            return Cola;
        }
        return Cola;
    }
    public Queue AgregarEstudiantesQueReprobaron(Queue Cola){
        NodoSTR Head= this.ListaAsignados.getHead();
        if (Head != null) {
            NodoSTR TempNode = Head;
            while (TempNode.getNext() != null) {
                Asignar Asig = (Asignar) TempNode.getContenido();
                if(Asig.getZona()+Asig.getFinal()<61)
                Cola.Enqueue(Asig);
                TempNode = TempNode.getNext();
            }
            Asignar Asig = (Asignar) TempNode.getContenido();
            if(Asig.getFinal()+Asig.getZona()<61)
            Cola.Enqueue(Asig);
            return Cola;
        }
        return Cola;
    }
    public Queue AgregarEstudiantesQueAprobaron(Queue Cola){
        NodoSTR Head= this.ListaAsignados.getHead();
        if (Head != null) {
            NodoSTR TempNode = Head;
            while (TempNode.getNext() != null) {
                Asignar Asig = (Asignar) TempNode.getContenido();
                if(Asig.getZona()+Asig.getFinal()>60)
                Cola.Enqueue(Asig);
                TempNode = TempNode.getNext();
            }
            Asignar Asig = (Asignar) TempNode.getContenido();
            if(Asig.getZona() +Asig.getFinal()>60)
            Cola.Enqueue(Asig);
            return Cola;
        }
        return Cola;
    }
    public Queue AgregarEstudiantesQueRecibenElCurso(Queue Cola){
        NodoSTR Head= this.ListaAsignados.getHead();
        if (Head != null) {
            NodoSTR TempNode = Head;
            while (TempNode.getNext() != null) {
               
                Asignar Asig = (Asignar) TempNode.getContenido();
                Cola.Enqueue(Asig);
                TempNode = TempNode.getNext();
            }
            Asignar Asig = (Asignar) TempNode.getContenido();
            Cola.Enqueue(Asig);
            TempNode = TempNode.getNext();
            return Cola;
        }
        return Cola;
    }
    public Asignar BuscarAsignacionEstudiante(int Carnet){
        NodoSTR Head= this.ListaAsignados.getHead();
        if (Head != null) {
            NodoSTR TempNode = Head;
            while (TempNode.getNext() != null) {
                Asignar Asig = (Asignar) TempNode.getContenido();
                if(Asig.getAsigEstudiante().getCarnet()==Carnet)
                    return Asig;
                TempNode = TempNode.getNext();
            }
            Asignar Asig = (Asignar) TempNode.getContenido();
            if(Asig.getAsigEstudiante().getCarnet()==Carnet)
                    return Asig;
        }
        return null;
    }
    private String GenerarAsignados(NodoSTR Head){
        if(Head!=null){
            NodoSTR TempNode=Head;
            String grafo="\n";
            grafo+=this.toString()+ " -> "+TempNode.toString()+"\n";
            grafo += "subgraph cluster_sub"+this.toString()+"{ \nstyle=filled;color=lightgrey; ";
            //grafo += "node[shape=\"box\"];";
            
            while(TempNode.getNext()!=null){
                Asignar Asig = (Asignar)TempNode.getContenido();
                grafo += TempNode.toString()+"[label=\" " + Asig.Graph()+ " \"];\n";
                grafo += TempNode.toString()+"->"+TempNode.getNext().toString()+"\n";
                grafo += Asig.toString()+"-> Hash:"+Asig.getAsigEstudiante().toString()+"\n";
                if(TempNode.getBack()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getBack().toString()+"\n";
                TempNode=TempNode.getNext();
            }
            Asignar Asig = (Asignar)TempNode.getContenido();
            if(TempNode.getBack()!=null)
            grafo += TempNode.toString()+" -> "+TempNode.getBack().toString()+"\n";
            grafo += TempNode.toString().replace(".", "")+"[label=\" " +  Asig.Graph()+ " \"];\n";
            grafo += Asig.toString()+"-> Hash:"+Asig.getAsigEstudiante().toString()+"\n";
            grafo += "}";
            return grafo;
        }
        return "";
    }
    public String Graph(){
        return "Codigo "+this.getCodigo()+" Hora "+this.getPeriodo().trim();
    }
    public int getCodigo() {
        return Codigo;
    }

    /**
     * @param Codigo the Codigo to set
     */
    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
    }

    /**
     * @return the CodSalon
     */
    public Salon getCodSalon() {
        return CodSalon;
    }

    /**
     * @param CodSalon the CodSalon to set
     */
    public void setCodSalon(Salon CodSalon) {
        this.CodSalon = CodSalon;
    }

    /**
     * @return the CodCurso
     */
    public Curso getCodCurso() {
        return CodCurso;
    }

    /**
     * @param CodCurso the CodCurso to set
     */
    public void setCodCurso(Curso CodCurso) {
        this.CodCurso = CodCurso;
    }

    /**
     * @return the CodEdificio
     */
    public Edificio getCodEdificio() {
        return CodEdificio;
    }

    /**
     * @param CodEdificio the CodEdificio to set
     */
    public void setCodEdificio(Edificio CodEdificio) {
        this.CodEdificio = CodEdificio;
    }

    /**
     * @return the NumIdentificacion
     */
    public Catedratico getNumIdentificacion() {
        return NumIdentificacion;
    }

    /**
     * @param NumIdentificacion the NumIdentificacion to set
     */
    public void setNumIdentificacion(Catedratico NumIdentificacion) {
        this.NumIdentificacion = NumIdentificacion;
    }

    /**
     * @return the Periodo
     */
    public String getPeriodo() {
        return Periodo;
    }

    /**
     * @param Periodo the Periodo to set
     */
    public void setPeriodo(String Periodo) {
        this.Periodo = Periodo;
    }

    /**
     * @return the Dia
     */
    public String getDia() {
        return Dia;
    }

    /**
     * @param Dia the Dia to set
     */
    public void setDia(String Dia) {
        this.Dia = Dia;
    }

    /**
     * @return the ListaAsignados
     */
    public ListaSimpleSTR getListaAsignados() {
        return ListaAsignados;
    }

    /**
     * @param ListaAsignados the ListaAsignados to set
     */
    public void setListaAsignados(ListaSimpleSTR ListaAsignados) {
        this.ListaAsignados = ListaAsignados;
    }
}
