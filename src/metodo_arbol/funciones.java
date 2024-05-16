/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_arbol;

import Clases.nodos;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author samuel
 */
public class funciones {
    
    public static ArrayList<nodos> listaNodosHojas = new ArrayList<>();
    public static String sameRank = "";
    
  
    public static void crearHoja(nodos dato) {
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
                
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("    \"" + dato + "\" [label=\"" + dato.getValor() +"\", fontsize=15]");

            Escribir.close();
            
            listaNodosHojas.add(dato);
            sameRank += "\"" + dato + "\"; ";
        } catch (Exception e) {
        }  
                
    }
    
    
    public static void crearUnion(nodos nodoFianl, Object dato1, Object dato2) {
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
        
        
        dato1 = ((nodos) dato1);
        dato2 = ((nodos) dato2);
        
        
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("    \"" + nodoFianl + "\" [label=\"" + nodoFianl.getValor() +"\", fontsize=15]");
            NuevaLinea.println("    \"" + nodoFianl + "\" -> \"" + dato1 + "\"");
            NuevaLinea.println("    \"" + nodoFianl + "\" -> \"" + dato2 + "\"");

            Escribir.close();
            //JOptionPane.showMessageDialog(null, "El Reporte se creo Satisfactoriamente", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }  
                
    }
    
    public static void conectarNodo(nodos nodoFianl, Object dato1) {
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
        
        
        dato1 = ((nodos) dato1);
        
        
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("    \"" + nodoFianl + "\" [label=\"" + nodoFianl.getValor() +"\", fontsize=15]");
            NuevaLinea.println("    \"" + nodoFianl + "\" -> \"" + dato1 + "\"");

            Escribir.close();
            //JOptionPane.showMessageDialog(null, "El Reporte se creo Satisfactoriamente", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }  
                
    }
    
}
