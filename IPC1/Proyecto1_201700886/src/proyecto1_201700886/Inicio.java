/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_201700886;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author usuario
 */
public class Inicio implements ActionListener{
public JFrame er;
public JButton bt3,bt1;
    public void Inicios(){
        er = new JFrame("INICIO");
        er.setLayout(new FlowLayout());      
        er.setResizable(false);
        er.setLocation((int) (500), 250);
        er.setSize(700, 350);
        er.setVisible(true);
        er.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
        JLabel l1 = new JLabel("INFORMACION BIBLIOTECA");
        l1.setBounds(450,110,400,20);
        er.setLayout(null);
        
        JLabel l2 = new JLabel("ESTO ES UNA BIBLIOTECA VIRTUAL ELECTRONICA.");
        l2.setBounds(390,140,400,20);
        er.setLayout(null);
      
     
        
        bt3= new JButton("LOGIN");
        bt3.setBounds(450,20,100,40);
        er.setLayout(null);
        
        bt1= new JButton("ABOUT");
        bt1.setBounds(575,20,100,40);
        er.setLayout(null);
        
        ImageIcon imagen = new ImageIcon("C:\\Users\\usuario\\Pictures\\TERA_ScreenShots\\d.png");
        JLabel l4 = new JLabel(imagen);
        l4.setBounds(60,10,100,100);
        er.setLayout(null);
        
        ImageIcon imagens = new ImageIcon("C:\\Users\\usuario\\Pictures\\TERA_ScreenShots\\c.jpg");
        JLabel l5 = new JLabel(imagens);
        l5.setBounds(60,140,300,150);
        er.setLayout(null);
        
       
        bt3.addActionListener(this);
        bt1.addActionListener(this);
        
        er.add(l1);er.add(l2);er.add(bt3);er.add(l4);er.add(l5);er.add(bt1);
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
   
        
        if(e.getSource()==bt3){
        boton zapato= new boton(); er.setVisible(false);
        zapato.login();}
        
         if(e.getSource()==bt1){
         JOptionPane.showMessageDialog(er,"Yo lo hice.  :D");}
        
        }
        
        }
        
       
        
 
    
