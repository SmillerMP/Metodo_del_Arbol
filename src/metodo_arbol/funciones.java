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
            
            sameRank += "\"" + dato + "\"; ";
           
        } catch (Exception e) {
        }  
        
        dato.setNivel(0);
        Metodo_arbol.listaNiveles[0].add(dato);
        
                
    }
    
    
    public static void crearUnion(nodos nodoFinal, Object dato1, Object dato2) {
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
        
        nodos datoN1 = ((nodos) dato1);
        nodos datoN2 = ((nodos) dato2);
        
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("    \"" + nodoFinal + "\" [label=\"" + nodoFinal.getValor() +"\", fontsize=15]");
            NuevaLinea.println("    \"" + nodoFinal + "\" -> \"" + datoN1 + "\"");
            NuevaLinea.println("    \"" + nodoFinal + "\" -> \"" + datoN2 + "\"");

            Escribir.close();
            //JOptionPane.showMessageDialog(null, "El Reporte se creo Satisfactoriamente", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }  
        
        if (datoN1.getNivel() >= datoN2.getNivel()) {
            nodoFinal.setNivel(datoN1.getNivel() + 1);
        } else {
            nodoFinal.setNivel(datoN2.getNivel() + 1);
        }
        
        Metodo_arbol.listaNiveles[nodoFinal.getNivel()].add(nodoFinal);
            
                
    }
    
    public static void conectarNodo(nodos nodoFinal, Object dato1) {
        File archivo = new File("./Reportes/arbol.dot");
        FileWriter Escribir;
        PrintWriter NuevaLinea;
        
        nodos datoN1 = ((nodos) dato1);
        
        try {
            archivo.createNewFile();
            Escribir = new FileWriter(archivo, true);

            NuevaLinea = new PrintWriter(Escribir);
            NuevaLinea.println("    \"" + nodoFinal + "\" [label=\"" + nodoFinal.getValor() +"\", fontsize=15]");
            NuevaLinea.println("    \"" + nodoFinal + "\" -> \"" + datoN1 + "\"");

            Escribir.close();
            //JOptionPane.showMessageDialog(null, "El Reporte se creo Satisfactoriamente", "Creacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
        }  
        
        nodoFinal.setNivel(datoN1.getNivel() + 1);
        Metodo_arbol.listaNiveles[datoN1.getNivel() + 1].add(nodoFinal);
                
    }
    
}
