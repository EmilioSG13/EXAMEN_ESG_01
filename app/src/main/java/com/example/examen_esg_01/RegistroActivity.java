package com.example.examen_esg_01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    private EditText etNombre, etCorreo, etUsuario, etClave, etConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Ui.aplicarInsets(findViewById(R.id.root));

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave);
        etConfirmar = findViewById(R.id.etConfirmar);

        Ui.configurarVerClave(etClave, findViewById(R.id.ivVer1));
        Ui.configurarVerClave(etConfirmar, findViewById(R.id.ivVer2));

        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        TextView tvYaTienes = findViewById(R.id.tvYaTienes);
        ImageView ivBack = findViewById(R.id.ivBack);

        btnRegistrar.setOnClickListener(v -> registrar());
        tvYaTienes.setOnClickListener(v -> irALogin());
        ivBack.setOnClickListener(v -> irALogin());
    }

    private void registrar() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String usuario = etUsuario.getText().toString().trim();
        String clave = etClave.getText().toString();
        String confirmar = etConfirmar.getText().toString();

        if (nombre.length() < 3) {
            etNombre.setError("El nombre debe tener mínimo 3 caracteres");
            etNombre.requestFocus();
            return;
        }

        if (!correo.contains("@")) {
            etCorreo.setError("Ingrese un correo electrónico válido");
            etCorreo.requestFocus();
            return;
        }

        if (usuario.length() < 3) {
            etUsuario.setError("El usuario debe tener mínimo 3 caracteres");
            etUsuario.requestFocus();
            return;
        }

        if (clave.length() < 6) {
            etClave.setError("La contraseña debe tener al menos 6 caracteres");
            etClave.requestFocus();
            return;
        }

        if (!clave.equals(confirmar)) {
            etConfirmar.setError("Las contraseñas no coinciden");
            etConfirmar.requestFocus();
            return;
        }

        Toast.makeText(this, "Cuenta creada correctamente", Toast.LENGTH_SHORT).show();
        irALogin();
    }

    private void irALogin() {
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("usuario_registrado", etUsuario.getText().toString().trim());
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(i);
        finish();
    }
}
