/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras.NoLineales;

/**
 *
 * @author lizandro
 */
public class MiniHash{
        private int Clave;
        private Object Contenido;
        MiniHash(int Clave,Object Contenido){
            this.Clave=Clave;
            this.Contenido=Contenido;
        }
        public int getClave(){
            return Clave;
        }
        public Object getContenido(){
            return Contenido;
        }
    }