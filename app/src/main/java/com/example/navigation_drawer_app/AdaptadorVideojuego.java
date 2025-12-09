package com.example.navigation_drawer_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdaptadorVideojuego extends BaseAdapter
{
    Context context;
    String videojuego[], genero[], plataforma[];
    int imagencita[];
    LayoutInflater inflater;

    public AdaptadorVideojuego(Context context, String[] videojuego, String[] genero, String[] plataforma, int[] imagencita, LayoutInflater inflater) {
        this.context = context;
        this.videojuego = videojuego;
        this.genero = genero;
        this.plataforma = plataforma;
        this.imagencita = imagencita;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return videojuego.length;
    }

    @Override
    public Object getItem(int position) {
        return videojuego[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=inflater.inflate(R.layout.modelovideojuego, null);

        TextView nombre=convertView.findViewById(R.id.texto);
        TextView generot=convertView.findViewById(R.id.genero);
        TextView plataformat=convertView.findViewById(R.id.plataforma);
        ImageView imagenv=convertView.findViewById(R.id.icono);
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

        nombre.setText(videojuego[position]);
        generot.setText(genero[position]);
        plataformat.setText(plataforma[position]);
        imagenv.setImageResource(imagencita[position]);
        return convertView;
    }
}
