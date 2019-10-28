/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.Lineales.ListaSimple;
import Nodos.Node;
import Nodos.NodoSTR;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lizandro
 */
public class Edificio {
    private String Nombre;
    private ListaSimple SalonLST;
    public Edificio(String Nombre){
        this.Nombre=Nombre;
        SalonLST= new ListaSimple();
    }

    /**
     * @return the Nombre
     */
    
    public DefaultTableModel ABCModeloSalones(String Edificio){
         String data[][] = {};
        String col[] = {"Numero","Capacidad"};
        DefaultTableModel model = new DefaultTableModel(data, col);
        Node Nodo = this.SalonLST.getHead();
        if(Nodo==null)
            return model;
        do
        {
            Salon Sal= (Salon) Nodo.getContenido();
            Object Dita[] = {Sal.getNumero(),Sal.getCapacidad()};
            model.addRow(Dita);
            Nodo=Nodo.getNext();
        }
        while(Nodo!=null);
        
        return model;
    }
    public String toString(){
        return "EDI"+this.Nombre.trim();
    }
    public String Graph(){
        return "Edificio "+this.Nombre;
    }
    public String GenerarSalones(){
        return this.GenerarSalones(this.SalonLST.getHead());
    }
    private String GenerarSalones(Node Head){
        if(Head!=null){
            Node TempNode=Head;
            String grafo="\n";
            
            grafo+=this.toString()+ " -> "+TempNode.toString()+"\n";
            grafo += "subgraph cluster_sub"+this.toString()+"{ \nstyle=filled;color=lightgrey; ";
            //grafo += "node[shape=\"box\"];";
            
            while(TempNode.getNext()!=null){
                Salon Sal = (Salon)TempNode.getContenido();
                grafo += TempNode.toString()+"[label=\" " + Sal.Graph()+ " \"];\n";
                grafo += TempNode.toString()+"->"+TempNode.getNext().toString()+"\n";
                if(TempNode.getBack()!=null)
                grafo += TempNode.toString()+"->"+TempNode.getBack().toString()+"\n";
                TempNode=TempNode.getNext();
            }
            Salon Sal = (Salon)TempNode.getContenido();
            if(TempNode.getBack()!=null)
            grafo += TempNode.toString()+" -> "+TempNode.getBack().toString()+"\n";
            grafo += TempNode.toString().replace(".", "")+"[label=\" " +  Sal.Graph()+ " \"];\n";
            grafo += "}";
            return grafo;
        }
        return "";
    }
    public String getNombre() {
        return Nombre;
    }
    public void CargarSalon(Salon Nuevo){
        Object Prueba=this.SalonLST.Find(Nuevo.getNumero());
        if(Prueba==null){
            this.SalonLST.Add(Nuevo.getNumero(), Nuevo);
        }else{
          JOptionPane.showMessageDialog(null, "Este Salón " + Nuevo.getNumero()+" Ya Fue Añadido Al Edificio", "Salon Ya Existe", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public Salon BuscarSalon(int ID){
        Salon Prueba=(Salon)this.SalonLST.Find(ID);
        if(Prueba==null){
            JOptionPane.showMessageDialog(null, "Este Salón " + ID+" No Existe", "No Existe En Este Edificio "+this.getNombre(), JOptionPane.INFORMATION_MESSAGE);
            return null;
        }else{
            return Prueba;
        }
    }
    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the SalonLST
     */
    public ListaSimple getSalonLST() {
        return SalonLST;
    }

    /**
     * @param SalonLST the SalonLST to set
     */
    public void setSalonLST(ListaSimple SalonLST) {
        this.SalonLST = SalonLST;
    }
}
