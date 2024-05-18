/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodo_arbol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
 

/**
 *
 * @author samuel
 */
public class Metodo_arbol {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Declarar lista para la tabla de transiciones 
        for (int i = 1; i < tabla_transiciones.followsNodos.length; i++){
            tabla_transiciones.followsNodos[i] = new ArrayList<>();
        }
        
        String textoBase = "(d|b|c|a|E).(d|b).d*";
        String textoAnalizar = "(" + textoBase + ").#";
        
        funciones.abrirArchivoArbol(textoAnalizar);
        tabla_transiciones.abrirArchivoTransiciones(textoAnalizar);
        

        Analizadores.Sintactico parser;
        try {
            parser = new Analizadores.Sintactico(new Analizadores.Lexico(new BufferedReader(new StringReader(textoAnalizar))));
            parser.parse(); 
            
            System.out.println("Hola");
   
        } catch (Exception e) {
            System.out.println("Error fatal en compilación de entrada.");
        }      
        

        funciones.escribirDotArbol("\n    { rank = same; " + funciones.sameRank + " }\n}");
        funciones.generarSVG();
        
        
        // Tabla de transiciones y siguientes
        tabla_transiciones.realizarTransiciones(); 
        tabla_transiciones.generarTablaSiguientes(textoAnalizar);
        tabla_transiciones.imprimirTabla();
        
        
        
        
        
    }
}