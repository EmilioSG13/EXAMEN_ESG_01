package com.example.examen_esg_01;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsuario, etClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Ui.aplicarInsets(findViewById(R.id.root));

        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave);
        ImageView ivVer = findViewById(R.id.ivVer);
        Button btnIniciar = findViewById(R.id.btnIniciar);
        Button btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        TextView tvOlvido = findViewById(R.id.tvOlvido);

        // Si viene un usuario registrado, prellenarlo
        String usuarioReg = getIntent().getStringExtra("usuario_registrado");
        if (usuarioReg != null) {
            etUsuario.setText(usuarioReg);
        }

        Ui.configurarVerClave(etClave, ivVer);

        btnIniciar.setOnClickListener(v -> iniciarSesion());

        btnCrearCuenta.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class)));

        tvOlvido.setOnClickListener(v ->
                Toast.makeText(this, "Función no disponible", Toast.LENGTH_SHORT).show());
    }

    private void iniciarSesion() {
        String usuario = etUsuario.getText().toString().trim();
        String clave = etClave.getText().toString();

        if (usuario.isEmpty()) {
            etUsuario.setError("Ingrese su usuario o correo");
            etUsuario.requestFocus();
            return;
        }

        if (clave.isEmpty()) {
            etClave.setError("Ingrese su contraseña");
            etClave.requestFocus();
            return;
        }

        // Permitir acceso con admin/123456 o cualquier usuario/correo válido ingresado
        if ((usuario.equals("admin") && clave.equals("123456")) || (usuario.length() >= 3 && clave.length() >= 4)) {
            startActivity(new Intent(this, UsuariosActivity.class));
            finish();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
