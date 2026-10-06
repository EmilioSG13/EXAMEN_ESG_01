package com.example.examen_esg_01;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/** Adapter del CustomListView de usuarios. */
public class UsuarioAdapter extends BaseAdapter {

    private final List<Usuario> datos;
    private final LayoutInflater inflater;

    public UsuarioAdapter(Context context, List<Usuario> datos) {
        this.datos = datos;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return datos.size(); }

    @Override
    public Usuario getItem(int position) { return datos.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    private static class Holder {
        TextView avatar, nombre, usuario;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder h;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_usuario, parent, false);
            h = new Holder();
            h.avatar = view.findViewById(R.id.tvAvatar);
            h.nombre = view.findViewById(R.id.tvNombre);
            h.usuario = view.findViewById(R.id.tvUsuario);
            view.setTag(h);
        } else {
            h = (Holder) view.getTag();
        }

        Usuario u = getItem(position);
        h.nombre.setText(u.getNombre());
        h.usuario.setText(u.getUsuario());
        h.avatar.setText(u.getInicial());

        Drawable bg = h.avatar.getBackground().mutate();
        if (bg instanceof GradientDrawable) {
            ((GradientDrawable) bg).setColor(u.getColor());
        }
        return view;
    }
}
