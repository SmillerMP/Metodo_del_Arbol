/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodo_arbol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringReader;

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
        
        
        File carpeta = new File("./Reportes");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta y sus subcarpetas si no existen
        }

        File archivo = new File("./Reportes/arbol.dot");

        FileWriter Escribir;
        PrintWriter NuevaLinea;
        archivo.delete();
        if (!archivo.exists()) {

            try {

                archivo.createNewFile();
                Escribir = new FileWriter(archivo, true);

                NuevaLinea = new PrintWriter(Escribir);
                NuevaLinea.println("digraph Gramatica{ \n"
                    + "    rankdir = TB\n"
                    + "    node[shape=circle, fontname=\"Arial\", fontsize=15]\n");

                Escribir.close();
            } catch (Exception e) {
            }    
        }
        
        
        String textoAnalizar = "( a.b | b. (a.d*) | c)";

        Analizadores.Sintactico parser;
        try {
            parser = new Analizadores.Sintactico(new Analizadores.Lexico(new BufferedReader(new StringReader(textoAnalizar))));
            parser.parse(); 
            
            System.out.println("Hola");
 

            
            
        } catch (Exception e) {
            System.out.println("Error fatal en compilación de entrada.");
        }      
        
        
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("}");

            Escribir.close();
            //JOptionPane.showMessageDialog(null, "El Reporte se creo Satisfactoriamente", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }    
        
        
        
        
    }
    
}
