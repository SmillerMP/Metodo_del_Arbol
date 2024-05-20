/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package metodo_arbol;

import java.util.ArrayList;
import java.util.HashMap;
import Clases.estados_pendientes;
import Clases.transiciones;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 *
 * @author samuel
 */


public class tabla_transiciones {
    public static String[] valoresNodos = new String[50];;
    public static ArrayList<Integer>[] followsNodos = new ArrayList[50];
 
    
    // Variables locales
    boolean inicio;
    HashMap<ArrayList, String> estadosCreados;
    ArrayList<estados_pendientes> estadosPorVerificar;
    int sumadorEstados;
    String titulo;

    public tabla_transiciones(String titulo) {
        this.titulo = titulo;
        this.inicio = true;
        this.estadosCreados = new HashMap<>();
        this.estadosPorVerificar = new ArrayList<>();
        sumadorEstados = 0;
    } 
    
    
    void limpiarElementos(){
        for (int i = 0; i < valoresNodos.length; i++){
            valoresNodos[i] = null;
            followsNodos[i] = null;
        }
    }
     
    void imprimirTabla(){
        // IMPRESION Tabla de siguientes
        for (int i = 1; i<followsNodos.length; i++){
            if (!followsNodos[i].isEmpty()){
                System.out.println("Simbolo: " + valoresNodos[i] + "  i: " + i + "  Siguientes: " + followsNodos[i]);
            } else {
                System.out.println("Simbolo: " + valoresNodos[i] + "  i: " + i + "  Siguientes: Aceptacion" );
                break;
            }
        }
    }
    
    
    // Realiza el archivo transiciones.txt
    // va realizando las transiciones para el automata
    void realizarTransiciones() {
        
        // verifica que sea el inicio del arbol, solo se ejecutara una vez
        if (inicio){ 
            estadosPorVerificar.add(new estados_pendientes("S" + String.valueOf(sumadorEstados), funciones.ultimoNodo.getFirstPost()));
            estadosCreados.put(funciones.ultimoNodo.getFirstPost(), "S" + String.valueOf(sumadorEstados));
            sumadorEstados++;
            inicio = false;
        }
         
        // estadosPorVerificar es una lista del tipo tabla_transiciones temporal que alamacena los estados que son necesarios verificar
        if (!estadosPorVerificar.isEmpty()) {
            String estadoActual = estadosPorVerificar.get(0).getEstado();
            escribirTxtTransiciones("\n" + estadoActual + " = " + estadosPorVerificar.get(0).getListaSiguientes());
            ArrayList<String> listaValoresEstado = new ArrayList<>();
        
            // recorre la lista de los estados por verificar
            for (int valores : estadosPorVerificar.get(0).getListaSiguientes()){
                
                // verifica si es necesario realizar uniones en transiciones
                // por ejemplo S0 = [1,2]; 1,2 = a; S1 = siguientes(1) U siguiente(2);
                if (!listaValoresEstado.contains(valoresNodos[valores])) {
                    if (verificarUnion(valoresNodos[valores])){
                        listaValoresEstado.add(valoresNodos[valores]);
                        continue;
                    }
                } else {
                    continue;
                }
                
                String estadoSiguiente = "";
                
                // Verifica que el estado S# no haya sido creado antes si ya fue creado buscara
                // en el hashmap el estado que corresponde a la lista de valores 
                if (!estadosCreados.containsKey(followsNodos[valores]) && !followsNodos[valores].isEmpty()){
                    
                    estadoSiguiente = "S" + String.valueOf(sumadorEstados);
                    estadosCreados.put(followsNodos[valores], estadoSiguiente);
                    estadosPorVerificar.add(new estados_pendientes(estadoSiguiente, followsNodos[valores]));
                    sumadorEstados++;

                } else {
                    estadoSiguiente = estadosCreados.get(followsNodos[valores]);
                }
                
                if (!valoresNodos[valores].equals("#")){
                    automata.listaAutomata.add(new transiciones(estadoActual, valoresNodos[valores], estadoSiguiente));
                    escribirTxtTransiciones("Transicion[ " + estadoActual + ", " + valoresNodos[valores] +" ] = Siguiente(" 
                        + valores + ") = "+ followsNodos[valores] +  " = " + estadoSiguiente);
                } else {
                    automata.listaAutomata.add(new transiciones(estadoActual, valoresNodos[valores], null));
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
    
    
    boolean verificarUnion(String valorNodo) {
        ArrayList<Integer> siguientes = new ArrayList<>();
        ArrayList<Integer> listaUnion = new ArrayList<>();
        String estadoActual = estadosPorVerificar.get(0).getEstado();
        
        if (verificarRepitencia(valorNodo) > 1){
            for (int valores: estadosPorVerificar.get(0).getListaSiguientes()){
                if (valorNodo.equals(valoresNodos[valores])){
                    for (int follows: followsNodos[valores]){
                        if (!siguientes.contains(follows)){
                            siguientes.add(follows);
                        }
                    }
                    
                    listaUnion.add(valores);
                }
            }
            
            String estadoSiguiente = "";
            if (!estadosCreados.containsKey(siguientes)){

                estadoSiguiente = "S" + String.valueOf(sumadorEstados);
                estadosCreados.put(siguientes, estadoSiguiente);
                estadosPorVerificar.add(new estados_pendientes(estadoSiguiente, siguientes));
                sumadorEstados++;

            } else {
                estadoSiguiente = estadosCreados.get(siguientes);
            }
            
            automata.listaAutomata.add(new transiciones(estadoActual, valorNodo, estadoSiguiente));
            escribirTxtTransiciones("Transicion[ " + estadoActual + ", " + valorNodo +" ] = Siguiente( Union -> " 
                + listaUnion + " ) = "+ siguientes +  " = " + estadoSiguiente);
           
            return true;
        }      
        return false;   
    }
    
    int verificarRepitencia(String valorNodo) {
        int repetidos = 0;
        for (int valores : estadosPorVerificar.get(0).getListaSiguientes()){
            if (valorNodo.equals(valoresNodos[valores])){
                repetidos++;
            }
        }
        
        return repetidos;
    }
    
    
    void abrirArchivoTransiciones(){
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
    
    
    void escribirTxtTransiciones(String linea) {
        
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
     
     
    void generarTablaSiguientes(){
        File carpeta = new File("./Reportes");
        if (!carpeta.exists()) {
            carpeta.mkdirs(); // Crea la carpeta y sus subcarpetas si no existen
        }

        File archivo = new File("./Reportes/siguientes.dot");
        archivo.delete();
        if (!archivo.exists()) {
            
            FileWriter Escribir;
            PrintWriter NuevaLinea;
        
            try {
                archivo.createNewFile();
                Escribir = new FileWriter(archivo, true);
                NuevaLinea = new PrintWriter(Escribir);
                
                //NuevaLinea.println(linea);
                NuevaLinea.println("digraph TablaSiguientes{ \n"
                    + "    fontname=\"Arial\"\n"
                    + "    fontsize=15\n"
                    + "    labelloc = \"t\"\n"
                    + "    label = \" Tabla de siguientes: " + titulo +"\" \n"
                    + "    node[fontname=\"Arial\", fontsize=15]\n");
                
                NuevaLinea.println("    tabla [shape=none label=<");
                NuevaLinea.println("    <TABLE border=\"1\" cellspacing=\"3\" cellpadding=\"13\">");
                
                NuevaLinea.println("    <TR>\n" +
                "      <TD>Simbolo</TD>\n" +
                "      <TD>i</TD>\n" +
                "      <TD>Siguientes</TD>\n" +
                "    </TR>");
                
                boolean salir = false;
                for (int i = 1; i<followsNodos.length; i++){
                   
                    NuevaLinea.println("\n    <TR>");
                    if (!followsNodos[i].isEmpty()){
                        NuevaLinea.println("        <TD>" + valoresNodos[i] + "</TD>");
                        NuevaLinea.println("        <TD>" + i + "</TD>");
                        NuevaLinea.println("        <TD>" + followsNodos[i] + "</TD>");
                    } else {
                        NuevaLinea.println("        <TD>" + valoresNodos[i] + "</TD>");
                        NuevaLinea.println("        <TD>" + i + "</TD>");
                        NuevaLinea.println("        <TD> Aceptacion </TD>");
                        salir = true;
                    } 
                    
                    NuevaLinea.println("    </TR>");
                    
                    if (salir){
                        break;
                    }
                }
                
                NuevaLinea.println("    </TABLE>>]; \n}");

                Escribir.close();
            } catch (Exception e) {
            }
        }
        
        ProcessBuilder processBuilder = new ProcessBuilder();

        if (Metodo_arbol.sistemaOperativo.equals("linux")){
            String comando = "dot -Tsvg ./Reportes/siguientes.dot -o ./Reportes/siguientes.svg "; 
            processBuilder.command("bash", "-c", comando);
        } else {
            processBuilder.command("cmd.exe", "/c", "dot -Tsvg", ".\\Reportes\\siguientes.dot", "-o", ".\\Reportes\\siguientes.svg");
        }
        

        try {
            // Inicia el proceso
            Process proceso = processBuilder.start();
            int exitCode = proceso.waitFor();       
        } catch (IOException | InterruptedException e) {
        }
    }
    
}
