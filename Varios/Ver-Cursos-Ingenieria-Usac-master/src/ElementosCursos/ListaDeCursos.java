/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ElementosCursos;

/**
 *
 * @author ENK
 */
public class ListaDeCursos {

    private Cursos Primero;
    private int Creditos = 0;

    public void Creditos(int Creditos) {
        this.Creditos = this.Creditos + Creditos;
    }

    public int Creditos() {
        return this.Creditos;
    }

    public void Añadir(Cursos Curso) {
        if (Primero == null) {
            Primero = Curso;
            Primero.SigCurso(null);
            Primero.AntCurso(null);
        } else {
            Cursos Actual = Primero;
            while (Actual.SigCurso() != null) {

                Actual = Actual.SigCurso();
                if (Actual.SigCurso() == Primero) {
                    break;
                }
            }

            //  System.out.println(Curso.Nombre());
            Curso.AntCurso(Actual);
            Curso.SigCurso(null);

            Actual.SigCurso(Curso);
            Primero.AntCurso(Curso);
        }
    }

    public void Sacar(String Codigo) {
        Cursos Actual = Primero;

        while (true) {

            Actual = Actual.SigCurso();

            if (Actual.Codigo().equals(Codigo)) {

                Cursos Anterior = Actual.AntCurso();
                Cursos Siguiente = Actual.SigCurso();

                Anterior.SigCurso(Siguiente);
                Siguiente.AntCurso(Anterior);

                if (Codigo.equals(Primero.Codigo())) {
                    Primero = Siguiente;
                }

                System.out.println("Borrar " + Actual.Nombre());
                break;

            } else if (Actual == Primero) {
                break;
            }

        }

    }

    public void Imprimir() {
        Cursos Actual = Primero;
        while (Actual != null) {

            Adorno(Actual);

            Actual = Actual.SigCurso();
        }
    }

    public Cursos get(Cursos Curso) {

        if (Curso == null) {

            return Primero;

        } else {
            return Curso.SigPre();
        }

    }

    public Cursos Mostrar(Cursos Actual) {

        if (Primero == null) {

            return null;
        } else {
            if (Actual == null) {
                return Primero;
            } else {
                if (Actual == Primero) {
                    return null;
                } else {

                    return Actual.SigPre();
                }
            }

        }
    }

    private Cursos SubComparar(ListaDeCursos Aprobados, Cursos CursoPensum) {


        boolean Llevar = false;
        Cursos ActualPre = CursoPensum.Pre().get(null);
        while (ActualPre != null) {
            Cursos ActualApro = Aprobados.get(null);
            while (ActualApro != null) {
                if (ActualPre.Codigo().equals(ActualApro.Codigo())) {
                    Llevar = true;
                    break;
                }
                ActualApro = ActualApro.SigCurso();
            }
            if(Llevar==false){System.out.println("????????"); 
           // return null;
            }
            ActualPre = CursoPensum.Pre().Siguiente(ActualPre);
        }
        if (Llevar == true) {
            return CursoPensum;
        } else {
            return null;
        }
    }

    public Object[] Comparar(ListaDeCursos Aprobados, ListaDeCursos Pensum) {
        int Veces=0;
        Cursos C33CR = new Cursos("33CR", "Creditos necesarios 33", 3);
        Cursos C90CR = new Cursos("90CR", "Creditos necesarios 90", 3);
        Cursos C150CR = new Cursos("150CR", "Creditos necesarios 150", 3);
        Cursos C170CR = new Cursos("170CR", "Creditos necesarios 170", 3);
        Cursos C190CR = new Cursos("190CR", "Creditos necesarios 190", 3);
        Cursos C200CR = new Cursos("200CR", "Creditos necesarios 200", 3);
        Cursos C225CR = new Cursos("225CR", "Creditos necesarios 225", 3);
        Cursos C220CR = new Cursos("220CR", "Creditos necesarios 220", 3);
        ListaDeCursos CursosPosibles = new ListaDeCursos();
        ListaDeCursos CursosGanados = new ListaDeCursos();

        Cursos Actual = Primero;
        while (Actual != null) {
            boolean Ver = false;
            Cursos ActualApro = Aprobados.get(null);
            
            if(CursosGanados.Creditos()>33 && Veces==0){Veces=Veces+1;  Aprobados.Añadir(C33CR);}else
            if(CursosGanados.Creditos()>90 && Veces==1){Veces=Veces+1;  Aprobados.Añadir(C90CR);}else
            if(CursosGanados.Creditos()>150 && Veces==2){Veces=Veces+1;  Aprobados.Añadir(C150CR);}else
            if(CursosGanados.Creditos()>170 && Veces==3){Veces=Veces+1;  Aprobados.Añadir(C170CR);}else
            if(CursosGanados.Creditos()>190 && Veces==4){Veces=Veces+1;  Aprobados.Añadir(C190CR);}else
            if(CursosGanados.Creditos()>200 && Veces==5){Veces=Veces+1;  Aprobados.Añadir(C200CR);}else
            if(CursosGanados.Creditos()>225 && Veces==6){Veces=Veces+1;  Aprobados.Añadir(C225CR);}else
            if(CursosGanados.Creditos()>220 && Veces==7){Veces=Veces+1;  Aprobados.Añadir(C220CR);}else{}

                
                
                
            System.out.println(CursosGanados.Creditos());

            while (ActualApro != null) {
                if (Actual.Codigo().equals(ActualApro.Codigo())) {
                    Cursos nuevo = new Cursos(Actual.Codigo(), Actual.Nombre(), Actual.Creditos());
                    nuevo.ActualizarPre(Actual.Pre());
                    CursosGanados.Añadir(nuevo);
                    CursosGanados.Creditos(nuevo.Creditos());
         //** System.out.println(CursosGanados.Creditos());
                    //System.out.println(" sssaqwpp "+nuevo.Nombre());
                    Ver = true;
                    break;
                }
                ActualApro = ActualApro.SigCurso();
            }
            if (Ver == false) {
                Cursos Apr = SubComparar(Aprobados, Actual);
                if (Apr != null) {
                    Cursos Nuevo = new Cursos(Apr.Codigo(), Apr.Nombre(), Apr.Creditos());
                    Nuevo.ActualizarPre(Apr.Pre());
                    CursosPosibles.Añadir(Nuevo);

                }
            }

            Actual = Actual.SigCurso();
        }

        Object[] Objeto = new Object[2];
        Objeto[0] = CursosPosibles;
        Objeto[1] = CursosGanados;

        return Objeto;

    }

    private void Adorno(Cursos Actual) {
        System.out.println("-----------Curso------------------");

        System.out.println(Actual.Codigo() + "   " + Actual.Nombre());

        System.out.println("-----------Pre Requisitos------------------");
        Actual.Pre().Imprimir();
    }
}
