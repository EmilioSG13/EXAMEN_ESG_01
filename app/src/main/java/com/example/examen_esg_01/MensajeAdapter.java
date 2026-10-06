package com.example.examen_esg_01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/** Adapter del CustomListView del chat: dos tipos de fila (izquierda / derecha). */
public class MensajeAdapter extends BaseAdapter {

    private static final int TIPO_IZQUIERDA = 0;
    private static final int TIPO_DERECHA = 1;

    private final List<Mensaje> mensajes;
    private final LayoutInflater inflater;

    public MensajeAdapter(Context context, List<Mensaje> mensajes) {
        this.mensajes = mensajes;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() { return mensajes.size(); }

    @Override
    public Mensaje getItem(int position) { return mensajes.get(position); }

    @Override
    public long getItemId(int position) { return position; }

    @Override
    public int getViewTypeCount() { return 2; }

    @Override
    public int getItemViewType(int position) {
        return mensajes.get(position).isEnviado() ? TIPO_DERECHA : TIPO_IZQUIERDA;
    }

    private static class Holder {
        TextView texto, hora;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder h;
        if (convertView == null) {
            int layout = getItemViewType(position) == TIPO_DERECHA
                    ? R.layout.item_mensaje_der
                    : R.layout.item_mensaje_izq;
            convertView = inflater.inflate(layout, parent, false);
            h = new Holder();
            h.texto = convertView.findViewById(R.id.tvTexto);
            h.hora = convertView.findViewById(R.id.tvHora);
            convertView.setTag(h);
        } else {
            h = (Holder) convertView.getTag();
        }

        Mensaje m = getItem(position);
        h.texto.setText(m.getTexto());
        h.hora.setText(m.getHora());
        return convertView;
    }
}
