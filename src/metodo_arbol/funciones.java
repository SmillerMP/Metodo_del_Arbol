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
/**
 *
 * @author samuel
 */
public class funciones {
    
    public static ArrayList<nodos> nodosHojas = new ArrayList<>();
    public static String sameRank = "";
    public static nodos ultimoNodo;
    public static int numeroHoja = 1;
    
  
    public static void crearHoja(nodos dato) {
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
        
        dato.setNivel(0);
        
        if (dato.getValor().equals("E")) {
            dato.setAnulable(true);
        } else {
            dato.setAnulable(false);
            dato.setFirstPost(numeroHoja);
            dato.setLastPost(numeroHoja);         
            nodosHojas.add(dato);
            numeroHoja ++;
        }  
        
                
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("    \"" + dato + "\" [label=\"First Post \\n" + dato.getFirstPost()+ " | Nodo: " +
                    dato.getValor() + " \\n Anulable: " + dato.getAnulable() + "| Last Post \\n " + dato.getLastPost() + "\", fontsize=11]");

            Escribir.close();
            
            sameRank += "\"" + dato + "\"; ";
           
        } catch (Exception e) {
        }  
        
          
    }
    

    public static void crearUnion(nodos nodoFinal, Object dato1, Object dato2) {
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
        
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
        
        if (ultimoNodo.getValor().equals("|")) {
            if (datoN1.getAnulable() == true || datoN2.getAnulable() == true){
                ultimoNodo.setAnulable(true);
            }else {
                ultimoNodo.setAnulable(false);
                
            }
            
            // FIRST POST
            for ( int first: datoN1.getFirstPost()){
                ultimoNodo.setFirstPost(first);
            }
            for ( int first: datoN2.getFirstPost()){
                ultimoNodo.setFirstPost(first);
            }
            
            // LAST POST
            for ( int last: datoN1.getLastPost()){
                ultimoNodo.setLastPost(last);
            }
            for ( int last: datoN2.getLastPost()){
                ultimoNodo.setLastPost(last);
            }
            
        } else if (ultimoNodo.getValor().equals(".")) {
            if (datoN1.getAnulable() == true && datoN2.getAnulable() == true){
                ultimoNodo.setAnulable(true);
            }else {
                ultimoNodo.setAnulable(false);
            }
            
            // FIRST POST
            if (datoN1.getAnulable() == true) {                    
                for ( int first: datoN1.getFirstPost()){
                    ultimoNodo.setFirstPost(first);
                }
                for ( int first: datoN2.getFirstPost()){
                    ultimoNodo.setFirstPost(first);
                }               
            } else {
                for ( int first: datoN1.getFirstPost()){
                    ultimoNodo.setFirstPost(first);
                }
            }
            
            
            // LAST POST
            if (datoN2.getAnulable() == true){
                for ( int last: datoN1.getLastPost()){
                    ultimoNodo.setLastPost(last);
                }
                for ( int last: datoN2.getLastPost()){
                    ultimoNodo.setLastPost(last);
                }
            } else {
                for ( int last: datoN2.getLastPost()){
                    ultimoNodo.setLastPost(last);
                }
            }
        }
        
        //--------------------------------------------------------------------------------
        
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("    \"" + nodoFinal + "\" [label=\"First Post \\n" + nodoFinal.getFirstPost()+ " | Nodo: \\" +
                    nodoFinal.getValor() + " \\n Anulable: " + nodoFinal.getAnulable() + "| Last Post \\n " + nodoFinal.getLastPost() + "\", fontsize=11]");
            NuevaLinea.println("    \"" + nodoFinal + "\" -> \"" + datoN1 + "\"");
            NuevaLinea.println("    \"" + nodoFinal + "\" -> \"" + datoN2 + "\"");

            Escribir.close();
            //JOptionPane.showMessageDialog(null, "El Reporte se creo Satisfactoriamente", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }  
        
        
                   
                
    }
    
    public static void conectarNodo(nodos nodoFinal, Object dato1) {
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
        
        nodos datoN1 = ((nodos) dato1);
        
        //------------------------------------------------------------------------------------------------
        // MANEJO DE NIVELS, ANULABILIDAD Y POSTS
        nodoFinal.setNivel(datoN1.getNivel() + 1);
        ultimoNodo = nodoFinal;
        
        
        if (ultimoNodo.getValor().equals("*") || ultimoNodo.getValor().equals("?")) {
            ultimoNodo.setAnulable(true);
            
        } else if (ultimoNodo.getValor().equals("+")) {
            if (datoN1.getAnulable() == true){
                ultimoNodo.setAnulable(true);
                
            } else {
                ultimoNodo.setAnulable(false);
            }
        }
        
        // no verifico si es *, ? o + por que al ser de un unico dato los first y last siempre seran igual a su hijo
        // FIRST POST
        for ( int first: datoN1.getFirstPost()){
            ultimoNodo.setFirstPost(first);
        }

        // LAST POST
        for ( int last: datoN1.getLastPost()){
            ultimoNodo.setLastPost(last);
        }
        //--------------------------------------------------------------------------------------------------
        
        
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("    \"" + nodoFinal + "\" [label=\"First Post \\n" + nodoFinal.getFirstPost()+ " | Nodo: \\" +
                    nodoFinal.getValor() + " \\n Anulable: " + nodoFinal.getAnulable() + "| Last Post \\n " + nodoFinal.getLastPost() + "\", fontsize=11]");
            NuevaLinea.println("    \"" + nodoFinal + "\" -> \"" + datoN1 + "\"");

            Escribir.close();
            //JOptionPane.showMessageDialog(null, "El Reporte se creo Satisfactoriamente", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }  
        
       
                
    }
    
}
