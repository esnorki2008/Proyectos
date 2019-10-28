/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Motor;

import java.util.ArrayList;

/**
 *
 * @author lizandro
 */
public class V9 {

    public String Consola = "";
    public Boolean BO=true;
    private ArrayList<Variable> ListaTotal = new ArrayList<Variable>();
    private ArrayList<Funcion> ListaFunciones = new ArrayList<Funcion>();
    private ArrayList<String> Instrucciones = new ArrayList<String>();
    
    
    public void H(String S){
        this.Instrucciones.add(S);
    }
    public void FSB(String Nombre,String Ingreso){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            Nueva.setBorde(Ingreso);
        }
        
    }
    public void FSC(String Nombre,String Ingreso){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            Nueva.setContenido(Ingreso);
        }
        
    }
    public void FSA(String Nombre,String Ingreso){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            Nueva.setAlineacion(Ingreso);
        }
        
    }
    public void FSP(String Nombre,String Ingreso){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            Nueva.setPath(Ingreso);
        }
        
    }
    public void FSAN(String Nombre,String Ingreso){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            Nueva.setAncho(Double.parseDouble(Ingreso));
        }
        
    }
    public void FSAL(String Nombre,String Ingreso){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            Nueva.setAlto(Double.parseDouble(Ingreso));
        }
        
    }
    public void FSAB(String Nombre,String Ingreso){
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
           Nueva.setAccion(Ingreso);
        }
    }
     public void FSTB(String Nombre,String Ingreso){
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            Nueva.setTextoBoton(Ingreso);
        }
    }
    public String FGTB(String Nombre){
        System.out.println("AQUI    "+Nombre);
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            return ""+Nueva.getTextoBoton();
        }else{
        return "NoFueDefinida";
        }
        
    }
    public String FGAL(String Nombre){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            return ""+Nueva.getAlto();
        }else{
        return "NoFueDefinida";
        }
        
    }
    public String FGAN(String Nombre){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            return ""+Nueva.getAncho();
        }else{
        return "NoFueDefinida";
        }
        
    }
    public String FGA(String Nombre){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            return Nueva.getAlineacion();
        }else{
        return "NoFueDefinida";
        }
        
    }
    public String FGC(String Nombre){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            return Nueva.getContenido();
        }else{
        return "NoFueDefinida";
        }
        
    }
    public String FGP(String Nombre){
        
        Funcion Nueva = BuscarF(Nombre);
        if(Nueva!=null){
            return Nueva.getPath();
        }else{
        return "NoFueDefinida";
        }
        
    }
    public String Insertar(String Ingreso){
        Funcion Funci=BuscarF(Ingreso);
        String Salida="";
        if(Funci!=null){
        switch(Funci.getTipo()){
            case 1:
                return "<P align="+(char)34+Funci.getAlineacion()+(char)34+">"+Funci.getContenido()+"</P>\n";
            case 2:
                return "<H1>"+Funci.getContenido()+"</H1>\n";
            case 3:
                return "<H3>"+Funci.getContenido()+"</H3>\n";
            case 4:
                return "<img src= "+Funci.getPath()+" height=\""+Funci.getAlto()+"\" width=\""+Funci.getAncho()+"\">\n";
            case 5:
                return "<table "+Funci.getBorde()+" >"+Funci.getContenido()+"</table>\n";
            case 6:
                return "<input name=\""+Funci.getNombre()+ "\" type=\"button\" onclick= 'alert(\""+Funci.getAccion()+ "\")' value=\"" +Funci.getTextoBoton()+ "\"/>  \n";
                
                

                      
        }   
        }
        return Salida;
    }
    public void AF(String Nombre,int Tipo) {
        if(this.BO){
            if(BuscarF(Nombre)==null){
             Funcion Var = new Funcion(Nombre,Tipo);
             this.ListaFunciones.add(Var);
            }
        
        }
    }
    private Funcion BuscarF(String Nombre){
        for(Funcion Fun: this.ListaFunciones){
            if(Fun.getNombre().toLowerCase().equals(Nombre.toLowerCase())){
                return Fun;
            }
        }
        return null;
    }
    
    public void A(String Nombre, String Conte, String Tipo) {
        if(this.BO){
        Variable Var=Busq(Nombre);
        if(Var==null){
        Variable VarNueva = new Variable(Nombre, Conte, Tipo);
        ListaTotal.add(VarNueva);
        }else{
            Var.setLexema(Conte);
        }
        
        }
    }

    public double P(String Ingreso) {
        /*if(Ingreso.equals("FAL")||Ingreso.equals("VER")){
            return 0;
        }*/
        Ingreso=Ingreso.replace(")", "");
        Ingreso=Ingreso.replace("(", "");
        Ingreso=Ingreso.replace((char)34+"", "");
        return Double.parseDouble(Ingreso);
    }

    public int Pint(String Ingreso) {
        /*if(Ingreso.equals("FAL")||Ingreso.equals("VER")){
            return 0;
        }*/
        Ingreso=Ingreso.replace(")", "");
        Ingreso=Ingreso.replace("(", "");
        Ingreso=Ingreso.replace((char)34+"", "");
        Ingreso=Ingreso.replace("\"", "");
        Ingreso=Ingreso.split("\\.")[0];
        return Integer.parseInt(Ingreso);
    }
    private Variable Busq(String Nombre){
        for(Variable Var:this.ListaTotal){
            if(Var.getNombre().equals(Nombre)){
            return Var;
            }
        }
         return null;
    }
    
    public String R(String Nombre, String Tipo) {
        String Reto = "ValorNoDefinido";
        for (Variable VR : ListaTotal) {
            if (VR.getNombre().equals(Nombre)) {
                return VR.getLexema();
            }
        }
        return Reto;

    }

    public void I(String Ingreso,int Veces) {
        
        String M="";
        for(int i=0;i<Veces;i++){
            if (this.BO) {
                M=M + Ingreso+"\n";
            }
                
        }
        

            this.Consola=this.Consola+M;
    }

    public void Imprimir() {
        for (Variable VR : ListaTotal) {
            System.out.println("ID: " + VR.getNombre() + " LEXEMA: " + VR.getLexema());
        }
        
        
    }
    public String Sacar(){
    String Salida="";
    
    for (Variable VR : ListaTotal) {
           Salida=Salida+" $ID: " + VR.getNombre() + " LEXEMA: " + VR.getLexema()+"\n";
        }
    for (Funcion VR : this.ListaFunciones) {
           Salida=Salida+" #ID: " + VR.getNombre() + " LEXEMA: " + this.Insertar(VR.getNombre())+"\n";
        }
    
    return Salida;
    }
    

}
