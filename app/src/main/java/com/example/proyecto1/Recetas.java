package com.example.proyecto1;

import org.w3c.dom.Text;

public class Recetas {
    private String titulo;
    private String pasos;
    public Recetas(String p1,String p2){
        this.titulo=p1;
        this.pasos=p2;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String x) {
        this.titulo = x;
    }

    public String getPasos() {
        return pasos;
    }

    public void setPasos(String x) {
        this.pasos = x;
    }

    public String toString() {
        return titulo + ": \n" + pasos;
    }
}
