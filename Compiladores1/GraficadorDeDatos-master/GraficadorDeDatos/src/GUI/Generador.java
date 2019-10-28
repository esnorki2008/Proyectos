/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Nodos.DXYLine;
import Nodos.GraficaBarra;
import Nodos.GraficaLineas;
import Nodos.NodoGaleria;
import Nodos.VarGlobales;
import Usuario.Calculator;
import java.awt.BasicStroke;
import java.awt.Color;
import java.io.*;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.chart.ChartUtilities; 
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Generador {
    LinkedList<VarGlobales> VG;
    
    LinkedList<String> G1=new LinkedList<String>();
    LinkedList<JFreeChart> G2=new LinkedList<JFreeChart>();
    
    Generador(LinkedList<VarGlobales> VG){
        this.VG=VG;
    }
    Generador(){}
    public void Guardar( LinkedList<NodoGaleria> Galeri){
     //      File XYChart = new File( ""+ID+".jpeg" ); 
   
        
    int Tm= Galeri.size();
    for(int i=0;i<Tm;i++){
        String Conte=Galeri.get(i).Galeria.replace('"', ' ').trim();
        String S[]=Conte.split(",");
        
        int sx = S.length;
        String Carpeta="C:\\Galeria\\"+S[0];
        File directorio=new File(Carpeta); 
        directorio.mkdir(); 
        for(int j=1;j<sx;j++){
            int Post =Busqueda(S[j]);

            if(Post!=-1){
              File XYChart = new File( Carpeta.trim()+"\\"+G1.get(Post).replace('"', ' ').trim()+".jpeg" );    
                try {
                    ChartUtilities.saveChartAsJPEG(XYChart, G2.get(Post), 700, 500);
                } catch (IOException ex) {
                    Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    
    }
    
    }
    private int Busqueda(String Bus){
    int Tm= G1.size();
    for(int i=0;i<Tm;i++){
        
        String QO=G1.get(i).replace('"', ' ');
        
        System.out.println(QO+"  "+Bus);
        
        if(Bus.trim().equalsIgnoreCase(QO.trim())){
            return i;
            
        }
    }
    return -1;
    }
    
    private double OP(String T) {
        double Num=0;
        try {
            
            
            Num=Integer.parseInt(T);
        } catch (Exception s) {
            
       
       
             int G = VG.size();
             for(int i=0;i<G;i++){
                 String Contenido = VG.get(i).Nombre;
                 
                  System.out.println(T+ "   ss   "+Contenido);
                 if(T.toLowerCase().equals(Contenido.toLowerCase())){
                 
                     
                     T=T.replace(Contenido, VG.get(i).ValorString);  
                    
                 }
            }
                  
                 try {
                      Calculator Calculadora = new Calculator();
                     Num=Double.parseDouble(Calculadora.analizar(T).trim());
                   //  System.out.println(Num+ "   "+Calculadora.analizar(T).trim());
                     } catch (Exception ex) {
                     Logger.getLogger(Generador.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 
                 
             
            
            
            
            
            
        }
             
             
        return Num;
    }
    
private String OPS(String T) {
            
            
            
       
       
             int G = VG.size();
             for(int i=0;i<G;i++){
                 String Contenido = VG.get(i).Nombre;
                 if(T.toLowerCase().equals(Contenido.toLowerCase())){T=T.replace(Contenido, VG.get(i).ValorString);     }
                
             }
            
            
            T=T.replace('"', ' ');
            
            
        
        return T;
    }
    
   public void Linea(LinkedList<GraficaLineas> GL)throws Exception {
       LinkedList<DXYLine> DXY = new LinkedList<DXYLine>();
       int G = GL.size();

           for(int i=0;i<G;i++){
                           final XYSeriesCollection dataset = new XYSeriesCollection( ); 

            String ID=GL.get(i).ID;
            String Titulo=GL.get(i).Titulo;
            String TituloX=GL.get(i).TituloX;
            String TituloY=GL.get(i).TituloY;   
               
            DXY=GL.get(i).DatosXY();
            int j= DXY.size();
            


            for(int q=0;q<j;q++){
            String Nombre=DXY.get(q).Nombre;
            String Color=DXY.get(q).Color;
            String Grosor=DXY.get(q).Grosor;
            String Puntos=DXY.get(q).Puntos;
            final XYSeries Contenido = new XYSeries(Nombre);
    
                String Hola = Puntos;
                Hola = Hola.replace('[', ' ').replace(']', ' ');;
                String[] ch = Hola.split("}");
                
                
                for (String M : ch) {
                    String s = M.substring(1).replace('{', ' ').trim();
                    String[] Sub = s.split(",");
                    if(!s.equals("")){
                      //  System.out.println(Sub[0]+ " "+Sub[1]);    
                    double S0=OP(Sub[0]);
                    double S1=OP(Sub[1]);
                  //  System.out.println(Sub[0]);
                //    System.out.println(Sub[0]);
                    Contenido.add(S0,S1);}
                }           
                             dataset.addSeries(Contenido);
            }
           
          Titulo=Titulo.replace('"',  ' ');  
          TituloX=TituloX.replace('"',  ' ');  
          TituloY=TituloY.replace('"',  ' ');  
            JFreeChart xylineChart = ChartFactory.createXYLineChart(
         OPS(Titulo), 
         OPS(TituloX),
         OPS(TituloY), 
         dataset,
         PlotOrientation.VERTICAL, 
         true, true, false);
      
      int width = 640;   /* Width of the image */
      int height = 480;  /* Height of the image */ 
      ID=ID.replace('"',  ' ').trim();
      File XYChart = new File( ""+ID+".jpeg" ); 
    //  ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
     // xylineChart.setBorderStroke(4);
      
   /*   XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
      renderer.setSeriesPaint(0, Color.RED);
    renderer.setSeriesPaint(1, Color.GREEN);
    renderer.setSeriesPaint(2, Color.YELLOW);  */
    XYPlot plot =  xylineChart.getXYPlot();
    XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
    plot.setRenderer(renderer);
    

           DXY=GL.get(i).DatosXY();
            for(int q=0;q<j;q++){
                String Colores=DXY.get(q).Color;
                String Grosor=DXY.get(q).Grosor;
                
                
                
                switch(Colores.toLowerCase().replace('"',' ').trim()){
                    case "rojo":
                        renderer.setSeriesPaint(q, Color.RED);
                        break;
                    case "amarillo":
                        renderer.setSeriesPaint(q, Color.YELLOW);
                        break;
                    case "naranja":
                        renderer.setSeriesPaint(q, Color.ORANGE);
                        break;
                    case "azul":
                        renderer.setSeriesPaint(q, Color.BLUE);
                        break;
                    case "negro":
                        renderer.setSeriesPaint(q, Color.BLACK);
                        break;
                    case "verde":
                        renderer.setSeriesPaint(q, Color.GREEN);
                        break;
                    case "rosado":
                        renderer.setSeriesPaint(q, Color.PINK);
                        break;    
                    default:
                        renderer.setSeriesPaint(q, Color.BLACK);
                        break;
                }
                System.out.println(OP(Grosor));
                renderer.setSeriesStroke(q, new BasicStroke(2*Float.parseFloat(OP(Grosor)+"")));

            }
      
    


    
    
    
      G1.add(ID);
      G2.add(xylineChart);
           }
      

      
   }
   
    public void Barra(LinkedList<GraficaBarra> GL) throws IOException {
        int G = GL.size();

        for (int i = 0; i < G; i++) {
            final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            final String ID = GL.get(i).Id;
            final String Titulo = GL.get(i).Titulo;
            final String TituloX = GL.get(i).TituloX;
            final String TituloY = GL.get(i).TituloY;
            
            final String Puntosxy=GL.get(i).PuntosXY;
             String EjeX=GL.get(i).EjeX;
             String EjeY=GL.get(i).EjeY;
            
            
            String Hola = Puntosxy;
            Hola = Hola.replace('[', ' ').replace(']', ' ');
            String[] ch = Hola.split("}");
            
            LinkedList<String> Pos = new LinkedList<String>();
            
            for (String M : ch) {
                    String s = M.substring(1).replace('{', ' ').trim();
                    String[] Pr= s.split(','+"");
                    if(!Pr[0].equals("")){
                    Pos.add(Pr[0]);
                    Pos.add(Pr[1]);
                    }
            }
            EjeX=EjeX.replace('[', ' ').replace('"', ' ').replace(']', ' ').trim();
            EjeY=EjeY.replace('[', ' ').replace('"', ' ').replace(']', ' ').trim();
            
            String[] AxisX=EjeX.split(",");
            String[] AxisY=EjeY.split(",");
            
            int Tem = Pos.size();
            for(int y=0;y<Tem;y=y+2){
                int Xi=Integer.parseInt(Pos.get(y));
                int Yi=Integer.parseInt(Pos.get(y+1));
                
           //     System.out.println(OPS(AxisX[Xi])+"-----"+OP(AxisY[Yi]));
                dataset.addValue( OP(AxisY[Yi]) , OPS(AxisX[Xi]) , "" );
                
            }
            
            
            
            
            
            
            JFreeChart barChart = ChartFactory.createBarChart(
                    OPS(Titulo),
                    OPS(TituloX), OPS(TituloY),
                    dataset, PlotOrientation.VERTICAL,
                    true, true, false);

            int width = 640;    /* Width of the image */

            int height = 480;   /* Height of the image */
            
            File BarChart = new File(ID.replace('"', ' ').trim()+".jpeg");
         //   ChartUtilities.saveChartAsJPEG(BarChart, barChart, width, height);
      G1.add(ID);
      G2.add(barChart);
        }

       
       
   /*    final String fiat = "FIAT";
      final String audi = "AUDI";
      final String ford = "FORD";
      final String speed = "Speed";
      final String millage = "Millage";
      final String userrating = "User Rating";
      final String safety = "safety";

      dataset.addValue( 1.0 , fiat , speed );
      dataset.addValue( 3.0 , fiat , userrating );
      dataset.addValue( 5.0 , fiat , millage );
      dataset.addValue( 5.0 , fiat , safety );

      dataset.addValue( 5.0 , audi , speed );
      dataset.addValue( 6.0 , audi , userrating );
      dataset.addValue( 10.0 , audi , millage );
      dataset.addValue( 4.0 , audi , safety );

      dataset.addValue( 4.0 , ford , speed );
      dataset.addValue( 2.0 , ford , userrating );
      dataset.addValue( 3.0 , ford , millage );
      dataset.addValue( 6.0 , ford , safety );

    */
   }
   
}
