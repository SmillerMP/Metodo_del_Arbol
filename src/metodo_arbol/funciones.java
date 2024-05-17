/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_arbol;

import Clases.nodos;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author samuel
 */
public class funciones {
    
    public static String sameRank = "";
    public static nodos ultimoNodo;
    public static int numeroHoja = 1;
    
  
    public static void crearHoja(nodos dato) {
        dato.setNivel(0);
        
        if (dato.getValor().equals("E")) {
            dato.setAnulable(true);
            
        } else {
            dato.setAnulable(false);
            dato.setFirstPost(numeroHoja);
            dato.setLastPost(numeroHoja);     
            tabla_transiciones.valoresNodos[numeroHoja] = dato.getValor();
            numeroHoja ++;
        }  
   
        escribirDotArbol("    \"" + dato + "\" [label=\"First Post \\n" + dato.getFirstPost()+ " | Nodo: " +
                dato.getValor() + " \\n Anulable: " + dato.getAnulable() + "| Last Post \\n " + dato.getLastPost() + "\", fontsize=11]");
        sameRank += "\"" + dato + "\"; ";
           
    }
    

    public static void crearUnion(nodos nodoFinal, Object dato1, Object dato2) {
        nodos datoN1 = ((nodos) dato1);
        nodos datoN2 = ((nodos) dato2);
        
        //--------------------------------------------------------------------------------
        // MANEJO DE NIVELS, ANULABILIDAD Y POSTS
        if (datoN1.getNivel() >= datoN2.getNivel()) {
            nodoFinal.setNivel(datoN1.getNivel() + 1);
        } else {
            nodoFinal.setNivel(datoN2.getNivel() + 1);
        }
        
        ultimoNodo = nodoFinal;
        
        if (nodoFinal.getValor().equals("|")) {
            if (datoN1.getAnulable() == true || datoN2.getAnulable() == true){
                nodoFinal.setAnulable(true);
            }else {
                nodoFinal.setAnulable(false);
                
            }
            
            // FIRST POST
            agregarAListas(nodoFinal, datoN1, "first");
            agregarAListas(nodoFinal, datoN2, "first");
            
            // LAST POST    
            agregarAListas(nodoFinal, datoN1, "last");
            agregarAListas(nodoFinal, datoN2, "last");
            
            
        } else if (nodoFinal.getValor().equals(".")) {
            if (datoN1.getAnulable() == true && datoN2.getAnulable() == true){
                nodoFinal.setAnulable(true);
            }else {
                nodoFinal.setAnulable(false);
            }
            
            // FIRST POST
            if (datoN1.getAnulable() == true) {     
                agregarAListas(nodoFinal, datoN1, "first");    
                agregarAListas(nodoFinal, datoN2, "first");
            } else {
                agregarAListas(nodoFinal, datoN1, "first");  
            }
            
            // LAST POST
            if (datoN2.getAnulable() == true){
                agregarAListas(nodoFinal, datoN1, "last");    
                agregarAListas(nodoFinal, datoN2, "last");
            } else {
                agregarAListas(nodoFinal, datoN2, "last");
            }
            
            // FOLLOW POST
            for ( int destino: datoN1.getLastPost()){            
                for (int fuente: datoN2.getFirstPost()){
                    if (!tabla_transiciones.followsNodos[destino].contains(fuente)){
                        tabla_transiciones.followsNodos[destino].add(fuente);
                    }
                }
            }

        }
        
        //--------------------------------------------------------------------------------
        
        escribirDotArbol("    \"" + nodoFinal + "\" [label=\"First Post \\n" + nodoFinal.getFirstPost()+ " | Nodo: \\" +
                nodoFinal.getValor() + " \\n Anulable: " + nodoFinal.getAnulable() + "| Last Post \\n " + nodoFinal.getLastPost() + "\", fontsize=11]");
        escribirDotArbol("    \"" + nodoFinal + "\" -> \"" + datoN1 + "\"");
        escribirDotArbol("    \"" + nodoFinal + "\" -> \"" + datoN2 + "\"");
                   
    }
    
    public static void conectarNodo(nodos nodoFinal, Object dato1) {

        nodos datoN1 = ((nodos) dato1);
        
        nodoFinal.setNivel(datoN1.getNivel() + 1);        
        
        if (nodoFinal.getValor().equals("*")) {
            nodoFinal.setAnulable(true);
            
            // FOLLOW POST
            for ( int destino: datoN1.getLastPost()){            
                for (int fuente: datoN1.getFirstPost()){
                    if (!tabla_transiciones.followsNodos[destino].contains(fuente)){
                        tabla_transiciones.followsNodos[destino].add(fuente);
                    }
                }
            }
            
        } else if (nodoFinal.getValor().equals("?")) {
            nodoFinal.setAnulable(true);
            
        } else if (nodoFinal.getValor().equals("+")) {
            if (datoN1.getAnulable() == true){
                nodoFinal.setAnulable(true);
                
            } else {
                nodoFinal.setAnulable(false);
            }
            
            // FOLLOW POST
            for ( int destino: datoN1.getLastPost()){            
                for (int fuente: datoN1.getFirstPost()){
                    if (!tabla_transiciones.followsNodos[destino].contains(fuente)){
                        tabla_transiciones.followsNodos[destino].add(fuente);
                    }
                }
            }
        }
        
        // no se verifica ya que tipo de cerradura es ya que al ser un unico valor
        // primera y ultima post son las mismas al hijo
        // FIRST POST
        agregarAListas(nodoFinal, datoN1, "first");    
        
        // LAST POST
        agregarAListas(nodoFinal, datoN1, "last");    
        //--------------------------------------------------------------------------------------------------
            
        escribirDotArbol("    \"" + nodoFinal + "\" [label=\"First Post \\n" + nodoFinal.getFirstPost()+ " | Nodo: \\" +
                nodoFinal.getValor() + " \\n Anulable: " + nodoFinal.getAnulable() + "| Last Post \\n " + nodoFinal.getLastPost() + "\", fontsize=11]");
        escribirDotArbol("    \"" + nodoFinal + "\" -> \"" + datoN1 + "\"");
         
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

            funciones.escribirDotArbol("digraph Gramatica{ \n"
                    + "    rankdir = TB\n"
                    + "    splines = line\n"
                    + "    label = \"" + titulo +"\" \n"
                    + "    labelloc = \"t\"\n"
                    + "    node[shape=record, fontname=\"Arial\", fontsize=15]\n");
        }
        
    }
    
}
