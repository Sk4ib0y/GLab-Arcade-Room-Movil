package com.example.navigation_drawer_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AdaptadorInventario extends BaseAdapter
{
    Context context;
    String consola[], observaciones[], estado[];
    int imagencita[];
    LayoutInflater inflater;

    public AdaptadorInventario(Context context, String[] consola, String[] observaciones, String[] estado, int[] imagencita, LayoutInflater inflater) {
        this.context = context;
        this.consola = consola;
        this.observaciones = observaciones;
        this.estado = estado;
        this.imagencita = imagencita;
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
        convertView=inflater.inflate(R.layout.modeloinventario, null);
        TextView consolat=convertView.findViewById(R.id.texto);
        TextView estadot=convertView.findViewById(R.id.estado);
        TextView observaciont=convertView.findViewById(R.id.observaciones);
        ImageView imagen=convertView.findViewById(R.id.icono);
        ImageView edit=convertView.findViewById(R.id.editar);
        ImageView borrar=convertView.findViewById(R.id.basura);

        edit.setOnClickListener(v -> {
            Intent intent= new Intent(context, NoDisponible.class);
            context.startActivity(intent);
        });
        borrar.setOnClickListener(v -> {
            Intent intent= new Intent(context, NoDisponible.class);
            context.startActivity(intent);
        });

        consolat.setText(consola[position]);
        estadot.setText(estado[position]);
        observaciont.setText(observaciones[position]);
        imagen.setImageResource(imagencita[position]);
        return convertView;
    }
}
