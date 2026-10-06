package com.example.examen_esg_01;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class UsuariosActivity extends AppCompatActivity {

    private final List<Usuario> usuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        Ui.aplicarInsets(findViewById(R.id.root));

        cargarUsuarios();

        ListView lvUsuarios = findViewById(R.id.lvUsuarios);
        lvUsuarios.setAdapter(new UsuarioAdapter(this, usuarios));

        // Click / touch sobre un usuario -> chat
        lvUsuarios.setOnItemClickListener((parent, view, position, id) -> {
            Usuario u = usuarios.get(position);
            Intent i = new Intent(UsuariosActivity.this, ChatActivity.class);
            i.putExtra("nombre", u.getNombre());
            i.putExtra("usuario", u.getUsuario());
            i.putExtra("color", u.getColor());
            startActivity(i);
        });
    }

    /** Lista de contactos escolares. */
    private void cargarUsuarios() {
        String[][] datos = {
                {"Carlos Pérez", "carlos_p"}, {"María González", "maria_g"},
                {"Sofía Martínez", "sofia_m"}, {"Alejandro López", "alex_l"},
                {"Fernanda Gómez", "fernanda_g"}, {"Diego Ramírez", "diego_r"},
                {"Valeria Torres", "valeria_t"}, {"Mateo Morales", "mateo_m"},
                {"Lucía Castillo", "lucia_c"}, {"Daniel Herrera", "daniel_h"},
                {"Camila Vargas", "camila_v"}, {"Gabriel Castro", "gabriel_c"},
                {"Jimena Ruiz", "jimena_r"}, {"Emiliano Silva", "emiliano_s"},
                {"Regina Medina", "regina_m"}, {"Leonardo Ortiz", "leonardo_o"},
                {"Victoria Ríos", "victoria_r"}, {"Santiago Peña", "santiago_p"},
                {"Paula Navarro", "paula_n"}, {"David Mendoza", "david_m"}
        };
        int[] colores = {
                Color.parseColor("#1E78E6"), Color.parseColor("#8E44AD"),
                Color.parseColor("#27AE60"), Color.parseColor("#E67E22"),
                Color.parseColor("#E74C3C"), Color.parseColor("#16A085")
        };
        for (int i = 0; i < datos.length; i++) {
            usuarios.add(new Usuario(datos[i][0], datos[i][1], colores[i % colores.length]));
        }
    }
}
