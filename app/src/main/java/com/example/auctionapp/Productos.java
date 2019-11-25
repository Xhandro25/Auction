package com.example.auctionapp;

public class Productos {

    private String id_producto;
    private String nombre_producto;
    private String precio;
    private String descripcion;
    private String foto;
    private String id_usuario;
    private String id_proovedor;
    private String id_categoria;
    private String id_subcategoria;

    public Productos(String id_producto, String nombre_producto, String precio, String descripcion, String foto, String id_usuario, String id_proovedor, String id_categoria, String id_subcategoria) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio = precio;
        this.descripcion = descripcion;
        this.foto = foto;
        this.id_usuario = id_usuario;
        this.id_proovedor = id_proovedor;
        this.id_categoria = id_categoria;
        this.id_subcategoria = id_subcategoria;
    }

    public String getId_producto() {
        return id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public String getId_usuario() {
        return id_usuario;
    }

    public String getId_proovedor() {
        return id_proovedor;
    }

    public String getId_categoria() {
        return id_categoria;
    }

    public String getId_subcategoria() {
        return id_subcategoria;
    }
}

