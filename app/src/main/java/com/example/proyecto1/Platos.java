package com.example.proyecto1;

public class Platos {
    private Integer dia;
    private Integer mes;
    private Integer año;
    private String plato;
    public Platos(Integer p1,Integer p2,Integer p3,String p4){
        this.dia=p1;
        this.mes=p2;
        this.año=p3;
        this.plato=p4;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer x) {
        this.dia = x;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer x) {
        this.mes = x;
    }

    public Integer getAño() {
        return año;
    }

    public void setAño(Integer x) {
        this.año = x;
    }

    public String getPlato() {
        return plato;
    }

    public void setPlato(String x) {
        this.plato = x;
    }

    public String toString() {
        return this.plato;
    }
}