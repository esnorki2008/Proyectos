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
import  proyecto1_201700886.Bibliogra;

/**
 *
 * @author usuario
 */
class etiqueta implements ActionListener{
        
        public JButton bt2,bt3,bt1,bt4,bt5,bt6,bt7,bt8,bt9,bt10,bt11,bt12,bt13,bt14,bt15,bt16,bt17,bt18,bt19,bt20;
       
        public  JTextField t1,t2,t3,t4,t5,t6,t7;
        public JFrame jf,er,cu;
        public JComboBox combito;
        public  int contador=3 ,h;
        public static int objetivo=0;
        
        
        
        
       
        
        public void MenuAdmin() {
            
            er = new JFrame("MENU");
            er.setLayout(new FlowLayout());      
            er.setResizable(false);
            er.setLocation((int) (125), 250);
            er.setSize(980, 350);
            er.setVisible(true);
            er.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        
            JLabel l1 = new JLabel("USUARIO ADMINISTRADOR");
            l1.setBounds(420,10,200,20);
            er.setLayout(null);

            JLabel l2 = new JLabel("USUARIOS");
            l2.setBounds(60,60,100,20);
            er.setLayout(null);

            JLabel l3= new JLabel("BIBLIOGRAFIAS");
            l3.setBounds(60,140,100,20);
            er.setLayout(null);

            JLabel l4= new JLabel("SECCION PRESTAMOS");
            l4.setBounds(60,220,200,20);
            er.setLayout(null);



            bt3= new JButton("CREAR USUARIOS");
            bt3.setBounds(60,80,200,40);
            er.setLayout(null);

            bt1= new JButton("LOGOUT");
            bt1.setBounds(765,10,155,40);
            er.setLayout(null);

            bt2= new JButton("MODIFICAR USUARIOS");
            bt2.setBounds(280,80,200,40);
            er.setLayout(null);

            bt4= new JButton("ELIMINAR USUARIOS");
            bt4.setBounds(500,80,200,40);
            er.setLayout(null);

            bt5= new JButton("VER USUARIOS");
            bt5.setBounds(720,80,200,40);
            er.setLayout(null);



            bt6= new JButton("CREAR BIBLIOGRAFIAS");
            bt6.setBounds(60,160,200,40);
            er.setLayout(null);

            bt7= new JButton("MODIFICAR BIBLIOGRAFIAS");
            bt7.setBounds(280,160,200,40);
            er.setLayout(null);

            bt8= new JButton("ELIMINAR BIBLIOGRAFIAS");
            bt8.setBounds(500,160,200,40);
            er.setLayout(null);

            bt9= new JButton("VER BIBLIOGRAFIAS");
            bt9.setBounds(720,160,200,40);
            er.setLayout(null);




            bt10= new JButton("CREAR PRESTAMOS");
            bt10.setBounds(60,240,200,40);
            er.setLayout(null);

            bt11= new JButton("MODIFICAR PRESTAMOS");
            bt11.setBounds(280,240,200,40);
            er.setLayout(null);

            bt12= new JButton("ELIMINAR PRESTAMOS");
            bt12.setBounds(500,240,200,40);
            er.setLayout(null);

            bt13= new JButton("VER PRESTAMOS");
            bt13.setBounds(720,240,200,40);
            er.setLayout(null);
            
        
        
        
        
        
        
        
        
        
        
        


            bt3.addActionListener(this);
            bt1.addActionListener(this);
            bt2.addActionListener(this);
            bt4.addActionListener(this);
            bt5.addActionListener(this);
            bt6.addActionListener(this);
            bt7.addActionListener(this);
            bt8.addActionListener(this);
            bt9.addActionListener(this);
            bt10.addActionListener(this);
            bt11.addActionListener(this);
            bt12.addActionListener(this);
            bt13.addActionListener(this);
        
            er.add(l1);er.add(l2);er.add(bt3);er.add(bt1);er.add(l3);er.add(l4);
            er.add(bt2);er.add(bt4);er.add(bt5);er.add(bt6);er.add(bt7);er.add(bt8);er.add(bt9);
            er.add(bt10);er.add(bt11);er.add(bt12);er.add(bt13);
        
    }
    
