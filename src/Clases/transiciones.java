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
public class transiciones {
    String estado;
    String transicion;
    String siguienteEstado;

    public transiciones(String estado, String transicion, String siguienteEstado) {
        this.estado = estado;
        this.transicion = transicion;
        this.siguienteEstado = siguienteEstado;
    }


    public String getEstado() {
        return estado;
    }

    public String getSiguienteEstado() {
        return siguienteEstado;
    }

    public String getTransicion() {
        return transicion;
    }
    
    
}
