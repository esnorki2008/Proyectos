/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operaciones;

import Estructuras.Lineales.Queue;
import Estructuras.NoLineales.ArbolB;
import Nodos.NodoB;
import Objetos.Asignar;
import Objetos.Curso;
import Objetos.Estudiante;
import Objetos.Horario;
import Objetos.Salon;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author lizandro
 */
public class Reportes {
    public Queue ReporteAsignacionesTodas(ArbolB B){
        return ReporteAsignacionesTodas(B.Raiz,new Queue(""));
    }
                  
    private Queue ReporteAsignacionesTodas(NodoB[] Raiz,Queue Cola){
        if (Raiz == null) {
            return Cola;
        }
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                //Cola= Hora.AgregarEstudiantesQueReprobaron(Cola);
                Cola.Enqueue(Hora);
                //Cola= Hora.AgregarAsignaciones(Cola);
                Cola=ReporteAsignacionesTodas(Raiz[i].getPagIzq(),Cola);
                if (Raiz[i + 1] == null) {
                    Cola=ReporteAsignacionesTodas(Raiz[i].getPagDer(),Cola);
                }
            } 
        }
        return Cola;
    }
    public Queue EstudiantesAprobadosReprobadosEnSemestre(int Semestre,ArbolB B){
        return EstudiantesAprobadosReprobadosEnSemestre(B.Raiz,new Queue(""),Semestre);
    }
    private Queue EstudiantesAprobadosReprobadosEnSemestre(NodoB[] Raiz,Queue Cola,int Semestre){
        if (Raiz == null) {
            return Cola;
        }
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                if(Hora.getCodCurso().getSemestre()==Semestre)
                //Cola= Hora.AgregarEstudiantesQueReprobaron(Cola);
                Cola.Enqueue(Hora);
                Cola=EstudiantesAprobadosReprobadosEnSemestre(Raiz[i].getPagIzq(),Cola,Semestre);
                if (Raiz[i + 1] == null) {
                    Cola=EstudiantesAprobadosReprobadosEnSemestre(Raiz[i].getPagDer(),Cola,Semestre);
                }
            } 
        }
        return Cola;
    }
   
    public Queue CursosEnCiertoSalon(Salon Sal,ArbolB B){
        return CursosEnCiertoSalon(B.Raiz,new Queue(""),Sal);
    }
    private Queue CursosEnCiertoSalon(NodoB[] Raiz,Queue Cola,Salon Sal){
        if (Raiz == null) {
            return Cola;
        }
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                if(Hora.getCodSalon()==Sal)
                Cola.Enqueue(Hora);
                
                Cola=CursosEnCiertoSalon(Raiz[i].getPagIzq(),Cola,Sal);
                if (Raiz[i + 1] == null) {
                    Cola=CursosEnCiertoSalon(Raiz[i].getPagDer(),Cola,Sal);
                }
            } 
        }
        return Cola;
    }
    public Queue EstudiantesAsignadosAUnCurso(Curso CodCurso,ArbolB B){
        return EstudiantesAsignadosAUnCurso(B.Raiz,new Queue(""),CodCurso);
    }
    private Queue EstudiantesAsignadosAUnCurso(NodoB[] Raiz,Queue Cola,Curso CodCurso){
        if (Raiz == null) {
            return Cola;
        }
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                if(Hora.getCodCurso()==CodCurso){
                    Cola=Hora.AgregarEstudiantesQueRecibenElCurso(Cola);
                }
                Cola=EstudiantesAsignadosAUnCurso(Raiz[i].getPagIzq(),Cola,CodCurso);
                if (Raiz[i + 1] == null) {
                    Cola=EstudiantesAsignadosAUnCurso(Raiz[i].getPagDer(),Cola,CodCurso);
                }
            } 
        }
        return Cola;
    }
    public Queue CursosAsignadosPorEstudiantes(int Carnet,ArbolB B){
        return SubCursosAsignadosPorEstudiantes(B.Raiz,new Queue(""),Carnet);
    }
    private Queue SubCursosAsignadosPorEstudiantes(NodoB[] Raiz,Queue Cola,int Carnet){
        if (Raiz == null) {
            return Cola;
        }
        for (int i = 0; i < Raiz.length; i++) {
            
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                Asignar Asig=Hora.BuscarAsignacionEstudiante(Carnet);
                if(Asig!=null){
                    Cola.Enqueue(Hora);
                }
                Cola=SubCursosAsignadosPorEstudiantes(Raiz[i].getPagIzq(),Cola,Carnet);
                if (Raiz[i + 1] == null) {
                    Cola=SubCursosAsignadosPorEstudiantes(Raiz[i].getPagDer(),Cola,Carnet);
                }
            } 
        }
        return Cola;
    }
    public Queue HorariosExistentes(ArbolB B){
        return SubHorariosExistentes(B.Raiz,new Queue(""));
    }
    private Queue  SubHorariosExistentes(NodoB[] Raiz,Queue Cola){
        if (Raiz == null) {
            return Cola;
        }
        for (int i = 0; i < Raiz.length; i++) {
            if (Raiz[i] != null) {
                Horario Hora= (Horario)Raiz[i].getContenido();
                Cola=SubHorariosExistentes(Raiz[i].getPagIzq(),Cola);
                Cola.Enqueue(Hora);
                if (Raiz[i + 1] == null) {
                    Cola=SubHorariosExistentes(Raiz[i].getPagDer(),Cola);
                }
            } 
        }
        return Cola;
    }
}
