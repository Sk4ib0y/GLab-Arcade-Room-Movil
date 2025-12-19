package com.example.navigation_drawer_app.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigation_drawer_app.Actividades.NoDisponible;
import com.example.navigation_drawer_app.Clases.SesionManager;
import com.example.navigation_drawer_app.R;

public class AdaptadorConsolaPrecio extends BaseAdapter {

    Context context;
    String consola[], jugadores[], precio[];
    int imagencita[];
    LayoutInflater inflater;
    SesionManager sesionManager;

    public AdaptadorConsolaPrecio(Context context, String[] consola, String[] jugadores, String[] precio, int[] imagencita, LayoutInflater inflater) {
        this.context = context;
        this.consola = consola;
        this.jugadores = jugadores;
        this.precio = precio;
        this.imagencita = imagencita;
        this.inflater = inflater;
        this.sesionManager=new SesionManager(context);
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

        boolean login=sesionManager.isAdmin();

        TextView precioo=convertView.findViewById(R.id.precio);
        ImageView imagen=convertView.findViewById(R.id.icono);
        TextView nombre= convertView.findViewById(R.id.texto);
        TextView njugadores=convertView.findViewById(R.id.jugadores);
        ImageView edit=convertView.findViewById(R.id.editar);
        ImageView basura=convertView.findViewById(R.id.basura);


        if(!login)
        {
            edit.setVisibility(View.GONE);
            basura.setVisibility(View.GONE);
        }
        else
        {
        edit.setOnClickListener(v -> {
            Intent intent= new Intent(context, NoDisponible.class);
            context.startActivity(intent);
                });

        basura.setOnClickListener(v -> {
            Intent intent= new Intent(context, NoDisponible.class);
            context.startActivity(intent);
                });
        }

        imagen.setImageResource(imagencita[position]);
        nombre.setText(consola[position]);
        precioo.setText(precio[position]);
        njugadores.setText(jugadores[position]);

        return convertView;
    }
}
