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
    boolean anulable;

    public nodos(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.nivel = 0;
        this.firstPost = new ArrayList<>();
        this.lastPost = new ArrayList<>();
        this.anulable = false;
    }

    // Metodos Get
    public String getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    public int getNivel() {
        return nivel;
    }
    
    public boolean getAnulable() {
        return anulable;
    }

    public ArrayList<Integer> getFirstPost() {
        return firstPost;
    }

    public ArrayList<Integer> getLastPost() {
        return lastPost;
    }
    
    
    // Metodos sets
    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setFirstPost(int valor) {
        this.firstPost.add(valor);
    }

    public void setLastPost(int valor) {
        this.lastPost.add(valor);
    }

    public void setAnulable(boolean anulable) {
        this.anulable = anulable;
    }

    
    
}
