/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

/**
 *
 * @author samuel
 */
public class nodos {
    String tipo;
    String valor;
    String padre;

    public nodos(String tipo, String valor, String padre) {
        this.tipo = tipo;
        this.valor = valor;
        this.padre = padre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }
    
}
