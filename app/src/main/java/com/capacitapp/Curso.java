package com.capacitapp;

public class Curso {
    int id;
    String img;
    String titulo;
    String descripcion;
    double precio;

    public Curso(){

    }

    public Curso(int id,String img,String titulo, String descripcion, double precio) {
        this.id = id;
        this.img = img;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId() {
        return id;
    }

    public String getImg() {
        return img;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImg(String img){
        this.img=img;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


}


