/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_201700886;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javax.swing.JTable; 
import javax.swing.JScrollPane; 
import javax.swing.JFrame; 
import java.awt.*; 
import java.awt.event.*;
import static proyecto1_201700886.boton.usuarios;


public class Bibliogra implements ActionListener {
 
public JButton bt2,bt3,bt1,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12,bt13,bt14,bt15,bt16,bt17,bt18,bt19,bt20;   
public  JTextField t1,t2,t3,t4,t5,t6,t7;
public JFrame jf,er,cu;
public JComboBox combito;

public static String libros[][] = new String[100][8];
public static String revistas[][] = new String[100][10];
public static String tesis[][] = new String[100][9];

public void hola(){
 cu = new JFrame();    
                cu.setLayout(new FlowLayout());      
                cu.setResizable(false);
                cu.setLocation((int) (125), 250);
                cu.setSize(460, 200);
                cu.setVisible(true);
                
                JLabel l1 = new JLabel("USUARIO ADMINISTRADOR - CARGA");
                l1.setBounds(130,10,240,10);
                cu.setLayout(null);
                
                bt1= new JButton("BUSCAR");
                bt1.setBounds(60,60,140,40);
                cu.setLayout(null);
                
                bt2= new JButton("BUSCAR");
                bt2.setBounds(250,60,140,40);
                cu.setLayout(null);
                
                
                
                
                cu.add(l1);cu.add(bt1);cu.add(bt2);
                bt1.addActionListener(this);
                bt2.addActionListener(this);
}

@Override
        public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bt1){
            cu.setVisible(false);
            javaxx(); }
        
        
         if(e.getSource()==bt2){
            cu.setVisible(false);
            javax(); }
        
        }
        
        
  

public void javaxx(){
 cu = new JFrame();    
            cu.setLayout(new FlowLayout());      
            cu.setResizable(false);
            cu.setLocation((int) (125), 250);
            cu.setSize(900, 350);
            cu.setVisible(true);
            cu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
                
            
            
            
            JLabel l1 = new JLabel("Crear Usuario");
            l1.setBounds(220,10,100,10);
            cu.setLayout(null);
            
            JLabel l2 = new JLabel("ID");
            l2.setBounds(60,60,100,10);
            cu.setLayout(null);
            
            JLabel l3 = new JLabel("NOMBRE");
            l3.setBounds(60,110,100,10);
            cu.setLayout(null);
            
            JLabel l4 = new JLabel("APELLIDO");
            l4.setBounds(60,160,100,10);
            cu.setLayout(null);
            
            JLabel l5 = new JLabel("USER");
            l5.setBounds(60,210,100,10);
            cu.setLayout(null);
            
            JLabel l6 = new JLabel("ROL");
            l6.setBounds(280,60,100,10);
            cu.setLayout(null);
            
            JLabel l7 = new JLabel("PASSWORD");
            l7.setBounds(280,110,100,10);
            cu.setLayout(null);
            
            JLabel l8 = new JLabel("CONFIRMAR PASSWORD");
            l8.setBounds(280,160,200,10);
            cu.setLayout(null);
            
            
            t1 = new JTextField("");
            t1.setBounds(60,80,200,20);
            cu.setLayout(null);
            
            t2 = new JTextField("");
            t2.setBounds(60,130,200,20);
            cu.setLayout(null);
            
            t3 = new JTextField("");
            t3.setBounds(60,180,200,20);
            cu.setLayout(null);
            
            t4 = new JTextField("");
            t4.setBounds(60,230,200,20);
            cu.setLayout(null);
            
            t5 = new JTextField("");
            t5.setBounds(280,130,200,20);
            cu.setLayout(null);
            
            t6 = new JTextField("");
            t6.setBounds(280,180,200,20);
            cu.setLayout(null);
            
           
            
            combito = new JComboBox();
            combito.setBounds(280,80,200,20);
            cu.setLayout(null);
            
            bt14 = new JButton("Crear Usuario");
            bt14.setBounds(180,270,150,40);
            cu.setLayout(null);
            bt14.addActionListener(this);
            
            combito.addItem("libro");
            combito.addItem("revista");
            combito.addItem("tesis");
            
            
            cu.add(bt14);
            cu.add(combito);cu.add(t1);cu.add(t2);cu.add(t3);cu.add(t4);cu.add(t5);cu.add(t6);
            cu.add(l1);cu.add(l2);cu.add(l3);cu.add(l4);cu.add(l5);cu.add(l6);cu.add(l7);cu.add(l8);


}  



public void javax(){
 cu = new JFrame();    
            cu.setLayout(new FlowLayout());      
            cu.setResizable(false);
            cu.setLocation((int) (125), 250);
            cu.setSize(540, 350);
            cu.setVisible(true);
                
            
            
            
            JLabel l1 = new JLabel("Crear Usuario");
            l1.setBounds(220,10,100,10);
            cu.setLayout(null);
            
            JLabel l2 = new JLabel("ID");
            l2.setBounds(60,60,100,10);
            cu.setLayout(null);
            
            JLabel l3 = new JLabel("NOMBRE");
            l3.setBounds(60,110,100,10);
            cu.setLayout(null);
            
            JLabel l4 = new JLabel("APELLIDO");
            l4.setBounds(60,160,100,10);
            cu.setLayout(null);
            
            JLabel l5 = new JLabel("USER");
            l5.setBounds(60,210,100,10);
            cu.setLayout(null);
            
            JLabel l6 = new JLabel("ROL");
            l6.setBounds(280,60,100,10);
            cu.setLayout(null);
            
            JLabel l7 = new JLabel("PASSWORD");
            l7.setBounds(280,110,100,10);
            cu.setLayout(null);
            
            JLabel l8 = new JLabel("CONFIRMAR PASSWORD");
            l8.setBounds(280,160,200,10);
            cu.setLayout(null);
            
            
            t1 = new JTextField("");
            t1.setBounds(60,80,200,20);
            cu.setLayout(null);
            
            t2 = new JTextField("");
            t2.setBounds(60,130,200,20);
            cu.setLayout(null);
            
            t3 = new JTextField("");
            t3.setBounds(60,180,200,20);
            cu.setLayout(null);
            
            t4 = new JTextField("");
            t4.setBounds(60,230,200,20);
            cu.setLayout(null);
            
            t5 = new JTextField("");
            t5.setBounds(280,130,200,20);
            cu.setLayout(null);
            
            t6 = new JTextField("");
            t6.setBounds(280,180,200,20);
            cu.setLayout(null);
            
           
            
            combito = new JComboBox();
            combito.setBounds(280,80,200,20);
            cu.setLayout(null);
            
            bt14 = new JButton("Crear Usuario");
            bt14.setBounds(180,270,150,40);
            cu.setLayout(null);
            bt14.addActionListener(this);
            
            combito.addItem("estudiante");
            combito.addItem("catedrático");
            
            cu.add(bt14);
            cu.add(combito);cu.add(t1);cu.add(t2);cu.add(t3);cu.add(t4);cu.add(t5);cu.add(t6);
            cu.add(l1);cu.add(l2);cu.add(l3);cu.add(l4);cu.add(l5);cu.add(l6);cu.add(l7);cu.add(l8);


}   

}