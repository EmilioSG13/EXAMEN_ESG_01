package com.example.examen_esg_01;

public class Usuario {
    private final String nombre;
    private final String usuario;
    private final int color;

    public Usuario(String nombre, String usuario, int color) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.color = color;
    }

    public String getNombre() { return nombre; }
    public String getUsuario() { return usuario; }
    public int getColor() { return color; }

    public String getInicial() {
        return nombre.isEmpty() ? "?" : nombre.substring(0, 1).toUpperCase();
    }
}
