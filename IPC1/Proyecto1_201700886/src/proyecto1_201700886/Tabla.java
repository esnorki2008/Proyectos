/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_201700886;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
public class Tabla extends JFrame{
    
   public Object[][] datos;
   public Object[][] nueva;
   
   
   public void VerUsuarios() { 
            setLocation(200, 250);  

            boton vers = new boton();

            //for(int i=0;i<2;i++){
            Object[][] datos = {
                {vers.usuarios[0][1],vers.usuarios[0][1], vers.usuarios[0][2], vers.usuarios[0][3], vers.usuarios[0][4],vers.usuarios[0][5]},

            };



         String[] columnNames = {"ID","NOMBRE","APELLIDO","USER","ROL","PASSWORD"};
         DefaultTableModel dtm= new DefaultTableModel(datos,columnNames);
         final JTable table = new JTable(dtm);



         for(int i=1;i<30;i++){

         Object[] nueva={vers.usuarios[i][0],vers.usuarios[i][1],vers.usuarios[i][2],vers.usuarios[i][3],vers.usuarios[i][4],vers.usuarios[i][5]};
         dtm.addRow(nueva);
         }


         // Modificar celda especifica
         //dtm.setValueAt("SXXX", 3, 3); // Row/Col
         table.setPreferredScrollableViewportSize(new Dimension(900, 250));


         JScrollPane scrollPane = new JScrollPane(table);

         getContentPane().add(scrollPane, BorderLayout.CENTER);
         addWindowListener(new WindowAdapter() {
         //public void windowClosing(WindowEvent e){JFrame jf= new JFrame();    JOptionPane.showMessageDialog(jf,"Algún campo está vacio");}
         });
         pack();
         setVisible(true);
  }}
   

