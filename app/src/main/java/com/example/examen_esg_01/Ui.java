package com.example.examen_esg_01;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/** Utilidades compartidas por las pantallas. */
public class Ui {

    /** Evita que el contenido quede debajo de la barra de estado / teclado. */
    public static void aplicarInsets(View root) {
        ViewCompat.setOnApplyWindowInsetsListener(root, (v, insets) -> {
            Insets b = insets.getInsets(
                    WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
            v.setPadding(b.left, b.top, b.right, b.bottom);
            return insets;
        });
    }

    /** Ojito para mostrar/ocultar la contraseña. */
    public static void configurarVerClave(final EditText et, ImageView ojo) {
        final boolean[] visible = {false};
        ojo.setOnClickListener(v -> {
            visible[0] = !visible[0];
            et.setTransformationMethod(visible[0]
                    ? HideReturnsTransformationMethod.getInstance()
                    : PasswordTransformationMethod.getInstance());
            et.setSelection(et.getText().length());
        });
    }
}
