/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author samuel
 */
public class estados_pendientes {
    String estado;
    ArrayList<Integer> listaSiguientes;

    public estados_pendientes(String estado, ArrayList<Integer> listaSiguientes) {
        this.estado = estado;
        this.listaSiguientes = listaSiguientes;
    }
    
    // Metodos Get
    public String getEstado() {
        return estado;
    }
    
    public ArrayList<Integer> getListaSiguientes() {
        return listaSiguientes;
    }

}
