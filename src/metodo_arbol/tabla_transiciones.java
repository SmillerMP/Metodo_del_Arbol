/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_arbol;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import Clases.nodos;
import Clases.estados_pendientes;
import Clases.transiciones;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
/**
 *
 * @author samuel
 */


public class tabla_transiciones {
    public static String[] valoresNodos = new String[50];
    public static ArrayList<Integer>[] followsNodos = new ArrayList[50];
 
    public static boolean inicio = true;
    public static HashMap<ArrayList, String> estadosCreados = new HashMap<>();
    public static ArrayList<estados_pendientes> estadosPorVerificar = new ArrayList<>();
    public static ArrayList<transiciones> listaTransiciones = new ArrayList<>();
    public static int sumadorEstados = 0;
    
   
    
    
    public static void imprimirTabla(){
        // IMPRESION TABLA DE TRANSICIONES
        for (int i = 1; i<followsNodos.length; i++){
            if (!followsNodos[i].isEmpty()){
                System.out.println("Simbolo: " + valoresNodos[i] + "  i: " + i + "  Siguientes: " + followsNodos[i]);
            } else {
                System.out.println("Simbolo: " + valoresNodos[i] + "  i: " + i + "  Siguientes: Aceptacion" );
                break;
            }
        }
    }
    
    
    public static void realizarTransiciones() {
        
        if (inicio){ 
            estadosPorVerificar.add(new estados_pendientes("S" + String.valueOf(sumadorEstados), funciones.ultimoNodo.getFirstPost()));
            estadosCreados.put(funciones.ultimoNodo.getFirstPost(), "S" + String.valueOf(sumadorEstados));
            sumadorEstados++;
            inicio = false;
        }
         
        if (!estadosPorVerificar.isEmpty()) {
            String estadoActual = estadosPorVerificar.get(0).getEstado();
            escribirTxtTransiciones("\n" + estadoActual + " = " + estadosPorVerificar.get(0).getListaSiguientes());
            ArrayList<String> listaValoresEstado = new ArrayList<>();
        
            for (int valores : estadosPorVerificar.get(0).getListaSiguientes()){
                
                if (!listaValoresEstado.contains(valoresNodos[valores])) {
                    if (verificarUnion(valoresNodos[valores])){
                        listaValoresEstado.add(valoresNodos[valores]);
                        continue;
                    }
                } else {
                    continue;
                }
                
                String estadoSiguiente = "";
                if (!estadosCreados.containsKey(followsNodos[valores]) && !followsNodos[valores].isEmpty()){
                    
                    estadoSiguiente = "S" + String.valueOf(sumadorEstados);
                    estadosCreados.put(followsNodos[valores], estadoSiguiente);
                    estadosPorVerificar.add(new estados_pendientes(estadoSiguiente, followsNodos[valores]));
                    sumadorEstados++;

                } else {
                    estadoSiguiente = estadosCreados.get(followsNodos[valores]);
                }
                
                if (!valoresNodos[valores].equals("#")){
                    listaTransiciones.add(new transiciones(estadoActual, valoresNodos[valores], followsNodos[valores], estadoSiguiente));
                    escribirTxtTransiciones("Transicion[ " + estadoActual + ", " + valoresNodos[valores] +" ] = Siguiente(" 
                        + valores + ") = "+ followsNodos[valores] +  " = " + estadoSiguiente);
                } else {
                    listaTransiciones.add(new transiciones(estadoActual, valoresNodos[valores], followsNodos[valores], null));
                    escribirTxtTransiciones("Transicion[ " + estadoActual + ", " + valoresNodos[valores] +" ] = Estado de Aceptacion");
                }

            }
            
            // vuelve a llamar a la funcion
            estadosPorVerificar.remove(0);
            listaValoresEstado.clear();
            realizarTransiciones();
            
        } else {
            System.out.println("transiciones terminadas");
        }    
    }
    
    
    public static boolean verificarUnion(String valorNodo) {
        ArrayList<Integer> siguientes = new ArrayList<>();
        
        if (verificarRepitencia(valorNodo) > 1){
            for (int valores: estadosPorVerificar.get(0).getListaSiguientes()){
                if (valorNodo.equals(valoresNodos[valores])){
                    for (int follows: followsNodos[valores]){
                        if (!siguientes.contains(follows)){
                            siguientes.add(follows);
                        }
                    }
                }
            }
            return true;
        }      
        return false;   
    }
    
    public static int verificarRepitencia(String valorNodo) {
        int repetidos = 0;
        for (int valores : estadosPorVerificar.get(0).getListaSiguientes()){
            if (valorNodo.equals(valoresNodos[valores])){
                repetidos++;
            }
        }
        
        return repetidos;
    }
    
    
    public static void abrirArchivoTransiciones(String titulo){
        File carpeta = new File("./Reportes");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta y sus subcarpetas si no existen
        }

        File archivo = new File("./Reportes/transiciones.txt");
        archivo.delete();
        if (!archivo.exists()) {

            escribirTxtTransiciones("Transiciones para: "+ titulo);
        }
        
    }
    
     public static void escribirTxtTransiciones(String linea) {
        
        File archivo = new File("./Reportes/transiciones.txt");
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
    
    
    
    // recorre la lista de numeros y retorna un string con esta lista
    //public static String conversionListaString()
    
}
