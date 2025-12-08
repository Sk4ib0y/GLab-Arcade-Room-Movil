package com.example.navigation_drawer_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorConsola extends BaseAdapter {

    Context context;
    String consola[], jugadores[];
    int imagencita[];
    LayoutInflater inflater;

    public AdaptadorConsola(Context context, String[] consola, String[] jugadores, int[] imagen, LayoutInflater inflater) {
        this.context = context;
        this.consola = consola;
        this.jugadores = jugadores;
        this.imagencita = imagen;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return consola.length;
    }

    @Override
    public Object getItem(int position) {
        return consola[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.modeloconsola, null);

        ImageView imagen=convertView.findViewById(R.id.icono);
        TextView nombre= convertView.findViewById(R.id.texto);
        TextView njugadores=convertView.findViewById(R.id.jugadores);
        ImageView edit=convertView.findViewById(R.id.editar);
        ImageView basura=convertView.findViewById(R.id.basura);

        edit.setOnClickListener(v -> {
            Intent intent= new Intent(context, NoDisponible.class);
            context.startActivity(intent);
                });

        basura.setOnClickListener(v -> {
            Intent intent= new Intent(context, NoDisponible.class);
            context.startActivity(intent);
                });

        imagen.setImageResource(imagencita[position]);
        nombre.setText(consola[position]);
        njugadores.setText(jugadores[position]);

        return convertView;
    }
}