        @Override
        public void actionPerformed(ActionEvent e) {
        if(e.getSource()==bt1){
            er.setVisible(false);}
        
        if(e.getSource()==bt2){
           Modificar();}
       
        if(e.getSource()==bt3){
            CrearUsuarios();}
        
        if(e.getSource()==bt4){
            Eliminar();}
        
        if(e.getSource()==bt5){
            VerUsuarios();}
       
        if(e.getSource()==bt6){
            Bibliogra CrearB = new Bibliogra();
            CrearB.hola();
        }
        /* 
        if(e.getSource()==bt7){
            JOptionPane.showMessageDialog(er,"Yo lo hice.  :D");}
        
        if(e.getSource()==bt8){
            JOptionPane.showMessageDialog(er,"Yo lo hice.  :D");}
        
        if(e.getSource()==bt9){
            JOptionPane.showMessageDialog(er,"Yo lo hice.  :D");}
         
        if(e.getSource()==bt10){
            JOptionPane.showMessageDialog(er,"Yo lo hice.  :D");}
        
        if(e.getSource()==bt11){
            JOptionPane.showMessageDialog(er,"Yo lo hice.  :D");}
        
        if(e.getSource()==bt12){
            JOptionPane.showMessageDialog(er,"Yo lo hice.  :D");}
        
        if(e.getSource()==bt13){
            JOptionPane.showMessageDialog(er,"Yo lo hice.  :D");}
            */
        
        if(e.getSource()==bt14){
            Object CU[]= new Object[7];
            String a;
            CU[0]=t1.getText();
            CU[1]=t2.getText();
            CU[2]=t3.getText();
            CU[3]=t4.getText();
            CU[4]=combito.getSelectedItem();
            CU[5]=t5.getText();
            CU[6]=t6.getText();
            a=CU[5].toString();
            int c=0;
        
            String b=a.toUpperCase();
            char[] mayu =a.toCharArray();
            char[] mayus =b.toCharArray();
            boton s= new boton();
             s.z=1;
            
            
            for(int i=0;i<100;i++){
            if(CU[0].equals(s.usuarios[i][0])){c=1;break;}}
               
            
            
            if(c==1){JOptionPane.showMessageDialog(cu,"Ya existe un Usuario con ese ID");}else{
            if((CU[0].equals(""))||(CU[1].equals(""))||(CU[2].equals(""))||(CU[3].equals(""))||(CU[4].equals(""))||(CU[5].equals(""))||(CU[6].equals(""))){JOptionPane.showMessageDialog(cu,"Al menos un campo está vacio");}
                else{ 
                        if(mayu[0]==mayus[0]){if(a.length()>=6){
                            if(CU[5].equals(CU[6])){ s.usuarios[contador][0]=CU[0].toString();s.usuarios[contador][1]=(CU[1].toString());s.usuarios[contador][2]=(CU[2].toString());
                                s.usuarios[contador][3]=CU[3].toString();s.usuarios[contador][4]=CU[4].toString();s.usuarios[contador][5]=CU[5].toString();
                                    contador=contador+1;
                                    JOptionPane.showMessageDialog(cu,"El Usuario se ha creado exitosamente");}
                                        else{JOptionPane.showMessageDialog(cu,"Las contraseñas no son iguales");}}
                                            else{JOptionPane.showMessageDialog(cu,"Error su contraseña no tiene como mínimo 6 caracteres");}}
                                                else{JOptionPane.showMessageDialog(cu,"Error su contraseña no empieza con mayuscula");}}
            }}
            
        
         
        if(e.getSource()==bt15){
           
            contador=contador-1;
      
            usuarios[objetivo][0] = (" ");//ID
            usuarios[objetivo][1]=(" ");//Nombre
            usuarios[objetivo][2] = (" ");//Apellido
            usuarios[objetivo][3]=(" ");//Usuario
            usuarios[objetivo][4]=(" ");//ROL
            usuarios[objetivo][5]=(" ");// Contraseña
            
            JOptionPane.showMessageDialog(cu,"El Usuario se ha eliminado exitosamente");
            cu.setVisible(false);


        }
        
        if(e.getSource()==bt16){
            
             boton ss =new boton(); 
            for(int i=0;i<contador;i++){
                    if(ss.usuarios[i][0].equals(t2.getText())){cu.setVisible(false); objetivo=i;EliminarUsuarios(); break;}if(h==0){JOptionPane.showMessageDialog(er,"No existe un Usuarios con esa ID");}
                }}
        
        if(e.getSource()==bt17){cu.setVisible(false);
            JOptionPane.showMessageDialog(er,"Proceso Cancelado, Regresando al Menú");}
        
        if(e.getSource()==bt18){
          Object CU[]= new Object[7];
            String a;
            
           
            
            
            CU[0]=t1.getText();
            CU[1]=t2.getText();
            CU[2]=t3.getText();
            CU[3]=t4.getText();
            CU[4]=combito.getSelectedItem();
            CU[5]=t5.getText();
            CU[6]=t6.getText();
            a=CU[5].toString();
            int c=0;
        
            String b=a.toUpperCase();
            char[] mayu =a.toCharArray();
            char[] mayus =b.toCharArray();
            boton s= new boton();
             s.z=1;
            
            
           
               
            
            
            
            if((CU[0].equals(""))||(CU[1].equals(""))||(CU[2].equals(""))||(CU[3].equals(""))||(CU[4].equals(""))||(CU[5].equals(""))||(CU[6].equals(""))){JOptionPane.showMessageDialog(cu,"Al menos un campo está vacio");}
                else{ 
                        if(mayu[0]==mayus[0]){if(a.length()>=6){
                            if(CU[5].equals(CU[6])){ s.usuarios[objetivo][0]=CU[0].toString();s.usuarios[objetivo][1]=(CU[1].toString());s.usuarios[objetivo][2]=(CU[2].toString());
                                s.usuarios[objetivo][3]=CU[3].toString();s.usuarios[objetivo][4]=CU[4].toString();s.usuarios[objetivo][5]=CU[5].toString();
                                    
                                    JOptionPane.showMessageDialog(cu,"El Usuario se ha modificado exitosamente");}
                                        else{JOptionPane.showMessageDialog(cu,"Las contraseñas no son iguales");}}
                                            else{JOptionPane.showMessageDialog(cu,"Error su contraseña no tiene como mínimo 6 caracteres");}}
                                                else{JOptionPane.showMessageDialog(cu,"Error su contraseña no empieza con mayuscula");}}
             
        }
        
        if(e.getSource()==bt19){cu.setVisible(false);
             JOptionPane.showMessageDialog(er,"Proceso Cancelado, Regresando al Menú");}
         
        if(e.getSource()==bt20){
          
            boton sss =new boton(); 
            for(int i=0;i<contador;i++){
                    if(sss.usuarios[i][0].equals(t2.getText())){h=1;cu.setVisible(false); objetivo=i;ModificarUsuarios(); break;}}if(h==0){JOptionPane.showMessageDialog(er,"No existe un Usuarios con esa ID");}
            t1.setText(usuarios[objetivo][0]);
            t2.setText(usuarios[objetivo][1]);
            t3.setText(usuarios[objetivo][2]);
            t4.setText(usuarios[objetivo][3]);
            combito.addItem(usuarios[objetivo][4]);
            t5.setText(usuarios[objetivo][5]);
            t6.setText(usuarios[objetivo][5]);
            
            
        }
        
        }
        
