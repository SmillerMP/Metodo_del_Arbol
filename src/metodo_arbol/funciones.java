/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_arbol;

import Clases.nodos;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author samuel
 */
public class funciones {
    
    public static nodos ultimoNodo;
    public static String sameRank = "";
    public static int numeroHoja = 1;
    
    
    public static void limpiarElementos(){
        ultimoNodo = null;
        sameRank = "";
        numeroHoja = 1;
    }
  
    public static void crearHoja(nodos hijo) {
        hijo.setNivel(0);
        
        if (hijo.getValor().equals("E")) {
            hijo.setAnulable(true);
            
        } else {
            hijo.setAnulable(false);
            hijo.setFirstPost(numeroHoja);
            hijo.setLastPost(numeroHoja);     
            tabla_transiciones.valoresNodos[numeroHoja] = hijo.getValor();
            numeroHoja ++;
        }  
   
        escribirDotArbol("    \"" + hijo + "\" [label=\"First Post \\n" + hijo.getFirstPost()+ " | Nodo: " +
                hijo.getValor() + " \\n Anulable: " + hijo.getAnulable() + "| Last Post \\n " + hijo.getLastPost() + "\", fontsize=11]");
        sameRank += "\"" + hijo + "\"; ";
           
    }
    

    public static void crearUnion(nodos nodoPadre, Object hIzq, Object hDer) {
        nodos hijoIzquierda = ((nodos) hIzq);
        nodos hijoDerecha = ((nodos) hDer);
        
        //--------------------------------------------------------------------------------
        // MANEJO DE NIVELS, ANULABILIDAD Y POSTS
        if (hijoIzquierda.getNivel() >= hijoDerecha.getNivel()) {
            nodoPadre.setNivel(hijoIzquierda.getNivel() + 1);
        } else {
            nodoPadre.setNivel(hijoDerecha.getNivel() + 1);
        }
        
        ultimoNodo = nodoPadre;
        
        if (nodoPadre.getValor().equals("|")) {
            if (hijoIzquierda.getAnulable() == true || hijoDerecha.getAnulable() == true){
                nodoPadre.setAnulable(true);
            }else {
                nodoPadre.setAnulable(false);
                
            }
            
            // FIRST POST
            agregarAListas(nodoPadre, hijoIzquierda, "first");
            agregarAListas(nodoPadre, hijoDerecha, "first");
            
            // LAST POST    
            agregarAListas(nodoPadre, hijoIzquierda, "last");
            agregarAListas(nodoPadre, hijoDerecha, "last");
            
            
        } else if (nodoPadre.getValor().equals(".")) {
            if (hijoIzquierda.getAnulable() == true && hijoDerecha.getAnulable() == true){
                nodoPadre.setAnulable(true);
            }else {
                nodoPadre.setAnulable(false);
            }
            
            // FIRST POST
            if (hijoIzquierda.getAnulable() == true) {     
                agregarAListas(nodoPadre, hijoIzquierda, "first");    
                agregarAListas(nodoPadre, hijoDerecha, "first");
            } else {
                agregarAListas(nodoPadre, hijoIzquierda, "first");  
            }
            
            // LAST POST
            if (hijoDerecha.getAnulable() == true){
                agregarAListas(nodoPadre, hijoIzquierda, "last");    
                agregarAListas(nodoPadre, hijoDerecha, "last");
            } else {
                agregarAListas(nodoPadre, hijoDerecha, "last");
            }
            
            // FOLLOW POST
            for ( int destino: hijoIzquierda.getLastPost()){            
                for (int fuente: hijoDerecha.getFirstPost()){
                    if (!tabla_transiciones.followsNodos[destino].contains(fuente)){
                        tabla_transiciones.followsNodos[destino].add(fuente);
                    }
                }
            }

        }
        
        //--------------------------------------------------------------------------------
        
        escribirDotArbol("    \"" + nodoPadre + "\" [label=\"First Post \\n" + nodoPadre.getFirstPost()+ " | Nodo: \\" +
                nodoPadre.getValor() + " \\n Anulable: " + nodoPadre.getAnulable() + "| Last Post \\n " + nodoPadre.getLastPost() + "\", fontsize=11]");
        escribirDotArbol("    \"" + nodoPadre + "\" -> \"" + hijoIzquierda + "\"");
        escribirDotArbol("    \"" + nodoPadre + "\" -> \"" + hijoDerecha + "\"");
                   
    }
    
