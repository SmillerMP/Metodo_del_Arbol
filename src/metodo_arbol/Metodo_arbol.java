/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package metodo_arbol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
 

/**
 *
 * @author samuel
 */
public class Metodo_arbol {
    public static String sistemaOperativo = System.getProperty("os.name").toLowerCase();        

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         
        
        if (sistemaOperativo.contains("linux")) {
            System.out.println("Sistema GNU/Linux detectado.");
        } else if (sistemaOperativo.contains("windows")) {
            System.out.println("Sistema Windows detectado.");
        }else {
            System.out.println("El sistema operativo es otro: " + sistemaOperativo);
        } 
        
        gui guiInicial = new gui();
        guiInicial.setVisible(true);

    }
}