        public void CrearUsuarios(){
            
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
        
        public void VerUsuarios(){
            
       Tabla Ver = new Tabla();
       Ver.VerUsuarios();
       
       
       
       
}
      
        public void EliminarUsuarios(){
            cu = new JFrame();    
            cu.setLayout(new FlowLayout());      
            cu.setResizable(false);
            cu.setLocation((int) (125), 250);
            cu.setSize(540, 350);
            cu.setVisible(true);
                
            
            
            
            JLabel l1 = new JLabel("ELIMINAR USUARIO");
            l1.setBounds(220,10,200,10);
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
            
            
            
            
            t1 = new JTextField("");
            t1.setBounds(60,80,200,20);
            t1.enable(false);
            cu.setLayout(null);
            
            t2 = new JTextField("");
            t2.setBounds(60,130,200,20);
            t2.enable(false);
            cu.setLayout(null);
            
            t3 = new JTextField("");
            t3.setBounds(60,180,200,20);
            t3.enable(false);
            cu.setLayout(null);
            
            t4 = new JTextField("");
            t4.setBounds(60,230,200,20);
            t4.enable(false);
            cu.setLayout(null);
            
            t5 = new JTextField("");
            t5.setBounds(280,130,200,20);
            t5.enable(false);
            cu.setLayout(null);
            
            
            
           
            t7 = new JTextField("");
            t7.setBounds(280,80,200,20);
            t7.enable(false);
            cu.setLayout(null);
            
            bt15 = new JButton("ELIMINAR USUARIO");
            bt15.setBounds(80,270,160,40);
            cu.setLayout(null);
            bt15.addActionListener(this);
            
            bt17 = new JButton("CANCELAR");
            bt17.setBounds(300,270,160,40);
            cu.setLayout(null);
            bt17.addActionListener(this);
           
            
            cu.add(bt15);cu.add(bt17);
            cu.add(t7);cu.add(t1);cu.add(t2);cu.add(t3);cu.add(t4);cu.add(t5);
            cu.add(l1);cu.add(l2);cu.add(l3);cu.add(l4);cu.add(l5);cu.add(l6);cu.add(l7);
            
            boton s= new boton();
            t1.setText(s.usuarios[objetivo][0]);
            t2.setText(s.usuarios[objetivo][1]);
            t3.setText(s.usuarios[objetivo][2]);
            t4.setText(s.usuarios[objetivo][3]);
            t5.setText(s.usuarios[objetivo][4]);
            t7.setText(s.usuarios[objetivo][5]);
        }
        
        public void Eliminar(){
                cu = new JFrame();    
                cu.setLayout(new FlowLayout());      
                cu.setResizable(false);
                cu.setLocation((int) (125), 250);
                cu.setSize(350, 250);
                cu.setVisible(true);
                
                JLabel l1 = new JLabel("ELIMINAR USUARIO");
                l1.setBounds(130,10,200,10);
                cu.setLayout(null);
                
                JLabel l2 = new JLabel("ID");
                l2.setBounds(60,60,100,10);
                cu.setLayout(null);
                
                t2 = new JTextField("");
                t2.setBounds(60,110,220,20);
                cu.setLayout(null);
                
                bt16= new JButton("BUSCAR");
                bt16.setBounds(80,160,200,40);
                cu.setLayout(null);
                
                
                cu.add(l1);cu.add(l2);cu.add(bt16);cu.add(t2);
                bt16.addActionListener(this);
                
            
            }
               
        
        public void ModificarUsuarios(){
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
            t1.enable(false);
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
            
            bt18 = new JButton("GUARDAR USUARIO");
            bt18.setBounds(80,270,160,40);
            cu.setLayout(null);
            bt18.addActionListener(this);
            
            bt19 = new JButton("CANCELAR");
            bt19.setBounds(300,270,160,40);
            cu.setLayout(null);
            bt19.addActionListener(this);
            
            combito.addItem("estudiante");
            combito.addItem("catedrático");
            
            cu.add(bt18);cu.add(bt19);
            cu.add(combito);cu.add(t1);cu.add(t2);cu.add(t3);cu.add(t4);cu.add(t5);cu.add(t6);
            cu.add(l1);cu.add(l2);cu.add(l3);cu.add(l4);cu.add(l5);cu.add(l6);cu.add(l7);cu.add(l8);
        }
        
        public void Modificar(){
                cu = new JFrame();    
                cu.setLayout(new FlowLayout());      
                cu.setResizable(false);
                cu.setLocation((int) (125), 250);
                cu.setSize(350, 250);
                cu.setVisible(true);
                
                JLabel l1 = new JLabel("MODIFICAR USUARIO");
                l1.setBounds(130,10,200,10);
                cu.setLayout(null);
                
                JLabel l2 = new JLabel("ID");
                l2.setBounds(60,60,100,10);
                cu.setLayout(null);
                
                t2 = new JTextField("");
                t2.setBounds(60,110,220,20);
                cu.setLayout(null);
                
                bt20= new JButton("BUSCAR");
                bt20.setBounds(80,160,200,40);
                 bt20.addActionListener(this); 
                cu.setLayout(null);
               
                
                cu.add(l1);cu.add(l2);cu.add(bt20);cu.add(t2);
               
            
        }
        
        }
 
        
    
    
    
    
        
    

