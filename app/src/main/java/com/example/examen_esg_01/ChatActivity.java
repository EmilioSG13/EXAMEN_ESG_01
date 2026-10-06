package com.example.examen_esg_01;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatActivity extends AppCompatActivity {

    private final List<Mensaje> mensajes = new ArrayList<>();
    private MensajeAdapter adapter;
    private ListView lvMensajes;
    private EditText etMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Ui.aplicarInsets(findViewById(R.id.root));

        String nombreRecibido = getIntent().getStringExtra("nombre");
        int color = getIntent().getIntExtra("color", 0xFF1E78E6);
        if (nombreRecibido == null) nombreRecibido = "Usuario";

        // Encabezado
        TextView tvNombre = findViewById(R.id.tvNombre);
        TextView tvAvatar = findViewById(R.id.tvAvatar);
        tvNombre.setText(nombreRecibido);
        tvAvatar.setText(nombreRecibido.substring(0, 1).toUpperCase());
        Drawable bg = tvAvatar.getBackground().mutate();
        if (bg instanceof GradientDrawable) {
            ((GradientDrawable) bg).setColor(color);
        }
        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(v -> finish());

        // Lista de mensajes (CustomListView)
        lvMensajes = findViewById(R.id.lvMensajes);
        etMensaje = findViewById(R.id.etMensaje);

        cargarMensajes(nombreRecibido.split(" ")[0]);
        adapter = new MensajeAdapter(this, mensajes);
        lvMensajes.setAdapter(adapter);
        lvMensajes.setSelection(mensajes.size() - 1);

        ImageView ivEnviar = findViewById(R.id.ivEnviar);
        ivEnviar.setOnClickListener(v -> enviarMensaje());
    }

    /** Conversación simulada sobre la escuela. */
    private void cargarMensajes(String nombre) {
        String[] textos = {
                "¡Hola " + nombre + "! Oye, ¿ya terminaste la tarea de la escuela?",
                "¡Hola! Casi, me falta resolver los últimos ejercicios de matemáticas",
                "A mí también me costó un poco ese tema, ¿me ayudas?",
                "¡Claro que sí! Nos vemos en la biblioteca de la escuela más tarde",
                "Perfecto, llevo mis apuntes y mi cuaderno",
                "Excelente, nos vemos a las 3:00 PM saliendo de clases",
                "Vale, avísame cuando llegues. ¡Nos vemos!"
        };
        for (int i = 0; i < textos.length; i++) {
            String hora = String.format(Locale.getDefault(), "08:%02d", 40 + i);
            mensajes.add(new Mensaje(textos[i], hora, i % 2 == 0));
        }
    }

    private void enviarMensaje() {
        String texto = etMensaje.getText().toString().trim();
        if (texto.isEmpty()) return;

        String hora = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        mensajes.add(new Mensaje(texto, hora, true));
        adapter.notifyDataSetChanged();
        lvMensajes.setSelection(mensajes.size() - 1);
        etMensaje.setText("");
    }
}
