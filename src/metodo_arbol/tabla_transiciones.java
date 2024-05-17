/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_arbol;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author samuel
 */


public class tabla_transiciones {
    public static String[] valoresNodos = new String[50];
    public static ArrayList<Integer>[] followsNodos = new ArrayList[50];
    
    
    public static void imprimirTabla(){
         // IMPRESION TABLA DE TRANSICIONES
        for (int i = 1; i<followsNodos.length; i++){
            if (!followsNodos[i].isEmpty()){
                System.out.println("Simbolo: " + valoresNodos[i] + "  i: " + i + "  Siguientes: " + followsNodos[i]);
            } else {
                break;
            }
        }
    }
    
}
