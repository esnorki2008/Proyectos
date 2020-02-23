/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Arbol.Funciones;

import Arbol.ListaValores;
import Arbol.Valor;
import Elementos.TablaDeSimbolos;

/**
 *
 * @author Norki
 */
public class Print {

    public Print(ListaValores Contenido, TablaDeSimbolos Tabla) {
        int Size = Contenido.getContenido().size();
        for (int i = 0; i < Size; i++) {
            
            Valor Puntual = Contenido.getContenido().get(i);
            Puntual.Ejecutar(Tabla);
            
            System.out.println(Puntual.SalidaEjecucion());
        }
    }
}
