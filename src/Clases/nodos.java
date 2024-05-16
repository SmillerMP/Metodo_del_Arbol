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
public class nodos {
    String tipo;
    String valor;
    int nivel;
    ArrayList<Integer> firstPost;
    ArrayList<Integer> lastPost;

    public nodos(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.nivel = 0;
        this.firstPost = new ArrayList<>();
        this.lastPost = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public int getNivel() {
        return nivel;
    }
    
    
    
    
    // Metodos sets

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setFirstPost(int valor) {
        this.firstPost.add(valor);
    }

    public void setLastPost(int valor) {
        this.firstPost.add(valor);
    }
    
    
    
    
    
    
    
    
}
