/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto1_201700886;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.*;


/**
 *
 * @author usuario
 */
class boton implements ActionListener{
    
    
    
    public int z=0;
    public JButton bt2,bt3,bt1;
    public static String usuarios[][] = new String[100][8];
    public  JTextField t1,t2;
    public JFrame jf,er;
    
    
    boton(){
   // for(int i=0;i<100;i++){usuarios[i][0] = ("");}   
    
    
    usuarios[0][0] = ("admin");//ID
    usuarios[0][1]=("admin");//Nombre
    usuarios[0][2] = ("admin");//Usuario
    usuarios[0][3] = ("admin");//Apellido
    usuarios[0][4]=("admin");//ROL
    usuarios[0][5]=("admin");// Contraseña
    
    usuarios[1][0] = ("123456");//ID
    usuarios[1][1]=("Esnorki");//Nombre
    usuarios[1][2] = ("Perro");//Apellido
    usuarios[1][3]=("Lobo");//Usuario
    usuarios[1][4]=("Estudiante");//ROL
    usuarios[1][5]=("G123au");// Contraseña
    
    usuarios[2][0] = ("928456");//ID
    usuarios[2][1]=("Ave");//Nombre
    usuarios[2][2] = ("Pajaro");//Apellido
    usuarios[2][3] = ("Perico");//Usuario
    usuarios[2][4]=("Estudiante");//ROL
    usuarios[2][5]=("G4567au");// Contraseña
    
    
    
    errorLogin();
    er.setVisible(false);
    }
    
    public void inicio(){
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
      String a,b;  
        
    
        
        
        if(e.getSource()==bt1){
            a=t1.getText();
            String c=t1.getText();
            b=t2.getText();
            if((a.equals(""))||((b.equals("")))){JOptionPane.showMessageDialog(jf,"Algún campo está vacio");}
            else{
                   
                    for(int i=0;i<100;i++){if(a.equals(usuarios[i][0])||(a.equals(usuarios[i][2]))){a="1"; break;}}  
                    for(int i=0;i<100;i++){if(b.equals(usuarios[i][4])){b="1"; break;}}   

                if((a.equals("1"))&&(b.equals("1"))){a="";b="";
                      if(c.equals("admin")){  etiqueta admin = new etiqueta();JOptionPane.showMessageDialog(jf,"Bienvenido administrador");
                        admin.MenuAdmin();  jf.setVisible(false); }
                      
                      
                        else{JOptionPane.showMessageDialog(jf,"Bienvenido  "+c);}
                }
                        else{er.setVisible(true);} 
            }
        }
        
        
        //JOptionPane.showMessageDialog(null, e.getActionCommand());
        if(e.getSource()==bt2){
            t1.setText("");
            t2.setText("");
            
        }
        
        if(e.getSource()==bt3){
         er.setVisible(false);
        
        }
        
    }
    
    public void login(){
        
        jf = new JFrame("LOGIN BIBLIOTECA");
        jf.setLayout(new FlowLayout());
 
         JLabel l1 = new JLabel("LOGIN");
        l1.setBounds(215,10,50,20);
        jf.setLayout(null);
        
        JLabel l2 = new JLabel("ID/USUARIO");
        l2.setBounds(30,60,100,20);
        jf.setLayout(null);
        
       t1 = new JTextField("");
        t1.setBounds(30,85,390,20);
        jf.setLayout(null);
        
       JLabel l3= new JLabel("PASSWORD");
       l3.setBounds(30,110,100,20);
       jf.setLayout(null);
        
       t2 = new JTextField("");
       t2.setBounds(30,135,390,20);
       jf.setLayout(null);
        
        
        
        
        
        bt1 = new JButton("LOGIN");
        bt1.setBounds(30,170,195,40);
        jf.setLayout(null);
        
        bt2 = new JButton("CANCELAR");
        bt2.setBounds(225,170,195,40);
        jf.setLayout(null);
        
        
       
       
        
        jf.add(l3);
        jf.add(bt1);jf.add(bt2);jf.add(t1);jf.add(l1);jf.add(l2);jf.add(t2);

        
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        
 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        jf.setResizable(false);
        jf.setLocation((int) (500), 250);
        jf.setSize(450, 250);
        jf.setVisible(true);
        
        
    } 
    
    public void errorLogin() {
        
      
        
        
        er = new JFrame("ERROR");
        er.setLayout(new FlowLayout());      
        er.setResizable(false);
        er.setLocation((int) (500), 250);
        er.setSize(450, 200);
       
        
        JLabel l1 = new JLabel("INFORMACION");
        l1.setBounds(200,10,100,20);
        er.setLayout(null);
        
        JLabel l2 = new JLabel("EL USUARIO NO EXISTE, POR FAVOR");
        l2.setBounds(200,50,400,20);
        er.setLayout(null);
        
        JLabel l3 = new JLabel("INTENTE NUEVAMENTE");
        l3.setBounds(200,65,400,20);
        er.setLayout(null);
        
        bt3= new JButton("ACEPTAR");
        bt3.setBounds(60,100,330,40);
        er.setLayout(null);
        
        ImageIcon imagen = new ImageIcon("C:\\Users\\usuario\\Pictures\\TERA_ScreenShots\\b.png");
        JLabel l4 = new JLabel(imagen);
        l4.setBounds(60,40,50,50);
        er.setLayout(null);
       
        bt3.addActionListener(this);
        
        er.add(l1);er.add(l3);er.add(l2);er.add(bt3);er.add(l4);
        
        
         
     
        
        
    }
    
    
    
    
    
}
