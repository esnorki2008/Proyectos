/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import Estructuras.Lineales.Queue;

/**
 *
 * @author linuxlite
 */
public class NodoBinario {
    private int ID;
    private Object Contenido;
    private NodoBinario Der;
    private NodoBinario Izq;
    public NodoBinario(int ID,Object Contenido){
        this.ID=ID;
        this.Contenido=Contenido;
        this.Izq=null;
        this.Der=null;
    }
    
    @Override
    public String toString(){
        return getContenido().toString();
    } 
    public String CrearNodo(){
        return this.toString()+"[label=\" " + this.toString()+ "\" ];\n";
    }
    public String Graph(){
        String Retorno="";
        if(null !=this.getIzq()){
        Retorno+=this.getIzq().Graph();
        Retorno+= this.toString()+"->"+getIzq().toString()+"\n";
        }
        if(null !=this.getDer()){
        Retorno+=this.getDer().Graph();
        Retorno+= this.toString()+"->"+getDer().toString()+"\n";
        }
        return Retorno +this.CrearNodo();
    }
    
     public Object Buscar(int ID){
        if(this.getID()==ID)
            return this.getContenido();
        else
        if(this.getID()>ID){
            if(this.getIzq()!=null)
                return this.getIzq().Buscar(ID);
            else
                return null;
        }
        else{
            if(this.getDer()!=null)
                return this.getDer().Buscar(ID);
            else
                return null;
        }
    }
    public void InsertarAVL(NodoBinario Nodo){
        if(Nodo.getID()<getID()){
            if(this.getIzq()==null)
                this.setIzq(Nodo);
            else{
                this.getIzq().InsertarAVL(Nodo);
            }
        }
        else
        if(Nodo.getID()>getID()){
            if(this.getDer()==null)
                this.setDer(Nodo);
            else{
                this.getDer().InsertarAVL(Nodo);
            }
        }
        int Factor=FE(this);
        if(Factor==-2){
            if(FE(this.getIzq())==-1){
                this.Cambiar(II());
            }
            else
                this.Cambiar(ID());
        }else if(Factor==2){
            if(FE(this.getDer())==1)
                this.Cambiar(DD());
            else
                this.Cambiar(DI());
        }
    }
    private NodoBinario DI(){
        NodoBinario N=new NodoBinario(this.getID(), this.getContenido());
        N.setDer(this.getDer());
        N.setIzq(this.getIzq());
        NodoBinario N1=N.getDer();
        NodoBinario N2=N1.getIzq();
        N1.setIzq(N2.getDer());
        N2.setDer(N1);
        N.setDer(N2.getIzq());
        N2.setIzq(N);
        N=N2;
        return N;
    }
    private NodoBinario ID(){
        NodoBinario N=new NodoBinario(this.getID(), this.getContenido());
        N.setDer(this.getDer());
        N.setIzq(this.getIzq());
        NodoBinario N1=N.getIzq();
        NodoBinario N2=N1.getDer();
        N1.setDer(N2.getIzq());
        N2.setIzq(N1);
        N.setIzq(N2.getDer());
        N2.setDer(N);
        N=N2;
        return N;
    }
    private NodoBinario II(){
        NodoBinario N=new NodoBinario(this.getID(), this.getContenido());
        N.setDer(this.getDer());
        N.setIzq(this.getIzq());
        NodoBinario N1=(N.getIzq());
        N.setIzq(N1.getDer());
        N1.setDer(N);
        N=N1;
        return N;
    }
    private NodoBinario DD(){
        NodoBinario N=new NodoBinario(this.getID(), this.getContenido());
        N.setDer(this.getDer());
            N.setIzq(this.getIzq());
            NodoBinario N1=(N.getDer());
        N.setDer(N1.getIzq());
        N1.setIzq(N);
        N=N1;
        return N;
    }
    public void Inorden(){
        if(getIzq()!=null)
            getIzq().Inorden();
        System.out.println(getID());
        if(getDer()!=null)
            getDer().Inorden();
        
    }
    private void Cambiar(NodoBinario Nodo){
        this.setDer(Nodo.getDer());
        this.setIzq(Nodo.getIzq());
        this.setID(Nodo.getID());
    }
    public int FE(NodoBinario N){
                

        return Altura(N.getDer())-Altura(N.getIzq());
    }
    
    public int Altura(NodoBinario Nodo){
        
        if(Nodo==null)
            return 1;
        else{
            int A=Altura(Nodo.getIzq())+1;
            int B=Altura(Nodo.getDer())+1;
            return Math.max(A, B);
        }
    }

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the Contenido
     */
    public Object getContenido() {
        return Contenido;
    }

    /**
     * @param Contenido the Contenido to set
     */
    public void setContenido(Object Contenido) {
        this.Contenido = Contenido;
    }

    /**
     * @return the Der
     */
    public NodoBinario getDer() {
        return Der;
    }

    /**
     * @param Der the Der to set
     */
    public void setDer(NodoBinario Der) {
        this.Der = Der;
    }

    /**
     * @return the Izq
     */
    public NodoBinario getIzq() {
        return Izq;
    }

    /**
     * @param Izq the Izq to set
     */
    public void setIzq(NodoBinario Izq) {
        this.Izq = Izq;
    }
}
