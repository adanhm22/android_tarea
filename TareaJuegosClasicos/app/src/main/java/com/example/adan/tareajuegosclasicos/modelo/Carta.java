package com.example.adan.tareajuegosclasicos.modelo;

import android.graphics.drawable.Drawable;

public class Carta {
    private Drawable reverso,carta,actual;
    private int numCarta;
    private String nombre;

    public Drawable getReverso() {
        return reverso;
    }

    public void setReverso(Drawable reverso) {
        this.reverso = reverso;
    }

    public Drawable getCarta() {
        return carta;
    }

    public void setCarta(Drawable carta) {
        this.carta = carta;
    }

    public Drawable getActual() {
        return actual;
    }

    public void setActual(Drawable actual) {
        this.actual = actual;
    }

    public int getNumCarta() {
        return numCarta;
    }

    public void setNumCarta(int numCarta) {
        this.numCarta = numCarta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isRey(){
        return this.nombre.toLowerCase().contains("rey".toLowerCase());
    }

}
