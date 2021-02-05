package com.johancap115.materialdesignintro.pojo;

import android.content.Intent;

import java.io.Serializable;

public class Mascota implements Serializable {
    private int foto;
    private String nombre;
    private int raiting;
    private boolean like;

    public Mascota(int foto, String nombre) {
        this.foto = foto;
        this.nombre = nombre;
        this.raiting = 0;
        this.like = false;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting += raiting;
    }

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }
}
