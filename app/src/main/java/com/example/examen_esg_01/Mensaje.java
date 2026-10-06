package com.example.examen_esg_01;

public class Mensaje {
    private final String texto;
    private final String hora;
    private final boolean enviado; // true = derecha (yo), false = izquierda (el otro)

    public Mensaje(String texto, String hora, boolean enviado) {
        this.texto = texto;
        this.hora = hora;
        this.enviado = enviado;
    }

    public String getTexto() { return texto; }
    public String getHora() { return hora; }
    public boolean isEnviado() { return enviado; }
}
