/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_arbol;

import Clases.transiciones;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import static metodo_arbol.tabla_transiciones.followsNodos;
import static metodo_arbol.tabla_transiciones.valoresNodos;

/**
 *
 * @author samuel
 */
public class automata {
    public static ArrayList<transiciones> listaAutomata = new ArrayList<>();

    String titulo;
    public automata(String titulo) {
        this.titulo = titulo;
    }
    
    void limpiarElementos() {
        listaAutomata.clear();
    }
    
    void crearAutomata(){    
        File carpeta = new File("./Reportes");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta y sus subcarpetas si no existen
        }

        File archivo = new File("./Reportes/automata.dot");
        archivo.delete();
        if (!archivo.exists()) {
            
            FileWriter Escribir;
            PrintWriter NuevaLinea;
        
            try {
                archivo.createNewFile();
                Escribir = new FileWriter(archivo, true);
                NuevaLinea = new PrintWriter(Escribir);
                
                NuevaLinea.println("digraph Automata{ \n"
                    + "    fontname=\"Arial \"\n"
                    + "    fontsize=12\n"
                    + "    rankdir = LR\n"
                    + "    label = \"Automata: " + titulo +"\" \n"
                    + "    labelloc = \"t\"\n"
                    + "    node[shape=circle, fontname=\"Arial\", fontsize=15]\n");
                
                
                for (transiciones movimiento: listaAutomata){                                  
                    if (movimiento.getSiguienteEstado() == null){
                        NuevaLinea.println("    \"" + movimiento.getEstado() + "\" [shape=doublecircle, style=filled, fillcolor=firebrick1]");
                    } else {
                        NuevaLinea.println("    \"" + movimiento.getEstado() + "\" -> \"" + movimiento.getSiguienteEstado() + 
                                "\" [label = \"" + movimiento.getTransicion() + "\"];");
                    }
                }
                
                NuevaLinea.println("    \"" + listaAutomata.get(0).getEstado() + "\" [style=filled, fillcolor=limegreen]");

                NuevaLinea.println("}");
                
                Escribir.close();
            } catch (IOException e) {
            }
        }
        
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (Metodo_arbol.sistemaOperativo.equals("linux")){
            String comando = "dot -Tsvg ./Reportes/automata.dot -o ./Reportes/automata.svg "; 
            processBuilder.command("bash", "-c", comando);
        } else {
            processBuilder.command("cmd.exe", "/c", "dot -Tsvg", ".\\Reportes\\automata.dot", "-o", ".\\Reportes\\automata.svg");
        }
        
       
        try {
            // Inicia el proceso
            Process proceso = processBuilder.start();
            int exitCode = proceso.waitFor();       
        } catch (IOException | InterruptedException e) {
        }
    }
    
}