    public static void conectarNodo(nodos nodoPadre, Object hIzq) {

        nodos hijoIzquierda = ((nodos) hIzq);
        
        nodoPadre.setNivel(hijoIzquierda.getNivel() + 1);        
        
        if (nodoPadre.getValor().equals("*")) {
            nodoPadre.setAnulable(true);
            
            // FOLLOW POST
            for ( int destino: hijoIzquierda.getLastPost()){            
                for (int fuente: hijoIzquierda.getFirstPost()){
                    if (!tabla_transiciones.followsNodos[destino].contains(fuente)){
                        tabla_transiciones.followsNodos[destino].add(fuente);
                    }
                }
            }
            
        } else if (nodoPadre.getValor().equals("?")) {
            nodoPadre.setAnulable(true);
            
        } else if (nodoPadre.getValor().equals("+")) {
            if (hijoIzquierda.getAnulable() == true){
                nodoPadre.setAnulable(true);
                
            } else {
                nodoPadre.setAnulable(false);
            }
            
            // FOLLOW POST
            for ( int destino: hijoIzquierda.getLastPost()){            
                for (int fuente: hijoIzquierda.getFirstPost()){
                    if (!tabla_transiciones.followsNodos[destino].contains(fuente)){
                        tabla_transiciones.followsNodos[destino].add(fuente);
                    }
                }
            }
        }
        
        // no se verifica ya que tipo de cerradura es ya que al ser un unico valor
        // primera y ultima post son las mismas al hijo
        // FIRST POST
        agregarAListas(nodoPadre, hijoIzquierda, "first");    
        
        // LAST POST
        agregarAListas(nodoPadre, hijoIzquierda, "last");    
        //--------------------------------------------------------------------------------------------------
            
        escribirDotArbol("    \"" + nodoPadre + "\" [label=\"First Post \\n" + nodoPadre.getFirstPost()+ " | Nodo: \\" +
                nodoPadre.getValor() + " \\n Anulable: " + nodoPadre.getAnulable() + "| Last Post \\n " + nodoPadre.getLastPost() + "\", fontsize=11]");
        escribirDotArbol("    \"" + nodoPadre + "\" -> \"" + hijoIzquierda + "\"");
         
    }
    
    
    // FUNCION PARA ESCRIBIR EN EL ARCHIVO .DOR DEL ARBOL
    public static void escribirDotArbol(String linea) {
        
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
        
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);
            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println(linea);
            Escribir.close();
        } catch (Exception e) {
        }    
    }
    
    public static void agregarAListas(nodos nodoDestino, nodos nodoBase, String post) {
        
        if (post.equals("last")) {
            for ( int last: nodoBase.getLastPost()){
                nodoDestino.setLastPost(last);
            }
        } else if (post.equals("first")){
            for ( int last: nodoBase.getFirstPost()){
                nodoDestino.setFirstPost(last);
            }
        }
    }
    
    public static void abrirArchivoArbol(String titulo){
        File carpeta = new File("./Reportes");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta y sus subcarpetas si no existen
        }

        File archivo = new File("./Reportes/arbol.dot");
        archivo.delete();
        if (!archivo.exists()) {

            funciones.escribirDotArbol("digraph Expresion{ \n"
                    + "    fontname=\"Arial \"\n"
                    + "    fontsize=20\n"
                    + "    rankdir = TB\n"
                    + "    splines = line\n"
                    + "    label = \"" + titulo +"\" \n"
                    + "    labelloc = \"t\"\n"
                    + "    node[shape=record, fontname=\"Arial\", fontsize=15]\n");
        }
        
    }
    
    public static void generarSVG(){
        
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (Metodo_arbol.sistemaOperativo.equals("linux")){
            String comando = "dot -Tsvg ./Reportes/arbol.dot -o ./Reportes/arbol.svg "; 
            processBuilder.command("bash", "-c", comando);
        } else {
            processBuilder.command("cmd.exe", "/c", "dot -Tsvg", ".\\Reportes\\arbol.dot", "-o", ".\\Reportes\\arbol.svg");
        }
        

        try {
            // Inicia el proceso
            Process proceso = processBuilder.start();
            int exitCode = proceso.waitFor();       
        } catch (IOException | InterruptedException e) {
        }
    }
    
}
