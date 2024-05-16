/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodo_arbol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import Clases.nodos;

 

/**
 *
 * @author samuel
 */
public class Metodo_arbol {
    
    public static ArrayList<nodos>[] listaNiveles = new ArrayList[15];
        
        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        for (int i= 0; i < listaNiveles.length; i++){
            listaNiveles[i] = new ArrayList<>();
        }
        
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
                    + "    splines = line\n"
                    + "    node[shape=circle, fontname=\"Arial\", fontsize=15]\n");

                Escribir.close();
            } catch (Exception e) {
            }    
        }
        
        
        String textoAnalizar = "(a|b.c|c.d).(a|b.c.r|c.d)*.(h|E)";

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
            
            NuevaLinea.print("\n");
            
            String sameRank = "";
            for (int i = 0; i < listaNiveles.length; i++){
                if (!listaNiveles[i].isEmpty()) {
                    for(int j = 0; j < listaNiveles[i].size(); j++){
                        sameRank += "\"" + listaNiveles[i].get(j)  + "\"; ";
                    }
                    NuevaLinea.println("{ rank = same; " + sameRank + " }");
                    sameRank = "";
                    
                } else {
                    break;
                }
                
            }
            
            
            NuevaLinea.println("}");

            Escribir.close();
            //JOptionPane.showMessageDialog(null, "El Reporte se creo Satisfactoriamente", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }    
        
        
        String comando = "dot -Tsvg ./Reportes/arbol.dot -o ./Reportes/arbol.svg "; 
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", comando);

        try {
            // Inicia el proceso
            Process proceso = processBuilder.start();
            int exitCode = proceso.waitFor();       
        } catch (IOException | InterruptedException e) {
        }
        
        for (int i = 0; i < listaNiveles.length; i++){
            System.out.println("Nivel " + i + " --> " + listaNiveles[i]);
        }
        
        
        
    }
    
}
